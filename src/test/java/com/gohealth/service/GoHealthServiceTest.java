package com.gohealth.service;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

public class GoHealthServiceTest {

	GoHealthService service;
	ClassLoader classLoader;
	
	@Before
	public void setup() {
		service = new GoHealthService();
		classLoader = getClass().getClassLoader();
	}
	@Test
	public void testRegularfile() throws Exception {
		File file = new File(classLoader.getResource("sample.txt").getFile());
		Map<String, Integer> result = service.getBigramParsed(file.getAbsolutePath());
		Assert.assertTrue(result.get("helloworld hi") == 2);
		Assert.assertTrue(result.get("hi helloworld") == 1);
	}
	
	@Test
	public void testFileWithOnlyPunctuations() throws Exception {
		File file = new File(classLoader.getResource("allNonAlphabetic.txt").getFile());
		Map<String, Integer> result = service.getBigramParsed(file.getAbsolutePath());
		Assert.assertTrue(result.size() == 0);
	}
	
	@Test(expected = FileNotFoundException.class)
	public void testFileDoesNotExist() throws Exception {
		
		service.getBigramParsed("src/main/resources/doesNotExist.txt");
	}

}
