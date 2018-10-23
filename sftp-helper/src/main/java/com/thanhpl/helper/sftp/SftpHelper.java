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

	/*
	 * Return remote path of uploaded file
	 */
	public static String uploadFile(ChannelSftp channelSftp, String localFilePath, String remoteFolderPath,
			String fileName) throws SftpException, IOException {
		if (channelSftp == null) {
			return null;
		}
		channelSftp.cd(remoteFolderPath);
		FileInputStream inputStream = new FileInputStream(localFilePath);
		if (inputStream != null) {
			channelSftp.put(localFilePath, fileName);
			inputStream.close();
		}
		return remoteFolderPath + '/' + fileName;
	}

	/*
	 * Return remote path of new file
	 */
	public static String moveFile(ChannelSftp channelSftp, String remoteSrcFolder, String remoteDesFolder,
			String fileName) throws SftpException, IOException {
		if (channelSftp == null) {
			return null;
		}
		channelSftp.cd(remoteSrcFolder);
		// Use separator '/' for SFTP to Linux
		String remoteSrcPath = remoteSrcFolder + '/' + fileName;
		String remoteDesPath = remoteDesFolder + '/' + fileName;
		InputStream tempStream = channelSftp.get(fileName);
		if (tempStream != null) {
			channelSftp.rename(remoteSrcPath, remoteDesPath);
			tempStream.close();
			return remoteDesPath;
		}
		return remoteSrcPath;
	}

	/*
	 * Move file and rename Return remote path of new file
	 */
	public static String moveFile(ChannelSftp channelSftp, String remoteSrcFolder, String remoteDesFolder,
			String srcFileName, String desFileName) throws SftpException, IOException {
		if (channelSftp == null) {
			return null;
		}
		channelSftp.cd(remoteSrcFolder);
		// Use separator '/' for SFTP to Linux
		String remoteSrcPath = remoteSrcFolder + '/' + srcFileName;
		String remoteDesPath = remoteDesFolder + '/' + desFileName;
		InputStream tempStream = channelSftp.get(srcFileName);
		if (tempStream != null) {
			channelSftp.rename(remoteSrcPath, remoteDesPath);
			tempStream.close();
			return remoteDesPath;
		}
		return remoteSrcPath;
	}

	/*
	 * Create new folder, just use mkdir and handle error if existed. Return folder
	 * path
	 */
	public static String createFolder(ChannelSftp channelSftp, String folderPath) {
		if (channelSftp == null) {
			return null;
		}
		try {
			channelSftp.mkdir(folderPath);
		} catch (Exception e) {
			// Ignore
		}
		return folderPath;
	}
}
