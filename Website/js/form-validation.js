//05/12/2019 validações em regex funcionais para browsers Opera e Firefox
//07/12/2019 validação em regex do nome e 2º campo da matricula melhorado (estava a aceitar 1 número)
//08/12/2019 tentativa de colocar botão "Submeter" a aparecer apenas quando todos os campos são válidos


$(function() {
    // Initialize form validation on the registration form.    
	$("form[name='pedido']").validate(
	{
      // Specify validation rules
      rules: {
        // The key name on the left side is the name attribute
        // of an input field. Validation rules are defined
        // on the right side
        matricula1: {
          required: true,
          digits: true
        },		
        matricula2: {
          required: true,
		  pattern: /^[a-zA-Z]+$/
        },	
        matricula3: {
          required: true,
          digits: true
        },
        nome: {
			required: true,
			pattern: /^[a-zA-Z]+$/
		},
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
      submitHandler: function(form) {
        form.submit();
      }
    }

	);
  });
  
