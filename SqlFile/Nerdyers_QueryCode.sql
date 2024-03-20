use Nerdyers
go

--Procedure Thống kê----

--THống kê doanh thu theo thời gian
--go
--create or alter procedure doanhThu @time nvarchar(10)
--as
--begin
--	select * from DONHANG
--end
--go
-- search Sách theo idSach | tenSach | tenTacGia | tenTheLoai

go
--
create or alter procedure searchSach
	@searchValue nvarchar(255)
as
begin
	select * from SACH s1
	where 
		TENSACH like '%' +@searchValue+ '%' or
		IDSACH in (select IDSACH from TACGIA tg
				join SVTG s2 on tg.IDTACGIA = s2.IDTACGIA
				where TENTACGIA like '%' +@searchValue+ '%') or
		IDSACH in (select IDSACH from THELOAI tl
				join SVTL s3 on tl.IDTHELOAI = s3.IDTHELOAI
				where TENTHELOAI like '%' +@searchValue+ '%')
end

exec searchSach 's'

------

--trigger

GO
create or alter TRIGGER insert_Reader
ON Reader
AFTER INSERT
AS
BEGIN
    DECLARE @idReader NVARCHAR(127); 
    SELECT @idReader = idReader FROM inserted;
    
    INSERT INTO Wishlist (idWishlist, totalCount) 
	VALUES 
	(@idReader, 0); 
    INSERT INTO READLIST (IDREADLIST, alternativeName, totalPDFCount, totalAudioCount) 
	VALUES 
	(@idReader, '', 0, 0); 
    --INSERT INTO GIOHANG (IDGIOHANG, SELECTALL, TONGTIEN, ITEMSCOUNT) VALUES (@idReader, 0, 0, 0); 
END;
GO

--trigger khi insert User
go
create or alter trigger insert_user
on [user]
after insert
as
begin
    --INSERT INTO noiBo (userID, hoTen)
    --SELECT userID, username
    --FROM inserted
    --WHERE reader = 0;

    --INSERT INTO Reader (idReader, hoTen)
    --SELECT userID, username
    --FROM inserted
    --WHERE reader = 1;

    --SET NOCOUNT ON;
	declare @isReader bit, @idUser nvarchar(127), @userName nvarchar(127);
	select	@isReader = reader,
			@idUser = userID, 
			@userName = username 
	from inserted;

	if (@isReader = 1)
	begin
		insert into Reader (idReader, hoTen)
		values
		(@idUser, @userName);
	end
	else if (@isReader = 0)
	begin
		insert into noiBo(userID, hoTen)
		values
		(@idUser, @userName);
	end
end
go

-- thongKeSach quanlyform
create or alter procedure thongKeSach @isView bit
as
begin
	if @isView = 1
	begin
		select * from Sach
		order by viewCount asc
	end
	else if @isView = 0
	begin
		select * from Sach
		order by likeCount asc		
	end
	else if @isView is null
	begin
		select * from Sach
		order by danhGiaTB asc
	end
end


/*
-- Insert rows into table 'Users' in schema '[dbo]'
INSERT INTO [dbo].[Users]
VALUES
(N'HUY2311', N'HUY000', N'123', N'NHOX', N'HUY', CAST(0xEF150B00 AS Date))
GO

SELECT * FROM [dbo].[USERS]
GO

INSERT INTO [dbo].[Reader]
VALUES
(N'WTF', N'HUY2311', 1, 1000)
GO
*/





--go
--create or alter TRIGGER tinh_subtotal
--ON DHCT
--AFTER INSERT
--AS
--BEGIN
--    DECLARE @iddonhang NVARCHAR(100);
--    DECLARE @subtotal INT;

--    -- Lấy ID đơn hàng từ dòng vừa được thêm vào bảng DHCT
--    SELECT @iddonhang = INSERTED.IDDONHANG FROM INSERTED;

--    -- Tính toán subtotal dựa trên số lượng sách và cập nhật vào bảng DHCT
--    UPDATE DHCT
--    SET SUBTOTAL = INSERTED.SOLUONGSACH * (SELECT GIANIEMYET FROM SACH WHERE IDSACH = INSERTED.IDSACH)
--    FROM DHCT
--    INNER JOIN INSERTED 
--	ON DHCT.IDSACH = INSERTED.IDSACH

--	-- Tính toán tổng tiền dựa trên subtotal và cập nhật vào bảng đơn hàng
--    SELECT @subtotal = SUM(SUBTOTAL) FROM DHCT WHERE IDDONHANG = @iddonhang;
--    UPDATE DONHANG
--    SET TONGTIEN = @subtotal
--    WHERE IDDONHANG = @iddonhang;
--END;
--GO
/*
-- Insert rows into table 'SACH' in schema '[dbo]'
INSERT INTO [dbo].[SACH]
VALUES
(N'1', N'HUYDZ', 120, 100, N'2TF', N'2TF', N'2TF', N'2TF',2 , 1, 1, 1, N'2TF' ,1 ,N'2TF', N'2TF',N'2TF',1,1),
(N'2', N'HUYDZ', 50, 100, N'2TF', N'2TF', N'2TF', N'2TF',2 , 1, 1, 1, N'2TF' ,1 ,N'2TF', N'2TF',N'2TF',1,1),
(N'3', N'HUYDZ', 60, 100, N'2TF', N'2TF', N'2TF', N'2TF',2 , 1, 1, 1, N'2TF' ,1 ,N'2TF', N'2TF',N'2TF',1,1)
GO


INSERT INTO [dbo].[KHUYENMAI]
VALUES
(N'HUYCAIWTF', CAST(0xEF150B00 AS Date), CAST(0xEF150B00 AS Date), 1, 1, 1, 1)
GO

INSERT INTO [dbo].[DONHANG]
VALUES
(N'DHHUY', N'WTF', N'HUYCAIWTF', 0, CAST(0x48230B00 AS Date))
GO

INSERT INTO [dbo].[DHCT]
VALUES
(N'DHHUY', N'1', 2, 0),
(N'DHHUY', N'2', 3, 0),
(N'DHHUY', N'3', 2, 0)
GO

-- Delete rows from table '[DHCT]' in schema '[dbo]'
DELETE FROM [dbo].[DHCT]
WHERE IDDONHANG = N'DHHUY'
GO
*/



go
create or alter TRIGGER tr_comment_update
ON COMMENT
INSTEAD OF UPDATE
AS
BEGIN
DECLARE @iddanhgia NVARCHAR(100), @idreader nvarchar(100), @idsach nvarchar(100);

SELECT @iddanhgia = IDDANHGIA, @idreader = idreader, @idsach = idsach
FROM deleted;

-- Ẩn bản ghi cũ
UPDATE COMMENT
SET [enable] = 0, editable = 0
WHERE IDDANHGIA = @iddanhgia;

-- Thêm bản ghi mới với ENABLES = 1 và không thể chỉnh sửa (EDITTABLE = 0)
INSERT INTO COMMENT (IDDANHGIA, IDREADER, IDSACH, SAO, CONTENT, [image], [video], editable, [enable])
SELECT @iddanhgia + '.v2', @IDREADER, @IDSACH, SAO, CONTENT, [image], [video], 0, 1
FROM INSERTED;

END;
GO

/* Insert rows into table 'COMMENT' in schema '[dbo]'
INSERT INTO [dbo].[COMMENT]
VALUES
(N'1', N'WTF', N'2', 1, N'CAIWTFGITHE', N'HUH', N'HUH', 1, 1),
(N'2', N'WTF', N'2', 1, N'CAIWTFGITHE', N'HUH', N'HUH', 1, 1)
GO

-- Update rows in table '[COMMENT]' in schema '[dbo]'
UPDATE [dbo].[COMMENT]
SET
CONTENT = N'OMG'
WHERE IDDANHGIA = N'1'
GO
*/


insert into [User] values
('user', 'reader', '123', 'user@gmail.com', 1, 1);
insert into [User] values
('admin', 'noiBo', '123', 'admin@gmail.com', 0, 1);
go

--('user1', 'reader1', '123', 'user1@gmail.com', 1, 1),
--insert into [User] values
--('nv', 'noiBo1', '123', 'user4@example.com', 0, 1);

--insert into [Access] (idAccess, moTa, fullAccess) values
--(1, 'admin accessibility', 1);
--insert into [Access] (idAccess, moTa, rNoiBo, uNoiBo, rPhongBan, uPhongBan, ) values
--(2, N'quản lý accessibility', 1);

--insert into noiBo (userID, hoTen) values
--('admin', 'noiBo')

select * from [user];
go
select * from noibo;
go
select * from reader;
go
select * from Sach;
select * from TACGIA;
select * from THELOAI;
select * from SVTG;
select * from SVTL;


-- châu phát insert 1 access và phòng ban
INSERT INTO [Access] (idAccess, moTa, fullAccess, rReadList, uReadList, rWishList, uWishList, rUser, uUser, rPhongBan, uPhongBan, rSach, uSach, rReader, uReader, rNoiBo, uNoiBo, rTacGia, uTacGia, rTheLoai, uTheLoai)
VALUES (1, N'Mô tả quyền truy cập', 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0),
(2, N'Mô tả quyền truy cập 2', 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0),
(3, N'Mô tả quyền truy cập 2', 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0);


insert into phongban values
(1,1,2,N'Phòng Gì Đó',N'???'),
(2,1,3,N'Phòng Gì Đó 2',N'???');

select * from [Access]
select * from phongban

insert into [user] values
(N'qlPHATTEACHER',N'chauphat2111',N'123',N'hieuphung2111@gmail.com',0,1);
insert into [user] values
(N'qlKhoadb',N'khoa123',N'123',N'khoadz123@gmail.com',0,1);

update noibo set idPB = 1, quanLy =1, ngaySinh = '2004-11-21', gioitinh = 1 where userid = N'qlPHATTEACHER';


update noibo set idPB = 2, quanLy =1, ngaySinh = '2004-11-11', gioitinh = 0 where userid = N'qlKhoadb';

select * from noiBo

go
--create snapshot để lưu lại database dưới dạng snapShot
--CREATE DATABASE Nerdyers_clone
--ON
--(
--    NAME = Nerdyers,
--    FILENAME = 'D:\Nerdyers_clone.ss'
--) AS SNAPSHOT OF Nerdyers;


--go
---- drop nếu không muốn dùng snapShot nữa
--DROP DATABASE Nerdyers_clone;

--go
---- backup database về lại bản snapShot đã được tạo
--USE master;
--RESTORE DATABASE Nerdyers
--    FROM DATABASE_SNAPSHOT = 'Nerdyers_clone'
--    WITH REPLACE;





