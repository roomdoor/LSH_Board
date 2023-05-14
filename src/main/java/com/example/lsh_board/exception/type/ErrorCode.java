package com.example.lsh_board.exception.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	BAD_REQUEST("입력 값을 확인해 주세요!"),
	NOT_FOUND_POST("존재하지 않는 글입니다."),

	EMPTY("값이 비어있습니다.")

	;
	private final String description;
}
