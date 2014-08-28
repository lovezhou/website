package com.jessrun.exception;

public enum ResultProcessType {
	FORWARD,
	REDIRECT,
	AUTO_REDIRECT_FOR_WAIT,
	HISTORY_FOR_WAIT,
	WAIT_FOR_CLOSE;
	
	public static final String RESULT_GOTO_URL_KEY = "___ResultGotoURL";
	public static final String RESULT_WAIT_TIME_KEY = "___ResultWaitTime";
	public static final String RESULT_PROCESS_TYPE_KEY = "___ResultProcess";
}
