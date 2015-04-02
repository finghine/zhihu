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
		System.out.println("--");
		System.out.println(an.getSummaryText());
		System.out.println("--");
		System.out.println(an.getQuestionText());
		String questionText = an.getQuestionText();
		questionText = questionText.replaceAll(Answer.fileNameCheckRegax,"");
		an.toText(new File("testdata/"+questionText+".txt"));

		// must call after toText;
		Assert.assertEquals("2014-10-08",an.getPubishTime());
		Assert.assertEquals("2014-10-09",an.getLastEditTime());
	}
	@Test
	public void testFileNameRegex()
	{
		String taget = "a|b*c?d:*e!f/g\\h>i<j";
		String res = taget.replaceAll(Answer.fileNameCheckRegax, "");
		Assert.assertEquals("abcde!fghij",res);
	}
	@Test
	public void testWithPicture() throws IOException
	{
		String testFile="testdata/withPicture.txt";
		Document doc = Jsoup.parse(new File(testFile), "utf8");
		Element em= doc.body().child(0);
//		System.out.println(em.html());
		Answer an = new Answer(em);
		String questionText = an.getQuestionText();
		questionText = questionText.replaceAll(Answer.fileNameCheckRegax,"");
		an.toText(new File("testdata/"+questionText+".txt"));
	}

	@Test
	public void expectionItem1() throws IOException
	{
		String testFile="testdata/answer_item1.txt";
		Document doc = Jsoup.parse(new File(testFile), "utf8");
		Element em= doc.body().child(0);
//		System.out.println(em.html());
		Answer an = new Answer(em);
		String questionText = an.getQuestionText();
		questionText = questionText.replaceAll(Answer.fileNameCheckRegax,"");
		an.toText(new File("testdata/"+questionText+".txt"));
	}
	@Test
	public void expectionItem2() throws IOException
	{
		String testFile="testdata/answer_item2.txt";
		Document doc = Jsoup.parse(new File(testFile), "utf8");
		Element em= doc.body().child(0);
//		System.out.println(em.html());
		Answer an = new Answer(em);
		String questionText = an.getQuestionText();
		questionText = questionText.replaceAll(Answer.fileNameCheckRegax,"");
		an.toText(new File("testdata/"+questionText+".txt"));
	}
}
