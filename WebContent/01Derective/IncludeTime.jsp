<%@ page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.time.LocalDateTime" %>
<%
	// ���� ��¥
	LocalDate  today = LocalDate.now();
	// ���� ��¥ �ð� + 1�� -> ���ϳ�¥
	LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
%>