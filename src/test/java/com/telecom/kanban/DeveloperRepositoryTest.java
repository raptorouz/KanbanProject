package com.telecom.kanban;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.telecom.kanban.dao.DeveloperRepository;
import com.telecom.kanban.domain.Developer;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class DeveloperRepositoryTest {

	@Autowired
	private DeveloperRepository developerRepository;

	@Test
	public void testFindAll() {
		log.info("run test find all dev");
		Developer developerToAdd1 = new Developer();
		Developer developerToAdd2 = new Developer();
		developerRepository.save(developerToAdd1);
		developerRepository.save(developerToAdd2);

		List<Developer> developersList = developerRepository.findAll();
		int numberOfDevelopersInRepo = developersList.size();

		// We assert that it's >2 because there are already 2 dev in the DB
		Assert.assertTrue(numberOfDevelopersInRepo > 2);
	}
}
