package sample;

import lombok.Data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;

@Data
public class ObraClass {

    public int Id;
    public String Titulo;
    public String Isbn;
    public String Autores;
    public String Editora;
    public Integer Lanc;

}
