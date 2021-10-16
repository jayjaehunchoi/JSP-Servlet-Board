package domain.bbs;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import domain.comment.Comment;

public class Bbs {
	
	private Long bbsID;
	private String bbsTitle;
	private Long userID; // Fk references member (member_id)
	private Timestamp bbsDate;
	private String bbsContent;
	private String loginId; // not stored in db (BbsForm will be updated)
	private List<Comment> comments = new ArrayList<>(); // not stored in db, 1:N mapping (mapped by commentId)
	
	public Long getBbsID() {
		return bbsID;
	}
	public void setBbsID(Long i) {
		this.bbsID = i;
	}
	public String getBbsTitle() {
		return bbsTitle;
	}
	public void setBbsTitle(String bbsTitle) {
		this.bbsTitle = bbsTitle;
	}
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public Timestamp getBbsDate() {
		return bbsDate;
	}
	public void setBbsDate(Timestamp bbsDate) {
		this.bbsDate = bbsDate;
	}
	public String getBbsContent() {
		return bbsContent;
	}
	public void setBbsContent(String bbsContent) {
		this.bbsContent = bbsContent;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
	public void addComments(Comment comment){
		comments.add(comment);
	}
	
	public List<Comment> getComments(){
		return comments;
	}
	
}
