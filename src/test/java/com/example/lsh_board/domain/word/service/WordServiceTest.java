package com.example.lsh_board.domain.word.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.lsh_board.domain.word.domain.Word;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
class WordServiceTest {

	@Autowired
	private WordService wordService;

	@DisplayName("01_00. ")
	@Test
	public void test_01_00() {
		//given
		Map<String, Integer> request = new HashMap<>();

		request.put("a", 2);
		request.put("b", 3);
		request.put("c", 4);
		request.put("d", 5);
		request.put("e", 6);

		//when
		List<Word> words = wordService.create(request);
		System.out.println(words);
		//then
		assertEquals(words.size(), 5);

		for (Word w : words) {
			assertEquals(w.getTotalCount(), 1);
		}
	}

}