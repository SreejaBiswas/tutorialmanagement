package com.tutorial.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.entity.Tutorial;
import com.tutorial.repository.TutorialRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TutorialController {

	@Autowired
	private TutorialRepository tutorialRepository;

	@GetMapping("/tutorials")
	public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
		try {
			List<Tutorial> all = new ArrayList<>();
			if (title == null)
				tutorialRepository.findAll().forEach(all::add);
			else
				tutorialRepository.findByTitleContaining(title).forEach(all::add);// body auto-generated for custom
																					// finder method
			if (all.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(all, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/tutorials/{id}")
	public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
		Optional<Tutorial> tdata = tutorialRepository.findById(id);

		if (tdata.isPresent()) {
			return new ResponseEntity<>(tdata.get(), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/tutorials")
	public ResponseEntity<Tutorial> createTutorialById(@RequestBody Tutorial tutorial) {
		try {
			Tutorial _tutorial = tutorialRepository
					.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), tutorial.isPublished()));
			return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);// 201
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/tutorials/{id}")
	public ResponseEntity<Tutorial> updateTutorialById(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
		Optional<Tutorial> tdata = tutorialRepository.findById(id);
		if (tdata.isPresent()) {
			Tutorial _t = tdata.get();
			_t.setTitle(tutorial.getTitle());
			_t.setDescription(tutorial.getDescription());
			_t.setPublished(tutorial.isPublished());
			return new ResponseEntity<>(tutorialRepository.save(_t), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/tutorials/{id}")
	public ResponseEntity<HttpStatus> deleteTutorialById(@PathVariable("id") long id) {
		try {
			tutorialRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/tutorials")
	public ResponseEntity<HttpStatus> deleteALLTutorials() {
		try {
			tutorialRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/tutorials/{published}")
	public ResponseEntity<List<Tutorial>> getTutorialsByPublication(boolean published) {

		try {
			List<Tutorial> ts = tutorialRepository.findByPublished(published);
			if (ts.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(ts, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/tutorials2/{title}")
	public List<Tutorial> getTutorialslike(@PathVariable String title) {

		return tutorialRepository.findTitleLike(title);
	}

}
