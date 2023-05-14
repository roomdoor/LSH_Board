package com.example.lsh_board.domain.posts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.lsh_board.domain.posts.domain.Post;
import com.example.lsh_board.domain.posts.dto.PostDto.Request;
import com.example.lsh_board.domain.posts.dto.PostDto.Response;
import com.example.lsh_board.domain.posts.dto.PostDto.ResponseList;
import com.example.lsh_board.domain.posts.repository.PostRepository;
import com.example.lsh_board.exception.LSH_BoardException;
import com.example.lsh_board.exception.type.ErrorCode;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class PostServiceTest {


	@Autowired
	private PostService postService;

	@Autowired
	private PostRepository postRepository;


	@DisplayName("01_00. create - relation post not exist ")
	@Test
	public void test_01_00() {
		//given
		Request request = Request.builder()
			.title("1")
			.text("a b b c c c d d d d e e e e e")
			.build();

		//when
		Response response = postService.create(request);

		//then
		assertEquals(response.getTitle(), request.getTitle());
		assertEquals(response.getText(), request.getText());
		assertEquals(response.getRelationPosts().size(), 0);
	}

	@DisplayName("01_01. create - relation post exist")
	@Test
	public void test_01_01() {
		//given
		Request request = Request.builder()
			.title("[글로벌널리지] 백엔드 과제")
			.text("안녕하세요 지원자님.\n"
				+ "\n"
				+ "먼저 저희 백엔드 개발자 채용공고에 지원해 주셔서 감사드립니다.\n"
				+ "지원자분들께 개별적으로 간단한 실무과제를 보내드립니다.\n"
				+ "아래 제출정보와 과제내용등을 확인하신 후 파일로 작성해 보내주시기 바랍니다.")
			.build();

		//when
		postService.create(request);
		Response response = postService.create(request);

		//then
		assertEquals(response.getTitle(), request.getTitle());
		assertEquals(response.getText(), request.getText());
		assertEquals(response.getRelationPosts().size(), 1);
	}

	@DisplayName("01_02. create - empty exception title")
	@Test
	public void test_01_02() {
		//given
		Request request = Request.builder()
			.title("")
			.text("a b b c c c d d d d e e e e e")
			.build();

		//when
		LSH_BoardException exception = assertThrows(LSH_BoardException.class,
			() -> postService.create(request));

		//then
		assertEquals(exception.getErrorCode(), ErrorCode.EMPTY);
	}

	@DisplayName("01_03. create - empty exception text")
	@Test
	public void test_01_03() {
		//given
		Request request = Request.builder()
			.title("1")
			.text("")
			.build();

		//when
		LSH_BoardException exception = assertThrows(LSH_BoardException.class,
			() -> postService.create(request));

		//then
		assertEquals(exception.getErrorCode(), ErrorCode.EMPTY);
	}


	@DisplayName("02_00. get list")
	@Test
	public void test_02_00() {
		//given

		//when
		List<ResponseList> list = postService.getList();
		List<Post> all = postRepository.findAll();

		//then
		assertEquals(list.size(), all.size());

	}

	@DisplayName("03_00. get post")
	@Test
	public void test_03_00() {
		Request request = Request.builder()
			.title("[글로벌널리지] 백엔드 과제")
			.text("안녕하세요 지원자님.\n"
				+ "\n"
				+ "먼저 저희 백엔드 개발자 채용공고에 지원해 주셔서 감사드립니다.\n"
				+ "지원자분들께 개별적으로 간단한 실무과제를 보내드립니다.\n"
				+ "아래 제출정보와 과제내용등을 확인하신 후 파일로 작성해 보내주시기 바랍니다.")
			.build();

		Response response = postService.create(request);

		//when
		Response post = postService.getPost(response.getPostId());

		//then
		assertEquals(post.getPostId(), response.getPostId());
		assertEquals(post.getTitle(), response.getTitle());
		assertEquals(post.getText(), response.getText());
		assertEquals(post.getCreatedAt(), response.getCreatedAt());

	}

	@DisplayName("03_01. get post not found exception")
	@Test
	public void test_03_01() {


		//when

		LSH_BoardException exception = assertThrows(LSH_BoardException.class,
			() -> postService.getPost(Long.MAX_VALUE));

		//then
		assertEquals(exception.getErrorCode(), ErrorCode.NOT_FOUND_POST);
	}
}