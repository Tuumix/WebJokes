/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.entidades;

/**
 *
 * @author Aluno
 */
public class Curtida {
    private int codigo_piada;
    private int codigo_usuario;

    public Curtida(){} 
   
    public Curtida(int codigo_piada, int codigo_usuario) {
        this.codigo_piada = codigo_piada;
        this.codigo_usuario = codigo_usuario;
    }

    public int getCodigo_piada() {
        return codigo_piada;
    }

    public void setCodigo_piada(int codigo_piada) {
        this.codigo_piada = codigo_piada;
    }

    public int getCodigo_usuario() {
        return codigo_usuario;
    }

    public void setCodigo_usuario(int codigo_usuario) {
        this.codigo_usuario = codigo_usuario;
    }
  
    
}
