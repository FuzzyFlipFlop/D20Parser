package net.monkeybutts.parse;

public class StringParser {
	private String buffer;

	public StringParser(String buffer) {
		super();
		this.buffer = buffer;
	}
	
	public void readToken(String token) throws Exception {
		int index = buffer.indexOf(" ");
		String tokenTest = buffer.substring(0, index).trim();
		if (!token.equals(tokenTest))
			throw new Exception(String.format("Expected start token '%s', but found '%s'.", token, tokenTest));
		
		buffer = buffer.substring(index).trim();
	}
	
	public String readStringToToken(String token) throws Exception {
		int index = buffer.indexOf(token);
		if (index < 0)
			throw new Exception(String.format("Expected end token '%s', but was not found.", token));
		String result = buffer.substring(0, index).trim();
		buffer = buffer.substring(index).trim();
		
		return trimEnding(result);
	}
	
	public String readStringTokenValue(String token) throws Exception {
		int startIndex = buffer.indexOf(" ");
		String tokenTest = buffer.substring(0, startIndex).trim();
		if (!token.equals(tokenTest))
			throw new Exception(String.format("Expected start token '%s', but found '%s'.", token, tokenTest));
		
		String result;
		int endIndex = buffer.indexOf(" ", startIndex + 1);
		if (endIndex < 0) {
			// Must be end of buffer
			result = buffer.substring(startIndex).trim();
			buffer = "";
		} else {
			result = buffer.substring(startIndex, endIndex).trim();
			buffer = buffer.substring(endIndex).trim();
		}
		
		return trimEnding(result);
	}
	
	public String readStringTokenToToken(String token, String endToken) throws Exception {
		if (!buffer.startsWith(token))
			throw new Exception(String.format("Expected start token '%s', but was not found", token));
		
		int startIndex = token.length();
		int endIndex = buffer.indexOf(endToken, startIndex);
		if (endIndex < 0)
			throw new Exception(String.format("Expected end token '%s', but was not found.", endToken));
		
		String result = buffer.substring(startIndex, endIndex).trim();
		buffer = buffer.substring(endIndex).trim();
		
		return trimEnding(result);
	}

	public String readStringTokenToEnd(String token) throws Exception {
		if (!buffer.startsWith(token))
			throw new Exception(String.format("Expected start token '%s', but was not found", token));

		int startIndex = token.length();
		String result = buffer.substring(startIndex).trim();
		buffer = "";

		return trimEnding(result);
	}

	public String readStringToEnd() {
		String result = buffer;
		buffer = "";

		return trimEnding(result);
	}

	protected String trimEnding(String value) {
		while (value.endsWith(";") || value.endsWith(","))
			value = value.substring(0,  value.length()-1);

		return value;
	}
}
