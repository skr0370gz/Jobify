/*
Class Name: Job.java
Author:Gary Zhong, Luke Shi, Justin Shi, and Bobby Tao
Date: 07/18/20
Purpose: This class file keeps track of the basic information
(id, title, location, company size, work experience, gender, and education background) of the job

*/
public class Job {
	protected int id;
	protected String title;
	protected int location;
	protected int industry;
	protected int companySize;
	protected int eduBackground;
	protected int workExp;
	protected char gender;

	// accessor and mutator
	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String s) {
		title = s;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public int getIndustry() {
		return industry;
	}

	public void setIndustry(int industry) {
		this.industry = industry;
	}

	public int getCompanySize() {
		return companySize;
	}

	public void setCompanySize(int companySize) {
		this.companySize = companySize;
	}

	public int getEduBackground() {
		return eduBackground;
	}

	public void setEduBackground(int eduBackground) {
		this.eduBackground = eduBackground;
	}

	public int getWorkExp() {
		return workExp;
	}

	public void setWorkExp(int workExp) {
		this.workExp = workExp;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	// constructor
	public Job(int id, String title, int location, int industry, int companySize, int eduBackground, int workExp,
			char gender) {
		this.id = id;
		this.title = title;
		this.location = location;
		this.industry = industry;
		this.companySize = companySize;
		this.eduBackground = eduBackground;
		this.workExp = workExp;
		this.gender = gender;
	}

	// equals method
	public boolean equals(Job other) {
		if (other.id == id && other.title.equals(title) && other.location == location && other.industry == industry
				&& other.companySize == companySize && other.eduBackground == eduBackground && other.workExp == workExp
				&& other.gender == gender) {
			return true;
		} else {
			return false;
		}
	}

	// Prints the information in the object
	public String toString() {
		String s = "\nID: " + id;
		s += "\nJob Title: " + title;
		s += "\nLocation: " + locationToString(location);
		s += "\nIndustry: " + industryToString(industry);
		s += "\nCompany Size: " + compSizeToString(companySize);
		s += "\nEducation Background: " + edubToString(eduBackground);
		s += "\nWork Experience: " + wexpToString(workExp);
		s += "\nGender: " + gender;

		return s;
	}   
// Returns the actual location
	private String locationToString(int location) {
		String line = "";
		if (location == 1) {
			line = "North America";
		} else if (location == 2) {
			line = "South America";
		} else if (location == 3) {
			line = "Europe";
		} else if (location == 4) {
			line = "Asia";
		} else if (location == 5) {
			line = "Australia";
		} else if (location == 6) {
			line = "Africa";
		}
		return line;
	}
	
	// Returns the actual industry
	private String industryToString(int industry) {
		String line = "";
		if (industry == 1) {
			line = "Law";
		} else if (industry == 2) {
			line = "Finance";
		} else if (industry == 3) {
			line = "Engineering";
		} else if (industry == 4) {
			line = "Medical";
		} else if (industry == 5) {
			line = "Service";
		} else if (industry == 6) {
			line = "Government";
		} else if (industry == 7) {
			line = "Other";
		}
		return line;
	}
   
   private String compSizeToString(int num) {
		String s = "";
		if (num == 1) {
			s = "<50";
		} else if (num == 2) {
			s = "50-500";
		} else if (num == 3) {
			s = "500-1000";
		} else if (num == 4) {
			s = "1000-5000";
		} else if (num == 5) {
			s = ">5000";
		} 
		return s;
	}
   
	// private: edubToString - returns the actual educational background
   private String edubToString(int eduBackground) {
		String edub = "";
		if (eduBackground == 1) {
			edub = "none";
		} else if (eduBackground == 2) {
			edub = "high school";
		} else if (eduBackground == 3) {
			edub = "college";
		} else if (eduBackground == 4) {
			edub = "undergraduate";
		} else if (eduBackground == 5) {
			edub = "master";
		} else if (eduBackground == 6) {
			edub = "phd";
		}
		return edub;
	}

	// private: wexpToString - return the actual working experience
	private String wexpToString(int workExp) {
		String wexp = "";
		if (workExp == 1) {
			wexp = "no experience";
		} else if (workExp == 2) {
			wexp = "studying/graduated";
		} else if (workExp == 3) {
			wexp = "1 year - 3 years";
		} else if (workExp == 4) {
			wexp = "3 years - 5 years";
		} else if (workExp == 5) {
			wexp = "5 years - 10 years";
		} else if (workExp == 6) {
			wexp = "more than 10 years";
		}
		return wexp;
	}

}
