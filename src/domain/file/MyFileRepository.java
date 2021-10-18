package domain.file;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import domain.member.Member;

import static utils.JdbcTemplate.*;
public class MyFileRepository {
	
	Connection conn;
	public MyFileRepository(Connection conn) {
		this.conn = conn;
	}
	
	public int upload(MyFile file) {
		String sql = "insert into files (bbs_id,file_name,file_real_name) values (?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, file.getBbsId());
			pstmt.setString(2, file.getFileName());
			pstmt.setString(3, file.getFileRealName());
			int res = pstmt.executeUpdate();
			commit(conn);
			return res;
		}catch (Exception e) {
			rollBack(conn);
			return -1;
		}finally {
			close(pstmt);
		}
	}
	
	public MyFile findFileByBbsId(Long id) {
		String sql = "select * from files where bbs_id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MyFile file = new MyFile();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				file.setId(rs.getLong(1));
				file.setBbsId(rs.getLong(2));
				file.setFileName(rs.getString(3));
				file.setFileRealName(rs.getString(4));
				return file;
			}
			return null;
		} catch (Exception e) {
			return null;
		}finally {
			close(rs);
			close(pstmt);
		}
	}
	
}
