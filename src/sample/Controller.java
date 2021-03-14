package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public ImageView imgAutor, imgLivros;

    @FXML
    public TextField txtNome, txtIsbn, txtAut, txtPub, txtEdt, txtNomeAut, txtPais;

    @FXML
    public Button btnCancel, btnCancel2, btnSave, btnSave2;

    @FXML
    public ListView listView;

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //File file = new File("..\\..\\..\\..\\Downloads\\Nietzsche187a.jpg");

        //imgAutor.setImage(new Image(getClass().getResourceAsStream("..\\..\\..\\..\\Downloads\\Nietzsche187a.jpg")));
        if(imgAutor == null){
            return;
        }

        imgAutor.setOnMouseClicked(event -> {
            try {
                abreCad();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        if(imgLivros == null){
            return;
        }

        imgLivros.setOnMouseClicked(event -> {
            try {
                abrePesq();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        DataBaseClass auxDbTwo = new DataBaseClass();
        Connection connAux = auxDbTwo.connec();


        DataBaseClass auxDel = new DataBaseClass();
        Connection con = auxDel.connec();

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
                    auxDb.updateObra(auxDb.connec(), txtNome, txtIsbn, txtAut, txtEdt, txtPub, listView);
                    break;
                }
                else{
                    auxDb.createObra(auxDb.connec(), txtNome, txtIsbn, txtAut, txtEdt, txtPub);
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
    public void cancelObra(ActionEvent event){

        //This method clear the TextFields to cancel the transaction of creation.

        txtNome.setText("");
        txtIsbn.setText("");
        txtPub.setText("");
        txtAut.setText("");
        txtEdt.setText("");

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



        List<ObraClass> lista =  auxDel.readObras(con, 0);
        for (ObraClass item: lista) {
            listView.getItems().add(item.Id + "- " + item.Titulo);
        }

        //listView.setItems(listAux);


    }

    @FXML
    public void Editor(ActionEvent event) throws SQLException {

        //This method get the infos of an selected item from listview for the TextFields to edit him.

        DataBaseClass edtAux = new DataBaseClass();
        Connection con = edtAux.connec();

        edtAux.edit(con, txtNome, txtIsbn, txtAut, txtEdt, txtPub, listView);

        if (con != null)
            con.close();
    }






    @FXML
    public void abreCad() throws IOException {

        Stage s1 = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root);

        s1.setScene(scene);
        s1.show();


    }

    @FXML
    public void abrePesq() throws  IOException {
        Stage s2 = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("obras.fxml"));
        Scene scene = new Scene(root);

        s2.setScene(scene);
        s2.show();
    }


}
