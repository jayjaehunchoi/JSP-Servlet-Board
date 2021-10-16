package domain.bbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import domain.comment.Comment;
import service.MemberService;

import static utils.JdbcTemplate.*;

public class BbsRepository {
	Connection conn;
	MemberService memberService = MemberService.getInstance();
	public BbsRepository(Connection conn){
		this.conn = conn;
	}
	
	public Timestamp getDate() {
		String sql = "select now()";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getTimestamp(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return new Timestamp(System.currentTimeMillis());			
	}
	
	public Long getNext() {
		String sql = "select bbs_id from bbs order by bbs_id desc";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getLong(1)+1;
			}
			return 1L; // 현재가 첫번째
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return -1L;			
	}
	
	public Long write(Bbs bbs, Long userID) {
		String sql = "insert into bbs values (?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		Long val = getNext();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, val);
			pstmt.setString(2, bbs.getBbsTitle());
			pstmt.setLong(3, userID);
			pstmt.setTimestamp(4, getDate());
			pstmt.setString(5, bbs.getBbsContent());
			pstmt.setInt(6, 1);
			pstmt.executeUpdate();
			commit(conn);
			return val;
			
		}catch(Exception e) {
			e.printStackTrace();
			rollBack(conn);
		}finally {
			close(pstmt);
		}
		return -1L;	
	}
	
	public List<Bbs> getList(int pageNumber){
		String sql = "select * from bbs where bbs_id < ? and bbs_available = 1 order by bbs_id desc limit 10";
		List<Bbs> list = new ArrayList<Bbs>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, getPrevLast(pageNumber));
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Bbs bbs = new Bbs();
				bbs.setBbsID(rs.getLong(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getLong(3));
				bbs.setBbsDate(rs.getTimestamp(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getInt(6));
				bbs.setLoginId(memberService.findById(rs.getLong(3)).getLoginId());
				list.add(bbs);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;			
	}
	
	public Long getPrevLast(int pageNumber) {
		String sql = "select bbs_id from bbs where bbs_available = 1 order by bbs_id desc limit ?,?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, (pageNumber-1) * 10);
			pstmt.setLong(2, 1);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getLong(1)+1;
			}
			return 1L; // 현재가 첫번째
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return -1L;	
	}
	
	public boolean nextPage(int pageNumber) {
		String sql = "select * from bbs where bbs_id < ? and bbs_available = 1";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, getPrevLast(pageNumber+1));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return false;
	}
	
	public Bbs getBbs(Long bbsID) {
		String sql = "select * from bbs b left join comments c on b.bbs_id = c.bbs_id where b.bbs_id = ? limit 10";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, bbsID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Bbs bbs = new Bbs();
				bbs.setBbsID(rs.getLong(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getLong(3));
				bbs.setBbsDate(rs.getTimestamp(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getInt(6));
				bbs.setLoginId(memberService.findById(rs.getLong(3)).getLoginId());
				if(rs.getString("member_login_id") != null) {
					Comment comment = new Comment(rs.getString("member_login_id"), rs.getString("comment_content"));
					comment.setId(rs.getLong("comment_id"));
					bbs.addComments(comment);
					while(rs.next()) {
						comment = new Comment(rs.getString("member_login_id"), rs.getString("comment_content"));
						comment.setId(rs.getLong("comment_id"));
						bbs.addComments(comment);
					}
				}
				return bbs;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return null;
	}
	
	public int countBbs() {
		String sql = "select count(bbs_id) from bbs where bbs_available = 1";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1)-1; //페이지 만들기 용 -1 
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return -1;	// 못헤아리면..
	}

	public int getCurOrderById(Long BbsId) {
		String sql = "select count(bbs_id) from bbs where bbs_id >= ? and bbs_available = 1";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, BbsId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1)-1; //페이지 만들기 용 -1 
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return -1;	// 못헤아리면..
	}
	
	public int update(Long bbsID, String bbsTitle, String bbsContent) {
		String sql = "update bbs set bbs_title = ?, bbs_content = ? where bbs_id = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bbsTitle);
			pstmt.setString(2, bbsContent);
			pstmt.setLong(3, bbsID);
			int res = pstmt.executeUpdate();
			commit(conn);
			return res;
			
		}catch(Exception e) {
			e.printStackTrace();
			rollBack(conn);
		}finally {
			close(pstmt);
		}
		return -1;	
	}
	
	/**
	 * update completed
	 * @param bbsID
	 * @return when succeed = res, else = -1
	 */
	public int delete(Long bbsID) {
		String sql = "update bbs set bbs_available = 0 where bbs_id = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, bbsID);
			int res = pstmt.executeUpdate();
			commit(conn);
			return res;
			
		}catch(Exception e) {
			e.printStackTrace();
			rollBack(conn);
		}finally {
			close(pstmt);
		}
		return -1;
	}
}

