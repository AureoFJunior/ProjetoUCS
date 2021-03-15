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
