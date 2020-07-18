/*
Class Name: JobifyRunner.java
Author:Gary Zhong, Luke Shi, Justin Shi, and Bobby Tao
Date: 07/18/20
Purpose: This class contains the main method, user interface and run the entire program
*/
import java.util.*;

public class JobifyRunner {
   static Scanner sc = new Scanner(System.in);
	// creates a new Database object
   static JobifyDatabase jbfy = new JobifyDatabase("database");
	// declears a new Employer object
   static Employer employer;
	// declear a new employee object
   static Employee employee;
   static String flush; // Used for flushing the Scanner
   static int jobSelected; // used to keep track of the amount of jobs selected for employee
   static int userCount; // used to keep track of the number of users;
   static int jobPosted; // keep track of the amount of jobs posed by the employer user
   
   public static void main(String[] args) {
      jobSelected = 0;
      jobPosted = 0;
      userCount = jbfy.getAccountNum();
      startscreen();// starts the software
   }
   
   //General user start screen
	// Purpose: The main menu asks if the user is an employee
	// or employer, then functions accordingly
   public static void startscreen() {
      try { // all the methods that prompt user for input is contained in a try and catch to
      		// avoid run-time error. If so, the method will call itself and start over again
         int choice;
         System.out.println("Now entering the Jobify software");
         System.out.println("Welcome User");
         System.out.println("Are you 1. employer or 2. employee");
         System.out.println("Enter your choice: ");
         choice = sc.nextInt();
         while (!(choice == 1 || choice == 2)) {
            System.out.println("invalid option. Please try again!");
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();
         }
         if (choice == 1) { // Runs the employer menu 
            employerStartScreen();
         }
         if (choice == 2) { // Runs the employee menu 
            employeeStartScreen();
         }
      } catch (Exception e) {
            // check for invalid input
         flush = sc.nextLine();
         System.out.println("Invalid Input (error occured in startScreen). Please try again!");
         startscreen();// re-runs the start screen interface
      }
   }
   
   //Employer user start screen
	// Purpose: Sign in and sign up menu for employers
   public static void employerStartScreen() {
      try {
         int choice;
         // prompting
         System.out.println("Welcome to the employer start screen");
         System.out.println("Do you want to 1. Sign Up, 2. Sign In or 3. Go back to previous option?");
         choice = sc.nextInt();
         while (choice != 1 && choice != 2 && choice != 3) {
            System.out.println("Invalid Input (error occured in employyerStartScreen). Please try again!");
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();
         }
         if (choice == 1) { // Allows employer to create an account in the sign up method
            signUp();
         }
         if (choice == 2) { // Allows employer to sign in with exisiting accoount in the sign in method
            signIn();
         }
         if (choice == 3) { // Goes back to main menu 
            startscreen();
         }
      } catch (Exception e) {
         flush = sc.nextLine();
         // check for invalid input
         System.out.println("Invalid Input (error occured in employyerStartScreen). Please try again!");
         employerStartScreen();
      }
   }
   
   // Employer sign up interface
	// Purpose: Prompts and allows the employer to create an account
   public static void signUp() {
      String flush;
      if (userCount >= 100){// checking for max limit
         System.out.println("Sorry, already reached maximum capacity for number of users");
         System.out.println("Thank you for using our program, stay safe from COVID19!");
      } else {
         try {
            int choice;
         // prompting for information
            System.out.println("Sign Up");
            System.out.println("Do you want to 1. Enter your information or 2. Go back to previous option?");
            choice = sc.nextInt();
            while (choice != 1 && choice != 2) {
               System.out.println("invalid option. Please try again!");
               System.out.println("Enter your choice: ");
               choice = sc.nextInt();
            }
            if (choice == 1) { // Allows employer to enter their information. go to sign in after execution in adding the account
               jbfy.addAccount();
               userCount++;
               signIn();
            }
            if (choice == 2) { // Goes back to employer menu 
               employerStartScreen();
            }
         } catch (Exception e) {
         // catch invalid input
            flush = sc.nextLine();
            System.out.println("Invalid Input (error occured in employer sign up process). Please try again!");
            signUp();
         }
      }
   }

	// sign in method
   // Purpose: Allows the employer to sign back in to a previous account
   public static void signIn() {
      try {
         int choice;
         // prompting
         System.out.println("Sign in");
         System.out.println("Do you want to 1.Enter your account or 2.Go back to previous option?");
         choice = sc.nextInt();
         while (choice != 1 && choice != 2) {
            System.out.println("invalid option. Please try again!");
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();
         }
         if (choice == 1) { // asks user for account info then test whether it matches an account in
         					// database
            sc.nextLine();
            System.out.println("Enter your username: ");
            String username = sc.nextLine();
            System.out.println("Enter your password: ");
            String password = sc.nextLine();
            if (jbfy.login(username, password)) { // if match, goes to employer menu
               employer = jbfy.getEmployer(username, password);
               jobPosted = employer.getNumPosted();
               employerMenu();
            } else { // if does not match, back to employer start screen, let user to choose whether to sign in or
            			// sign up again
               employerStartScreen();
            }
         } else if (choice == 2) {// go back to previous option
            employerStartScreen();
         }
      } catch (Exception e) {
         // catch invalid input
         flush = sc.nextLine();
         System.out.println("Invalid Input (error occured in employer signIn). Please try again!");
         signIn();
      }
   }
   
   // Employer menu method
	// Purpose: Allows employer to make their task selection
   public static void employerMenu() {
      try {
         int choice;
         // prompting
         System.out.println("Employer menu");
         System.out.println("Notice: You have "+(100-jobPosted)+" empty job slots left");
         System.out.println("1.Add job \n2.Delete job\n3.View posted jobs\n4.Change account information\n5.Exit with saving all information to text file\n6.Exit without saving to text file\n7.Go back to previous option\n8.Print user information");
         choice = sc.nextInt();
         while (choice < 1 || choice > 8) {
            System.out.println("invalid option. Please try again!");
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();
         }
         if (choice == 1) { // goes to the add job method in JobifyDatabase, add a job using database method, then back to employer menu
            if (jobPosted >= 100){// check for max amount of jobs posted
               System.out.println("Sorry you have reached your limit for number of jobs you can posted, you can try to delete job in order to add more");
            } else {
               jbfy.addJob();
               jobPosted++;
            }
            employerMenu();
         }
         if (choice == 2) { // goes to the delete job method in JobifyDatabase, delete a job using database method, then back to employer
         					// menu
            System.out.println("Enter the id of the job to be deleted: ");
            int id = sc.nextInt();
            if (findJob(id)){
               jobPosted--;
            }
            jbfy.deleteJob(id);
            employerMenu();
         }
         if (choice == 3) { // displays all jobs posted by the employer, then goes back to the employer menu
            jbfy.displayJob(employer.getExistingJob());
            employerMenu();
         }
         if (choice == 4) { // goes to the change account info method in JobifyDatabase, change the users information using database method, then
         					// back to employer menu
            employer.changeAccountInfo();
            employerMenu();
         }
         if (choice == 5) { // saves any information to a given file, then exits the program
            jbfy.saveToFile();
            System.out.println("Thank you for using our program, stay safe from COVID19!");
         }
         if (choice == 6) { // exit without saving anything
            System.out.println("Thank you for using our program, stay safe from COVID19!");
         }
         if (choice == 7) { // Goes back to the employer menu 
            employerStartScreen();
         }
         if (choice == 8) {// Print out the employer user information
            System.out.println(employer);
            System.out.println("");
            employerMenu();
         }
      
      } catch (Exception e) {
         // catch invalid input
         flush = sc.nextLine();
         System.out.println("Invalid Input (error occured in employer menu). Please try again!");
         employerMenu();
      }
   }
   
   // employee start screen method
	// Purpose: Allows employee to enter their information
   public static void employeeStartScreen() {
      try {
         int choice;
         // prompting
         System.out.println("Welcome employee user");
         System.out.println("Do you want to 1. Enter your information or 2. Go back to previous option?");
         System.out.println("Enter your choice: ");
         choice = sc.nextInt();
         while (choice != 1 && choice != 2) {
            System.out.println("invalid option. Please try again!");
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();
         }
      	// Allows user to enter information
      	// - option 1
         if (choice == 1) {
            jbfy.setEmployeeInfo();
            employee = jbfy.getEmployee();
            jobSelected = 0;
            employeeMenu();
         }
      	// Goes back to the main menu
      	// - option 2
         if (choice == 2) {
            startscreen();
         }
      } catch (Exception e) {
            // catch invalid input
         flush = sc.nextLine();
         System.out.println("Invalid Input (error occured in employee start screen). Please try again!");
         employeeStartScreen();
      }
   }
   
   // employee menu method
	// Purpose: Allows employee to make their task selection
   public static void employeeMenu() {
      try {
         int choice;
         System.out.println("Employee menu");
         System.out.println("Notice: Reminder again, you can only select "+(100-jobSelected)+" jobs from now on");
         // prompting
         System.out.println(
            	"1. Display all jobs\n2. Display all full time jobs\n3. Display all part time jobs\n4. Search and display full time jobs by your demands\n5. Search and display part time jobs by your demands\n6. Search and display job list by keyword (job title)\n7. Search and display job list by keyword (company title)\n8. Print Resume\n9. Save selected jobs to a file\n10. Exit");
         System.out.println("Enter your option: ");
         choice = sc.nextInt();
         while (choice < 1 || choice > 10) {
            System.out.println("invalid option. Please try again!");
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();
         }
      	// - option 1: Display all jobs
         if (choice == 1) {
            Job[] list = jbfy.get1dJob();
            jbfy.displayJob(list);
            selectajob(list);
         }
      	//- option 2: Display all full time jobs
         if (choice == 2) {
            Job[] list = jbfy.allFullTime();
            jbfy.displayJob(list);
            selectajob(list);
         }
      	// - option 3: Display all part time jobs
         if (choice == 3) {
            Job[] list = jbfy.allPartTime();
            jbfy.displayJob(list);
            selectajob(list);
         }
      	// - option 4: Search and display full time jobs by your demands
         if (choice == 4) {
            Job[] joblist;
            joblist = jbfy.searchFullTimeDemand();
            searchFollowUp(joblist);
         }
      	//- option 5: Search and display part time jobs by your demands
         if (choice == 5) {
            Job[] joblist;
            joblist = jbfy.searchPartTimeDemand();
            searchFollowUp(joblist);
         }
      	// - option 6: Search and display for job list by keyword (job
      	// title)
         if (choice == 6) {
            Job[] joblist;
            joblist = jbfy.searchByTitle();
            searchFollowUp(joblist);
         }
      	// - option 7: Search and display job list by keyword (company
      	// title)
         if (choice == 7) {
            Job[] joblist;
            joblist = jbfy.searchByCompany();
            searchFollowUp(joblist);
         }
      	//- option 8: Print Resume
         if (choice == 8) {
            System.out.println(employee);
            employeeMenu();
         }
      	// - option 9: Save selected jobs to a file
         if (choice == 9) {
            jbfy.saveJobsToFile(jbfy.getSelectJob());
            employeeMenu();
         }
      	// - option 10: Exit
         if (choice == 10) {
            System.out.println("Thank you for using our program, stay safe from COVID19!");
         }
      } catch (Exception e) {
            // catch invalid input
         flush = sc.nextLine();
         System.out.println("Invalid Input (error occured in employee menu). Please try again!");
         employeeMenu();
      }
   }
   
   // search follow up method
	// Purpose: Acts as a follow up to all searching options
   public static void searchFollowUp(Job[] jobslist) {
      Job[] temp = jobslist;
      try {
         int choice;
         //prompting
         System.out.println("Searching Follow Up");
         System.out.println(
            	"1. Display all possible jobs\n2. Sort and display all possible jobs\n3. Back to employee menu");
         choice = sc.nextInt();
      	// Checks for invalid input
         while (choice < 1 || choice > 3) {
            System.out.println("invalid option. Please try again!");
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();
         }
         if (choice == 1) {
            jbfy.displayJob(jobslist);// display the job 
            selectajob(jobslist);
         }
         if (choice == 2) {
            Job[] sortedJob = sortJobs(jobslist);// sorts the job according the user's liking
            jbfy.displayJob(sortedJob);
            selectajob(sortedJob);
         }
         if (choice == 3) {
            employeeMenu(); // goes back to employee menu
         }
      } catch (Exception e) {
         // catch invalid input
         flush = sc.nextLine();
         System.out.println("Invalid Input (error occured in searchFollowUp). Please try again!");
         searchFollowUp(temp);// returns back to the original prompt
      }
   
   }
   
   // Selecting job interface
	// Purpose: Allows employee to select jobs after displaying
   public static void selectajob(Job[] jobslist) {
      Job[] temp = jobslist;
      try {
         int choice;
         System.out.println("Selecting Job Interface");
         System.out.println("Notice: Reminder again, you can only select "+(100-jobSelected)+" jobs from now on");
         System.out.println(
            	"1. Select a job from list by job number and store in the cart \n2. Display selected jobs\n3. Back to employee menu");
         choice = sc.nextInt();
      	// Checks for invalid input
         while (choice < 1 || choice > 3) {
            System.out.println("invalid option. Please try again!");
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();
         }
         if (choice == 1) { // saves job to the list
            if (jobSelected >= 100){// check for limit
               System.out.println("You have reached the maximum amount of jobs that you can select");
            } else {
               int jobNumber;
               System.out.println("Enter the job number: ");
               jobNumber = sc.nextInt();
               jbfy.selectJob(jobNumber, jobslist);
               jobSelected++;
            }
            selectajob(temp);
         }
         if (choice == 2) {// display the jobs already save to the selected job list
            jbfy.displayJob(jbfy.getSelectJob());
            selectajob(temp);
         }
         if (choice == 3) {// goes back to the employee menu
            employeeMenu();
         }
      } catch (Exception e) {
         // catch invalid input
         flush = sc.nextLine();
         System.out.println("Invalid Input (error occured in select a job). Please try again!");
         selectajob(temp);
      }
   }
   
   // Sorting job interface
	// Purpose: Sorts jobs and returns a sorted Job array
   public static Job[] sortJobs(Job[] jobslist) {
      Job[] temp = jobslist;
      try {
         int choice;
         //prompting
         System.out.println("Sorting Job Menu");
         System.out.println(
            	"1. Sort from high wage to low wage\n2. Sort from large company size to small company size");
         System.out.println("Enter your choice: ");
         choice = sc.nextInt();
      	// Checks for invalid input
         while (choice != 1 && choice != 2) {
            System.out.println("invalid option. Please try again!");
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();
         }
         if (choice == 1) {
            return jbfy.sortByWage(jobslist); // Sorts by wage
         }
         if (choice == 2) {
            return jbfy.sortByCompanySize(jobslist); //Sorts by company size
         }
      } catch (Exception e) {
         // catch invalid input
         flush = sc.nextLine();
         System.out.println("Invalid Input (error occured in sorting job). Please try again!");
         sortJobs(temp);
      }
      return null;
   }
   
   // use this method to see if the id the user want to delete actually exist and update jobs posted
   public static boolean findJob(int id){ 
      Job [] jobs = employer.getExistingJob();
      for (int i = 0; i < jobs.length; i++) {
         if (id == jobs[i].getId()) {
            return true;
         }
      }
      return false;
   }
}