package com.mazdausa.ssc.common.db;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SqlFileReader {
	
	public static String readTextFromFile(String fPath) {
		StringBuilder sb = new StringBuilder();
		try (InputStream inStrm = SqlFileReader.class.getResourceAsStream(fPath)) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(inStrm));
			if (inStrm != null) {
				String str = null;
				while ((str = reader.readLine()) != null) {
					sb.append(str + "\n");
				}
			}
		} catch (Exception e) {
			log.error("ERROR Reading data from text file : " + fPath, e);
		}
		return sb.toString();
	}

}
