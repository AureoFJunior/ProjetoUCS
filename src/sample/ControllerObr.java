package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import lombok.SneakyThrows;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerObr implements Initializable {

    @FXML
    public TextField txtNome, txtIsbn, txtAut, txtPub, txtEdt, txtNomeAut, txtPais;

    @FXML
    public Button btnCancel, btnCancel2, btnSave, btnSave2;

    @FXML
    public ListView listView;

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DataBaseClass auxDbTwo = new DataBaseClass();
        Connection connAux = auxDbTwo.connec();


        //DataBaseClass auxDel = new DataBaseClass();
        //Connection con = auxDel.connec();

        Refresh();
        if (connAux != null)
            connAux.close();
    }

    @FXML
    public void saveObra(ActionEvent event) throws SQLException, ParseException {

        //Save the cliente, this method call update if already existis or else call create.

        String help1 = txtNome.getText();
        String help2 = txtIsbn.getText();




        if (!help1.equals("") && !help1.equals("")) {

            DataBaseClass auxDb = new DataBaseClass();

            Connection connAux = auxDb.connec();

            List<ObraClass> listaAux = auxDb.readObras(connAux, 0);

            for (ObraClass item: listaAux) {
                if (help1.equals(item.Titulo) && help2.equals(item.Isbn)){
                    auxDb.updateObra(auxDb.connec(), txtNome, txtIsbn, txtAut, txtPub, txtEdt, listView);
                    break;
                }
                else{
                    auxDb.createObra(auxDb.connec(), txtNome, txtIsbn, txtAut, txtPub, txtEdt);
                    break;
                }

            }

            Refresh();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Campos obrigatórios\n não preenchidos");
            alert.setContentText("Ooops, there was an error here!");

            alert.showAndWait();
        }

    }

    @FXML
    public void saveAutor(ActionEvent event) throws SQLException, ParseException {

        //Save the cliente, this method call update if already existis or else call create.

        String help1 = txtNome.getText();
        String help2 = txtIsbn.getText();

        DataBaseClass auxDb = new DataBaseClass();
        Connection connAux = auxDb.connec();
        auxDb.createAutor(auxDb.connec(), txtNomeAut, txtPais);

        //Refresh();
    }

    @FXML
    public void cancelObra(ActionEvent event){

        //This method clear the TextFields to cancel the transaction of creation.

        txtNome.setText("");
        txtIsbn.setText("");
        txtPub.setText("");
        txtAut.setText("");
        txtEdt.setText("");

    }

    @FXML
    public void cancelAut(ActionEvent event){

        //This method clear the TextFields to cancel the transaction of creation.

        txtNomeAut.setText("");
        txtPais.setText("");

    }

    @FXML
    public void deleterObra(ActionEvent event) throws SQLException {

        //This method call the Delete function.

        DataBaseClass auxDel = new DataBaseClass();
        Connection con = auxDel.connec();


        auxDel.delete(con, listView);

        Refresh();

        if (con != null)
            con.close();
    }


    @FXML
    public void Refresh() throws SQLException {

        //This method refresh the list view, reading from the database to add the elements.

        if(listView != null){

            listView.getItems().clear();
        }


        DataBaseClass auxDel = new DataBaseClass();
        Connection con = auxDel.connec();



        List<ObraClass> lista = auxDel.readObras(con, 0);
        for (ObraClass item: lista) {
            if (item != null){
                //listView.getItems().add("Teste");
                listView.getItems().add(item.Id + "-" + item.Titulo);
            }

            else {
                System.out.println("ERRO");
            }
        }

    }

    @FXML
    public void Editor(ActionEvent event) throws SQLException {

        //This method get the infos of an selected item from listview for the TextFields to edit him.

        DataBaseClass edtAux = new DataBaseClass();
        Connection con = edtAux.connec();

        edtAux.edit(con, txtNome, txtIsbn, txtAut, txtPub, txtEdt, listView);

        if (con != null)
            con.close();
    }

}
