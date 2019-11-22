/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.dal;

import bd.entidades.Curtida;
import bd.util.Conexao;
import java.sql.ResultSet;

/**
 *
 * @author Aluno
 */
public class DALCurtida {

    public boolean salvar(Curtida c) {
        String sql = "insert into curtidas (codigo_piada,codigo_usu) values ($1,$2)";
        sql = sql.replace("$1", c.getCodigo_piada() + "");
        sql = sql.replace("$2", c.getCodigo_usuario() + "");
        System.out.println("" + sql);
        return new Conexao().manipular(sql);
    }

    public boolean apagar(int id, int codigo_usu) {
        return new Conexao().manipular("delete from curtidas where codigo_piada = " + id + " and codigo_usu = "+codigo_usu);
    }

    public Curtida getCurtida(int codigoP, int codigoU) {
        Curtida curtida = null;
        String sql = "select * from curtidas where codigo_piada = $1 and codigo_usu = $2";
        sql = sql.replace("$1", codigoP + "");
        sql = sql.replace("$2", codigoU + "");
        ResultSet rs = new Conexao().consultar(sql);
        try {
            while (rs.next()) {
                curtida = new Curtida(rs.getInt("codigo_piada"), rs.getInt("codigo_usu"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return curtida;
    }
}
