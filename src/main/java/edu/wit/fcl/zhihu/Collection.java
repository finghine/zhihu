package edu.wit.fcl.zhihu;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Collection {
	public String getId() {
		return id;
	}
	public String getCreator() {
		return creator;
	}
	public String getName() {
		return name;
	}
	public List<Answer> getAnswerlist() {
		return answerlist;
	}

	public Collection(File file) {
		Document doc;
		try {
			doc = Jsoup.parse(file, "utf8");
			init(doc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void init(Document doc)
	{
		answerlist=new ArrayList<Answer>();
			initAnserList(doc);
			initName(doc);
	}

	private void initName(Document doc) {
		String titleName = "div#zh-list-title h2#zh-fav-head-title";
		Elements es = doc.select(titleName);
		name = es.get(0).html();
	}
	private void initAnserList(Document doc)
	{
		Elements elist = doc.select("#zh-list-answer-wrap div.zm-item");
		int i=0;
		for(Element e:elist)
		{
			Answer aw = new Answer(e);
			System.out.println(aw.getQuestionText());
			answerlist.add(aw);
		}
	}
	private String id;
	private String creator;
	private String name;
	private List<Answer> answerlist;

}
