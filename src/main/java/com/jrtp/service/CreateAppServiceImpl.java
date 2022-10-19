package com.jrtp.service;

import java.util.Optional;

import javax.management.InvalidAttributeValueException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.jrtp.bindings.CreateAppBinding;
import com.jrtp.entites.CreateAppEntity;
import com.jrtp.exception.InvalidSsnException;
import com.jrtp.repo.CreateAppRepo;

@Service
public class CreateAppServiceImpl implements CreateAppService {

	private static final Logger log = LoggerFactory.getLogger(CreateAppServiceImpl.class);
	@Autowired
	private CreateAppRepo createAppRepo;

	@Override
	public String createApp(CreateAppBinding cr) {
		String msg = "";
		Long ssn = cr.getSsn();
		String url = "https://ssa-web-api.herokuapp.com/ssn/{ssn}";
             var webClient= WebClient.create();
             String stateCode = webClient.get()
                      .uri(url,ssn)
                      .retrieve()
                      .bodyToMono(String.class)
                      .block();
             
             log.info("State code reterived {}",stateCode);
             
 //     After Spring 5.4 RestTemplate is deprecated.
//		RestTemplate rt = new RestTemplate();
//		ResponseEntity<String> response = rt.getForEntity(url, String.class, ssn);
//		String apimsg = response.getBody();

		// synchronous means blocking ==> REST TEAMPLATE(Synchronous)
		// asynchronous non blocking ==> WebClient ( Synchronous and ASynchronous)

		if ("New Jersey".equals(stateCode)) // response.equals("Valid SSN") don't use
		{
			CreateAppEntity entity = new CreateAppEntity();
			BeanUtils.copyProperties(cr, entity);
			CreateAppEntity res = createAppRepo.save(entity);
			msg = (res.getAppId() > 0) ? "Application inserted success: " + res.getAppId() : "Invalid SSN";
		}else
		{
			throw new InvalidSsnException();
			//msg="Invalid SSN";
		}
		
		return msg;
	}

	@Override
	public String getRecordbyId(Integer id) throws InvalidAttributeValueException {

		Optional<CreateAppEntity> findById = createAppRepo.findById(id);
		if (findById == null || findById.isEmpty())
		{
		  throw new InvalidAttributeValueException("Invalid id Arjun from ServiceImpl");
		}else
		
		return "Record reterived " +findById;
	}

}
