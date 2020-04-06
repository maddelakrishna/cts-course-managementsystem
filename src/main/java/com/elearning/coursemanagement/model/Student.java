package com.elearning.coursemanagement.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@Entity
@Table(name="student")
public class Student {
    @SequenceGenerator(name = "stdGenerator",initialValue = 2020,allocationSize = 1,sequenceName = "STUDENT_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "stdGenerator")
	private int studentId;
    @Column(unique =true)
    @NotEmpty(message = "User Name should not  be empty")
	private String username;
    @Column
	private String firstname;
    @Column
    private String lastname;
    @Column
	private String password;
    @Column(unique = true)
    @Email(message = "Invalid Email")
	private String  email;
    @Column
    @Pattern(regexp = "[6789][0-9]{9}",message = "Invalid Mobile")
	private String mobile;
    @Column
	private  Date dateCreated;
    
    
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "STUDENT_COURSE",joinColumns = {@JoinColumn(name="st_No",referencedColumnName = "studentId")},inverseJoinColumns =

    {@JoinColumn(name="courseid", referencedColumnName ="courseid" )}  )
	private List<Course>  courseList;
	public int getStudentId() {
		return studentId;
	}
	

	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public List<Course> getCourseList() {
		return courseList;
	}
	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}
	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", username=" + username + ", firstname=" + firstname + ", password="
				+ password + ", email=" + email + ", mobile=" + mobile + ", dateCreated=" + dateCreated
				+ ", courseList=" + courseList + "]";
	}
	
	
	
	
	
	
	

}
