<%@page import="bd.entidades.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bd.dal.DALCategoria"%>
<%@page import="bd.dal.DALPiada"%>
<%@page import="bd.entidades.Usuario"%>
<%@page import="bd.dal.DALUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="CSS/inicial.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="CSS/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="https://fonts.googleapis.com/css?family=Sunshiney&display=swap" rel="stylesheet"></head>
    <body>
        <header class="cabecario">
            <button><i class="fa fa-home">Home</i></button>
            <div class="header-div">
                <%
                    Usuario usu;
                    usu = (Usuario) session.getAttribute("usuario");
                    if (session.getAttribute("usuario") != null && !usu.isAdm()) {
                        out.println("<button onclick='deslogar()'><i>Log out</i></button>");
                        out.println("<button onclick='red_piada()'><i>Piadas</i></button>");
                    }
                    if (session.getAttribute("usuario") != null && usu.isAdm()) {
                        out.println("<div class=\"dropdown\">\n"
                                + "  <button class=\"btn btn-secondary dropdown-toggle\" type=\"button\" id=\"dropdownMenuButton\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n"
                                + "    Menu\n"
                                + "  </button>\n"
                                + "  <div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuButton\">\n"
                                + "    <button class=\"dropdown-item\" onclick=\"red_categoria()\">Criar Categorias</button>\n"
                                + "    <button class=\"dropdown-item\" href=\"#\">Excluir Usuario</button>\n"
                                + "  </div>\n"
                                + "</div>");
                        out.println("<button onclick='deslogar()'><i>Log out</i></button>");
                        out.println("<button onclick='red_piada()'><i>Piadas</i></button>");
                    } else {
                        out.println("<button onclick='red_form()'><i>Cadastrar</i></button>");
                        out.println("<button onclick='red_login()'><i>Login</i></button>");
                    }
                %>
            </div>
        </header>
        <form name="form-index" class="form-inline" style="display: flex;align-content: center;align-items: center;width: 100%;height: 940px;flex-direction: column;justify-content: center;">
            <div style="display: flex;align-content: center;align-items: center;justify-content: flex-end;background-color: #2f3640;width: 60%; height: 70px;">
                <div id="menu-busca" style="margin-right: 30px;">
                    <input style="margin-top: 10px;"type="text" id="chave">
                    <input class="btn-pesquisar" onclick="buscar_piada()" type="button" Value="Pesquisar">
                </div>
            </div>
            <div class="menu-botoes" style="display: flex;align-content: center;align-content: center;flex-direction: row;width: 60%;height: 550px;">
                <div id="div-btn-categoria" style="background-color: #2f3640;width: 40%;height: 600px; overflow: auto;">
                    <%
                        DALCategoria dalC = new DALCategoria();
                        ArrayList<Categoria> listaC = new ArrayList();
                        listaC = dalC.getCategorias("");

                        for (int i = 0; i < listaC.size(); i++) {
                            out.println("<button type=\"button\" id=\"btn-cat\" style='border: none;width: 100%;height: 70px;font-size: 30px; background-color:#ced6e0;' value='" + listaC.get(i).getCod() + "'>" + listaC.get(i).getTitulo() + "</button>");
                        }
                    %>
                </div>
                <div style="background-color: #dfe6e9;width: 70%;height: 600px;overflow: auto;border: 2px;">
                    <div style="margin-top: 10px;" id="piada">
                    </div>
                </div>
            </div>
        </form
    </body>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
    <script src="JS/popper.min.js" type="text/javascript"></script>
    <script src="JS/bootstrap.js" type="text/javascript"></script>
    <script src="JS/scripts.js" type="text/javascript"></script>
</html>
