/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cms;

/**
 * Class user represents the user table from MySql
 * @author anaclaudiaproenca
 */
public class User {

    private String username;
    private String password;
    private String role;
    private int user_id;

    /**
     * Constructor that initializes the User class with username, password and role defined
     * @param username
     * @param password
     * @param role 
     */
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * Method to return the userId
     *
     * @return
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * Method to set the userId during the query in the data based
     *
     * @param user_id
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     * method to get user name
     * @return 
     */
    public String getUsername() {
        return username;
    }

    /**
     * method to get password
     * @return 
     */
    public String getPassword() {
        return password;
    }

    /**
     * method to get role
     * @return 
     */
    public String getRole() {
        return role;
    }

    /**
     * method to set username
     * @param username 
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * method to set password
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
