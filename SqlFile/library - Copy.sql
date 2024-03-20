use master 
go
drop database if exists Nerdyers
go
Create database Nerdyers
go
use Nerdyers
go
CREATE TABLE [Access] (
  idAccess INT,
  moTa NVARCHAR(300) default N'',
  fullAccess BIT default 0,
  rReadList BIT default 0,
  uReadList BIT default 0,
  rWishList BIT default 0,
  uWishList BIT default 0,
  rGioHang BIT default 0,
  uGioHang BIT default 0,
  rDonHang BIT default 0,
  uDonHang BIT default 0,
  rkhuyenMai BIT default 0,
  uKhuyenMai BIT default 0,
  rUser BIT default 0,
  uUser BIT default 0,
  uPhongBan BIT default 0,
  rPhongBan BIT default 0,
  uSanPham BIT default 0,
  rSanPham bit default 0,
  uReader BIT default 0,
  rReader BIT default 0,
  uNoiBo BIT default 0,
  rNoiBo BIT default 0,
  uTacGia bit default 0,
  rTacGia bit default 0,
  uTheLoai bit default 0,
  rTheLoai bit default 0,
  PRIMARY KEY (idAccess)
);
CREATE TABLE [User] (
  userID NVARCHAR(64),
  username NVARCHAR(64) unique,
  passwords NVARCHAR(128) NOT NULL,
  email NVARCHAR(100) unique,
  --hoten NVARCHAR(100) NOT NULL,
  --ngaysinh DATE NOT NULL,
  PRIMARY KEY (userID)
);
--insert into users
--values
--(N'a1',N'a',N'123',N'a',N'a','2022-1-1');

CREATE TABLE SACH(
	IDSACH NVARCHAR(64) NOT NULL,
	TENSACH NVARCHAR(256) NOT NULL,
	GIANIEMYET INT NOT NULL,
	--GIAKHUYENMAI INT NOT NULL,
	THUMBNAIL NVARCHAR(256),
	AVATAR NVARCHAR(256),
	TRUESIZEAVATAR NVARCHAR(256) default '',
	MOTA NVARCHAR(100),
	DANHGIATB FLOAT default 0,
	MINAGE INT NULL,
	PDFAVAI BIT default 1,
	AUDIOAVAI BIT default 0,
	VERSIONS NVARCHAR(100) default '1.0',
	SOTRANG INT NOT NULL,
	NGONNGU NVARCHAR(100) default 'en',
	SOURCEPDF NVARCHAR(256) default '',
	sourceSound NVARCHAR(256) default '',
	FREE BIT default 1,
	LIKECOUNT INT default 0,
	viewcount INT default 0,
	PRIMARY KEY (IDSACH)
);
CREATE TABLE TACGIA(
	IDTACGIA INT NOT NULL,
	TENTACGIA NVARCHAR(100) NOT NULL,
	MOTA NVARCHAR(100) NOT NULL,
	NGAYSINH DATE NOT NULL,
	NGAYMAT DATE,
	QUOCTICH NVARCHAR(100) NOT NULL,
	primary key(IDTACGIA)
)

CREATE TABLE THELOAI (
	IDTHELOAI INT NOT NULL,
	TENTHELOAI NVARCHAR(100) NOT NULL,
	MOTA NVARCHAR(100) NOT NULL,
	PRIMARY KEY(IDTHELOAI)
);
CREATE TABLE KHUYENMAI(
	MAKHUYENMAI NVARCHAR(100) NOT NULL,
	NGAYSTART DATETIME NOT NULL,
	NGAYEND DATETIME NOT NULL,
	SOLUONG INT NOT NULL,
	DISCOUNT INT NOT NULL,
	MAXS INT NOT NULL,
	MINSPENT INT NOT NULL,
	PRIMARY KEY(MAKHUYENMAI)
);
--insert into KHUYENMAI values
--(N'km1','2022-1-1','2022-2-2',1,1,23,12);

CREATE TABLE phongBan (
  idPB INT NOT NULL ,
  qlAccess INT NOT NULL,
  nvAccess INT NOT NULL,
  tenPB NVARCHAR(100) NOT NULL,
  moTa NVARCHAR(300) NOT NULL,
  PRIMARY KEY (idPB),
  FOREIGN KEY (qlAccess) REFERENCES access(idAccess),
  FOREIGN KEY (nvAccess) REFERENCES access(idAccess)
);
CREATE TABLE Reader(
	idReader NVARCHAR(100) NOT NULL,	
	userID NVARCHAR(100) NOT NULL,
	thanthiet bit NOT NULL,
	tichDiem INT NOT NULL,
	PRIMARY KEY (idReader),
	FOREIGN KEY (userID) REFERENCES Users(userID)
);
--insert into reader
--values
--(N'1','a1',0,10);


CREATE TABLE SVTG(
	IDSACH NVARCHAR(100) NOT NULL,
	IDTACGIA INT NOT NULL,
	PRIMARY KEY(IDSACH,IDTACGIA),
	FOREIGN KEY (IDSACH) REFERENCES SACH(IDSACH),
	FOREIGN KEY (IDTACGIA) REFERENCES TACGIA(IDTACGIA)
);

CREATE TABLE SVTL(
	IDSACH NVARCHAR(100) NOT NULL,
	IDTHELOAI INT NOT NULL,
	PRIMARY KEY (IDSACH, IDTHELOAI),
	FOREIGN KEY (IDSACH) REFERENCES SACH(IDSACH),
	FOREIGN KEY (idTHELOAI) REFERENCES THELOAI(IDtheloai)
);

CREATE TABLE KMVS(
	MAKHUYENMAI NVARCHAR(100) NOT NULL,
	IDSACH NVARCHAR(100) NOT NULL,
	PRIMARY KEY(MAKHUYENMAI,IDSACH),
	FOREIGN KEY (MAKHUYENMAI) REFERENCES KHUYENMAI(MAKHUYENMAI),
	FOREIGN KEY (IDSACH) REFERENCES SACH(IDSACH)
);

CREATE TABLE noiBo (
  idNoiBo nvarchar(100) NOT NULL ,
  userID NVARCHAR(100) NOT NULL,
  idPB INT NOT NULL,
  idQuanLy NVARCHAR(100) NOT NULL,
  luong FLOAT NOT NULL,
  fullTime BIT NOT NULL,
  ngayThue DATE NOT NULL,
  caLam VARCHAR(100) NOT NULL,
  quanLi BIT NOT NULL,
  luongBong INT NOT NULL,
  PRIMARY KEY (idNoiBo),
  FOREIGN KEY (idPB) REFERENCES phongban(idPB),
  FOREIGN KEY (userID) REFERENCES Users(userID),
  FOREIGN KEY (idQuanLy) REFERENCES noiBo(idNoiBo)
);

CREATE TABLE Wishlist (
    idWishlist NVARCHAR(100) NOT NULL,
    totalCount INT NOT NULL,
    PRIMARY KEY (idWishlist),
    FOREIGN KEY (idWishlist) REFERENCES Reader(idReader)
);

CREATE TABLE READLIST (
	IDREADLIST NVARCHAR(100) NOT NULL,
	alternativeName nvarchar(100) NOT NULL,
	totalPDFCount INT NOT NULL,
	totalAudioCount INT NOT NULL,
	PRIMARY KEY(IDREADLIST),
	FOREIGN KEY (IDREADLIST) REFERENCES Reader(idReader)
);

CREATE TABLE GIOHANG (
	IDGIOHANG NVARCHAR(100) NOT NULL,
	SELECTALL BIT NOT NULL,
	TONGTIEN INT NOT NULL,
	ITEMSCOUNT INT NOT NULL,
	PRIMARY KEY (IDGIOHANG),
	FOREIGN KEY (IDGIOHANG) REFERENCES Reader(idReader)
);


CREATE TABLE KMVR(
	MAKHUYENMAI NVARCHAR(100) NOT NULL,
	IDREADER NVARCHAR(100) NOT NULL,
	PRIMARY KEY(MAKHUYENMAI,IDREADER),
	FOREIGN KEY (MAKHUYENMAI) REFERENCES KHUYENMAI(MAKHUYENMAI),
	FOREIGN KEY (IDREADER) REFERENCES Users(userID)
);

CREATE TABLE COMMENT(
	IDDANHGIA NVARCHAR(100) NOT NULL,
	IDREADER NVARCHAR(100) NOT NULL,
	IDSACH NVARCHAR(100) NOT NULL,
	SAO INT NOT NULL,
	CONTENT NVARCHAR(100) NOT NULL,
	IMAGES NVARCHAR(100) NOT NULL,
	VIDEOS NVARCHAR(100) NOT NULL,
	EDITTABLE BIT NOT NULL,
	ENABLES BIT NOT NULL,
	PRIMARY KEY (IDDANHGIA),
	FOREIGN KEY (IDSACH) REFERENCES SACH(IDSACH),
	FOREIGN KEY (IDREADER) REFERENCES Reader(idReader)
);

CREATE TABLE SACHPDF (
	IDREADLIST NVARCHAR(100) NOT NULL,
	IDSACH NVARCHAR(100) NOT NULL,
	LASTSEENPAGE INT NOT NULL,
	ADDEDTIME DATETIME NOT NULL,
	PRIMARY KEY(IDREADLIST,IDSACH),
	FOREIGN KEY (IDREADLIST) REFERENCES READLIST(IDREADLIST),
	FOREIGN KEY (IDSACH) REFERENCES SACH(IDSACH)
);

CREATE TABLE SACHNOI(
	IDREADLIST NVARCHAR(100) NOT NULL,
	IDSACH NVARCHAR(100) NOT NULL,
	LASTLEFT TIME NOT NULL,
	ADDEDTIME DATETIME NOT NULL,
	PRIMARY KEY(IDREADLIST,IDSACH),
	FOREIGN KEY (IDSACH) REFERENCES SACH(IDSACH),
	FOREIGN KEY (IDREADLIST) REFERENCES READLIST(IDREADLIST)
);

CREATE TABLE GHCT(
	IDGIOHANG NVARCHAR(100) NOT NULL,
	IDSACH NVARCHAR(100) NOT NULL,
	SELECTEDOPTION NVARCHAR(100) NOT NULL,
	SOLUONGSACH INT NOT NULL,
	SELECTED BIT,
	PRIMARY KEY(IDGIOHANG,IDSACH),
	FOREIGN KEY (IDGIOHANG) REFERENCES Giohang(IDGIOHANG),
	FOREIGN KEY (IDSACH) REFERENCES SACH(IDSACH)
);

CREATE TABLE DONHANG(
	IDDONHANG NVARCHAR(100) NOT NULL,
	IDGIOHANG NVARCHAR(100) NOT NULL,
	MAKHUYENMAI NVARCHAR(100) NOT NULL,
	TONGTIEN INT NOT NULL,
	NGAYLAPDON DATETIME NOT NULL,
	PRIMARY KEY (IDDONHANG),
	FOREIGN KEY (IDGIOHANG) REFERENCES GIOHANG(IDGIOHANG),
	FOREIGN KEY (MAKHUYENMAI) REFERENCES KHUYENMAI(MAKHUYENMAI)
);


CREATE TABLE DHCT(
	IDDONHANG NVARCHAR(100) NOT NULL,
	IDSACH NVARCHAR(100) NOT NULL,
	SOLUONGSACH INT NOT NULL,
	SUBTOTAL INT NOT NULL,
	PRIMARY KEY(IDDONHANG,IDSACH),
	FOREIGN KEY (IDSACH) REFERENCES SACH(IDSACH),
	FOREIGN KEY (iddonhang) REFERENCES donhang(iddonhang)

);
---------
INSERT INTO access (idAccess, moTa, fullAccess, xReadList, eReadList, xWishList, eWishList, xGioHang, eGioHang, xDonHang, eDonHang, xkhuyenMai, eKhuyenMai, xUser, eUser, ePhongBan, xPhongBan, eSanPham, eNguoiDoc, xNguoiDoc, eNhanVien, xNhanVien)
VALUES 
(1, N'Mô tả quyền truy cập 1', 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1),
(2, N'Mô tả quyền truy cập 2', 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1),
(3, N'Mô tả quyền truy cập 3', 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1),
(4, N'Mô tả quyền truy cập 4', 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0),
(5, N'Mô tả quyền truy cập 5', 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1),
(6, N'Mô tả quyền truy cập 6', 1, 1, 0, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1),
(7, N'Mô tả quyền truy cập 7', 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1),
(8, N'Mô tả quyền truy cập 8', 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0),
(9, N'Mô tả quyền truy cập 9', 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1),
(10, N'Mô tả quyền truy cập 10', 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1);

INSERT INTO Users (userID, username, passwords, email, hoten, ngaysinh)
VALUES 
(N'user1', N'nguyen_van_a', N'password123', N'nguyenvana@email.com', N'Nguyễn Văn A', '1990-01-15'),
(N'user2', N'tran_thi_b', N'securepass456', N'tranthib@email.com', N'Trần Thị B', '1985-08-22'),
(N'user3', N'le_hoang_c', N'strongpassword789', N'lehoangc@email.com', N'Lê Hoàng C', '1995-05-10'),
(N'user4', N'pham_dinh_d', N'abc123', N'phamdinhd@email.com', N'Phạm Đình D', '1988-12-05'),
(N'user5', N'mai_thi_e', N'pass456', N'maithie@email.com', N'Mai Thị E', '1992-03-28'),
(N'user6', N'nguyen_van_f', N'password789', N'nguyenvanf@email.com', N'Nguyễn Văn F', '1980-07-17'),
(N'user7', N'le_thi_g', N'securepass123', N'lethig@email.com', N'Lê Thị G', '1998-09-09'),
(N'user8', N'tran_van_h', N'strongpass456', N'tranvanh@email.com', N'Trần Văn H', '1993-06-20'),
(N'user9', N'hoang_thi_i', N'abc789', N'hoangthii@email.com', N'Hoàng Thị I', '1986-04-03'),
(N'user10', N'pham_van_j', N'pass123', N'phamvanj@email.com', N'Phạm Văn J', '1997-11-12');

-- INSERT INTO SACH
INSERT INTO SACH (IDSACH, TENSACH, GIANIEMYET, GIAKHUYENMAI, THUMBNAIL, AVATAR, TRUESIZEAVATAR, MOTA, DANHGIATB, MINAGE, PDFAVAI, AUDIOAVAI, VERSIONS, SOTRANG, NGONNGU, SOURCEPDF, FREE, LIKECOUNT)
VALUES
(N'sach1', N'Tiếng Chuông Tình Yêu', 120000, 95000, N'thumbnail_sach1.jpg', N'avatar_sach1.jpg', N'true_size_avatar_sach1.jpg', N'Mô tả sách 1', 4.5, 16, 1, 0, N'Phiên bản 1', 300, N'Tiếng Việt', N'source_pdf_sach1.pdf', 0, 120),
(N'sach2', N'Đắc Nhân Tâm', 80000, 65000, N'thumbnail_sach2.jpg', N'avatar_sach2.jpg', N'true_size_avatar_sach2.jpg', N'Mô tả sách 2', 4.8, 18, 1, 1, N'Phiên bản 2', 250, N'Tiếng Việt', N'source_pdf_sach2.pdf', 0, 230),
(N'sach3', N'Cuộc Phiêu Lưu Của Alice', 150000, 120000, N'thumbnail_sach3.jpg', N'avatar_sach3.jpg', N'true_size_avatar_sach3.jpg', N'Mô tả sách 3', 4.2, 12, 0, 1, N'Phiên bản 1', 200, N'Tiếng Anh', N'source_pdf_sach3.pdf', 1, 180),
(N'sach4', N'Harry Potter và Hòn Đá Phù Thủy', 180000, 150000, N'thumbnail_sach4.jpg', N'avatar_sach4.jpg', N'true_size_avatar_sach4.jpg', N'Mô tả sách 4', 4.9, 14, 1, 0, N'Phiên bản 3', 350, N'Tiếng Anh', N'source_pdf_sach4.pdf', 0, 350),
(N'sach5', N'Người Mẫu', 95000, 80000, N'thumbnail_sach5.jpg', N'avatar_sach5.jpg', N'true_size_avatar_sach5.jpg', N'Mô tả sách 5', 4.6, 20, 0, 1, N'Phiên bản 1', 280, N'Tiếng Việt', N'source_pdf_sach5.pdf', 1, 200);

-- INSERT INTO TACGIA
INSERT INTO TACGIA (IDTACGIA, TENTACGIA, MOTA, NGAYSINH, NGAYMAT, QUOCTICH)
VALUES
(1, N'Nguyễn Nhật Ánh', N'Mô tả tác giả 1', '1955-05-02', NULL, N'Việt Nam'),
(2, N'Dale Carnegie', N'Mô tả tác giả 2', '1888-11-24', '1955-11-01', N'Hoa Kỳ'),
(3, N'Lewis Carroll', N'Mô tả tác giả 3', '1832-01-27', '1898-01-14', N'Anh'),
(4, N'J.K. Rowling', N'Mô tả tác giả 4', '1965-07-31', NULL, N'Anh'),
(5, N'Paulina Porizkova', N'Mô tả tác giả 5', '1965-04-09', NULL, N'Cộng hòa Séc');

-- INSERT INTO THELOAI
INSERT INTO THELOAI (IDTHeLOaI, TENTHELOAI, MOTA)
VALUES
(1, N'Văn Học', N'Mô tả thể loại văn học'),
(2, N'Tâm Lý - Kỹ Năng Sống', N'Mô tả thể loại tâm lý và kỹ năng sống'),
(3, N'Tiểu Thuyết Trinh Thám', N'Mô tả thể loại tiểu thuyết trinh thám'),
(4, N'Sách Ngoại Ngữ', N'Mô tả thể loại sách ngoại ngữ'),
(5, N'Tiểu Thuyết Ngôn Tình', N'Mô tả thể loại tiểu thuyết ngôn tình');

-- INSERT INTO KHUYENMAI
INSERT INTO KHUYENMAI (MAKHUYENMAI, NGAYSTART, NGAYEND, SOLUONG, DISCOUNT, MAXS, MINSPENT)
VALUES
(N'km1', '2023-01-01', '2023-02-28', 2, 15, 30, 100000),
(N'km2', '2023-03-01', '2023-04-30', 1, 10, 20, 80000),
(N'km3', '2023-05-01', '2023-06-30', 3, 20, 40, 120000);

-- INSERT INTO PHONGBAN
INSERT INTO PHONGBAN (IDPB, QLACCESS, NVACCESS, TENPB, MOTA)
VALUES
(1, 1, 2, N'Quản lý Sách', N'Mô tả phòng ban quản lý sách'),
(2, 3, 4, N'Nhân viên Bán hàng', N'Mô tả phòng ban nhân viên bán hàng'),
(3, 5, 6, N'Nhân viên Thư viện', N'Mô tả phòng ban nhân viên thư viện');

-- INSERT INTO READER
INSERT INTO READER (IDREADER, USERID, THANTHIET, TICHDIEM)
VALUES
(N'reader1', 'user1', 1, 50),
(N'reader2', 'user2', 0, 30),
(N'reader3', 'user3', 1, 40),
(N'reader4', 'user4', 0, 25),
(N'reader5', 'user5', 1, 60),
(N'reader6', 'user6', 0, 35),
(N'reader7', 'user7', 1, 45),
(N'reader8', 'user8', 1, 55),
(N'reader9', 'user9', 0, 20),
(N'reader10', 'user10', 1, 70);

-- INSERT INTO SVTG
INSERT INTO SVTG (IDSACH, IDTACGIA)
VALUES
(N'sach1', 1),
(N'sach2', 2),
(N'sach3', 3),
(N'sach4', 4),
(N'sach5', 5);

-- INSERT INTO SVTL
INSERT INTO SVTL (IDSACH, IDTHELOAI)
VALUES
(N'sach1', 1),
(N'sach2', 2),
(N'sach3', 3),
(N'sach4', 4),
(N'sach5', 5);

-- INSERT INTO KMVS
INSERT INTO KMVS (MAKHUYENMAI, IDSACH)
VALUES
(N'km1', N'sach1'),
(N'km2', N'sach2'),
(N'km3', N'sach3'),
(N'km2', N'sach4'),
(N'km3', N'sach5');	

-- INSERT INTO noiBo
INSERT INTO noiBo (idNoiBo, userID, idPB, idQuanLy, luong, fullTime, ngayThue, caLam, quanLi, luongBong)
VALUES
(N'NB1', 'user1', 1, N'NB1', 20000000, 1, '2023-01-01', 'Ca 1', 0, 5000000),
(N'NB2', 'user2', 2, 'NB2', 18000000, 0, '2023-02-01', 'Ca 2', 1, 4500000),
(N'NB3', 'user3', 3, 'NB3', 22000000, 1, '2023-03-01', 'Ca 3', 1, 5500000),
(N'NB4', 'user4', 1, 'NB4', 19000000, 0, '2023-04-01', 'Ca 1', 0, 4800000),
(N'NB5', 'user5', 2, 'NB5', 21000000, 1, '2023-05-01', 'Ca 2', 1, 5200000);

-- INSERT INTO Wishlist
INSERT INTO Wishlist (idWishlist, totalCount)
VALUES
(N'reader1', 3),
(N'reader2', 2),
(N'reader3', 1),
(N'reader4', 4),
(N'reader5', 2);

-- INSERT INTO READLIST
INSERT INTO READLIST (IDREADLIST, alternativeName, totalPDFCount, totalAudioCount)
VALUES
(N'reader1', N'Readlist A', 5, 3),
(N'reader2', N'Readlist B', 2, 1),
(N'reader3', N'Readlist C', 3, 2),
(N'reader4', N'Readlist D', 4, 1),
(N'reader5', N'Readlist E', 1, 5);

-- INSERT INTO GIOHANG
INSERT INTO GIOHANG (IDGIOHANG, SELECTALL, TONGTIEN, ITEMSCOUNT)
VALUES
(N'reader1', 1, 350000, 3),
(N'reader2', 0, 250000, 2),
(N'reader3', 1, 180000, 1),
(N'reader4', 0, 480000, 4),
(N'reader5', 1, 520000, 2);

-- INSERT INTO KMVR
INSERT INTO KMVR (MAKHUYENMAI, IDREADER)
VALUES
(N'km1', 'user1'),
(N'km2', 'user2'),
(N'km3', 'user3'),
(N'km2', 'user4'),
(N'km3', 'user5');

-- INSERT INTO COMMENT
INSERT INTO COMMENT (IDDANHGIA, IDREADER, IDSACH, SAO, CONTENT, IMAGES, VIDEOS, EDITTABLE, ENABLES)
VALUES
(N'comment1', 'reader1', 'sach1', 4, N'Nội dung đánh giá 1', N'image1.jpg', N'video1.mp4', 1, 1),
(N'comment2', 'reader2', 'sach2', 5, N'Nội dung đánh giá 2', N'image2.jpg', N'video2.mp4', 0, 1),
(N'comment3', 'reader3', 'sach3', 3, N'Nội dung đánh giá 3', N'image3.jpg', N'video3.mp4', 1, 0),
(N'comment4', 'reader4', 'sach4', 4, N'Nội dung đánh giá 4', N'image4.jpg', N'video4.mp4', 0, 1),
(N'comment5', 'reader5', 'sach5', 5, N'Nội dung đánh giá 5', N'image5.jpg', N'video5.mp4', 1, 1);

-- INSERT INTO SACHPDF
INSERT INTO SACHPDF (IDREADLIST, IDSACH, LASTSEENPAGE, ADDEDTIME)
VALUES
(N'reader1', 'sach1', 50, '2023-01-05'),
(N'reader2', 'sach2', 20, '2023-02-10'),
(N'reader3', 'sach3', 30, '2023-03-15'),
(N'reader4', 'sach4', 40, '2023-04-20'),
(N'reader5', 'sach5', 10, '2023-05-25');

-- INSERT INTO SACHNOI
INSERT INTO SACHNOI (IDREADLIST, IDSACH, LASTLEFT, ADDEDTIME)
VALUES
(N'reader1', 'sach1', '08:00:00', '2023-01-05'),
(N'reader2', 'sach2', '09:30:00', '2023-02-10'),
(N'reader3', 'sach3', '10:45:00', '2023-03-15'),
(N'reader4', 'sach4', '11:20:00', '2023-04-20'),
(N'reader5', 'sach5', '12:05:00', '2023-05-25');

-- INSERT INTO GHCT
INSERT INTO GHCT (IDGIOHANG, IDSACH, SELECTEDOPTION, SOLUONGSACH, SELECTED)
VALUES
(N'reader1', 'sach1', N'Option 1', 2, 1),
(N'reader2', 'sach2', N'Option 2', 1, 0),
(N'reader3', 'sach3', N'Option 3', 1, 1),
(N'reader4', 'sach4', N'Option 4', 3, 0),
(N'reader5', 'sach5', N'Option 5', 1, 1);

-- INSERT INTO DONHANG
INSERT INTO DONHANG (IDDONHANG, IDGIOHANG, MAKHUYENMAI, TONGTIEN, NGAYLAPDON)
VALUES
(N'donhang1', 'reader1', 'km1', 190000, '2023-01-10'),
(N'donhang2', 'reader2', 'km2', 160000, '2023-02-15'),
(N'donhang3', 'reader3', 'km3', 100000, '2023-03-20'),
(N'donhang4', 'reader4', 'km2', 290000, '2023-04-25'),
(N'donhang5', 'reader5', 'km3', 320000, '2023-05-30');

-- INSERT INTO DHCT
INSERT INTO DHCT (IDDONHANG, IDSACH, SOLUONGSACH, SUBTOTAL)
VALUES
(N'donhang1', 'sach1', 2, 95000),
(N'donhang1', 'sach2', 1, 65000),
(N'donhang2', 'sach3', 1, 120000),
(N'donhang2', 'sach4', 1, 150000),
(N'donhang2', 'sach5', 1, 80000);


------

--trigger

GO
CREATE TRIGGER insert_Reader
ON Reader
AFTER INSERT
AS
BEGIN
    DECLARE @idReader NVARCHAR(100); 
    SELECT @idReader = idReader FROM inserted;
    
    INSERT INTO Wishlist (idWishlist, totalCount) VALUES (@idReader, 0); 
    INSERT INTO READLIST (IDREADLIST, alternativeName, totalPDFCount, totalAudioCount) VALUES (@idReader, '', 0, 0); 
    INSERT INTO GIOHANG (IDGIOHANG, SELECTALL, TONGTIEN, ITEMSCOUNT) VALUES (@idReader, 0, 0, 0); 
END;
GO
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






CREATE TRIGGER tinh_subtotal
ON DHCT
AFTER INSERT
AS
BEGIN
    DECLARE @iddonhang NVARCHAR(100);
    DECLARE @subtotal INT;

    -- Lấy ID đơn hàng từ dòng vừa được thêm vào bảng DHCT
    SELECT @iddonhang = INSERTED.IDDONHANG FROM INSERTED;

    -- Tính toán subtotal dựa trên số lượng sách và cập nhật vào bảng DHCT
    UPDATE DHCT
    SET SUBTOTAL = INSERTED.SOLUONGSACH * (SELECT GIANIEMYET FROM SACH WHERE IDSACH = INSERTED.IDSACH)
    FROM DHCT
    INNER JOIN INSERTED 
	ON DHCT.IDSACH = INSERTED.IDSACH

	-- Tính toán tổng tiền dựa trên subtotal và cập nhật vào bảng đơn hàng
    SELECT @subtotal = SUM(SUBTOTAL) FROM DHCT WHERE IDDONHANG = @iddonhang;
    UPDATE DONHANG
    SET TONGTIEN = @subtotal
    WHERE IDDONHANG = @iddonhang;
END;
GO
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




CREATE TRIGGER tr_comment_update
ON COMMENT
INSTEAD OF UPDATE
AS
BEGIN
DECLARE @iddanhgia NVARCHAR(100), @idreader nvarchar(100), @idsach nvarchar(100);

SELECT @iddanhgia = IDDANHGIA, @idreader = idreader, @idsach = idsach
FROM deleted;

-- Ẩn bản ghi cũ
UPDATE COMMENT
SET ENABLES = 0, EDITTABLE = 0
WHERE IDDANHGIA = @iddanhgia;

-- Thêm bản ghi mới với ENABLES = 1 và không thể chỉnh sửa (EDITTABLE = 0)
INSERT INTO COMMENT (IDDANHGIA, IDREADER, IDSACH, SAO, CONTENT, IMAGES, VIDEOS, EDITTABLE, ENABLES)
SELECT @iddanhgia + '.v2', @IDREADER, @IDSACH, SAO, CONTENT, IMAGES, VIDEOS, 0, 1
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