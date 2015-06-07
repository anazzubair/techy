package com.anazzubair.techy.utility;

import java.nio.charset.StandardCharsets;

import org.springframework.http.MediaType;

public class UnitTestUtility {
	
	public static final MediaType TEXT_HTML_UTF8 = new MediaType(MediaType.TEXT_HTML.getType(), MediaType.TEXT_HTML.getSubtype(), StandardCharsets.UTF_8);
}
