function red_login()
{
    window.location.replace("tela_login.html");
}
function red_index()
{
    window.location.replace("index.jsp");
}

function red_form()
{
    window.location.replace("formulario.html");
}

function red_piada()
{
    window.location.replace("cadastro_piadas.jsp");
}

function red_categoria()
{
    window.location.replace("cadastro_categoria.jsp");
}

function red_usuario()
{
    window.location.replace("excluir_usuario.jsp");
}

$(document).ready(function () {
    $.ajax({
        url: "servlet_piada",
        type: "GET",
        data: {"tipo": "onload"},
        success: function (form) {
            $('#piada').empty();
            $('#piada').append(form);
        }
    });
});

function autenticar() {

    $.ajax({
        url: "valida_sessao",
        type: "POST",
        data: {email: $('#email').val(), senha: $('#senha').val()},
        success: function (form) {
            if (!form) {
                $('body').load("index.jsp");
            } else
                $("#erro").html(form);
        }
    });
}

function deslogar() {
    $.ajax({
        url: "deslogar_sessao",
        type: "GET",
        success: function () {
            window.location.replace("index.jsp");
        }
    });
}

function excluir_usuario() {
    $.ajax({
        url: "excluir_usuario",
        type: "GET",
        success: function () {
            window.location.replace("index.jsp");
        }
    });
}

function buscar_piada() {
    $.ajax({
        url: "servlet_piada",
        type: "GET",
        data: {chave: $('#chave').val(), "tipo": "pesq_piada"},
        success: function (form) {
            $('#piada').empty();
            $('#piada').append(form);
        }
    });
}

$("#profileImage").click(function (e) {
    $("#foto").click();
});

function fasterPreview(uploader) {
    if (uploader.files && uploader.files[0]) {
        $('#profileImage').attr('src',
                window.URL.createObjectURL(uploader.files[0]));
    }
}

$("#foto").change(function () {
    fasterPreview(this);
});

$("#piada").on("click", "#curtir", function () {
    $.ajax({
        url: "servlet_piada",
        type: "GET",
        data: {codigo: $(this).val(), "tipo": "curtir"},
        success: function (form) {
            $('#piada').empty();
            $('#piada').append(form);
        }
    });
});

$("#piada").on("click", "#descurtir", function () {
    $.ajax({
        url: "servlet_piada",
        type: "GET",
        data: {codigo: $(this).val(), "tipo": "descurtir"},
        success: function (form) {
            $('#piada').empty();
            $('#piada').append(form);
        }
    });
});

$("#div-btn-categoria").on("click", "#btn-cat", function () {
    $.ajax({
        url: "servlet_piada",
        type: "GET",
        data: {"codigo_cat": $(this).val(), "tipo": "busc_categoria"},
        success: function (form) {
            $('#piada').empty();
            $('#piada').append(form);
        }
    });
});

$('body').on("click", ".btn-cad", function(){
        $.ajax({
        url: "servlet_categoria",
        type: "POST",
        data:{"descricao":$("#titulo").val()},
        success: function () {
            alert('sucesso')
        }
    });
});

$('body').on("click", ".btn-del", function(){
        $.ajax({
        url: "excluir_usuario",
        type: "POST",
        data:{"nome":$("#usuario").val()},
        success: function (form) {
            if(!form)
                alert('sucesso');
            else
                $('#resul').append(form);
        }
    });
});
