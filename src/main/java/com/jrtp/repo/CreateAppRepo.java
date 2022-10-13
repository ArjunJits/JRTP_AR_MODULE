package com.jrtp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jrtp.entites.CreateAppEntity;


public interface CreateAppRepo  extends JpaRepository<CreateAppEntity, Integer>{

}
