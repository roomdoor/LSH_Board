package com.example.lsh_board.domain.posts.controller;

import static com.example.lsh_board.domain.posts.dto.PostDto.Request;

import com.example.lsh_board.domain.posts.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@PostMapping("/post")
	public ResponseEntity<?> createPost(@RequestBody Request request) {
		return ResponseEntity.ok(postService.create(request));
	}

	@GetMapping("/posts")
	public ResponseEntity<?> getList() {
		return ResponseEntity.ok(postService.getList());
	}

	@GetMapping("/post")
	public ResponseEntity<?> getPost(@RequestParam Long postId) {
		return ResponseEntity.ok(postService.getPost(postId));
	}


	@GetMapping("/test")
	public ResponseEntity<?> test(@RequestParam String s) {
		return ResponseEntity.ok(s);
	}

}
