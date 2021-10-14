package service;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;

import domain.bbs.Bbs;
import domain.bbs.BbsRepository;

import static utils.JdbcTemplate.*;

public class BbsService {
	
	private BbsService() {
		
	}
	private static BbsService instance = new BbsService();
	public static BbsService getInstance() {
		return instance;
	}
	
	
	public Timestamp getDate() {
		Connection conn = getConnection();
		Timestamp date = new BbsRepository(conn).getDate();
		close(conn);
		return date;
	}
	
	// 글 추가 할 시 다음 bbsId
	public Long getNext() {
		Connection conn = getConnection();
		Long next = new BbsRepository(conn).getNext();
		close(conn);
		return next;
	}
	
	public Long write(Bbs bbs, Long userID) {
		Connection conn = getConnection();
		Long writeOk = new BbsRepository(conn).write(bbs, userID);
		close(conn);
		return writeOk;
	}

	public List<Bbs> findAll(int pageNumber) {
		Connection conn = getConnection();
		List<Bbs> list = new BbsRepository(conn).getList(pageNumber);
		close(conn);
		return list;
	}
	
	// 다음 page 가 필요한지 확인
	public boolean checkNextPage(int pageNumber) {
		Connection conn = getConnection();
		boolean check = new BbsRepository(conn).nextPage(pageNumber);
		close(conn);
		return check;
	}
	
	public Bbs getBbs(Long bbsID) {
		Connection conn = getConnection();
		Bbs bbs = new BbsRepository(conn).getBbs(bbsID);
		close(conn);
		return bbs;
	}
	
	public int getCurOrder(Long bbsID) {
		Connection conn = getConnection();
		int order = new BbsRepository(conn).getCurOrderById(bbsID);
		close(conn);
		return order;
	}
	
	public int update(Long bbsID, String bbsTitle, String bbsContent) {
		Connection conn = getConnection();
		int res = new BbsRepository(conn).update(bbsID, bbsTitle, bbsContent);
		close(conn);
		return res;
	}
	
	public int delete(Long bbsID) {
		Connection conn = getConnection();
		int res = new BbsRepository(conn).delete(bbsID);
		close(conn);
		return res;
	}
	
	// 페이징처리 위한 count
	public int getCount() {
		Connection conn = getConnection();
		int res = new BbsRepository(conn).countBbs();
		close(conn);
		return res;
	}

}
