package com.example.lsh_board.domain.postWord.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.lsh_board.domain.postWord.domain.PostWord;
import com.example.lsh_board.domain.posts.domain.Post;
import com.example.lsh_board.domain.posts.repository.PostRepository;
import com.example.lsh_board.domain.word.domain.Word;
import com.example.lsh_board.domain.word.service.WordService;
import java.time.LocalDate;
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
class PostWordServiceTest {

	@Autowired
	private PostWordService postWordService;

	@Autowired
	private WordService wordService;

	@Autowired
	private PostRepository postRepository;


	@DisplayName("01_00. ")
	@Test
	public void test_01_00() {
		//given
		Post savedPost = postRepository.save(Post.builder()
			.title("")
			.text("a b b c c c d d d d e e e e e")
			.createdAt(LocalDate.now())
			.build()
		);

		Map<String, Integer> request1 = new HashMap<>();

		request1.put("a", 1);
		request1.put("b", 2);
		request1.put("c", 3);
		request1.put("d", 4);
		request1.put("e", 5);

		List<Word> request2 = wordService.create(request1);

		//when
		List<PostWord> postWords = postWordService.create(request1, request2, savedPost);

		//then

		assertEquals(postWords.size(), 5);

		for (int i = 0; i < postWords.size(); i++) {
			assertEquals(postWords.get(i).getCount(), i + 1);
			assertEquals(postWords.get(i).getPost().getId(), savedPost.getId());
			assertEquals(postWords.get(i).getPost().getCreatedAt(), savedPost.getCreatedAt());
		}
	}
}