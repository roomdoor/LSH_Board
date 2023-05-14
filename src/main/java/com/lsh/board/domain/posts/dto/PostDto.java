package com.lsh.board.domain.posts.dto;

import com.lsh.board.domain.posts.domain.Post;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


public class PostDto {

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	@Builder
	@Schema(description = "게시물 생성 Request")
	public static class Request {

		@Schema(description = "제목")
		private String title;

		@Schema(description = "본문")
		private String text;


		public Post toEntity() {
			return Post.builder()
				.title(this.title)
				.text(this.text)
				.createdAt(LocalDate.now())
				.build();
		}

	}

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	@Builder
	@Schema(description = "게시글 Response")
	public static class Response {

		@Schema(description = "ID")
		private Long postId;

		@Schema(description = "제목")
		private String title;

		@Schema(description = "본문")
		private String text;

		@Schema(description = "생성시간")
		private LocalDate createdAt;

		@Schema(description = "연관게시글 List")
		private List<ResponseList> relationPosts;

	}

	@Getter
	@Setter
	@NoArgsConstructor
	@ToString
	@Builder
	@Schema(description = "게시글 List")
	public static class ResponseList {

		@Schema(description = "ID")
		private Long postId;

		@Schema(description = "제목")
		private String title;

		@Schema(description = "생성시간")
		private LocalDate createdAt;

		@QueryProjection
		public ResponseList(Long postId, String title, LocalDate createdAt) {
			this.postId = postId;
			this.title = title;
			this.createdAt = createdAt;
		}

	}

}
