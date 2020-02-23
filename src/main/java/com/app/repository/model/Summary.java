package com.app.repository.model;

import java.sql.Date;

public class Summary {
    private Date fecha;
    private Double primerPrecioDeApertura;
    private Double ultimoPrecioDeApertura;
    private Double mayorMaximo;
    private Double menorMinimo;

    public Summary() {}

    public Summary(
            Date fecha, Double primerPrecioDeApertura, Double ultimoPrecioDeApertura,
            Double mayorMaximo, Double menorMinimo
    ) {
        this.fecha = fecha;
        this.primerPrecioDeApertura = primerPrecioDeApertura;
        this.ultimoPrecioDeApertura = ultimoPrecioDeApertura;
        this.mayorMaximo = mayorMaximo;
        this.menorMinimo = menorMinimo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getPrimerPrecioDeApertura() {
        return primerPrecioDeApertura;
    }

    public void setPrimerPrecioDeApertura(Double primerPrecioDeApertura) {
        this.primerPrecioDeApertura = primerPrecioDeApertura;
    }

    public Double getUltimoPrecioDeApertura() {
        return ultimoPrecioDeApertura;
    }

    public void setUltimoPrecioDeApertura(Double ultimoPrecioDeApertura) {
        this.ultimoPrecioDeApertura = ultimoPrecioDeApertura;
    }

    public Double getMayorMaximo() {
        return mayorMaximo;
    }

    public void setMayorMaximo(Double mayorMaximo) {
        this.mayorMaximo = mayorMaximo;
    }

    public Double getMenorMinimo() {
        return menorMinimo;
    }

    public void setMenorMinimo(Double menorMinimo) {
        this.menorMinimo = menorMinimo;
    }
}
