package com.app.repository.model;

public class Medias {
    private Double mayorMaximoMedia;
    private Double menorMinimoMedia;
    private Double primerPrecioAperturaMedia;
    private Double ultimoPrecioCierreMedia;

    public Medias() {

    }

    public Medias(
            Double mayorMaximoMedia, Double menorMinimoMedia,
            Double primerPrecioAperturaMedia, Double ultimoPrecioCierreMedia
    ) {
        this.mayorMaximoMedia = mayorMaximoMedia;
        this.menorMinimoMedia = menorMinimoMedia;
        this.primerPrecioAperturaMedia = primerPrecioAperturaMedia;
        this.ultimoPrecioCierreMedia = ultimoPrecioCierreMedia;
    }

    public Double getMayorMaximoMedia() {
        return mayorMaximoMedia;
    }

    public void setMayorMaximoMedia(Double mayorMaximoMedia) {
        this.mayorMaximoMedia = mayorMaximoMedia;
    }

    public Double getMenorMinimoMedia() {
        return menorMinimoMedia;
    }

    public void setMenorMinimoMedia(Double menorMinimoMedia) {
        this.menorMinimoMedia = menorMinimoMedia;
    }

    public Double getPrimerPrecioAperturaMedia() {
        return primerPrecioAperturaMedia;
    }

    public void setPrimerPrecioAperturaMedia(Double primerPrecioAperturaMedia) {
        this.primerPrecioAperturaMedia = primerPrecioAperturaMedia;
    }

    public Double getUltimoPrecioCierreMedia() {
        return ultimoPrecioCierreMedia;
    }

    public void setUltimoPrecioCierreMedia(Double ultimoPrecioCierreMedia) {
        this.ultimoPrecioCierreMedia = ultimoPrecioCierreMedia;
    }
}
