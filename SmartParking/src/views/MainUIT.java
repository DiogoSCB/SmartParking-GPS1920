package views;

import database.DBConnection;
import models.User;

import java.util.ArrayList;
import java.util.Scanner;

import static models.Constants.ip;
import static models.Constants.port;



public class MainUIT {

    DBConnection db = new DBConnection(ip,port);
    ArrayList<User> users = db.getUserList();
    //ArrayList<User> users = new ArrayList<>();
    public int comparador(String s ){
        for(User u : this.users){
            String mat;
            mat =u.getLicensePlate();
            if(mat.equals(s))
                return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        MainUIT test = new MainUIT();
        //User a = new User(22,"Carlos", "12II88", null, null, "carlinho@gmail.com", null, 2);
        //User b = new User(22,"Carlos", "21II88", null, null, "carlinho@gmail.com", null, 2);
        //test.users.add(a);
        //test.users.add(b);
        while(true) {

            System.out.println("Inserir matricula para teste:");

            Scanner scanIn = new Scanner(System.in);

            String matricula = scanIn.nextLine();
            int size = test.users.size();
            int comparador = test.comparador(matricula);
            if(comparador==1)
                System.out.println("\n A abrir cancela");
            else
                System.out.println("\n Acesso negado");

        }



    }
}
