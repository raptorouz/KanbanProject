package com.telecom.kanban.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecom.kanban.dao.DeveloperRepository;
import com.telecom.kanban.domain.Developer;
import com.telecom.kanban.service.DeveloperService;

@Service
public class DeveloperServiceImpl implements DeveloperService {

	@Autowired
	private DeveloperRepository developerRepository;

	@Override
	public List<Developer> findAllDevelopers() {

		// TODO Auto-generated method stub
		return developerRepository.findAll();
	}

}
