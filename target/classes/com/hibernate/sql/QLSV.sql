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
    _mssv CHAR(10) UNIQUE NOT NULL,
    _hoTen NVARCHAR(100),
    _gioiTinh NVARCHAR(3),
    _cmnd CHAR(9) UNIQUE,
    _maLop BIGINT NOT NULL,

    CONSTRAINT PK_SinhVien PRIMARY KEY(_maSinhVien),
    CONSTRAINT Check_GioiTinh CHECK (_gioiTinh in ('Nam', 'nam', N'Nữ', N'nữ'))
)
GO

CREATE TABLE LopHoc (
    _maLop BIGINT IDENTITY(1,1),
    _tenLop VARCHAR(10) UNIQUE NOT NULL,

    CONSTRAINT PK_LopHoc PRIMARY KEY(_maLop)
)
GO

CREATE TABLE MonHoc (
    _maMonHoc BIGINT IDENTITY(1,1),
    _maMonCuaTruong CHAR(6) UNIQUE NOT NULL,
    _tenMonHoc NVARCHAR(100) UNIQUE NOT NULL,

    CONSTRAINT PK_MonHoc PRIMARY KEY(_maMonHoc)
)
GO

CREATE TABLE MonHoc_LopHoc (
    _maMonHoc_LopHoc BIGINT IDENTITY(1,1),
    _maLop BIGINT NOT NULL,
    _maMonHoc BIGINT NOT NULL,
    _phongHoc VARCHAR(10),

    CONSTRAINT mh_lh UNIQUE (_maLop, _maMonHoc),
    CONSTRAINT PK_MonHoc_LopHoc PRIMARY KEY(_maMonHoc_LopHoc)
)
GO

CREATE TABLE SinhVien_MonHoc (
    _maSinhVien_MonHoc BIGINT IDENTITY(1,1),
    _maMonHoc_LopHoc BIGINT NOT NULL,
    _maSinhVien BIGINT NOT NULL,
    _diemCC FLOAT,
    _diemGK FLOAT,
    _diemCK FLOAT,
    _diemTong FLOAT,

    CONSTRAINT sinhVien_MonHoc_LopHoc UNIQUE (_maSinhVien, _maMonHoc_LopHoc),
    CONSTRAINT PK_SinhVien_MonHoc PRIMARY KEY(_maSinhVien_MonHoc)
)
GO

ALTER TABLE SinhVien ADD CONSTRAINT FK_SinhVien_LopHoc FOREIGN KEY (_maLop) REFERENCES LopHoc(_maLop)
ALTER TABLE MonHoc_LopHoc ADD CONSTRAINT FK_MonHocLopHoc_LopHoc FOREIGN KEY (_maLop) REFERENCES LopHoc(_maLop)
ALTER TABLE MonHoc_LopHoc ADD CONSTRAINT FK_MonHocLopHoc_MonHoc FOREIGN KEY (_maMonHoc) REFERENCES MonHoc(_maMonHoc)
ALTER TABLE SinhVien_MonHoc ADD CONSTRAINT FK_SinhVienMonHoc_MonHocLopHoc FOREIGN KEY (_maMonHoc_LopHoc) REFERENCES MonHoc_LopHoc(_maMonHoc_LopHoc)
ALTER TABLE SinhVien_MonHoc ADD CONSTRAINT FK_SinhVienMonHoc_SinhVien FOREIGN KEY (_maSinhVien) REFERENCES SinhVien(_maSinhVien)
GO

-- kiểm tra 1 lớp học đã tồn tại
CREATE PROCEDURE check_exists_lopHoc @tenLop VARCHAR(10)
AS BEGIN 
    IF (EXISTS (SELECT * FROM LopHoc lh WHERE lh._tenLop = @tenLop))
        RETURN 1
    RETURN 0
END
GO

-- kiểm tra 1 sinh viên đã tồn tại
CREATE PROCEDURE check_exists_sinhVien @mssv CHAR(10)
AS BEGIN 
    IF (EXISTS (SELECT * FROM SinhVien sv WHERE sv._mssv = @mssv))
        RETURN 1
    RETURN 0
END
GO

-- kiểm tra 1 môn đã tồn tại
CREATE PROCEDURE check_exists_monHoc @tenMonHoc NVARCHAR(100)
AS BEGIN 
    IF (EXISTS (SELECT * FROM MonHoc mh WHERE mh._tenMonHoc = @tenMonHoc))
        RETURN 1
    RETURN 0
END
GO

-- tạo môn học từ tên môn học mà mã môn học do trường quy đinh
-- khi dùng phải kiểm tra trước, xem môn học có tồn tại hay không
CREATE PROCEDURE create_monHoc @tenMonHoc NVARCHAR(100), @maMonHoc CHAR(6)
AS BEGIN
    DECLARE @bool INT
    EXEC @bool = check_exists_monHoc @tenMonHoc
    IF (@bool = 0)
        INSERT MonHoc (_maMonCuaTruong, _tenMonHoc) VALUES (@maMonHoc, @tenMonHoc)
END
GO

-- tạo lớp học từ tên lớp được truyền vào
-- khi dùng phải kiểm tra trước, xem lớp học có tồn tại hay không
CREATE PROCEDURE create_lopHoc @tenLop VARCHAR(10)
AS BEGIN
    DECLARE @bool INT
    EXEC @bool = check_exists_lopHoc @tenLop
    IF (@bool = 0)
        INSERT LopHoc (_tenLop) VALUES (@tenLop)
END
GO

-- yêu cầu 1 - 2: import sinh viên vào 1 lớp
-- khi dùng phải kiểm tra trước, xem lớp học có tồn tại hay không
CREATE PROCEDURE Import_SinhVien @mssv CHAR(10), @hoTen NVARCHAR(100), @gioiTinh NVARCHAR(3), @cmnd CHAR(9), @tenLop VARCHAR(10)
AS BEGIN
    DECLARE @maLop BIGINT
    SET @maLop = (
        SELECT l._maLop
        FROM LopHoc l 
        WHERE l._tenLop = @tenLop
    )
    INSERT SinhVien (_mssv, _hoTen, _gioiTinh, _cmnd, _maLop) VALUES (@mssv, @hoTen, @gioiTinh, @cmnd, @maLop)

    -- sinh viên thuộc @maLop phải học môn học của lớp 
    -- khai báo con trỏ trỏ đến _maMonHoc_LopHoc của MonHoc_LopHoc (which has _maLopHoc = @maLop)
    DECLARE pointerMonHoc_LopHoc CURSOR FOR (
        SELECT mh_lh._maMonHoc_LopHoc
        FROM MonHoc_LopHoc mh_lh
        WHERE mh_lh._maLop = @maLop
    )

    -- khai báo biến chứa _maMonHoc_LopHoc 
    DECLARE @maMonHoc_LopHoc BIGINT

    -- mở con trỏ để làm việc 
    OPEN pointerMonHoc_LopHoc

    -- đọc vào 1 dòng
    FETCH NEXT FROM pointerMonHoc_LopHoc INTO @maMonHoc_LopHoc

    -- tìm lại _maSinhVien của sv vừa add vào
    DECLARE @maSinhVien BIGINT
    SET @maSinhVien = (
        SELECT sv._maSinhVien
        FROM SinhVien sv 
        WHERE sv._mssv = @mssv
    )

    -- loop
    WHILE @@FETCH_STATUS = 0 BEGIN 
        INSERT SinhVien_MonHoc (_maSinhVien, _maMonHoc_LopHoc) VALUES (@maSinhVien, @maMonHoc_LopHoc)
        -- i++
        FETCH NEXT FROM pointerMonHoc_LopHoc INTO @maMonHoc_LopHoc
    END

    -- đóng con trỏ + giải phóng
    CLOSE pointerMonHoc_LopHoc
    DEALLOCATE pointerMonHoc_LopHoc
END
GO

-- EXEC Import_SinhVien '18120201', N'Nguyễn Bảo Long', 'Nam', '241845617', '18CTT2'
-- GO

-- yêu cầu 3: import TKB của 1 lớp vào hệ thống
            -- sinh viên thuộc lớp phải xem được tkb (nằm ở yêu cầu 6 và đã xong)
            -- mặc định sinh viên đều học các môn có trong lớp: import sẵn cho sinh viên trong SinhVien_MonHoc luôn
-- khi dùng phải kiểm tra trước, xem môn học, lớp học có tồn tại hay không
CREATE PROCEDURE Import_TKB @tenMonHoc NVARCHAR(100), @tenLop VARCHAR(10), @phongHoc VARCHAR(10)
AS BEGIN
    -- nếu tồn tại tên lớp và tên môn học thì add vào hệ thống
    -- sau đó set mặc định sinh viên thuộc lớp @tenLop phải học môn đó
    -- add lịch học vào hệ thống bằng cách insert data vào MonHoc_LopHoc
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

    -- tìm lại mã môn học lớp học vừa được add vào để làm yêu cầu bên dưới
    DECLARE @maMonHoc_LopHoc BIGINT
    SET @maMonHoc_LopHoc = (
        SELECT mh_lh._maMonHoc_LopHoc
        FROM MonHoc_LopHoc mh_lh
        WHERE mh_lh._maLop = @maLop
            AND mh_lh._maMonHoc = @maMonHoc
    )

    -- sinh viên thuộc @tenLop phải học môn học vừa được add vào 
    -- khai báo con trỏ trỏ đến _maSinhVien của SinhVien (which has _maLopHoc = @maLop)
    DECLARE pointerSinhVien CURSOR FOR (
        SELECT sv._maSinhVien 
        FROM SinhVien sv 
        WHERE sv._maLop = @maLop
    )

    -- khai báo biến chứa _maSinhVien
    DECLARE @maSinhVien BIGINT

    -- mở con trỏ để làm việc
    OPEN pointerSinhVien

    -- đọc vào 1 dòng 
    FETCH NEXT FROM pointerSinhVien INTO @maSinhVien

    -- loop: chừng nào còn đọc được data thì next con trỏ lên 1 dòng 
    WHILE @@FETCH_STATUS = 0 BEGIN
        INSERT SinhVien_MonHoc (_maSinhVien, _maMonHoc_LopHoc) VALUES (@maSinhVien, @maMonHoc_LopHoc)
        -- i++
        FETCH NEXT FROM pointerSinhVien INTO @maSinhVien
    END

    -- đóng con trỏ và giải phóng nó
    CLOSE pointerSinhVien
    DEALLOCATE pointerSinhVien
END
GO

-- EXEC Import_TKB N'Lập trình hướng đối tượng', '18CTT1', 'E2'
-- EXEC Import_TKB N'Cơ sở dữ liệu', '18CTT2', 'E3'
-- GO

-- kiểm tra xem nếu sinh viên đang học hoặc không học môn học
CREATE PROCEDURE check_SinhVien_MonHoc @mssv CHAR(10), @tenMonHoc NVARCHAR(100)
AS BEGIN 
    IF (EXISTS (
            SELECT * 
            FROM SinhVien sv, SinhVien_MonHoc sv_mh, MonHoc_LopHoc mh_lh, MonHoc mh 
            WHERE sv._mssv = @mssv AND sv._maSinhVien = sv_mh._maSinhVien
                AND sv_mh._maMonHoc_LopHoc = mh_lh._maMonHoc_LopHoc
                AND mh_lh._maMonHoc = mh._maMonHoc
                AND mh._tenMonHoc = @tenMonHoc
        )
    ) BEGIN 
        RETURN 1
    END

    RETURN 0
END
GO

-- yêu cầu 4.1: Sinh viên A xin học môn B
CREATE PROCEDURE DangKyMonHoc @mssv CHAR(10), @tenMonHoc NVARCHAR(100), @tenLop VARCHAR(10)
AS BEGIN
    -- phải tồn tại sinh viên A và môn học B trong hệ thống
    DECLARE @boolSV INT, @boolMonHoc INT, @boolLopHoc INT
    EXEC @boolSV = check_exists_sinhVien @mssv
    EXEC @boolMonHoc = check_exists_monHoc @tenMonHoc
    EXEC @boolLopHoc = check_exists_lopHoc @tenLop
    IF (@boolLopHoc = 1 AND @boolMonHoc = 1 AND @boolSV = 1)
    BEGIN
        -- tìm _maSinhVien của A và _maMonHoc của B
        DECLARE @maSinhVien BIGINT, @maMonHoc BIGINT
        SET @maSinhVien = (
            SELECT sv._maSinhVien
            FROM SinhVien sv
            WHERE sv._mssv = @mssv
        )
        SET @maMonHoc = (
            SELECT mh._maMonHoc
            FROM MonHoc mh 
            WHERE mh._tenMonHoc = @tenMonHoc
        )

        -- Nếu A đang học môn B thì dẹp, ngược lại, insert data để A học môn B
        DECLARE @boolSinhVien_MonHoc INT
        EXEC @boolSinhVien_MonHoc = check_SinhVien_MonHoc @mssv, @tenMonHoc
        IF (@boolSinhVien_MonHoc = 0) BEGIN
            DECLARE @maLop BIGINT
            SET @maLop = (
                SELECT lh._maLop
                FROM LopHoc lh 
                WHERE lh._tenLop = @tenLop
            )

            -- tìm _maMonHoc_LopHoc để add vào bảng SinhVien_MonHoc
            -- có thể hiểu là đang tìm lịch học môn B cho A
            DECLARE @maMonHoc_LopHoc BIGINT
            SET @maMonHoc_LopHoc = (
                SELECT mh_lh._maMonHoc_LopHoc
                FROM MonHoc_LopHoc mh_lh
                WHERE mh_lh._maMonHoc = @maMonHoc
                    AND mh_lh._maLop = @maLop
            )

            INSERT SinhVien_MonHoc (_maSinhVien, _maMonHoc_LopHoc) VALUES (@maSinhVien, @maMonHoc_LopHoc)
        END
        ELSE THROW 50009, N'Sinh viên đang học môn học. Đăng ký thất bại', 1;
    END
    ELSE THROW 50010, N'Không tìm thấy sinh viên/lớp hoc/môn học', 1;
END
GO

-- EXEC DangKyMonHoc '18120201', N'Cơ sở dữ liệu', '18CTT1'
-- GO

-- yêu cầu 4.2: Sinh viên C không học môn D
CREATE PROCEDURE HuyBoMonHoc @mssv CHAR(10), @tenMonHoc NVARCHAR(100)
AS BEGIN
    -- phải tồn tại sinh viên C, môn D trong hệ thống
    -- phải đảm bảo là C đang học môn D. Do đó mới xin thôi học được
    DECLARE @boolSinhVien INT, @boolMonHoc INT
    EXEC @boolSinhVien = check_exists_sinhVien @mssv
    EXEC @boolMonHoc = check_exists_monHoc @tenMonHoc
    IF (@boolSinhVien = 1 AND @boolMonHoc = 1) BEGIN 
        DECLARE @boolSinhVien_MonHoc INT
        EXEC @boolSinhVien_MonHoc = check_SinhVien_MonHoc @mssv, @tenMonHoc
        IF (@boolSinhVien_MonHoc = 1) BEGIN 
            -- tìm ra _maSinhVien_MonHoc: which illustrates that student C joins subject D
            DECLARE @maSinhVien_MonHoc BIGINT
            SET @maSinhVien_MonHoc = (
                SELECT sv_mh._maSinhVien_MonHoc
                FROM SinhVien sv, SinhVien_MonHoc sv_mh, MonHoc_LopHoc mh_lh, MonHoc mh 
                WHERE sv._mssv = @mssv AND sv._maSinhVien = sv_mh._maSinhVien
                    AND sv_mh._maMonHoc_LopHoc = mh_lh._maMonHoc_LopHoc
                    AND mh_lh._maMonHoc = mh._maMonHoc
                    AND mh._tenMonHoc = @tenMonHoc
            )

            DELETE FROM SinhVien_MonHoc WHERE _maSinhVien_MonHoc = @maSinhVien_MonHoc
        END
        ELSE THROW 50011, N'Sinh viên không học môn học nên không huỷ được', 1;
    END
    ELSE THROW 50012, N'Không tìm thấy sinh viên/môn học', 1;
END
GO

-- EXEC HuyBoMonHoc '18120201', N'Cơ sở dữ liệu'
-- GO

-- yêu cầu 5: xem danh sách lớp
CREATE PROCEDURE XemDanhSachLop @tenLop VARCHAR(10)
AS BEGIN
    IF (NOT EXISTS (SELECT * FROM LopHoc lh WHERE lh._tenLop = @tenLop))
        THROW 50013, N'Không tìm thấy lớp học', 1;
    SELECT sv._mssv, sv._hoTen, sv._gioiTinh, sv._cmnd
    FROM SinhVien sv, LopHoc lh
    WHERE sv._maLop = lh._maLop 
        AND lh._tenLop = @tenLop
END
GO

-- EXEC XemDanhSachLop '18CTT2'
-- GO

-- yêu cầu 6.1: xem TKB phía sinh viên
CREATE PROCEDURE XemTKB_SinhVien @mssv CHAR(10)
AS BEGIN
    IF (NOT EXISTS (SELECT * FROM SinhVien sv WHERE sv._mssv = @mssv))
        THROW 50008, N'Không tìm thấy sinh viên', 1;
    SELECT mh._maMonCuaTruong, mh._tenMonHoc, mh_lh._phongHoc
    FROM MonHoc mh, MonHoc_LopHoc mh_lh, SinhVien_MonHoc sv_mh, SinhVien sv
    WHERE sv_mh._maSinhVien = sv._maSinhVien AND sv._mssv = @mssv
        AND sv_mh._maMonHoc_LopHoc = mh_lh._maMonHoc_LopHoc
        AND mh_lh._maMonHoc = mh._maMonHoc
END
GO

-- EXEC XemTKB_SinhVien '18120227'
-- GO

-- yêu cầu 6.2: xem TKB theo lớp của giáo vụ
CREATE PROCEDURE XemTKB_LopHoc @tenLop VARCHAR(10)
AS BEGIN
    IF (NOT EXISTS (SELECT * FROM LopHoc lh WHERE lh._tenLop = @tenLop))
        THROW 50007, N'Không tìm thấy lớp', 1;

    SELECT mh._maMonCuaTruong, mh._tenMonHoc, mh_lh._phongHoc
    FROM MonHoc mh, MonHoc_LopHoc mh_lh, LopHoc lh
    WHERE mh._maMonHoc = mh_lh._maMonHoc
        AND lh._maLop = mh_lh._maLop
        AND lh._tenLop = @tenLop
END
GO

-- EXEC XemTKB_LopHoc '18CTT2'
-- GO

-- yêu cầu 7: import bảng điểm theo lớp + môn học (kèm theo mssv và điểm)
CREATE PROCEDURE ImportBangDiem @tenLop VARCHAR(10), @tenMonHoc NVARCHAR(100), @mssv CHAR(10), @diemCC FLOAT, @diemGK FLOAT, @diemCK FLOAT, @diemTong FLOAT
AS BEGIN
    DECLARE @maSinhVien BIGINT, @maMonHoc_LopHoc BIGINT
    SET @maSinhVien = (
        SELECT sv._maSinhVien
        FROM SinhVien sv 
        WHERE sv._mssv = @mssv
    )
    SET @maMonHoc_LopHoc = (
        SELECT mh_lh._maMonHoc_LopHoc
        FROM MonHoc_LopHoc mh_lh, LopHoc lh, MonHoc mh 
        WHERE mh_lh._maLop = lh._maLop AND lh._tenLop = @tenLop
            AND mh_lh._maMonHoc = mh._maMonHoc AND mh._tenMonHoc = @tenMonHoc
    )

    UPDATE SinhVien_MonHoc 
    SET _diemCC = @diemCC, _diemCK = @diemCK, _diemGK = @diemGK, _diemTong = @diemTong 
    WHERE _maSinhVien = @maSinhVien AND _maMonHoc_LopHoc = @maMonHoc_LopHoc
END
GO

-- EXEC ImportBangDiem '18CTT1', N'Cơ sở dữ liệu', '18120201', 5,5,5,5
-- GO

-- yêu cầu 8: Xem bảng điểm theo môn học cho giáo vụ
CREATE PROCEDURE XemDiem_GiaoVu @tenMonHoc NVARCHAR(100), @tenLop VARCHAR(10)
AS BEGIN
    IF (NOT EXISTS (
        SELECT * 
        FROM LopHoc lh, MonHoc mh, MonHoc_LopHoc mh_lh
        WHERE lh._tenLop = @tenLop AND lh._maLop = mh_lh._maLop
            AND mh._tenMonHoc = @tenMonHoc AND mh._maMonHoc = mh_lh._maMonHoc
    ))
        THROW 50015, N'Không tìm thấy môn học thuộc lớp học', 1
    SELECT sv._mssv, sv._hoTen, sv_mh._diemCC, sv_mh._diemGK, sv_mh._diemCK, sv_mh._diemTong
    FROM SinhVien_MonHoc sv_mh, SinhVien sv, MonHoc mh, MonHoc_LopHoc mh_lh, LopHoc lh
    WHERE sv._maSinhVien = sv_mh._maSinhVien 
        AND sv_mh._maMonHoc_LopHoc = mh_lh._maMonHoc_LopHoc
        AND mh_lh._maMonHoc = mh._maMonHoc AND mh._tenMonHoc = @tenMonHoc
        AND mh_lh._maLop = lh._maLop AND lh._tenLop = @tenLop
END
GO

-- EXEC XemDiem_GiaoVu N'Lập trình hướng đối tượng', '18CTT1'
-- GO

-- yêu cầu 9: Sửa điểm sinh viên 
CREATE PROCEDURE UpdateDiemSinhVien @mssv CHAR(10), @tenMonHoc NVARCHAR(100), @diemCC FLOAT, @diemGK FLOAT, @diemCK FLOAT, @diemTong FLOAT
AS BEGIN 
    -- phải tồn tại sinh viên, môn học trong hệ thống
    -- phải thoả mãn sinh viên đó có học môn học đó 
    DECLARE @boolSinhVien INT, @boolMonHoc INT
    EXEC @boolMonHoc = check_exists_monHoc @tenMonHoc
    EXEC @boolSinhVien = check_exists_sinhVien @mssv
    IF (@boolSinhVien = 1 AND @boolMonHoc = 1) BEGIN 
        DECLARE @boolSinhVien_MonHoc INT 
        EXEC @boolSinhVien_MonHoc = check_SinhVien_MonHoc @mssv, @tenMonHoc
        IF (@boolSinhVien_MonHoc = 1) BEGIN 
            DECLARE @maSinhVien_MonHoc BIGINT
            SET @maSinhVien_MonHoc = (
                SELECT sv_mh._maSinhVien_MonHoc
                FROM SinhVien sv, SinhVien_MonHoc sv_mh, MonHoc_LopHoc mh_lh, MonHoc mh 
                WHERE sv._mssv = @mssv AND sv._maSinhVien = sv_mh._maSinhVien
                    AND sv_mh._maMonHoc_LopHoc = mh_lh._maMonHoc_LopHoc
                    AND mh_lh._maMonHoc = mh._maMonHoc 
                    AND mh._tenMonHoc = @tenMonHoc
            )

            UPDATE SinhVien_MonHoc 
            SET _diemCC = @diemCC, _diemCK = @diemCK, _diemGK = @diemGK, _diemTong = @diemTong 
            WHERE _maSinhVien_MonHoc = @maSinhVien_MonHoc
        END
        ELSE THROW 50005, N'Không tìm thấy sinh viên học lớp học', 1
    END
    ELSE THROW 50006, N'Không tìm thấy sinh viên hay môn học', 1;
END
GO

-- EXEC UpdateDiemSinhVien '18120201', N'Lập trình hướng đối tượng', 5.5, 5.5, 5.5, 0
-- GO

-- yêu cầu 10: Xem bảng điểm cho sinh viên
CREATE PROCEDURE XemDiem_SinhVien @mssv CHAR(10)
AS BEGIN
    IF (NOT EXISTS (SELECT * FROM SinhVien sv WHERE sv._mssv = @mssv))
        THROW 50014, N'Không tìm thấy sinh viên', 1;
    SELECT mh._tenMonHoc, sv_mh._diemCC, sv_mh._diemGK, sv_mh._diemCK, sv_mh._diemTong
    FROM SinhVien_MonHoc sv_mh, SinhVien sv, MonHoc mh, MonHoc_LopHoc mh_lh
    WHERE sv._mssv = @mssv AND sv._maSinhVien = sv_mh._maSinhVien 
        AND sv_mh._maMonHoc_LopHoc = mh_lh._maMonHoc_LopHoc
        AND mh_lh._maMonHoc = mh._maMonHoc
END
GO

-- EXEC XemDiem_SinhVien '18120201'
-- GO

-- insert LopHoc (_tenLop) values ('18CTT1')
-- insert LopHoc (_tenLop) values ('18CTT2')
-- insert LopHoc (_tenLop) values ('18CTT3')
-- go

-- insert SinhVien (_mssv, _hoTen, _gioiTinh, _cmnd, _maLop) values ('18120201', N'Nguyễn Bảo Long', 'Nam', '241845617', 1)
-- insert SinhVien (_mssv, _hoTen, _gioiTinh, _cmnd, _maLop) values ('18120211', N'Võ Thế Minh', 'Nam', '241845618', 1)
-- insert SinhVien (_mssv, _hoTen, _gioiTinh, _cmnd, _maLop) values ('18120227', N'Phạm Văn Minh Phương', 'Nam', '241845619', 2)
-- insert SinhVien (_mssv, _hoTen, _gioiTinh, _cmnd, _maLop) values ('18120662', N'Trà Anh Toàn', 'Nam', '241845620', 2)
-- insert SinhVien (_mssv, _hoTen, _gioiTinh, _cmnd, _maLop) values ('10000000', N'Đặng Thị Hồng Suyên', N'Nữ', '241845621', 3)
-- go

-- insert MonHoc (_maMonCuaTruong, _tenMonHoc) values ('CTT001', N'Lập trình hướng đối tượng')
-- insert MonHoc (_maMonCuaTruong, _tenMonHoc) values ('CTT002', N'Cơ sở dữ liệu')
-- go 

/* -- insert data for testing
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
*/

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