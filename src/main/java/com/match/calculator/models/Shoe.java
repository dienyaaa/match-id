package com.match.calculator.models;

import com.match.calculator.DatabaseConfig;
import jakarta.persistence.*;

import java.sql.*;

@Entity
@Table(name = "shoe_sizes")
public class Shoe {
    @Id
    private Long shoeId;
    @Column(name = "gender")
    private final Gender gender;
    @Column(name = "foot_length")
    private final double footLength;
    @Embedded
    private Size shoeSize;

    public Shoe(Gender gender, double footLength) {
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
        String query = "SELECT * FROM shoe_sizes WHERE gender=? AND foot_length<? ORDER BY foot_length DESC LIMIT 1";
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
    }
}
