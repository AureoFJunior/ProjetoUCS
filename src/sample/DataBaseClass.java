package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseClass {

    static final String driver = "com.mysql.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost/javaregistrador";
    static final String user = "root";
    static final String pass = "root123456";

    public static Connection Connec() throws SQLException {

        Connection conn = null;


        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,pass);



        }catch (Exception e){
            e.getMessage();

        }



        return conn;

    }

    public static void Create(Connection conn, TextField txtNome,TextField txtEmail,
                              TextField txtTele,TextField txtLogra,TextField txtBairro,
                              TextField txtNumero,TextField txtComp,TextField txtCep)
                            throws SQLException {

        PreparedStatement st = null;
        ClieClass item = new ClieClass();

        item.Nome = txtNome.getText();
        item.Email = txtEmail.getText();
        item.Telefone = txtTele.getText();
        item.Logradouro = txtLogra.getText();
        item.Bairro = txtBairro.getText();
        item.Numero = Integer.parseInt(txtNumero.getText());
        item.CEP = txtCep.getText();
        item.Complemento = txtComp.getText();

        String ajudaPlmds = String.format(" \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", %s, \"%s\", \"%s\" ",
                item.Nome, item.Email, item.Telefone, item.Logradouro, item.Bairro, item.Numero, item.CEP, item.Complemento);

        String sql = "INSERT INTO clientes(clie_nome, clie_email, clie_telefone, clie_lograd, clie_bairro, clie_numero, clie_cep, clie_comp) " +
                     "VALUES (" + ajudaPlmds + ")";



        st = conn.prepareStatement(sql);
        int rows = st.executeUpdate();

        if (st != null){
            st.close();
        }

    }

    public static List<ClieClass> Read(Connection conn) throws SQLException {

        Statement st = null;
        ResultSet rs = null;

        String sql = "select * from clientes";

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
                item.Numero = rs.getInt("clie_numero");
                item.CEP = rs.getString("clie_cep");
                item.Complemento = rs.getString("clie_comp");

                lista.add(item);




            }


        }
        catch (Exception e){
            e.getMessage();
        }
        if (rs != null){
            rs.close();
            if ( st != null){
                st.close();
                if (conn != null){
                    conn.close();
                }
            }




        }

        return lista;

    }



}
