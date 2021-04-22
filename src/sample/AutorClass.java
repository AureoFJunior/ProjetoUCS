package sample;

import lombok.Data;

import java.util.Objects;

@Data
public class AutorClass {

    public int Id;
    public String Nome;
    public String Pais;


    @Override
    public String toString() {
        return  "Id=" + Id +
                ", Nome='" + Nome + '\'' +
                ", Pais='" + Pais + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AutorClass)) return false;
        AutorClass that = (AutorClass) o;
        return Objects.equals(getNome(), that.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getPais());
    }
}
