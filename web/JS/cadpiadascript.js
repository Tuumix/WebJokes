/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var valid = false;

function cadastra_piada() {
    $.ajax({
        url: "cadastra_pia",
        type: "POST",
        data: {titulo: $('#titulo').val(), texto: $('#texto').val(), chave: $('#chave').val(), file: $('#file').val()},
        success: function () {
            alert('deu certo')
        }
    });
}

$(document).ready(function () {
    $.ajax({
        url: "servlet_piada",
        type: "GET",
        data:{"tipo":"carrega_piaUsu"},
        success: function (form) {
            $('#piada').empty();
            $('#piada').append(form);
        }
    });
});

$("body").on("click", ".alterar", function () {
    $(this).removeClass('alterar').addClass('confirmar');
    $(this).text('confirmar');
});

$("body").on("click", ".confirmar", function () {
    $(this).removeClass('alterar').addClass('confirmar');
    $.ajax({
        url: "servlet_piada",
        type: "GET",
        data: {"codigo": $(this).val(),"tipo":"deleta_pia"},
        success: function (form) {
            $('#piada').empty();
            $('#piada').append(form);
            alert('Excluído com Sucesso!');
        }
    });
});

$("body").on("click", ".deletar", function () {
    $.ajax({
        url: "servlet_piada",
        type: "GET",
        data: {"codigo": $(this).val(),"tipo":"deleta_pia"},
        success: function (form) {
            $('#piada').empty();
            $('#piada').append(form);
            alert('Excluído com Sucesso!');
        }
    });
});

function red_index()
{
    window.location.replace("index.jsp");
}

$("#piadaimg").click(function (e) {
    $("#fotoP").click();
});

function fasterPreview(uploader) {
    if (uploader.files && uploader.files[0]) {
        $('#piadaimg').attr('src',
                window.URL.createObjectURL(uploader.files[0]));
    }
}

$("#fotoP").change(function () {
    fasterPreview(this);
});