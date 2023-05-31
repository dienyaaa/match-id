package com.match.calculator.models;

import com.match.calculator.DatabaseConfig;
import jakarta.persistence.*;

import java.sql.*;

@Entity
@Table(name = "shoe_sizes")
public class Shoes {
    @Column(name = "gender")
    private final Gender gender;
    @Column(name = "foot_length")
    private final double footLength;
    @Embedded
    private Size shoeSize;

    public Shoes(Gender gender, double footLength) {
        this.gender = gender;
        this.footLength = footLength;
    }

    public double getFootLength() {
        return footLength;
    }

    public Size getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize() throws SQLException, ClassNotFoundException {
        DatabaseConfig config = new DatabaseConfig();
        Connection conn = config.getConnection();
        String query = "SELECT * FROM shoe_sizes WHERE gender=? AND foot_length=?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, String.valueOf(gender));
        pstmt.setDouble(2, footLength);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            this.shoeSize = new Size(rs.getDouble("ru_size"),
                    rs.getDouble("eu_size"), rs.getDouble("us_size"));
        }
        rs.close();
        pstmt.close();
        conn.close();

//        double key = footLength;
//        TreeMap<Double, Double> map = new TreeMap<Double, Double>();
//        if (gender == Gender.MAN)
//        {
//            map = sizesMan;
//        }
//        else if (gender == Gender.WOMAN) {
//            map = sizesWoman;
//        }
//        Map.Entry<Double, Double> low = map.floorEntry(key);
//        Map.Entry<Double, Double> high = map.ceilingEntry(key);
//        Double res = 40.0;
//        if (low != null && high != null) {
//            res = Math.abs(key-low.getKey()) < Math.abs(key-high.getKey())
//                    ?   low.getValue()
//                    :   high.getValue();
//        } else if (low != null || high != null) {
//            res = low != null ? low.getValue() : high.getValue();
//        }
    }
}
