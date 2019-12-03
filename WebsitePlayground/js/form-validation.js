// Wait for the DOM to be ready


$(function() {
    // Initialize form validation on the registration form.
    // It has the name attribute "registration"
    $("form[name='pedido']").validate({
      // Specify validation rules
      rules: {
        // The key name on the left side is the name attribute
        // of an input field. Validation rules are defined
        // on the right side
        matricula1: {
          required: true,
          minlength: 2,
          maxlength: 2,
          digits: true
        },
		
        matricula2: {
          required: true,
          minlength: 2,
          maxlength: 2,
		  //type: regex,
		  //type: "text",
		  //text: true,
		  pattern: /^[a-zA-Z]/
         // digits: false
        },	
        matricula3: {
          required: true,
          minlength: 2,
          maxlength: 2,
          digits: true
        },
        nome: "required",
        email: {
          required: true,
          email: true
        },
        parque: "required"
      },
      // Specify validation error messages
      messages: {
        matricula1: "2 digitos",
        matricula2: "2 letras",
        matricula3: "2 digitos",
        nome: "Introduza um nome válido. (Min. 3, Máx. 40 caracteres)",
        email: "Introduza um endereço válido."
      },
      // Make sure the form is submitted to the destination defined
      // in the "action" attribute of the form when valid
      submitHandler: function(form) {
        form.submit();
      }
    });
  });
