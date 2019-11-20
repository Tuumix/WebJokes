package bd.entidades;

import java.awt.Image;


public class Piada 
{
    private int cod, pontucao, usu_cod, cat_cod;
    private String titulo, texto, palchave;
    private byte[] foto;

    public int getCod() {
        return cod;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Piada(){}
    
    public Piada(int cod, int pontucao, int usu_cod, int cat_cod, String titulo, String texto, String palchave, byte[] foto) {
        this.cod = cod;
        this.pontucao = pontucao;
        this.usu_cod = usu_cod;
        this.cat_cod = cat_cod;
        this.titulo = titulo;
        this.texto = texto;
        this.palchave = palchave;
        this.foto = foto;
    }

    public Piada(int pontucao, int usu_cod, int cat_cod, String titulo, String texto, String palchave, byte[] foto) {
        this.pontucao = pontucao;
        this.usu_cod = usu_cod;
        this.cat_cod = cat_cod;
        this.titulo = titulo;
        this.texto = texto;
        this.palchave = palchave;
        this.foto = foto;
    }

    public Piada(int cod, int pontucao, String titulo, String texto, String palchave, byte[] foto) {
        this.cod = cod;
        this.pontucao = pontucao;
        this.titulo = titulo;
        this.texto = texto;
        this.palchave = palchave;
        this.foto = foto;
    }
    

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getPontucao() {
        return pontucao;
    }

    public void setPontucao(int pontucao) {
        this.pontucao = pontucao;
    }

    public int getUsu_cod() {
        return usu_cod;
    }

    public void setUsu_cod(int usu_cod) {
        this.usu_cod = usu_cod;
    }

    public int getCat_cod() {
        return cat_cod;
    }

    public void setCat_cod(int cat_cod) {
        this.cat_cod = cat_cod;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getPalchave() {
        return palchave;
    }

    public void setPalchave(String palchave) {
        this.palchave = palchave;
    }

}
