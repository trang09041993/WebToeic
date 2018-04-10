package BEAN;

public class member {
	private int memberID;
	private String memberName;
	private String memberPass;
	private String name;
	private int categoryMember;
	public member() {
		super();
	}
	public member(int memberID, String memberName, String memberPass, String name, int categoryMember) {
		super();
		this.memberID = memberID;
		this.memberName = memberName;
		this.memberPass = memberPass;
		this.name = name;
		this.categoryMember = categoryMember;
	}
	public int getMemberID() {
		return memberID;
	}
	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberPass() {
		return memberPass;
	}
	public void setMemberPass(String memberPass) {
		this.memberPass = memberPass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCategoryMember() {
		return categoryMember;
	}
	public void setCategoryMember(int categoryMember) {
		this.categoryMember = categoryMember;
	}

}
