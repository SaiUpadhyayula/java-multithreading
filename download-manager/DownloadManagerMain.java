package com.java.concurrency.downloadmanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DownloadManagerMain {

	public static void main(String[] args) throws NumberFormatException, IOException{
		System.out.println("Welcome to Console Download Manager");
		System.out.println("Enter the number of downloads you want to have");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int numberOfDownloads = Integer.parseInt(bufferedReader.readLine());
		List<String> downloadList = new ArrayList<>();
		for(int i = 0; i < numberOfDownloads; i++){
			System.out.println("Enter the "+i+"st URL you want to download");
			downloadList.add(bufferedReader.readLine());
		}
		System.out.println("Starting your download...");
	}
}
