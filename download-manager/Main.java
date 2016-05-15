package com.java.concurrency.downloadmanager;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Main {

	public static void main(String[] args) {
		System.out
				.println("Welcome to JConsoleDownloader!Enter you URL's seperated by SPACE to download");
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(System.in));
		DownloadManager downloadManager = DownloadManager.getInstance();
		try {
			String urls = bufferedReader.readLine();
			String[] urlArray = urls.split(" ");
			for (int i = 0; i < urlArray.length; i++) {
				assertThat(urlArray[i].toLowerCase().startsWith("http://"))
						.isTrue();
				downloadManager.addDownload(new URL(urlArray[i]));
				downloadManager.completeDownload();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
