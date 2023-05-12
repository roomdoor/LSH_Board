package com.example.lsh_board.domain.posts.dto;

import com.example.lsh_board.domain.posts.domain.Post;
import com.querydsl.core.annotations.QueryProjection;
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
	public static class Request {

		private String title;

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
	public static class Response {

		private Long postId;

		private String title;

		private String text;

		private LocalDate createdAt;

		private List<ResponseList> relationPosts;

	}

	@Getter
	@Setter
	@NoArgsConstructor
	@ToString
	@Builder
	public static class ResponseList {

		private Long postId;

		private String title;

		private LocalDate createdAt;

		@QueryProjection
		public ResponseList(Long postId, String title, LocalDate createdAt) {
			this.postId = postId;
			this.title = title;
			this.createdAt = createdAt;
		}

	}

}
