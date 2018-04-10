package BEAN;

public class Grammar {
	
	private int grammarGuidelineId;
	private String grammarName;
	private String grammarImage;
	private String content;
	public Grammar() {
		super();
	}
	public Grammar(int grammarGuidelineId, String grammarName, String grammarImage, String content) {
		super();
		this.grammarGuidelineId = grammarGuidelineId;
		this.grammarName = grammarName;
		this.grammarImage = grammarImage;
		this.content = content;
	}
	public int getGrammarGuidelineId() {
		return grammarGuidelineId;
	}
	public void setGrammarGuidelineId(int grammarGuidelineId) {
		this.grammarGuidelineId = grammarGuidelineId;
	}
	public String getGrammarName() {
		return grammarName;
	}
	public void setGrammarName(String grammarName) {
		this.grammarName = grammarName;
	}
	public String getGrammarImage() {
		return grammarImage;
	}
	public void setGrammarImage(String grammarImage) {
		this.grammarImage = grammarImage;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
