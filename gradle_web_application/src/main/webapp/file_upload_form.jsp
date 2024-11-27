<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>파일첨부 테스트</title>
    </head>
    <body>
    <br>
 
    <form action="/upload" method="post" 
    	enctype="multipart/form-data">
		제목:<input type="text" name="title"><br>
		이름:<input type="text" name="name"><br>
		첨부파일1<input type="file" name="uploadfile" /><br>
		첨부파일2<input type="file" name="uploadfile" /><br>
		<input type="submit" value="저장" />
    </form>

    </body>
</html>