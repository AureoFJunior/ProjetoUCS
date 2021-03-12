package sample;

import lombok.Data;

import java.sql.Date;

@Data
public class ObraClass {

    public int Id;
    public String Titulo;
    public String Isbn;
    public String Autores;
    public String Editora;
    public Date Lanc;
}
