/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        url: "carregapia_usu",
        type: "GET",
        success: function (form) {
            $('#piada').append(form);
        }
    });
});

$("body").on("click", "#alterar", function () {
    $.ajax({
        url: "carrega_piada",
        type: "GET",
        data: {"tipo": "alteracao", "codigo": $(this).val()},
        success: function (form) {
            var str = form.split("#");
            $('#titulo').val(str[0].toString());
            $('#texto').val(str[1].toString());
        }
    });
});

function red_index()
{
    window.location.replace("index.jsp");
}
