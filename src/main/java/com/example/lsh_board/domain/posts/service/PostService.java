package com.example.lsh_board.domain.posts.service;

import static com.example.lsh_board.domain.posts.dto.PostDto.Request;
import static com.example.lsh_board.domain.posts.dto.PostDto.Response;

import com.example.lsh_board.domain.postWord.domain.PostWord;
import com.example.lsh_board.domain.postWord.service.PostWordService;
import com.example.lsh_board.domain.posts.domain.Post;
import com.example.lsh_board.domain.posts.dto.PostDto.ResponseList;
import com.example.lsh_board.domain.posts.repository.PostRepository;
import com.example.lsh_board.domain.word.domain.Word;
import com.example.lsh_board.domain.word.service.WordService;
import com.example.lsh_board.util.KomoranUtils;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

	private final PostRepository postRepository;

	private final WordService wordService;

	private final PostWordService postWordService;

	private final KomoranUtils komoranUtils;

	public Response create(Request request) {

		Post post = postRepository.save(request.toEntity());

		// 1. 총 단어 갯수 파악 -> komoran 으로 단어 추출, 갯수 파악
		Map<String, Integer> wordMap = komoranUtils.getWord(post.getText());

		// 2. 중복 단어 제거 and word 저장 and totalCount++(전체 게시글에서 몇개의 게시글에서 단어가 사용되는지 표시하는 지표);
		List<Word> words = wordService.create(wordMap);

		// 3. postWord 저장
		List<PostWord> postWords = postWordService.create(wordMap, words, post);
		Response response = post.toDto();
		response.setRelationPosts(postWordService.getRelationPost(post));

		return response;
	}

	public List<ResponseList> getList() {
		return Post.toDto(postRepository.findAll());
	}

	public Response getPost(Long postId) {
		Post post = postRepository.findById(postId).orElseThrow(null);
		Response response = post.toDto();
		response.setRelationPosts(postWordService.getRelationPost(post));

		return response;
	}


}
