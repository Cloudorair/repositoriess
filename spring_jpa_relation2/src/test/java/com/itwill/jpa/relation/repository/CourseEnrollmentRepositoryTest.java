package com.itwill.jpa.relation.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.itwill.SpringJpaApplicationTests;
import com.itwill.jpa.relation.entity.Course;
import com.itwill.jpa.relation.entity.CourseEnrollment;
import com.itwill.jpa.relation.entity.Student;

import jakarta.transaction.Transactional;

class CourseEnrollmentRepositoryTest extends SpringJpaApplicationTests{
	@Autowired
	CourseEnrollmentRepository courseEnrollmentRepository;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	CourseRepository courseRepository;
	
	@Test
	@Transactional
	@Rollback(false)
	void saveCourseEnrollmentWithStudentAndCourse() {
		Student student1=studentRepository.findById(1L).get();
		Course course1=courseRepository.findById(3L).get();
		CourseEnrollment courseEnrollment1=CourseEnrollment.builder()
											.student(student1)
											.course(course1)
											.build();
		courseEnrollmentRepository.save(courseEnrollment1);
	}
	

}
