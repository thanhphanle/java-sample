package com.thanhpl.helper.sftp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.SftpException;

public class SftpHelper {
	public static List<String> getFileNames(ChannelSftp channelSftp, String folderPath) throws SftpException {
		if (channelSftp == null) {
			return null;
		}
		List<String> fileNameList = new ArrayList<String>();
		@SuppressWarnings("rawtypes")
		Vector filelist = channelSftp.ls(folderPath);
		for (int i = 0; i < filelist.size(); i++) {
			LsEntry entry = (LsEntry) filelist.get(i);
			if (!"..".equals(entry.getFilename()) && !".".equals(entry.getFilename())) {
				fileNameList.add(entry.getFilename());
			}
		}
		return fileNameList;
	}

	public static InputStream getInputStream(ChannelSftp channelSftp, String remoteFolderPath, String fileName)
			throws SftpException {
		if (channelSftp == null) {
			return null;
		}
		channelSftp.cd(remoteFolderPath);
		return channelSftp.get(fileName);
	}

	public static void uploadFile(ChannelSftp channelSftp, String localFilePath, String remoteFolderPath,
			String fileName) throws SftpException, IOException {
		if (channelSftp == null) {
			return;
		}
		channelSftp.cd(remoteFolderPath);
		FileInputStream inputStream = new FileInputStream(localFilePath);
		if (inputStream != null) {
			channelSftp.put(localFilePath, fileName);
			inputStream.close();
		}
	}

	public static void moveFile(ChannelSftp channelSftp, String remoteSrcFolder, String remoteDesFolder,
			String fileName) throws SftpException, IOException {
		if (channelSftp == null) {
			return;
		}
		channelSftp.cd(remoteSrcFolder);
		InputStream tempStream = channelSftp.get(fileName);
		if (tempStream != null) {
			// Use separator '/' for SFTP to Linux
			channelSftp.rename(remoteSrcFolder + '/' + fileName, remoteDesFolder + '/' + fileName);
			tempStream.close();
		}
	}
}
