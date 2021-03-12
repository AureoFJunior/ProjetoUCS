package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public ImageView imgAutor, imgObra;

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
    }



    public void abreCad() throws IOException {

        Stage s1 = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root);

        s1.setScene(scene);
        s1.show();


    }

    public void abrePesq() throws  IOException {
        Stage s2 = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Obras.fxml"));
        Scene scene = new Scene(root);

        s2.setScene(scene);
        s2.show();
    }


}
