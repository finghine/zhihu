package edu.wit.fcl.zhihu;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class TestCollection {

	@Test
	public void ansert() throws UnsupportedEncodingException, IOException {
		String filename = "collection_31446835_out_js.html";
		Collection collection = new Collection(new File(filename));
		String name = collection.getName();
		Assert.assertEquals("应用程序与网络安全的方方面面",name);
		Assert.assertEquals("31446835",collection.getId());
		Assert.assertEquals("huang_haopeng",collection.getCreator().getId());
		Assert.assertEquals("黄捞捞",collection.getCreator().getName());
		List<Answer> list = collection.getAnswerlist();
		Assert.assertEquals(22,list.size());
		Answer firstAnswer = list.get(0);
		firstAnswer.toText(new File("testdata/"+firstAnswer.getQuestionText()+".txt"));
	}
	@Test
	public void testOutputAllAnwser() throws UnsupportedEncodingException, IOException {
		String filename = "collection_31446835_out_js.html";
		Collection collection = new Collection(new File(filename));
		List<Answer> list = collection.getAnswerlist();
		Assert.assertEquals(22,list.size());
		for (int i = 0; i < list.size(); i++) {
		Answer firstAnswer = list.get(i);
		firstAnswer.toText(new File("testdata/Collection/"+(i+1)+"_"+firstAnswer.getQuestionText()+".txt"));
		}
	}

}
