package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import lombok.SneakyThrows;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerPesq implements Initializable {

    @FXML
    public TextField txtTitle, txtIsbn, txtActor, txtEditora, txtDate, txtDateFinal;

    @FXML
    public Button btnSearch;

    @FXML
    public ListView listViewPesq;


    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PesqClass auxDbTwo = new PesqClass();
        Connection connAux = auxDbTwo.connec();

        //Refresh();
        if (connAux != null)
            connAux.close();
    }

    @FXML
    public void Refresh() throws SQLException {

        //This method refresh the list view, reading from the database to add the elements.

        if(listViewPesq != null){

            listViewPesq.getItems().clear();
        }


        PesqClass auxDel = new PesqClass();
        Connection con = auxDel.connec();
        ObraClass obj = new ObraClass();



        List<ObraClass> lista = auxDel.pesquisa(obj, txtTitle, txtIsbn, txtActor, txtEditora, txtDate, txtDateFinal);
        for (ObraClass item: lista) {
            if (item != null){
                //listView.getItems().add("Teste");
                listViewPesq.getItems().add(item.Id + "-" + item.Titulo);
            }

            else {
                System.out.println("ERRO");
            }
        }

    }



}
