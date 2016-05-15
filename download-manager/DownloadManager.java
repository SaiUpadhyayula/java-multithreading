package com.java.concurrency.downloadmanager;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DownloadManager {

	private static DownloadManager INSTANCE;

	private ExecutorService executors = Executors.newCachedThreadPool();

	public static DownloadManager getInstance() {
		if (INSTANCE != null) {
			return INSTANCE;
		} else {
			INSTANCE = new DownloadManager();
			return INSTANCE;
		}
	}

	public synchronized void addDownload(URL urlToDownload) throws IOException {
		System.out.println("Starting download for URL - "
				+ urlToDownload.toString());
		executors.submit(() -> {
			// Connect to the website using JSoup
				try {
					Document doc = Jsoup.connect(urlToDownload.toString())
							.get();

					Elements img = doc.getElementsByTag("img");

					for (Element el : img) {
						String src = el.absUrl("src");

						System.out.println("Image Found!");
						System.out.println("src attribute is : " + src);

						String fileName = FileUtils.generateFileName(new URL(
								src));

						downloadImages(urlToDownload, fileName);
					}
				} catch (Exception e) {
					System.err.print("File not found");
					e.printStackTrace();
				}
			});
	}

	private synchronized void downloadImages(URL urlToDownload, String fileName)
			throws IOException, FileNotFoundException {

		InputStream in = urlToDownload.openStream();

		OutputStream out = new BufferedOutputStream(new FileOutputStream(
				"I://download/" + fileName));

		for (int b; (b = in.read()) != -1;) {
			out.write(b);
		}
		out.close();
		in.close();
	}

	public void completeDownload() {
		executors.shutdown();
	}
}
