package com.lsh.board.domain.posts.domain;

import static com.lsh.board.domain.posts.dto.PostDto.Response;

import com.lsh.board.domain.postWord.domain.PostWord;
import com.lsh.board.domain.posts.dto.PostDto.ResponseList;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Long id;

	private String title;

	private String text;

	private LocalDate createdAt;

	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
	private List<PostWord> postWord;

	public Response toDto() {
		return Response.builder()
			.postId(this.getId())
			.title(this.getTitle())
			.text(this.text)
			.createdAt(this.createdAt)
			.build();
	}

	public ResponseList toDtoList() {
		return ResponseList.builder()
			.postId(this.getId())
			.title(this.getTitle())
			.build();
	}

	public static List<ResponseList> toDto(List<Post> posts) {
		return posts.stream().map(Post::toDtoList).collect(Collectors.toList());
	}
}
