package com.lsh.board.domain.postWord.repository;

import static com.lsh.board.domain.postWord.domain.QPostWord.postWord;
import static com.lsh.board.domain.posts.domain.QPost.post;
import static com.lsh.board.domain.word.domain.QWord.word;
import static com.querydsl.jpa.JPAExpressions.select;

import com.lsh.board.domain.posts.domain.Post;
import com.lsh.board.domain.posts.dto.PostDto;
import com.lsh.board.domain.posts.dto.QPostDto_ResponseList;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostWordRepositoryCustomImpl implements PostWordRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<PostDto.ResponseList> getRelationPost(Post findPost) {
//		select post_id, sum(pw.count) as relation_score, count(*) c
//		from post_word pw
//
//		where pw.word_id in (
//		select w.word_id
//		from Post p
//		join post_word pw on p.post_id = pw.post_id
//		join word w on pw.word_id = w.word_id
//		where p.post_id = 1
//		and total_count < (select count(*) from post) * 0.6)
//		)
//
//		and pw.post_id != 1
//		group by post_id
//		having c > 1
//		order by relation_score DESC, c DESC
//		;

		return jpaQueryFactory.select(
				new QPostDto_ResponseList(post.id, post.title, post.createdAt))
			.from(postWord)
			.where(postWord.word.id.in(
					select(word.id).from(post)
						.innerJoin(postWord).on(post.id.eq(postWord.post.id))
						.innerJoin(word).on(postWord.word.id.eq(word.id))
						.where(post.id.eq(findPost.getId())
							.and(word.totalCount.lt(select(post.count()).from(post)
								)
							)
						)
				).and(postWord.post.id.ne(findPost.getId()))
			).groupBy(post.id)
			.having(postWord.count().gt(1L))
			.orderBy(postWord.count.sum().desc(), postWord.count().desc()).fetch();

	}
}
