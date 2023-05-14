package com.example.lsh_board;

import com.example.lsh_board.domain.posts.dto.PostDto.Request;
import com.example.lsh_board.domain.posts.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LshBoardApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private PostService postService;

	@DisplayName("01_00. db_in")
	@Test
	public void test_01_00() {
		//given

		postService.create(Request.builder()
			.title("1")
			.text("아침 오늘 내일 모래 사흘 사흘 사흘 사흘")
			.build());

		postService.create(Request.builder()
			.title("2")
			.text("아침 오늘 내일 내일 내일 내일 내일 사흘 사흘 사흘 사흘 사흘 사흘")
			.build());

		postService.create(Request.builder()
			.title("3")
			.text("아침 모래 사흘 사흘")
			.build());

		postService.create(Request.builder()
			.title("4")
			.text("아침 오늘 오늘 오늘 모래")
			.build());

		postService.create(Request.builder()
			.title("5")
			.text("아침 오늘 사흘")
			.build());

		postService.create(Request.builder()
			.title("6")
			.text("아침 내일 내일 내일 사흘")
			.build());

		postService.create(Request.builder()
			.title("7")
			.text("오늘 오늘 오늘 오늘 오늘 오늘 오늘 내일")
			.build());

		postService.create(Request.builder()
			.title("8")
			.text("내일 모래")
			.build());

		postService.create(Request.builder()
			.title("9")
			.text("모래")
			.build());

		postService.create(Request.builder()
			.title("10")
			.text("a")
			.build());
		//when
		//then

	}

	@DisplayName("02_00. ")
	@Test
	public void test_02_00() {
		//given

//		List<ResponseList> list = postService.getList();
//
//		for (ResponseList r : list) {
//			System.out.println(r);
//		}

		System.out.println(postService.getPost(1L));
		//when
		//then
	}
}
