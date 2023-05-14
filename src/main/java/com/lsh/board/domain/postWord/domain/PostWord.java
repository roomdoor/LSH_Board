package com.lsh.board.domain.postWord.domain;

import com.lsh.board.domain.posts.domain.Post;
import com.lsh.board.domain.word.domain.Word;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Entity(name = "post_word")
public class PostWord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_word_id")
	private Long id;

	private Integer count;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	@ToString.Exclude
	private Post post;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "word_id")
	@ToString.Exclude
	private Word word;

}
