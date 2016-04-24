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
//		"http://r1---sn-q4fl6n7s.googlevideo.com/videoplayback?signature=34F3384694941370A734A4044C259B0DE5863004.6B7F0B4386430FBD104F9947CB27C8171C33CDFF&upn=IWkOzxwQ1S4&ratebypass=yes&fexp=9407610%2C9408210%2C9416126%2C9416891%2C9419451%2C9422342%2C9422596%2C9426926%2C9428398%2C9429149%2C9429165%2C9429585%2C9431012%2C9432363%2C9433045%2C9433097%2C9433301%2C9433424%2C9433858%2C9433947%2C9433999%2C9435058&key=yt6&lmt=1461038533327741&itag=18&mm=31&mn=sn-q4fl6n7s&mime=video%2Fmp4&id=o-AKmqOzcNDvHyQKrVqamR1ctFoh1FpS19k5f45ySxUVyk&pl=40&ipbits=0&ip=2a03%3A8180%3A1001%3A16a%3A%3A8ee1&mt=1461423036&mv=m&ms=au&dur=347.207&initcwndbps=531250&sver=3&expire=1461444774&sparams=dur%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cnh%2Cpl%2Cratebypass%2Csource%2Cupn%2Cexpire&source=youtube&nh=IgpwcjA1LmRmdzA2KgkxMjcuMC4wLjE&title=Is+Jon+Snow+Coming+Back%3F+The+%27Game+of+Thrones%27+Cast+Weighs+In%2C+Talks+Season+6"
//		"http://r1---sn-q4fl6n7s.googlevideo.com/videoplayback?signature=07A4712B6C774F4FBFC352750D6AD8BBECE4CFC8.224AC01B3E33DAB42F3B418480B3BADA073708E6&upn=IWkOzxwQ1S4&ratebypass=yes&fexp=9407610%2C9408210%2C9416126%2C9416891%2C9419451%2C9422342%2C9422596%2C9426926%2C9428398%2C9429149%2C9429165%2C9429585%2C9431012%2C9432363%2C9433045%2C9433097%2C9433301%2C9433424%2C9433858%2C9433947%2C9433999%2C9435058&key=yt6&lmt=1461038853915917&itag=22&mm=31&mn=sn-q4fl6n7s&mime=video%2Fmp4&id=o-AKmqOzcNDvHyQKrVqamR1ctFoh1FpS19k5f45ySxUVyk&pl=40&ipbits=0&ip=2a03%3A8180%3A1001%3A16a%3A%3A8ee1&mt=1461423036&mv=m&ms=au&dur=347.207&initcwndbps=531250&sver=3&expire=1461444774&sparams=dur%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cnh%2Cpl%2Cratebypass%2Csource%2Cupn%2Cexpire&source=youtube&nh=IgpwcjA1LmRmdzA2KgkxMjcuMC4wLjE&title=Is+Jon+Snow+Coming+Back%3F+The+%27Game+of+Thrones%27+Cast+Weighs+In%2C+Talks+Season+6"
//		"http://r1---sn-q4fl6n7s.googlevideo.com/videoplayback?signature=516B7AF0E184AE8F83645DA1A7E44527355EF158.491227534EA6F8D4524F22A8E7F3266BCC04FB0F&upn=IWkOzxwQ1S4&fexp=9407610%2C9408210%2C9416126%2C9416891%2C9419451%2C9422342%2C9422596%2C9426926%2C9428398%2C9429149%2C9429165%2C9429585%2C9431012%2C9432363%2C9433045%2C9433097%2C9433301%2C9433424%2C9433858%2C9433947%2C9433999%2C9435058&key=yt6&lmt=1461038809617232&keepalive=yes&itag=160&mm=31&mn=sn-q4fl6n7s&mime=video%2Fmp4&id=o-AKmqOzcNDvHyQKrVqamR1ctFoh1FpS19k5f45ySxUVyk&pl=40&ipbits=0&ip=2a03%3A8180%3A1001%3A16a%3A%3A8ee1&mt=1461423036&gir=yes&ms=au&dur=347.146&initcwndbps=531250&sver=3&expire=1461444774&mv=m&sparams=clen%2Cdur%2Cgir%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Ckeepalive%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cnh%2Cpl%2Csource%2Cupn%2Cexpire&clen=4774567&source=youtube&nh=IgpwcjA1LmRmdzA2KgkxMjcuMC4wLjE&title=Is+Jon+Snow+Coming+Back%3F+The+%27Game+of+Thrones%27+Cast+Weighs+In%2C+Talks+Season+6"
	}

	private static void downloadFile(URL url,File downloadLocation) throws IOException {
		ReadableByteChannel rbc = Channels.newChannel(url.openStream());
		FileOutputStream fos = new FileOutputStream(downloadLocation);
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
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
