package BEAN;

public class slideBanner {
	
	private int slideBannerId;
	private String slideName;
	private String slideContent;
	private String slideImage;
	public int getSlideBannerId() {
		return slideBannerId;
	}
	public void setSlideBannerId(int slideBannerId) {
		this.slideBannerId = slideBannerId;
	}
	public String getSlideName() {
		return slideName;
	}
	public void setSlideName(String slideName) {
		this.slideName = slideName;
	}
	public String getSlideContent() {
		return slideContent;
	}
	public void setSlideContent(String slideContent) {
		this.slideContent = slideContent;
	}
	public String getSlideImage() {
		return slideImage;
	}
	public void setSlideImage(String slideImage) {
		this.slideImage = slideImage;
	}
	public slideBanner() {
		super();
	}
	public slideBanner(int slideBannerId, String slideName, String slideContent, String slideImage) {
		super();
		this.slideBannerId = slideBannerId;
		this.slideName = slideName;
		this.slideContent = slideContent;
		this.slideImage = slideImage;
	}

}
