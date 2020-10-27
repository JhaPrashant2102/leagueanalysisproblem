package com.cg.leagueanalysisproblem;

/**
 * @author Prashant
 *custom exception for league analysis problem
 */
public class IplAnalyserException extends Exception{
	private static final long serialVersionUID = 1L;

	public enum ExceptionType{
		INCORRECT_CLASS_TYPE,INCORRECT_DELIMITER,INCORRECT_HEADER, CENSUS_FILE_PROBLEM, NO_DATA, EMPTY_LIST;
	}
	
	public ExceptionType type;

	public IplAnalyserException(ExceptionType type) {
		super();
		this.type = type;
	}

	public IplAnalyserException(String message,ExceptionType type) {
		super(message);
		this.type = type;
	}
	
}
