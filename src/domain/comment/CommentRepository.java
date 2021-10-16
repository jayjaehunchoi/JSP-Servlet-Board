package domain.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import domain.member.Member;
import utils.MemberConst;

import static utils.JdbcTemplate.*;

public class CommentRepository {
	
	Connection conn;
	public CommentRepository(Connection conn) {
		this.conn = conn;
	}
	
	public int save(Comment comment) {
		String sql = "insert into comments (bbs_id,member_id,comment_content) values (?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, comment.getBbsId());
			pstmt.setLong(2, comment.getMemberId());
			pstmt.setString(3, comment.getCommentContent());
			int res = pstmt.executeUpdate();
			commit(conn);
			return res;
		}catch (Exception e) {
			e.printStackTrace();
			rollBack(conn);
			return -1;
		}finally {
			close(pstmt);
		}
	}
	
	public int deleteComment(Long id) {
		String sql = "delete from comments where comment_id = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			int res = pstmt.executeUpdate();
			commit(conn);
			return res;
		}catch (Exception e) {
			e.printStackTrace();
			rollBack(conn);
			return -1; // 해당 댓글 없음
		}finally {
			close(pstmt);
		}
	}
	
	public int deleteCommentByMemberId(Long memberId) {
		String sql = "delete from comments where member_id = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, memberId);
			int res = pstmt.executeUpdate();
			commit(conn);
			return res;
		}catch (Exception e) {
			e.printStackTrace();
			rollBack(conn);
			return -1; // 해당 댓글 없음
		}finally {
			close(pstmt);
		}
	}
	
	/**
	 * to be updated
	 * 
	 */
	public int updateComment(String commentContent, Long id) {
		String sql = "update members set comment_content = ? where comment_id = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, commentContent);
			pstmt.setLong(2, id);
			
			int res = pstmt.executeUpdate();
			commit(conn);
			return res;
		}catch (Exception e) {
			e.printStackTrace();
			rollBack(conn);
			return -1;
		}finally {
			close(pstmt);
		}
	}
}
