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
@WebServlet(name = "decrementa_pontos", urlPatterns = {"/decrementa_pontos"})
public class decrementa_pontos extends HttpServlet {

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
            DALPiada dalP = new DALPiada();
            DALCurtida dalC = new DALCurtida();
            Usuario usu = new Usuario();
            HttpSession session = request.getSession(false);
            usu = (Usuario) session.getAttribute("usuario");
            Curtida curte = new Curtida();
            String piada = "";
            ArrayList<Piada> lista = new ArrayList();
            int codigo = Integer.parseInt(request.getParameter("codigo"));

            if (dalP.decrementa(codigo, usu.getCod())) { //incrementando no banco
                dalC.apagar(codigo);
                lista.clear();
                lista = dalP.carregaP();
                for (int i = 0; i < lista.size(); i++) {
                    int cod = lista.get(i).getCod();
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
                out.println(piada);
            }
        }
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
