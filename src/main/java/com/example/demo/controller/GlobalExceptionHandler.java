package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// @RestControllerAdviceとはControllerで発生した例外をまとめて処理するクラス
@RestControllerAdvice
public class GlobalExceptionHandler {

	// @ExceptionHandlerとは、@Valid のバリデーションエラーが発生したら、このメソッドで処理する
	// @Valid によるバリデーションエラーが発生すると、Spring Boot「MethodArgumentNotValidException」という
	// 例外を投げる
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
		
		System.out.println("★★★ GlobalExceptionHandlerが呼ばれました ★★★");

        Map<String, String> errors = new HashMap<>();

        // ex.getBindingResult().getFieldErrors() で項目毎のエラーを取得
        // .forEach(error -> { で取得したエラーを1件ずつ処理する
        // for文でexに入っているエラー情報を、errorsマップにputしているイメージ
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return errors;
    }

	
}
