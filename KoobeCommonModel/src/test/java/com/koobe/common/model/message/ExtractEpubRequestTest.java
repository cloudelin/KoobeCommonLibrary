package com.koobe.common.model.message;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author cloude
 * @since 2014-1-17
 */
public class ExtractEpubRequestTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testToJson() throws JsonProcessingException {
		ExtractEpubRequest extractEpubRequest = new ExtractEpubRequest(
				"koobe-cloude-test", "koobe-cloude-test", "dickens-oliver-twist.epub", "/unziped_epub");
		String json = extractEpubRequest.toJson();
		assertNotNull(json);
		System.out.println(json);
	}
	
	@Test
	public void testToObject() throws JsonParseException, JsonMappingException, IOException {
		
		String json = "{\"type\":\"ExtractEpubRequest\",\"action\":\"SendExtractEpubRequest\",\"srcBucket\":\"ap-northeast-1\",\"destBucket\":\"koobe-cloude-test\",\"srcPath\":\"dickens-oliver-twist.epub\",\"destPath\":\"/unziped_epub\"}";
		
		ObjectMapper mapper = new ObjectMapper();
		GeneralRequest generalRequest = mapper.readValue(json, GeneralRequest.class);
		
		System.out.println(generalRequest.getClass().getCanonicalName());
		System.out.println(generalRequest.getAction());
		
		assertNotNull(generalRequest.getClass().getCanonicalName());
		assertNotNull(generalRequest.getAction());
		
		ExtractEpubRequest epubRequest = (ExtractEpubRequest) generalRequest;
		
		System.out.println(epubRequest.getSrcBucket());
		System.out.println(epubRequest.getDestBucket());
		System.out.println(epubRequest.getSrcPath());
		System.out.println(epubRequest.getDestPath());
		
		assertNotNull(epubRequest.getSrcBucket());
		assertNotNull(epubRequest.getDestBucket());
		assertNotNull(epubRequest.getSrcPath());
		assertNotNull(epubRequest.getDestPath());
	}
}
