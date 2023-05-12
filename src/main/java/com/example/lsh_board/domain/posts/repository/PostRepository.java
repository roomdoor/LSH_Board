package com.example.lsh_board.domain.posts.repository;

import com.example.lsh_board.domain.posts.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
