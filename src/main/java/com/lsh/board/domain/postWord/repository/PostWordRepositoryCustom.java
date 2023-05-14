package com.lsh.board.domain.postWord.repository;

import com.lsh.board.domain.posts.domain.Post;
import com.lsh.board.domain.posts.dto.PostDto;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface PostWordRepositoryCustom {

	public List<PostDto.ResponseList> getRelationPost(Post post);

}
