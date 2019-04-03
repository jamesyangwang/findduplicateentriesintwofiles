package com.digicert.dup.file;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileUtil {

	public Set<String> buildSetFromFile(String fileName) throws IOException {

		Set<String> res = new HashSet<>();
		
//		try (RandomAccessFile file = new RandomAccessFile(fileName, "r")) {
//			for (String line; (line = file.readLine()) != null;) res.add(line);
//		}
		
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
	        stream.forEach(line -> res.add(line));
		}

		log.info("{} records loaded from {}", res.size(), fileName);
		return res;
	}

	public void writeSetToFile(Set<String> set, String fileName) throws IOException {

		log.info("{} duplicate entries found", set.size());

		try (RandomAccessFile file = new RandomAccessFile(fileName, "rw")) {
			for (String str : set) {
				log.info(str);
				file.writeChars(str + "\n");
			}
		}

		log.info("Saved duplicate entries into {}", fileName);
	}
}
