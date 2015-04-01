package edu.wit.fcl.zhihu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

public class Answer {
	public void toText(File outFile) throws UnsupportedEncodingException, IOException
	{
		Document doc = Jsoup.parse(contentHtml);
		List<Node> nodes = doc.body().childNodes();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < nodes.size(); i++) {
			Node node = nodes.get(i);
			if(node instanceof Element)
			{
				Element e = (Element)node;
				String tagname = e.tagName();
				if(tagname.equalsIgnoreCase("br"))
				{
					sb.append("\r\n");
				}
				else if(tagname.equalsIgnoreCase("strong"))
				{
//					sb.append(e.text());
				}
				else if(tagname.equalsIgnoreCase("img"))
				{
					//TODO
				}
				else if(tagname.equalsIgnoreCase("span"))
				{
					//TODO
					break;
				}
				else
				{
					//TODO
				}
				
			}
			else if( node instanceof TextNode)
			{
				TextNode tn = (TextNode)node;
				sb.append(tn.text());
			}
			else
			{
				//TODO
			}
		}
		
		FileOutputStream fos = new FileOutputStream(outFile);
		fos.write(sb.toString().replaceAll("\r\n\r\n","\r\n").getBytes("utf8"));
		fos.close();
	}
	public static final String fileNameCheckRegax  ="[/\\\\:\\*\\?\\\"\\<\\>\\|]";
	public Answer(Element answerNode) {
		initAnserIdQuestionId(answerNode);
		initContentHtml(answerNode);
		initVoteCount(answerNode);
		initSummaryText(answerNode);
		initQuestionText(answerNode);
	}
	private void initQuestionText(Element answerNode) {
		String queryStr = "h2.zm-item-title a";
		Elements nodes = answerNode.select(queryStr);
		questionText= nodes.get(0).text();
	}
	private void initSummaryText(Element answerNode) {
		String queryStr = "div.zm-item-rich-text div.zh-summary";
		Elements nodes = answerNode.select(queryStr);
		summaryText = nodes.get(0).text();
	}
	private void initVoteCount(Element answerNode) {
		String queryStr = "div.zm-item-vote a.zm-item-vote-count";
		Elements nodes = answerNode.select(queryStr);
		String votecount = nodes.get(0).attr("data-votecount");
		voteCount = Integer.parseInt(votecount != null ? votecount : "0");
	}

	private void initContentHtml(Element answerNode) {
		String queryStr = "div.zm-item-fav div.zm-item-answer div.zm-item-rich-text textarea.content";
		Elements nodes = answerNode.select(queryStr);
		contentHtml = nodes.get(0).ownText();
	}

	private void initAnserIdQuestionId(Element answerNode) {
		Elements node = answerNode.select("a.toggle-expand");
		String hrefstr = node.get(0).attr("href");
		String[] ss = hrefstr.split("/");
		questionId = ss[2];
		answerId = ss[4];
	}

	private String answerId;
	private String questionId;
	private String contentHtml;
	private int voteCount;

	// TODO
	private String summaryText;
	private String questionText;
	// private String lastEditTime;
	// private String pubishTime;

	public String getQuestionText() {
		return questionText;
	}
	public String getSummaryText() {
		return summaryText;
	}
	public void setSummaryText(String summaryText) {
		this.summaryText = summaryText;
	}
	public String getAnswerId() {
		return answerId;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getContentHtml() {
		return contentHtml;
	}

	public void setContentHtml(String contentHtml) {
		this.contentHtml = contentHtml;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}
}
