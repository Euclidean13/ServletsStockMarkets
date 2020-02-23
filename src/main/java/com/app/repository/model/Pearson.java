package com.app.repository.model;

public class Pearson {
    private double ppaAndMmaxPearson;
    private double ppaAndMminPearson;
    private double upcAndMmaxPearson;
    private double upcAndMminPearson;

    public Pearson() {

    }

    public Pearson(double ppaAndMmaxPearson, double ppaAndMminPearson, double upcAndMmaxPearson, double upcAndMminPearson) {
        this.ppaAndMmaxPearson = ppaAndMmaxPearson;
        this.ppaAndMminPearson = ppaAndMminPearson;
        this.upcAndMmaxPearson = upcAndMmaxPearson;
        this.upcAndMminPearson = upcAndMminPearson;
    }

    public double getPpaAndMmaxPearson() {
        return ppaAndMmaxPearson;
    }

    public void setPpaAndMmaxPearson(double ppaAndMmaxPearson) {
        this.ppaAndMmaxPearson = ppaAndMmaxPearson;
    }

    public double getPpaAndMminPearson() {
        return ppaAndMminPearson;
    }

    public void setPpaAndMminPearson(double ppaAndMminPearson) {
        this.ppaAndMminPearson = ppaAndMminPearson;
    }

    public double getUpcAndMmaxPearson() {
        return upcAndMmaxPearson;
    }

    public void setUpcAndMmaxPearson(double upcAndMmaxPearson) {
        this.upcAndMmaxPearson = upcAndMmaxPearson;
    }

    public double getUpcAndMminPearson() {
        return upcAndMminPearson;
    }

    public void setUpcAndMminPearson(double upcAndMminPearson) {
        this.upcAndMminPearson = upcAndMminPearson;
    }
}
