package edu.wit.fcl.zhihu;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Answer {
	public Answer(Element answerNode) {
		initAnserIdQuestionId(answerNode);
		initContentHtml(answerNode);
		initVoteCount(answerNode);
	}

	private void initVoteCount(Element answerNode) {
		String queryStr = "div.zm-item-vote a.zm-item-vote-count";
		Elements nodes = answerNode.select(queryStr);
		String votecount = nodes.get(0).attr("data-votecount");
		voteCount = Integer.parseInt(votecount != null ? votecount : "0");
	}

	private void initContentHtml(Element answerNode) {
		String queryStr = "div.zm-item-fav div.zm-item-answer div.zm-item-rich-text";
		Elements nodes = answerNode.select(queryStr);
		contentHtml = nodes.get(0).html();
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
	// private String summaryText;
	// private String lastEditTime;
	// private String pubishTime;

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
