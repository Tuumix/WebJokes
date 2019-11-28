/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.dal;

import bd.entidades.Usuario;
import bd.util.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DALUsuario
{

    public boolean salvar(Usuario u)
    {
        String sql = "insert into usuario (usu_nome,usu_email,usu_senha,usu_adm) values ('$1','$2','$3',$4)";
        sql = sql.replace("$1", u.getNome());
        sql = sql.replace("$2", u.getEmail());
        sql = sql.replace("$3", u.getSenha());
        sql = sql.replace("$4", u.isAdm() + "");
        System.out.println(""+sql);
        Conexao con = new Conexao();
        if (con.getEstadoConexao())
            if (con.manipular(sql))
                return true;
        return false;
    }

    /*public boolean alterar(Pais p) {
     String sql = "update pais set sigla='$1', idioma='$2', nome='$3' where id=" + p.getId();
     sql = sql.replace("$1", p.getSigla());
     sql = sql.replace("$2", p.getIdioma());
     sql = sql.replace("$3", p.getNome());
     return new Conexao().manipular(sql);
     }

     public boolean apagar(int id) {
     return new Conexao().manipular("delete from pais where id=" + id);
     }*/
    public Boolean delete_usr(int cod)
    {
        String sql = "delete from usuario where usu_cod = " + cod;
        try
        {
            Conexao con = new Conexao();
            return con.manipular(sql);
        } catch (Exception e)
        {
            System.out.println(e);
        }
        return false;
    }
    
    public int getCod(String email)
    {
        int cod=-1;
        String sql = "select usu_cod from usuario where usu_email = '" + email + "'";
        ResultSet rs = new Conexao().consultar(sql);
        try
        {
            if (rs.next())
                cod = rs.getInt("usu_cod");
        } catch (Exception e)
        {
            System.out.println(e);
        }
        return cod;
    }

    public Usuario getUsuario(int cod)
    {
        Usuario u = null;
        String sql = "select * from usuario where id=" + cod;
        ResultSet rs = new Conexao().consultar(sql);
        try
        {
            if (rs.next())
                u = new Usuario(rs.getString("usu_nome"), rs.getString("usu_email"), rs.getString("usu_senha"), rs.getBytes("usu_foto"), rs.getBoolean("usu_adm"), rs.getInt("usu_cod"));
        } catch (Exception e)
        {
            System.out.println(e);
        }
        return u;
    }
    
    public Usuario busca_usr(String nome)
    {
        Usuario u = null;
        String sql = "select * from usuario where usu_nome = " + "'"+ nome+"'";
        ResultSet rs = new Conexao().consultar(sql);
        try
        {
            if (rs.next())
                u = new Usuario(rs.getString("usu_nome"), rs.getString("usu_email"), rs.getString("usu_senha"), rs.getBytes("usu_foto"), rs.getBoolean("usu_adm"), rs.getInt("usu_cod"));
        } catch (Exception e)
        {
            System.out.println(e);
        }
        return u;
    }

    public Usuario valida_usu(String email, String senha)
    {
        Usuario u = null;
        String sql = "select * from usuario where usu_email = '$1' and usu_senha = '$2'";
        sql = sql.replace("$1", email);
        sql = sql.replace("$2", senha);
        ResultSet rs = new Conexao().consultar(sql);
        try
        {
            if (rs.next())
                u = new Usuario(rs.getString("usu_nome"), rs.getString("usu_email"), rs.getString("usu_senha"), rs.getBytes("usu_foto"), rs.getBoolean("usu_adm"), rs.getInt("usu_cod"));
        } catch (Exception e)
        {
            System.out.println(e);
        }
        return u;
    }

    /*public ArrayList<Pais> getPais(String filtro) {
     ArrayList<Pais> lista = new ArrayList();
     String sql = "select * from pais";
     if (!filtro.isEmpty()) {
     sql += " where " + filtro;
     }
     sql += " order by sigla";
     ResultSet rs = new Conexao().consultar(sql);
     try {
     while (rs.next()) {
     lista.add(
     new Pais(rs.getInt("id"), rs.getString("sigla"), rs.getString("idioma"), rs.getString("nome")));
     }
     } catch (Exception e) {
     System.out.println(e);
     }
     return lista;
     }*/
}
