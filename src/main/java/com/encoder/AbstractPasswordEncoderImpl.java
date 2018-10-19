package com.encoder;

import org.apache.commons.lang.StringUtils;

public abstract class AbstractPasswordEncoderImpl implements PasswordEncoder {
	private String encoderType;
	private boolean encodeHashAsBase64 = false;
	private Object salt;
	private boolean strict = false;

	public AbstractPasswordEncoderImpl(String encoderType) {
		this.encoderType = encoderType;
	}

	public String encoderType() {
		return this.encoderType;
	}

	public void setSalt(Object salt) {
		this.salt = salt;
	}

	public void setStrict(boolean strict) {
		this.strict = strict;
	}

	public boolean isEncodeHashAsBase64() {
		return this.encodeHashAsBase64;
	}

	public void setEncodeHashAsBase64(boolean encodeHashAsBase64) {
		this.encodeHashAsBase64 = encodeHashAsBase64;
	}

	protected abstract String encodeInternal(String paramString);

	public boolean isPasswordValid(String encPassword, String rawPassword) {
		if (isDecodable()) {
			return StringUtils.equals(rawPassword, decodePassword(encPassword));
		}
		return StringUtils.equals(encPassword, encodePassword(rawPassword));
	}

	public String encodePassword(String rawPassword) {
		return encodeInternal(mergePasswordAndSalt(rawPassword));
	}

	public String decodePassword(String encPassword) {
		if (isDecodable()) {
			return splitPasswordFromSalt(decodeInternal(encPassword));
		}
		return encPassword;
	}

	protected abstract String decodeInternal(String paramString);

	private String mergePasswordAndSalt(String password) {
		if (password == null) {
			password = "";
		}
		if ((this.strict)
				&& (this.salt != null)
				&& ((this.salt.toString().lastIndexOf("{") != -1) || (this.salt
						.toString().lastIndexOf("}") != -1))) {
			throw new IllegalArgumentException(
					"Cannot use { or } in salt.toString()");
		}
		if ((this.salt == null) || ("".equals(this.salt))) {
			return password;
		}
		return password + "{" + this.salt.toString() + "}";
	}

	private String splitPasswordFromSalt(String password) {
		int startIndex;
		if ((password.endsWith("}"))
				&& ((startIndex = password.lastIndexOf('{')) != -1)) {
			return password.substring(0, startIndex);
		}
		return password;
	}
}
