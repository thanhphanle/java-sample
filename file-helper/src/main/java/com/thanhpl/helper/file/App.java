package com.thanhpl.helper.file;

import java.io.IOException;
import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        try {
			List<String> files = NioFileHelper.getFiles("D:\\dev\\csv");
			for (int i = 0; i < files.size(); i++) {
				System.out.println(files.get(i));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
