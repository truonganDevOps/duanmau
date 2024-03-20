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
  --rGioHang BIT default 0,
  --uGioHang BIT default 0,
  --rDonHang BIT default 0,
  --uDonHang BIT default 0,
  --rkhuyenMai BIT default 0,
  --uKhuyenMai BIT default 0,
  rUser BIT default 0,
  uUser BIT default 0,
  rPhongBan BIT default 0,
  uPhongBan BIT default 0,
  rSach bit default 0,
  uSach BIT default 0,
  rReader BIT default 0,
  uReader BIT default 0,
  rNoiBo BIT default 0,
  uNoiBo BIT default 0,
  rTacGia bit default 0,
  uTacGia bit default 0,
  rTheLoai bit default 0,
  uTheLoai bit default 0,
  PRIMARY KEY (idAccess)
);
CREATE TABLE [User] (
  userID NVARCHAR(127),
  username NVARCHAR(127) unique,
  [password] NVARCHAR(127) NOT NULL,
  email NVARCHAR(127) unique,
  reader bit default 0,
  verificated bit default 0,
  --hoten NVARCHAR(127) NOT NULL,
  --ngaysinh DATE NOT NULL,
  PRIMARY KEY (userID)
);


--CREATE TABLE SACH(
--	IDSACH NVARCHAR(127),
--	TENSACH NVARCHAR(127) NOT NULL,
--	GIANIEMYET INT default 0,
--	--GIAKHUYENMAI INT NOT NULL,
--	THUMBNAIL NVARCHAR(127),
--	AVATAR NVARCHAR(127),
--	TRUESIZEAVATAR NVARCHAR(127) not null,
--	MOTA NVARCHAR(1023),
--	DANHGIATB FLOAT default 0,
--	MINAGE INT NULL,
--	PDFAVAI BIT default 1,
--	AUDIOAVAI BIT default 0,
--	[VERSION] NVARCHAR(127) default '1.0',
--	SOTRANG INT,
--	NGONNGU NVARCHAR(127),
--	SOURCEPDF NVARCHAR(127),
--	sourceSound NVARCHAR(127),
--	FREE BIT default 1,
--	LIKECOUNT INT default 0,
--	viewcount INT default 0,
--	thoiLuong time,
--	PRIMARY KEY (IDSACH)
--);

CREATE TABLE Sach (
    idSach NVARCHAR(127) PRIMARY KEY,
    tenSach NVARCHAR(255) NOT NULL,
	namXB int default year(getdate()),
    namSangTac INT default year(getdate()),
    soTrang INT not null,
    ebook_access NVARCHAR(63) default 'public',
    has_fulltext BIT default 1,
    public_scan_b BIT default 1,
    urlLink NVARCHAR(255) not null,
    cover_i nvarchar(255) not null,
    moTa NVARCHAR(max) default '',
    ngonNgu NVARCHAR(255) default 'eng',
    phienBan NVARCHAR(63) default 1,
    viewCount INT default 0,
    likeCount INT default 0,	
    danhGiaTB float null,
    --free BIT DEFAULT 1,
 --   giaNiemYet INT DEFAULT 0,
 --   danhGiaTB FLOAT DEFAULT 0,
 --   moTa NVARCHAR(255),
 --   ngonNgu NVARCHAR(50) DEFAULT 'vn', -- Adjust the length as needed
 --   thumbnail NVARCHAR(255),
 --   avatar NVARCHAR(255),
 --   trueSizeAvatar NVARCHAR(255) NOT NULL,
 --   pdfAvai BIT DEFAULT 1,
 --   sourcePDF NVARCHAR(255),
 --   soTrang INT,
 --   audioAvai BIT DEFAULT 0,
 --   sourceSound NVARCHAR(255),
 --   thoiLuong TIME,
 --   namSangTac INT,
 --   [version] NVARCHAR(127) DEFAULT '1.0',
 --   namXB INT,
 --   minAge INT,
 --   likeCount INT DEFAULT 0,
 --   viewCount INT DEFAULT 0,
	----publisher nvarchar(127),
	--authors nvarchar(255) default '',
	--categories nvarchar(255) default ''
);



CREATE TABLE TACGIA(
	IDTACGIA nvarchar(127),
	TENTACGIA NVARCHAR(255) NOT NULL,
	primary key(IDTACGIA)
)

CREATE TABLE THELOAI (
	IDTHELOAI nvarchar(127) NOT NULL,
	TENTHELOAI NVARCHAR(255) NOT NULL,
	PRIMARY KEY(IDTHELOAI)
);

CREATE TABLE SVTG(
	IDSACH NVARCHAR(127),
	IDTACGIA NVARCHAR(127),
	PRIMARY KEY(IDSACH,IDTACGIA),
	FOREIGN KEY (IDSACH) REFERENCES SACH(idsach),
	FOREIGN KEY (IDTACGIA) REFERENCES TACGIA(IDTACGIA)
);

CREATE TABLE SVTL(
	IDSACH NVARCHAR(127) NOT NULL,
	IDTHELOAI NVARCHAR(127) NOT NULL,
	PRIMARY KEY (IDSACH, IDTHELOAI),
	FOREIGN KEY (IDSACH) REFERENCES SACH(IDSACH),
	FOREIGN KEY (idTHELOAI) REFERENCES THELOAI(IDtheloai)
);


--CREATE TABLE KHUYENMAI(
--	MAKHUYENMAI NVARCHAR(127) NOT NULL,
--	NGAYSTART DATETIME default getdate(),
--	NGAYEND DATETIME default cast(getdate() + 7 as Datetime),
--	SOLUONG INT default 127,
--	DISCOUNT INT default 20,
--	[max] INT default 50000,
--	MINSPENT INT default 50000,
--	PRIMARY KEY(MAKHUYENMAI)
--);
--insert into KHUYENMAI values
--(N'km1','2022-1-1','2022-2-2',1,1,23,12);

CREATE TABLE phongBan (
  idPB INT NOT NULL ,
  qlAccess INT NOT NULL,
  nvAccess INT NOT NULL,
  tenPB NVARCHAR(127) NOT NULL,
  moTa NVARCHAR(300),
  PRIMARY KEY (idPB),
  FOREIGN KEY (qlAccess) REFERENCES access(idAccess),
  FOREIGN KEY (nvAccess) REFERENCES access(idAccess)
);
CREATE TABLE Reader(
	--idReader int identity(1,1),	
	idReader NVARCHAR(127) NOT NULL,
	thanthiet bit default 0,
	tichDiem INT default 0,
	ngaySinh date,
	avatar nvarchar(127),
	hoTen nvarchar(127) not null,
	gioiTinh bit,
	PRIMARY KEY (idReader),
	FOREIGN KEY (idReader) REFERENCES [User](userID)
);
--insert into reader
--values
--(N'1','a1',0,10);


--CREATE TABLE SVTG(
--	IDSACH NVARCHAR(127),
--	IDTACGIA INT,
--	PRIMARY KEY(IDSACH,IDTACGIA),
--	FOREIGN KEY (IDSACH) REFERENCES SACH(idsach),
--	FOREIGN KEY (IDTACGIA) REFERENCES TACGIA(IDTACGIA)
--);

--CREATE TABLE SVTL(
--	IDSACH NVARCHAR(127) NOT NULL,
--	IDTHELOAI INT NOT NULL,
--	PRIMARY KEY (IDSACH, IDTHELOAI),
--	FOREIGN KEY (IDSACH) REFERENCES SACH(IDSACH),
--	FOREIGN KEY (idTHELOAI) REFERENCES THELOAI(IDtheloai)
--);

--CREATE TABLE KMVS(
--	MAKHUYENMAI NVARCHAR(127) NOT NULL,
--	IDSACH NVARCHAR(127) NOT NULL,
--	PRIMARY KEY(MAKHUYENMAI,IDSACH),
--	FOREIGN KEY (MAKHUYENMAI) REFERENCES KHUYENMAI(MAKHUYENMAI),
--	FOREIGN KEY (IDSACH) REFERENCES SACH(IDSACH)
--);

CREATE TABLE noiBo (
	idNoiBo int identity(1,1) ,
	userID NVARCHAR(127) unique not null,
	idPB INT null,
	idQuanLy int null,
	luong int default 0,
	fullTime BIT default 1,
	ngayThue DATE default getdate(),
	caLam VARCHAR(127) default 'ca123-thu246',
	quanLy BIT default 0,
	luongBong INT default 0,
	ngaySinh date,
	avatar nvarchar(127),
	hoTen nvarchar(127) not null,
	gioiTinh bit,
	PRIMARY KEY (idNoiBo),
	FOREIGN KEY (idPB) REFERENCES phongban(idPB),
	FOREIGN KEY (userID) REFERENCES [User](userID),
	FOREIGN KEY (idQuanLy) REFERENCES noiBo(idNoiBo)
);
--insert into noibo values ()

CREATE TABLE Wishlist (
    idWishlist nvarchar(127) NOT NULL,
    totalCount INT default 0,
    PRIMARY KEY (idWishlist),
    FOREIGN KEY (idWishlist) REFERENCES Reader(idReader)
);

create table WishlistCT (
	idWishlist nvarchar(127),
	idSach nvarchar(127),
	addedTime datetime default getdate(),
	primary key (idWishlist, idSach),
	foreign key (idwishlist) references wishlist(idwishlist),
	foreign key (idSach) references sach(idsach)

);

CREATE TABLE READLIST (
	IDREADLIST nvarchar(127) NOT NULL,
	alternativeName nvarchar(127) default 'Readlist',
	totalPDFCount INT default 0,
	totalAudioCount INT default 0,
	PRIMARY KEY(IDREADLIST),
	FOREIGN KEY (IDREADLIST) REFERENCES Reader(idReader)
);

--CREATE TABLE GIOHANG (
--	IDGIOHANG nvarchar(127) NOT NULL,
--	SELECTALL BIT default 0,
--	TONGTIEN INT default 0,
--	ITEMSCOUNT INT default 0,
--	PRIMARY KEY (IDGIOHANG),
--	FOREIGN KEY (IDGIOHANG) REFERENCES Reader(idReader)
--);


--CREATE TABLE KMVR(
--	MAKHUYENMAI NVARCHAR(127) NOT NULL,
--	IDREADER nvarchar(127) NOT NULL,
--	soLuong int default 1,
--	PRIMARY KEY(MAKHUYENMAI,IDREADER),
--	FOREIGN KEY (MAKHUYENMAI) REFERENCES KHUYENMAI(MAKHUYENMAI),
--	FOREIGN KEY (IDREADER) REFERENCES reader(idreader)
--);

CREATE TABLE COMMENT(
	IDDANHGIA NVARCHAR(127) NOT NULL,
	IDREADER nvarchar(127) NOT NULL,
	IDSACH NVARCHAR(127) NOT NULL,
	SAO INT default 5,
	CONTENT NVARCHAR(127) default '',
	[image] NVARCHAR(255),
	[video] NVARCHAR(255),
	EDITABLE BIT default 1,
	[ENABLE] BIT default 1,
	PRIMARY KEY (IDDANHGIA),
	FOREIGN KEY (IDSACH) REFERENCES SACH(IDSACH),
	FOREIGN KEY (IDREADER) REFERENCES Reader(idReader)
);

CREATE TABLE SACHPDF (
	IDREADLIST nvarchar(127) NOT NULL,
	IDSACH NVARCHAR(127) NOT NULL,
	LASTSEENPAGE INT default 0,
	ADDEDTIME DATETIME default getdate(),
	PRIMARY KEY(IDREADLIST,IDSACH),
	FOREIGN KEY (IDREADLIST) REFERENCES READLIST(IDREADLIST),
	FOREIGN KEY (IDSACH) REFERENCES SACH(IDSACH)
);

--CREATE TABLE SACHNOI(
--	IDREADLIST nvarchar(127) NOT NULL,
--	IDSACH NVARCHAR(127) NOT NULL,
--	LASTLEFT TIME default cast('00:00:00' as time),
--	ADDEDTIME DATETIME default getdate(),
--	PRIMARY KEY(IDREADLIST,IDSACH),
--	FOREIGN KEY (IDSACH) REFERENCES SACH(IDSACH),
--	FOREIGN KEY (IDREADLIST) REFERENCES READLIST(IDREADLIST)
--);

--CREATE TABLE GHCT(
--	IDGIOHANG nvarchar(127) NOT NULL,
--	IDSACH NVARCHAR(127) NOT NULL,
--	SELECTEDOPTION NVARCHAR(127) default N'Sách PDF',
--	SOLUONGSACH INT default 1,
--	SELECTED BIT default 0,
--	PRIMARY KEY(IDGIOHANG,IDSACH),
--	FOREIGN KEY (IDGIOHANG) REFERENCES Giohang(IDGIOHANG),
--	FOREIGN KEY (IDSACH) REFERENCES SACH(IDSACH)
--);

--CREATE TABLE DONHANG(
--	IDDONHANG int identity(1, 1) NOT NULL,
--	IDGIOHANG nvarchar(127) NOT NULL,
--	MAKHUYENMAI NVARCHAR(127) null,
--	TONGTIEN INT default 0,
--	NGAYLAPDON DATETIME default getdate(),
--	PRIMARY KEY (IDDONHANG),
--	FOREIGN KEY (IDGIOHANG) REFERENCES GIOHANG(IDGIOHANG),
--	FOREIGN KEY (MAKHUYENMAI) REFERENCES KHUYENMAI(MAKHUYENMAI)
--);


--CREATE TABLE DHCT(
--	IDDONHANG int NOT NULL,
--	IDSACH NVARCHAR(127) NOT NULL,
--	SOLUONGSACH INT default 1,
--	SUBTOTAL INT default 0,
--	PRIMARY KEY(IDDONHANG,IDSACH),
--	FOREIGN KEY (IDSACH) REFERENCES SACH(IDSACH),
--	FOREIGN KEY (iddonhang) REFERENCES donhang(iddonhang)
--);
