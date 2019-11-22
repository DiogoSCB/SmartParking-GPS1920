package sample;

/*
 * NOTAS
 * <nota 21/11/2019>
 *     Classe com Setters, Getters e Validações para formulário de Cliente *
 *     Fica a faltar:
 *     - Cruzar com BD
 *     - Cruzar com interface gráfica
 *     - Testes de J-UNIT
 *
 *      Em caso de se usar JSON, provavelmente validação de matrícula será mais simples. *
 *     </nota>
 *
 * <nota 22/11/2019>
        * Aplicadas code conventions (passado de PT para ING)
        * Função setter e getter de park alterada para arraylist para testes, solicitado por colega
 </nota>
 */

import java.util.ArrayList;

public class Controller {
   private String licensePlate, name, email;
   private ArrayList<String>park;

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        if (validateLicensePlate(licensePlate)==false) return;
        this.licensePlate = licensePlate;
    }

    //se se usar JSON, altera-se
    private boolean validateLicensePlate(String licensePlate) {
        if (licensePlate.isBlank()|| licensePlate.isEmpty()) return false;
        if ( licensePlate.length() != 8) return false; //2nums + 2letras + 2nums + 2"-"
        int count = 0;
        for(char c : licensePlate.toCharArray()) {
            if(Character.isWhitespace(c)) return false; //salta logo fora
            count ++;
            if(Character.isDigit(c) && (count == 3 || count == 4))
                return false;
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (validateNome(name) == false) return;
        this.name = name;
    }

    private boolean validateNome(String name) {
        if (name.isBlank()|| name.isEmpty()) return false;

        for(char c : name.toCharArray()) {
            if(Character.isDigit(c)) //se tem número
                return false;
        }
        if (name.length()<3 || name.length()>40) return false;

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

    //para teste, solicitado por colega
    public ArrayList getPark() {
        //return park;
        park = new ArrayList<> ();
        park.add("P02");
        return park;
    }

    public void setPark(ArrayList<String> park) {
        this.park = park;
    }

}
