package bd.dal;

import bd.entidades.Categoria;
import bd.util.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DALCategoria 
{
    public boolean salvar(String descricao) {
        String sql = "insert into categoria (cat_titulo) values('$2')";
        sql = sql.replace("$2", descricao);
        System.out.println(""+sql);
        return new Conexao().manipular(sql);
    }
    
     public boolean apagar(int id) {
        return new Conexao().manipular("delete from categoria where id=" + id);
    }

    public Categoria getCategoria(int cod) {
        Categoria c = null;
        String sql = "select * from categoria where id=" + cod;
        ResultSet rs = new Conexao().consultar(sql);
        try {
            if (rs.next()) {
                c = new Categoria(rs.getInt("cat_cod"), rs.getString("cat_titulo"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return c;
    }
    
    public ArrayList<Categoria> getCategorias(String filtro) {
        ArrayList<Categoria> lista = new ArrayList();
        Conexao conect = new Conexao();
        String sql = "select * from categoria";
        if (!filtro.isEmpty()) {
            sql += " where " + filtro;
        }
        ResultSet rs = new Conexao().consultar(sql);
        try {
            while (rs.next()) {
                lista.add(
                        new Categoria(rs.getInt("cat_cod"), rs.getString("cat_titulo")));
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }
}
