package com.lsh.board.domain.word.domain;

import com.lsh.board.domain.postWord.domain.PostWord;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Word {

	@Id
	@Column(name = "word_id")
	private String id;

	private Long totalCount;

	@OneToMany(mappedBy = "word", fetch = FetchType.LAZY)
	@Exclude
	private List<PostWord> postWords;

}
