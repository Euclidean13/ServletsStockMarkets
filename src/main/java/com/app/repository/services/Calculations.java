package com.app.repository.services;

import com.app.repository.dao.DBRepository;
import com.app.repository.model.*;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

public class Calculations {

    private Connection con;

    public Calculations(Connection connection) {
        this.con = connection;
    }

    public void saveRegistriesCalculationsIntoSummary(List<Entry> entryList, Connection con) {
        DBRepository dbRepository = new DBRepository(con);

        double primerPrecioApertura = entryList.get(0).getApertura();
        double ultimoPrecioCierre = entryList.get(entryList.size() - 1).getCierre();
        double mayorMaximo = getMayorMaximo(entryList);
        double menorMinimo = getMenorMinimo(entryList);

        dbRepository.saveCalculationsIntoSummary(primerPrecioApertura, ultimoPrecioCierre, mayorMaximo, menorMinimo);
    }

    public double getMayorMaximo(List<Entry> entryList) {
        double mayorMaximo = entryList.get(0).getMaximo();
        List<Double> listMayorMaximos = entryList.stream().map(Entry::getMaximo).collect(Collectors.toList());
        for (Double mmax : listMayorMaximos) {
            if (mmax > mayorMaximo) {
                mayorMaximo = mmax;
            }
        }
        return mayorMaximo;
    }

    public double getMenorMinimo(List<Entry> entryList) {
        double menorMinimo = entryList.get(0).getMinimo();
        List<Double> listMenorMinimos = entryList.stream().map(Entry::getMinimo).collect(Collectors.toList());
        for (Double mmin : listMenorMinimos) {
            if (mmin < menorMinimo) {
                menorMinimo = mmin;
            }
        }
        return menorMinimo;
    }

    public Summary getStandardDeviation() {
        Summary summary = new Summary();

        DBRepository dbRepository = new DBRepository(con);
        Medias medias = dbRepository.getMedias();
        int numeroDatos = dbRepository.getNumeroDatosSummary();
        List<Summary> summaryList = dbRepository.getAllSummaryData();

        Double ppa = getDeviation(
                summaryList.stream().map(Summary::getPrimerPrecioDeApertura).collect(Collectors.toList()),
                medias.getPrimerPrecioAperturaMedia(), numeroDatos
        );

        Double upc = getDeviation(
                summaryList.stream().map(Summary::getUltimoPrecioDeApertura).collect(Collectors.toList()),
                medias.getUltimoPrecioCierreMedia(), numeroDatos
        );

        Double mmax = getDeviation(
                summaryList.stream().map(Summary::getMayorMaximo).collect(Collectors.toList()),
                medias.getMayorMaximoMedia(), numeroDatos
        );

        Double mmin = getDeviation(
                summaryList.stream().map(Summary::getMenorMinimo).collect(Collectors.toList()),
                medias.getMenorMinimoMedia(), numeroDatos
        );

        summary.setPrimerPrecioDeApertura(ppa);
        summary.setUltimoPrecioDeApertura(upc);
        summary.setMayorMaximo(mmax);
        summary.setMenorMinimo(mmin);

        return summary;
    }

    public Double getDeviation(List<Double> summaryList, double media, int numeroDatos) {
        double summation = 0.0;
        for (Double elem : summaryList) {
            summation = summation + Math.pow(elem - media, 2);
        }
        return Math.sqrt(summation / numeroDatos);
    }

    public Summary getVariance() {
        Summary deviation = getStandardDeviation();
        Summary variance = new Summary();
        variance.setPrimerPrecioDeApertura(Math.pow(deviation.getPrimerPrecioDeApertura(), 2));
        variance.setUltimoPrecioDeApertura(Math.pow(deviation.getUltimoPrecioDeApertura(), 2));
        variance.setMayorMaximo(Math.pow(deviation.getMayorMaximo(), 2));
        variance.setMenorMinimo(Math.pow(deviation.getMenorMinimo(), 2));

        return variance;
    }

    public Covariances getSummaryCovariances() {
        Covariances covariances = new Covariances();

        DBRepository dbRepository = new DBRepository(con);
        Medias medias = dbRepository.getMedias();
        int numeroDatos = dbRepository.getNumeroDatosSummary();
        List<Summary> summaryList = dbRepository.getAllSummaryData();

        List<Double> ppa = summaryList.stream().map(Summary::getPrimerPrecioDeApertura).collect(Collectors.toList());
        List<Double> upc = summaryList.stream().map(Summary::getUltimoPrecioDeApertura).collect(Collectors.toList());
        List<Double> mmax = summaryList.stream().map(Summary::getMayorMaximo).collect(Collectors.toList());
        List<Double> mmin = summaryList.stream().map(Summary::getMenorMinimo).collect(Collectors.toList());

        Double ppaMmax = getCovariance(ppa, mmax, medias.getPrimerPrecioAperturaMedia(), medias.getMayorMaximoMedia(), numeroDatos);
        Double ppaMmin = getCovariance(ppa, mmin, medias.getPrimerPrecioAperturaMedia(), medias.getMenorMinimoMedia(), numeroDatos);
        Double upcMmax = getCovariance(upc, mmax, medias.getUltimoPrecioCierreMedia(), medias.getMayorMaximoMedia(), numeroDatos);
        Double upcMmin = getCovariance(upc, mmin, medias.getUltimoPrecioCierreMedia(), medias.getMenorMinimoMedia(), numeroDatos );

        covariances.setPpaAndMmax(ppaMmax);
        covariances.setPpaAndMmin(ppaMmin);
        covariances.setUpcAndMmax(upcMmax);
        covariances.setUpcAndMmin(upcMmin);

        return covariances;
    }

    public Double getCovariance(List<Double> var1, List<Double> var2, double media1, double media2, int numeroDatos){
        double summation = 0.0;
        for (int i = 0; i < var1.size()-1; i++) {
            summation = summation + ((var1.get(i)-media1)*(var2.get(i)-media2));
        }
        return summation/numeroDatos;
    }

    public Pearson getSummaryPearson() {
        Pearson pearson = new Pearson();
        Covariances covariances = getSummaryCovariances();
        Summary deviation = getStandardDeviation();

        pearson.setPpaAndMmaxPearson(getPearson(covariances.getPpaAndMmax(), deviation.getPrimerPrecioDeApertura(), deviation.getMayorMaximo()));
        pearson.setPpaAndMminPearson(getPearson(covariances.getPpaAndMmin(), deviation.getPrimerPrecioDeApertura(), deviation.getMenorMinimo()));
        pearson.setUpcAndMmaxPearson(getPearson(covariances.getUpcAndMmax(), deviation.getUltimoPrecioDeApertura(), deviation.getMayorMaximo()));
        pearson.setUpcAndMminPearson(getPearson(covariances.getUpcAndMmin(), deviation.getUltimoPrecioDeApertura(), deviation.getMenorMinimo()));

        return pearson;
    }

    public double getPearson(double covariance, double deviation1, double deviaton2) {
        if (covariance == 0) {
            return 0.0;
        } else {
            return covariance/(deviation1 * deviaton2);
        }
    }
}
