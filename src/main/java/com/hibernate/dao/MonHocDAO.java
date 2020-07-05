package com.hibernate.dao;

import java.sql.*;

import com.hibernate.util.HibernateUtil;

public class MonHocDAO {
    // ghi môn học xuống db 
    public static void addMonHoc(String tenMonHoc, String maMonHoc) {
        Connection conn = null;
        try {
            conn = HibernateUtil.getConnection();

            // do something here
            // create_monHoc @tenMonHoc NVARCHAR(100), @maMonHoc CHAR(6)
            CallableStatement addMon = conn.prepareCall("{Call create_monHoc(?, ?)}");
            addMon.setString(1, tenMonHoc);
            addMon.setString(2, maMonHoc);
            addMon.execute();
        }
        catch (SQLException se) {
            System.err.println("Lỗi ở hàm addMonHoc(String tenMonHoc, String maMonHoc) file MonHocDAO");
            do {
                System.out.println("MESSAGE: " + se.getMessage());
                System.out.println();
                se = se.getNextException();
            }
            while (se != null);
        }
    }

    // // test nhẹ,, mà có lone gì đâu mà test :> 
    // public static void main(String[] args) {
    //     addMonHoc("Lập trình hướng đối tượng", "CTT001");
    //     addMonHoc("Cơ sở dữ liệu", "CTT002");
    // }
}