package com.koobe.common.converter.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubWriter;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentHelper;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Files;
import com.koobe.common.converter.KoobeFileType;

/**
 * 
 * @author cloude
 * @since 2014-1-13
 * 
 * @TODO
 * 1. Determine the charset of source file automatically<BR>
 * Ref: https://code.google.com/p/juniversalchardet/
 */
public class TxtConverter extends GeneralConverter {
	
	private final static Logger log = LoggerFactory.getLogger(TxtConverter.class);
	
	private final static String PUBED_CHAPTER = "chapter-001";
	
	{
		srcType = KoobeFileType.TXT;
	}
	
	public TxtConverter(KoobeFileType destType) {
		this.destType = destType;
	}

	@Override
	public boolean convert(File src, File dest) {
		
		if (!src.exists()) {
			log.error("Source file {} not found", src);
			return false;
		}
		
		if (dest.isFile()) {
            log.warn("File {} exists and will be overwrite.", dest);
        }
		
		BufferedReader br = null;
		FileOutputStream fos = null;
		
		Book epuBook = new Book();
		
//		Metadata metadata = new Metadata();
//		List<String> titleList = new ArrayList<String>();
//		titleList.add("koobe");
		
		try {
			
			DocumentFactory documentFactory = new DocumentFactory();
			DocumentType documentType = documentFactory.createDocType("html", "-//W3C//DTD XHTML 1.1//EN", "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd");
			
			Document document = DocumentHelper.createDocument();
			document.setXMLEncoding("UTF-8");
			document.setDocType(documentType);
			
			Namespace namespace = new Namespace("", "http://www.w3.org/1999/xhtml");
			
			Element elementHtml = document.addElement("html", namespace.getURI());
			
			Element elementHead = elementHtml.addElement("head");
			elementHead.addElement("title");
			Element elementBody = elementHtml.addElement("body");
			
			//TODO charset can determine by source file, otherwise will only support utf8
			br = Files.newReader(src, Charset.forName("UTF-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				Element elementP = elementBody.addElement("p");
				elementP.setText(line);
			}
			
			Resource resource = new Resource(document.asXML().toString().getBytes(), PUBED_CHAPTER + ".html");
			
			epuBook.addSection(PUBED_CHAPTER, resource);
			
			EpubWriter epubWriter = new EpubWriter();
			fos = new FileOutputStream(dest);
			epubWriter.write(epuBook, fos);
			
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {}
			}
		}
	}
}
