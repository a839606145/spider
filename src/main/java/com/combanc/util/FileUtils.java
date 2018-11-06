package com.combanc.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class FileUtils {
	
	/**
	 * 根据文件名得到数据流
	 * @param filename
	 * @return
	 */
	public static InputStream getInputStream(String filename){
		try{
			return FileUtils.class.getClassLoader().getResourceAsStream(filename);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String readFile(String filename){
		StringBuffer sb=new StringBuffer();
		BufferedReader in=null;
		try{
			in=new BufferedReader(new InputStreamReader(new FileInputStream(new File(filename))));
			byte[] b=new byte[512];
			String line=null;
			while((line=in.readLine())!=null){
				sb.append(line);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
	
	public static String readFile(File file){
		StringBuffer sb=new StringBuffer();
		BufferedReader in=null;
		try{
			in=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			byte[] b=new byte[512];
			String line=null;
			while((line=in.readLine())!=null){
				sb.append(line);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
	
	
	public static void copyFile(File src,File des){
		InputStream in=null;
		OutputStream out=null;
		try{
			in=new BufferedInputStream(new FileInputStream(src));
			byte[] b=new byte[1014];
			out=new BufferedOutputStream(new FileOutputStream(des));
			int read=0;
			while((read=in.read(b))!=-1){
				out.write(b, 0, b.length);
			}
			out.flush();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 
	 * @param fileName
	 * @param path
	 * @param inputStream
	 */
	public static void writeFile(String fileName,String path,byte[] inputStream){
		FileOutputStream fileOutputStream=null;
		ByteArrayOutputStream output = null;
		try{
			fileOutputStream = new FileOutputStream(new File(path+File.separator
					+fileName));
			output = new ByteArrayOutputStream();
			output.write(inputStream, 0, inputStream.length);
			fileOutputStream.write(output.toByteArray());
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(output!=null){
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fileOutputStream!=null){
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
