/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import bd.dal.DALCurtida;
import bd.dal.DALPiada;
import bd.entidades.Curtida;
import bd.entidades.Piada;
import bd.entidades.Usuario;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hiroshi
 */
@WebServlet(name = "servlet_piada", urlPatterns = {"/servlet_piada"})
public class servlet_piada extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String tipo = request.getParameter("tipo");
            String chave = request.getParameter("chave");
            HttpSession session = request.getSession(false);
            Usuario usu = new Usuario();
            usu = (Usuario) session.getAttribute("usuario");

            if (tipo.equals("onload")) {
                out.println(onload(usu));
            }
            if (tipo.equals("pesq_piada")) {
                out.println(pesquisa_pia(usu, chave));
            }
            if (tipo.equals("busc_categoria")) {
                String aux = request.getParameter("codigo_cat");

                int codigo_cat = Integer.parseInt(aux);
                out.println(busca_categoria(usu, codigo_cat));
            }
            if (tipo.equals("carrega_piaUsu")) {
                out.println(carrega_piadaUser(usu));
            }

            if (tipo.equals("deleta_pia")) {
                int codigoP = Integer.parseInt(request.getParameter("codigo"));
                out.println(deleta_piada(usu, codigoP));
            }

            if (tipo.equals("curtir")) {
                int codigo = Integer.parseInt(request.getParameter("codigo"));
                out.println(curtir_piada(usu, codigo));
            }

            if (tipo.equals("descurtir")) {
                int codigo = Integer.parseInt(request.getParameter("codigo"));
                out.println(descurtir_piada(usu, codigo));
            }
        }
    }

    public String onload(Usuario usu) {
        int cod;
        Curtida curte = new Curtida();
        DALPiada dalP = new DALPiada();
        DALCurtida dalC = new DALCurtida();
        String piada = "";
        ArrayList<Piada> lista = new ArrayList();

        if (usu != null) {
            lista = dalP.carregaP();
            for (int i = 0; i < lista.size(); i++) {
                cod = lista.get(i).getCod();
                piada += "<div style=\"width: 40%; border-bottom:2px solid;border-bottom-color: #2c3e50;border-bottom-width: 3px;margin-left: 30px;\">";
                piada += "<b>" + lista.get(i).getTitulo() + "</b><br><br>";
                piada += "<p>" + lista.get(i).getTexto() + "</p><br>";
                piada += "<p>Pontuação:" + lista.get(i).getPontucao() + "</p><br>";
                curte = dalC.getCurtida(lista.get(i).getCod(), usu.getCod());

                if (curte != null) {
                    piada += "<button class=\"descurtir\" id=\"descurtir\" type=\"button\" value=\"" + cod + "\">Dislike</button>";
                } else {
                    piada += "<button class=\"curtir\" id=\"curtir\" type=\"button\" value=\"" + cod + "\">Curtir</button>";
                }
                piada += "</div>";
            }
        } else {
            lista = dalP.carregaP();
            for (int i = 0; i < lista.size(); i++) {
                cod = lista.get(i).getCod();
                piada += "<div style=\"width: 40%; border-bottom:2px solid;border-bottom-color: #2c3e50;border-bottom-width: 3px;margin-left: 30px;\">";
                piada += "<b>" + lista.get(i).getTitulo() + "</b><br><br>";
                piada += "<p>" + lista.get(i).getTexto() + "</p><br>";
                piada += "<p>Pontuação:" + lista.get(i).getPontucao() + "</p><br>";

                piada += "</div>";
            }
        }
        return piada;
    }

    public String pesquisa_pia(Usuario usu, String chave) {
        DALPiada dalP = new DALPiada();
        DALCurtida dalC = new DALCurtida();
        Curtida curte = new Curtida();

        String piada = "";
        int cod;
        ArrayList<Piada> lista = new ArrayList();

        lista = dalP.busca(chave);
        if (usu != null) {
            for (int i = 0; i < lista.size(); i++) {
                cod = lista.get(i).getCod();
                piada += "<div style=\"width: 40%; border-bottom:2px solid;border-bottom-color: #2c3e50;border-bottom-width: 3px;margin-left: 30px;\">";
                piada += "<b>" + lista.get(i).getTitulo() + "</b><br><br>";
                piada += "<p>" + lista.get(i).getTexto() + "</p><br>";
                piada += "<p>Pontuação:" + lista.get(i).getPontucao() + "</p><br>";
                curte = dalC.getCurtida(cod, usu.getCod());

                if (curte != null) {
                    piada += "<button class=\"descurtir\" id=\"descurtir\" type=\"button\" value=\"" + cod + "\">Dislike</button>";
                } else {
                    piada += "<button class=\"curtir\" id=\"curtir\" type=\"button\" value=\"" + cod + "\">Curtir</button>";
                }
                piada += "</div>";
            }
        } else {
            for (int i = 0; i < lista.size(); i++) {
                cod = lista.get(i).getCod();
                piada += "<div style=\"width: 40%; border-bottom:2px solid;border-bottom-color: #2c3e50;border-bottom-width: 3px;margin-left: 30px;\">";
                piada += "<b>" + lista.get(i).getTitulo() + "</b><br><br>";
                piada += "<p>" + lista.get(i).getTexto() + "</p><br>";
                piada += "<p>Pontuação:" + lista.get(i).getPontucao() + "</p><br>";
                piada += "</div>";
            }
        }
        return piada;
    }

    public String busca_categoria(Usuario usu, int codigoC) {
        int cod;
        Curtida curte = new Curtida();
        DALPiada dalP = new DALPiada();
        DALCurtida dalC = new DALCurtida();
        String piada = "";
        ArrayList<Piada> lista = new ArrayList();
        if (usu != null) {
            lista = dalP.carregaP_Categoria(codigoC);
            for (int i = 0; i < lista.size(); i++) {
                cod = lista.get(i).getCod();
                piada += "<div style=\"width: 40%; border-bottom:2px solid;border-bottom-color: #2c3e50;border-bottom-width: 3px;margin-left: 30px;\">";
                piada += "<b>" + lista.get(i).getTitulo() + "</b><br>";
                piada += "<p>" + lista.get(i).getTexto() + "</p><br>";
                piada += "<p>Pontuação:" + lista.get(i).getPontucao() + "</p><br>";
                curte = dalC.getCurtida(lista.get(i).getCod(), usu.getCod());

                if (curte != null) {
                    piada += "<button class=\"descurtir\" id=\"descurtir\" type=\"button\" value=\"" + cod + "\">Dislike</button>";
                } else {
                    piada += "<button class=\"curtir\" id=\"curtir\" type=\"button\" value=\"" + cod + "\">Curtir</button>";
                }
                piada += "</div>";
            }
        } else {
            lista = dalP.carregaP_Categoria(codigoC);
            for (int i = 0; i < lista.size(); i++) {
                piada += "<div style=\"width: 40%; border-bottom:2px solid;border-bottom-color: #2c3e50;border-bottom-width: 3px;margin-left: 30px;\">";
                piada += "<b>" + lista.get(i).getTitulo() + "</b><br>";
                piada += "<p>" + lista.get(i).getTexto() + "</p><br>";
                piada += "<p>Pontuação:" + lista.get(i).getPontucao() + "</p><br>";
                piada += "</div>";
            }
        }
        return piada;
    }

    public String carrega_piadaUser(Usuario usu) {
        DALPiada dalP = new DALPiada();
        int cod;
        String piada = "";
        ArrayList<Piada> lista = new ArrayList();

        lista = dalP.carrega_piadaUsu(usu.getCod());

        for (int i = 0; i < lista.size(); i++) {
            cod = lista.get(i).getCod();
            piada += "<div style=\"width: 40%; border-bottom:2px solid;border-bottom-color: #2c3e50;border-bottom-width: 3px;margin-left: 30px;\">";
            piada += "<p>Titulo</p>";
            piada += "<input class='input_data' value='" + lista.get(i).getTitulo() + "'>";
            piada += "<p>Texto</p>";
            piada += "<input class='input_data' value='" + lista.get(i).getTexto() + "'>";
            piada += "<button class=\"alterar\" id=\"alterar\" type=\"button\" value=\"" + cod + "\">Alterar</button>";
            piada += "<button class=\"deletar\" id=\"deletar\" type=\"button\" value=\"" + cod + "\">Excluir</button>";
            piada += "</div>";
        }
        return piada;
    }

    public String deleta_piada(Usuario usu, int codigoP) {
        DALPiada dalP = new DALPiada();
        String piada = "";
        int cod;
        ArrayList<Piada> lista = new ArrayList();
        dalP.apagar(codigoP);
        lista = dalP.carrega_piadaUsu(usu.getCod());
        for (int i = 0; i < lista.size(); i++) {
            cod = lista.get(i).getCod();
            piada += "<div style=\"width: 40%; border-bottom:2px solid;border-bottom-color: #2c3e50;border-bottom-width: 3px;margin-left: 30px;\">";
            piada += "<p>Titulo</p>";
            piada += "<input class='input_data' value='" + lista.get(i).getTitulo() + "'>";
            piada += "<p>Texto</p>";
            piada += "<input class='input_data' value='" + lista.get(i).getTexto() + "'>";
            piada += "<button class=\"alterar\" id=\"alterar\" type=\"button\" value=\"" + cod + "\">Alterar</button>";
            piada += "</div>";
        }
        return piada;
    }

    public String curtir_piada(Usuario usu, int codigo) {
        DALPiada dalP = new DALPiada();
        DALCurtida dalC = new DALCurtida();
        Curtida curte;
        String piada = "";
        ArrayList<Piada> lista = new ArrayList();
        curte = new Curtida(codigo, usu.getCod());

        if (dalP.alterar(codigo)) { //incrementando no banco
            dalC.salvar(curte); //grava na tabela curtida
            lista.clear();
            lista = dalP.carregaP();
            for (int i = 0; i < lista.size(); i++) {
                int cod = lista.get(i).getCod();
                piada += "<div style=\"width: 40%; border-bottom:2px solid;border-bottom-color: #2c3e50;border-bottom-width: 3px;margin-left: 30px;\">";
                piada += "<b>" + lista.get(i).getTitulo() + "</b><br><br>";
                piada += "<p>" + lista.get(i).getTexto() + "</p><br>";
                piada += "<p>Pontuação:" + lista.get(i).getPontucao() + "</p><br>";
                curte = dalC.getCurtida(lista.get(i).getCod(), usu.getCod());

                if (curte != null) {
                    piada += "<button class=\"descurtir\" id=\"descurtir\" type=\"button\" value=\"" + cod + "\">Dislike</button>";
                } else {
                    piada += "<button class=\"curtir\" id=\"curtir\" type=\"button\" value=\"" + cod + "\">Curtir</button>";
                }
                piada += "</div>";
            }
        }
        return piada;
    }

    public String descurtir_piada(Usuario usu, int codigo) {
        DALPiada dalP = new DALPiada();
        DALCurtida dalC = new DALCurtida();
        Curtida curte = new Curtida();
        String piada = "";
        ArrayList<Piada> lista = new ArrayList();

        if (dalP.decrementa(codigo, usu.getCod())) { //incrementando no banco
            dalC.apagar(codigo, usu.getCod());
            lista.clear();
            lista = dalP.carregaP();
            for (int i = 0; i < lista.size(); i++) {
                int cod = lista.get(i).getCod();
                piada += "<div style=\"width: 40%; border-bottom:2px solid;border-bottom-color: #2c3e50;border-bottom-width: 3px;margin-left: 30px;\">";
                piada += "<b>" + lista.get(i).getTitulo() + "</b><br><br>";
                piada += "<p>" + lista.get(i).getTexto() + "</p><br>";
                piada += "<p>Pontuação:" + lista.get(i).getPontucao() + "</p><br>";
                curte = dalC.getCurtida(lista.get(i).getCod(), usu.getCod());

                if (curte != null) {
                    piada += "<button class=\"descurtir\" id=\"descurtir\" type=\"button\" value=\"" + cod + "\">Dislike</button>";
                } else {
                    piada += "<button class=\"curtir\" id=\"curtir\" type=\"button\" value=\"" + cod + "\">Curtir</button>";
                }
                piada += "</div>";
            }
        }
        return piada;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
