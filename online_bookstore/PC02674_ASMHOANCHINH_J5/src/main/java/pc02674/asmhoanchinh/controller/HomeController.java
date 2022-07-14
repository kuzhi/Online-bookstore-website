package pc02674.asmhoanchinh.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pc02674.asmhoanchinh.DAO.DonHangCTDAO;
import pc02674.asmhoanchinh.DAO.DonHangDAO;
import pc02674.asmhoanchinh.DAO.FavoriteDAO;
import pc02674.asmhoanchinh.DAO.GaleryDAO;
import pc02674.asmhoanchinh.DAO.GioHangCTDAO;
import pc02674.asmhoanchinh.DAO.GioHangDAO;
import pc02674.asmhoanchinh.DAO.KhachHangDAO;
import pc02674.asmhoanchinh.DAO.LoaiSachDAO;
import pc02674.asmhoanchinh.DAO.NguoiDungDAO;
import pc02674.asmhoanchinh.DAO.NhaCungCapDAO;
import pc02674.asmhoanchinh.DAO.NhaXuatBanDAO;
import pc02674.asmhoanchinh.DAO.SachDAO;
import pc02674.asmhoanchinh.DAO.TacGiaDAO;
import pc02674.asmhoanchinh.common.PageInfo;
import pc02674.asmhoanchinh.common.PageType;
import pc02674.asmhoanchinh.entity.DonHang;
import pc02674.asmhoanchinh.entity.DonHangCT;
import pc02674.asmhoanchinh.entity.Favorite;
import pc02674.asmhoanchinh.entity.GioHang;
import pc02674.asmhoanchinh.entity.GioHangCT;
import pc02674.asmhoanchinh.entity.KhachHang;
import pc02674.asmhoanchinh.entity.LoaiSach;
import pc02674.asmhoanchinh.entity.NguoiDung;
import pc02674.asmhoanchinh.entity.Sach;
import pc02674.asmhoanchinh.services.CookieService;
import pc02674.asmhoanchinh.services.MailerServiceImpl;
import pc02674.asmhoanchinh.services.ParamService;
import pc02674.asmhoanchinh.services.SessionService;
import pc02674.asmhoanchinh.services.ShoppingCartService;
import pc02674.asmhoanchinh.services.ShoppingCartServiceImpl;

@Controller

public class HomeController {
	
	@Autowired
	HttpServletResponse response;
	@Autowired
	HttpServletRequest request;
	
	//DAO
		@Autowired
		NguoiDungDAO nguoiDungDAO;
		
		@Autowired
		GaleryDAO galeryDAO;
		
		@Autowired
		LoaiSachDAO loaiSachDAO;
		
		@Autowired
		DonHangDAO donHangDAO;
		
		@Autowired
		DonHangCTDAO donHangCTDAO;
		
		@Autowired
		KhachHangDAO khachHangDAO;
		
		@Autowired
		NhaXuatBanDAO nxbDAO;
		
		@Autowired
		NhaCungCapDAO nccDAO;
		
		@Autowired
		TacGiaDAO tacGiaDAO;
		
		@Autowired
		SachDAO sachDAO;
		
		@Autowired
		GioHangCTDAO gioHangCTDAO;
		
		@Autowired
		GioHangDAO gioHangDAO;
		
		@Autowired
		FavoriteDAO favoDAO;
		
		
		
		@Autowired
		ShoppingCartServiceImpl shoppingCart; 
		
		@Autowired
		ShoppingCartService cart; 
		
		@Autowired
		SessionService session;
		
		@Autowired
		ParamService paramService;
		
		@Autowired
		CookieService cookieService;
		
		@Autowired
		MailerServiceImpl mailer;
	
	@RequestMapping("/kgbBookstore.com")
	public String HomeControlle(Model model) {
		// TODO Auto-generated constructor stub
		
		try {
			//paginate
			session.remove("errorAuth");
			Pageable pageable = PageRequest.of(0,10);
			

			///
			Page<Sach> page = sachDAO.findAll(pageable); 
			model.addAttribute("page1", page);
			if(session.get("user")!= null) {
				gioHang(model);
			}
			
			PageInfo.prepareAndForward(request, PageType.SITE_HOME_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "home/product";
	}
	
	@RequestMapping("/kgbBookstore.com/page")
	public String sachPaginate(Model model, @RequestParam("p") Optional<Integer>p) {
		try {
		//paginate
		
		Pageable pageable = PageRequest.of(p.orElse(0), 10);
		

		///
		Page<Sach> page = sachDAO.findAll(pageable); 
		checkGioHang(model);
		model.addAttribute("page1", page);
		
			PageInfo.prepareAndForward(request, PageType.SITE_HOME_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return "home/product";
	}
	
	
	@RequestMapping("/kgbBookstore.com/The-loai-sach/{id}")
	public String loaiSach(Model model, @PathVariable("id")Integer id) {
		// TODO Auto-generated constructor stub
		
		try {
			//paginate
			
			Pageable pageable = PageRequest.of(0,10);
			LoaiSach cate = loaiSachDAO.findById(id).get();
			checkGioHang(model);
			///
			Page<Sach> page =  sachDAO.findAllByLoai(id, pageable);;
			model.addAttribute("page1", page);
			model.addAttribute("cate", cate);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		return "home/theloai";
	}
	
	@RequestMapping("/kgbBookstore.com/The-loai-sach/{id}/page")
	public String loaiSachPaginate(Model model, @RequestParam("p") Optional<Integer>p, @PathVariable("id")Integer id) {
		
		//paginate
		try {
			
		
		Pageable pageable = PageRequest.of(p.orElse(0), 10);
		

		LoaiSach cate = loaiSachDAO.findById(id).get();
		

		
		///
		Page<Sach> page =  sachDAO.findAllByLoai(id, pageable);;
		model.addAttribute("page1", page);
		model.addAttribute("cate", cate);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "home/theloai";
	}

	@RequestMapping("/kgbBookstore.com/search")
	public String search(Model model,@RequestParam("searchBook") Optional<String>nameBook) {
		// TODO Auto-generated constructor stub
		
		try {
			//paginate
			String nBook = nameBook.orElse(session.get("searchBook"));
			session.set("searchBook", nBook);
			Pageable pageable = PageRequest.of(0,10);
			
			///
			Page<Sach> page =  sachDAO.findAllByName("%"+nBook+"%" ,pageable);
			if(page.isEmpty()) {
				request.setAttribute("errorP", "không có sách này trong hệ thống");
				 page =  sachDAO.findAll(pageable);
			}
			model.addAttribute("page1", page);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		return "home/product";
	}
	
	
	
	
	
	@RequestMapping("/kgbBookstore.com/customer/account")
	public String hoSoCN(Model model) {
		// TODO Auto-generated constructor stub
		
		try {
			checkGioHang(model);
			PageInfo.prepareAndForward(request, PageType.SITE_EDIT_PROFILE_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "home/hoSoCaNhan";
	}
	
	
	@RequestMapping("/kgbBookstore.com/customer/favorite")
	public String yeuthich(Model model) {
		// TODO Auto-generated constructor stub
		
		try {
			//paginate
			
			Pageable pageable = PageRequest.of(0,10);
			NguoiDung a = session.get("user"); 
			List<Favorite> list = favoDAO.findAllByUser(a.getId());
			

			checkGioHang(model);
			///
			Page<Favorite> page =  new PageImpl<Favorite>(list.subList(0, list.size()),pageable,list.size());
			model.addAttribute("page1", page);
			
			PageInfo.prepareAndForward(request, PageType.SITE_FAVORITE_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "home/YeuThich";
	}
	
	@RequestMapping("/kgbBookstore.com/customer/favorite/page")
	public String yeuthichPaginate(Model model, @RequestParam("p") Optional<Integer>p) {
		try {
		//paginate
		
		Pageable pageable = PageRequest.of(p.orElse(0), 10);
		

		NguoiDung a = session.get("user"); 
		List<Favorite> list = favoDAO.findAllByUser(a.getId());
		
		checkGioHang(model);
		
		///
		Page<Favorite> page =  new PageImpl<Favorite>(list.subList(0, list.size()),pageable,list.size());
		model.addAttribute("page1", page);
		
		PageInfo.prepareAndForward(request, PageType.SITE_FAVORITE_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return "home/YeuThich";
	}
	
	@RequestMapping("/kgbBookstore.com/customer/favorite/remove/{id}")
	public String yeuthichRemove(@PathVariable("id") Long id) {
		try {
		
		favoDAO.deleteById(id);
		
		
		PageInfo.prepareAndForward(request, PageType.SITE_FAVORITE_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return "redirect:/kgbBookstore.com/customer/favorite";
	}
	
	
	
	
	
	@RequestMapping("/kgbBookstore.com/customer/cart")
	public String gioHang(Model model) {
		// TODO Auto-generated constructor stub
		
		try {
			
			NguoiDung user = session.get("user");
			GioHang gh = gioHangDAO.findByUser("%" + user.getId() + "%");
			if(gh != null) {
				List<GioHangCT> list = gioHangCTDAO.findAllByIDGioHang(gh.getId());
				
				
				session.set("carts", list);
				Double totalPrice = shoppingCart.getAmount(gh.getId());
				model.addAttribute("totalPrice", totalPrice);
				int count = shoppingCart.getCount(gh.getId());
				model.addAttribute("count", count);
			}
			else {
				return "redirect:/kgbBookstore.com";
			}
			PageInfo.prepareAndForward(request, PageType.SITE_CART_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "home/GioHang";
	}
	
	@RequestMapping("/kgbBookstore.com/customer/cart/add/{id}")
	public String addgioHang(@PathVariable("id")String id) {
		// TODO Auto-generated constructor stub
		NguoiDung user = session.get("user");
		
		
		GioHang gh = gioHangDAO.findByUser("%" + user.getId() + "%");
		
		if(gh !=null) {
			shoppingCart.add(id, gh);

		}
		else {
			gh = new GioHang();
			gh.setNguoiDung(user);
			gioHangDAO.saveAndFlush(gh);
			shoppingCart.add(id, gh);
		}
		
		
		return "redirect:/kgbBookstore.com";
	}
	
	@RequestMapping("/kgbBookstore.com/customer/cart/update/{id}")
	public String update(@PathVariable("id") Long id, 
			@RequestParam("soLuong") Optional<Integer>  qty) {
		if(qty.isPresent()) {
			if(qty.get()>0) {
				shoppingCart.update(id, qty.get());

			}
			else {
				System.out.println("wrong negative");
			}
		}
		else {
			System.out.println("wrong");
		}
		return "redirect:/kgbBookstore.com/customer/cart";
	}
	
	@RequestMapping("/kgbBookstore.com/customer/cart/remove/{id}")
	public String remove(@PathVariable("id") Long id) {
		shoppingCart.remove(id);
		return "redirect:/kgbBookstore.com/customer/cart";
	}
	
	
	
	@RequestMapping("/kgbBookstore.com/detail-product/{id}")
	public String chiTiet(@PathVariable("id") String id, Model model) {
		// TODO Auto-generated constructor stub
		
		try {
			
			checkGioHang(model);
			Sach sach = sachDAO.findById(id).get();
			model.addAttribute("sachs", sach);
			
			PageInfo.prepareAndForward(request, PageType.SITE_PRODUCT_DETAIL_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "home/ChiTietSanPham";
	}
	
	@RequestMapping("/kgbBookstore.com/detail-product/add/{id}")
	public String addSanpham(@PathVariable("id") String id ,Model model) {
		// TODO Auto-generated constructor stub
		
		try {
			int soluong = paramService.getInt("soluong2", 0);

			NguoiDung user = session.get("user");
			
			
			GioHang gh = gioHangDAO.findByUser("%" + user.getId() + "%");
			
			if(gh !=null) {
				shoppingCart.add(id, gh, soluong);

			}
			else {
				gh = new GioHang();
				gh.setNguoiDung(user);
				gioHangDAO.saveAndFlush(gh);
				shoppingCart.add(id, gh, soluong);
			}
			
			
			PageInfo.prepareAndForward(request, PageType.SITE_PRODUCT_DETAIL_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/kgbBookstore.com/detail-product/{id}";
	}
	
	
	@RequestMapping("/kgbBookstore.com/customer/favorite/add/{id}")
	public String favorite(Model model,@PathVariable("id")String id) {
		
		NguoiDung user = session.get("user");
		checkGioHang(model);
		Sach sach = sachDAO.findById(id).get();
		Date date = new Date();
		Favorite favo = favoDAO.findAllByUserAndSach(user.getId(), "%"+id+"%");
		if(favo == null) {
			 favo = new Favorite();
			
			favo.setNguoiDung(user);
			favo.setSach(sach);
			favo.setLikeDate(date);
			favoDAO.saveAndFlush(favo);
		}
		
		
		return "redirect:/kgbBookstore.com/detail-product/{id}";
	}
	
	
	@ModelAttribute("loaiSachs")
	public List<LoaiSach> getlSach(){
		
		return loaiSachDAO.findAll(); 
		
		
	}
	
	
	
	public void checkGioHang(Model model) {
		if(session.get("user")!= null) {
			gioHang(model);
		}
		
		
	}
	
	@RequestMapping("/kgbBookstore.com/customer/checkout")
	public String checkout(Model model) {
		
		
		
		checkGioHang(model);
		KhachHang customers = new KhachHang();
		model.addAttribute("customers",customers);
		return "home/KhachHang";
	}
	
	
	@PostMapping("/kgbBookstore.com/customer/checkout")
	public String checkoutP(@Validated @ModelAttribute("customers") Optional<KhachHang> customers, Model model, BindingResult errors) {
		checkGioHang(model);
		if(errors.hasErrors()) {
			model.addAttribute("error", "Vui lòng sửa các lỗi sau: ");
		}
		else {
			try {
				
			
			model.addAttribute("message", "Chúc mừng bạn đã nhập đúng");
			khachHangDAO.saveAndFlush(customers.get());

			//don hang 
			Long countDh = donHangDAO.count();
			NguoiDung user = session.get("user");
			GioHang gh = gioHangDAO.findByUser("%" + user.getId() + "%");
			List<GioHangCT> gioHangCT=gioHangCTDAO.findAllByIDGioHang(gh.getId());
			
			DonHang donHang = new DonHang();
			Date date = new Date();
			float totalPrice = (float) shoppingCart.getAmount(gh.getId());
			if(countDh<=9) {
				donHang.setId("DH0" + (countDh+1));
			}
			else {
				donHang.setId("DH" + (countDh+1));
			}
			donHang.setKhachhang(customers.get());
			donHang.setNguoiDung(user);
			donHang.setTrangThai(false);
			donHang.setNgay(date);
			donHang.setTongTien(totalPrice);
			donHangDAO.saveAndFlush(donHang);
			for(GioHangCT ct: gioHangCT ) {
				DonHangCT donHangCT = new DonHangCT();
				donHangCT.setDonHang(donHang);
				donHangCT.setSach(ct.getSach());
				donHangCT.setSoLuong(ct.getSoLuong());
				
				if(ct.getSach().getDiscount()>0) {
					donHangCT.setTien(ct.getSach().getGia() * ct.getSach().getDiscount()*ct.getSoLuong());
				}
				else {
					donHangCT.setTien(ct.getSach().getGia()*ct.getSoLuong());
				}
				
				donHangCTDAO.saveAndFlush(donHangCT);
				Sach sachs =sachDAO.findById(donHangCT.getSach().getId()).get();
				int soluongSach= sachs.getSoLuong() - donHangCT.getSoLuong();
				sachs.setSoLuong(soluongSach);
				}
			
			
				shoppingCart.clear(gh.getId(),user.getId());
				session.remove("carts");
				return "redirect:/kgbBookstore.com/customer/done";
				
			

			
			
			} catch (Exception e) {
				model.addAttribute("error", "Lỗi tạo đon hàng ");

			}
		}
		
		
		return "home/KhachHang";
	}
	
	@RequestMapping("/kgbBookstore.com/customer/done")
	public String done(Model model) throws MessagingException {
		
		
		NguoiDung user = session.get("user");
		checkGioHang(model);
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		
		
		mailer.send(user.getEmail(), 
				"[KGB BOOKSTORE] [No-reply] Đơn hàng! ", 
				"Đơn hàng của bạn đã được đặt vào lúc " + formatter.format(calendar.getTime()));
		return "home/ThanhToan";
	}
	
	
	@RequestMapping("/kgbBookstore.com/customer/history")
	public String donHangTable(Model model) {
		
		try {
			//paginate
			
			Pageable pageable = PageRequest.of(0,5);

			
			NguoiDung user = session.get("user");
			///
			Page<DonHang> page = donHangDAO.findAllByIdUser(user.getId(),pageable); 
			checkGioHang(model);
			model.addAttribute("page1", page);
			PageInfo.prepareAndForward(request, PageType.SITE_HISTORY_BUY);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "home/history";
	}
	
	@RequestMapping("/kgbBookstore.com/customer/history/page")
	public String donHangPaginate(Model model, @RequestParam("p") Optional<Integer>p) {
		try {
		//paginate
		
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		
		NguoiDung user = session.get("user");
		///
		Page<DonHang> page = donHangDAO.findAllByIdUser(user.getId(),pageable); 
		checkGioHang(model);
		model.addAttribute("page1", page);
		
		PageInfo.prepareAndForward(request, PageType.SITE_HISTORY_BUY);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return "home/history";
	}
	
	
	//thanh sort
	@RequestMapping("/kgbBookstore.com/sort/A-Z")
	public String sortAZ(Model model ) {
		try {
			//paginate
			Pageable pageable = Pageable.unpaged();
			


			///
			Page<Sach> page = sachDAO.findByOrderByTenSachAsc(pageable); 
			model.addAttribute("page1", page);
			if(session.get("user")!= null) {
				gioHang(model);
			}
			
			PageInfo.prepareAndForward(request, PageType.SITE_HOME_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "home/sort";
	}
	
		
	
	@RequestMapping("/kgbBookstore.com/sort/Z-A")
	public String sortZA(Model model) {
		
		try {
			//paginate
			Pageable pageable = Pageable.unpaged();
			


			///
			Page<Sach> page = sachDAO.findByOrderByTenSachDesc(pageable); 
			model.addAttribute("page1", page);
			if(session.get("user")!= null) {
				gioHang(model);
			}
			
			PageInfo.prepareAndForward(request, PageType.SITE_HOME_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "home/sort";
	}
}
