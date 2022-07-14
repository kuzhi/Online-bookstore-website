USE [master]
GO
/****** Object:  Database [QLKGB]    Script Date: 27/6/2022 4:13:38 AM ******/
CREATE DATABASE [QLKGB]

GO
ALTER DATABASE [QLKGB] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QLKGB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QLKGB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QLKGB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QLKGB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QLKGB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QLKGB] SET ARITHABORT OFF 
GO
ALTER DATABASE [QLKGB] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [QLKGB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QLKGB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QLKGB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QLKGB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QLKGB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QLKGB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QLKGB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QLKGB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QLKGB] SET  ENABLE_BROKER 
GO
ALTER DATABASE [QLKGB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QLKGB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QLKGB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QLKGB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QLKGB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QLKGB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QLKGB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QLKGB] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [QLKGB] SET  MULTI_USER 
GO
ALTER DATABASE [QLKGB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QLKGB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QLKGB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QLKGB] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [QLKGB] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [QLKGB] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [QLKGB] SET QUERY_STORE = OFF
GO
USE [QLKGB]
GO
/****** Object:  Table [dbo].[DONHANG]    Script Date: 27/6/2022 4:13:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DONHANG](
	[ID] [nchar](15) NOT NULL,
	[KhachHangID] [int] NOT NULL,
	[NGAY] [datetime] NOT NULL,
	[UserID] [nchar](10) NOT NULL,
	[TrangThai] [bit] NOT NULL,
	[TongTien] [float] NOT NULL,
	[DaXoa] [bit] NOT NULL,
 CONSTRAINT [PK__DONHANG__3214EC27C62BDA5D] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DONHANGCHITIET]    Script Date: 27/6/2022 4:13:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DONHANGCHITIET](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[DonHangID] [nchar](15) NOT NULL,
	[SachID] [nchar](10) NOT NULL,
	[SoLuong] [int] NOT NULL,
	[Tien] [float] NOT NULL,
 CONSTRAINT [PK__DONHANGC__3214EC27A2A21DD9] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[FAVORITE]    Script Date: 27/6/2022 4:13:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FAVORITE](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[UserID] [nchar](10) NOT NULL,
	[SachID] [nchar](10) NOT NULL,
	[LikeDate] [datetime] NOT NULL,
 CONSTRAINT [PK__FAVORITE__3214EC27A6E27FAC] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GALERY]    Script Date: 27/6/2022 4:13:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GALERY](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[SachID] [nchar](10) NOT NULL,
	[URLImage] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK__GALERY__3214EC27BD03B1F6] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GIOHANG]    Script Date: 27/6/2022 4:13:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GIOHANG](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[UserID] [nchar](10) NOT NULL,
 CONSTRAINT [PK_GIOHANG] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GIOHANGCHITIET]    Script Date: 27/6/2022 4:13:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GIOHANGCHITIET](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[GioHangID] [int] NOT NULL,
	[SachID] [nchar](10) NOT NULL,
	[SoLuong] [int] NOT NULL,
 CONSTRAINT [PK_GIOHANGCHITIET] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KHACHHANG]    Script Date: 27/6/2022 4:13:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KHACHHANG](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Fullname] [nvarchar](50) NOT NULL,
	[DiaChi] [nvarchar](max) NOT NULL,
	[SDT] [nvarchar](15) NOT NULL,
	[City] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK__KHACHHAN__3214EC27B1E55EA8] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LOAISACH]    Script Date: 27/6/2022 4:13:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LOAISACH](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[TenLoai] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NGUOIDUNG]    Script Date: 27/6/2022 4:13:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NGUOIDUNG](
	[ID] [nchar](10) NOT NULL,
	[Password] [nchar](15) NOT NULL,
	[Ho] [nvarchar](50) NOT NULL,
	[Ten] [nvarchar](50) NOT NULL,
	[Email] [nvarchar](50) NOT NULL,
	[GioiTinh] [bit] NOT NULL,
	[NgaySinh] [date] NOT NULL,
	[ChucVu] [int] NOT NULL,
	[CMND] [nvarchar](15) NULL,
	[SDT] [nvarchar](15) NULL,
	[DiaChi] [nvarchar](max) NULL,
	[TrangThai] [bit] NOT NULL,
	[DaXoa] [bit] NULL,
 CONSTRAINT [PK__NGUOIDUN__3214EC27784E74A4] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NHACUNGCAP]    Script Date: 27/6/2022 4:13:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NHACUNGCAP](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[TenNCC] [nvarchar](50) NOT NULL,
	[DiaChi] [nvarchar](max) NOT NULL,
	[SDT] [nvarchar](15) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NHAXUATBAN]    Script Date: 27/6/2022 4:13:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NHAXUATBAN](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[TenNXB] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SACH]    Script Date: 27/6/2022 4:13:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SACH](
	[ID] [nchar](10) NOT NULL,
	[LoaiSachID] [int] NOT NULL,
	[NXBID] [int] NOT NULL,
	[NCCID] [int] NOT NULL,
	[TacGiaID] [int] NOT NULL,
	[TenSach] [nvarchar](50) NOT NULL,
	[SoLuong] [int] NOT NULL,
	[Gia] [float] NOT NULL,
	[Images] [nvarchar](max) NULL,
	[Discount] [float] NOT NULL,
	[Mota] [nvarchar](max) NOT NULL,
	[NgayTao] [date] NOT NULL,
	[NgaySua] [date] NULL,
	[Dvt] [nchar](5) NOT NULL,
	[DaXoa] [bit] NOT NULL,
 CONSTRAINT [PK__SACH__3214EC2783A12A98] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TACGIA]    Script Date: 27/6/2022 4:13:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TACGIA](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Ten] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[DONHANG] ([ID], [KhachHangID], [NGAY], [UserID], [TrangThai], [TongTien], [DaXoa]) VALUES (N'DH01           ', 1, CAST(N'2022-12-12T00:00:00.000' AS DateTime), N'admin     ', 1, 100000, 0)
GO
INSERT [dbo].[DONHANG] ([ID], [KhachHangID], [NGAY], [UserID], [TrangThai], [TongTien], [DaXoa]) VALUES (N'DH02           ', 2, CAST(N'2022-12-12T00:00:00.000' AS DateTime), N'admin     ', 1, 100000, 0)
GO
INSERT [dbo].[DONHANG] ([ID], [KhachHangID], [NGAY], [UserID], [TrangThai], [TongTien], [DaXoa]) VALUES (N'DH03           ', 3, CAST(N'2022-12-12T00:00:00.000' AS DateTime), N'admin     ', 1, 100000, 0)
GO
INSERT [dbo].[DONHANG] ([ID], [KhachHangID], [NGAY], [UserID], [TrangThai], [TongTien], [DaXoa]) VALUES (N'DH04           ', 4, CAST(N'2022-12-12T00:00:00.000' AS DateTime), N'admin     ', 1, 100000, 0)
GO
INSERT [dbo].[DONHANG] ([ID], [KhachHangID], [NGAY], [UserID], [TrangThai], [TongTien], [DaXoa]) VALUES (N'DH05           ', 5, CAST(N'2022-12-12T00:00:00.000' AS DateTime), N'admin     ', 1, 100000, 1)
GO
INSERT [dbo].[DONHANG] ([ID], [KhachHangID], [NGAY], [UserID], [TrangThai], [TongTien], [DaXoa]) VALUES (N'DH06           ', 6, CAST(N'2022-12-12T00:00:00.000' AS DateTime), N'admin     ', 1, 100000, 0)
GO
INSERT [dbo].[DONHANG] ([ID], [KhachHangID], [NGAY], [UserID], [TrangThai], [TongTien], [DaXoa]) VALUES (N'DH07           ', 7, CAST(N'2022-12-12T00:00:00.000' AS DateTime), N'admin     ', 1, 100000, 0)
GO
INSERT [dbo].[DONHANG] ([ID], [KhachHangID], [NGAY], [UserID], [TrangThai], [TongTien], [DaXoa]) VALUES (N'DH08           ', 8, CAST(N'2022-12-12T00:00:00.000' AS DateTime), N'admin     ', 1, 100000, 0)
GO
INSERT [dbo].[DONHANG] ([ID], [KhachHangID], [NGAY], [UserID], [TrangThai], [TongTien], [DaXoa]) VALUES (N'DH09           ', 9, CAST(N'2022-12-12T00:00:00.000' AS DateTime), N'admin     ', 1, 100000, 0)
GO
INSERT [dbo].[DONHANG] ([ID], [KhachHangID], [NGAY], [UserID], [TrangThai], [TongTien], [DaXoa]) VALUES (N'DH10           ', 10, CAST(N'2022-12-12T00:00:00.000' AS DateTime), N'admin     ', 1, 100000, 0)
GO
INSERT [dbo].[DONHANG] ([ID], [KhachHangID], [NGAY], [UserID], [TrangThai], [TongTien], [DaXoa]) VALUES (N'DH11           ', 11, CAST(N'2022-12-12T00:00:00.000' AS DateTime), N'admin     ', 1, 100000, 0)
GO
INSERT [dbo].[DONHANG] ([ID], [KhachHangID], [NGAY], [UserID], [TrangThai], [TongTien], [DaXoa]) VALUES (N'DH12           ', 12, CAST(N'2022-12-12T00:00:00.000' AS DateTime), N'admin     ', 1, 100000, 0)
GO
INSERT [dbo].[DONHANG] ([ID], [KhachHangID], [NGAY], [UserID], [TrangThai], [TongTien], [DaXoa]) VALUES (N'DH13           ', 13, CAST(N'2022-12-12T00:00:00.000' AS DateTime), N'admin     ', 1, 100000, 0)
GO
INSERT [dbo].[DONHANG] ([ID], [KhachHangID], [NGAY], [UserID], [TrangThai], [TongTien], [DaXoa]) VALUES (N'DH14           ', 14, CAST(N'2022-12-12T00:00:00.000' AS DateTime), N'admin     ', 1, 100000, 0)
GO
INSERT [dbo].[DONHANG] ([ID], [KhachHangID], [NGAY], [UserID], [TrangThai], [TongTien], [DaXoa]) VALUES (N'DH15           ', 15, CAST(N'2022-12-12T00:00:00.000' AS DateTime), N'admin     ', 1, 100000, 1)
GO
INSERT [dbo].[DONHANG] ([ID], [KhachHangID], [NGAY], [UserID], [TrangThai], [TongTien], [DaXoa]) VALUES (N'DH16           ', 17, CAST(N'2022-06-26T00:00:00.000' AS DateTime), N'admin     ', 0, 1940000, 0)
GO
INSERT [dbo].[DONHANG] ([ID], [KhachHangID], [NGAY], [UserID], [TrangThai], [TongTien], [DaXoa]) VALUES (N'DH17           ', 18, CAST(N'2022-06-26T00:00:00.000' AS DateTime), N'admin     ', 1, 1940000, 0)
GO
INSERT [dbo].[DONHANG] ([ID], [KhachHangID], [NGAY], [UserID], [TrangThai], [TongTien], [DaXoa]) VALUES (N'DH18           ', 19, CAST(N'2022-06-26T00:00:00.000' AS DateTime), N'user1     ', 0, 110000, 1)
GO
INSERT [dbo].[DONHANG] ([ID], [KhachHangID], [NGAY], [UserID], [TrangThai], [TongTien], [DaXoa]) VALUES (N'DH19           ', 20, CAST(N'2022-06-26T00:00:00.000' AS DateTime), N'admin     ', 0, 330000, 0)
GO
INSERT [dbo].[DONHANG] ([ID], [KhachHangID], [NGAY], [UserID], [TrangThai], [TongTien], [DaXoa]) VALUES (N'DH20           ', 21, CAST(N'2022-06-26T00:00:00.000' AS DateTime), N'admin     ', 0, 330000, 0)
GO
INSERT [dbo].[DONHANG] ([ID], [KhachHangID], [NGAY], [UserID], [TrangThai], [TongTien], [DaXoa]) VALUES (N'DH21           ', 22, CAST(N'2022-06-27T00:00:00.000' AS DateTime), N'admin     ', 0, 220000, 1)
GO
INSERT [dbo].[DONHANG] ([ID], [KhachHangID], [NGAY], [UserID], [TrangThai], [TongTien], [DaXoa]) VALUES (N'DH22           ', 23, CAST(N'2022-06-27T00:00:00.000' AS DateTime), N'admin     ', 0, 330000, 1)
GO
INSERT [dbo].[DONHANG] ([ID], [KhachHangID], [NGAY], [UserID], [TrangThai], [TongTien], [DaXoa]) VALUES (N'DH23           ', 24, CAST(N'2022-06-27T00:00:00.000' AS DateTime), N'admin     ', 0, 220000, 0)
GO
SET IDENTITY_INSERT [dbo].[DONHANGCHITIET] ON 
GO
INSERT [dbo].[DONHANGCHITIET] ([ID], [DonHangID], [SachID], [SoLuong], [Tien]) VALUES (1, N'DH01           ', N'SACH01    ', 1, 110000)
GO
INSERT [dbo].[DONHANGCHITIET] ([ID], [DonHangID], [SachID], [SoLuong], [Tien]) VALUES (2, N'DH02           ', N'SACH02    ', 1, 110000)
GO
INSERT [dbo].[DONHANGCHITIET] ([ID], [DonHangID], [SachID], [SoLuong], [Tien]) VALUES (3, N'DH03           ', N'SACH03    ', 1, 110000)
GO
INSERT [dbo].[DONHANGCHITIET] ([ID], [DonHangID], [SachID], [SoLuong], [Tien]) VALUES (4, N'DH04           ', N'SACH04    ', 1, 110000)
GO
INSERT [dbo].[DONHANGCHITIET] ([ID], [DonHangID], [SachID], [SoLuong], [Tien]) VALUES (5, N'DH05           ', N'SACH05    ', 1, 110000)
GO
INSERT [dbo].[DONHANGCHITIET] ([ID], [DonHangID], [SachID], [SoLuong], [Tien]) VALUES (6, N'DH06           ', N'SACH06    ', 1, 110000)
GO
INSERT [dbo].[DONHANGCHITIET] ([ID], [DonHangID], [SachID], [SoLuong], [Tien]) VALUES (7, N'DH07           ', N'SACH07    ', 1, 110000)
GO
INSERT [dbo].[DONHANGCHITIET] ([ID], [DonHangID], [SachID], [SoLuong], [Tien]) VALUES (8, N'DH08           ', N'SACH08    ', 1, 110000)
GO
INSERT [dbo].[DONHANGCHITIET] ([ID], [DonHangID], [SachID], [SoLuong], [Tien]) VALUES (9, N'DH09           ', N'SACH09    ', 1, 110000)
GO
INSERT [dbo].[DONHANGCHITIET] ([ID], [DonHangID], [SachID], [SoLuong], [Tien]) VALUES (10, N'DH10           ', N'SACH10    ', 1, 110000)
GO
INSERT [dbo].[DONHANGCHITIET] ([ID], [DonHangID], [SachID], [SoLuong], [Tien]) VALUES (11, N'DH11           ', N'SACH11    ', 1, 110000)
GO
INSERT [dbo].[DONHANGCHITIET] ([ID], [DonHangID], [SachID], [SoLuong], [Tien]) VALUES (12, N'DH12           ', N'SACH12    ', 1, 110000)
GO
INSERT [dbo].[DONHANGCHITIET] ([ID], [DonHangID], [SachID], [SoLuong], [Tien]) VALUES (13, N'DH13           ', N'SACH13    ', 1, 110000)
GO
INSERT [dbo].[DONHANGCHITIET] ([ID], [DonHangID], [SachID], [SoLuong], [Tien]) VALUES (14, N'DH14           ', N'SACH14    ', 1, 110000)
GO
INSERT [dbo].[DONHANGCHITIET] ([ID], [DonHangID], [SachID], [SoLuong], [Tien]) VALUES (15, N'DH15           ', N'SACH15    ', 1, 110000)
GO
INSERT [dbo].[DONHANGCHITIET] ([ID], [DonHangID], [SachID], [SoLuong], [Tien]) VALUES (16, N'DH16           ', N'SACH16    ', 2, 750000)
GO
INSERT [dbo].[DONHANGCHITIET] ([ID], [DonHangID], [SachID], [SoLuong], [Tien]) VALUES (17, N'DH16           ', N'SACH02    ', 1, 110000)
GO
INSERT [dbo].[DONHANGCHITIET] ([ID], [DonHangID], [SachID], [SoLuong], [Tien]) VALUES (18, N'DH16           ', N'SACH01    ', 3, 110000)
GO
INSERT [dbo].[DONHANGCHITIET] ([ID], [DonHangID], [SachID], [SoLuong], [Tien]) VALUES (19, N'DH17           ', N'SACH16    ', 2, 750000)
GO
INSERT [dbo].[DONHANGCHITIET] ([ID], [DonHangID], [SachID], [SoLuong], [Tien]) VALUES (20, N'DH17           ', N'SACH02    ', 1, 110000)
GO
INSERT [dbo].[DONHANGCHITIET] ([ID], [DonHangID], [SachID], [SoLuong], [Tien]) VALUES (21, N'DH17           ', N'SACH01    ', 3, 110000)
GO
INSERT [dbo].[DONHANGCHITIET] ([ID], [DonHangID], [SachID], [SoLuong], [Tien]) VALUES (22, N'DH18           ', N'SACH01    ', 1, 110000)
GO
INSERT [dbo].[DONHANGCHITIET] ([ID], [DonHangID], [SachID], [SoLuong], [Tien]) VALUES (23, N'DH19           ', N'SACH03    ', 1, 110000)
GO
INSERT [dbo].[DONHANGCHITIET] ([ID], [DonHangID], [SachID], [SoLuong], [Tien]) VALUES (24, N'DH20           ', N'SACH03    ', 1, 110000)
GO
INSERT [dbo].[DONHANGCHITIET] ([ID], [DonHangID], [SachID], [SoLuong], [Tien]) VALUES (25, N'DH21           ', N'SACH01    ', 1, 110000)
GO
INSERT [dbo].[DONHANGCHITIET] ([ID], [DonHangID], [SachID], [SoLuong], [Tien]) VALUES (26, N'DH22           ', N'SACH03    ', 2, 220000)
GO
INSERT [dbo].[DONHANGCHITIET] ([ID], [DonHangID], [SachID], [SoLuong], [Tien]) VALUES (27, N'DH22           ', N'SACH13    ', 1, 110000)
GO
INSERT [dbo].[DONHANGCHITIET] ([ID], [DonHangID], [SachID], [SoLuong], [Tien]) VALUES (28, N'DH23           ', N'SACH14    ', 2, 110000)
GO
INSERT [dbo].[DONHANGCHITIET] ([ID], [DonHangID], [SachID], [SoLuong], [Tien]) VALUES (29, N'DH23           ', N'SACH02    ', 1, 110000)
GO
SET IDENTITY_INSERT [dbo].[DONHANGCHITIET] OFF
GO
SET IDENTITY_INSERT [dbo].[FAVORITE] ON 
GO
INSERT [dbo].[FAVORITE] ([ID], [UserID], [SachID], [LikeDate]) VALUES (1, N'admin     ', N'SACH01    ', CAST(N'2022-01-01T00:00:00.000' AS DateTime))
GO
INSERT [dbo].[FAVORITE] ([ID], [UserID], [SachID], [LikeDate]) VALUES (2, N'admin     ', N'SACH02    ', CAST(N'2022-01-01T00:00:00.000' AS DateTime))
GO
INSERT [dbo].[FAVORITE] ([ID], [UserID], [SachID], [LikeDate]) VALUES (3, N'admin     ', N'SACH03    ', CAST(N'2022-01-01T00:00:00.000' AS DateTime))
GO
INSERT [dbo].[FAVORITE] ([ID], [UserID], [SachID], [LikeDate]) VALUES (4, N'admin     ', N'SACH04    ', CAST(N'2022-01-01T00:00:00.000' AS DateTime))
GO
INSERT [dbo].[FAVORITE] ([ID], [UserID], [SachID], [LikeDate]) VALUES (5, N'admin     ', N'SACH05    ', CAST(N'2022-01-01T00:00:00.000' AS DateTime))
GO
INSERT [dbo].[FAVORITE] ([ID], [UserID], [SachID], [LikeDate]) VALUES (7, N'admin2    ', N'SACH07    ', CAST(N'2022-01-01T00:00:00.000' AS DateTime))
GO
INSERT [dbo].[FAVORITE] ([ID], [UserID], [SachID], [LikeDate]) VALUES (8, N'admin2    ', N'SACH08    ', CAST(N'2022-01-01T00:00:00.000' AS DateTime))
GO
INSERT [dbo].[FAVORITE] ([ID], [UserID], [SachID], [LikeDate]) VALUES (9, N'admin2    ', N'SACH09    ', CAST(N'2022-01-01T00:00:00.000' AS DateTime))
GO
INSERT [dbo].[FAVORITE] ([ID], [UserID], [SachID], [LikeDate]) VALUES (10, N'admin2    ', N'SACH10    ', CAST(N'2022-01-01T00:00:00.000' AS DateTime))
GO
INSERT [dbo].[FAVORITE] ([ID], [UserID], [SachID], [LikeDate]) VALUES (11, N'admin2    ', N'SACH11    ', CAST(N'2022-01-01T00:00:00.000' AS DateTime))
GO
INSERT [dbo].[FAVORITE] ([ID], [UserID], [SachID], [LikeDate]) VALUES (12, N'staff1    ', N'SACH12    ', CAST(N'2022-01-01T00:00:00.000' AS DateTime))
GO
INSERT [dbo].[FAVORITE] ([ID], [UserID], [SachID], [LikeDate]) VALUES (13, N'staff1    ', N'SACH13    ', CAST(N'2022-01-01T00:00:00.000' AS DateTime))
GO
INSERT [dbo].[FAVORITE] ([ID], [UserID], [SachID], [LikeDate]) VALUES (14, N'user1     ', N'SACH13    ', CAST(N'2022-01-01T00:00:00.000' AS DateTime))
GO
INSERT [dbo].[FAVORITE] ([ID], [UserID], [SachID], [LikeDate]) VALUES (15, N'user1     ', N'SACH15    ', CAST(N'2022-01-01T00:00:00.000' AS DateTime))
GO
SET IDENTITY_INSERT [dbo].[FAVORITE] OFF
GO
SET IDENTITY_INSERT [dbo].[GALERY] ON 
GO
INSERT [dbo].[GALERY] ([ID], [SachID], [URLImage]) VALUES (1, N'SACH01    ', N'SACH01.PNG')
GO
INSERT [dbo].[GALERY] ([ID], [SachID], [URLImage]) VALUES (2, N'SACH01    ', N'SACH02.PNG')
GO
INSERT [dbo].[GALERY] ([ID], [SachID], [URLImage]) VALUES (3, N'SACH01    ', N'SACH03.PNG')
GO
INSERT [dbo].[GALERY] ([ID], [SachID], [URLImage]) VALUES (4, N'SACH01    ', N'SACH04.PNG')
GO
INSERT [dbo].[GALERY] ([ID], [SachID], [URLImage]) VALUES (5, N'SACH01    ', N'SACH05.PNG')
GO
INSERT [dbo].[GALERY] ([ID], [SachID], [URLImage]) VALUES (6, N'SACH02    ', N'SACH06.PNG')
GO
INSERT [dbo].[GALERY] ([ID], [SachID], [URLImage]) VALUES (7, N'SACH02    ', N'SACH07.PNG')
GO
INSERT [dbo].[GALERY] ([ID], [SachID], [URLImage]) VALUES (8, N'SACH02    ', N'SACH08.PNG')
GO
INSERT [dbo].[GALERY] ([ID], [SachID], [URLImage]) VALUES (9, N'SACH02    ', N'SACH09.PNG')
GO
INSERT [dbo].[GALERY] ([ID], [SachID], [URLImage]) VALUES (10, N'SACH02    ', N'SACH10.PNG')
GO
INSERT [dbo].[GALERY] ([ID], [SachID], [URLImage]) VALUES (11, N'SACH03    ', N'SACH11.PNG')
GO
INSERT [dbo].[GALERY] ([ID], [SachID], [URLImage]) VALUES (12, N'SACH03    ', N'SACH12.PNG')
GO
INSERT [dbo].[GALERY] ([ID], [SachID], [URLImage]) VALUES (13, N'SACH03    ', N'SACH13.PNG')
GO
INSERT [dbo].[GALERY] ([ID], [SachID], [URLImage]) VALUES (14, N'SACH03    ', N'SACH14.PNG')
GO
INSERT [dbo].[GALERY] ([ID], [SachID], [URLImage]) VALUES (15, N'SACH03    ', N'SACH15.PNG')
GO
SET IDENTITY_INSERT [dbo].[GALERY] OFF
GO
SET IDENTITY_INSERT [dbo].[GIOHANG] ON 
GO
INSERT [dbo].[GIOHANG] ([ID], [UserID]) VALUES (1, N'babara    ')
GO
SET IDENTITY_INSERT [dbo].[GIOHANG] OFF
GO
SET IDENTITY_INSERT [dbo].[GIOHANGCHITIET] ON 
GO
INSERT [dbo].[GIOHANGCHITIET] ([ID], [GioHangID], [SachID], [SoLuong]) VALUES (1, 1, N'SACH01    ', 2)
GO
SET IDENTITY_INSERT [dbo].[GIOHANGCHITIET] OFF
GO
SET IDENTITY_INSERT [dbo].[KHACHHANG] ON 
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (1, N'Trần Hùng Dũng', N'55A ngô quyèn', N'0345665226', N'Cần Thơ')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (2, N'Trần Anh Dũng', N'55A  quyèn', N'0345335229', N'Cần Thơ')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (3, N'Trần Tuấn Dũng', N'55A quang quyèn', N'0345338226', N'Hồ Chí Minh')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (4, N'Trần Kinh Dũng', N'55A hạ quyèn', N'0345635226', N'Huế')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (5, N'Trần Nghê Dũng', N'55A kinh quyèn', N'0155335226', N'Huế')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (6, N'Trần Bá Dũng', N'55A gio quyèn', N'0345935226', N'Huế')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (7, N'Trần Chi Dũng', N'55A pao quyèn', N'0348335226', N'Đà Nẵng')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (8, N'Trần Sara Dũng', N'55A pasu quyèn', N'0245335226', N'Đà Nẵng')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (9, N'Nguyễn Tuyết Dũng', N'55A qua quyèn', N'0323335226', N'Đà Nẵng')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (10, N'Nguyễn Hùng Dũng', N'55A quang trung', N'0255335226', N'Hồ Chí Minh')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (11, N'Nguyễn Chi Dũng', N'55A ngô trung', N'090335226', N'Hồ Chí Minh')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (12, N'Nguyễn Trương Dũng', N'55A nguyễn huệ', N'0545335226', N'Hồ Chí Minh')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (13, N'Nghê Hùng Dũng', N'55A nguyễn trãi', N'0745335226', N'Vĩnh Long')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (14, N'Nghê Trần Dũng', N'55A nguyễn ánh', N'0345335226', N'Vĩnh Long')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (15, N'Nghê Bá Dũng', N'55A ngô nguyễn lữ', N'0245335226', N'Vĩnh Long')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (16, N'Khoa Tran Anh', N'123 saint pt te', N'0345335273', N'Can Tho')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (17, N'Khoa Tran Anh', N'123 saint pt te', N'0345335273', N'Can Tho')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (18, N'Khoa Tran Anh', N'123 saint pt te', N'0345335273', N'Can Tho')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (19, N'Khoa Tran Anh', N'123 saint pt te', N'0345335273', N'Can Tho')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (20, N'asd', N'123 saint pt te', N'0345335273', N'Can Tho')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (21, N'admin', N'123 saint pt te', N'0345335273', N'Can Tho')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (22, N'Khoa Tran Anh', N'123 saint pt te', N'0345335273', N'Can Tho')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (23, N'asd', N'123 saint pt te', N'0345335273', N'Can Tho')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (24, N'admin', N'123 saint pt te', N'0345335273', N'Can Tho')
GO
SET IDENTITY_INSERT [dbo].[KHACHHANG] OFF
GO
SET IDENTITY_INSERT [dbo].[LOAISACH] ON 
GO
INSERT [dbo].[LOAISACH] ([ID], [TenLoai]) VALUES (1, N'VĂN HỌC')
GO
INSERT [dbo].[LOAISACH] ([ID], [TenLoai]) VALUES (2, N'KINH TẾ')
GO
INSERT [dbo].[LOAISACH] ([ID], [TenLoai]) VALUES (3, N'THIẾU NHI')
GO
INSERT [dbo].[LOAISACH] ([ID], [TenLoai]) VALUES (4, N'TÂM LÍ')
GO
INSERT [dbo].[LOAISACH] ([ID], [TenLoai]) VALUES (5, N'NUÔI DẠY CON')
GO
INSERT [dbo].[LOAISACH] ([ID], [TenLoai]) VALUES (6, N'GIÁO KHOA')
GO
INSERT [dbo].[LOAISACH] ([ID], [TenLoai]) VALUES (7, N'THAM KHẢO')
GO
INSERT [dbo].[LOAISACH] ([ID], [TenLoai]) VALUES (8, N'SÁCH HỌC NGOẠI NGỮ')
GO
INSERT [dbo].[LOAISACH] ([ID], [TenLoai]) VALUES (9, N'TIỂU SỬ')
GO
INSERT [dbo].[LOAISACH] ([ID], [TenLoai]) VALUES (10, N'HỒI KÍ')
GO
INSERT [dbo].[LOAISACH] ([ID], [TenLoai]) VALUES (11, N'KỸ NĂNG SỐNG')
GO
INSERT [dbo].[LOAISACH] ([ID], [TenLoai]) VALUES (12, N'NẤU ĂN')
GO
INSERT [dbo].[LOAISACH] ([ID], [TenLoai]) VALUES (13, N'CÂY TRỒNG')
GO
INSERT [dbo].[LOAISACH] ([ID], [TenLoai]) VALUES (14, N'NGHỆ THUẬT')
GO
INSERT [dbo].[LOAISACH] ([ID], [TenLoai]) VALUES (15, N'VPP-DỤNG CỤ HỌC SINH')
GO
SET IDENTITY_INSERT [dbo].[LOAISACH] OFF
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'admin     ', N'admin          ', N'admin', N'admin', N'kirudono@gmail.com', 1, CAST(N'1995-12-12' AS Date), 1, N'0000000000', N'0000000000', N'0', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'admin2    ', N'admin2         ', N'Admin', N'Admin', N'admin2@gmail.com', 1, CAST(N'1988-07-07' AS Date), 1, N'090123456122', N'0345999999', N'55 quang trung, hà nội', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'admin22   ', N'admin22        ', N'admin', N'Khoa Tran Anh', N'khboabe2o@gmail.com', 1, CAST(N'2022-06-14' AS Date), 1, N'11111111111', N'0345335253', N'123 saint pt te', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'admind    ', N'asdasd         ', N'tran', N'Khoa Tran Anh', N'khbodabeo@gmail.com', 1, CAST(N'2022-06-08' AS Date), 2, N'012456789', N'0345335253', N'123 saint pt te', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'asdasd    ', N'asdasd         ', N'asd', N'asd', N'kirudono@gmail.com', 1, CAST(N'2022-05-31' AS Date), 3, N'0000000000', N'0000000000', N'0', 0, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'babara    ', N'babara         ', N'Hai', N'Bara', N'babara@gmail.com', 0, CAST(N'2000-12-12' AS Date), 3, N'0000000000', N'0000000000', N'55 quang trung, hà nội', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'java5     ', N'java5          ', N'Kinh', N'Qua', N'java5@gmail.com', 1, CAST(N'2002-12-12' AS Date), 3, N'0000000000', N'0000000000', N'55 quang trung, hà nội', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'kazuz     ', N'kazuz          ', N'Trần', N'Tuyết Nhung', N'kazuz@gmail.com', 0, CAST(N'1999-12-12' AS Date), 3, N'0000000000', N'0000000000', N'55 quang trung, hà nội', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'khoaQL    ', N'sadasd         ', N'tran', N'anh', N'khboabeo@gmail.com', 1, CAST(N'2022-06-08' AS Date), 1, N'0123456789', N'0345335273', N'123 saint pt te', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'kinzal    ', N'kinzal         ', N'Trần', N'Tuấn', N'kinzal@gmail.com', 1, CAST(N'1988-12-12' AS Date), 3, N'0000000000', N'0000000000', N'55 quang trung, hà nội', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'staff1    ', N'staff1         ', N'Nhân', N'Viên', N'staff1@gmail.com', 1, CAST(N'2003-12-12' AS Date), 2, N'030123426122', N'0905325123', N'55 quang trung, hà nội', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'staff2    ', N'staff2         ', N'Nhân', N'Viên Hai', N'staff2@gmail.com', 0, CAST(N'2002-11-18' AS Date), 2, N'030153456122', N'090511123', N'55 quang trung, hà nội', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'staff3    ', N'staff3         ', N'Nhân', N'Viên Ba', N'staff3@gmail.com', 0, CAST(N'2003-12-01' AS Date), 2, N'030143456122', N'090545123', N'55 quang trung, hà nội', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'staff4    ', N'staff4         ', N'Nhân', N'Viên Bốn', N'staff4@gmail.com', 1, CAST(N'2000-10-12' AS Date), 2, N'090523456122', N'090552123', N'55 quang trung, hà nội', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'staff5    ', N'staff5         ', N'Nhân', N'Viên Năm', N'staff5@gmail.com', 1, CAST(N'1998-10-12' AS Date), 2, N'090423456122', N'090555548', N'55 quang trung, hà nội', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'staff6    ', N'staff6         ', N'Nhân', N'Viên Sáu', N'staff6@gmail.com', 1, CAST(N'1997-11-12' AS Date), 2, N'090323456122', N'0345335335', N'55 quang trung, hà nội', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'test222   ', N'test222        ', N'test222', N'test222', N'khoabro@gmail.com', 1, CAST(N'2022-06-14' AS Date), 3, N'0000000000', N'0000000000', N'0', 0, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'test6     ', N'test6          ', N'test6', N'test6', N'khoabro@gmail.com', 1, CAST(N'2022-06-07' AS Date), 3, N'0000000000', N'0000000000', N'0', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'user1     ', N'user1          ', N'José', N'Mazuro', N'user1@gmail.com', 1, CAST(N'1995-12-12' AS Date), 3, N'0000000000', N'0000000000', N'55 quang trung, hà nội', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'user2     ', N'user2          ', N'Nguyễn', N'Anh', N'user2@gmail.com', 1, CAST(N'1996-12-12' AS Date), 3, N'0000000000', N'0000000000', N'55 quang trung, hà nội', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'user3     ', N'user3          ', N'Nghê', N'Thường', N'user3@gmail.com', 1, CAST(N'1997-12-12' AS Date), 3, N'0000000000', N'0000000000', N'55 quang trung, hà nội', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'zxczxc    ', N'zxczczc        ', N'zxczczc', N'zxczxc', N'zczxc@gmail.com', 0, CAST(N'2022-06-06' AS Date), 3, N'0000000000', N'0000000000', N'0', 1, 1)
GO
SET IDENTITY_INSERT [dbo].[NHACUNGCAP] ON 
GO
INSERT [dbo].[NHACUNGCAP] ([ID], [TenNCC], [DiaChi], [SDT]) VALUES (1, N'KIM ĐỒNG', N'55 Quang Trung, Hai Bà Trưng, Hà Nội', N'1900571595')
GO
INSERT [dbo].[NHACUNGCAP] ([ID], [TenNCC], [DiaChi], [SDT]) VALUES (2, N'SKYBOOKS', N'155 Quang Trung, Hai Bà Trưng, Hà Nội', N'1900111222')
GO
INSERT [dbo].[NHACUNGCAP] ([ID], [TenNCC], [DiaChi], [SDT]) VALUES (3, N'NHÃ NAM', N'10 Quang Trung, Hai Bà Trưng, Hà Nội', N'1900777888')
GO
INSERT [dbo].[NHACUNGCAP] ([ID], [TenNCC], [DiaChi], [SDT]) VALUES (4, N'IPM', N'15 Quang Trung, Hai Bà Trưng, Hà Nội', N'1900666555')
GO
INSERT [dbo].[NHACUNGCAP] ([ID], [TenNCC], [DiaChi], [SDT]) VALUES (5, N'MINH LONG', N'20 Quang Trung, Hai Bà Trưng, Hà Nội', N'1900888999')
GO
INSERT [dbo].[NHACUNGCAP] ([ID], [TenNCC], [DiaChi], [SDT]) VALUES (6, N'BÁCH VIỆT', N'11 Quang Trung, Hai Bà Trưng, Hà Nội', N'1900121121')
GO
INSERT [dbo].[NHACUNGCAP] ([ID], [TenNCC], [DiaChi], [SDT]) VALUES (7, N'QUẢNG VĂN', N'22 Quang Trung, Hai Bà Trưng, Hà Nội', N'1900363363')
GO
INSERT [dbo].[NHACUNGCAP] ([ID], [TenNCC], [DiaChi], [SDT]) VALUES (8, N'ĐINH TỊ', N'33 Quang Trung, Hai Bà Trưng, Hà Nội', N'1900575575')
GO
INSERT [dbo].[NHACUNGCAP] ([ID], [TenNCC], [DiaChi], [SDT]) VALUES (9, N'HOA HỌC TRÒ', N'105 Quang Trung, Hai Bà Trưng, Hà Nội', N'1900222888')
GO
INSERT [dbo].[NHACUNGCAP] ([ID], [TenNCC], [DiaChi], [SDT]) VALUES (10, N'NHÀ XUẤT BẢN TRẺ', N'125 Quang Trung, Hai Bà Trưng, Hà Nội', N'1900198098')
GO
INSERT [dbo].[NHACUNGCAP] ([ID], [TenNCC], [DiaChi], [SDT]) VALUES (11, N'ALPHABOOKS', N'60 Quang Trung, Hai Bà Trưng, Hà Nội', N'1900369696')
GO
INSERT [dbo].[NHACUNGCAP] ([ID], [TenNCC], [DiaChi], [SDT]) VALUES (12, N'HUY HOÀNG JSC', N'70 Quang Trung, Hai Bà Trưng, Hà Nội', N'1900812335')
GO
INSERT [dbo].[NHACUNGCAP] ([ID], [TenNCC], [DiaChi], [SDT]) VALUES (13, N'AZ VIETNAM', N'80 Quang Trung, Hai Bà Trưng, Hà Nội', N'1900444555')
GO
INSERT [dbo].[NHACUNGCAP] ([ID], [TenNCC], [DiaChi], [SDT]) VALUES (14, N'CHIBOOKS', N'88 Quang Trung, Hai Bà Trưng, Hà Nội', N'1900333777')
GO
INSERT [dbo].[NHACUNGCAP] ([ID], [TenNCC], [DiaChi], [SDT]) VALUES (15, N'ĐÔNG A', N'99 Quang Trung, Hai Bà Trưng, Hà Nội', N'1900180012')
GO
INSERT [dbo].[NHACUNGCAP] ([ID], [TenNCC], [DiaChi], [SDT]) VALUES (17, N'THIÊN LONG HOÀN CẦU', N'55 Lê Thị Riêng, HCM', N'1900808080')
GO
SET IDENTITY_INSERT [dbo].[NHACUNGCAP] OFF
GO
SET IDENTITY_INSERT [dbo].[NHAXUATBAN] ON 
GO
INSERT [dbo].[NHAXUATBAN] ([ID], [TenNXB]) VALUES (1, N'KIM ĐỒNG')
GO
INSERT [dbo].[NHAXUATBAN] ([ID], [TenNXB]) VALUES (2, N'TRẺ')
GO
INSERT [dbo].[NHAXUATBAN] ([ID], [TenNXB]) VALUES (3, N'DÂN TRÍ')
GO
INSERT [dbo].[NHAXUATBAN] ([ID], [TenNXB]) VALUES (4, N'GIÁO DỤC')
GO
INSERT [dbo].[NHAXUATBAN] ([ID], [TenNXB]) VALUES (5, N'HỘI NHÀ VĂN')
GO
INSERT [dbo].[NHAXUATBAN] ([ID], [TenNXB]) VALUES (6, N'TƯ PHÁP')
GO
INSERT [dbo].[NHAXUATBAN] ([ID], [TenNXB]) VALUES (7, N'HỒNG ĐỨC')
GO
INSERT [dbo].[NHAXUATBAN] ([ID], [TenNXB]) VALUES (8, N'LAO ĐỘNG')
GO
INSERT [dbo].[NHAXUATBAN] ([ID], [TenNXB]) VALUES (9, N'GIAO THÔNG VẬN TẢI')
GO
INSERT [dbo].[NHAXUATBAN] ([ID], [TenNXB]) VALUES (10, N'ĐẠI HỌC QUỐC GIA HÀ NỘI')
GO
INSERT [dbo].[NHAXUATBAN] ([ID], [TenNXB]) VALUES (11, N'HỒNG ĐỨC')
GO
INSERT [dbo].[NHAXUATBAN] ([ID], [TenNXB]) VALUES (12, N'KHOA HỌC XÃ HỘI')
GO
INSERT [dbo].[NHAXUATBAN] ([ID], [TenNXB]) VALUES (13, N'TÔN GIÁO')
GO
INSERT [dbo].[NHAXUATBAN] ([ID], [TenNXB]) VALUES (14, N'TRI THỨC')
GO
INSERT [dbo].[NHAXUATBAN] ([ID], [TenNXB]) VALUES (15, N'Y HỌC')
GO
INSERT [dbo].[NHAXUATBAN] ([ID], [TenNXB]) VALUES (16, N'THIÊN LONG')
GO
SET IDENTITY_INSERT [dbo].[NHAXUATBAN] OFF
GO
INSERT [dbo].[SACH] ([ID], [LoaiSachID], [NXBID], [NCCID], [TacGiaID], [TenSach], [SoLuong], [Gia], [Images], [Discount], [Mota], [NgayTao], [NgaySua], [Dvt], [DaXoa]) VALUES (N'SACH01    ', 1, 5, 3, 1, N'Cây Cam Ngọt Của Tôi', 99, 110000, N'caycam.png', 0, N'Mở đầu bằng những thanh âm trong sáng và kết thúc lắng lại trong những nốt trầm hoài niệm, Cây cam ngọt của tôi khiến ta nhận ra vẻ đẹp thực sự của cuộc sống đến từ những điều giản dị như bông hoa trắng của cái cây sau nhà, và rằng cuộc đời thật khốn khổ nếu thiếu đi lòng yêu thương và niềm trắc ẩn. Cuốn sách kinh điển này bởi thế không ngừng khiến trái tim người đọc khắp thế giới thổn thức, kể từ khi ra mắt lần đầu năm 1968 tại Brazil.', CAST(N'2022-05-20' AS Date), NULL, N'Đ    ', 0)
GO
INSERT [dbo].[SACH] ([ID], [LoaiSachID], [NXBID], [NCCID], [TacGiaID], [TenSach], [SoLuong], [Gia], [Images], [Discount], [Mota], [NgayTao], [NgaySua], [Dvt], [DaXoa]) VALUES (N'SACH02    ', 2, 2, 2, 2, N'Nhà Lãnh Đạo Không Chức Danh', 99, 110000, N'nhalanhdao.png', 0, N'Suốt hơn 15 năm, Robin Sharma đã thầm lặng chia sẻ với những công ty trong danh sách Fortune 500 và nhiều người siêu giàu khác một công thức thành công đã giúp ông trở thành một trong những nhà cố vấn lãnh đạo được theo đuổi nhiều nhất thế giới. Đây là lần đầu tiên Sharma công bố công thức độc quyền này với bạn, để bạn có thể đạt được những gì tốt nhất, đồng thời giúp tổ chức của bạn có thể có những bước đột phá đến một cấp độ thành công mới trong thời đại thiên biến vạn hóa như hiện nay.', CAST(N'2022-05-20' AS Date), NULL, N'Đ    ', 0)
GO
INSERT [dbo].[SACH] ([ID], [LoaiSachID], [NXBID], [NCCID], [TacGiaID], [TenSach], [SoLuong], [Gia], [Images], [Discount], [Mota], [NgayTao], [NgaySua], [Dvt], [DaXoa]) VALUES (N'SACH03    ', 3, 3, 3, 3, N'Cổ Oai Ơi! - Tập 16: Làm Gì Khi Sổ Mũi?', 100, 110000, N'cooaioi.png', 0, N'Bộ truyện Cổ Oai Ơi! là bộ truyện tranh giáo dục y khoa dành cho trẻ nhỏ, đặc biệt là các bé ở độ tuổi từ 2-8 tuổi. Đây là những tập truyện tranh đơn giản vui tươi tình cảm, giúp con nhận biết và có được những kiến thức cơ bản thật sự cần thiết cho việc phòng ngừa bệnh, phòng ngừa tai nạn, và những hành vi chăm sóc sức khoẻ đúng đắn cần thiết cho cá nhân con và cho xã hội!', CAST(N'2022-05-20' AS Date), NULL, N'Đ    ', 0)
GO
INSERT [dbo].[SACH] ([ID], [LoaiSachID], [NXBID], [NCCID], [TacGiaID], [TenSach], [SoLuong], [Gia], [Images], [Discount], [Mota], [NgayTao], [NgaySua], [Dvt], [DaXoa]) VALUES (N'SACH04    ', 4, 4, 4, 4, N'Thiên Tài Bên Trái, Kẻ Điên Bên Phải ', 100, 110000, N'thientai.png', 0, N'Hỡi những con người đang oằn mình trong cuộc sống, bạn biết gì về thế giới của mình? Là vô vàn thứ lý thuyết được các bậc vĩ nhân kiểm chứng, là luật lệ, là cả nghìn thứ sự thật bọc trong cái lốt hiển nhiên, hay những triết lý cứng nhắc của cuộc đời?

Lại đây, vượt qua thứ nhận thức tẻ nhạt bị đóng kín bằng con mắt trần gian, khai mở toàn bộ suy nghĩ, để dòng máu trong bạn sục sôi trước những điều kỳ vĩ, phá vỡ mọi quy tắc. Thế giới sẽ gọi bạn là kẻ điên, nhưng vậy thì có sao? Ranh giới duy nhất giữa kẻ điên và thiên tài chẳng qua là một sợi chỉ mỏng manh: Thiên tài chứng minh được thế giới của mình, còn kẻ điên chưa kịp làm điều đó. Chọn trở thành một kẻ điên để vẫy vùng giữa nhân gian loạn thế hay khóa hết chúng lại, sống mãi một cuộc đời bình thường khiến bạn cảm thấy hạnh phúc hơn?

Thiên tài bên trái, kẻ điên bên phải là cuốn sách dành cho những người điên rồ, những kẻ gây rối, những người chống đối, những mảnh ghép hình tròn trong những ô vuông không vừa vặn… những người nhìn mọi thứ khác biệt, không quan tâm đến quy tắc. Bạn có thể đồng ý, có thể phản đối, có thể vinh danh hay lăng mạ họ, nhưng điều duy nhất bạn không thể làm là phủ nhận sự tồn tại của họ. Đó là những người luôn tạo ra sự thay đổi trong khi hầu hết con người chỉ sống rập khuôn như một cái máy. Đa số đều nghĩ họ thật điên rồ nhưng nếu nhìn ở góc khác, ta lại thấy họ thiên tài. Bởi chỉ những người đủ điên nghĩ rằng họ có thể thay đổi thế giới mới là những người làm được điều đó. 

Chào mừng đến với thế giới của những kẻ điên.

Mã hàng	8936186546815
Tên Nhà Cung Cấp	AZ Việt Nam
Tác giả	Cao Minh
Người Dịch	Thu Hương
NXB	NXB Thế Giới
Năm XB	2021
Ngôn Ngữ	Tiếng Việt
Trọng lượng (gr)	450
Kích Thước Bao Bì	24 x 16 cm
Số trang	424
Hình thức	Bìa Mềm
Sản phẩm hiển thị trong	
AZ Việt Nam
Đồ Chơi Cho Bé - Giá Cực Tốt
PNJ
Tủ Sách Tâm Lý Kỹ Năng
Sản phẩm bán chạy nhất	Top 100 sản phẩm Tâm lý bán chạy của tháng
Thiên Tài Bên Trái, Kẻ Điên Bên Phải

NẾU MỘT NGÀY ANH THẤY TÔI ĐIÊN, THỰC RA CHÍNH LÀ ANH ĐIÊN ĐẤY!

Hỡi những con người đang oằn mình trong cuộc sống, bạn biết gì về thế giới của mình? Là vô vàn thứ lý thuyết được các bậc vĩ nhân kiểm chứng, là luật lệ, là cả nghìn thứ sự thật bọc trong cái lốt hiển nhiên, hay những triết lý cứng nhắc của cuộc đời?', CAST(N'2022-05-20' AS Date), NULL, N'Đ    ', 0)
GO
INSERT [dbo].[SACH] ([ID], [LoaiSachID], [NXBID], [NCCID], [TacGiaID], [TenSach], [SoLuong], [Gia], [Images], [Discount], [Mota], [NgayTao], [NgaySua], [Dvt], [DaXoa]) VALUES (N'SACH05    ', 5, 5, 5, 5, N'Dạy Con Song Ngữ Thực Hành', 100, 110000, N'daycon.png', 0, N'Sách dành cho cả bố mẹ có khả năng tiếng Anh ở mức Căn bản (Benginner) hoặc Trung cấp (Intermediate).', CAST(N'2022-05-20' AS Date), NULL, N'Đ    ', 0)
GO
INSERT [dbo].[SACH] ([ID], [LoaiSachID], [NXBID], [NCCID], [TacGiaID], [TenSach], [SoLuong], [Gia], [Images], [Discount], [Mota], [NgayTao], [NgaySua], [Dvt], [DaXoa]) VALUES (N'SACH06    ', 6, 6, 6, 6, N'Sinh Học Lớp 12 (Nâng Cao) - 2020', 100, 110000, N'sinhhoc.png', 0, N'Sinh Học Lớp 12 (Nâng Cao) - 2020', CAST(N'2022-05-20' AS Date), NULL, N'Đ    ', 0)
GO
INSERT [dbo].[SACH] ([ID], [LoaiSachID], [NXBID], [NCCID], [TacGiaID], [TenSach], [SoLuong], [Gia], [Images], [Discount], [Mota], [NgayTao], [NgaySua], [Dvt], [DaXoa]) VALUES (N'SACH07    ', 7, 7, 7, 7, N'Bảng Hệ Thống Tuần Hoàn Các Nguyên Tố Hóa Học', 100, 110000, N'tuanhoan.png', 0, N'Bảng tuần hoàn hóa học', CAST(N'2022-05-20' AS Date), NULL, N'Đ    ', 0)
GO
INSERT [dbo].[SACH] ([ID], [LoaiSachID], [NXBID], [NCCID], [TacGiaID], [TenSach], [SoLuong], [Gia], [Images], [Discount], [Mota], [NgayTao], [NgaySua], [Dvt], [DaXoa]) VALUES (N'SACH08    ', 8, 8, 8, 8, N'Hackers Ielts: Writing', 100, 110000, N'ielst.png', 0, N'Bộ sách luyện thi IELTS đầu tiên có kèm giải thích đáp án chi tiết và hướng dẫn cách tự nâng band điểm.', CAST(N'2022-05-20' AS Date), NULL, N'Đ    ', 0)
GO
INSERT [dbo].[SACH] ([ID], [LoaiSachID], [NXBID], [NCCID], [TacGiaID], [TenSach], [SoLuong], [Gia], [Images], [Discount], [Mota], [NgayTao], [NgaySua], [Dvt], [DaXoa]) VALUES (N'SACH09    ', 9, 9, 9, 9, N'Tiểu Sử Steve Jobs', 100, 110000, N'tieususteve.png', 0, N'Cuốn sách Tiểu Sử Steve Jobs tiết lộ nhiều thông tin chưa từng được kể về Steve Jobs như tính cách cay nghiệt, kỳ dị, chuyện ông chiến đấu với bệnh ung thư, những mối quan hệ lãng mạn của ông và cuộc hôn nhân với bà Laurene Powell hay gặp cha đẻ Abdulfattah "John" Jandali...', CAST(N'2022-05-20' AS Date), NULL, N'Đ    ', 0)
GO
INSERT [dbo].[SACH] ([ID], [LoaiSachID], [NXBID], [NCCID], [TacGiaID], [TenSach], [SoLuong], [Gia], [Images], [Discount], [Mota], [NgayTao], [NgaySua], [Dvt], [DaXoa]) VALUES (N'SACH10    ', 10, 10, 10, 10, N'Hồi Ký Alex Ferguson', 100, 110000, N'hoiky.png', 0, N'Hồi ký Ferguson là câu chuyện đấy ấn tượng về con người được thừa nhận rộng rãi là huấn luyện viên xuất sắc nhất trong lịch sử bóng đá Liên hiệp Anh.', CAST(N'2022-05-20' AS Date), NULL, N'Đ    ', 0)
GO
INSERT [dbo].[SACH] ([ID], [LoaiSachID], [NXBID], [NCCID], [TacGiaID], [TenSach], [SoLuong], [Gia], [Images], [Discount], [Mota], [NgayTao], [NgaySua], [Dvt], [DaXoa]) VALUES (N'SACH11    ', 11, 11, 11, 11, N'Rèn Luyện Tư Duy Phản Biện
', 100, 110000, N'kynangsong.png', 0, N'Như bạn có thể thấy, chìa khóa để trở thành một người có tư duy phản biện tốt chính là sự tự nhận thức. Bạn cần phải đánh giá trung thực những điều trước đây bạn nghĩ là đúng, cũng như quá trình suy nghĩ đã dẫn bạn tới những kết luận đó.', CAST(N'2022-05-20' AS Date), NULL, N'Đ    ', 0)
GO
INSERT [dbo].[SACH] ([ID], [LoaiSachID], [NXBID], [NCCID], [TacGiaID], [TenSach], [SoLuong], [Gia], [Images], [Discount], [Mota], [NgayTao], [NgaySua], [Dvt], [DaXoa]) VALUES (N'SACH12    ', 12, 12, 12, 12, N'Khóa Học Nấu Ăn Tại Gia Của Gordon Ramsay', 100, 110000, N'gordon.png', 0, N'KHÓA HỌC NẤU ĂN TẠI GIA CỦA GORDON RAMSAY là một cuốn sách dạy nấu ăn cho cả những người mới bắt đầu với với hơn 120 công thức nấu ăn hiện đại, đơn giản và dễ tiếp cận.', CAST(N'2022-05-20' AS Date), NULL, N'Đ    ', 0)
GO
INSERT [dbo].[SACH] ([ID], [LoaiSachID], [NXBID], [NCCID], [TacGiaID], [TenSach], [SoLuong], [Gia], [Images], [Discount], [Mota], [NgayTao], [NgaySua], [Dvt], [DaXoa]) VALUES (N'SACH13    ', 13, 13, 13, 13, N'Trồng Mai Kỹ Thuật Bón Tưới, Phòng Trừ Sâu Rầy', 100, 110000, N'tronghoa.png', 0, N'Phòng trừ sâu rầy và bệnh hại cho cây mai là công việc mà bất cứ người trồng mai nào thời nay cũng phải đặc biệt quan tâm tới, nhằm tạo ra những cây mai đẹp và hoàn thiện nhất. Nhưng, điều này lại khác biệt với người trồng mai thời xưa.', CAST(N'2022-05-20' AS Date), NULL, N'Đ    ', 0)
GO
INSERT [dbo].[SACH] ([ID], [LoaiSachID], [NXBID], [NCCID], [TacGiaID], [TenSach], [SoLuong], [Gia], [Images], [Discount], [Mota], [NgayTao], [NgaySua], [Dvt], [DaXoa]) VALUES (N'SACH14    ', 14, 14, 14, 14, N'Nghệ Thuật Gấp Giấy Nhật Bản
', 98, 110000, N'nghethuat.png', 0.5, N'Origami là nghệ thuật gấp giấy (hay nghệ thuật xếp giấy) có xuất xứ từ Nhật Bản. Chữ origami trong tiếng Nhật bắt nguồn từ hai chữ: oru là gấp hay xếp và kami là giấy. Origami chỉ được dùng từ 1880 trước đó, người Nhật dùng chữ orikata.', CAST(N'2022-05-20' AS Date), NULL, N'Đ    ', 0)
GO
INSERT [dbo].[SACH] ([ID], [LoaiSachID], [NXBID], [NCCID], [TacGiaID], [TenSach], [SoLuong], [Gia], [Images], [Discount], [Mota], [NgayTao], [NgaySua], [Dvt], [DaXoa]) VALUES (N'SACH15    ', 15, 16, 15, 16, N'Bút Lông Bi', 100, 110000, N'butlongbi.png', 0, N'Đây là một trong những cây bút đang được học sinh sử dụng nhiều nhất tại Việt Nam.', CAST(N'2022-05-20' AS Date), NULL, N'Đ    ', 0)
GO
INSERT [dbo].[SACH] ([ID], [LoaiSachID], [NXBID], [NCCID], [TacGiaID], [TenSach], [SoLuong], [Gia], [Images], [Discount], [Mota], [NgayTao], [NgaySua], [Dvt], [DaXoa]) VALUES (N'SACH16    ', 15, 16, 15, 16, N'Hộp 10 Bút Lông Bảng Thiên Long', 100, 750000, N'butbangtrang.png', 0.5, N'Bút được sản xuất theo công nghệ hiện đại , đạt tiêu chuẩn an toàn quốc tế .', CAST(N'2022-06-20' AS Date), NULL, N'Đ    ', 0)
GO
SET IDENTITY_INSERT [dbo].[TACGIA] ON 
GO
INSERT [dbo].[TACGIA] ([ID], [Ten]) VALUES (1, N'José Mauro de Vasconcelos')
GO
INSERT [dbo].[TACGIA] ([ID], [Ten]) VALUES (2, N'Robin Sharma')
GO
INSERT [dbo].[TACGIA] ([ID], [Ten]) VALUES (3, N'Bộ Giáo Dục Và Đào Tạo')
GO
INSERT [dbo].[TACGIA] ([ID], [Ten]) VALUES (4, N'Võ Nguyên Giáp')
GO
INSERT [dbo].[TACGIA] ([ID], [Ten]) VALUES (5, N'Sir Alex Ferguson')
GO
INSERT [dbo].[TACGIA] ([ID], [Ten]) VALUES (6, N'Wayne Rooney')
GO
INSERT [dbo].[TACGIA] ([ID], [Ten]) VALUES (7, N'Vũ Hoàng')
GO
INSERT [dbo].[TACGIA] ([ID], [Ten]) VALUES (8, N'Trần Anh Khoa')
GO
INSERT [dbo].[TACGIA] ([ID], [Ten]) VALUES (9, N'Tchaikopski')
GO
INSERT [dbo].[TACGIA] ([ID], [Ten]) VALUES (10, N'Nguyễn Nhật Ánh')
GO
INSERT [dbo].[TACGIA] ([ID], [Ten]) VALUES (11, N'Adui na HUi')
GO
INSERT [dbo].[TACGIA] ([ID], [Ten]) VALUES (12, N'Alahu Akba')
GO
INSERT [dbo].[TACGIA] ([ID], [Ten]) VALUES (13, N'Dương Thái Phong')
GO
INSERT [dbo].[TACGIA] ([ID], [Ten]) VALUES (14, N'Trần Đình')
GO
INSERT [dbo].[TACGIA] ([ID], [Ten]) VALUES (15, N'Tố Hữu')
GO
INSERT [dbo].[TACGIA] ([ID], [Ten]) VALUES (16, N'VPP')
GO
SET IDENTITY_INSERT [dbo].[TACGIA] OFF
GO
ALTER TABLE [dbo].[DONHANG]  WITH CHECK ADD  CONSTRAINT [FK__DONHANG__KhachHa__5FB337D6] FOREIGN KEY([KhachHangID])
REFERENCES [dbo].[KHACHHANG] ([ID])
GO
ALTER TABLE [dbo].[DONHANG] CHECK CONSTRAINT [FK__DONHANG__KhachHa__5FB337D6]
GO
ALTER TABLE [dbo].[DONHANG]  WITH CHECK ADD  CONSTRAINT [FK__DONHANG__User_ID__60A75C0F] FOREIGN KEY([UserID])
REFERENCES [dbo].[NGUOIDUNG] ([ID])
GO
ALTER TABLE [dbo].[DONHANG] CHECK CONSTRAINT [FK__DONHANG__User_ID__60A75C0F]
GO
ALTER TABLE [dbo].[DONHANGCHITIET]  WITH CHECK ADD  CONSTRAINT [FK__DONHANGCH__Sach___6477ECF3] FOREIGN KEY([SachID])
REFERENCES [dbo].[SACH] ([ID])
GO
ALTER TABLE [dbo].[DONHANGCHITIET] CHECK CONSTRAINT [FK__DONHANGCH__Sach___6477ECF3]
GO
ALTER TABLE [dbo].[DONHANGCHITIET]  WITH CHECK ADD  CONSTRAINT [FK_DONHANGCHITIET_DONHANG] FOREIGN KEY([DonHangID])
REFERENCES [dbo].[DONHANG] ([ID])
GO
ALTER TABLE [dbo].[DONHANGCHITIET] CHECK CONSTRAINT [FK_DONHANGCHITIET_DONHANG]
GO
ALTER TABLE [dbo].[FAVORITE]  WITH CHECK ADD  CONSTRAINT [FK__FAVORITE__Sach_I__6C190EBB] FOREIGN KEY([SachID])
REFERENCES [dbo].[SACH] ([ID])
GO
ALTER TABLE [dbo].[FAVORITE] CHECK CONSTRAINT [FK__FAVORITE__Sach_I__6C190EBB]
GO
ALTER TABLE [dbo].[FAVORITE]  WITH CHECK ADD  CONSTRAINT [FK__FAVORITE__User_I__6B24EA82] FOREIGN KEY([UserID])
REFERENCES [dbo].[NGUOIDUNG] ([ID])
GO
ALTER TABLE [dbo].[FAVORITE] CHECK CONSTRAINT [FK__FAVORITE__User_I__6B24EA82]
GO
ALTER TABLE [dbo].[GALERY]  WITH CHECK ADD  CONSTRAINT [FK__GALERY__URLImage__571DF1D5] FOREIGN KEY([SachID])
REFERENCES [dbo].[SACH] ([ID])
GO
ALTER TABLE [dbo].[GALERY] CHECK CONSTRAINT [FK__GALERY__URLImage__571DF1D5]
GO
ALTER TABLE [dbo].[GIOHANG]  WITH CHECK ADD  CONSTRAINT [FK_GIOHANG_NGUOIDUNG] FOREIGN KEY([UserID])
REFERENCES [dbo].[NGUOIDUNG] ([ID])
GO
ALTER TABLE [dbo].[GIOHANG] CHECK CONSTRAINT [FK_GIOHANG_NGUOIDUNG]
GO
ALTER TABLE [dbo].[GIOHANGCHITIET]  WITH CHECK ADD  CONSTRAINT [FK_GIOHANGCHITIET_GIOHANG] FOREIGN KEY([GioHangID])
REFERENCES [dbo].[GIOHANG] ([ID])
GO
ALTER TABLE [dbo].[GIOHANGCHITIET] CHECK CONSTRAINT [FK_GIOHANGCHITIET_GIOHANG]
GO
ALTER TABLE [dbo].[GIOHANGCHITIET]  WITH CHECK ADD  CONSTRAINT [FK_GIOHANGCHITIET_SACH] FOREIGN KEY([SachID])
REFERENCES [dbo].[SACH] ([ID])
GO
ALTER TABLE [dbo].[GIOHANGCHITIET] CHECK CONSTRAINT [FK_GIOHANGCHITIET_SACH]
GO
ALTER TABLE [dbo].[SACH]  WITH CHECK ADD  CONSTRAINT [FK__SACH__DaXoa__5165187F] FOREIGN KEY([LoaiSachID])
REFERENCES [dbo].[LOAISACH] ([ID])
GO
ALTER TABLE [dbo].[SACH] CHECK CONSTRAINT [FK__SACH__DaXoa__5165187F]
GO
ALTER TABLE [dbo].[SACH]  WITH CHECK ADD  CONSTRAINT [FK__SACH__NCC_ID__534D60F1] FOREIGN KEY([NCCID])
REFERENCES [dbo].[NHACUNGCAP] ([ID])
GO
ALTER TABLE [dbo].[SACH] CHECK CONSTRAINT [FK__SACH__NCC_ID__534D60F1]
GO
ALTER TABLE [dbo].[SACH]  WITH CHECK ADD  CONSTRAINT [FK__SACH__NXB_ID__52593CB8] FOREIGN KEY([NXBID])
REFERENCES [dbo].[NHAXUATBAN] ([ID])
GO
ALTER TABLE [dbo].[SACH] CHECK CONSTRAINT [FK__SACH__NXB_ID__52593CB8]
GO
ALTER TABLE [dbo].[SACH]  WITH CHECK ADD  CONSTRAINT [FK__SACH__TacGia_ID__5441852A] FOREIGN KEY([TacGiaID])
REFERENCES [dbo].[TACGIA] ([ID])
GO
ALTER TABLE [dbo].[SACH] CHECK CONSTRAINT [FK__SACH__TacGia_ID__5441852A]
GO
USE [master]
GO
ALTER DATABASE [QLKGB] SET  READ_WRITE 
GO
