package service;

import domain.bbs.Bbs;
import domain.member.Member;
import domain.member.MemberRepository;
import utils.MemberConst;
import utils.ShaEncoder;

import static utils.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MemberService {
	private MemberService() {
		
	}
	private static MemberService instance = new MemberService();
	
	public static MemberService getInstance() {
		return instance;
	}
	
	public int join(Member member) {
		Connection conn = getConnection();
		member.setPassword(encodeInput(member.getPassword(), member.getSalt()));
		int saveCheck = new MemberRepository(conn).save(member);
		close(conn);
		return saveCheck;

	}
	
	public Member findById(Long id) {
		Connection conn = getConnection();
		Member member = new MemberRepository(conn).findById(id);
		close(conn);
		return member;
	}
	
	public Member findByLoginId(String loginId) {
		Connection conn = getConnection();
		Member member = new MemberRepository(conn).findByLoginId(loginId);
		close(conn);
		return member;
	}
	
	public Member findByEmail(String email) {
		Connection conn = getConnection();
		Member member = new MemberRepository(conn).findByEmail(email);
		close(conn);
		return member;
	}
	
	public List<Bbs> findBbsByMemberId(Long id){
		Connection conn = getConnection();
		List<Bbs> bbsList = new MemberRepository(conn).findBbsByMemberId(id);
		close(conn);
		return bbsList;
	}
	
	public int deleteMember(Member member) {
		Connection conn = getConnection();
		int result = new MemberRepository(conn).deleteMember(member);
		close(conn);
		return result;
	}
	
	public Member checkIdAndPassword(String loginId, String inputPassword) {
		Member member = findByLoginId(loginId);
		if(member == null) {
			return null;
		}
		String salt = member.getSalt();
		String password = encodeInput(inputPassword, salt);
		
		if(!isPasswordCorrect(password, member.getPassword())) {
			return null;
		}
		
		return member;
	}
	
	public int updateMember(Member member) {
		Connection conn = getConnection();
		member.setPassword(encodeInput(member.getPassword(), member.getSalt()));
		int result = new MemberRepository(conn).updateMember(member);
		close(conn);
		return result;
	}
	
	private String encodeInput(String input, String salt) {
		return ShaEncoder.transferToSHA_256(input, salt);
	}

	
	public boolean isPasswordCorrect(String password, String dbPassword) {
		if(password.equals(dbPassword)) {
			return true;
		}
		return false;
	}
	
	public boolean isPasswordSatisfiedGivenCondition(String password) {
		Pattern p = Pattern.compile(MemberConst.PASSWORD_REGEX);
		Matcher m = p.matcher(password);
		return m.matches();
	}

	public boolean isMemberIdNotExist(String name) {
		Member findMember = findByLoginId(name);
		if(findMember != null) {
			return false;
		}
		return true;
	}
	
	public boolean isPassWordSameWithPasswordCheck(String password, String passwordCheck) {
		if(password.equals(passwordCheck)) {
			return true;
		}
		return false;
	}
	
	public boolean isValidEmail(String email) {
		Pattern p = Pattern.compile(MemberConst.EMAIL_REGEX);
		Matcher m = p.matcher(email);
		return m.matches();
	}
	

}
