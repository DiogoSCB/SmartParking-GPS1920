//05/12/2019 validações em regex funcionais para browsers Opera e Firefox

$(function() {
    // Initialize form validation on the registration form.
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
		  pattern: /^[a-zA-Z]/
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
          email: true,
		  pattern: /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
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