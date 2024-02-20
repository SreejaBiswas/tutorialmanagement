package com.tutorial;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tutorial.entity.Tutorial;
import com.tutorial.repository.TutorialRepository;

@SpringBootApplication
public class TutorialmanagementApplication implements CommandLineRunner {

	@Autowired
	private TutorialRepository tutorialRepository;

	public static void main(String[] args) {
		SpringApplication.run(TutorialmanagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Tutorial> tls = List.of(Tutorial.builder().title("java").description("core java").published(true).build(),
				Tutorial.builder().title("javaee").description("ent java").published(true).build(),
				Tutorial.builder().title("javame").description("mob java").published(false).build(),
				Tutorial.builder().title("python").description("core python").published(true).build(),
				Tutorial.builder().title("flask").description("web application").published(false).build(),
				Tutorial.builder().title("django").description("web application").published(true).build(),
				Tutorial.builder().title("spring boot").description("API development").published(true).build(),
				Tutorial.builder().title("SQL").description("Oracle 21c SQL").published(false).build()

		);

		tutorialRepository.saveAll(tls);
		System.out.println("-----------------------all saved---------------------");

	}

}
