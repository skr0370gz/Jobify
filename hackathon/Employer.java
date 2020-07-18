/*
Class Name: Employer.java
Author:Gary Zhong, Luke Shi, Justin Shi, and Bobby Tao
Date: 07/18/20
Purpose: This class file keeps track of the necessary information concerning the employer(name, company name, phone number) and the type of jobs(full-time and part-time) 
and information about the position that the company offers
*/
import java.util.*;
import java.io.*;

public class Employer {
	// accessors and mutators.
	private String firstName;
	private String lastName;
	private String companyName;
	private String phoneNum;
	private Account user;
	private Job[] jobsPosted;
	private int numPosted;;
	private Scanner sc = new Scanner(System.in);

	/*
	 * Constructor method creates a Employer object which stores the parameters
	 *  into the global variables. Return the
	 * newly created Employer object
	 */
	public Employer(String username, String password, String first, String last, String companyName, String phoneNum) {
		user = new Account(username, password);
		firstName = first;
		lastName = last;
		this.companyName = companyName;
		this.phoneNum = phoneNum;
		jobsPosted = new Job[100];
		numPosted = 0;
	}

	// Accessors and Mutators
	public void setFirstName(String first) {
		firstName = first;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String last) {
		lastName = last;
	}

	public String getLastName() {
		return lastName;
	}

	public void setCompanyName(String companyName) {
		companyName = companyName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setPhoneNum(String phoneNum) {
		phoneNum = phoneNum;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setUser(Account user) {
		user = user;
	}

	public Account getUser() {
		return user;
	}

	public Job[] getJobsPosted() {
		return jobsPosted;
	}
   
   //post job method adds the newly created job from database and updates the employer's job list
	public void postJob(Job job, int id) {
		jobsPosted[id] = job;
      numPosted++;
	}

	// This method returns a Job array that is not null
	public Job[] getExistingJob() {
		Job[] current = new Job[numPosted];
		int count = 0;
		for (int i = 0; i < jobsPosted.length; i++) {
			if (jobsPosted[i] != null) {
				current[count] = jobsPosted[i];
				count++;
			}
		}
		return current;
	}

	// Searches and returns the number of full time jobs
	public int getNumFulltime() {
		int count = 0;
		for (int i = 0; i < numPosted; i++) {
			if (jobsPosted[i] instanceof FullTimeJob) {
				count++;
			}
		}
		return count;
	}

	// Searches and returns the number of part time jobs
	public int getNumParttime() {
		int count = 0;
		for (int i = 0; i < numPosted; i++) {
			if (jobsPosted[i] instanceof PartTimeJob) {
				count++;
			}
		}
		return count;
	}

	// Returns the total number of posted jobs
	public int getNumPosted() {
		return numPosted;
	}

	// This method is used to change the employer's account information
	public void changeAccountInfo() {
		System.out.println("Enter the new first name: ");
		firstName = sc.nextLine();
		System.out.println("Enter the new Last name: ");
		lastName = sc.nextLine();
		System.out.println("Enter the new contact information: ");
		phoneNum = sc.nextLine();
		System.out.println("Enter the new company name: ");
		companyName = sc.nextLine();
      System.out.println("Account Change is successful");
	}

	// This method is used to delete a job from the list
	public void deleteJob(int id) {
		for (int i = 0; i < jobsPosted.length; i++) {
			if (jobsPosted[i]!= null && id == jobsPosted[i].getId()) {
				jobsPosted[i] = null;
            numPosted--;
			}
		}
	}

	// This method is used to check if the entered login credentials match an account
	public boolean matchLogin(String username, String password) {
		if (username.equals(user.getUserName()) && password.equals(user.getPassword())) {
			return true;
		} else {
			return false;
		}
	}

	// equals method
   // check if one employer equal the other employer object in the parameter
	public boolean equals(Employer other) {
		if (other != null && (firstName.equals(other.firstName)) && (lastName.equals(other.lastName))
				&& (companyName.equals(other.companyName)) && (phoneNum.equals(other.phoneNum))
				&& (user.equals(other.user)) && (numPosted == other.numPosted)) {
			for (int i = 0; i < jobsPosted.length; i++) {
				if (jobsPosted[i] instanceof FullTimeJob && other.jobsPosted[i] instanceof FullTimeJob) {
					if ((((FullTimeJob) jobsPosted[i]).equals(((FullTimeJob) other.jobsPosted[i])))) {
						return false;
					}
				} else if (jobsPosted[i] instanceof PartTimeJob && other.jobsPosted[i] instanceof FullTimeJob) {
					if ((((PartTimeJob) jobsPosted[i]).equals(((PartTimeJob) other.jobsPosted[i])))) {
						return false;
					}
				} else {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}
   
   // toString method
   // convert the global variable into a string variable and returns that string variable
	public String toString() {
		return "Employer name: " + firstName + " " + lastName + "\nCompany name: " + companyName
				+ "\nContact information: " + phoneNum;
	}
}