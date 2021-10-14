package domain.member;
import static utils.JdbcTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import domain.bbs.Bbs;
import domain.member.Member;
import utils.MemberConst;


public class MemberRepository {
	
	Connection conn;
	public MemberRepository (Connection conn) {
		this.conn = conn;
	}
	
	public int save(Member member) {
		String sql = "insert into members (member_login_id,member_password,member_email,salt,create_date,update_date,member_status) values (?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getLoginId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getSalt());
			pstmt.setTimestamp(5, member.getCreateDate());
			pstmt.setTimestamp(6, member.getUpdateDate());
			pstmt.setString(7, member.getMemberStatus().name());
			pstmt.executeUpdate();
			commit(conn);
			return MemberConst.JOIN_SUCCESSED;
		}catch (Exception e) {
			e.printStackTrace();
			rollBack(conn);
			return MemberConst.JOIN_FAILED;
		}finally {
			close(pstmt);
		}
	}
	
	public Member findById(Long id) {
		String sql = "select * from members where member_id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member member = new Member();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {

				member.setId(rs.getLong(1));
				member.setLoginId(rs.getString(2));
				member.setPassword(rs.getString(3));
				member.setEmail(rs.getString(4));
				member.setSalt(rs.getString(5));
				member.setCreateDate(rs.getTimestamp(6));
				member.setUpdateDate(rs.getTimestamp(7));
				member.setMemberStatus(MemberStatus.valueOf(rs.getString(8)));
			}
			return member;
		} catch (Exception e) {
			return null;
		}finally {
			close(rs);
			close(pstmt);
		}
	}
	
	public Member findByLoginId(String loginId) {
		String sql = "select * from members where member_login_id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member member = new Member();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,loginId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member.setId(rs.getLong(1));
				member.setLoginId(rs.getString(2));
				member.setPassword(rs.getString(3));
				member.setEmail(rs.getString(4));
				member.setSalt(rs.getString(5));
				member.setCreateDate(rs.getTimestamp(6));
				member.setUpdateDate(rs.getTimestamp(7));
				member.setMemberStatus(MemberStatus.valueOf(rs.getString(8)));
				return member;
			}
			return null;
		} catch (Exception e) {
			return null;
		}finally {
			close(rs);
			close(pstmt);
		}
	}
	
	public Member findByEmail(String email) {
		String sql = "select * from members where member_email = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member member = new Member();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member.setId(rs.getLong(1));
				member.setLoginId(rs.getString(2));
				member.setPassword(rs.getString(3));
				member.setEmail(rs.getString(4));
				member.setSalt(rs.getString(5));
				member.setCreateDate(rs.getTimestamp(6));
				member.setUpdateDate(rs.getTimestamp(7));
				member.setMemberStatus(MemberStatus.valueOf(rs.getString(8)));
				return member;
			}
			return null;
		} catch (Exception e) {
			return null;
		}finally {
			close(rs);
			close(pstmt);
		}
	}
	
	/**
	 * to be updated (need handle bbs table)
	 * @param member
	 * @return 
	 */
	public int deleteMember(Member member) {
		//String sql = "update members set member_login_id = ?, member_email = ?, update_date = ?, member_status = ? where member_id = ?";
		String sql = "delete from members where member_id = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, member.getId());
			int res = pstmt.executeUpdate();
			commit(conn);
			return res;
		}catch (Exception e) {
			e.printStackTrace();
			rollBack(conn);
			return -1; // 해당 인원 없음
		}finally {
			close(pstmt);
		}
	}
	
	public int updateMember(Member member) {
		String sql = "update members set member_password = ?, member_email = ?, update_date = ? where member_id = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getEmail());
			pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			pstmt.setLong(4, member.getId());
			
			int res = pstmt.executeUpdate();
			commit(conn);
			return res;
		}catch (Exception e) {
			e.printStackTrace();
			rollBack(conn);
			return -1; // 해당 인원 없음
		}finally {
			close(pstmt);
		}
	}
	
	public List<Bbs> findBbsByMemberId(Long id){
		String sql = "select * from members m join bbs b on m.member_id = b.member_id where m.member_id = ? and b.bbs_available = 1 order by bbs_id desc limit 10";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Bbs> bbsList = new ArrayList<Bbs>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1,id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Bbs bbs = new Bbs();
				bbs.setBbsID(rs.getLong("bbs_id"));
				bbs.setBbsTitle(rs.getString("bbs_title"));
				bbs.setUserID(rs.getLong("member_id"));
				bbs.setBbsDate(rs.getTimestamp("bbs_date"));
				bbs.setBbsContent(rs.getString("bbs_content"));
				bbs.setBbsAvailable(rs.getInt("bbs_available"));
				
				bbsList.add(bbs);
			}
			return bbsList;
		} catch (Exception e) {
			return null;
		}finally {
			close(rs);
			close(pstmt);
		}
	}
}
