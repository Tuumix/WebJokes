package bd.entidades;

import java.awt.Image;

public class Usuario 
{
    private String nome, email, senha;
    private byte[] foto;
    private boolean adm;
    private int cod;

    public Usuario(){}
    
    public Usuario(String nome, String email, String senha, byte[] foto, boolean adm, int cod) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.foto = foto;
        this.adm = adm;
        this.cod = cod;
    }

    public Usuario(String nome, String email, String senha, byte[] foto, boolean adm) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.foto = foto;
        this.adm = adm;
    }
    
    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public boolean isAdm() {
        return adm;
    }

    public void setAdm(boolean adm) {
        this.adm = adm;
    }
}
