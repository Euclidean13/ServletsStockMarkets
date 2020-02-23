package com.app.repository.dao;

import com.app.repository.model.Entry;
import com.app.repository.model.Medias;
import com.app.repository.model.Summary;

import javax.servlet.http.HttpServlet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBRepository {
    private Connection con;

    private static final String WRITE_SQL1 = "INSERT INTO registers(fecha, hora, apertura, cierre, maximo, minimo) " +
            "VALUES(?, ?, ?, ?, ?, ?)";

    private static final String WRITE_SQL2 = "INSERT INTO summary(fecha, primer_precio_apertura, ultimo_precio_cierre," +
            " mayor_maximo, menor_minimo) VALUES(?, ?, ?, ?, ?)";

    private static final String ALL_MEDIAS = "SELECT SUM(mayor_maximo)/COUNT(mayor_maximo) mmax, " +
            "SUM(menor_minimo)/COUNT(menor_minimo) mmin, SUM(primer_precio_apertura)/COUNT(primer_precio_apertura) " +
            "ppa, SUM(ultimo_precio_cierre)/COUNT(ultimo_precio_cierre) upc FROM summary";

    private static final String NUM_DATOS_SUMMARY = "SELECT count(*) numero FROM summary";

    private static final String ALL_SUMMARY_DATA = "SELECT * FROM summary";

    public DBRepository(Connection connection) {
        this.con = connection;
    }

    public void saveEntryIntoRegisters(Entry entry) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(WRITE_SQL1);
            ps.setDate(1, entry.getFecha());
            ps.setTime(2, entry.getHora());
            ps.setDouble(3, entry.getApertura());
            ps.setDouble(4, entry.getCierre());
            ps.setDouble(5, entry.getMaximo());
            ps.setDouble(6, entry.getMinimo());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveCalculationsIntoSummary(
            double primerPrecioApertura, double ultimoPrecioApertura, double mayorMaximo,
            double menorMinimo
    ) {
        PreparedStatement ps = null;
        Date currentDate = new Date(System.currentTimeMillis());
        try {
            ps = con.prepareStatement(WRITE_SQL2);
            ps.setDate(1, currentDate);
            ps.setDouble(2, primerPrecioApertura);
            ps.setDouble(3, ultimoPrecioApertura);
            ps.setDouble(4, mayorMaximo);
            ps.setDouble(5, menorMinimo);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Medias getMedias() {
        Medias medias = new Medias();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(ALL_MEDIAS);
            rs = ps.executeQuery();
            rs.next();
            medias.setMayorMaximoMedia(rs.getDouble("mmax"));
            medias.setMenorMinimoMedia(rs.getDouble("mmin"));
            medias.setPrimerPrecioAperturaMedia(rs.getDouble("ppa"));
            medias.setUltimoPrecioCierreMedia(rs.getDouble("upc"));
            ps.close();
            return medias;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getNumeroDatosSummary() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(NUM_DATOS_SUMMARY);
            rs = ps.executeQuery();
            rs.next();
            int numeroDatosSummary = rs.getInt("numero");
            ps.close();
            return numeroDatosSummary;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<Summary> getAllSummaryData() {
        List<Summary> summaries = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(ALL_SUMMARY_DATA);
            rs = ps.executeQuery();
            while(rs.next()) {
                Summary summary = new Summary();
                summary.setFecha(rs.getDate("fecha"));
                summary.setMayorMaximo(rs.getDouble("mayor_maximo"));
                summary.setMenorMinimo(rs.getDouble("menor_minimo"));
                summary.setPrimerPrecioDeApertura(rs.getDouble("primer_precio_apertura"));
                summary.setUltimoPrecioDeApertura(rs.getDouble("ultimo_precio_cierre"));

                summaries.add(summary);
            }
            ps.close();
            return summaries;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
