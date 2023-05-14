package com.lsh.board.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KomoranUtils {

	private final Komoran komoran;

	public KomoranUtils() {
		komoran = new Komoran(DEFAULT_MODEL.FULL);
	}

	public Map<String, Integer> getWord(String text) {
		List<String> words = komoran.analyze(text).getNouns();
		Map<String, Integer> map = new HashMap<>();
		for (String word : words) {
			Integer count = map.getOrDefault(word, 0);
			map.put(word, count + 1);
		}

		return map;
	}

}
