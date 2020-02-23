package com.app.repository.model;

public class Covariances {
    private double ppaAndMmax;
    private double ppaAndMmin;
    private double upcAndMmax;
    private double upcAndMmin;

    public Covariances() {

    }

    public Covariances(double ppaAndMmax, double ppaAndMmin, double upcAndMmax, double upcAndMmin) {
        this.ppaAndMmax = ppaAndMmax;
        this.ppaAndMmin = ppaAndMmin;
        this.upcAndMmax = upcAndMmax;
        this.upcAndMmin = upcAndMmin;
    }

    public double getPpaAndMmax() {
        return ppaAndMmax;
    }

    public void setPpaAndMmax(double ppaAndMmax) {
        this.ppaAndMmax = ppaAndMmax;
    }

    public double getPpaAndMmin() {
        return ppaAndMmin;
    }

    public void setPpaAndMmin(double ppaAndMmin) {
        this.ppaAndMmin = ppaAndMmin;
    }

    public double getUpcAndMmax() {
        return upcAndMmax;
    }

    public void setUpcAndMmax(double upcAndMmax) {
        this.upcAndMmax = upcAndMmax;
    }

    public double getUpcAndMmin() {
        return upcAndMmin;
    }

    public void setUpcAndMmin(double upcAndMmin) {
        this.upcAndMmin = upcAndMmin;
    }
}
