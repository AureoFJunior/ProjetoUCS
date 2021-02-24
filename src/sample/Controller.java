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

    DataBaseClass auxDb = new DataBaseClass();


    @FXML
    public Button btnA;

    @FXML
    public TextField txtName, txtPass;

    @FXML
    public void save(ActionEvent event) throws SQLException {

        Connection connAux = auxDb.Connec();

        auxDb.Read(connAux);

    }

    @FXML
    public void cancel(ActionEvent event){

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
