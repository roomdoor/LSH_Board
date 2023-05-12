package com.example.lsh_board.domain.postWord.repository;

import com.example.lsh_board.domain.postWord.domain.PostWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostWordRepository extends JpaRepository<PostWord, Long>,
	PostWordRepositoryCustom {

}
