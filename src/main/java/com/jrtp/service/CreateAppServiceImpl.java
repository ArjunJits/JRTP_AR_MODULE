package com.jrtp.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jrtp.bindings.CreateAppBinding;
import com.jrtp.entites.CreateAppEntity;
import com.jrtp.repo.CreateAppRepo;

@Service
public class CreateAppServiceImpl implements CreateAppService {

	@Autowired
	private CreateAppRepo createAppRepo;

	@Override
	public String createApp(CreateAppBinding cr) {
		String msg = "";
		Long ssn = cr.getSsn();
		String url = "https://ssa-web-api.herokuapp.com/ssn/{ssn}";

		RestTemplate rt = new RestTemplate();
		ResponseEntity<String> response = rt.getForEntity(url, String.class, ssn);
		String apimsg = response.getBody();

		if ("Valid SSN".equals(apimsg)) // response.equals("Valid SSN") don't use
		{
			CreateAppEntity entity = new CreateAppEntity();
			BeanUtils.copyProperties(cr, entity);
			CreateAppEntity res = createAppRepo.save(entity);

			if (res.getAppId() > 0) {
				msg = "record insterd success";
			} else {
				msg = "record insterd failed";
			}
		} else {
			msg = "Invalid SSN";
		}
		return msg;
	}

}
