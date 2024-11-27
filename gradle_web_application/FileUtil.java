package com.itwill.fileupload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class FileUtil {
	/**
	 * 설정 파일에서 업로드 디렉토리 경로를 로드
	 *
	 * @return 업로드 디렉토리 경로
	 */
	public static String getSavedFileDirectoryPath() {
		Properties properties = new Properties();
		try (InputStream input = FileUtil.class.getClassLoader().getResourceAsStream("config.properties")) {
			properties.load(input);
			return properties.getProperty("UPLOAD_DIRECTORY");
		} catch (IOException ex) {
			ex.printStackTrace();
			return null; // 또는 기본값을 반환
		}
	}

	/**
	 * 업로드 디렉토리가 존재하는지 확인하고, 존재하지 않으면 생성
	 *
	 * @return 파일 업로드 디렉토리
	 */
	public static File getUploadDirectory() {
		String directoryPath = getSavedFileDirectoryPath();
		// 파일을 upload 할 directory 생성
		File uploadDirectory = new File(directoryPath);
		if (!uploadDirectory.exists()) {
			uploadDirectory.mkdirs();
		}
		return uploadDirectory;
	}

	public static FileVO handleUploadFile(HttpServletRequest request, File uploadDir)
			throws IOException, ServletException {
		// 파일 처리
		Part filePart = request.getPart("upload_file");
		return saveFileAndGetVO(filePart, uploadDir);
	}

	private static FileVO saveFileAndGetVO(Part part, File uploadDir) throws IOException {
		// 파일 원본 이름, 확장자, 저장 이름 추출
		String originalName = part.getSubmittedFileName();
		String extension = FilenameUtils.getExtension(originalName);
		String savedName = UUID.randomUUID() + "_" + originalName;
		File file = new File(uploadDir, savedName);
		// 파일 저장
		try (InputStream inputStream = part.getInputStream();
				OutputStream outputStream = Files.newOutputStream(file.toPath())) {
			inputStream.transferTo(outputStream);
		}
		// 파일 메타데이터 반환
		return FileVO.builder().originalName(originalName)
								.savedName(savedName)
								.savedPath(file.getAbsolutePath())
								.ext(extension)
								.size(part.getSize())
								.build();
	}
}