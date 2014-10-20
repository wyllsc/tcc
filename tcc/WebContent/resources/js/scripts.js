function validarFormulario(xhr, status, args) {  
    if(!args.sucesso) {  
        jQuery('#dialog').effect("shake", { times:3 }, 100);  
    } else {  
        alterar.hide();
    }  
}