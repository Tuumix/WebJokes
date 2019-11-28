package Servlet;

import bd.dal.DALPiada;
import bd.dal.DALUsuario;
import bd.entidades.Piada;
import bd.entidades.Usuario;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig(
        location = "/",
        fileSizeThreshold = 1024 * 1024, // 1MB *      
        maxFileSize = 1024 * 1024 * 100, // 100MB **
        maxRequestSize = 1024 * 1024 * 10 * 10 // 100MB ***
)
@WebServlet(name = "cadastra_pia", urlPatterns = {"/cadastra_pia"})
public class cadastra_pia extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(true);
            Piada pia; int codigoC;
            String titulo, texto, chave, email, senha;
            DALPiada dalpia = new DALPiada();
            Usuario usuario = new Usuario();
            usuario = (Usuario) session.getAttribute("usuario");

            titulo = request.getParameter("titulo");
            texto = request.getParameter("texto");
            chave = request.getParameter("chave");
            codigoC = Integer.parseInt(request.getParameter("cbbox"));
            DALUsuario usu = new DALUsuario();

            int cod = usu.getCod(usuario.getEmail());
            pia = new Piada(0, cod, codigoC, titulo, texto, chave, null);

            if (dalpia.salvar(pia)) {
                try {
                    // recebe a foto
                    Part foto = request.getPart("foto");
                    // cria um vetor para armazenar a foto
                    byte[] imagem = new byte[(int) foto.getSize()];
                    // carrega o vetor com os bytes da foto
                    foto.getInputStream().read(imagem);
                    // cria um arquivo com o mesmo nome da foto e grava o vetor como seu conteúdo
                    FileOutputStream arquivo = new FileOutputStream(new File(getServletContext().getRealPath("/") + "/" + "fotos_piadas/" + dalpia.pegacodigo() + ".jpg"));
                    arquivo.write(imagem);
                    arquivo.close();
                    response.sendRedirect("cadastro_piadas.jsp");
                } catch (Exception e) {
                    out.print(e.getMessage());
                }
            } else {
                response.sendRedirect("./");
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
