package sample;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseClass {

    static final String driver = "com.mysql.cj.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost/javaregistrador";
    static final String user = "root";
    static final String pass = "root123456";

    public Connection Connec() throws SQLException {

        Connection conn = null;

        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,pass);

        }catch (Exception e){
            e.getMessage();

        }

      return conn;

    }

    public void Create(Connection conn, TextField txtNome,TextField txtEmail,
                              TextField txtTele,TextField txtLogra,TextField txtBairro,
                              TextField txtNumero,TextField txtComp,TextField txtCep)
                            throws SQLException {


        ClieClass item = new ClieClass();

        item.Nome = txtNome.getText();
        item.Email = txtEmail.getText();
        item.Telefone = txtTele.getText();
        item.Logradouro = txtLogra.getText();
        item.Bairro = txtBairro.getText();
        item.Numero = Integer.parseInt(txtNumero.getText());
        item.CEP = txtCep.getText();
        item.Complemento = txtComp.getText();

        String sqlAux = String.format(" \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", %s, \"%s\", \"%s\" ",
                item.Nome, item.Email, item.Telefone, item.Logradouro, item.Bairro, item.Numero, item.CEP, item.Complemento);

        String sql = "INSERT INTO clientes(clie_nome, clie_email, clie_telefone, clie_lograd, clie_bairro, clie_numero, clie_cep, clie_comp) " +
                     "VALUES (" + sqlAux + ")";



        PreparedStatement st = conn.prepareStatement(sql);
        int rows = st.executeUpdate();

        if (st != null){
            st.close();
            conn.close();
        }




    }

    public List<ClieClass> Read(Connection conn) throws SQLException {

        Statement st = null;
        ResultSet rs = null;

        String sql = "select * from clientes";


        List<ClieClass> lista = new ArrayList<ClieClass>();

        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {

                ClieClass item = new ClieClass();

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
            st.close();
            conn.close();
        }


        return lista;

    }

    public void Update(Connection conn, TextField txtNome, TextField txtEmail,
                              TextField txtTele, TextField txtLogra, TextField txtBairro,
                              TextField txtNumero, TextField txtComp, TextField txtCep, ListView listClie) throws  SQLException {

        PreparedStatement st = null;
        ClieClass item = new ClieClass();

        int id = getId(listClie);


        item.Id = id;
        item.Nome = txtNome.getText();
        item.Email = txtEmail.getText();
        item.Telefone = txtTele.getText();
        item.Logradouro = txtLogra.getText();
        item.Bairro = txtBairro.getText();
        item.Numero = Integer.parseInt(txtNumero.getText());
        item.CEP = txtCep.getText();
        item.Complemento = txtComp.getText();

        String sqlAux = String.format(" clie_nome=\"%s\", clie_email=\"%s\",clie_telefone=\"%s\",clie_logra=\"%s\",clie_bairro=\"%s\",clie_numero=%s,clie_cep=\"%s\",clie_comp=\"%s\" ",
                item.Nome, item.Email, item.Telefone, item.Logradouro, item.Bairro, item.Numero, item.CEP, item.Complemento);

        String sql = "UPDATE clientes " +
                "SET " + sqlAux + " WHERE clie_id =" + item.Id + String.format("\"");



        st = conn.prepareStatement(sql);
        int rows = st.executeUpdate();

        if (st != null){
            st.close();
            conn.close();
        }
    }

    public void Delete(Connection conn, ListView listView) throws SQLException {

        int id = getId(listView);
        String sql = "DELETE FROM clientes WHERE clie_id=" + id;

        PreparedStatement st = conn.prepareStatement(sql);
        int rows = st.executeUpdate();

        if (st != null){
            st.close();
            conn.close();
        }



    }

    public void Edit(Connection conn, TextField txtNome, TextField txtEmail,
                            TextField txtTele, TextField txtLogra, TextField txtBairro,
                            TextField txtNumero, TextField txtComp, TextField txtCep,
                            ListView listClie) throws SQLException {

        Statement st = null;
        ResultSet rs = null;

        int id = getId(listClie);


        String sql = "select * from clientes WHERE clie_id =" + id;

        ClieClass itens = new ClieClass();
        List<ClieClass> lista = new ArrayList<ClieClass>();

        try {
            st = conn.createStatement();


            rs = st.executeQuery(sql);

            while (rs.next()) {
               //itens.Id = rs.getInt("clie_id");
               itens.Nome = rs.getString("clie_nome");
               itens.Email = rs.getString("clie_email");
               itens.Telefone = rs.getString("clie_telefone");
               itens.Logradouro = rs.getString("clie_lograd");
               itens.Bairro = rs.getString("clie_bairro");
               itens.Numero = rs.getInt("clie_numero");
               itens.CEP = rs.getString("clie_cep");
               itens.Complemento = rs.getString("clie_comp");



                txtNome.setText(itens.Nome);
                txtEmail.setText(itens.Email);
                txtTele.setText(itens.Telefone);
                txtLogra.setText(itens.Logradouro);
                txtBairro.setText(itens.Bairro);
                txtNumero.setText(String.valueOf(itens.Numero));
                txtCep.setText(itens.CEP);
                txtComp.setText(itens.Complemento);

            }


        }
        catch (Exception e){
            e.getMessage();
        }
        if (rs != null){
            rs.close();
            st.close();
            conn.close();
        }

    }

    public  int getId(ListView listClie){

        Object auxVar = listClie.getSelectionModel().getSelectedItem();
        String converted = auxVar.toString().substring(0, auxVar.toString().indexOf("-"));

        int id = Integer.parseInt(converted);

        return id;
    }

}
