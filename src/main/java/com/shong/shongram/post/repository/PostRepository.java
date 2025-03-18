package com.shong.shongram.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shong.shongram.post.domain.Post;

public interface PostRepository extends JpaRepository<Post,Integer> {

	// WHERE `userId` = #{} ORDER BY `id` DESC;
	public List<Post> findByUserId(int userId);
	public List<Post> findByUserIdOrderByCreatedAtDesc(int userId);
	
	
	
}
