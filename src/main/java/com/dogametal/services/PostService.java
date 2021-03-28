 package com.dogametal.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dogametal.domain.Post;
import com.dogametal.repository.PostRepository;
import com.dogametal.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired //Annotation capable to return with all information from User
	private PostRepository repo;
	

	public Post findById(String id) {		
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado !"));
	}
	
	public List<Post> findByTitle (String text){
		// method used by spring framework
		//return repo.findByTitleContainingIgnoreCase(text);
		return repo.searchTitle(text);
	}
	
	public List<Post> findPerson(String text){
		return repo.person(text);
	}
	
	@Transactional
	public List<Post> fullSearch (String text, Date minDate, Date maxDate){
		//implements date +1 posts searching inside the date
		maxDate = new Date(maxDate.getTime()+ 24 * 60 * 60 * 1000);
		return repo.fullSearch(text, minDate, maxDate);
	}
}
