USE master
GO

DROP DATABASE QLSV
GO

CREATE DATABASE QLSV
GO

USE QLSV
GO

CREATE TABLE sinh_vien (
    maSinhVien BIGINT,
    mssv CHAR(20),
    hoTen NVARCHAR(100),
    gioiTinh NCHAR(3),
    cmnd INT,
    maLop BIGINT,

    CONSTRAINT pk_sinhVien PRIMARY KEY(maSinhVien),
    CONSTRAINT checkGioiTinh CHECK (gioiTinh IN ('Nam', 'nam', N'Nữ', N'nữ'))
)
GO

CREATE TABLE mon_hoc (
    maMonHoc BIGINT,
    tenMonHoc NVARCHAR(100),

    CONSTRAINT pk_monHoc PRIMARY KEY (maMonHoc)
)
GO

CREATE TABLE lop_hoc (
    maLop BIGINT,
    tenLop VARCHAR(20),

    CONSTRAINT pk_lopHoc PRIMARY KEY (maLop)
)
GO

CREATE TABLE monHoc_lopHoc (
    maMonHocLopHoc BIGINT,
    maMonHoc BIGINT,
    maLop BIGINT,
    phongHoc VARCHAR(20),

    CONSTRAINT pk_monHoc_lopHoc PRIMARY KEY (maMonHocLopHoc)
)
GO

CREATE TABLE sinhVien_monHoc (
    maSinhVienMonHoc BIGINT,
    maMonHocLopHoc BIGINT,
    maSinhVien BIGINT,
    diemGK FLOAT,
    diemCK FLOAT,
    diemChuyenCan FLOAT,
    diemTong FLOAT,

    CONSTRAINT pk_sinhVien_monHoc PRIMARY KEY (maSinhVienMonHoc)
)
GO

ALTER TABLE monHoc_lopHoc ADD CONSTRAINT fk_monHocLopHoc_lopHoc FOREIGN KEY (maLop) REFERENCES lop_hoc(maLop)
ALTER TABLE monHoc_lopHoc ADD CONSTRAINT fk_monHocLopHoc_monHoc FOREIGN KEY (maMonHoc) REFERENCES mon_hoc(maMonHoc)
ALTER TABLE sinh_vien ADD CONSTRAINT fk_sinhVien_lopHoc FOREIGN KEY (maLop) REFERENCES lop_hoc(maLop)
ALTER TABLE sinhVien_monHoc ADD CONSTRAINT fk_sinhVienMonHoc_monHocLopHoc FOREIGN KEY (maMonHocLopHoc) REFERENCES monHoc_lopHoc(maMonHocLopHoc)
ALTER TABLE sinhVien_monHoc ADD CONSTRAINT fk_sinhVienHocMonHoc_sinhVien FOREIGN KEY (maSinhVien) REFERENCES sinh_vien(maSinhVien)
GO




