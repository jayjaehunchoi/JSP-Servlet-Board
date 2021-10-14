package service;

import java.sql.Connection;

import domain.file.MyFile;
import domain.file.MyFileRepository;

import static utils.JdbcTemplate.*;
public class MyFileService {
	private MyFileService() {
		
	}
	
	private static MyFileService instance = new MyFileService();
	public static MyFileService getInstance() {
		return instance;
	}
	
	
	public int fileUpload(MyFile file) {
		Connection conn = getConnection();
		int res = new MyFileRepository(conn).upload(file);
		close(conn);
		return res;
	}
	
	public MyFile findFileByBbsId(Long id) {
		Connection conn = getConnection();
		MyFile file = new MyFileRepository(conn).findFileByBbsId(id);
		close(conn);
		return file;
	}
}
