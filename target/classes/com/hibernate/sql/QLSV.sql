USE master
GO

DROP DATABASE QLSV
GO

CREATE DATABASE QLSV
GO

USE QLSV
GO

CREATE TABLE SinhVien (
    _maSinhVien BIGINT IDENTITY(1,1),
    _mssv CHAR(10),
    _hoTen NVARCHAR(100),
    _gioiTinh NVARCHAR(3),
    _cmnd CHAR(9),
    _maLop BIGINT,

    CONSTRAINT PK_SinhVien PRIMARY KEY(_maSinhVien)
)
GO

CREATE TABLE LopHoc (
    _maLop BIGINT IDENTITY(1,1),
    _tenLop VARCHAR(10),

    CONSTRAINT PK_LopHoc PRIMARY KEY(_maLop)
)
GO

CREATE TABLE MonHoc (
    _maMonHoc BIGINT IDENTITY(1,1),
    _tenMonHoc NVARCHAR(100),

    CONSTRAINT PK_MonHoc PRIMARY KEY(_maMonHoc)
)
GO

CREATE TABLE MonHoc_LopHoc (
    _maMonHoc_LopHoc BIGINT IDENTITY(1,1),
    _maLop BIGINT,
    _maMonHoc BIGINT,
    _phongHoc VARCHAR(10),

    CONSTRAINT PK_MonHoc_LopHoc PRIMARY KEY(_maMonHoc_LopHoc)
)
GO

CREATE TABLE SinhVien_MonHoc (
    _maSinhVien_MonHoc BIGINT IDENTITY(1,1),
    _maMonHoc_LopHoc BIGINT,
    _maSinhVien BIGINT,
    _diemCC FLOAT,
    _diemGK FLOAT,
    _diemCK FLOAT,
    _diemTong FLOAT,

    CONSTRAINT PK_SinhVien_MonHoc PRIMARY KEY(_maSinhVien_MonHoc)
)
GO

ALTER TABLE SinhVien ADD CONSTRAINT FK_SinhVien_LopHoc FOREIGN KEY (_maLop) REFERENCES LopHoc(_maLop)
ALTER TABLE MonHoc_LopHoc ADD CONSTRAINT FK_MonHocLopHoc_LopHoc FOREIGN KEY (_maLop) REFERENCES LopHoc(_maLop)
ALTER TABLE MonHoc_LopHoc ADD CONSTRAINT FK_MonHocLopHoc_MonHoc FOREIGN KEY (_maMonHoc) REFERENCES MonHoc(_maMonHoc)
ALTER TABLE SinhVien_MonHoc ADD CONSTRAINT FK_SinhVienMonHoc_MonHocLopHoc FOREIGN KEY (_maMonHoc_LopHoc) REFERENCES MonHoc_LopHoc(_maMonHoc_LopHoc)
ALTER TABLE SinhVien_MonHoc ADD CONSTRAINT FK_SinhVienMonHoc_SinhVien FOREIGN KEY (_maSinhVien) REFERENCES SinhVien(_maSinhVien)
GO

insert SinhVien_MonHoc (_maMonHoc_LopHoc, _maSinhVien) VALUES (1,1)

SELECT * FROM MonHoc
SELECT * from LopHoc
select * from SinhVien
select * from MonHoc_LopHoc
select * from SinhVien_MonHoc


select 
    mh._maMonHoc, mh._tenMonHoc
from 
    SinhVien sv, SinhVien_MonHoc svmh, MonHoc_LopHoc mhlh, MonHoc mh
where 
    sv._mssv = '18120201' and svmh._maSinhVien = sv._maSinhVien
    and svmh._maMonHoc_LopHoc = mhlh._maMonHoc_LopHoc
    and mhlh._maMonHoc = mh._maMonHoc
