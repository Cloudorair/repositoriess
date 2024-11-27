<%@page import="java.io.InputStream"%>
<%@page import="java.util.Properties"%>
<%@page import="java.util.ArrayList"%>

<%@page import="com.itwill.fileupload.FileVO"%>
<%@page import="java.io.File"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Properties"%>
<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
	     pageEncoding="UTF-8"%>
<%
// 저장될 폴더가 없으면 새로 생성한다.
Properties properties = new Properties();
InputStream input = FileVO.class.getClassLoader().getResourceAsStream("upload.properties");
properties.load(input);
String fileUploadPath = properties.getProperty("UPLOAD_DIRECTORY");
File fileUploadDir = new File(fileUploadPath);
if (!fileUploadDir.exists()) {
	fileUploadDir.mkdirs();
}
// 파일이 저장된 폴더에서 전체 파일목록을 구해와서 List에 담아준다.
File[] allFiles = fileUploadDir.listFiles();
List<FileVO> fileList = new ArrayList<FileVO>();

for (File file : allFiles) {
	if (file.isFile()) {
		FileVO fileVO = new FileVO();
		fileVO.setOriginalName(file.getName());
		fileVO.setSavedPath(file.getAbsolutePath());
		fileVO.setSize((int) Math.ceil(file.length() / 1024.0));
		fileList.add(fileVO);
	}
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servlet File Upload</title>
</head>
<body>
	<%

	%>

	<h2>전체 업로드 파일 목록</h2>


	<table border='1'>
		<tr>
			<td>파일이름</td>
			<td>파일경로</td>
			<td>파일크기</td>
			<td>업로드 상태</td>
			<td>비고</td>
		</tr>

		<%
		if (fileList != null) {
			for (FileVO fileVo : fileList) {
		%>
		<tr>
			<td><%=fileVo.getOriginalName()%></td>
			<td><%=fileVo.getSavedPath()%></td>
			<td><%=fileVo.getSize()%></td>
			<td><%=fileVo.getId()%></td>
			<td><a
				href="<%=request.getContextPath()%>/fileDownload?filename=<%=fileVo.getOriginalName()%>">Download</a></td>
		</tr>
		<%
		}
		}
		%>

	</table>
	<br>
	<hr>
	<br>
	<a href="<%=request.getContextPath()%>/file_upload_form.jsp">파일
		업로드 시작문서로 가기</a>
</body>
</html>