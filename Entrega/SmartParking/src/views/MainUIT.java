package views;

import database.DBConnection;
import models.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import static models.Constants.IP;
import static models.Constants.PORT;


public class MainUIT {

    DBConnection db;

    {
        try {
            db = new DBConnection(IP,PORT);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    ArrayList<User> users = db != null ? db.getUserList() : new ArrayList<User>();
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
