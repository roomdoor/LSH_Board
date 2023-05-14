package com.lsh.board.domain.posts.service;

import static com.lsh.board.domain.posts.dto.PostDto.Request;
import static com.lsh.board.domain.posts.dto.PostDto.Response;

import com.lsh.board.domain.postWord.domain.PostWord;
import com.lsh.board.domain.postWord.service.PostWordService;
import com.lsh.board.domain.posts.domain.Post;
import com.lsh.board.domain.posts.dto.PostDto.ResponseList;
import com.lsh.board.domain.posts.repository.PostRepository;
import com.lsh.board.domain.word.domain.Word;
import com.lsh.board.domain.word.service.WordService;
import com.lsh.board.exception.LSH_BoardException;
import com.lsh.board.exception.type.ErrorCode;
import com.lsh.board.util.KomoranUtils;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

	private final PostRepository postRepository;

	private final WordService wordService;

	private final PostWordService postWordService;

	private final KomoranUtils komoranUtils;

	@Transactional
	public Response create(Request request) {
		if (request.getText().isEmpty() || request.getTitle().isEmpty()) {
			throw new LSH_BoardException(ErrorCode.EMPTY);
		}

		Post post = postRepository.save(request.toEntity());

		// 1. 게시글 내의 단어 추출, count -> komoran 으로 단어 추출, 갯수 파악
		Map<String, Integer> wordMap = komoranUtils.getWord(post.getText());

		// 2. Word 저장 and totalCount++(전체 게시글에서 몇개의 게시글에서 사용 되는 단어인지 표시하는 지표)
		List<Word> words = wordService.create(wordMap);

		// 3. PostWord 저장 (Post와 Word ManyToMany 관계에 중간역할) postWord에 해당 게시글에서 사용된 단어 갯수 저장
		List<PostWord> postWords = postWordService.create(wordMap, words, post);

		// 4. 리턴값 변환
		Response response = post.toDto();
		// 4-1. 연관게시글 검색 and 반환값에 저장
		response.setRelationPosts(postWordService.getRelationPost(post));

		return response;
	}


	public List<ResponseList> getList() {
		return Post.toDto(postRepository.findAll());
	}

	public Response getPost(Long postId) {
		Post post = postRepository.findById(postId)
			.orElseThrow(() -> new LSH_BoardException(ErrorCode.NOT_FOUND_POST));
		Response response = post.toDto();
		response.setRelationPosts(postWordService.getRelationPost(post));

		return response;
	}


}
