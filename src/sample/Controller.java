package sample;

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

    @FXML
    public ListView listView;

    @FXML
    public MenuItem menuUpdate, menuDel, menuHelp;

    @FXML
    public void Controlar(){

    }

    @FXML
    public void Save(ActionEvent event) throws SQLException {

        if (txtNome.getText() != "" && txtLogra.getText() != "" && txtNumero.getText() != "") {

            DataBaseClass auxDb = new DataBaseClass();

            Connection connAux = auxDb.Connec();

            List<ClieClass> listaAux = auxDb.Read(connAux);

            for (ClieClass item: listaAux) {
                if (item.Nome != txtNome.getText()){
                    auxDb.Create(connAux, txtNome, txtEmail, txtTele, txtLogra, txtBairro, txtNumero, txtComp, txtCep);
                }
                else{
                    auxDb.Update(connAux, txtNome, txtEmail, txtTele, txtLogra, txtBairro, txtNumero, txtComp, txtCep, listView);
                }

            }
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
    public void Refresh(List<ClieClass> lista) {

        for (ClieClass item: lista) {
            listView.getItems().add(item.Id + "- " + item.Nome);
        }


    }

    @FXML
    public void Editor(ActionEvent event) throws SQLException {
        DataBaseClass edtAux = new DataBaseClass();
        Connection con = edtAux.Connec();

        edtAux.Edit(con, txtNome, txtEmail, txtTele, txtLogra, txtBairro, txtNumero, txtComp, txtCep, listView);
    }


    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DataBaseClass auxDbTwo = new DataBaseClass();
        Connection connAux = auxDbTwo.Connec();

        Refresh(auxDbTwo.Read(connAux));
    }

}
