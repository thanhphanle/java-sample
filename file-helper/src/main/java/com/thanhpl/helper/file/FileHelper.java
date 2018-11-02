package com.thanhpl.helper.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {
	public static List<File> getFiles(String dirPath) {
		List<File> fileList = new ArrayList<File>();
		File directory = new File(dirPath);
		File[] files = directory.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					fileList.add(files[i]);
				}
			}
		}
		return fileList;
	}
	
	public static boolean deleteFile(String filePath) {
		File file = new File(filePath);
		return file.delete();
	}

	public static String saveFile(String filePath, byte[] bytes) throws IOException {		
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			fw = new FileWriter(filePath);
			bw = new BufferedWriter(fw);
			bw.write(new String(bytes, "UTF-8"));
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {
				throw ex;
			}
		}
		return filePath;
	}
	
	public static boolean createDir(String dirPath) {
		return new File(dirPath).mkdirs();
	}
}