package service;

import java.sql.Connection;

import domain.comment.Comment;
import domain.comment.CommentRepository;

import static utils.JdbcTemplate.*;

public class CommentService {
	private CommentService() {
	}
	
	private static CommentService instance = new CommentService();
	public static CommentService getInstance () {
		return instance;
	}
	
	public int saveComment(Comment comment) {
		Connection conn = getConnection();
		int res = new CommentRepository(conn).save(comment);
		close(conn);
		return res;
	}
	
	public int updateComment(String commentContent, Long id) {
		Connection conn = getConnection();
		int res = new CommentRepository(conn).updateComment(commentContent, id);
		close(conn);
		return res;
	}
	
	public int deleteComment(Long id) {
		Connection conn = getConnection();
		int res = new CommentRepository(conn).deleteComment(id);
		close(conn);
		return res;
	}
	
	public int deleteCommentByMember(Long memberId) {
		Connection conn = getConnection();
		int res = new CommentRepository(conn).deleteCommentByMemberId(memberId);
		close(conn);
		return res;
	}
}
