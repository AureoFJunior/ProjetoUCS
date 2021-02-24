package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseClass {

    static final String driver = ".com.mysql.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost/";
    static final String user = "root";
    static final String pass = "123456";

    public static Connection Connec() throws SQLException {

        Connection conn = null;


        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,pass);


        }catch (Exception e){
            e.getMessage();
            conn.close();
        }


        return conn;
    }

    public static List<ClieClass> Read(Connection conn) throws SQLException {

        Statement st = null;
        ResultSet rs = null;

        String sql = "";
        ClieClass item = new ClieClass();
        List<ClieClass> lista = new ArrayList<ClieClass>();

        try {
            st = conn.createStatement();


            rs = st.executeQuery(sql);

            while (rs.next()) {
                item.Id = rs.getInt("clie_id");
                item.Nome = rs.getString("clie_nome");
                item.Email = rs.getString("clie_email");
                item.Telefone = rs.getString("clie_telefone");
                item.Logradouro = rs.getString("clie_lograd");
                item.Bairro = rs.getString("clie_bairro");
                item.CEP = rs.getString("clie_cep");
                item.Complemento = rs.getString("clie_comp");

                lista.add(item);
                rs.close();
                st.close();
                conn.close();

            }
        }
        catch (Exception e){
            e.getMessage();
        }
            return lista;

    }

}
