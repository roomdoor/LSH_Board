package com.lsh.board.exception;

import com.lsh.board.exception.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class LSH_BoardException extends RuntimeException{

	private ErrorCode errorCode;
	private String errorMessage;

	public LSH_BoardException(ErrorCode errorCode) {
		this.errorCode = errorCode;
		this.errorMessage = errorCode.getDescription();
	}

}
