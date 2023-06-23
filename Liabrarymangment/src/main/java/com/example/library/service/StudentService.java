//package com.example.library.service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import com.example.library.entitys.Student;
//import com.example.library.repository.StudentRepository;
//
//@Service
//public class StudentService 
//{
//	private static List<Student> list=new ArrayList<>();
//	static
//	{
//		list.add(new Student(101,"Vipul","3rd year","vipul@gmail.com"));
//	}
//	
//	@Autowired
//	private StudentRepository studrepo;
//	public StudentService(StudentRepository studrepo)
//	{
//		super();
//		this.studrepo=studrepo;
//	}
//	public List<Student> getStudents()
//	{
//		return studrepo.findAll();
//	}
//	public Student getById(int id)
//	{
//		//return studrepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));
//
//		Student stu=null;
//		try
//		{
//		stu=list.stream().filter(e-> e.getSid()==id).findFirst().get();
//		return stu;
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			return stu;
//		}
//		
//	}
//	//to add new book to list
//	public Student addstudent(Student s)
//	{
//		list.add(s);
//		return s;
//	}
//	 
//	//deleting book from list
//	public void deleteStudent(int id) 
//	{
//		list=list.stream().filter(stud->stud.getSid()!=id).collect(Collectors.toList());
//		
//	}
//	
//	//updating student
//	public void updateStudent(Student stud,int studid) 
//	{
//		list=list.stream().map(b->
//		{
//			if(b.getSid()==studid)
//			{
//				b.setSname(stud.getSname());
//				b.setStudclass(stud.getStudclass());
//			}
//			return b;
//		}).collect(Collectors.toList());
//				
//	}
//	
//}

package com.example.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.library.entitys.Student;
import com.example.library.exceptions.InsertStudentException;
import com.example.library.exceptions.StudentNotFound;
import com.example.library.repository.StudentRepository;

@Service
public class StudentService {

	private final StudentRepository studrepo;

	@Autowired
	public StudentService(StudentRepository studrepo) {
		this.studrepo = studrepo;
	}

	public  ResponseEntity<List<Student>> getStudents() 
	{
		if(studrepo.findAll().size() <=0) 
		{
			throw new StudentNotFound();
		}
		else
		{
			return ResponseEntity.ok(studrepo.findAll());
		}
		 //studrepo.findAll();
	}

	public ResponseEntity<Student> getById(int id) {
		try {
		return ResponseEntity.ok(studrepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id)));
		//studrepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));
				
		}
		catch (Exception e) {
			throw new StudentNotFound();
		}
	}

	public ResponseEntity<String> addStudent(Student s) 
	{
		try {
		 studrepo.save(s);
		 return ResponseEntity.ok("insert");
		}
		catch(Exception e) 
		{
			throw new InsertStudentException();
		}
	}

	public ResponseEntity<String> deleteStudent(int id)
	{
		try
		{
		studrepo.deleteById(id);
		return ResponseEntity.ok("Delete");
		}
		catch(Exception e) 
		{
			throw new StudentNotFound();
		}
	}

	public ResponseEntity<String> updateStudent(Student stud, int studid) 
	{
		try
		{
		Student existingStudent = studrepo.findById(studid).orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + studid));
		existingStudent.setSname(stud.getSname());
		existingStudent.setStudclass(stud.getStudclass());
		studrepo.save(existingStudent);
		return ResponseEntity.ok("update");
		}
		catch (Exception e) 
		{
			throw new StudentNotFound();
		}
	}
}

