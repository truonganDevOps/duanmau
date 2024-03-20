use Nerdyers
go

-- Inserting records into the SACH table
INSERT INTO SACH (IDSACH,TRUESIZEAVATAR, TENSACH, GIANIEMYET, THUMBNAIL, AVATAR, MOTA, DANHGIATB, MINAGE, PDFAVAI, AUDIOAVAI, SOTRANG, NGONNGU, SOURCEPDF, sourceSound, FREE, LIKECOUNT, viewcount)
VALUES 
('S001','', 'Book One', 20, '/thumbnails/book1_thumbnail.jpg', '/avatars/book1_avatar.jpg', 'Description for Book One', 4.5, 10, 1, 0, 150, 'en', '/pdfs/book1.pdf', '/sounds/book1.mp3', 1, 50, 100),
('S002','', 'Book Two', 25, '/thumbnails/book2_thumbnail.jpg', '/avatars/book2_avatar.jpg', 'Description for Book Two', 4.2, 12, 1, 1, 200, 'en', '/pdfs/book2.pdf', '/sounds/book2.mp3', 0, 30, 80),
('S003', '','Book Three', 18, '/thumbnails/book3_thumbnail.jpg', '/avatars/book3_avatar.jpg', 'Description for Book Three', 4.8, 8, 0, 0, 180, 'en', '/pdfs/book3.pdf', '/sounds/book3.mp3', 1, 70, 120);

--update SACH
--set TENSACH = '?', GIANIEMYET = '?', THUMBNAIL = '?' ,...
--where IDSACH = '?'

--insert into SACH (IDSACH, TENSACH, THUMBNAIL, SOTRANG)
--values
--('s001', 'abc', null, 256);

--go

--create or alter procedure searchSach 
--	@searchValue nvarchar(127),
--	@outputTable dbo.Sach output
--as
--begin
	
--end
--go
-- Sample SQL insert statements for the Sach table
INSERT INTO Sach (idSach, tenSach, free, giaNiemYet, danhGiaTB, moTa, ngonNgu, thumbnail, avatar, trueSizeAvatar, pdfAvai, sourcePDF, soTrang, audioAvai, sourceSound, thoiLuong, namSangTac, version, namXB, minAge, likeCount, viewCount)
VALUES 
    ('1', 'Book 1', 1, 50, 4.5, 'Description 1', 'vn', 'thumb_1.jpg', 'avatar_1.jpg', 'true_avatar_1.jpg', 1, 'book1.pdf', 200, 0, 'sound1.mp3', '01:30:00', 2000, '1.0', 2022, 18, 20, 100);

INSERT INTO Sach (idSach, tenSach, free, giaNiemYet, danhGiaTB, moTa, ngonNgu, thumbnail, avatar, trueSizeAvatar, pdfAvai, sourcePDF, soTrang, audioAvai, sourceSound, thoiLuong, namSangTac, version, namXB, minAge, likeCount, viewCount)
VALUES 
    ('2', 'Book 2', 1, 30, 4.2, 'Description 2', 'vn', 'thumb_2.jpg', 'avatar_2.jpg', 'true_avatar_2.jpg', 1, 'book2.pdf', 150, 1, 'sound2.mp3', '02:15:00', 2010, '2.0', 2023, 16, 15, 80);


-- procedure
go
create or alter procedure searchSach
	@searchValue nvarchar(127)
as
begin
	select * from SACH s1
	where 
		IDSACH like '%' +@searchValue+ '%' or TENSACH like '%' +@searchValue+ '%' or
		IDSACH in (select IDSACH from TACGIA tg
				join SVTG s2 on tg.IDTACGIA = s2.IDTACGIA
				where TENTACGIA like '%' +@searchValue+ '%') or
		IDSACH in (select IDSACH from THELOAI tl
				join SVTL s3 on tl.IDTHELOAI = s3.IDTHELOAI
				where TENTHELOAI like '%' +@searchValue+ '%')
end

exec searchSach 's'


--trigger khi insert User
go
create or alter trigger insert_user
on [user]
after insert
as
begin
	declare @isReader bit, @idUser nvarchar(127), @userName nvarchar(127);
	select	@isReader = reader,
			@idUser = userID, 
			@userName = username 
	from inserted;

	if (@isReader = 1)
	begin
		insert into Reader (userID, hoTen)
		values
		(@idUser, @userName);
	end
	else if (@isReader = 0)
	begin
		insert into noiBo (userID, hoTen)
		values
		(@idUser, @userName);
	end
end
go

update [user] set [password] = 123 where userID = 'user_ka57vZlWeiPT1YZ'


ALTER LOGIN sa
WITH PASSWORD = 'root';

use nerdyers

select * from reader where idreader = 'user'
UPDATE Reader SET thanthiet = 1
ngaySinh = '2004-07-06', hoTen = 'reader1', 
gioiTinh = 0 WHERE idReader = 'user'
=======
select * from Sach
where cover_i like '%4986307-L.jpg%'

select * from COMMENT

delete from COMMENT
where IDDANHGIA = 'user/works/OL10071W.v2'

select * from noiBo