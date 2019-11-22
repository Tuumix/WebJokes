<%@page import="bd.entidades.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bd.dal.DALCategoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="CSS/formcss.css">
        <link href="https://fonts.googleapis.com/css?family=Sunshiney&display=swap" rel="stylesheet">
    </head>
    <title>Document</title>
</head>
<body>
    <header>
        <button onclick="red_index();"><i class="fa fa-home">Home</i></button>
    </header>
    <main class="main-cad-piadas" >
        <form action="cadastra_pia" method="POST" enctype="multipart/form-data">
            <div id="piada-container" style="display: flex;align-items: center;align-content: center;justify-content: center; margin-top: 15px;">
                <image id="piadaimg" src="https://thewondrous.com/wp-content/uploads/2015/09/funny-clean-short-jokes.jpg" />
            </div>
            <input type="file" name="foto" id="fotoP">

            <select name="cbbox" style="width: 30%;height: 30px;margin-top: 20px;">
                <%
                    DALCategoria dalC = new DALCategoria();
                    ArrayList<Categoria> listC = new ArrayList<>();
                    listC = dalC.getCategorias("");

                    for (int i = 0; i < listC.size(); i++) {
                        out.println("<option value=\"" + listC.get(i).getCod() + "\">" + listC.get(i).getTitulo() + "</option>");
                    }
                %>
            </select>
            <input id="titulo" name="titulo" class="input_data" type="text" placeholder="Título">
            <textarea id="texto" name="texto" class="input_data" placeholder="Texto"></textarea>
            <input name="chave" class="input_data" type="text" placeholder="Palavras chave Ex: loira#!inteligente">
            <button class="btn btn-outline-success">Cadastrar</button>
            <br><br>
        </form>
        <div id="piada" style="width: 50%;height: 50vh;background-color: lightslategray;overflow:auto;">
        </div>
    </main>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
    <script src="JS/cadpiadascript.js" type="text/javascript"></script>
</body>
</html>
