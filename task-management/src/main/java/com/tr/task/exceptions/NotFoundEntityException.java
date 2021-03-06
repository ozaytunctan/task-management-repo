package com.tr.task.exceptions;

public class NotFoundEntityException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String message;
	
	private Object []args;

	
	public NotFoundEntityException(String message,Object ...args) {
		super(message);
		this.message=message;
		this.args=args;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}
	
	
}
