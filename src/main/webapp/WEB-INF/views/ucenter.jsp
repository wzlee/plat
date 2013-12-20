<%@page import="java.util.LinkedList"%>
<%@page import="com.fivestars.interfaces.bbs.util.XMLHelper"%>
<%@page import="com.fivestars.interfaces.bbs.client.Client"%>
<%
Client uc = new Client();
String result = uc.uc_user_login("liangping2", "liangping");

LinkedList<String> rs = XMLHelper.uc_unserialize(result);
if(rs.size()>0){
	int $uid = Integer.parseInt(rs.get(0));
	String $username = rs.get(1);
	String $password = rs.get(2);
	String $email = rs.get(3);
	if($uid > 0) {
		response.addHeader("P3P"," CP=\"CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR\"");

		out.println("asdfasdf");
		out.println($username);
		out.println($password);
		out.println($email);
		
		String $ucsynlogin = uc.uc_user_synlogin($uid);
		out.println("asdfasdfdsafasdf"+$ucsynlogin);
		
		Cookie auth = new Cookie("auth", uc.uc_authcode($password+"\t"+$uid, "ENCODE"));
		auth.setMaxAge(31536000);
		//auth.setDomain("localhost");
		response.addCookie(auth);
		
		Cookie user = new Cookie("uchome_loginuser", $username);
		response.addCookie(user);
		
	} else if($uid == -1) {
		out.println("sdafasdfadsf");
	} else if($uid == -2) {
		out.println("123123123);
	} else {
		out.println("DSFSDF");
	}
}else{
	out.println("Login failed");
	System.out.println(result);
}
%>