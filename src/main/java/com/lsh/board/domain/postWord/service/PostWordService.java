package com.lsh.board.domain.postWord.service;

import com.lsh.board.domain.postWord.domain.PostWord;
import com.lsh.board.domain.postWord.repository.PostWordRepository;
import com.lsh.board.domain.posts.domain.Post;
import com.lsh.board.domain.posts.dto.PostDto.ResponseList;
import com.lsh.board.domain.word.domain.Word;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostWordService {

	private final PostWordRepository postWordRepository;

	public List<PostWord> create(Map<String, Integer> wordMap, List<Word> words, Post post) {
		List<PostWord> postWords = new ArrayList<>();
		for (Word word : words) {
			postWords.add(postWordRepository.save(
					PostWord.builder()
						.post(post)
						.word(word)
						.count(wordMap.get(word.getId()))
						.build()
				)
			);
		}

		return postWords;
	}


	public List<ResponseList> getRelationPost(Post post) {
		return postWordRepository.getRelationPost(post);
	}

}
