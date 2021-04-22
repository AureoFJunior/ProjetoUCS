package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DataBaseClass extends ConectaBanco {



    public void createAut(Connection conn, TextField txtNomeAut, TextField txtPais) throws SQLException {

        //Instances a new model with her properties and add this to the database in MySql.


        AutorClass item = new AutorClass();

        item.Nome = txtNomeAut.getText();
        item.Pais = txtPais.getText();


        String sqlAux = String.format(" \"%s\", \"%s\"",
                item.Nome, item.Pais);

        String sql = "INSERT INTO autores(aut_nome, aut_pais) " +
                "VALUES (" + sqlAux + ")";



        PreparedStatement st = conn.prepareStatement(sql);
        int rows = st.executeUpdate();

        if (st != null){
            st.close();
            conn.close();
        }




    }

    public void createObra(Connection conn, TextField txtNome, TextField txtIsbn,
                          TextField txtAut, TextField txtPub, TextField txtEdt
                          ) throws SQLException, ParseException {

        //Instances a new model with her properties and add this to the database in MySql.


        ObraClass item = new ObraClass();


        item.Titulo = txtNome.getText();
        item.Isbn = txtIsbn.getText();
        item.Autores = txtAut.getText();
        String aux = txtPub.getText();
        item.Lanc = Integer.parseInt(aux);
        item.Editora = txtEdt.getText();



        String sqlAux = String.format(" \"%s\", \"%s\", \"%s\", \"%s\" ",
                item.Titulo, item.Isbn, item.Autores, item.Editora);

        String sql = "INSERT INTO obras(obr_nome, obr_isbn, obr_autores, obr_editora, obr_anopub) " +
                "VALUES (" + sqlAux +"," + item.Lanc + ")";



        PreparedStatement st = conn.prepareStatement(sql);
        int rows = st.executeUpdate();

        if (st != null){
            st.close();
            conn.close();
        }




    }

    public void createAutor(Connection conn, TextField txtNome, TextField txtPais
    ) throws SQLException, ParseException {

        //Instances a new model with her properties and add this to the database in MySql.


        AutorClass cara = new AutorClass();
        AutorClass caraAux = new AutorClass();

        cara.Nome = txtNome.getText();
        cara.Pais = txtPais.getText();

        if(!cara.equals(caraAux)) {


            String sqlAux = String.format(" \"%s\", \"%s\" ",
                    cara.Nome, cara.Pais);

            String sql = "INSERT INTO autores(aut_nome, aut_pais) " +
                    "VALUES (" + sqlAux + ")";


            PreparedStatement st = conn.prepareStatement(sql);
            int rows = st.executeUpdate();

            if (st != null) {
                st.close();
                conn.close();
            }

        }
        else{

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Impossível adicionar Autor");
            alert.setContentText("Deu ruim");

            alert.showAndWait();
        }
    }

    public ListaLivros<ObraClass> readObras(Connection conn, int p) throws SQLException {

        //Read and retrieve a list of itens in database.

        Statement st = null;
        ResultSet rs = null;
        ListaLivros<ObraClass> lista = new ListaLivros<ObraClass>();

        String sql = "select * from obras";

        if (p == 1){

            return lista;
        }
        else{

            try {
                st = conn.createStatement();
                rs = st.executeQuery(sql);

                while (rs.next()) {

                    ObraClass item = new ObraClass();

                    item.Id = rs.getInt("obr_id");
                    item.Titulo = rs.getString("obr_nome");
                    item.Isbn = rs.getString("obr_isbn");
                    item.Autores = rs.getString("obr_autores");
                    item.Editora = rs.getString("obr_editora");
                    item.Lanc = rs.getInt("obr_anopub");


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

    }

    public void updateObra(Connection conn, TextField txtNome, TextField txtIsbn,
                          TextField txtAut, TextField txtPub, TextField txtEdt,
                           ListView listObra) throws SQLException, ParseException {

        //Update itens if already existis in database.

        PreparedStatement st = null;
        ObraClass item = new ObraClass();

        int id = getId(listObra);


        item.Id = id;
        item.Titulo = txtNome.getText();
        item.Isbn = txtIsbn.getText();
        item.Autores = txtAut.getText();
        item.Editora = txtEdt.getText();
        Integer aux = Integer.parseInt(txtPub.getText());
        item.Lanc = aux;



        String sqlAux = String.format(" obr_nome=\"%s\", obr_isbn=\"%s\",obr_autores=\"%s\" ",
                item.Titulo, item.Isbn, item.Autores);

        String sql = "UPDATE clientes " +
                "SET " + sqlAux + "obr_anopub=" + "obr_editora=" + item.Lanc + " WHERE obr_id =" + item.Id + String.format("\"");



        st = conn.prepareStatement(sql);
        int rows = st.executeUpdate();

        if (st != null){
            st.close();
            conn.close();
        }
    }

    public void delete(Connection conn, ListView listView) throws SQLException {

        //Delete a especified item in database.

        int id = getId(listView);
        String sql = "DELETE FROM obras WHERE obr_id=" + id;

        PreparedStatement st = conn.prepareStatement(sql);
        int rows = st.executeUpdate();

        if (st != null){
            st.close();
            conn.close();
        }



    }

    public void edit(Connection conn, TextField txtNome, TextField txtIsbn,
                     TextField txtAut, TextField txtPub, TextField txtEdt,
                     ListView listObra) throws SQLException {

        Statement st = null;
        ResultSet rs = null;

        int id = getId(listObra);


        String sql = "select * from obras WHERE obr_id =" + id;

        ObraClass itens = new ObraClass();
        ListaLivros<ObraClass> lista = new ListaLivros<ObraClass>();

        try {
            st = conn.createStatement();


            rs = st.executeQuery(sql);

            while (rs.next()) {
                //itens.Id = rs.getInt("clie_id");
                itens.Id = rs.getInt("obr_id");
                itens.Titulo = rs.getString("obr_nome");
                itens.Isbn = rs.getString("obr_isbn");
                itens.Autores = rs.getString("obr_autores");
                itens.Editora = rs.getString("obr_editora");
                itens.Lanc = rs.getInt("obr_anopub");



                txtNome.setText(itens.Titulo);
                txtIsbn.setText(itens.Isbn);
                txtAut.setText(itens.Autores);
                txtEdt.setText(itens.Editora);
                txtPub.setText(itens.Lanc.toString());


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

    public  int getId(ListView listObra){

        //Get the Id from database who increment yourself.

        Object auxVar = listObra.getSelectionModel().getSelectedItem();
        String converted = auxVar.toString().substring(0, auxVar.toString().indexOf("-"));

        int id = Integer.parseInt(converted);

        return id;
    }

}





