package Errors;

public class SQLConnectionException extends Exception {

	public SQLConnectionException(String connectionString) {
		super("Connection String must be invalid : " + connectionString);
	}
}
