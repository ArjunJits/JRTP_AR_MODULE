package com.jrtp.entites;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="CR_TABLE")
@Data
public class CreateAppEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer appId;
	private String fullName;
	private String emailId;
	private Long mobileNumber;
	private String gender;
	private LocalDate dob;
	private Long ssn;
	
	
	
}
