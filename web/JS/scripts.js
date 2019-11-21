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
    window.location.replace("formulario.html")
}

function red_piada()
{
    window.location.replace("cadastro_piadas.jsp")
}

$(document).ready(function () {
    $.ajax({
        url: "carrega_piada",
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
                $('body').load("index.jsp")
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
            window.location.replace("index.jsp")
        }
    });
}

function excluir_usuario() {
    $.ajax({
        url: "excluir_usuario",
        type: "GET",
        success: function () {
            window.location.replace("index.jsp")
        }
    });
}

function buscar_piada() {
    $.ajax({
        url: "buscapiada",
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
        url: "incrementa_pontos",
        type: "GET",
        data: {codigo: $(this).val()},
        success: function (form) {
            $('#piada').empty();
            $('#piada').append(form);
        }
    });
});

$("#piada").on("click", "#descurtir", function () {
    $.ajax({
        url: "decrementa_pontos",
        type: "GET",
        data: {codigo: $(this).val()},
        success: function (form) {
            $('#piada').empty();
            $('#piada').append(form);
        }
    });
});

$("#div-btn-categoria").on("click", "#btn-cat", function () {
    $.ajax({
        url: "carrega_piada_categoria",
        type: "GET",
        data: {codigo: $(this).val()},
        success: function (form) {
            $('#piada').empty();
            $('#piada').append(form);
        }
    });
});
