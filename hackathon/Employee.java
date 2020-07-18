/*
Class Name: Employee.java
Author:Gary Zhong, Luke Shi, Justin Shi, and Bobby Tao
Date: 07/18/20
Purpose: This class file keeps track of the employee's necessary information that would be required to match his or her
situation with jobs that he or she demands

*/

public class Employee{
      private String firstName;
      private String lastName;
      private String birthdate;
      private char gender;
      private int eduBackground;
      private int workExp;
      
      //constructor
      public Employee(String firstName, String lastName, String birthdate, 
      char gender,int eduBackground, int workExp){
         this.firstName = firstName;
         this.lastName = lastName;
         this.birthdate = birthdate;
         this.gender = gender;
         this.eduBackground = eduBackground;
         this.workExp = workExp;
      }
      
      //accessor
      public String getFirstName(){
         return firstName;
      }
      
      public String getLastName(){
         return lastName; 
      }
      
      public String getBirthdate(){
         return birthdate;   
      }
      
      public char getGender(){
         return gender;   
      }
      
      public int getEduBackground(){
         return eduBackground;   
      }
      
      public int getWorkExp(){
         return workExp;   
      }
      
      //mutator
      public void setFirstName(String firstName){
         this.firstName = firstName;
      }
      
      public void setLastName(String lastName){
         this.lastName = lastName;
      }
      
      public void setBirthdate(String birthdate){
         this.birthdate = birthdate;
      }
      
      public void setGender(char gender){
         this.gender = gender;
      }
      
      public void setEduBackground(int eduBackground){
         this.eduBackground = eduBackground;
      }
      
      public void setWorkExp(int workExp){
         this.workExp = workExp;
      }
      
      //toString - return a string representation of an employee object
      public String toString(){
         String s = "First name: " + firstName + "\n";
         s += "Last name: " + lastName + "\n";
         s += "Birthdate: " + birthdate + "\n";
         s += "Gender: " + gender + "\n";
         s += "Educational background: " + edubToString(eduBackground) + "\n";
         s += "Working experience: " + wexpToString(workExp) + "\n";
         return s;
      }
      
      //private: edubToString - return the actual educational background
      private String edubToString(int eduBackground){
         String edub = "";
         if (eduBackground == 1){
            edub = "none";
         } else if (eduBackground == 2){
            edub = "high school";
         } else if (eduBackground == 3){
            edub = "college";
         } else if (eduBackground == 4){
            edub = "undergraduate";
         } else if (eduBackground == 5){
            edub = "master";
         } else if (eduBackground == 6){
            edub = "phd";
         }
         return edub;
      }
      
      //private: wexpToString - return the actual working experience
      private String wexpToString(int workExp){
         String wexp = "";
         if (workExp == 1){
            wexp = "no experience";
         } else if (workExp == 2){
            wexp = "studying/graduated";
         } else if (workExp == 3){
            wexp = "1 year - 3 years";
         } else if (workExp == 4){
            wexp = "3 years - 5 years";
         } else if (workExp == 5){
            wexp = "5 years - 10 years";
         } else if (workExp == 6){
            wexp = "more than 10 years";
         }
         return wexp;    
      }
      
}