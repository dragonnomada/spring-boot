package com.example.th;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MicroblogService {

	ArrayList<Post> posts = new ArrayList<>();
	
	public List<Post> getAllPosts() {
		return posts;
	}
	
	public Post addPost(String content, String authorName) {
		if (authorName.equals("Trump")) {
			return null;
		}
		
		Post post = Post.builder()
				.content(content)
				.authorName(authorName)
				.createAt(new Date())
				.build();
		
		posts.add(post);
		
		return post;
	}
	
}
