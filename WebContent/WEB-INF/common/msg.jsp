<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//String msg = (String)request.getAttribute("msg");
	String loc = (String)request.getAttribute("loc");
%>

<script>
	if(${not empty msg}){
		alert("${msg}");
	}
	location.href="<%=loc%>";
</script>