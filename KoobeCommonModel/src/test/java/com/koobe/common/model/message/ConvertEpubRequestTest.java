package com.koobe.common.model.message;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koobe.common.model.enums.FileTypeEnum;

public class ConvertEpubRequestTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testToJson() throws JsonProcessingException {
		
		ConvertEpubRequest request = new ConvertEpubRequest("src-bucket", "dest-bucket", FileTypeEnum.PDF, FileTypeEnum.EPUB, "src-path", "dest-path");
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(request));
	}
	
	@Test
	public void testToObject() throws IOException {
		
		ConvertEpubRequest request = new ConvertEpubRequest("src-bucket", "dest-bucket", FileTypeEnum.PDF, FileTypeEnum.EPUB, "src-path", "dest-path");
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(request);
		
		GeneralRequest generalRequest = mapper.readValue(json, GeneralRequest.class);
		
		ConvertEpubRequest request2 = (ConvertEpubRequest) generalRequest;
		System.out.println(request2.getDestFileType());
	}

}
