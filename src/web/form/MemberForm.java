package web.form;

/**
 * 
 * @author JaehunChoi
 * not used, need to use (will refactor when update to spring project)
 *
 */
public class MemberForm {
	private String loginId;
	private String password;
	private String email;

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

	public MemberForm(String loginId, String password, String email) {
		this.loginId = loginId;
		this.password = password;
		this.email = email;
	}
	public MemberForm() {
		
	}
	
}
