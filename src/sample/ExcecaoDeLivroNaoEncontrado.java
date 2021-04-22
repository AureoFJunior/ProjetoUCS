package sample;

public class ExcecaoDeLivroNaoEncontrado extends  Exception {

    private ListaLivros lista;

    public ExcecaoDeLivroNaoEncontrado(ListaLivros lista) {
        this.lista = lista;
    }

    public ExcecaoDeLivroNaoEncontrado(String message, ListaLivros lista) {
        super(message);
        this.lista = lista;
    }

    public ExcecaoDeLivroNaoEncontrado(String message, Throwable cause, ListaLivros lista) {
        super(message, cause);
        this.lista = lista;
    }

    public ExcecaoDeLivroNaoEncontrado(Throwable cause, ListaLivros lista) {
        super(cause);
        this.lista = lista;
    }

    public ExcecaoDeLivroNaoEncontrado(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ListaLivros lista) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.lista = lista;
    }


    @Override
    public String toString() {
        return "ExcecaoDeLivroNaoEncontrado";
    }
}
