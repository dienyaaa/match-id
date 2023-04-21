package com.match.calculator.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;
import java.util.TreeMap;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username, email;

    private Gender gender;
    private double height, breast, waist, hips, foot;

    private double shoeSize; //TODO: заменить на новую модель

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

    public double getFoot() {
        return foot;
    }

    public void setFoot(double foot) {
        this.foot = foot;
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

    public User() {
    }

    public double getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize() {
        TreeMap<Double, Double> sizesMan = new TreeMap<Double, Double>()
        {{
            put(250.0, 39.0);
            put(257.0,40.0);
            put(263.0,41.0);
            put(270.0,42.0);
            put(277.0,43.0);
            put(283.0,44.0);
            put(290.0,45.0);
            put(297.0,46.0);
            put(303.0,47.0);
        }};
        TreeMap<Double, Double> sizesWoman = new TreeMap<Double, Double>()
        {{
            put(223.0, 35.0);
            put(230.0, 36.0);
            put(237.0, 37.0);
            put(243.0, 38.0);
            put(250.0, 39.0);
            put(257.0, 40.0);
            put(263.0, 41.0);
        }};

        double key = this.foot;
        TreeMap<Double, Double> map = new TreeMap<Double, Double>();
        if (this.gender == Gender.MAN)
        {
            map = sizesMan;
        }
        else if (this.gender == Gender.WOMAN) {
            map = sizesWoman;
        }
        Map.Entry<Double, Double> low = map.floorEntry(key);
        Map.Entry<Double, Double> high = map.ceilingEntry(key);
        Double res = null;
        if (low != null && high != null) {
            res = Math.abs(key-low.getKey()) < Math.abs(key-high.getKey())
                    ?   low.getValue()
                    :   high.getValue();
        } else if (low != null || high != null) {
            res = low != null ? low.getValue() : high.getValue();
        }
        this.shoeSize = res;
    }
}
