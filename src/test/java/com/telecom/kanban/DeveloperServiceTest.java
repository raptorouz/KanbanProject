package com.telecom.kanban;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.telecom.kanban.domain.Developer;
import com.telecom.kanban.service.DeveloperService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class DeveloperServiceTest {

	@Autowired
	private DeveloperService developerService;

	@Test
	public void testFindAllDevelopers() {
		log.info("run test find all dev (via service)");

		List<Developer> developersList = developerService.findAllDevelopers();
		int numberOfDevelopersInRepo = developersList.size();

		// We assert that it's >2 because there are already 2 dev in the DB
		Assert.assertTrue(numberOfDevelopersInRepo > 0);
	}
}
