package com.encoder;

public abstract interface PasswordEncoder {
	public abstract String encoderType();

	public abstract boolean isPasswordValid(String paramString1,
			String paramString2);

	public abstract String encodePassword(String paramString);

	public abstract String decodePassword(String paramString);

	public abstract boolean isDecodable();
}
