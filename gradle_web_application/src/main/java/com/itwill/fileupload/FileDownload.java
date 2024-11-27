package com.itwill.fileupload;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Properties;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/fileDownload")
public class FileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 파라미터로 넘어오는 filename 구하기
		String fileName = request.getParameter("filename");
		/**설정 파일에서 업로드 디렉토리 경로를 로드*/
		Properties properties = new Properties();
		InputStream input = FileVO.class.getClassLoader().getResourceAsStream("upload.properties");
		properties.load(input);
		String directoryPath = properties.getProperty("UPLOAD_DIRECTORY");
		/**파일을 upload 할 directory 생성*/
		File downloadDirectory = new File(directoryPath);
		File downloadFile = new File(downloadDirectory,fileName);
		OutputStream out = null;
		FileInputStream fin = null;
		if(downloadFile.exists()) { // 파일이 있을 때...
			// ContentType 설정
			response.setContentType("application/octet-stream; charset=utf-8"); //파일 다운로드 할 때 octet-stream가 들어가야 함
			
			//response객체의 content-disposition헤더 속성 설정하기
			String headerKey = "Content-Disposition";
			
			// 한글 getFileNameEncoding 메서드 사용
			String downFileName = getFilenameEncoding(fileName, getBrowser(request));
			
			String headerValue = "attachment; filename=\"" + downFileName + "\";";
			response.setHeader(headerKey, headerValue);
			
			getBrowser(request); // 한글이 어떻게 나오는지 검사
			
			try {
				// 출력용 스트림 객체 생성 ==> response객체 이용
				out = response.getOutputStream();
				
				// 파일 입력용 스트림 객체 생성
				fin = new FileInputStream(downloadFile);
				byte[] buffer = new byte[1024 * 100]; // 한번에 100KB씩 읽어오라는 뜻
				
				int len = 0;
				
				// byte배열을 이용하여 파일 내용을 읽어와 출력용 스트림을 이용하여 클라이언트에게 전송한다.
				while((len = fin.read(buffer)) > 0) {
					out.write(buffer, 0, len);
				}
				
			} catch (IOException e) {
				System.out.println("입출력 오류 : " + e.getMessage());
			}finally {
				if(fin!=null)try {fin.close();} catch(Exception e) {}
				if(out!=null)try {out.close();} catch(Exception e) {}
			}
			
		}else { // 파일이 없을 때
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().println("<h3>" + fileName + "파일은 존재하지 않습니다.</h3>");
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	// 사용자의 브라우저 알아내기
	private String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		//System.out.println(header);
		if(header.indexOf("MSIE") > -1) {
			return "MSIE";
		}else if(header.indexOf("Chrome") > -1){
			return "Chrome";
		}else if(header.indexOf("Opera") > -1) {
			return "Opera";
		}else if(header.indexOf("Trident/7.0") > -1) { // IE 11이상
			return "MSIE";
		}
		
		
		return "Firefox";
	}
	
	// 브라우저별로 한글 파일명을 인코딩하는 메서드
	private String getFilenameEncoding(String filename, String browser) {
		String encodedFilename = null;
		try {
			if(browser.equals("MSIE")) {
				encodedFilename = URLEncoder.encode(filename, "utf-8").replaceAll("\\+", "%20");
			}else if(browser.equals("Firefox")) {
				encodedFilename = "\"" + new String(filename.getBytes("utf-8"), "8859_1") + "\"";
			}else if(browser.equals("Opera")) {
				encodedFilename = "\"" + new String(filename.getBytes("utf-8"), "8859_1") + "\"";
			}else if(browser.equals("Chrome")) {
				StringBuffer sb = new StringBuffer();
				
				for(int i = 0; i <filename.length(); i++) {
					char c = filename.charAt(i);
					
					if(c> '-') {
						sb.append(URLEncoder.encode(""+c, "utf-8"));
					}else {
						sb.append(c);
					}
				}
				
				encodedFilename = sb.toString();
			}else {
				throw new RuntimeException("지원하지 않는 브라우저입니다.");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return encodedFilename;
	}

}