package com.match.calculator.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class Size {
    private double sizeRU;
    private double sizeEU;
    private double sizeUS;

    public Size(double sizeRU, double sizeEU, double sizeUS) {
        this.sizeRU = sizeRU;
        this.sizeEU = sizeEU;
        this.sizeUS = sizeUS;
    }

    public double getSizeRU() {
        return sizeRU;
    }

    public void setSizeRU(double sizeRU) {
        this.sizeRU = sizeRU;
    }

    public double getSizeEU() {
        return sizeEU;
    }

    public void setSizeEU(double sizeEU) {
        this.sizeEU = sizeEU;
    }

    public double getSizeUS() {
        return sizeUS;
    }

    public void setSizeUS(double sizeUS) {
        this.sizeUS = sizeUS;
    }
}
