/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bball_000
 */
public class Test_Encrypt 
{
    public static void main(String[] args) throws InterruptedException 
    {
        Encrypt encrypt = new Encrypt();
        String password = "Test";
        encrypt.setPassword(password);
        encrypt.encryptPassword();
        System.out.println(encrypt.getGeneratedPassword());
    }
}
