<%@ page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.time.LocalDateTime" %>
<%
	// 현재 날짜
	LocalDate  today = LocalDate.now();
	// 현재 날짜 시간 + 1일 -> 내일날짜
	LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
%>