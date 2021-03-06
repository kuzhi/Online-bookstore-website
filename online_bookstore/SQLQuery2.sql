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
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (1, N'Tr???n H??ng D??ng', N'55A ng?? quy??n', N'0345665226', N'C???n Th??')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (2, N'Tr???n Anh D??ng', N'55A  quy??n', N'0345335229', N'C???n Th??')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (3, N'Tr???n Tu???n D??ng', N'55A quang quy??n', N'0345338226', N'H??? Ch?? Minh')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (4, N'Tr???n Kinh D??ng', N'55A h??? quy??n', N'0345635226', N'Hu???')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (5, N'Tr???n Ngh?? D??ng', N'55A kinh quy??n', N'0155335226', N'Hu???')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (6, N'Tr???n B?? D??ng', N'55A gio quy??n', N'0345935226', N'Hu???')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (7, N'Tr???n Chi D??ng', N'55A pao quy??n', N'0348335226', N'???? N???ng')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (8, N'Tr???n Sara D??ng', N'55A pasu quy??n', N'0245335226', N'???? N???ng')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (9, N'Nguy???n Tuy???t D??ng', N'55A qua quy??n', N'0323335226', N'???? N???ng')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (10, N'Nguy???n H??ng D??ng', N'55A quang trung', N'0255335226', N'H??? Ch?? Minh')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (11, N'Nguy???n Chi D??ng', N'55A ng?? trung', N'090335226', N'H??? Ch?? Minh')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (12, N'Nguy???n Tr????ng D??ng', N'55A nguy???n hu???', N'0545335226', N'H??? Ch?? Minh')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (13, N'Ngh?? H??ng D??ng', N'55A nguy???n tr??i', N'0745335226', N'V??nh Long')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (14, N'Ngh?? Tr???n D??ng', N'55A nguy???n ??nh', N'0345335226', N'V??nh Long')
GO
INSERT [dbo].[KHACHHANG] ([ID], [Fullname], [DiaChi], [SDT], [City]) VALUES (15, N'Ngh?? B?? D??ng', N'55A ng?? nguy???n l???', N'0245335226', N'V??nh Long')
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
INSERT [dbo].[LOAISACH] ([ID], [TenLoai]) VALUES (1, N'V??N H???C')
GO
INSERT [dbo].[LOAISACH] ([ID], [TenLoai]) VALUES (2, N'KINH T???')
GO
INSERT [dbo].[LOAISACH] ([ID], [TenLoai]) VALUES (3, N'THI???U NHI')
GO
INSERT [dbo].[LOAISACH] ([ID], [TenLoai]) VALUES (4, N'T??M L??')
GO
INSERT [dbo].[LOAISACH] ([ID], [TenLoai]) VALUES (5, N'NU??I D???Y CON')
GO
INSERT [dbo].[LOAISACH] ([ID], [TenLoai]) VALUES (6, N'GI??O KHOA')
GO
INSERT [dbo].[LOAISACH] ([ID], [TenLoai]) VALUES (7, N'THAM KH???O')
GO
INSERT [dbo].[LOAISACH] ([ID], [TenLoai]) VALUES (8, N'S??CH H???C NGO???I NG???')
GO
INSERT [dbo].[LOAISACH] ([ID], [TenLoai]) VALUES (9, N'TI???U S???')
GO
INSERT [dbo].[LOAISACH] ([ID], [TenLoai]) VALUES (10, N'H???I K??')
GO
INSERT [dbo].[LOAISACH] ([ID], [TenLoai]) VALUES (11, N'K??? N??NG S???NG')
GO
INSERT [dbo].[LOAISACH] ([ID], [TenLoai]) VALUES (12, N'N???U ??N')
GO
INSERT [dbo].[LOAISACH] ([ID], [TenLoai]) VALUES (13, N'C??Y TR???NG')
GO
INSERT [dbo].[LOAISACH] ([ID], [TenLoai]) VALUES (14, N'NGH??? THU???T')
GO
INSERT [dbo].[LOAISACH] ([ID], [TenLoai]) VALUES (15, N'VPP-D???NG C??? H???C SINH')
GO
SET IDENTITY_INSERT [dbo].[LOAISACH] OFF
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'admin     ', N'admin          ', N'admin', N'admin', N'kirudono@gmail.com', 1, CAST(N'1995-12-12' AS Date), 1, N'0000000000', N'0000000000', N'0', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'admin2    ', N'admin2         ', N'Admin', N'Admin', N'admin2@gmail.com', 1, CAST(N'1988-07-07' AS Date), 1, N'090123456122', N'0345999999', N'55 quang trung, h?? n???i', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'admin22   ', N'admin22        ', N'admin', N'Khoa Tran Anh', N'khboabe2o@gmail.com', 1, CAST(N'2022-06-14' AS Date), 1, N'11111111111', N'0345335253', N'123 saint pt te', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'admind    ', N'asdasd         ', N'tran', N'Khoa Tran Anh', N'khbodabeo@gmail.com', 1, CAST(N'2022-06-08' AS Date), 2, N'012456789', N'0345335253', N'123 saint pt te', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'asdasd    ', N'asdasd         ', N'asd', N'asd', N'kirudono@gmail.com', 1, CAST(N'2022-05-31' AS Date), 3, N'0000000000', N'0000000000', N'0', 0, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'babara    ', N'babara         ', N'Hai', N'Bara', N'babara@gmail.com', 0, CAST(N'2000-12-12' AS Date), 3, N'0000000000', N'0000000000', N'55 quang trung, h?? n???i', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'java5     ', N'java5          ', N'Kinh', N'Qua', N'java5@gmail.com', 1, CAST(N'2002-12-12' AS Date), 3, N'0000000000', N'0000000000', N'55 quang trung, h?? n???i', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'kazuz     ', N'kazuz          ', N'Tr???n', N'Tuy???t Nhung', N'kazuz@gmail.com', 0, CAST(N'1999-12-12' AS Date), 3, N'0000000000', N'0000000000', N'55 quang trung, h?? n???i', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'khoaQL    ', N'sadasd         ', N'tran', N'anh', N'khboabeo@gmail.com', 1, CAST(N'2022-06-08' AS Date), 1, N'0123456789', N'0345335273', N'123 saint pt te', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'kinzal    ', N'kinzal         ', N'Tr???n', N'Tu???n', N'kinzal@gmail.com', 1, CAST(N'1988-12-12' AS Date), 3, N'0000000000', N'0000000000', N'55 quang trung, h?? n???i', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'staff1    ', N'staff1         ', N'Nh??n', N'Vi??n', N'staff1@gmail.com', 1, CAST(N'2003-12-12' AS Date), 2, N'030123426122', N'0905325123', N'55 quang trung, h?? n???i', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'staff2    ', N'staff2         ', N'Nh??n', N'Vi??n Hai', N'staff2@gmail.com', 0, CAST(N'2002-11-18' AS Date), 2, N'030153456122', N'090511123', N'55 quang trung, h?? n???i', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'staff3    ', N'staff3         ', N'Nh??n', N'Vi??n Ba', N'staff3@gmail.com', 0, CAST(N'2003-12-01' AS Date), 2, N'030143456122', N'090545123', N'55 quang trung, h?? n???i', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'staff4    ', N'staff4         ', N'Nh??n', N'Vi??n B???n', N'staff4@gmail.com', 1, CAST(N'2000-10-12' AS Date), 2, N'090523456122', N'090552123', N'55 quang trung, h?? n???i', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'staff5    ', N'staff5         ', N'Nh??n', N'Vi??n N??m', N'staff5@gmail.com', 1, CAST(N'1998-10-12' AS Date), 2, N'090423456122', N'090555548', N'55 quang trung, h?? n???i', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'staff6    ', N'staff6         ', N'Nh??n', N'Vi??n S??u', N'staff6@gmail.com', 1, CAST(N'1997-11-12' AS Date), 2, N'090323456122', N'0345335335', N'55 quang trung, h?? n???i', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'test222   ', N'test222        ', N'test222', N'test222', N'khoabro@gmail.com', 1, CAST(N'2022-06-14' AS Date), 3, N'0000000000', N'0000000000', N'0', 0, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'test6     ', N'test6          ', N'test6', N'test6', N'khoabro@gmail.com', 1, CAST(N'2022-06-07' AS Date), 3, N'0000000000', N'0000000000', N'0', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'user1     ', N'user1          ', N'Jos??', N'Mazuro', N'user1@gmail.com', 1, CAST(N'1995-12-12' AS Date), 3, N'0000000000', N'0000000000', N'55 quang trung, h?? n???i', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'user2     ', N'user2          ', N'Nguy???n', N'Anh', N'user2@gmail.com', 1, CAST(N'1996-12-12' AS Date), 3, N'0000000000', N'0000000000', N'55 quang trung, h?? n???i', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'user3     ', N'user3          ', N'Ngh??', N'Th?????ng', N'user3@gmail.com', 1, CAST(N'1997-12-12' AS Date), 3, N'0000000000', N'0000000000', N'55 quang trung, h?? n???i', 1, 0)
GO
INSERT [dbo].[NGUOIDUNG] ([ID], [Password], [Ho], [Ten], [Email], [GioiTinh], [NgaySinh], [ChucVu], [CMND], [SDT], [DiaChi], [TrangThai], [DaXoa]) VALUES (N'zxczxc    ', N'zxczczc        ', N'zxczczc', N'zxczxc', N'zczxc@gmail.com', 0, CAST(N'2022-06-06' AS Date), 3, N'0000000000', N'0000000000', N'0', 1, 1)
GO
SET IDENTITY_INSERT [dbo].[NHACUNGCAP] ON 
GO
INSERT [dbo].[NHACUNGCAP] ([ID], [TenNCC], [DiaChi], [SDT]) VALUES (1, N'KIM ?????NG', N'55 Quang Trung, Hai B?? Tr??ng, H?? N???i', N'1900571595')
GO
INSERT [dbo].[NHACUNGCAP] ([ID], [TenNCC], [DiaChi], [SDT]) VALUES (2, N'SKYBOOKS', N'155 Quang Trung, Hai B?? Tr??ng, H?? N???i', N'1900111222')
GO
INSERT [dbo].[NHACUNGCAP] ([ID], [TenNCC], [DiaChi], [SDT]) VALUES (3, N'NH?? NAM', N'10 Quang Trung, Hai B?? Tr??ng, H?? N???i', N'1900777888')
GO
INSERT [dbo].[NHACUNGCAP] ([ID], [TenNCC], [DiaChi], [SDT]) VALUES (4, N'IPM', N'15 Quang Trung, Hai B?? Tr??ng, H?? N???i', N'1900666555')
GO
INSERT [dbo].[NHACUNGCAP] ([ID], [TenNCC], [DiaChi], [SDT]) VALUES (5, N'MINH LONG', N'20 Quang Trung, Hai B?? Tr??ng, H?? N???i', N'1900888999')
GO
INSERT [dbo].[NHACUNGCAP] ([ID], [TenNCC], [DiaChi], [SDT]) VALUES (6, N'B??CH VI???T', N'11 Quang Trung, Hai B?? Tr??ng, H?? N???i', N'1900121121')
GO
INSERT [dbo].[NHACUNGCAP] ([ID], [TenNCC], [DiaChi], [SDT]) VALUES (7, N'QU???NG V??N', N'22 Quang Trung, Hai B?? Tr??ng, H?? N???i', N'1900363363')
GO
INSERT [dbo].[NHACUNGCAP] ([ID], [TenNCC], [DiaChi], [SDT]) VALUES (8, N'??INH T???', N'33 Quang Trung, Hai B?? Tr??ng, H?? N???i', N'1900575575')
GO
INSERT [dbo].[NHACUNGCAP] ([ID], [TenNCC], [DiaChi], [SDT]) VALUES (9, N'HOA H???C TR??', N'105 Quang Trung, Hai B?? Tr??ng, H?? N???i', N'1900222888')
GO
INSERT [dbo].[NHACUNGCAP] ([ID], [TenNCC], [DiaChi], [SDT]) VALUES (10, N'NH?? XU???T B???N TR???', N'125 Quang Trung, Hai B?? Tr??ng, H?? N???i', N'1900198098')
GO
INSERT [dbo].[NHACUNGCAP] ([ID], [TenNCC], [DiaChi], [SDT]) VALUES (11, N'ALPHABOOKS', N'60 Quang Trung, Hai B?? Tr??ng, H?? N???i', N'1900369696')
GO
INSERT [dbo].[NHACUNGCAP] ([ID], [TenNCC], [DiaChi], [SDT]) VALUES (12, N'HUY HO??NG JSC', N'70 Quang Trung, Hai B?? Tr??ng, H?? N???i', N'1900812335')
GO
INSERT [dbo].[NHACUNGCAP] ([ID], [TenNCC], [DiaChi], [SDT]) VALUES (13, N'AZ VIETNAM', N'80 Quang Trung, Hai B?? Tr??ng, H?? N???i', N'1900444555')
GO
INSERT [dbo].[NHACUNGCAP] ([ID], [TenNCC], [DiaChi], [SDT]) VALUES (14, N'CHIBOOKS', N'88 Quang Trung, Hai B?? Tr??ng, H?? N???i', N'1900333777')
GO
INSERT [dbo].[NHACUNGCAP] ([ID], [TenNCC], [DiaChi], [SDT]) VALUES (15, N'????NG A', N'99 Quang Trung, Hai B?? Tr??ng, H?? N???i', N'1900180012')
GO
INSERT [dbo].[NHACUNGCAP] ([ID], [TenNCC], [DiaChi], [SDT]) VALUES (17, N'THI??N LONG HO??N C???U', N'55 L?? Th??? Ri??ng, HCM', N'1900808080')
GO
SET IDENTITY_INSERT [dbo].[NHACUNGCAP] OFF
GO
SET IDENTITY_INSERT [dbo].[NHAXUATBAN] ON 
GO
INSERT [dbo].[NHAXUATBAN] ([ID], [TenNXB]) VALUES (1, N'KIM ?????NG')
GO
INSERT [dbo].[NHAXUATBAN] ([ID], [TenNXB]) VALUES (2, N'TR???')
GO
INSERT [dbo].[NHAXUATBAN] ([ID], [TenNXB]) VALUES (3, N'D??N TR??')
GO
INSERT [dbo].[NHAXUATBAN] ([ID], [TenNXB]) VALUES (4, N'GI??O D???C')
GO
INSERT [dbo].[NHAXUATBAN] ([ID], [TenNXB]) VALUES (5, N'H???I NH?? V??N')
GO
INSERT [dbo].[NHAXUATBAN] ([ID], [TenNXB]) VALUES (6, N'T?? PH??P')
GO
INSERT [dbo].[NHAXUATBAN] ([ID], [TenNXB]) VALUES (7, N'H???NG ?????C')
GO
INSERT [dbo].[NHAXUATBAN] ([ID], [TenNXB]) VALUES (8, N'LAO ?????NG')
GO
INSERT [dbo].[NHAXUATBAN] ([ID], [TenNXB]) VALUES (9, N'GIAO TH??NG V???N T???I')
GO
INSERT [dbo].[NHAXUATBAN] ([ID], [TenNXB]) VALUES (10, N'?????I H???C QU???C GIA H?? N???I')
GO
INSERT [dbo].[NHAXUATBAN] ([ID], [TenNXB]) VALUES (11, N'H???NG ?????C')
GO
INSERT [dbo].[NHAXUATBAN] ([ID], [TenNXB]) VALUES (12, N'KHOA H???C X?? H???I')
GO
INSERT [dbo].[NHAXUATBAN] ([ID], [TenNXB]) VALUES (13, N'T??N GI??O')
GO
INSERT [dbo].[NHAXUATBAN] ([ID], [TenNXB]) VALUES (14, N'TRI TH???C')
GO
INSERT [dbo].[NHAXUATBAN] ([ID], [TenNXB]) VALUES (15, N'Y H???C')
GO
INSERT [dbo].[NHAXUATBAN] ([ID], [TenNXB]) VALUES (16, N'THI??N LONG')
GO
SET IDENTITY_INSERT [dbo].[NHAXUATBAN] OFF
GO
INSERT [dbo].[SACH] ([ID], [LoaiSachID], [NXBID], [NCCID], [TacGiaID], [TenSach], [SoLuong], [Gia], [Images], [Discount], [Mota], [NgayTao], [NgaySua], [Dvt], [DaXoa]) VALUES (N'SACH01    ', 1, 5, 3, 1, N'C??y Cam Ng???t C???a T??i', 99, 110000, N'caycam.png', 0, N'M??? ?????u b???ng nh???ng thanh ??m trong s??ng v?? k???t th??c l???ng l???i trong nh???ng n???t tr???m ho??i ni???m, C??y cam ng???t c???a t??i khi???n ta nh???n ra v??? ?????p th???c s??? c???a cu???c s???ng ?????n t??? nh???ng ??i???u gi???n d??? nh?? b??ng hoa tr???ng c???a c??i c??y sau nh??, v?? r???ng cu???c ?????i th???t kh???n kh??? n???u thi???u ??i l??ng y??u th????ng v?? ni???m tr???c ???n. Cu???n s??ch kinh ??i???n n??y b???i th??? kh??ng ng???ng khi???n tr??i tim ng?????i ?????c kh???p th??? gi???i th???n th???c, k??? t??? khi ra m???t l???n ?????u n??m 1968 t???i Brazil.', CAST(N'2022-05-20' AS Date), NULL, N'??    ', 0)
GO
INSERT [dbo].[SACH] ([ID], [LoaiSachID], [NXBID], [NCCID], [TacGiaID], [TenSach], [SoLuong], [Gia], [Images], [Discount], [Mota], [NgayTao], [NgaySua], [Dvt], [DaXoa]) VALUES (N'SACH02    ', 2, 2, 2, 2, N'Nh?? L??nh ?????o Kh??ng Ch???c Danh', 99, 110000, N'nhalanhdao.png', 0, N'Su???t h??n 15 n??m, Robin Sharma ???? th???m l???ng chia s??? v???i nh???ng c??ng ty trong danh s??ch Fortune 500 v?? nhi???u ng?????i si??u gi??u kh??c m???t c??ng th???c th??nh c??ng ???? gi??p ??ng tr??? th??nh m???t trong nh???ng nh?? c??? v???n l??nh ?????o ???????c theo ??u???i nhi???u nh???t th??? gi???i. ????y l?? l???n ?????u ti??n Sharma c??ng b??? c??ng th???c ?????c quy???n n??y v???i b???n, ????? b???n c?? th??? ?????t ???????c nh???ng g?? t???t nh???t, ?????ng th???i gi??p t??? ch???c c???a b???n c?? th??? c?? nh???ng b?????c ?????t ph?? ?????n m???t c???p ????? th??nh c??ng m???i trong th???i ?????i thi??n bi???n v???n h??a nh?? hi???n nay.', CAST(N'2022-05-20' AS Date), NULL, N'??    ', 0)
GO
INSERT [dbo].[SACH] ([ID], [LoaiSachID], [NXBID], [NCCID], [TacGiaID], [TenSach], [SoLuong], [Gia], [Images], [Discount], [Mota], [NgayTao], [NgaySua], [Dvt], [DaXoa]) VALUES (N'SACH03    ', 3, 3, 3, 3, N'C??? Oai ??i! - T???p 16: L??m G?? Khi S??? M??i?', 100, 110000, N'cooaioi.png', 0, N'B??? truy???n C??? Oai ??i! l?? b??? truy???n tranh gi??o d???c y khoa d??nh cho tr??? nh???, ?????c bi???t l?? c??c b?? ??? ????? tu???i t??? 2-8 tu???i. ????y l?? nh???ng t???p truy???n tranh ????n gi???n vui t????i t??nh c???m, gi??p con nh???n bi???t v?? c?? ???????c nh???ng ki???n th???c c?? b???n th???t s??? c???n thi???t cho vi???c ph??ng ng???a b???nh, ph??ng ng???a tai n???n, v?? nh???ng h??nh vi ch??m s??c s???c kho??? ????ng ?????n c???n thi???t cho c?? nh??n con v?? cho x?? h???i!', CAST(N'2022-05-20' AS Date), NULL, N'??    ', 0)
GO
INSERT [dbo].[SACH] ([ID], [LoaiSachID], [NXBID], [NCCID], [TacGiaID], [TenSach], [SoLuong], [Gia], [Images], [Discount], [Mota], [NgayTao], [NgaySua], [Dvt], [DaXoa]) VALUES (N'SACH04    ', 4, 4, 4, 4, N'Thi??n T??i B??n Tr??i, K??? ??i??n B??n Ph???i ', 100, 110000, N'thientai.png', 0, N'H???i nh???ng con ng?????i ??ang o???n m??nh trong cu???c s???ng, b???n bi???t g?? v??? th??? gi???i c???a m??nh? L?? v?? v??n th??? l?? thuy???t ???????c c??c b???c v?? nh??n ki???m ch???ng, l?? lu???t l???, l?? c??? ngh??n th??? s??? th???t b???c trong c??i l???t hi???n nhi??n, hay nh???ng tri???t l?? c???ng nh???c c???a cu???c ?????i?

L???i ????y, v?????t qua th??? nh???n th???c t??? nh???t b??? ????ng k??n b???ng con m???t tr???n gian, khai m??? to??n b??? suy ngh??, ????? d??ng m??u trong b???n s???c s??i tr?????c nh???ng ??i???u k??? v??, ph?? v??? m???i quy t???c. Th??? gi???i s??? g???i b???n l?? k??? ??i??n, nh??ng v???y th?? c?? sao? Ranh gi???i duy nh???t gi???a k??? ??i??n v?? thi??n t??i ch???ng qua l?? m???t s???i ch??? m???ng manh: Thi??n t??i ch???ng minh ???????c th??? gi???i c???a m??nh, c??n k??? ??i??n ch??a k???p l??m ??i???u ????. Ch???n tr??? th??nh m???t k??? ??i??n ????? v???y v??ng gi???a nh??n gian lo???n th??? hay kh??a h???t ch??ng l???i, s???ng m??i m???t cu???c ?????i b??nh th?????ng khi???n b???n c???m th???y h???nh ph??c h??n?

Thi??n t??i b??n tr??i, k??? ??i??n b??n ph???i l?? cu???n s??ch d??nh cho nh???ng ng?????i ??i??n r???, nh???ng k??? g??y r???i, nh???ng ng?????i ch???ng ?????i, nh???ng m???nh gh??p h??nh tr??n trong nh???ng ?? vu??ng kh??ng v???a v???n??? nh???ng ng?????i nh??n m???i th??? kh??c bi???t, kh??ng quan t??m ?????n quy t???c. B???n c?? th??? ?????ng ??, c?? th??? ph???n ?????i, c?? th??? vinh danh hay l??ng m??? h???, nh??ng ??i???u duy nh???t b???n kh??ng th??? l??m l?? ph??? nh???n s??? t???n t???i c???a h???. ???? l?? nh???ng ng?????i lu??n t???o ra s??? thay ?????i trong khi h???u h???t con ng?????i ch??? s???ng r???p khu??n nh?? m???t c??i m??y. ??a s??? ?????u ngh?? h??? th???t ??i??n r??? nh??ng n???u nh??n ??? g??c kh??c, ta l???i th???y h??? thi??n t??i. B???i ch??? nh???ng ng?????i ????? ??i??n ngh?? r???ng h??? c?? th??? thay ?????i th??? gi???i m???i l?? nh???ng ng?????i l??m ???????c ??i???u ????. 

Ch??o m???ng ?????n v???i th??? gi???i c???a nh???ng k??? ??i??n.

M?? h??ng	8936186546815
T??n Nh?? Cung C???p	AZ Vi???t Nam
T??c gi???	Cao Minh
Ng?????i D???ch	Thu H????ng
NXB	NXB Th??? Gi???i
N??m XB	2021
Ng??n Ng???	Ti???ng Vi???t
Tr???ng l?????ng (gr)	450
K??ch Th?????c Bao B??	24 x 16 cm
S??? trang	424
H??nh th???c	B??a M???m
S???n ph???m hi???n th??? trong	
AZ Vi???t Nam
????? Ch??i Cho B?? - Gi?? C???c T???t
PNJ
T??? S??ch T??m L?? K??? N??ng
S???n ph???m b??n ch???y nh???t	Top 100 s???n ph???m T??m l?? b??n ch???y c???a th??ng
Thi??n T??i B??n Tr??i, K??? ??i??n B??n Ph???i

N???U M???T NG??Y ANH TH???Y T??I ??I??N, TH???C RA CH??NH L?? ANH ??I??N ?????Y!

H???i nh???ng con ng?????i ??ang o???n m??nh trong cu???c s???ng, b???n bi???t g?? v??? th??? gi???i c???a m??nh? L?? v?? v??n th??? l?? thuy???t ???????c c??c b???c v?? nh??n ki???m ch???ng, l?? lu???t l???, l?? c??? ngh??n th??? s??? th???t b???c trong c??i l???t hi???n nhi??n, hay nh???ng tri???t l?? c???ng nh???c c???a cu???c ?????i?', CAST(N'2022-05-20' AS Date), NULL, N'??    ', 0)
GO
INSERT [dbo].[SACH] ([ID], [LoaiSachID], [NXBID], [NCCID], [TacGiaID], [TenSach], [SoLuong], [Gia], [Images], [Discount], [Mota], [NgayTao], [NgaySua], [Dvt], [DaXoa]) VALUES (N'SACH05    ', 5, 5, 5, 5, N'D???y Con Song Ng??? Th???c H??nh', 100, 110000, N'daycon.png', 0, N'S??ch d??nh cho c??? b??? m??? c?? kh??? n??ng ti???ng Anh ??? m???c C??n b???n (Benginner) ho???c Trung c???p (Intermediate).', CAST(N'2022-05-20' AS Date), NULL, N'??    ', 0)
GO
INSERT [dbo].[SACH] ([ID], [LoaiSachID], [NXBID], [NCCID], [TacGiaID], [TenSach], [SoLuong], [Gia], [Images], [Discount], [Mota], [NgayTao], [NgaySua], [Dvt], [DaXoa]) VALUES (N'SACH06    ', 6, 6, 6, 6, N'Sinh H???c L???p 12 (N??ng Cao) - 2020', 100, 110000, N'sinhhoc.png', 0, N'Sinh H???c L???p 12 (N??ng Cao) - 2020', CAST(N'2022-05-20' AS Date), NULL, N'??    ', 0)
GO
INSERT [dbo].[SACH] ([ID], [LoaiSachID], [NXBID], [NCCID], [TacGiaID], [TenSach], [SoLuong], [Gia], [Images], [Discount], [Mota], [NgayTao], [NgaySua], [Dvt], [DaXoa]) VALUES (N'SACH07    ', 7, 7, 7, 7, N'B???ng H??? Th???ng Tu???n Ho??n C??c Nguy??n T??? H??a H???c', 100, 110000, N'tuanhoan.png', 0, N'B???ng tu???n ho??n h??a h???c', CAST(N'2022-05-20' AS Date), NULL, N'??    ', 0)
GO
INSERT [dbo].[SACH] ([ID], [LoaiSachID], [NXBID], [NCCID], [TacGiaID], [TenSach], [SoLuong], [Gia], [Images], [Discount], [Mota], [NgayTao], [NgaySua], [Dvt], [DaXoa]) VALUES (N'SACH08    ', 8, 8, 8, 8, N'Hackers Ielts: Writing', 100, 110000, N'ielst.png', 0, N'B??? s??ch luy???n thi IELTS ?????u ti??n c?? k??m gi???i th??ch ????p ??n chi ti???t v?? h?????ng d???n c??ch t??? n??ng band ??i???m.', CAST(N'2022-05-20' AS Date), NULL, N'??    ', 0)
GO
INSERT [dbo].[SACH] ([ID], [LoaiSachID], [NXBID], [NCCID], [TacGiaID], [TenSach], [SoLuong], [Gia], [Images], [Discount], [Mota], [NgayTao], [NgaySua], [Dvt], [DaXoa]) VALUES (N'SACH09    ', 9, 9, 9, 9, N'Ti???u S??? Steve Jobs', 100, 110000, N'tieususteve.png', 0, N'Cu???n s??ch Ti???u S??? Steve Jobs ti???t l??? nhi???u th??ng tin ch??a t???ng ???????c k??? v??? Steve Jobs nh?? t??nh c??ch cay nghi???t, k??? d???, chuy???n ??ng chi???n ?????u v???i b???nh ung th??, nh???ng m???i quan h??? l??ng m???n c???a ??ng v?? cu???c h??n nh??n v???i b?? Laurene Powell hay g???p cha ????? Abdulfattah "John" Jandali...', CAST(N'2022-05-20' AS Date), NULL, N'??    ', 0)
GO
INSERT [dbo].[SACH] ([ID], [LoaiSachID], [NXBID], [NCCID], [TacGiaID], [TenSach], [SoLuong], [Gia], [Images], [Discount], [Mota], [NgayTao], [NgaySua], [Dvt], [DaXoa]) VALUES (N'SACH10    ', 10, 10, 10, 10, N'H???i K?? Alex Ferguson', 100, 110000, N'hoiky.png', 0, N'H???i k?? Ferguson l?? c??u chuy???n ?????y ???n t?????ng v??? con ng?????i ???????c th???a nh???n r???ng r??i l?? hu???n luy???n vi??n xu???t s???c nh???t trong l???ch s??? b??ng ???? Li??n hi???p Anh.', CAST(N'2022-05-20' AS Date), NULL, N'??    ', 0)
GO
INSERT [dbo].[SACH] ([ID], [LoaiSachID], [NXBID], [NCCID], [TacGiaID], [TenSach], [SoLuong], [Gia], [Images], [Discount], [Mota], [NgayTao], [NgaySua], [Dvt], [DaXoa]) VALUES (N'SACH11    ', 11, 11, 11, 11, N'R??n Luy???n T?? Duy Ph???n Bi???n
', 100, 110000, N'kynangsong.png', 0, N'Nh?? b???n c?? th??? th???y, ch??a kh??a ????? tr??? th??nh m???t ng?????i c?? t?? duy ph???n bi???n t???t ch??nh l?? s??? t??? nh???n th???c. B???n c???n ph???i ????nh gi?? trung th???c nh???ng ??i???u tr?????c ????y b???n ngh?? l?? ????ng, c??ng nh?? qu?? tr??nh suy ngh?? ???? d???n b???n t???i nh???ng k???t lu???n ????.', CAST(N'2022-05-20' AS Date), NULL, N'??    ', 0)
GO
INSERT [dbo].[SACH] ([ID], [LoaiSachID], [NXBID], [NCCID], [TacGiaID], [TenSach], [SoLuong], [Gia], [Images], [Discount], [Mota], [NgayTao], [NgaySua], [Dvt], [DaXoa]) VALUES (N'SACH12    ', 12, 12, 12, 12, N'Kh??a H???c N???u ??n T???i Gia C???a Gordon Ramsay', 100, 110000, N'gordon.png', 0, N'KH??A H???C N???U ??N T???I GIA C???A GORDON RAMSAY l?? m???t cu???n s??ch d???y n???u ??n cho c??? nh???ng ng?????i m???i b???t ?????u v???i v???i h??n 120 c??ng th???c n???u ??n hi???n ?????i, ????n gi???n v?? d??? ti???p c???n.', CAST(N'2022-05-20' AS Date), NULL, N'??    ', 0)
GO
INSERT [dbo].[SACH] ([ID], [LoaiSachID], [NXBID], [NCCID], [TacGiaID], [TenSach], [SoLuong], [Gia], [Images], [Discount], [Mota], [NgayTao], [NgaySua], [Dvt], [DaXoa]) VALUES (N'SACH13    ', 13, 13, 13, 13, N'Tr???ng Mai K??? Thu???t B??n T?????i, Ph??ng Tr??? S??u R???y', 100, 110000, N'tronghoa.png', 0, N'Ph??ng tr??? s??u r???y v?? b???nh h???i cho c??y mai l?? c??ng vi???c m?? b???t c??? ng?????i tr???ng mai n??o th???i nay c??ng ph???i ?????c bi???t quan t??m t???i, nh???m t???o ra nh???ng c??y mai ?????p v?? ho??n thi???n nh???t. Nh??ng, ??i???u n??y l???i kh??c bi???t v???i ng?????i tr???ng mai th???i x??a.', CAST(N'2022-05-20' AS Date), NULL, N'??    ', 0)
GO
INSERT [dbo].[SACH] ([ID], [LoaiSachID], [NXBID], [NCCID], [TacGiaID], [TenSach], [SoLuong], [Gia], [Images], [Discount], [Mota], [NgayTao], [NgaySua], [Dvt], [DaXoa]) VALUES (N'SACH14    ', 14, 14, 14, 14, N'Ngh??? Thu???t G???p Gi???y Nh???t B???n
', 98, 110000, N'nghethuat.png', 0.5, N'Origami l?? ngh??? thu???t g???p gi???y (hay ngh??? thu???t x???p gi???y) c?? xu???t x??? t??? Nh???t B???n. Ch??? origami trong ti???ng Nh???t b???t ngu???n t??? hai ch???: oru l?? g???p hay x???p v?? kami l?? gi???y. Origami ch??? ???????c d??ng t??? 1880 tr?????c ????, ng?????i Nh???t d??ng ch??? orikata.', CAST(N'2022-05-20' AS Date), NULL, N'??    ', 0)
GO
INSERT [dbo].[SACH] ([ID], [LoaiSachID], [NXBID], [NCCID], [TacGiaID], [TenSach], [SoLuong], [Gia], [Images], [Discount], [Mota], [NgayTao], [NgaySua], [Dvt], [DaXoa]) VALUES (N'SACH15    ', 15, 16, 15, 16, N'B??t L??ng Bi', 100, 110000, N'butlongbi.png', 0, N'????y l?? m???t trong nh???ng c??y b??t ??ang ???????c h???c sinh s??? d???ng nhi???u nh???t t???i Vi???t Nam.', CAST(N'2022-05-20' AS Date), NULL, N'??    ', 0)
GO
INSERT [dbo].[SACH] ([ID], [LoaiSachID], [NXBID], [NCCID], [TacGiaID], [TenSach], [SoLuong], [Gia], [Images], [Discount], [Mota], [NgayTao], [NgaySua], [Dvt], [DaXoa]) VALUES (N'SACH16    ', 15, 16, 15, 16, N'H???p 10 B??t L??ng B???ng Thi??n Long', 100, 750000, N'butbangtrang.png', 0.5, N'B??t ???????c s???n xu???t theo c??ng ngh??? hi???n ?????i , ?????t ti??u chu???n an to??n qu???c t??? .', CAST(N'2022-06-20' AS Date), NULL, N'??    ', 0)
GO
SET IDENTITY_INSERT [dbo].[TACGIA] ON 
GO
INSERT [dbo].[TACGIA] ([ID], [Ten]) VALUES (1, N'Jos?? Mauro de Vasconcelos')
GO
INSERT [dbo].[TACGIA] ([ID], [Ten]) VALUES (2, N'Robin Sharma')
GO
INSERT [dbo].[TACGIA] ([ID], [Ten]) VALUES (3, N'B??? Gi??o D???c V?? ????o T???o')
GO
INSERT [dbo].[TACGIA] ([ID], [Ten]) VALUES (4, N'V?? Nguy??n Gi??p')
GO
INSERT [dbo].[TACGIA] ([ID], [Ten]) VALUES (5, N'Sir Alex Ferguson')
GO
INSERT [dbo].[TACGIA] ([ID], [Ten]) VALUES (6, N'Wayne Rooney')
GO
INSERT [dbo].[TACGIA] ([ID], [Ten]) VALUES (7, N'V?? Ho??ng')
GO
INSERT [dbo].[TACGIA] ([ID], [Ten]) VALUES (8, N'Tr???n Anh Khoa')
GO
INSERT [dbo].[TACGIA] ([ID], [Ten]) VALUES (9, N'Tchaikopski')
GO
INSERT [dbo].[TACGIA] ([ID], [Ten]) VALUES (10, N'Nguy???n Nh???t ??nh')
GO
INSERT [dbo].[TACGIA] ([ID], [Ten]) VALUES (11, N'Adui na HUi')
GO
INSERT [dbo].[TACGIA] ([ID], [Ten]) VALUES (12, N'Alahu Akba')
GO
INSERT [dbo].[TACGIA] ([ID], [Ten]) VALUES (13, N'D????ng Th??i Phong')
GO
INSERT [dbo].[TACGIA] ([ID], [Ten]) VALUES (14, N'Tr???n ????nh')
GO
INSERT [dbo].[TACGIA] ([ID], [Ten]) VALUES (15, N'T??? H???u')
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
