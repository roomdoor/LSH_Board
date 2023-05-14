package com.lsh.board.domain.posts.controller;

import static com.lsh.board.domain.posts.dto.PostDto.Request;

import com.lsh.board.domain.posts.dto.PostDto.Response;
import com.lsh.board.domain.posts.dto.PostDto.ResponseList;
import com.lsh.board.domain.posts.service.PostService;
import com.lsh.board.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

	@Operation(summary = "게시글 생성", description = "게시글 생성 API")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "생성된 게시글 반환, 게시글과 연관게시글 함께 반환", content = @Content(schema = @Schema(implementation = Response.class))),
		@ApiResponse(responseCode = "400", description = "생성된 상품 반환, 상품 상태 검수중으로 변경, 수정 불가", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	@PostMapping("/post")
	public ResponseEntity<?> createPost(@RequestBody Request request) {
		return ResponseEntity.ok(postService.create(request));
	}

	@Operation(summary = "모든 게시글 조회", description = "모든 게시글 조회 API")
	@ApiResponse(responseCode = "200", description = "DB에 저장된 모든 게시글 반환", content = @Content(schema = @Schema(implementation = ResponseList.class)))
	@GetMapping("/posts")
	public ResponseEntity<?> getList() {
		return ResponseEntity.ok(postService.getList());
	}

	@Operation(summary = "해당 ID 게시글 조회", description = "해당 ID 게시글 조회 API")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "해당 ID 게시글 조회 게시글 모든 내용과 연관된 게시글 ID List로 반환", content = @Content(schema = @Schema(implementation = Response.class))),
		@ApiResponse(responseCode = "400", description = "해당 ID의 게시물이 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	@GetMapping("/post")
	public ResponseEntity<?> getPost(@RequestParam Long postId) {
		return ResponseEntity.ok(postService.getPost(postId));
	}


	@Operation(summary = "test", description = "hello api example", hidden = true)
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "OK !!"),
		@ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
		@ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
		@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
	})
	@GetMapping("/test")
	public ResponseEntity<?> test(@RequestParam String s) {
		return ResponseEntity.ok(s);
	}

}
