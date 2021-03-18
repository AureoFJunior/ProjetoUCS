package sample;

import javafx.scene.control.TextField;
import lombok.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

@Data
 public class PesqClass {

    public String Title;
    public String Isbn;
    public String Autor;
    public String Editora;
    public Integer DataInicial;
    public Integer DataFinal;

    static final String driver = "com.mysql.cj.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost/biblioteca";
    static final String user = "root";
    static final String pass = "rootado";

    public Connection conex() {

        Connection conecta = null;

        try{
            Class.forName(driver);
            conecta = DriverManager.getConnection(url,user,pass);

        }catch (Exception e){
            e.getMessage();

        }

        return conecta;
    }

    public List<PesqClass> pesquisa(ObraClass obra, TextField txtTitle,
                         TextField txtIsbn, TextField txtActor,
                         TextField txtEditora, TextField txtDate) {

        PesqClass pesq = new PesqClass();

        Connection conAux = pesq.conex();

        pesq.Title = txtTitle.getText();
        pesq.Isbn = txtIsbn.getText();
        pesq.Autor = txtActor.getText();
        pesq.Editora = txtEditora.getText();
        pesq.DataInicial = Integer.parseInt(txtDate.getText());
        //pesq.DataFinal = Integer.parseInt(txtDateFinal.getText());

        /*String sqlAux = String.format(" \"%s\", \"%s\", \"%s\", \"%s\", \"%s\"  ",
                pesq.Title, pesq.Isbn, pesq.Autor, pesq.Editora, pesq.Data);*/

        String sql = "SELECT * FROM obras";

        List<String> where = null;

        if(pesq.Title != null){
            where.add(" obr_nome= '" + pesq.Title + "'");
        }
        if(pesq.Isbn != null){
            where.add(" obr_isbn= '" + pesq.Isbn + "'");
        }
        if(pesq.Editora != null){
            where.add(" obr_editora= '" + pesq.Editora + "'");
        }
        if(pesq.Autor != null){
            where.add(" obr_autores= '" + pesq.Autor + "'");
        }
        if(!pesq.DataInicial.equals(null)){
            where.add(" obr_anopub= " + pesq.DataInicial + "");
        }
        if(!pesq.DataFinal.equals(null)){
            where.add(" obr_anopub= " + pesq.Data + "");
        }



        PreparedStatement st = conn.prepareStatement(sql);
        int rows = st.executeUpdate();

        if (st != null){
            st.close();
            conn.close();
        }






    }

}
