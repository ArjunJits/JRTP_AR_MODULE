package com.jrtp.service;

import javax.management.InvalidAttributeValueException;

import com.jrtp.bindings.CreateAppBinding;

public interface CreateAppService {

	public String createApp(CreateAppBinding crb);
	public String getRecordbyId(Integer id) throws InvalidAttributeValueException;
}
