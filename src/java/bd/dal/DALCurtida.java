/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.dal;

import bd.entidades.Categoria;
import bd.entidades.Curtida;
import bd.util.Conexao;

/**
 *
 * @author Aluno
 */
public class DALCurtida {

    public boolean salvar(Curtida c) {
        String sql = "insert into curtidas (codigo_piada,codigo_usu) values ($1,$2)";
        sql = sql.replace("$1", c.getCodigo_piada() + "");
        sql = sql.replace("$2", c.getCodigo_usuario() + "");
        System.out.println(""+sql);
        return new Conexao().manipular(sql);
    }

    public boolean apagar(int id) {
        return new Conexao().manipular("delete from curtidas where codigo_piada=" + id);
    }
}
