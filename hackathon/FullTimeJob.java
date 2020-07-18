/*
Class Name: FullTimeJob.java
Author:Gary Zhong, Luke Shi, Justin Shi, and Bobby Tao
Date: 07/18/20
Purpose: This class file keeps track of the partial information(benefit, sick days,monthly earnings and breaks)
 of the job, if the type of job is a full time

*/
public class FullTimeJob extends Job {
	private double monthlyEarning;
	private char benefit;
	private char sickDays;
	private char breaks;
	
	//accessor and mutator
	public double getMonthlyEarning() {
		return monthlyEarning;
	}
   
	public void setMonthlyEarning(double monthlyEarning) {
		this.monthlyEarning = monthlyEarning;
	}
   
	public char getBenefit() {
		return benefit;
	}
   
	public void setBenefit(char benefit) {
		this.benefit = benefit;
	}
   
	public char getSickDays() {
		return sickDays;
	}
   
	public void setSickDays(char sickDays) {
		this.sickDays = sickDays;
	}
   
	public char getBreaks() {
		return breaks;
	}
   
	public void setBreaks(char breaks) {
		this.breaks = breaks;
	}
	
	//constructor
   // returns a FullTimeJob object with the global parameters filled with the parameters
	public FullTimeJob(int id,String title,int location, int industry, int companySize, int eduBackground, int workExp, char gender, double monthlyEarning, char benefit, char sickDays, char breaks) {
		super(id,title,location, industry, companySize, eduBackground, workExp, gender);
		this.monthlyEarning = monthlyEarning;
		this.benefit = benefit;
		this.sickDays = sickDays;
		this.breaks = breaks;
	}
   
	// equals method
   // check if if the FullTimeJob object is equal to the FullTimeJob object
   public boolean equals(FullTimeJob other){
      if (other!=null && super.equals((Job)other) && monthlyEarning == other.monthlyEarning && benefit == other.benefit && sickDays == other.sickDays && breaks == other.breaks){
           return true;
      } else {
         return false;
      }
   }
   
   // toString method
   // converts the information for the required global variables into a string and returns that string
	public String toString() {
      String s = super.toString();
		s += "\nMonthly Earning: $" + monthlyEarning;
		s += "\nBenefit: " + benefit;
		s += "\nSick Days: " + sickDays;
		s += "\nBreaks: " + breaks;
		
		return s;
	}
}
