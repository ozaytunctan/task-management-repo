package com.tr.task.handler;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler  {

	@ExceptionHandler(value = { IllegalArgumentException.class, EmptyResultDataAccessException.class })
	@ResponseBody
	public ResponseEntity<String> illegalArgumentExceptionHadler(RuntimeException ex) {
		return ResponseEntity.ok(ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public List<FieldError> handleMethodArgumentNotValidExcepiton(MethodArgumentNotValidException mValidException,
			BindingResult bindingResult) {
		List<FieldError> fieldErrors = mValidException.getFieldErrors();
		return fieldErrors;
	}
}
