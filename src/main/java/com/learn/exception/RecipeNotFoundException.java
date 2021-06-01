package com.learn.exception;


public class RecipeNotFoundException extends RuntimeException{

	public RecipeNotFoundException(Long id) {
		super("Unable to Find Recipe with ID :: "+ id);
	}
}
