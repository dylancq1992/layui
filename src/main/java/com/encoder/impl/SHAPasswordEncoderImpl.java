package com.encoder.impl;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import com.encoder.AbstractPasswordEncoderImpl;

public class SHAPasswordEncoderImpl extends AbstractPasswordEncoderImpl {
	public SHAPasswordEncoderImpl() {
		super("sha");
	}

	protected String encodeInternal(String input) {
		if (!isEncodeHashAsBase64()) {
			return DigestUtils.shaHex(input);
		}
		byte[] encoded = Base64.encodeBase64(DigestUtils.sha(input));
		return new String(encoded);
	}

	public boolean isDecodable() {
		return false;
	}

	protected String decodeInternal(String encPassword) {
		throw new UnsupportedOperationException();
	}
}
