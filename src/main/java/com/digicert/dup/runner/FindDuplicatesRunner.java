package com.digicert.dup.runner;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.digicert.dup.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FindDuplicatesRunner implements CommandLineRunner {

	@Autowired
	private FileUtil fileUtil;
	
	@Override
	public void run(String... args) throws Exception {

		log.info("Starting the application...");
		
		Set<String> set1 = fileUtil.buildSetFromFile(args[1]);
		Set<String> set2 = fileUtil.buildSetFromFile(args[2]);
		
		set1.retainAll(set2);
		
		fileUtil.writeSetToFile(set1, "duplicates.txt");
		
		log.info("Exiting application...");
	}
}
