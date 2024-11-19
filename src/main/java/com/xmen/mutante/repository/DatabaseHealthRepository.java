package com.xmen.mutante.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DatabaseHealthRepository {
    
    @Autowired
    private DataSource dataSource;

    public boolean checkConnection() {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT 1");
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            return false;
        }
    }

    public String getDatabaseVersion() {
        try (Connection conn = dataSource.getConnection()) {
            return conn.getMetaData().getDatabaseProductVersion();
        } catch (SQLException ex) {
            return "Unknown";
        }
    }
}
