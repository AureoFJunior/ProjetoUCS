package sample;

import lombok.Data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.util.Objects;

@Data
public class ObraClass {

    public int Id;
    public String Titulo;
    public String Isbn;
    public String Autores;
    public String Editora;
    public Integer Lanc;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ObraClass)) return false;
        ObraClass obraClass = (ObraClass) o;
        return Objects.equals(getIsbn(), obraClass.getIsbn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitulo(), getIsbn(), getAutores(), getEditora(), getLanc());
    }

    @Override
    public String toString() {
        return "ObraClass{" +
                "Id=" + Id +
                ", Titulo='" + Titulo + '\'' +
                ", Isbn='" + Isbn + '\'' +
                '}';
    }
}