<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>JSP ù�������Դϴ�!</title>
<%! // ����ǥ�� �ִ� �����!
	// �Լ��� ���鶧�� ���⿡ �������Ѵ�!
	// ����ǥ�� ���� ��ũ��Ʈ���� ����� ������ ����!
	public String myFn(){
		return "<h1>���߳�!</h1>";
	}
%>
</head>
<body>
<h1>JSP ù�������Դϴ�!</h1>

	<% // ��ũ��Ʈ�� ���� 
		// ������ ������ url�� ���޵Ǵ� ���� �޴´�!
		// request.getParameter(�Ķ���� ����)
		String user = request.getParameter("msg");
		// if������ ���ް��� ���� ��� �Ÿ���
		if(user == null){
			user = "���� Ǯ���ð����� �Դϴ�!";  
		}
	%>
	
	<!-- ��ũ��Ʈ ǥ���� (���) -->
	<h1><%=user%></h1>
	<h2>
		<a href="index.jsp?msg='Perfect Coding'">�������ڵ�!</a>
		<br>
		<a href="index.jsp?msg='Poor Coding'">���������ڵ�!</a>
		<br>
		<a href="index.jsp">ó������!</a>
	</h2>
	<div><%=myFn() %></div>
	
	
	
	
	
	
</body>
</html>