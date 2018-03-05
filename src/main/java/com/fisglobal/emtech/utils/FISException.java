package com.fisglobal.emtech.utils;

import lombok.Data;

@Data
public class FISException extends Exception {

	private static final long serialVersionUID = -7330274467672471699L;
	String message;
	
	public FISException() {
		super();
	}
	
	public FISException(final String message) {
		super(message);
	}
	
	public FISException(final Throwable cause) {
		super(cause);
	}
	
	public FISException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
}
