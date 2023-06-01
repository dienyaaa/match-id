package com.match.calculator.models;

import jakarta.persistence.*;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username, email;
    private Gender gender;
    private double height, chest, waist, hips, footLength;
    private double shoeSizeRU, shoeSizeEU, shoeSizeUS;
    private String pantsSizeRU, pantsSizeEU;
    private String shirtSizeRU, shirtSizeEU, shirtSizeUS;

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

    public double getChest() {
        return chest;
    }

    public void setChest(double chest) {
        this.chest = chest;
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

    public double getShoeSizeRU() {
        return shoeSizeRU;
    }

    public double getShoeSizeEU() {
        return shoeSizeEU;
    }

    public double getShoeSizeUS() {
        return shoeSizeUS;
    }

    public void setShoeSize() {
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("static/shoe_sizes.yml");
        Map<String, List<Map<String, Object>>> data = yaml.load(inputStream);
        for (Map<String, Object> item : data.get("spring_web_calculator.shoe_sizes")) {
            String itemGender = (String) item.get("gender");
            double itemFootLength = (double) item.get("foot_length");
            if (String.valueOf(gender).equals(itemGender) && footLength <= itemFootLength) {
                shoeSizeRU = (double) item.get("ru_size");
                shoeSizeEU = (double) item.get("eu_size");
                shoeSizeUS = (double) item.get("us_size");
                break;
            }
        }
    }

    public String getPantsSizeRU() {
        return pantsSizeRU;
    }

    public String getPantsSizeEU() {
        return pantsSizeEU;
    }

    public void setPantsSize() {
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("static/pants_sizes.yml");
        Map<String, List<Map<String, Object>>> data = yaml.load(inputStream);
        double closestWaist = Double.MAX_VALUE;
        double closestHips = Double.MAX_VALUE;
        double closestSizeWaist = Double.MAX_VALUE;
        double closestSizeHips = Double.MAX_VALUE;
        for (Map<String, Object> item : data.get("spring_web_calculator.pants_sizes")) {
            String itemGender = (String) item.get("gender");
            double itemWaist = (double) item.get("waist");
            double itemHips = (double) item.get("hips");
            double itemSize = (double) item.get("ru_size");
            if (String.valueOf(gender).equals(itemGender)) {
                if (waist <= itemWaist && closestSizeWaist > itemSize) {
                    closestWaist = itemWaist;
                    closestSizeWaist = itemSize;
                }
                if (hips <= itemHips && closestSizeHips > itemSize) {
                    closestHips = itemHips;
                    closestSizeHips = itemSize;
                }
            }
        }
        double size = Math.max(closestSizeWaist, closestSizeHips);
        pantsSizeRU = String.valueOf(size);
        for (Map<String, Object> item : data.get("spring_web_calculator.pants_sizes")) {
            String itemGender = (String) item.get("gender");
            double itemSize = (double) item.get("ru_size");
            if (String.valueOf(gender).equals(itemGender) && itemSize == size) {
                pantsSizeEU = (String) item.get("eu_size");
            }
        }
        if (closestWaist == Double.MAX_VALUE || closestHips == Double.MAX_VALUE) {
            if (String.valueOf(gender).equals("WOMAN")) {
                pantsSizeRU = String.valueOf(70.0);
                pantsSizeEU = "5XL";
            } else if (String.valueOf(gender).equals("MAN")) {
                pantsSizeRU = String.valueOf(70.0);
                pantsSizeEU = "5XL";
            }
        }
    }

    public String getShirtSizeRU() {
        return shirtSizeRU;
    }

    public String getShirtSizeEU() {
        return shirtSizeEU;
    }

    public String getShirtSizeUS() {
        return shirtSizeUS;
    }

    public void setShirtSize() {
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("static/shirt_sizes.yml");
        Map<String, List<Map<String, Object>>> data = yaml.load(inputStream);
        double closestChest = Double.MAX_VALUE;
        double closestWaist = Double.MAX_VALUE;
        double closestHips = Double.MAX_VALUE;
        double closestSizeChest = Double.MAX_VALUE;
        double closestSizeWaist = Double.MAX_VALUE;
        double closestSizeHips = Double.MAX_VALUE;
        for (Map<String, Object> item : data.get("spring_web_calculator.shirt_sizes")) {
            String itemGender = (String) item.get("gender");
            double itemChest = (double) item.get("chest");
            double itemWaist = (double) item.get("waist");
            double itemHips = (double) item.get("hips");
            double itemSize = (double) item.get("ru_size");
            if (String.valueOf(gender).equals(itemGender)) {
                if (chest <= itemChest && closestSizeChest > itemSize) {
                    closestChest = itemChest;
                    closestSizeChest = itemSize;
                }
                if (waist <= itemWaist && closestSizeWaist > itemSize) {
                    closestWaist = itemWaist;
                    closestSizeWaist = itemSize;
                }
                if (hips <= itemHips && closestSizeHips > itemSize) {
                    closestHips = itemHips;
                    closestSizeHips = itemSize;
                }
            }
        }
        double size = Math.max(closestSizeChest, Math.max(closestSizeWaist, closestSizeHips));
        shirtSizeRU = String.valueOf(size);
        for (Map<String, Object> item : data.get("spring_web_calculator.shirt_sizes")) {
            String itemGender = (String) item.get("gender");
            double itemSize = (double) item.get("ru_size");
            if (String.valueOf(gender).equals(itemGender) && itemSize == size) {
                shirtSizeEU = item.get("eu_size").toString();
                shirtSizeUS = item.get("us_size").toString();
            }
        }
        if (closestChest == Double.MAX_VALUE || closestWaist == Double.MAX_VALUE || closestHips == Double.MAX_VALUE) {
            if (String.valueOf(gender).equals("WOMAN")) {
                shirtSizeRU = String.valueOf(54.0);
                shirtSizeEU = "XXL";
                shirtSizeUS = String.valueOf(16.0);
            } else if (String.valueOf(gender).equals("MAN")) {
                shirtSizeRU = String.valueOf(60.0);
                shirtSizeEU = "XXL";
                shirtSizeUS = String.valueOf(20.0);
            }
        }
    }

    public User() {
    }
}
