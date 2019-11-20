package bd.dal;

import bd.entidades.Piada;
import bd.util.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DALPiada {

    public boolean salvar(Piada p) {
        String sql = "insert into piada (pia_titulo,pia_texto,pia_pontuacao,pia_palchave,usu_cod,cat_cod) values ('$2','$3','$4','$5','$6','$7')";
        sql = sql.replace("$2", p.getTitulo());
        sql = sql.replace("$3", p.getTexto());
        sql = sql.replace("$4", p.getPontucao() + "");
        sql = sql.replace("$5", p.getPalchave());
        sql = sql.replace("$6", p.getUsu_cod() + "");
        sql = sql.replace("$7", p.getCat_cod() + "");
        Conexao con = new Conexao();
        System.out.println("" + sql);
        if (con.manipular(sql)) {
            return true;
        }
        return false;
    }

    public int pegacodigo() {
        Conexao con = new Conexao();
        return con.getMaxPK("piada", "pia_cod");
    }

    public boolean apagar(int id) {
        return new Conexao().manipular("delete from categoria where id=" + id);
    }

    public ArrayList<Piada> carregaP() {
        ArrayList<Piada> lista = new ArrayList();
        String sql = "select * from piada order by pia_pontuacao desc";
        System.out.println(""+sql);
        ResultSet rs = new Conexao().consultar(sql);
        //int cod, int pontucao, String titulo, String texto, String palchave, byte[] foto
        try {
            while (rs.next()) {
                lista.add(
                        new Piada(rs.getInt("pia_cod"), rs.getInt("pia_pontuacao"), rs.getString("pia_titulo"), rs.getString("pia_texto"), rs.getString("pia_palchave"), rs.getBytes("pia_foto")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }

    public ArrayList<Piada> carrega_piadaUsu(int cod) {
        ArrayList<Piada> lista = new ArrayList();
        String sql = "select * from piada where usu_cod = " + cod + " order by pia_pontuacao desc";
        System.out.println(""+sql);
        ResultSet rs = new Conexao().consultar(sql);
        //int cod, int pontucao, String titulo, String texto, String palchave, byte[] foto
        try {
            while (rs.next()) {
                lista.add(
                        new Piada(rs.getInt("pia_cod"), rs.getInt("pia_pontuacao"), rs.getString("pia_titulo"), rs.getString("pia_texto"), rs.getString("pia_palchave"), rs.getBytes("pia_foto")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }

    public Piada carregaUma(int codigo) {
        String sql = "select * from piada where pia_cod = " + codigo;
        Piada pia = new Piada();
        ResultSet rs = new Conexao().consultar(sql);
        //int cod, int pontucao, String titulo, String texto, String palchave, byte[] foto
        try {
            while (rs.next()) {
                pia = new Piada(rs.getInt("pia_cod"), rs.getInt("pia_pontuacao"), rs.getString("pia_titulo"), rs.getString("pia_texto"), rs.getString("pia_palchave"), rs.getBytes("pia_foto"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return pia;
    }

    public ArrayList<Piada> busca(String chave) {
        ArrayList<Piada> lista = new ArrayList();
        String sql = "select * from piada where pia_palchave like '%" + chave + "%' order by pia_pontuacao desc";
        System.out.println("SQL:" + sql);
        ResultSet rs = new Conexao().consultar(sql);
        //int cod, int pontucao, String titulo, String texto, String palchave, byte[] foto
        try {
            while (rs.next()) {
                lista.add(
                        new Piada(rs.getInt("pia_cod"), rs.getInt("pia_pontuacao"), rs.getString("pia_titulo"), rs.getString("pia_texto"), rs.getString("pia_palchave"), rs.getBytes("pia_foto")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }
    
    public boolean alterar(int codigo) {
     String sql = "update piada set pia_pontuacao = pia_pontuacao + 1 where pia_cod = " + codigo;
     return new Conexao().manipular(sql);
     }

}
