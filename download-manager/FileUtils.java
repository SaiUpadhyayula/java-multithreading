package com.java.concurrency.downloadmanager;

import java.net.URL;

public class FileUtils {

	public static String generateFileName(URL urlToDownload) {
		String url = urlToDownload.toString();
		return url.substring(url.lastIndexOf("/"), url.length());
	}

}
