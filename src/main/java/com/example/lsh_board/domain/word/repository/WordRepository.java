package com.example.lsh_board.domain.word.repository;

import com.example.lsh_board.domain.word.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends JpaRepository<Word, String> {

}
