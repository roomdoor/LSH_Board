package com.lsh.board.exception;

import com.lsh.board.exception.type.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(LSH_BoardException.class)
	public ResponseEntity<?> handlerPlaceException(LSH_BoardException e) {
		return ResponseEntity.badRequest()
			.body(ErrorResponse.builder()
				.errorCode(e.getErrorCode())
				.errorMessage(e.getErrorMessage())
				.build());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationException(
		MethodArgumentNotValidException e) {
		ErrorResponse errorResponse = new ErrorResponse(ErrorCode.BAD_REQUEST,
			e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> handleHttpMessageNotReadableException(
		HttpMessageNotReadableException e) {
		ErrorResponse errorResponse = new ErrorResponse(ErrorCode.BAD_REQUEST,
			"Request Body가 비어 있습니다");
		return ResponseEntity.badRequest().body(errorResponse);
	}
}
