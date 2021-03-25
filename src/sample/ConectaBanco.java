package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaBanco {

    static final String driver = "com.mysql.cj.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost/biblioteca";
    static final String user = "root";
    static final String pass = "rootado";

    public Connection connec() throws SQLException {

        //Create the connection with the Database.

        Connection conn = null;

        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,pass);

        }catch (Exception e){
            e.getMessage();

        }

        return conn;

    }
}
