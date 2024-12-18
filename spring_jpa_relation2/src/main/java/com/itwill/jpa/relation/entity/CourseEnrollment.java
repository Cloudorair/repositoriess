package com.itwill.jpa.relation.entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "COURSE_ENROLLMENT")
@Table(name = "COURSE_ENROLLMENT",uniqueConstraints = {
		@UniqueConstraint(name="COURSE_ENROLLMENT_UQ",columnNames = {"COURSE_ID","STUD_ID"})
})
public class CourseEnrollment  {
	@Id 
	@SequenceGenerator(name = "COURSE_ENROLLMENT_ID_SEQ", sequenceName = "COURSE_ENROLLMENT_ID_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COURSE_ENROLLMENT_ID_SEQ")
    private Long courseEnrollmentId;
	
	@ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	@JoinColumn(name = "stud_id")
	private Student student;
	
	@ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id")
	private Course course;
	
}





