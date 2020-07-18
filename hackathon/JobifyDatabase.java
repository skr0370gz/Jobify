/*
Class Name: JobifyDatabase.java
Author:Gary Zhong, Luke Shi, Justin Shi, and Bobby Tao
Date: 07/18/20
Purpose: This class file keeps track of all the information involved in the program(Employer requirements, Jobs Posted, Employee information.. and many more).
JobifyDatabase also contains methods that help the employer(user) find employees or helps employees (temporary users) find jobs. 

*/
import java.util.*;
import java.io.*;

public class JobifyDatabase {
	// maximum 100 employer account
	// maximum 100 jobs can be posted per account
	// maximum 100 jobs can be selected by the employee user
   private Employer[] employerAccount;
   private Job[][] jobs;
   private int numAccounts;
   private Employee userEmployeeInfo;
   private int numJobs;
   private String databaseTextFile;
   private Job[] selectedJobs;
   private int currentUser;
   private int jobcount;
   
	/*
	 * Constructor method creates a JobifyDatabase object which stores the parameter
	 * file as one of the local variables for the database text file. Return the
	 * newly created JobifyDatabase object
	 */
   public JobifyDatabase(String file) {
      databaseTextFile = file;
      jobs = new Job[100][100];
      employerAccount = new Employer[100];
   	// Creates a try catch to read from the desired file
      try {
         BufferedReader in = new BufferedReader(new FileReader(file + ".txt"));
         String username;
         String password;
         String first;
         String last;
         String companyName;
         String phoneNum;
         int id;
         int type;
         String title;
         int location;
         int industry;
         int companySize;
         int eduBackground;
         int workExp;
         char gender;
         double monthlyEarning;
         char benefit;
         char sickDays;
         char breaks;
         double hourlyEarning;
         int minWorkHourPerWeek;
         String input;
         numAccounts = Integer.parseInt(in.readLine());
         numJobs = Integer.parseInt(in.readLine());
         boolean finished = false;
      
      	// Reads in information from the file
         for (int i = 0; i < numAccounts; i++) {
         	// Reads in information for employer
            id = 0;
            username = in.readLine();
            password = in.readLine();
            first = in.readLine();
            last = in.readLine();
            phoneNum = in.readLine();
            companyName = in.readLine();
            input = in.readLine();
         
         	// Creates a new employer object with the aquired information
            employerAccount[i] = new Employer(username, password, first, last, companyName, phoneNum);
         
         	// Reads in information for jobs
            while (!finished && !(input.equals("*"))) {
               type = Integer.parseInt(input);
               title = in.readLine();
               location = Integer.parseInt(in.readLine());
               industry = Integer.parseInt(in.readLine());
               companySize = Integer.parseInt(in.readLine());
               eduBackground = Integer.parseInt(in.readLine());
               workExp = Integer.parseInt(in.readLine());
               gender = (in.readLine()).charAt(0);
            	// Reads information and creates a FullTimeJob object
               if (type == 1) {
                  monthlyEarning = Double.parseDouble(in.readLine());
                  benefit = (in.readLine()).charAt(0);
                  sickDays = (in.readLine()).charAt(0);
                  breaks = (in.readLine()).charAt(0);
                  jobs[i][id] = new FullTimeJob(id, title, location, industry, companySize, eduBackground,
                     	workExp, gender, monthlyEarning, benefit, sickDays, breaks);
                  employerAccount[i].postJob(jobs[i][id], id);
               	// Reads information and creates a PartTimeJob object
               } else if (type == 2) {
                  hourlyEarning = Double.parseDouble(in.readLine());
                  minWorkHourPerWeek = Integer.parseInt(in.readLine());
                  jobs[i][id] = new PartTimeJob(id, title, location, industry, companySize, eduBackground,
                     	workExp, gender, hourlyEarning, minWorkHourPerWeek);
                  employerAccount[i].postJob(jobs[i][id], id);
               }
               id++;
               input = in.readLine();
            
            	// Detects if the file has been completely read by the BufferedReader
               if (input == null) {
                  finished = true;
               }
            }
         }
      } catch (IOException iox) { // This catches errors that may result from file reading
         System.out.println("Error reading the file.");
      }
   }

	/*
	 * Data: This method has no parameter and returns an Employer array without nulls
	 * Purpose: This method used to prevent an error when a field or fields are null, and they are being compared.
	 */
   public Employer[] getExistingEmployer() {
      Employer[] exist = new Employer[numAccounts];
      int count = 0;
      for (int i = 0; i < employerAccount.length; i++) {
         if (employerAccount[i] != null) {
            exist[count] = employerAccount[i];
            count++;
         }
      }
      return exist;
   }
   
	// This method returns the employee object stored to the runner
	// Purpose: save to the runner employee object
   public Employee getEmployee() {
      return userEmployeeInfo;
   }
	
	// Accessor for selectedJobs
   public Job[] getSelectJob() {
      return selectedJobs;
   }
   
   // Accessor for jobs posted
   public int getJobPosted(){
      return numJobs;
   }
   
   // Accessor for number of users
   public int getAccountNum(){
      return numAccounts;
   }
   
   // Accessor for number of jobs selected
   public int getNumSelected(){
      return jobcount;
   }
   
	/*
	 * Purpose: it’s helpful for user to import information from their, and it is a
	 * necessary step for our program to restore the information created during
	 * previous runs
	 */
   public void loadFromFile() {
      Scanner sc = new Scanner(System.in);
      boolean end = false;
   	// Prevent incorrect input from the user with a while loop and a boolean
   	// variable
      while (!end) {
         System.out.println("Please enter the text file name: ");// Prompt
         String file = sc.nextLine();
         try {
            BufferedReader in = new BufferedReader(new FileReader(file + ".txt"));
            end = true;
            String username;
            String password;
            String first;
            String last;
            String companyName;
            String phoneNum;
            int id;
            int type;
            String title;
            int location;
            int industry;
            int companySize;
            int eduBackground;
            int workExp;
            char gender;
            double monthlyEarning;
            char benefit;
            char sickDays;
            char breaks;
            double hourlyEarning;
            int minWorkHourPerWeek;
            String input;
            numAccounts = Integer.parseInt(in.readLine());
            numJobs = Integer.parseInt(in.readLine());
            boolean finished = false;
         
            for (int i = 0; i < numAccounts; i++) {
               id = 0;
               username = in.readLine();
               password = in.readLine();
               first = in.readLine();
               last = in.readLine();
               phoneNum = in.readLine();
               companyName = in.readLine();
               input = in.readLine();
               employerAccount[i] = new Employer(username, password, first, last, companyName, phoneNum);
               while (!finished && !(input.equals("*"))) {
                  type = Integer.parseInt(input);
                  title = in.readLine();
                  location = Integer.parseInt(in.readLine());
                  industry = Integer.parseInt(in.readLine());
                  companySize = Integer.parseInt(in.readLine());
                  eduBackground = Integer.parseInt(in.readLine());
                  workExp = Integer.parseInt(in.readLine());
                  gender = (in.readLine()).charAt(0);
                  if (type == 1) {
                     monthlyEarning = Double.parseDouble(in.readLine());
                     benefit = (in.readLine()).charAt(0);
                     sickDays = (in.readLine()).charAt(0);
                     breaks = (in.readLine()).charAt(0);
                     jobs[i][id] = new FullTimeJob(id, title, location, industry, companySize, eduBackground,
                        	workExp, gender, monthlyEarning, benefit, sickDays, breaks);
                     employerAccount[i].postJob(jobs[i][id], id);
                  } else if (type == 2) {
                     hourlyEarning = Double.parseDouble(in.readLine());
                     ;
                     minWorkHourPerWeek = Integer.parseInt(in.readLine());
                     jobs[i][id] = new PartTimeJob(id, title, location, industry, companySize, eduBackground,
                        	workExp, gender, hourlyEarning, minWorkHourPerWeek);
                     employerAccount[i].postJob(jobs[i][id], id);
                  }
                  id++;
                  input = in.readLine();
                  if (input == null) {
                     finished = true;
                  }
               }
            }
         } catch (IOException iox) {
            System.out.println("Error reading the file.");
         }
      }
      selectedJobs = new Job[100];
   
   }

	/*
	 * Purpose: It is used to store the program's information to the database file
	 * through a BufferedWriter. It will store all employee and job data so that
	 * they can be used for future runs.
	 */
   public void saveToFile() {
      int count1 = 0;
      int count2 = 0;
      String fileName = "";
      BufferedWriter out;
      BufferedWriter out1;
      int option;
      boolean finished1;
      String flush;
      Employer[] employer = getExistingEmployer();
      Job[] jobPerEmployer = null;
      Scanner sc = new Scanner(System.in);
      boolean end = false;
   	// Prevent incorrect input from the user with a while loop and a boolean
   	// variable
      while (!end) {
         option = -1;
         finished1 = false;
      	// Prevent incorrect input from the user with a while loop
         while (!(option == 1 || option == 2) || !finished1) {
            try {
               System.out.println(
                  	"Save to database or to your own file: 1 to save to company database; 2 to save to your own file");
               option = sc.nextInt();
               finished1 = true;
            } catch (InputMismatchException IME) {
               System.out.println("Incorrect data type inputted");
               flush = sc.nextLine();
            }
         }
      	// Allows user to enter their desired file name
         if (option == 2) {
            System.out.print("Enter file name: ");
            flush = sc.nextLine();
            fileName = sc.nextLine();
         }
         try {
         	// Checks the user's choice and outputs to a separate file or the main database
         	// file
            if (option == 2) {
               out1 = new BufferedWriter(new FileWriter(fileName + ".txt"));
               out1.write("" + numAccounts);
               out1.newLine();
               out1.write("" + numJobs);
               out1.newLine();
               for (int i = 0; i < employer.length; i++) {
                  count1++;
                  count2 = 0;
                  out1.write(employer[i].getUser().getUserName());
                  out1.newLine();
                  out1.write(employer[i].getUser().getPassword());
                  out1.newLine();
                  out1.write(employer[i].getFirstName());
                  out1.newLine();
                  out1.write(employer[i].getLastName());
                  out1.newLine();
                  out1.write(employer[i].getPhoneNum());
                  out1.newLine();
                  out1.write(employer[i].getCompanyName());
                  out1.newLine();
                  jobPerEmployer = employer[i].getExistingJob();
                  for (int j = 0; j < jobPerEmployer.length; j++) {
                     if (jobPerEmployer[j] instanceof FullTimeJob) {
                        out1.write("1");
                        out1.newLine();
                     } else {
                        out1.write("2");
                        out1.newLine();
                     }
                     out1.write(jobPerEmployer[j].getTitle());
                     out1.newLine();
                     out1.write("" + jobPerEmployer[j].getLocation());
                     out1.newLine();
                     out1.write("" + jobPerEmployer[j].getIndustry());
                     out1.newLine();
                     out1.write("" + jobPerEmployer[j].getCompanySize());
                     out1.newLine();
                     out1.write("" + jobPerEmployer[j].getEduBackground());
                     out1.newLine();
                     out1.write("" + jobPerEmployer[j].getWorkExp());
                     out1.newLine();
                     out1.write("" + jobPerEmployer[j].getGender());
                     out1.newLine();
                  
                  	// Checks if the job is full or part time and accesses their unique fields
                     if (jobPerEmployer[j] instanceof FullTimeJob) {
                        // polymorphism is used here with casting (FullTimeJob)
                        // inheritance is used here with casting (FullTimeJob)
                        out1.write("" + ((FullTimeJob) jobPerEmployer[j]).getMonthlyEarning());
                        out1.newLine();
                        out1.write("" + ((FullTimeJob) jobPerEmployer[j]).getBenefit());
                        out1.newLine();
                        out1.write("" + ((FullTimeJob) jobPerEmployer[j]).getSickDays());
                        out1.newLine();
                        out1.write("" + ((FullTimeJob) jobPerEmployer[j]).getBreaks());
                        if (count1 != employer.length || count2 != jobPerEmployer.length - 1) {
                           out1.newLine();
                           count2++;
                        }
                     } else {
                        // polymorphism is used here with casting (PartTimeJob)
                        // inheritance is used here with casting (PartTimeJob)
                        out1.write("" + ((PartTimeJob) jobPerEmployer[j]).getHourlyEarning());
                        out1.newLine();
                        out1.write("" + ((PartTimeJob) jobPerEmployer[j]).getMinWorkHourPerWeek());
                        if (count1 != employer.length || count2 != jobPerEmployer.length - 1) {
                           out1.newLine();
                           count2++;
                        }
                     }
                  }
               
               	// Places an asterisk to separate each employer's information in the file
                  if (count1 != employer.length) {
                     out1.write("*");
                     out1.newLine();
                  }
               }
               out1.close();
            }
            out = new BufferedWriter(new FileWriter(databaseTextFile + ".txt"));
            count1= 0 ;
         	// Writes the specified information from each object into the file
            out.write("" + numAccounts);
            out.newLine();
            out.write("" + numJobs);
            out.newLine();
            for (int i = 0; i < employer.length; i++) {
               count1++;
               count2 = 0;
               out.write(employer[i].getUser().getUserName());
               out.newLine();
               out.write(employer[i].getUser().getPassword());
               out.newLine();
               out.write(employer[i].getFirstName());
               out.newLine();
               out.write(employer[i].getLastName());
               out.newLine();
               out.write(employer[i].getPhoneNum());
               out.newLine();
               out.write(employer[i].getCompanyName());
               out.newLine();
               jobPerEmployer = employer[i].getExistingJob();
               for (int j = 0; j < jobPerEmployer.length; j++) {
                  if (jobPerEmployer[j] instanceof FullTimeJob) {
                     out.write("1");
                     out.newLine();
                  } else {
                     out.write("2");
                     out.newLine();
                  }
                  out.write(jobPerEmployer[j].getTitle());
                  out.newLine();
                  out.write("" + jobPerEmployer[j].getLocation());
                  out.newLine();
                  out.write("" + jobPerEmployer[j].getIndustry());
                  out.newLine();
                  out.write("" + jobPerEmployer[j].getCompanySize());
                  out.newLine();
                  out.write("" + jobPerEmployer[j].getEduBackground());
                  out.newLine();
                  out.write("" + jobPerEmployer[j].getWorkExp());
                  out.newLine();
                  out.write("" + jobPerEmployer[j].getGender());
                  out.newLine();
               
               	// Checks if the job is full or part time and accesses their unique fields
                  if (jobPerEmployer[j] instanceof FullTimeJob) {
                     // polymorphism is used here with casting (FullTimeJob)
                     // inheritance is used here with casting (FullTimeJob)
                     out.write("" + ((FullTimeJob) jobPerEmployer[j]).getMonthlyEarning());
                     out.newLine();
                     out.write("" + ((FullTimeJob) jobPerEmployer[j]).getBenefit());
                     out.newLine();
                     out.write("" + ((FullTimeJob) jobPerEmployer[j]).getSickDays());
                     out.newLine();
                     out.write("" + ((FullTimeJob) jobPerEmployer[j]).getBreaks());
                     if (count1 != employer.length || count2 != jobPerEmployer.length - 1) {
                        out.newLine();
                        count2++;
                     }
                  } else {
                     // polymorphism is used here with casting (PartTimeJob)
                     // inheritance is used here with casting (PartTimeJob)
                     out.write("" + ((PartTimeJob) jobPerEmployer[j]).getHourlyEarning());
                     out.newLine();
                     out.write("" + ((PartTimeJob) jobPerEmployer[j]).getMinWorkHourPerWeek());
                     if (count1 != employer.length || count2 != jobPerEmployer.length - 1) {
                        out.newLine();
                        count2++;
                     }
                  }
               }
            
            	// Places an asterisk to separate each employer's information in the file
               if (count1 != employer.length) {
                  out.write("*");
                  out.newLine();
               }
            }
            out.close();
            end = true;
         } catch (IOException iox) { // This catches errors that may result from file writing
            System.out.print("WARNING: Error Writing File");
         }
      }
   }

	/*
	 * Data: This method takes in a job array and writes the information to a file
	 * Purpose: The list's information is written to the user specified file so that
	 * they can have a copy of their job list stored on their computer.
	 */
   public void saveJobsToFile(Job[] list) {
      Scanner sc = new Scanner(System.in);
      String fileName = "";
      BufferedWriter out;
      int count1 = 0;
      int count2 = 0;
      Job[] jobList = getExistingJob(list);
      boolean complete = false;
   	
   	// Allows user to enter their desired file name
      while (!complete) {
         System.out.print("Enter file name: ");
         fileName = sc.nextLine();
         try {
            out = new BufferedWriter(new FileWriter(fileName + ".txt"));
            complete = true;
         
            for (int i = 0; i < jobList.length; i++) {
               if (jobList[i] instanceof FullTimeJob) {
                  out.write("1");
                  out.newLine();
               } else {
                  out.write("2");
                  out.newLine();
               }
            	// Writes the specified information from each object into the file
               out.write("" + jobList[i].getTitle());
               out.newLine();
               out.write("" + jobList[i].getLocation());
               out.newLine();
               out.write("" + jobList[i].getIndustry());
               out.newLine();
               out.write("" + jobList[i].getCompanySize());
               out.newLine();
               out.write("" + jobList[i].getEduBackground());
               out.newLine();
               out.write("" + jobList[i].getWorkExp());
               out.newLine();
               out.write("" + jobList[i].getGender());
               out.newLine();
            
            	// Checks if the job is full or part time and accesses their unique fields
               if (jobList[i] instanceof FullTimeJob) {
                  // polymorphism is used here with casting (FullTimeJob)
               // inheritance is used here with casting (FullTimeJob)
                  out.write("" + ((FullTimeJob) jobList[i]).getMonthlyEarning());
                  out.newLine();
                  out.write("" + ((FullTimeJob) jobList[i]).getBenefit());
                  out.newLine();
                  out.write("" + ((FullTimeJob) jobList[i]).getSickDays());
                  out.newLine();
                  out.write("" + ((FullTimeJob) jobList[i]).getBreaks());
                  if (count2 != jobList.length - 1) {
                     out.newLine();
                     count2++;
                  }
               } else {
                  // polymorphism is used here with casting (PartTimeJob)
               // inheritance is used here with casting (PartTimeJob)
                  out.write("" + ((PartTimeJob) jobList[i]).getHourlyEarning());
                  out.newLine();
                  out.write("" + ((PartTimeJob) jobList[i]).getMinWorkHourPerWeek());
                  if (count2 != jobList.length - 1) {
                     out.newLine();
                     count2++;
                  }
               }
            }
            out.close();
         } catch (IOException iox) { // This catches errors that may result from file writing
            System.out.print("WARNING: Error Writing File");
         }
      }
   }

	// This method prompts and allows the employer to create a new Job object
   public void postJobPrompt() {
      Scanner sc = new Scanner(System.in);
      int index = 0;
      boolean validInput = true;
      System.out.println("What type is the job? (1. Full time, 2. Part time)");
      int type = sc.nextInt();
      while (type > 2) {
         System.out.println("invalid input, enter again");
         System.out.println("What type is the job? (1. Full time, 2. Part time)");
         type = sc.nextInt();
      }
      System.out.println("What is the job title: ");
      sc.nextLine();
      String title = sc.nextLine();
      System.out.println("What location is the job(1.North America, 2.South America, 3. Europe, 4.Asia, 5.Australia, 6.Africa)");
      int location = sc.nextInt();
      while (location > 6) {
         System.out.println("invalid input, enter again");
         System.out.println("What location is the job (1.North America, 2.South America, 3. Europe, 4.Asia, 5.Australia, 6.Africa)");
         location = sc.nextInt();
      }
      System.out.println(
         	"What industry does it belongs to? (1. law, 2. finance, 3. engineering, 4. medical, 5. service industry, 6. government, 7. other) ");
      int industry = sc.nextInt();
      while (industry > 7) {
         System.out.println("invalid input, enter again");
         System.out.println(
            	"What industry does it belongs to? (1. law, 2. finance, 3. engineering, 4. medical, 5. service industry, 6. government, 7. other) ");
         industry = sc.nextInt();
      }
      System.out.println(
         	"What is the company size of this job? (1. <50, 2. 50-500, 3. 500-1000, 4. 1000-5000, 5. >5000)");
      int companySize = sc.nextInt();
      while (companySize > 5) {
         System.out.println("invalid input, enter again");
         System.out.println(
            	"What is the company size of this job? (1. <50, 2. 50-500, 3. 500-1000, 4. 1000-5000, 5. >5000)");
         companySize = sc.nextInt();
      }
      System.out.println(
         	"What is the education background required? (1. none, 2. high school, 3. college, 4. undergraduate, 5. master, 6. phd)");
      int eduBackground = sc.nextInt();
      while (eduBackground > 6) {
         System.out.println("invalid input, enter again");
         System.out.println(
            	"What is the education background required? (1. none, 2. high school, 3. college, 4. undergraduate, 5. master, 6. phd)");
         eduBackground = sc.nextInt();
      }
      System.out.println(
         	"What is the work experience required? (1. no experience needed, 2. studying/graduated, 3. 1-3yr, 4. 3-5yr, 5. 5-10yr, 6. >10yr)");
      int workExp = sc.nextInt();
      while (workExp > 6) {
         System.out.println("invalid input, enter again");
         System.out.println(
            	"What is the work experience required? (1. no experience needed, 2. studying/graduated, 3. 1-3yr, 4. 3-5yr, 5. 5-10yr, 6. >10yr)");
         workExp = sc.nextInt();
      }
      System.out.println("Any gender specification? (M, F, N)");
      sc.nextLine();
      char gender = sc.nextLine().charAt(0);
      while (gender != 'M' && gender != 'F' && gender != 'N') {
         System.out.println("invalid input, enter again");
         System.out.println("Any gender specification? (M, F, N)");
         gender = sc.nextLine().charAt(0);
      }
      boolean found = false;
      if (type == 1) {
         System.out.println("What is the monthly wages of the job: ");
         double monthlyEarnings = sc.nextDouble();
         sc.nextLine();
         System.out.println("Any benefits? (Y, N)");
         char benefits = sc.nextLine().charAt(0);
         while (benefits != 'Y' && benefits != 'N') {
            System.out.println("invalid input, enter again");
            System.out.println("Any benefits? (Y, N)");
            benefits = sc.nextLine().charAt(0);
         }
         System.out.println("Sick days applicable? (Y, N)");
         char sickDays = sc.nextLine().charAt(0);
         while (sickDays != 'Y' && sickDays != 'N') {
            System.out.println("invalid input, enter again");
            System.out.println("Sick days applicable? (Y, N)");
            sickDays = sc.nextLine().charAt(0);
         }
         System.out.println("Break applicable? (Y, N)");
         char breaks = sc.nextLine().charAt(0);
         while (breaks != 'Y' && breaks != 'N') {
            System.out.println("invalid input, enter again");
            System.out.println("Break applicable? (Y, N)");
            breaks = sc.nextLine().charAt(0);
         }
      	// Find the first job object that is null and saves the new job into the null
      	// job with found index
         for (int i = 0; i < jobs[currentUser].length & !found; i++) {
            if (jobs[currentUser][i] == null) {
               found = true;
               index = i;
            }
         }
         if (found) {
            jobs[currentUser][index] = new FullTimeJob(index, title, location, industry, companySize, eduBackground,
               	workExp, gender, monthlyEarnings, benefits, sickDays, breaks);
            numJobs++;
            employerAccount[currentUser].postJob(jobs[currentUser][index], index);
         } else {
            System.out.println("You can no longer post job, all filled");
         }
      } else if (type == 2) {
         System.out.println("What is the hourly earning of the job: ");
         double hourlyEarnings = sc.nextDouble();
         System.out.println("What is the minimum work hours per week: ");
         int minWorkHourPerWeek = sc.nextInt();
      	// Find the first job object that is null and saves the new job into the null
      	// job with found index
         for (int i = 0; i < jobs[currentUser].length & !found; i++) {
            if (jobs[currentUser][i] == null) {
               found = true;
               index = i;
            }
         }
         if (found) {
            jobs[currentUser][index] = new PartTimeJob(index, title, location, industry, companySize, eduBackground,
               	workExp, gender, hourlyEarnings, minWorkHourPerWeek);
            numJobs++;
            employerAccount[currentUser].postJob(jobs[currentUser][index], index);
         } else {
            System.out.println("You can no longer post job, all filled");
         }
      }
   }

	/*
	 * Data: Takes in the name of the file
	 * Purpose: This method is used to create a Job object from a file
	 */
   public void postJobFile(String filename) {
      int index = 0;
      try {
         BufferedReader in = new BufferedReader(new FileReader(filename));
         boolean found = false;
         if (in.readLine().compareTo("1") == 0) {
         	// Find the first job object that is null and saves the new job into the null
         	// job with found index
            for (int i = 0; i < jobs[currentUser].length && !found; i++) {
               if (jobs[currentUser][i] == null) {
                  found = true;
                  index = i;
               }
            }
            if (found) {
               jobs[currentUser][index] = new FullTimeJob(index, in.readLine(), Integer.parseInt(in.readLine()),
                  	Integer.parseInt(in.readLine()), Integer.parseInt(in.readLine()),
                  	Integer.parseInt(in.readLine()), Integer.parseInt(in.readLine()), in.readLine().charAt(0),
                  	Double.parseDouble(in.readLine()), in.readLine().charAt(0), in.readLine().charAt(0),
                  	in.readLine().charAt(0));
               numJobs++;
               employerAccount[currentUser].postJob(jobs[currentUser][index], index);
            } else {
               System.out.println("You can no longer post job, all filled");
            }
         } else if (in.readLine().compareTo("2") == 0) {
         	// Find the first job object that is null and saves the new job into the null
         	// job with found index
            for (int i = 0; i < jobs[currentUser].length && !found; i++) {
               if (jobs[currentUser][i] == null) {
                  found = true;
                  index = i;
               }
            }
         
            if (found) {
               jobs[currentUser][index] = new PartTimeJob(index, in.readLine(), Integer.parseInt(in.readLine()),
                  	Integer.parseInt(in.readLine()), Integer.parseInt(in.readLine()),
                  	Integer.parseInt(in.readLine()), Integer.parseInt(in.readLine()), in.readLine().charAt(0),
                  	Double.parseDouble(in.readLine()), Integer.parseInt(in.readLine()));
               numJobs++;
               employerAccount[currentUser].postJob(jobs[currentUser][index], index);
            } else {
               System.out.println("You can no longer post job, all filled");
            }
         }
      } catch (Exception e) {
         System.out.println("Cannot find text file ");
      }
   }

	/*
	 * This method prompts a new employer user to create their new account. Purpose:
	 * By creating an object, the employer's data can be stored and be recorded
	 * within the database file for later use.
	 */
   public void addAccount() {
      Scanner sc = new Scanner(System.in);
   	// Prompts and allows the employer to enter their account information
      try {
         System.out.print("Enter a username: ");
         String username = sc.nextLine();
         while (findRepeat(username)) { // Checks if the username is already used and prompts for a new one if it is
            System.out.println("Username already exists. Please enter a new username: ");
            username = sc.nextLine();
         }
         System.out.print("Enter a password: ");
         String password = sc.nextLine();
         System.out.println("You have created an account! Now please enter your personal information.");
         System.out.print("First Name: ");
         String firstName = sc.nextLine();
         System.out.print("Last Name: ");
         String lastName = sc.nextLine();
         System.out.print("Company Name: ");
         String companyName = sc.nextLine();
         System.out.print("Contact Information: ");
         String phoneNum = sc.nextLine();
         int index = 0;
         boolean found = false;
      	// Searches for the first null slot in the emoployer array
         for (int i = 0; i < numAccounts & !found; i++) {
            if (employerAccount[i] == null) {
               found = true;
               index = i;
            }
         }
      
      	// Creates a new Employer object in the previously aquired slot
         if (found) {
            employerAccount[index] = new Employer(username, password, firstName, lastName, companyName, phoneNum);
         } else {
            employerAccount[numAccounts] = new Employer(username, password, firstName, lastName, companyName,
               	phoneNum);
            numAccounts++;
         }
      } catch (Exception e) {
         System.out.println("error with your input ");
      }
   }

	// this method returns the current jobs that are listed in the company data
   public Job[][] getJobs() {
      return jobs;
   }

	// Data: The parameter is the username prompt from the user. It returns the status of the username
	// Purpose: This method is used to check if the new employer's username is already taken,
	// then return a boolean
   private boolean findRepeat(String username) {
      Boolean repeat = false;
      Employer[] list = getExistingEmployer();
      for (int i = 0; i < list.length && !repeat; i++) {
         if (list[i].getUser().getUserName().equals(username)) {
            repeat = true;
         }
      }
      return repeat;
   }

	// This method allows the employer to add a new job to their listing
   public void addJob() {
      Scanner sc = new Scanner(System.in);
      System.out.println("Would you like to enter job information manually or from a file (1. Manually, 2. File)");
      int choice = sc.nextInt();
      sc.nextLine();
   	// The first choice uses the manual prompts to post a job
      if (choice == 1) {
         postJobPrompt();
      	// The second choice allows the user to post a job from their files
      } else if (choice == 2) {
         System.out.println("Enter your file name: ");
         String filename = sc.nextLine();
         postJobFile(filename + ".txt");
      	// This catches invalid input
      } else {
         System.out.println("invalid choice");
      }
   }

	// This method is used to allow the employer to delete a job from their listings
   public void deleteJob(int id) {
      Scanner sc = new Scanner(System.in);
   	// Prevent invalid input
         boolean found = false;
   
   	// Checks if the job indicated belongs to the current employer
      for (int i = 0; i < jobs[currentUser].length && !found; i++) {
         if (id == jobs[currentUser][i].getId()) {
            found = true;
            employerAccount[currentUser].deleteJob(id);
            jobs[currentUser][i] = null;
            numJobs--;
         }
      }
      if (!found) {
         System.out.println("The id does not match your posted jobs");
      }
   }

	/*
	 * Purpose: By creating an object, the employee's data can be stored and be used for their
	 * job searches. The information will be deleted after they exit the program.
	 */
   public void setEmployeeInfo() {
      Scanner sc = new Scanner(System.in);
      String input;
      String firstName;
      String lastName;
      String birthdate;
      char gender;
      int eduBackground;
      int workExp;
      selectedJobs = new Job[100]; //Sets the max number of selected jobs
   	
      System.out.print("First Name: ");
      firstName = sc.nextLine();
      System.out.print("Last Name: ");
      lastName = sc.nextLine();
      System.out.print("Birthdate: ");
      birthdate = sc.nextLine();
   
   	// Prompts for the employee's information
      System.out.print("Gender (M, F, N): ");
      input = sc.nextLine();
   	// Verifies the input and continues prompting if it is invalid
      while (!input.equals("M") && !input.equals("F") && !input.equals("N")) {
         System.out.println("ERROR: Invalid Input");
         System.out.print("Gender (M, F, N): ");
         input = sc.nextLine();
      }
      gender = input.charAt(0);
   
      System.out.println(
         	"Education Background: \n(1) None\n(2) High School\n(3) College\n(4) Undergraduate\n(5) Master\n(6) PhD");
      input = sc.nextLine();
   	// Verifies the input and continues prompting if it is invalid
      while (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4")
      		&& !input.equals("5") && !input.equals("6")) {
         System.out.println("ERROR: Invalid Input");
         System.out.println(
            	"Education Background: \n(1) None\n(2) High School\n(3) College\n(4) Undergraduate\n(5) Master\n(6) PhD");
         input = sc.nextLine();
      }
      eduBackground = Integer.parseInt(input);
   
      System.out.println(
         	"Work Experience: \n(1) None\n(2) Studying/Graduated\n(3) 1-3yr\n(4) 3-5yr\n(5) 5-10yr\n(6) >10yr");
      input = sc.nextLine();
   	// Verifies the input and continues prompting if it is invalid
      while (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4")
      		&& !input.equals("5") && !input.equals("6")) {
         System.out.println("ERROR: Invalid Input");
         System.out.println(
            	"Work Experience: \n(1) None\n(2) Studying/Graduated\n(3) 1-3yr\n(4) 3-5yr\n(5) 5-10yr\n(6) >10yr");
         input = sc.nextLine();
      }
      workExp = Integer.parseInt(input);
   
   	// Creates a new employee object
      userEmployeeInfo = new Employee(firstName, lastName, birthdate, gender, eduBackground, workExp);
   
      jobcount = 0;
   }

	// An accessor method that returns the list of users
   public Employer[] getEmployerAccount() {
      return employerAccount;
   }

	/*
	 * Data: Takes in a Job array and returns a sorted Job array
	 * Purpose: Sorts the given job array from high wage to low wage Uses sorting algorithm(s)
	 * to compare wages from high to low and reorders the jobs to a temporary job
	 * array The sorting algorithm used in this method is the bubble sort algorithm
	 */
   public Job[] sortByWage(Job[] list) {
      int index;
   	// go through the job array using the knowledge of bubble sort to sort by wage
      boolean sorted = false;
      Job[] job = getExistingJob(list);
      Job temp = null;
      for (int i = job.length - 1; i > 0 && !sorted; i--) {
         sorted = true;
         for (int j = 0; j < i; j++) {
         	// check for if the two object comparing are both fulltime(higher rank)
         	// and look at the monthly earning for each and swap if possible
            // polymorphism is used here with casting (FullTimeJob)
            // inheritance is used here with casting (FullTimeJob)
            if ((job[j] instanceof FullTimeJob) && (job[j + 1] instanceof FullTimeJob) && ((FullTimeJob) job[j])
            		.getMonthlyEarning() < ((FullTimeJob) job[j + 1]).getMonthlyEarning()) {
               temp = job[j];
               job[j] = job[j + 1];
               job[j + 1] = temp;
               sorted = false;
            }
         }
      }
      sorted = false;
      temp = null;
      for (int i = job.length - 1; i > 0 && !sorted; i--) {
         sorted = true;
         for (int j = 0; j < i; j++) {
         	// check for if the first object job[j+1]is full time and job[j] is part time
         	// and if true, swap as the heirchy is incorrect
            if ((job[j + 1] instanceof FullTimeJob) && (job[j] instanceof PartTimeJob)) {
               temp = job[j];
               job[j] = job[j + 1];
               job[j + 1] = temp;
               sorted = false;
            }
         }
      }
      sorted = false;
      temp = null;
      for (int i = job.length - 1; i > 0 && !sorted; i--) {
         sorted = true;
         for (int j = 0; j < i; j++) {
         	// check for if the two object comparing are both parttime(lower rank)
         	// and look at the monthly earning for each and swap if possible
            // polymorphism is used here with casting (PartTimeJob)
            // inheritance is used here with casting (PartTimeJob)
            if ((job[j] instanceof PartTimeJob) && (job[j + 1] instanceof PartTimeJob)
            		&& ((PartTimeJob) job[j]).getHourlyEarning() < ((PartTimeJob) job[j + 1]).getHourlyEarning()) {
               temp = job[j];
               job[j] = job[j + 1];
               job[j + 1] = temp;
               sorted = false;
            }
         }
      }
   
      return job;
   }

	/* Purpose: The purpose of this method is to check if there is an employer in
	 *  the list that match the given parameters.
	 */
   public Employer getEmployer(String username, String password) {
      for (int i = 0; i < numAccounts; i++) {
         if (employerAccount[i].getUser().getUserName().equals(username)
         		&& employerAccount[i].getUser().getPassword().equals(password)) {
            return employerAccount[i];
         }
      }
      return null;
   }

	/*
	 * Purpose: Sorts the given job array from high company size to low company size
	 * Uses sorting algorithm(s) to compare company size from high to low and
	 * reorders the jobs to a temporary job array The sorting algorithm used in this
	 * method is the bubble sort algorithm
	 */
   public Job[] sortByCompanySize(Job[] list) {
      int index;
   	// go through the job array using the knowledge of bubble sort to sort by company size
      boolean sorted = false;
      Job[] job = getExistingJob(list);
      Job temp = null;
      for (int i = job.length - 1; i > 0 && !sorted; i--) {
         sorted = true;
         for (int j = 0; j < i; j++) {
            if (job[j].getCompanySize() < job[j + 1].getCompanySize()) {
               temp = job[j];
               job[j] = job[j + 1];
               job[j + 1] = temp;
               sorted = false;
            }
         }
      }
   
      return job;
   }

	/* Data: The method that returns a list of job that matches the company keyword
	 * which was prompted from the user in the method
	 * Purpose: The purpose of this method is not to make the user enter too much
	 * but not too
	 * little information while given a specific range of desirement
	 * Therefore this special searching algorithm will also make use of the
	 * recursive method (equalSectionIgnoreCase)
	 */
   public Job[] searchByCompany() {
      Scanner sc = new Scanner(System.in);
      System.out.println("Please enter the keyword for the company");
      String input = sc.nextLine();
      int index = 0;
      int numMatch = 0;
      for (int i = 0; i < numAccounts; i++) {
         if (equalSectionIgnoreCase("", employerAccount[i].getCompanyName(), input, 0)) {
         	// use the boolean returned to determine the size of the array that keep track
         	// of the employer account matched
            numMatch++;
         }
      }
      if (numMatch ==0){
         System.out.println("Sorry, could not find any company that match your keyword.");
         return null;
      }
   	// first use array to keep track of the employer index in which that employer's
   	// company name matches the keyword
      int[] matchNumber = new int[numMatch];
      numMatch = 0;
      for (int i = 0; i < numAccounts; i++) {
         if (equalSectionIgnoreCase("", input, employerAccount[i].getCompanyName(), 0)) {
         	// use the boolean returned to determine the index in the array of employer that
         	// match the company name
            matchNumber[numMatch] = i;
            numMatch++;
         }
      }
      int jobCount = 0;
      for (int i = 0; i < matchNumber.length; i++) {
         for (int j = 0; j < jobs[matchNumber[i]].length; j++) {
            if (jobs[matchNumber[i]][j] != null) {
            	// find the ending list array size
               jobCount++;
            }
         }
      }
      
   	// Then make another array that keep track of the actual employer account
      Job[] list = new Job[jobCount];
      jobCount = 0;
      for (int i = 0; i < matchNumber.length; i++) {
         for (int j = 0; j < jobs[matchNumber[i]].length; j++) {
            if (jobs[matchNumber[i]][j] != null) {
            	// adding the matched array to the fnial list of job
               list[jobCount] = jobs[matchNumber[i]][j];
               jobCount++;
            }
         }
      }
      return list;
   
   }

	// Data: Takes in a empty string(longerString-determined in the recursive
	// method),s1 and s2 are the strings used to compare
	// i is the amount of length deducted, and is intially taken in as an integer at
	// value of 0.
	// Purpose: Checks if the smaller string is a part of the larger string.
   private static boolean equalSectionIgnoreCase(String longerString, String s1, String s2, int i) {
      String longer;
      String shorter;
      if (s1.length() == 0 || s2.length() == 0 || s1 == null || s2 == null) {
         return false;
      }
   
      if (s1.length() >= s2.length()) {
         longer = s1;
         shorter = s2;
      } else {
         longer = s2;
         shorter = s1;
      }
   
   	// First run only, make the longestString the longer string from prompt
      if (i == 0) {
         longerString = longer;
      }
   
      if ((longer.toLowerCase()).equals(shorter.toLowerCase())) {// lowercase each string
         return true;
      }
   
   	// To prevent when using the substring the index is out of bound 
      //as when the longerone is smaller than the shorter one then there would be index of bound.
      if (i + shorter.length() > longerString.length()) {
         return false;
      }
   
   	// substring the longerstring according to the number of time the longer string
   	// have been substringed(i)
      return equalSectionIgnoreCase(longerString, longerString.substring(i, i + shorter.length()), shorter, i + 1);
   }

	// Data: This method returns a list of jobs that matches the company keyword
	// which was prompted from the user in the method
	// Purpose: The purpose of this method is not to make the user enter too much
	// but not too
	// little information while given a specific range of desirement
	// Therefore this special searching algorithm will also make use of the
	// recursive method (equalSectionIgnoreCase)
   public Job[] searchByTitle() {
      int count = 0;
      Scanner sc = new Scanner(System.in);
      System.out.println("Please enter the job title: ");
      String title = sc.nextLine();
      for (int i = 0; i < numAccounts; i++) {
         for (int j = 0; j < jobs[i].length; j++) {
            if (jobs[i][j] != null && equalSectionIgnoreCase("", title, jobs[i][j].getTitle(), 0)) {
            	// use the boolean returned to determine the size of the array that keep track
            	// of the employer account matched
               count++;
            }
         }
      }
      if (count ==0){
         System.out.println("Sorry, could not find any job that match your keyword.");
         return null;
      }
      Job[] list = new Job[count];
      count = 0;
      for (int i = 0; i < numAccounts; i++) {
         for (int j = 0; j < jobs[i].length; j++) {
            if (jobs[i][j] != null && equalSectionIgnoreCase("", title, jobs[i][j].getTitle(), 0)) {
            	// add the matched job to the final returned array
               list[count] = jobs[i][j];
               count++;
            }
         }
      }
      return list;
   }

	/*
	 * Data: Takes in no parameters and returns a Job array
	 * Purpose: This method searches the database and returns a list of jobs that
	 * match the full time employee's specified demands
	 */
   public Job[] searchFullTimeDemand() {
      Scanner sc = new Scanner(System.in);
      String input;
      int location;
      int industry;
      int companySize = 0;
      double monthlyEarning = 0;
      char benefit;
      char sickDays;
      char breaks;
      boolean checker = false;
      Job[] match = new Job[10000]; // Array for matched objects
      int matched = 0; // Used for the new matched array
      int count = 0;
   
   	// Prompts and allows the user to enter their demands
      System.out.println("Enter Demands:");
      System.out.println("Location: \n(1) A1\n(2) A2\n(3) B1\n(4) B2\n(5) C1\n(6) C2");
      input = sc.nextLine();
   	// Verifies the input and continues prompting if it is invalid
      while (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4")
      		&& !input.equals("5") && !input.equals("6")) {
         System.out.println("ERROR: Invalid Input");
         System.out.println("Location: \n(1) A1\n(2) A2\n(3) B1\n(4) B2\n(5) C1\n(6) C2");
         input = sc.nextLine();
      }
      location = Integer.parseInt(input);
   
      System.out.println(
         	"Industry: \n(1) Law\n(2) Finance\n(3) Engineering\n(4) Medical\n(5) Service\n(6) Government\n(7) Other");
      input = sc.nextLine();
   	// Verifies the input and continues prompting if it is invalid
      while (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4")
      		&& !input.equals("5") && !input.equals("6")) {
         System.out.println("ERROR: Invalid Input");
         System.out.println(
            	"Industry: \n(1) Law\n(2) Finance\n(3) Engineering\n(4) Medical\n(5) Service\n(6) Government\n(7) Other");
         input = sc.nextLine();
      }
      industry = Integer.parseInt(input);
   
   	// Verifies the input and continues prompting if it is invalid
      while (!checker) {
         System.out.print("Minimum Company Size(1. <50, 2. 50-500, 3. 500-1000, 4. 1000-5000, 5. >5000): ");
         input = sc.nextLine();
         try {
            companySize = Integer.parseInt(input);
            if (companySize >= 1 && companySize <= 5){
               checker = true;
            }
         } catch (NumberFormatException nfx) {
            System.out.println("ERROR: Invalid Input");
         }
      }
      checker = false;
   
   	// Verifies the input and continues prompting if it is invalid
      while (!checker) {
         System.out.print("Minimum Monthly Earning: $");
         input = sc.nextLine();
         try {
            monthlyEarning = Double.parseDouble(input);
            checker = true;
         } catch (NumberFormatException nfx) {
            System.out.println("ERROR: Invalid Input");
         }
      }
   
      System.out.print("Benefit (Y/N): ");
      input = sc.nextLine();
   	// Verifies the input and continues prompting if it is invalid
      while (!input.equals("Y") && !input.equals("N")) {
         System.out.println("ERROR: Invalid Input");
         System.out.print("Benefit (Y/N): ");
         input = sc.nextLine();
      }
      benefit = input.charAt(0);
   
      System.out.print("Sick Days (Y/N): ");
      input = sc.nextLine();
   	// Verifies the input and continues prompting if it is invalid
      while (!input.equals("Y") && !input.equals("N")) {
         System.out.println("ERROR: Invalid Input");
         System.out.print("Sick Days (Y/N): ");
         input = sc.nextLine();
      }
      sickDays = input.charAt(0);
   
      System.out.print("Breaks (Y/N): ");
      input = sc.nextLine();
   	// Verifies the input and continues prompting if it is invalid
      while (!input.equals("Y") && !input.equals("N")) {
         System.out.println("ERROR: Invalid Input");
         System.out.print("Breaks (Y/N): ");
         input = sc.nextLine();
      }
      breaks = input.charAt(0);
   
   	// Searches the database for jobs that match the employee's demands
      for (int i = 0; i < jobs.length; i++) {
         for (int j = 0; j < jobs[i].length; j++) {
            if (jobs[i][j] instanceof FullTimeJob) {
            // polymorphism is used here with casting (FullTimeJob)
               // inheritance is used here with casting (FullTimeJob)
               if (((FullTimeJob) jobs[i][j]).getLocation() == location
               		&& ((FullTimeJob) jobs[i][j]).getIndustry() == industry
               		&& ((FullTimeJob) jobs[i][j]).getCompanySize() >= companySize
               		&& ((FullTimeJob) jobs[i][j]).getMonthlyEarning() >= monthlyEarning
               		&& ((FullTimeJob) jobs[i][j]).getBenefit() == benefit
               		&& ((FullTimeJob) jobs[i][j]).getSickDays() == sickDays
               		&& ((FullTimeJob) jobs[i][j]).getBreaks() == breaks) {
                  match[matched] = jobs[i][j];
                  matched++;
               }
            }
         }
      }
      if (matched == 0){
         System.out.println("Sorry, could not find any full time jobs matching your demand.");
         return null;
      }
      count = 0;
      for (int i = 0; i < match.length; i++) {
         if (match[i] != null) {
            count++;
         }
      }
      Job[] list = new Job[count];
      count = 0;
      for (int i = 0; i < match.length; i++) {
         if (match[i] != null) {
            list[count] = match[i];
            count++;
         }
      }
      return list;
   }

	/*
	 *Data: Takes in no parameters and returns a Job array
	 * Purpose: This method searches the database and returns a list of jobs that
	 * match the part time employee's specified demands
	 */
   public Job[] searchPartTimeDemand() {
      Scanner sc = new Scanner(System.in);
      String input;
      int location;
      int industry;
      int companySize = 0;
      double hourlyEarning = 0;
      int minWorkHourPerWeek = 0;
      boolean checker = false;
      Job[] match = new Job[10000]; // Array for matched objects
      int matched = 0; // Used for the new matched array
      int count = 0;
   	
      System.out.println("Enter Demands:");
      System.out.println("Location: \n(1) A1\n(2) A2\n(3) B1\n(4) B2\n(5) C1\n(6) C2");
      input = sc.nextLine();
   	// Verifies the input and continues prompting if it is invalid
      while (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4")
      		&& !input.equals("5") && !input.equals("6")) {
         System.out.println("ERROR: Invalid Input");
         System.out.println("Location: \n(1) A1\n(2) A2\n(3) B1\n(4) B2\n(5) C1\n(6) C2");
         input = sc.nextLine();
      }
      location = Integer.parseInt(input);
   
      System.out.println(
         	"Industry: \n(1) Law\n(2) Finance\n(3) Engineering\n(4) Medical\n(5) Service\n(6) Government\n(7) Other");
      input = sc.nextLine();
   	// Verifies the input and continues prompting if it is invalid
      while (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4")
      		&& !input.equals("5") && !input.equals("6")) {
         System.out.println("ERROR: Invalid Input");
         System.out.println(
            	"Industry: \n(1) Law\n(2) Finance\n(3) Engineering\n(4) Medical\n(5) Service\n(6) Government\n(7) Other");
         input = sc.nextLine();
      }
      industry = Integer.parseInt(input);
   
   	// Verifies the input and continues prompting if it is invalid
      while (!checker) {
         System.out.print("Minimum Company Size(1. <50, 2. 50-500, 3. 500-1000, 4. 1000-5000, 5. >5000): ");
         input = sc.nextLine();
         try {
            companySize = Integer.parseInt(input);
            if (companySize >= 1 && companySize <= 5){
               checker = true;
            }
         } catch (NumberFormatException nfx) {
            System.out.println("ERROR: Invalid Input");
         }
      }
      checker = false;
   
   	// Verifies the input and continues prompting if it is invalid
      while (!checker) {
         System.out.print("Minimum Hourly Earning: $");
         input = sc.nextLine();
         try {
            hourlyEarning = Double.parseDouble(input);
            checker = true;
         } catch (NumberFormatException nfx) {
            System.out.println("ERROR: Invalid Input");
         }
      }
      checker = false;
   
   	// Verifies the input and continues prompting if it is invalid
      while (!checker) {
         System.out.print("Minimum Work Per Week (h): ");
         input = sc.nextLine();
         try {
            minWorkHourPerWeek = Integer.parseInt(input);
            checker = true;
         } catch (NumberFormatException nfx) {
            System.out.println("ERROR: Invalid Input");
         }
      }
   
   	// Searches the database for jobs that match the employee's demands
      for (int i = 0; i < jobs.length; i++) {
         for (int j = 0; j < jobs[i].length; j++) {
            if (jobs[i][j] instanceof PartTimeJob) {
               // polymorphism is used here with casting (PartTimeJob)
               // inheritance is used here with casting (PartTimeJob)
               if (((PartTimeJob) jobs[i][j]).getLocation() == location
               		&& ((PartTimeJob) jobs[i][j]).getIndustry() == industry
               		&& ((PartTimeJob) jobs[i][j]).getCompanySize() >= companySize
               		&& ((PartTimeJob) jobs[i][j]).getHourlyEarning() >= hourlyEarning
               		&& ((PartTimeJob) jobs[i][j]).getMinWorkHourPerWeek() >= minWorkHourPerWeek) {
                  match[matched] = jobs[i][j];
                  matched++;
               }
            }
         }
      }
      if (matched == 0){
         System.out.println("Sorry, could not find any part time jobs matching your demand.");
         return null;
      }
      count = 0;
      for (int i = 0; i < match.length; i++) {
         if (match[i] != null) {
            count++;
         }
      }
      Job[] list = new Job[count];
      count = 0;
      for (int i = 0; i < match.length; i++) {
         if (match[i] != null) {
            list[count] = match[i];
            count++;
         }
      }
      return list;
   }

	// display job - takes in a 2D array and prints it to the screen
   public void displayJob(Job[][] joblist) {
      for (int i = 0; i < joblist.length; i++) {
         for (int j = 0; j < joblist[i].length; j++) {
            System.out.println(joblist[i][j]);
         }
      }
   }

	// display job - takes in a 1D array and prints it to the screen
   public void displayJob(Job[] joblist) {
      if (joblist != null){
         for (int i = 0; i < joblist.length; i++) {
            if (joblist[i] != null) {
               System.out.print("Job #: " + i);
               System.out.println(joblist[i]);
               System.out.println(employerAccount[matchJob(joblist[i])]);
               System.out.println("");
            }
         }
      }
   }

	// This method returns a Job array that is not null
   public Job[] getExistingJob(Job[] list) {
      int count = 0;
      for (int i = 0; i < list.length; i++) {
         if (list[i] != null) {
            count++;
         }
      }
      Job[] current = new Job[count];
      count = 0;
      for (int i = 0; i < list.length; i++) {
         if (list[i] != null) {
            current[count] = list[i];
            count++;
         }
      }
      return current;
   }

	// This method returns a 1D array that contains all full time jobs
   public Job[] allFullTime() {
      Job[][] allfulltime = new Job[numAccounts][];
      int totaljob = 0;
      int count = 0;
   
   	// Searches the job listings for all full time jobs and adds them to the return
   	// array
      for (int i = 0; i < numAccounts; i++) {
         allfulltime[i] = new Job[employerAccount[i].getNumFulltime()];
         for (int j = 0, k = 0; j < employerAccount[i].getNumPosted()
         		&& k < employerAccount[i].getNumFulltime(); j++) {
            if (jobs[i][j] != null && jobs[i][j] instanceof FullTimeJob) {
               allfulltime[i][k] = jobs[i][j];
               k++;
               totaljob++;
            }
         }
      }
      Job[] allfulltime1 = new Job[totaljob];
      for (int i = 0; i < allfulltime.length; i++) {
         for (int j = 0; j < allfulltime[i].length; j++) {
            allfulltime1[count] = allfulltime[i][j];
            count++;
         }
      }
      return allfulltime1;
   }

	// This method returns a 1D version of the 2D Job array
   public Job[] get1dJob() {
      int max = 100;
      int count = 0;
      int notNull = 0;
      Job[] list = new Job[10000];
      for (int i = 0; i < jobs.length; i++) {
         for (int j = 0; j < jobs[i].length; j++) {
            list[count] = jobs[i][j];
            if (list[count]!= null){
               notNull++;
            }
            count++;
         }
      }
      Job[] finalList = new Job[notNull];
      notNull = 0;
      for (int i = 0; i < list.length; i++) {
         if (list[i]!= null){
            finalList[notNull]= list[i];
            notNull++;
         }
      }
      return finalList;
   }

	// This method returns a 1D array that contains all part time jobs
   public Job[] allPartTime() {
      Job[][] allparttime = new Job[numAccounts][];
      int totaljob = 0;
      int count = 0;
   
   	// Searches the job listings for all part time jobs and adds them to the return
   	// array
      for (int i = 0; i < numAccounts; i++) {
         allparttime[i] = new Job[employerAccount[i].getNumParttime()];
         for (int j = 0, k = 0; j < employerAccount[i].getNumPosted()
         		&& k < employerAccount[i].getNumParttime(); j++) {
            if (jobs[i][j] != null && jobs[i][j] instanceof PartTimeJob) {
               allparttime[i][k] = jobs[i][j];
               k++;
               totaljob++;
            }
         }
      }
      Job[] allparttime1 = new Job[totaljob];
      for (int i = 0; i < allparttime.length; i++) {
         for (int j = 0; j < allparttime[i].length; j++) {
            allparttime1[count] = allparttime[i][j];
            count++;
         }
      }
      return allparttime1;
   }

	// This method prints the employee user's personal information
   public void printResume() {
      System.out.println(userEmployeeInfo);
   }

	// This method is used to check the log in credentials that the returning
	// employer enters
   public boolean login(String username, String password) {
      boolean found = false;
   
      for (int i = 0; i < employerAccount.length && !found; i++) {
         if (employerAccount[i]!= null && employerAccount[i].matchLogin(username, password)) {
            currentUser = i;
            System.out.println("Login successfully! Welcome"); // Successfully loged in
            found = true;
         }
      }
      if (!found) {
         System.out.println("Wrong username or password"); // Failed to log in
      }
      return found;
   }

	// This method returns the index of the employer who posted the job
   public int matchJob(Job job) {
      int index = -1;
      boolean found = false;
   
   	// Searches through the 2D array and finds the index of the employer
      for (int i = 0; i < jobs.length && !found; i++) {
         for (int j = 0; j < jobs[i].length && !found; j++) {
            if (jobs[i][j] == job) {
               index = i;
               found = true;
            }
         }
      }
      return index;
   }

	// Allows the user to select a job from the listings
   public void selectJob(int index, Job[] list) {
      selectedJobs[jobcount] = list[index];
      jobcount++;
   }
}
