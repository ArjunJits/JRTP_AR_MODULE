package com.jrtp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jrtp.bindings.CreateAppBinding;
import com.jrtp.service.CreateAppService;

@RestController
public class CreateAppController {
	//Controller --> service --> REPO 
	@Autowired
	private CreateAppService createAppService;
	
	@PostMapping("/add")
	public ResponseEntity<String> createAppRecord(@RequestBody CreateAppBinding cr) {

		String createApp = createAppService.createApp(cr);
		return (createApp.equals("Invalid SSN") ? 
				  new ResponseEntity<>(createApp, HttpStatus.BAD_REQUEST)
				: new ResponseEntity<>(createApp, HttpStatus.CREATED));

	}
	
}
