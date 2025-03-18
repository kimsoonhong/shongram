package com.shong.shongram.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shong.shongram.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/post")
public class PostRestController {
	
	private PostService postService;
	
	public PostRestController(PostService postService) {
		this.postService = postService;
	}
	
	@PostMapping("/create")
	public Map<String, String> createMemo(
				 @RequestParam String title
				, @RequestParam String contents
				, HttpSession session){
		
		int userId = (Integer)session.getAttribute("userId");
		
		Map<String, String> resultMap = new HashMap<>();
		
		
		if(postService.addPost(userId, title, contents)) {
			resultMap.put("result", "success");
		}else{
			resultMap.put("result", "fail");
		};
		
		return resultMap;
		
	}
	
}
