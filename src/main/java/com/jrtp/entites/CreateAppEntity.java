package com.jrtp.entites;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

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
	private String createBy;
	@Column(name="created_date",updatable = false)
	@CreationTimestamp
	private LocalDate creationDate;
	private String updatedBy;
	@Column(name="updated_date",insertable = false)
	private LocalDate updatedDate;
	
}
