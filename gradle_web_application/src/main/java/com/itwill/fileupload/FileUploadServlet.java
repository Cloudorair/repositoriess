package com.itwill.fileupload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Collections;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * 파일 업로드 HTTP 요청을 처리하는 서블릿
 * 
 * @fileSizeThreshold fileUpload 시에 메모리에 저장되는 임시 파일 크기를 정의 [자료형 : int]
 * @location 파일 업로드 시에 임시 저장 디렉터리를 지정한다. [자료형 : String]
 * @maxFileSize 업로드할 파일의 치대 크기를 지정한다. [자료형 : long]
 * @maxRequestSize request 시에 최대 크기를 지정한다. [자료형 : long]
 */
@MultipartConfig( fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 100, maxRequestSize = 1024 * 1024 * 100)
@WebServlet("/upload")
public class FileUploadServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("file_upload_form.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*******파일이아닌파라메타데이타**********/
		String name=request.getParameter("name");
		String title=request.getParameter("title");
		System.out.println("name :"+name);
		System.out.println("title:"+title);
		
		/**설정 파일에서 업로드 디렉토리 경로를 로드*
		Properties properties = new Properties();
		InputStream input = FileVO.class.getClassLoader().getResourceAsStream("upload.properties");
		properties.load(input);
		String directoryPath = properties.getProperty("UPLOAD_DIRECTORY");
		*/
		//String directoryPath="C:/file/upload";
		String directoryPath=getServletContext().getRealPath("image");
		/**파일을 upload 할 directory 생성*/
		File uploadDirectory = new File(directoryPath);
		if (!uploadDirectory.exists()) {
			uploadDirectory.mkdirs();
		}
		/*******파일인 파라메타데이타**********/
		Collection<Part> fileParts = request.getParts();
		
		for (Part filePart : fileParts) {
			System.out.println(">>>>>>>> filePart.getName(): "+filePart.getName());
			System.out.println(">>>>>>>> filePart.getSize(): "+filePart.getSize());
			if(!filePart.getName().equals("uploadfile") || filePart.getSize()==0) continue;
			//파일 원본 이름, 확장자, 저장 이름 추출
			String originalName = filePart.getSubmittedFileName();
			String extension = FilenameUtils.getExtension(originalName);
			String savedName = UUID.randomUUID() + "_" + originalName;
			File uploadFile = new File(uploadDirectory, savedName);
			//파일 저장
			//클라이언트업로드파일입력파일스트림
			InputStream inputStream = filePart.getInputStream();
			//서버저장파일출력스트림
			OutputStream outputStream = Files.newOutputStream(uploadFile.toPath());
			inputStream.transferTo(outputStream);
			if (uploadFile != null) {
				// 파일 정보를 DB에 저장
				FileDao fileDAO = new FileDao();
				FileVO fileVo = FileVO.builder()
								.originalName(originalName)
								.savedName(savedName)
								.savedPath(uploadFile.getAbsolutePath())
								.ext(extension)
								.size(filePart.getSize())
								.build();
				fileDAO.insertFile(fileVo);
			}
		}
		response.sendRedirect("/file_upload_list.jsp");
	}

}
