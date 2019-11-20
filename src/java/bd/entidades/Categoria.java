package bd.entidades;

public class Categoria 
{
    private int cod;
    private String titulo;

    public Categoria(int cod, String titulo) {
        this.cod = cod;
        this.titulo = titulo;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
