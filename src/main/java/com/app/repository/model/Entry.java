package com.app.repository.model;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Entry {
    private String Fecha;
    private String Hora;
    private double apertura;
    private double cierre;
    private double maximo;
    private double minimo;

    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");

    public Entry() {}

    public Entry(String Fecha, String Hora, double apertura, double cierre, double maximo, double minimo) {
        this.Fecha = Fecha;
        this.Hora = Hora;
        this.apertura = apertura;
        this.cierre = cierre;
        this.maximo = maximo;
        this.minimo = minimo;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public void setHora(String Hora) {
        this.Hora = Hora;
    }

    public void setApertura(double apertura) {
        this.apertura = apertura;
    }

    public void setCierre(double cierre) {
        this.cierre = cierre;
    }

    public void setMaximo(double maximo) {
        this.maximo = maximo;
    }

    public void setMinimo(double minimo) {
        this.minimo = minimo;
    }

    public Date getFecha() {
        try {
            return new Date(dateFormat.parse(Fecha).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Time getHora() {
        try {
            return new Time(hourFormat.parse(Hora).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public double getApertura() {
        return apertura;
    }

    public double getCierre() {
        return cierre;
    }

    public double getMaximo() {
        return maximo;
    }

    public double getMinimo() {
        return minimo;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "fecha='" + Fecha + '\'' +
                ", hora='" + Hora + '\'' +
                ", apertura='" + apertura + '\'' +
                ", cierre='" + cierre + '\'' +
                ", maximo=" + maximo +
                ", minimo=" + minimo +
                '}';
    }
}
