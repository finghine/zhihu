package edu.wit.fcl.zhihu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Utils {

	public static String USEANGEN = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:36.0) Gecko/20100101 Firefox/36.0";

	public static void deleteJs(File inputFile,File outputFile) throws IOException {
		Document doc = Jsoup.parse(inputFile, "utf8");
		Elements elem = doc.select("script");
		System.out.println("delete "+elem.size()+" js element.");
		for(Element em : elem)
		{
			em.remove();
		}
		String str = doc.toString();
		FileOutputStream fos = new FileOutputStream(outputFile);
		fos.write(str.getBytes("utf8"));
		fos.close();
	}
	public static void getFile(String url,File outputFile) throws ClientProtocolException, IOException
	{
		HttpEntity entity = Request.Get(url).userAgent(USEANGEN).execute()
				.returnResponse().getEntity();
		byte[] bys = EntityUtils.toByteArray(entity);
		FileOutputStream fos = new FileOutputStream(outputFile);
		fos.write(bys);
		fos.close();
	}
}
