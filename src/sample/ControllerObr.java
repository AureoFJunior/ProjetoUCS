package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import lombok.SneakyThrows;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

        ObraClass obra = new ObraClass();
        ObraClass obraAux = new ObraClass();


        obra.Isbn = txtIsbn.getText();
        obra.Autores = txtNome.getText();

        String help1 = txtNome.getText();
        String help2 = txtIsbn.getText();
        String auxHelp3 = txtPub.getText();
        Integer help3 = 0;

        if (auxHelp3 != "") {
            help3 = Integer.parseInt(auxHelp3);
            obra.Lanc = Integer.parseInt(txtPub.getText());
        }
        else{
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("Data inválida!");
            alerta.setContentText("Opa, deu erro aqui!");

            alerta.showAndWait();
        }




        if (!obra.equals(obraAux) && help3 != null ) {

            DataBaseClass auxDb = new DataBaseClass();

            Connection connAux = auxDb.connec();

            ListaLivros<ObraClass> listaAux = auxDb.readObras(connAux, 0);
            ListaLivros<ObraClass> verificador = new ListaLivros<>();

            try {
                for (ObraClass item : listaAux) {

                    if (obra.equals(item) && !item.equals(obraAux)) {
                        verificador.IncluirNoFim(item);
                    }
                }

                if (verificador.size() > 0) {
                    throw new ExcecaoDeLivroJaExistente();
                } else {

                    auxDb.createObra(auxDb.connec(), txtNome, txtIsbn, txtAut, txtPub, txtEdt);
                }

                Refresh();
            }catch (ExcecaoDeLivroJaExistente e){

                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error");
                alerta.setHeaderText(e.toString() + "  \n" + "Obra ou Autor já existente no cadastro\nNão foi possível inclui-la\n");
                alerta.setContentText("Opa, deu erro aqui!");

                Optional<ButtonType> result = alerta.showAndWait();
                ButtonType button = result.orElse(ButtonType.CANCEL);
            }

        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Campos obrigatórios\nnão preenchidos");
            alert.setContentText("Opa, deu erro aqui!");

            alert.showAndWait();
        }
        cancelar();
    }

    @FXML
    public void saveAutor(ActionEvent event) throws SQLException, ParseException {

        //Save the cliente, this method call update if already existis or else call create.

        DataBaseClass auxDb = new DataBaseClass();
        Connection connAux = auxDb.connec();
        auxDb.createAutor(auxDb.connec(), txtNomeAut, txtPais);

        //Refresh();
    }

    @FXML
    public void cancelObra(ActionEvent event){

        //This method clear the TextFields to cancel the transaction of creation.
        cancelar();


    }

    @FXML
    public void cancelar(){
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



        ListaLivros<ObraClass> lista = auxDel.readObras(con, 0);
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
