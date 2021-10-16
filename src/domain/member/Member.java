package domain.member;

import java.sql.Timestamp;

import utils.ShaEncoder;

public class Member {
	private Long id;
	private String loginId;
	private String password;
	private String email;
	private String salt;
	private Timestamp createDate;
	private Timestamp updateDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public Member(String loginId, String password, String email) {
		this.loginId = loginId;
		this.password = password;
		this.email = email;
		createDate = new Timestamp(System.currentTimeMillis());
		updateDate = createDate;
	}
	public Member() {
		
	}
	
	public void createSalt() {
		salt = ShaEncoder.randomSaltBySHA1PRNG();
	}
	
	
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	
	
}
