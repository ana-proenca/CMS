/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cms;

import java.util.Scanner;

/**
 *
 * @author anaclaudiaproenca
 */
public class CMS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        User userLogin = login(sc);
        
        // TODO code application logic here
        DBConnector db = new DBConnector();
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static User login(Scanner sc) {
        System.out.println("Inform an username");
        String username = sc.next();

        System.out.println("Inform a password");
        String password = sc.next();
        try {
            DBConnector db = new DBConnector();
            
            User user = db.login(username, password);
            
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
