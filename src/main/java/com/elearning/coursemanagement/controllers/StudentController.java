package com.elearning.coursemanagement.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elearning.coursemanagement.model.Course;
import com.elearning.coursemanagement.model.Student;
import com.elearning.coursemanagement.model.StudentLogin;
import com.elearning.coursemanagement.services.StudentServices;


@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentServices studentServices;
	@GetMapping("/")
	public String studentHome(Model model)
	{
		
		model.addAttribute("student", new StudentLogin());
		return "student";
	}
	
	@PostMapping("/stlogin")
	public String studentLogin(@ModelAttribute("student") StudentLogin stLogin,BindingResult result, Model model,HttpSession session)
	{
		//System.out.println(stLogin);
		if(result.hasErrors())
		{
			return "student";
		}
		else {
		boolean status = studentServices.login(stLogin);
		if(status)
		{
			session.setAttribute("stusername",stLogin.getUsername());
			return "studenthome";
		}
		else
		{
			model.addAttribute("message","Invalid Username OR password");
			return "student";
		}
		
		}
	}
	
	
	@GetMapping("/register")
	public String studentRegisterForm(Model model)
	{
		Student student = new Student();
		model.addAttribute("stdreg", student);
		
		return "studentregistration";
	}

	
	@PostMapping("/stdregistration")
	public String registerStudent(@Valid @ModelAttribute("stdreg") Student student,BindingResult result,Model model)
	{
		if(result.hasErrors())
		{
			return "studentregistration";
		}
		
		else {
			StudentLogin stdlogin = new StudentLogin();
			model.addAttribute("student",stdlogin);
		Date date = new Date();
		student.setDateCreated(date);
		
		int res = studentServices.createStudent(student);
		if(res==0)
		{
			model.addAttribute("message", student.getFirstname().concat(student.getLastname()).toUpperCase()+" You are already registered");
		
		
		}
		else if(res==1)
		{
			model.addAttribute("message", student.getFirstname().concat(student.getLastname()).toUpperCase()+"Congrats your registration is successfull");
		
		
		}
		else
		{
			model.addAttribute("message", "Some thing went wrong");
		
		}
		
		
		return "student";
		}
		

	
	}
	
	@GetMapping("/listcourse")
	
	public String findAllCourses(HttpSession session, Model model)
	{
		SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
		List<Course>  courses = studentServices.getAllCourses();
		
		
		// stream
		
		// list foreach 
		 
		
		
		
		//System.out.println(courses);
		model.addAttribute("courseList", courses);
		return "studenthome";
	}
	
	
	@GetMapping("/applycourse")
	public String applyCourse(@RequestParam("couresid") int id, HttpSession session, Model model)
	{
		System.out.println(id);
		//System.out.println(studentServices.getCourse(id));
	//	List<Course>  list = new ArrayList<Course>();
		
		
		String username = (String) session.getAttribute("stusername");
		
		    
 		Student s = studentServices.getStudent(username);  // 
 		List<Course>  list = s.getCourseList();
 		list.add(studentServices.getCourse(id));
 		  s.setCourseList(list);;
 		 boolean status =  studentServices.updateStudent(s);
 		 
 		 System.out.println(status);
		System.out.println((String) session.getAttribute("stusername"));
		return "studenthome";
	}

	@GetMapping("/mycourses")
	public String getStudentAppiesCourse(HttpSession session, Model model)
	
	{
		String username = (String)session.getAttribute("stusername");
		
	Student  s = studentServices.getStudent(username);
	
	
	model.addAttribute("courseList", s.getCourseList());
	
	return "studenthome";
		
	}
	
	@GetMapping("/logout")
	@ResponseBody
	public String logout(HttpSession session)
	{
		session.invalidate();
		return "<h1>Logged Out Successfully <a href='/student/'>Login Again</a>";
	}

}
