package util;

import java.sql.Connection;
 
import java.sql.DriverManager;
import java.sql.SQLException; 
public abstract class DBConnecter {

    private Connection connect;

    public Connection connect() {

        try {
            if (this.connect == null || this.connect.isClosed()) {
                try {
                    Class.forName("org.postgresql.Driver");
                    this.connect=DriverManager.getConnection("jdbc:postgresql://localhost/pagila","postgres","root");
                } catch (Exception e) {
                    System.out.println("connect()" + e.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println("util.DBConnecter.connect()" + ex.getMessage());
        }
        return connect;
    }
}
