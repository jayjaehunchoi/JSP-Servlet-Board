<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="utils.JdbcTemplate, java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
		Connection conn = JdbcTemplate.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet  rs = stmt.executeQuery("SELECT * FROM bbs");
		while(rs.next()){
			out.print(rs.getString(1) + " : " + rs.getString(2) + "<br/>");
		}
		JdbcTemplate.close(rs);
		JdbcTemplate.close(stmt);
		JdbcTemplate.close(conn);
	%>
</body>
</html>