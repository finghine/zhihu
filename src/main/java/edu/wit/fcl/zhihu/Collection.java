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

	private void init(Document doc) {
		answerlist = new ArrayList<Answer>();
		initAnserList(doc);
		initName(doc);
		initCreator(doc);
		initId(doc);
	}

	private void initId(Document doc) {

		String csspath = "div#zh-list-meta-wrap.zm-item-meta.zm-item-comment-el a";
		Elements es = doc.select(csspath);
		Element e = es.get(1);
		String href = e.attr("href");
		String[] ss = href.split("/");
		id =ss[2];
	}

	public User getCreator() {
		return creator;
	}

	public String getId() {
		return id;
	}

	private void initCreator(Document doc) {
		String csspath= "div#zh-single-answer-author-info.zm-list-content-medium h2.zm-list-content-title a";
		Elements els = doc.select(csspath);
		Element e = els.get(0);
		String href = e.attr("href");
		String name = e.html();
		creator = new User();
		creator.setId(href.split("/")[2]);
		creator.setName(name);
	}

	private void initName(Document doc) {
		String titleName = "div#zh-list-title h2#zh-fav-head-title";
		Elements es = doc.select(titleName);
		name = es.get(0).html();
	}

	private void initAnserList(Document doc) {
		Elements elist = doc.select("#zh-list-answer-wrap div.zm-item");
		int i = 0;
		for (Element e : elist) {
			Answer aw = new Answer(e);
			// System.out.println(aw.getQuestionText());
			answerlist.add(aw);
		}
	}

	public String getName() {
		return name;
	}

	private String id;
	private User creator;
	private String name;
	private List<Answer> answerlist;

}
