package sample;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lombok.Data;
import lombok.SneakyThrows;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {




    @FXML
    public Button btnSave, btnCancel;

    @FXML
    public TextField txtNome, txtEmail, txtTele, txtLogra, txtBairro, txtNumero, txtComp, txtCep;

    private ObservableList<String> listAux = FXCollections.observableArrayList();
    @FXML
    public ListView listView;

    @FXML
    public MenuItem menuUpdate, menuDel, menuHelp;

    @FXML
    public void Save(ActionEvent event) throws SQLException {

        String help1 = txtNome.getText();
        String help2 = txtLogra.getText();
        String help3 = txtNumero.getText();



        if (!help1.equals("") && !help2.equals("") && !help3.equals("")) {

            DataBaseClass auxDb = new DataBaseClass();

            Connection connAux = auxDb.Connec();

            List<ClieClass> listaAux = auxDb.Read(connAux);

            for (ClieClass item: listaAux) {
                if (help1.equals(item.Nome)){
                    auxDb.Update(auxDb.Connec(), txtNome, txtEmail, txtTele, txtLogra, txtBairro, txtNumero, txtComp, txtCep, listView);
                    break;
                }
                else{
                    auxDb.Create(auxDb.Connec(), txtNome, txtEmail, txtTele, txtLogra, txtBairro, txtNumero, txtComp, txtCep);
                    break;
                }

            }

            Refresh();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Campos obrigatórios\n não preenchidos");
            alert.setContentText("Ooops, there was an error!");

            alert.showAndWait();
        }





    }

    @FXML
    public void Cancel(ActionEvent event){
        txtNome.setText("");
        txtEmail.setText("");
        txtTele.setText("");
        txtLogra.setText("");
        txtBairro.setText("");
        txtNumero.setText("");
        txtComp.setText("");
        txtCep.setText("");
    }

    @FXML
    public void Deleter(ActionEvent event) throws SQLException {
        DataBaseClass auxDel = new DataBaseClass();
        Connection con = auxDel.Connec();


        auxDel.Delete(con, listView);

        Refresh();

        if (con != null)
            con.close();
    }


    @FXML
    public void Refresh() throws SQLException {

        listView.getItems().clear();

        DataBaseClass auxDel = new DataBaseClass();
        Connection con = auxDel.Connec();


        List<ClieClass> lista =  auxDel.Read(con);

        for (ClieClass item: lista) {
            listView.getItems().add(item.Id + "- " + item.Nome);
        }

        //listView.setItems(listAux);


    }

    @FXML
    public void Editor(ActionEvent event) throws SQLException {
        DataBaseClass edtAux = new DataBaseClass();
        Connection con = edtAux.Connec();

        edtAux.Edit(con, txtNome, txtEmail, txtTele, txtLogra, txtBairro, txtNumero, txtComp, txtCep, listView);

        if (con != null)
            con.close();
    }


    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DataBaseClass auxDbTwo = new DataBaseClass();
        Connection connAux = auxDbTwo.Connec();

        /*listAux.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {

            }
        });*/

        DataBaseClass auxDel = new DataBaseClass();
        Connection con = auxDel.Connec();




        Refresh();
        if (connAux != null)
            connAux.close();
    }

}
