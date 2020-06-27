USE master
GO

DROP DATABASE IF EXISTS  QLSV
GO

CREATE DATABASE QLSV
GO

USE QLSV
GO

CREATE TABLE SinhVien (
    _maSinhVien BIGINT IDENTITY(1,1),
    _mssv CHAR(10) UNIQUE,
    _hoTen NVARCHAR(100),
    _gioiTinh NVARCHAR(3),
    _cmnd CHAR(9) UNIQUE,
    _maLop BIGINT,

    CONSTRAINT PK_SinhVien PRIMARY KEY(_maSinhVien),
    CONSTRAINT Check_GioiTinh CHECK (_gioiTinh in ('Nam', 'nam', N'Nữ', N'nữ'))
)
GO

CREATE TABLE LopHoc (
    _maLop BIGINT IDENTITY(1,1),
    _tenLop VARCHAR(10) UNIQUE,

    CONSTRAINT PK_LopHoc PRIMARY KEY(_maLop)
)
GO

CREATE TABLE MonHoc (
    _maMonHoc BIGINT IDENTITY(1,1),
    _tenMonHoc NVARCHAR(100) UNIQUE,

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

insert LopHoc (_tenLop) values ('18CTT1')
insert LopHoc (_tenLop) values ('18CTT2')
insert LopHoc (_tenLop) values ('18CTT3')
go

insert SinhVien (_mssv, _hoTen, _gioiTinh, _cmnd, _maLop) values ('18120201', N'Nguyễn Bảo Long', 'Nam', '241845617', 1)
insert SinhVien (_mssv, _hoTen, _gioiTinh, _cmnd, _maLop) values ('18120211', N'Võ Thế Minh', 'Nam', '241845618', 1)
insert SinhVien (_mssv, _hoTen, _gioiTinh, _cmnd, _maLop) values ('18120227', N'Phạm Văn Minh Phương', 'Nam', '241845619', 2)
insert SinhVien (_mssv, _hoTen, _gioiTinh, _cmnd, _maLop) values ('18120662', N'Trà Anh Toàn', 'Nam', '241845620', 2)
insert SinhVien (_mssv, _hoTen, _gioiTinh, _cmnd, _maLop) values ('10000000', N'Đặng Thị Hồng Suyên', N'Nữ', '241845621', 3)
go

insert MonHoc (_tenMonHoc) values (N'Lập trình hướng đối tượng')
insert MonHoc (_tenMonHoc) values (N'Cơ sở dữ liệu')

-- 18ctt1 - oop - e1
insert MonHoc_LopHoc (_maLop, _maMonHoc, _phongHoc) values (1,1,'E1')
-- 18ctt1 - db - e2
insert MonHoc_LopHoc (_maLop, _maMonHoc, _phongHoc) values (1,2,'E2')
-- 18ctt2 - oop - e3
insert MonHoc_LopHoc (_maLop, _maMonHoc, _phongHoc) values (2,1,'E3')
-- 18ctt2 - db - e4
insert MonHoc_LopHoc (_maLop, _maMonHoc, _phongHoc) values (2,2,'E4')
-- 18ctt3 - oop - e5
insert MonHoc_LopHoc (_maLop, _maMonHoc, _phongHoc) values (3,1,'E5')
-- 18ctt3 - db - e6
insert MonHoc_LopHoc (_maLop, _maMonHoc, _phongHoc) values (3,2,'E6')
go

-- sv 1 - 18ctt1 - oop - e1 - mark
insert SinhVien_MonHoc (_maSinhVien, _maMonHoc_LopHoc, _diemCC, _diemCK, _diemGK, _diemTong) values (1,1,10,1,10,1)
-- sv 1 - 18ctt2 - db - e4 - mark - optional
insert SinhVien_MonHoc (_maSinhVien, _maMonHoc_LopHoc, _diemCC, _diemCK, _diemGK, _diemTong) values (1,4,9,2,9,2)
-- sv 2 - 18ctt1 - oop - e1 - mark
insert SinhVien_MonHoc (_maSinhVien, _maMonHoc_LopHoc, _diemCC, _diemCK, _diemGK, _diemTong) values (2,1,8,3,8,3)
-- sv 2 - 18ctt3 - db - e6 - mark - optional
insert SinhVien_MonHoc (_maSinhVien, _maMonHoc_LopHoc, _diemCC, _diemCK, _diemGK, _diemTong) values (2,6,7,4,6,4)
-- sv 3 - 18ctt2 - oop - e3 - mark
insert SinhVien_MonHoc (_maSinhVien, _maMonHoc_LopHoc, _diemCC, _diemCK, _diemGK, _diemTong) values (3,3,6,5,7,5)
-- sv 3 - 18ctt1 - db - e2 - mark - optional
insert SinhVien_MonHoc (_maSinhVien, _maMonHoc_LopHoc, _diemCC, _diemCK, _diemGK, _diemTong) values (3,2,5,6,5,6)
-- sv 4 - 18ctt2 - db - e4 - mark
insert SinhVien_MonHoc (_maSinhVien, _maMonHoc_LopHoc, _diemCC, _diemCK, _diemGK, _diemTong) values (4,3,4,7,4,7)
-- sv 4 - 18ctt3 - db - e6 - mark - optional
insert SinhVien_MonHoc (_maSinhVien, _maMonHoc_LopHoc, _diemCC, _diemCK, _diemGK, _diemTong) values (4,6,3,8,3,8)
-- sv 5 - 18ctt3 - oop - e5 - mark
insert SinhVien_MonHoc (_maSinhVien, _maMonHoc_LopHoc, _diemCC, _diemCK, _diemGK, _diemTong) values (5,5,2,9,2,9)
-- sv 5 - 18ctt1 - db - e2 - mark - optional 
insert SinhVien_MonHoc (_maSinhVien, _maMonHoc_LopHoc, _diemCC, _diemCK, _diemGK, _diemTong) values (5,2,1,10,1,10)
go

-- _mssv CHAR(10) UNIQUE,
-- _hoTen NVARCHAR(100),
-- _gioiTinh NVARCHAR(3),
-- _cmnd CHAR(9) UNIQUE,
-- _maLop BIGINT,

-- -- kiểm tra sự tồn tại của 1 lớp
-- -- truyền vào tên lớp
-- -- trả về 1 nếu tồn tại, 0 nếu không tồn tại
-- CREATE PROCEDURE check_exists_class @tenLop VARCHAR(10)
-- AS BEGIN
--     IF (EXISTS (SELECT * FROM LopHoc l WHERE l._tenLop = @tenLop))
--         RETURN 1
--     ELSE
--         RETURN 0
-- END
-- GO

-- yêu cầu 1 - 2: import sinh viên vào 1 lớp
CREATE PROCEDURE Import_SinhVien @mssv CHAR(10), @hoTen NVARCHAR(100), 
                @gioiTinh NVARCHAR(3), @cmnd CHAR(9), @tenLop VARCHAR(10)
AS BEGIN
    IF (EXISTS (SELECT * FROM LopHoc l WHERE l._tenLop = @tenLop)) BEGIN
        DECLARE @maLop BIGINT
        SET @maLop = (
            SELECT l._maLop
            FROM LopHoc l 
            WHERE l._tenLop = @tenLop
        )
        INSERT SinhVien (_mssv, _hoTen, _gioiTinh, _cmnd, _maLop) VALUES (@mssv, @hoTen, @gioiTinh, @cmnd, @maLop)
    END
END
GO

-- yêu cầu 3: import TKB của 1 lớp vào hệ thống
            -- sinh viên thuộc lớp phải xem được tkb
            -- mặc định sinh viên đều học các môn có trong lớp: import sẵn cho sinh viên trong SinhVien_MonHoc luôn
CREATE PROCEDURE Import_TKB @tenMonHoc NVARCHAR(100), @tenLop VARCHAR(10), @phongHoc VARCHAR(10)
AS BEGIN
    -- nếu tồn tại tên lớp và tên môn học thì add vào hệ thống
    -- sau đó set mặc định sinh viên thuộc lớp @tenLop phải học môn đó
    IF (EXISTS(SELECT * FROM LopHoc l WHERE l._tenLop = @tenLop) 
        AND EXISTS(SELECT * FROM MonHoc mh WHERE mh._tenMonHoc = @tenMonHoc)) 
    BEGIN
        -- add môn học vào hệ thống
        DECLARE @maLop BIGINT, @maMonHoc BIGINT
        SET @maLop = (
            SELECT lh._maLop
            FROM LopHoc lh
            WHERE lh._tenLop = @tenLop
        )
        SET @maMonHoc = (
            SELECT mh._maMonHoc
            FROM MonHoc mh 
            WHERE mh._tenMonHoc = @tenMonHoc
        )
        INSERT MonHoc_LopHoc (_maLop, _maMonHoc, _phongHoc) VALUES (@maLop, @maMonHoc, @phongHoc)

        -- sinh viên thuộc @tenLop phải học môn học vừa được add vào 
        
    END
END
GO

-- yêu cầu 5: xem danh sách lớp
CREATE PROCEDURE XemDanhSachLop @tenLop VARCHAR(10)
AS BEGIN
    SELECT sv._mssv, sv._hoTen, sv._gioiTinh, sv._cmnd
    FROM SinhVien sv, LopHoc lh
    WHERE sv._maLop = lh._maLop 
        AND lh._tenLop = @tenLop
END
GO

-- yêu cầu 6.1: xem TKB phía sinh viên
CREATE PROCEDURE XemTKB_SinhVien @mssv CHAR(10)
AS BEGIN
    SELECT mh._tenMonHoc, mh_lh._phongHoc
    FROM MonHoc mh, MonHoc_LopHoc mh_lh, SinhVien_MonHoc sv_mh, SinhVien sv
    WHERE sv_mh._maSinhVien = sv._maSinhVien AND sv._mssv = @mssv
        AND sv_mh._maMonHoc_LopHoc = mh_lh._maMonHoc_LopHoc
        AND mh_lh._maMonHoc = mh._maMonHoc
END
GO

-- yêu cầu 6.2: xem TKB theo lớp của giáo vụ
CREATE PROCEDURE XemTKB_LopHoc @tenLop VARCHAR(10)
AS BEGIN
    SELECT mh._tenMonHoc, mh_lh._phongHoc
    FROM MonHoc mh, MonHoc_LopHoc mh_lh, LopHoc lh
    WHERE mh._maMonHoc = mh_lh._maMonHoc
        AND lh._maLop = mh_lh._maLop
        AND lh._tenLop = @tenLop
END
GO

-- yêu cầu 8: Xem bảng điểm theo môn học cho giáo vụ
CREATE PROCEDURE XemDiem_GiaoVu @tenMonHoc NVARCHAR(100)
AS BEGIN
    SELECT sv._mssv, sv._hoTen, mh._tenMonHoc, sv_mh._diemCC, sv_mh._diemGK, sv_mh._diemCK, sv_mh._diemTong
    FROM SinhVien_MonHoc sv_mh, SinhVien sv, MonHoc mh, MonHoc_LopHoc mh_lh
    WHERE sv._maSinhVien = sv_mh._maSinhVien 
        AND sv_mh._maMonHoc_LopHoc = mh_lh._maMonHoc_LopHoc
        AND mh_lh._maMonHoc = mh._maMonHoc AND mh._tenMonHoc = @tenMonHoc
END
GO

-- yêu cầu 10: Xem bảng điểm cho sinh viên
CREATE PROCEDURE XemDiem_SinhVien @mssv CHAR(10)
AS BEGIN
    SELECT mh._tenMonHoc, sv_mh._diemCC, sv_mh._diemGK, sv_mh._diemCK, sv_mh._diemTong
    FROM SinhVien_MonHoc sv_mh, SinhVien sv, MonHoc mh, MonHoc_LopHoc mh_lh
    WHERE sv._mssv = @mssv AND sv._maSinhVien = sv_mh._maSinhVien 
        AND sv_mh._maMonHoc_LopHoc = mh_lh._maMonHoc_LopHoc
        AND mh_lh._maMonHoc = mh._maMonHoc
END
GO

SELECT * FROM MonHoc
SELECT * from LopHoc
select * from SinhVien
select * from MonHoc_LopHoc
select * from SinhVien_MonHoc


-- select 
--     mh._maMonHoc, mh._tenMonHoc
-- from 
--     SinhVien sv, SinhVien_MonHoc svmh, MonHoc_LopHoc mhlh, MonHoc mh
-- where 
--     sv._mssv = '18120201' and svmh._maSinhVien = sv._maSinhVien
--     and svmh._maMonHoc_LopHoc = mhlh._maMonHoc_LopHoc
--     and mhlh._maMonHoc = mh._maMonHoc

-- delete from MonHoc_LopHoc where _maMonHoc_LopHoc = 8