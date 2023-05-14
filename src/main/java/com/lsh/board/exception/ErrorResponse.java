package com.lsh.board.exception;

import com.lsh.board.exception.type.ErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "에러 Response")
public class ErrorResponse {

	@Schema(description = "에러 코드")
	private ErrorCode errorCode;
	@Schema(description = "에러 메세지")
	private String errorMessage;
}
