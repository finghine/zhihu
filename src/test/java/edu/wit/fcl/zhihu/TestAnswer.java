package edu.wit.fcl.zhihu;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

public class TestAnswer {

	@Test
	public void testAnswerInit() throws IOException {
		String testFile="testdata/answer.txt";
		Document doc = Jsoup.parse(new File(testFile), "utf8");
		Element em= doc.body().child(0);
//		System.out.println(em.html());
		Answer an = new Answer(em);
		Assert.assertEquals("31552610",an.getAnswerId());
		Assert.assertEquals("25814355",an.getQuestionId());
		Assert.assertEquals(19350,an.getVoteCount());
		System.out.println(an.getContentHtml());
	}

}