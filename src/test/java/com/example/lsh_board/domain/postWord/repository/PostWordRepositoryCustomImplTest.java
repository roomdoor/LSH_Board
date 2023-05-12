package com.example.lsh_board.domain.postWord.repository;

import com.example.lsh_board.util.config.QueryDslConfig;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(QueryDslConfig.class)
class PostWordRepositoryCustomImplTest {


}