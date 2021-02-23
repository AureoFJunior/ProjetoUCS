package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public Button btnA;

    @FXML
    public TextField txtName, txtPass;

    @FXML
    public void display(ActionEvent event){

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
