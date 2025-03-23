package com.shong.shongram.post.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shong.shongram.common.FileManager;
import com.shong.shongram.post.domain.Post;
import com.shong.shongram.post.repository.PostRepository;

import jakarta.persistence.PersistenceException;

@Service
public class PostService {
	
	private final PostRepository postRepository;
	
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	public boolean addPost(int userId, String title, String contents, MultipartFile file){
		
		String urlPath = FileManager.saveFile(userId, file);
		
		Post post =  Post.builder()
				.userId(userId)
				.title(title)
				.contents(contents)
				.imagePath(urlPath)
				.build();
		
		try {
			postRepository.save(post);
		}catch(PersistenceException e) {
			return false;
		}
		
		return true;
		
	}
	
	
	
	public List<Post> getPostList(int userId){
		
		return postRepository.findByUserIdOrderByCreatedAtDesc(userId);
		
	}
	
	public Post getPost(int id) {
		
		Optional<Post> optionalPost =  postRepository.findById(id); 
		
		return optionalPost.orElse(null);
		 
		 
	}

}
