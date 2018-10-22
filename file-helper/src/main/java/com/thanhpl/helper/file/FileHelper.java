package com.thanhpl.helper.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {
	public static List<File> getFiles(String folderPath) {
		List<File> fileList = new ArrayList<File>();
		try {
			File directory = new File(folderPath);
			File[] files = directory.listFiles();
			if (files != null) {
				for (int i = 0; i < files.length; i++) {
					if (files[i].isFile()) {
						fileList.add(files[i]);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileList;
	}

	public static boolean moveFile(String srcFilePath, String desFilePath) {
		try {
			Path srcPath = Paths.get(srcFilePath);
			Path desPath = Paths.get(desFilePath);
			if (!Files.exists(desPath)) {
				Files.createDirectories(desPath.getParent());
			}
			Files.move(srcPath, desPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean deleteFile(String filePath) {
		try {
			File file = new File(filePath);
			return file.delete();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
