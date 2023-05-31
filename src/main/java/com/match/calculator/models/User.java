package com.match.calculator.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username, email;

    private Gender gender;
    private double height, breast, waist, hips, footLength;
    private Shoes shoeSize;

    public String getGender() {
        if (gender == Gender.MAN) {
            return "мужской";
        }
        else if (gender == Gender.WOMAN) {
            return "женский";
        }
        return null;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getBreast() {
        return breast;
    }

    public void setBreast(double breast) {
        this.breast = breast;
    }

    public double getWaist() {
        return waist;
    }

    public void setWaist(double waist) {
        this.waist = waist;
    }

    public double getHips() {
        return hips;
    }

    public void setHips(double hips) {
        this.hips = hips;
    }

    public double getFootLength() {
        return footLength;
    }

    public void setFootLength(double footLength) {
        this.footLength = footLength;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Shoes getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize() {
        this.shoeSize = new Shoes(gender, footLength);
    }

    public User() {
    }
}
