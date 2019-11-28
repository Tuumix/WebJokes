<%-- 
    Document   : cadastro_categoria
    Created on : 27/11/2019, 04:06:03
    Author     : Hiroshi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="CSS/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href="CSS/inicial.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <header class="cabecario">
            <button onclick="red_index()"><i class="fa fa-home">Home</i></button>
        </header>
        <form style="display: flex;align-content: center;align-items: center;width: 100%;height: 940px;flex-direction: column;justify-content: center;background-image: linear-gradient(#34495e, #2c3e50);">
            <div class="formulario">
                <div class="input-group mb-3" style="width:350px;">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon3">Categoria</span>
                    </div>
                    <input type="text" class="form-control" id="titulo" aria-describedby="basic-addon3">
                </div>
                <input class="btn-cad" type="button" value="Cadastrar">
            </div>
        </form>
    </body>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
    <script src="JS/scripts.js" type="text/javascript"></script>
</html>
