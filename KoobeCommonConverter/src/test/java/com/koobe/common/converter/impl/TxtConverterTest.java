package com.koobe.common.converter.impl;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.koobe.common.converter.KoobeConverter;
import com.koobe.common.converter.KoobeConverterService;
import com.koobe.common.converter.KoobeConverterType;
import com.koobe.common.core.KoobeApplication;

/**
 * 
 * @author cloude
 * @since 2014-1-14
 */
public class TxtConverterTest {
	
	private KoobeApplication application;
    private KoobeConverterService service;
    private KoobeConverter converter;

	@Before
	public void setUp() throws Exception {
		application = KoobeApplication.getInstance();
        service = (KoobeConverterService)application.getService(KoobeConverterService.class);
        converter = service.getConverter(KoobeConverterType.TXT_TO_EPUB);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void status() {
		assertNotNull(application);
        assertNotNull(service);
        assertNotNull(converter);
	}

	@Test
	public void convert() {
		
		String data = "hello\nepub\n<koobe>\n<ebook>";
		
		File srcFile = new File("testfilesrc.txt");
		File destFile = new File("testfiledesc.epub");
		
		if (destFile.exists()) {
			destFile.delete();
		}
		
		FileWriter fWriter = null;
		
		try {
			fWriter = new FileWriter(srcFile);
			fWriter.write(data);
			fWriter.close();
			
			converter.convert(srcFile, destFile);
			assertTrue(destFile.exists());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
