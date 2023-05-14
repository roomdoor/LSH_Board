package com.lsh.board.domain.word.service;

import com.lsh.board.domain.word.domain.Word;
import com.lsh.board.domain.word.repository.WordRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class WordService {

	private final WordRepository wordRepository;

	public List<Word> create(Map<String, Integer> words) {
		List<Word> result = new ArrayList<>();
		for (String s : words.keySet()) {
			Word word = wordRepository.findById(s)
				.orElse(
					Word.builder()
						.id(s)
						.totalCount(0L)
						.build()
				);

			word.setTotalCount(word.getTotalCount() + 1);
			result.add(wordRepository.save(word));
		}

		return result;
	}
}
