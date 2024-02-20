package com.tutorial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tutorial.entity.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

	// custom finder method

//	List<Tutorial> seeusingpublished(boolean published);
//
//	List<Tutorial> seeusingtitle(String title);

	List<Tutorial> findByPublished(boolean published);

	List<Tutorial> findByTitleContaining(String title);

	// custom query method which uses a typical naming convention
	@Query(value = "select t from Tutorial t where t.title like %?1%")
	List<Tutorial> findTitleLike(String title);
}
