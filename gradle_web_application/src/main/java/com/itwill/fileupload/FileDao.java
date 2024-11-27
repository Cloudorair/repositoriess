package com.itwill.fileupload;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 파일 관련 데이터 액세스 객체
 * - 파일 데이터와 관련된 데이터베이스 연산 수행
 */
public class FileDao {
    /**
     * 업로드한 파일을 데이터베이스에 저장
     *
     * @param file 저장할 파일
     */
    public void insertFile(FileVO file){
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
           
            String sql = "INSERT INTO tb_file (" +
                    "original_name, saved_name, saved_path, ext, size)" +
                    "VALUES (?, ?, ?, ?, ?);";
           System.out.println("upload file 정보          :"+ file);
           System.out.println("upload file db insert sql :"+sql);
           
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
       
        }
    }
}