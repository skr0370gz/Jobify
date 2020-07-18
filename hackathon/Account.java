/*
Class Name: Account.java
Author:Gary Zhong, Luke Shi, Justin Shi, and Bobby Tao
Date: 07/18/20
Purpose: This class file keep track of the private information of the user(employer) which consists
of username and password. 

*/
public class Account {
   private String userName;
   private String password;
   
   // constructor
   public Account(String a, String b){
      userName = a;
      password = b;
   }
   
   // accessor and mutator
   public String getUserName(){
      return userName;
   }
   
   public String getPassword (){
      return password;
   }
   
   public void setUserName(String name){
      userName = name;
   }
   
   public void setPassword (String line){
      password = line;
   }
   
    // equals method
   public boolean equals(Account other){
      if (other!=null &&(userName.equals(other.userName)) && (password.equals(other.password))){
         return true;
      } else {
         return false;
      }
   }

}