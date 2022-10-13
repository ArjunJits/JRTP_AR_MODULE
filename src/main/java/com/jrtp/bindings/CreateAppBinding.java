package com.jrtp.bindings;

import java.time.LocalDate;

import lombok.Data;


@Data
public class CreateAppBinding {
	private String fullName;
	private String emailId;
	private Long mobileNumber;
	private String gender;
	private LocalDate dob;
	private Long ssn;
	
}
