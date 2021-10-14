package domain.comment;

public class Comment {
	
	private Long id;
	private Long bbsId; // FK references bbs (bbsID);
	private String memberLoginId; // no FK , managed by memberLoginId col (will be updated to manage this var with memberId index)
	private String commentContent;
	
	
	public Comment(String memberLoginId, String commentContent) {
		this.memberLoginId = memberLoginId;
		this.commentContent = commentContent;
	}
	
	public Long getBbsId() {
		return bbsId;
	}
	public void setBbsId(Long bbsId) {
		this.bbsId = bbsId;
	}
	public String getMemberLoginId() {
		return memberLoginId;
	}
	public void setMemberLoginId(String memberLoginId) {
		this.memberLoginId = memberLoginId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	
	
}
