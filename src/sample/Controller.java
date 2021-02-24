package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable {




    @FXML
    public Button btnSave, btnCancel;

    @FXML
    public TextField txtNome, txtEmail, txtTele, txtLogra, txtBairro, txtNumero, txtComp, txtCep;

    @FXML
    public void Save(ActionEvent event) throws SQLException {

        DataBaseClass auxDb = new DataBaseClass();

        Connection connAux = auxDb.Connec();

        auxDb.Create(connAux, txtNome, txtEmail, txtTele, txtLogra, txtBairro, txtNumero, txtComp, txtCep);

    }

    @FXML
    public void Cancel(ActionEvent event2){
        txtNome = null;
        txtEmail = null;
        txtTele  = null;
        txtLogra = null;
        txtBairro = null;
        txtNumero = null;
        txtComp = null;
        txtCep = null;

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
