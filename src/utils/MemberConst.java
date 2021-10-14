package utils;

public class MemberConst {
	
	public static final int JOIN_SUCCESSED = 123425231;
	public static final int JOIN_FAILED = 24325135;
	public static final String MY_SESSION_ID = "mySessionId";
	public static final int LOGIN_ID_MIN_LENGTH = 5;
	public static final int LOGIN_ID_MAX_LENGTH = 19;
	public static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$";
	public static final String EMAIL_REGEX = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
	public static final String EMAIL_SALT = "Dasf123dsavASFvwa3246qr1";
}
