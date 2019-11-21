package sample;
/*
 * NOTAS
 * <nota 21/11/2019>
 *     Classe com Setters, Getters e Validações para formulário de Cliente
 *
 *     Fica a faltar:
 *     - Cruzar com BD
 *     - Cruzar com interface gráfica
 *     - Testes de J-UNIT 
 * 
 *      Em caso de se usar JSON, provavelmente validação de matrícula será mais simples.
 * 
 *     </nota>
 */
public class Controller {
   private String matricula, nome, parque, email;

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        if (validateMatricula(matricula)==false) return;
        this.matricula = matricula;
    }

    //se se usar JSON, altera-se
    private boolean validateMatricula(String matricula) {
        if (matricula.isBlank()|| matricula.isEmpty()) return false;
        if ( matricula.length() != 8) return false; //2nums + 2letras + 2nums + 2"-"
        int conta =0;
        for(char c : matricula.toCharArray()) {
            if(Character.isWhitespace(c)) return false; //salta logo fora
            conta ++;
            if(Character.isDigit(c) && (conta ==3 || conta ==4))
                return false;
        }
        return true;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (validateNome(nome) == false) return;
        this.nome = nome;
    }

    private boolean validateNome(String nome) {
        if (nome.isBlank()|| nome.isEmpty()) return false;

        for(char c : nome.toCharArray()) {
            if(Character.isDigit(c)) //se tem número
                return false;
        }
        if (nome.length()<3 || nome.length()>40) return false;

        else
            return true;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (validateEmail(email) == false) return;
        this.email = email;
    }

    private boolean validateEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$"; //formato manhoso
        return email.matches(regex);
    }

    public String getParque() {
        return parque;
    }

    public void setParque(String parque) {
        this.parque = parque;
    }

}
