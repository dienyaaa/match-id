package com.match.calculator.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Size {
    @Column(name = "ru_size")
    private double sizeRU;
    @Column(name = "eu_size")
    private double sizeEU;
    @Column(name = "us_size")
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
