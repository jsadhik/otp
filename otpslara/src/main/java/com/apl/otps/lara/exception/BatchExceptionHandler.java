package com.apl.otps.lara.exception;

import java.io.Serializable;

public class BatchExceptionHandler extends Exception implements Serializable{

	public BatchExceptionHandler(String message) {
		super(message);
	}

	public BatchExceptionHandler(String message, Throwable cause) {
		super(message, cause);
	}

}
