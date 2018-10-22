package com.thanhpl.helper.sftp;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

// Try to connect to SFTP remote by public key file
public class SftpPublicKeyApp 
{
    public static void main( String[] args ) {
    	String SFTPHOST = "10.10.10.10";
		int SFTPPORT = 22;
		String SFTPUSER = "sample";
		String publicKey = "key.ppk";
		String passphrase = "passphrase";
		String SFTPWORKINGDIR = "/home/sftp";

		JSch jSch = new JSch();
		Session session = null;
		Channel channel = null;
		ChannelSftp channelSftp = null;
		try {
			jSch.addIdentity(publicKey, passphrase);
			System.out.println("Private Key Added.");
			session = jSch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
			System.out.println("session created.");

			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			channel = session.openChannel("sftp");
			channel.connect();
			System.out.println("shell channel connected....");
			channelSftp = (ChannelSftp) channel;
			channelSftp.cd(SFTPWORKINGDIR);
			System.out.println("Changed the directory...");
			
			
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (SftpException e) {
			e.printStackTrace();
		} finally {
			if (channelSftp != null) {
				channelSftp.disconnect();
				channelSftp.exit();
			}
			if (channel != null)
				channel.disconnect();

			if (session != null)
				session.disconnect();
		}
    }
}
