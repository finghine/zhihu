package edu.wit.fcl.zhihu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.junit.Test;

public class TempTest {

	public static String USEANGEN = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:36.0) Gecko/20100101 Firefox/36.0";


	@Test
	public void deleteScript() throws IOException
	{
		Document doc = Jsoup.parse(new File("collection"), "utf8");
		Elements elem = doc.select("script");
		System.out.println("delete:"+elem.size());
		for(Element em : elem)
		{
			em.remove();
		}
		String str = doc.toString();
		FileOutputStream fos = new FileOutputStream("collection_widthoutjs.html");
		fos.write(str.getBytes("utf8"));
		fos.close();
	}
	@Test
	public void pressAnsower() throws Exception {
		Document doc = Jsoup.parse(new File("data/0.html"), "gbk");
		String nodestr = doc.toString();
		// System.out.println(nodestr);
		List<Node> localNodes = doc.body().childNodes();
		Iterator<Node> iter = localNodes.iterator();
		while (iter.hasNext()) {
			Node localNode = iter.next();
			if (localNode instanceof Element) {
				Element em = (Element)localNode;
				if(em.tagName().equals("br"))
				{
//					System.out.println("br");
				}
				else if(em.tagName().equals("strong"))
				{
//					System.out.println("strong");
					String strongtext= em.html();
//					System.out.println(strongtext);
				}
				else if(em.tagName().equals("img"))
				{
					System.out.println("img");
				}
				else if( em.tagName().equals("span"))
				{
					System.out.println("span");
				}
			} else if (localNode instanceof TextNode) {
				TextNode tn = (TextNode)localNode;
//				System.out.println(tn.text());
			} else {
				System.out.println("other");
			}
		}
	}

	public void split_Html() throws IOException {
		Document doc = Jsoup.parse(new File("collection"), "utf8");
		Elements ecl = doc.select(".zm-item-rich-text textarea.content");
		for (int i = 0; i < ecl.size(); i++) {
			Element e = ecl.get(i);
			int size = e.childNodeSize();
			FileOutputStream fos = new FileOutputStream(new File("data/"+i + ".html"));
			String text =e.ownText(); 
			fos.write(text.getBytes());
			fos.close();
		}
	}

	public void pressHtml() throws IOException {
		Document doc = Jsoup.parse(new File("collection"), "utf8");
		Elements ecl = doc.select("h2");
		for (Element element : ecl) {
			System.out.println(element.html());
		}
		Elements ecl2 = doc.select(".border-pager");
		for (Element e : ecl2) {
			System.out.println(e.html());
		}
	}

	public void test() throws ClientProtocolException, IOException {
		String uri = "http://www.zhihu.com/collection/37640097?page=2";
		// String uri="http://www.zhihu.com/collection/37640097";
		HttpEntity entity = Request.Get(uri).userAgent(USEANGEN).execute()
				.returnResponse().getEntity();
		byte[] bys = EntityUtils.toByteArray(entity);
		FileOutputStream fos = new FileOutputStream("collection");
		fos.write(bys);
		fos.close();
	}

}
