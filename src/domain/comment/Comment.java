package domain.comment;

import domain.member.Member;

public class Comment {
	
	private Long id;
	private Long bbsId; // FK references bbs (bbsID);
	private Long memberId;
	private String commentContent;
	private Member member; // not stored in db, manage member with memberId 
	
	
	public Comment(Long memberId, String commentContent) {
		this.memberId = memberId;
		this.commentContent = commentContent;
	}
	
	public Long getBbsId() {
		return bbsId;
	}
	public void setBbsId(Long bbsId) {
		this.bbsId = bbsId;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
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

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	
}
