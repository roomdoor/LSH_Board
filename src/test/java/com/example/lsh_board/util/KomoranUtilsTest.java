package com.example.lsh_board.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KomoranUtilsTest {

	@Autowired
	private KomoranUtils komoranUtils;


	@DisplayName("01_00. ")
	@Test
	public void test_01_00() {
		//given
		String s = "오늘은 아침에 아침밥을 먹었다 어제는 아침에 아침밥을 못 먹었다";

		Map<String, Integer> words = komoranUtils.getWord(s);

		//when
		for (String x : words.keySet()) {
			System.out.println(words.get(x));
		}

		//then
		assertEquals(words.get("오늘"), 1);
		assertEquals(words.get("어제"), 1);
		assertEquals(words.get("아침"), 2);
		assertEquals(words.get("아침밥"), 2);
	}

}