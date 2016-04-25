package com.java.concurrency.downloadmanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class DownloadManagerMain {

	public static void main(String[] args) throws NumberFormatException, IOException{
		
		System.out.println("Welcome to Console Download Manager");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the URL you want to download");
		String urlString = bufferedReader.readLine();
		URL url = new URL(urlString);
		System.out.println("Checking the availability of the URL");		
		assert verifyValidUrl(urlString);
		assert checkURLAvailability(url);		
		System.out.println("Url is available for the download");	
		downloadFile(url,new File("I://video.JPG"));
	}

	private static void downloadFile(URL url,File downloadLocation) throws IOException {
		ReadableByteChannel rbc = Channels.newChannel(url.openStream());
		FileOutputStream fos = new FileOutputStream(downloadLocation);
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		fos.close();
	}

	private static boolean verifyValidUrl(String url) {		
		return url.toLowerCase().startsWith("http://");
	}

	private static boolean checkURLAvailability(URL url) {
		try{
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(10);
			connection.setReadTimeout(10);
			connection.setRequestMethod("HEAD");
			connection.setRequestProperty("User-Agent", ""); 
			int response = connection.getResponseCode();
			return (response >= 200 && response<=399);
		}catch(Exception exception){
			exception.printStackTrace();
			return false;
		}
	}
}
