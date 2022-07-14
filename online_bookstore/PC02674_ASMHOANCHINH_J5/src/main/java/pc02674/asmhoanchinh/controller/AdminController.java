package pc02674.asmhoanchinh.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import pc02674.asmhoanchinh.DAO.DonHangCTDAO;
import pc02674.asmhoanchinh.DAO.DonHangDAO;
import pc02674.asmhoanchinh.DAO.GaleryDAO;
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
import pc02674.asmhoanchinh.entity.Galery;
import pc02674.asmhoanchinh.entity.KhachHang;
import pc02674.asmhoanchinh.entity.LoaiSach;
import pc02674.asmhoanchinh.entity.NguoiDung;
import pc02674.asmhoanchinh.entity.NhaCungCap;
import pc02674.asmhoanchinh.entity.NhaXuatBan;
import pc02674.asmhoanchinh.entity.Report;
import pc02674.asmhoanchinh.entity.Sach;
import pc02674.asmhoanchinh.entity.TacGia;
import pc02674.asmhoanchinh.services.SessionService;
import pc02674.asmhoanchinh.services.UploadService;

@Controller

@RequestMapping("/kgbBookstore.com/admin")
public class AdminController {
	
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
	SessionService session;
	
	@Autowired
	ServletContext application;
	
	@Autowired
	UploadService upload;
	
	
	public long countSach = 0;
	
	//
	@RequestMapping("/index")
	public String index(Model model) {
		
		Long countUsers = nguoiDungDAO.count();
		Long countBook = sachDAO.count();
		Long countDonHang = donHangDAO.countDonHang(0);
		
		model.addAttribute("countUsers", countUsers);
		model.addAttribute("countBook", countBook);
		model.addAttribute("countDonHang", countDonHang);
		
		return "admin/Dashboard/dashboard";
	}
	
	//quan ly nguoi dung
	@RequestMapping("/user/form")
	public String userForm(Model model) {
		
		try {
			NguoiDung users = new NguoiDung();
			model.addAttribute("users", users);
			model.addAttribute("readOnly", "false");
			PageInfo.prepareAndForward(request, PageType.USERF_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/NguoiDung/FormNguoiDung";
	}
	
	@RequestMapping("/user/form/create")
	public String userForm(@Validated @ModelAttribute("users") NguoiDung users, BindingResult errors, Model model) {
		
		try {
			
			
			if(errors.hasErrors()) {
				model.addAttribute("error", "Vui lòng sửa các lỗi sau: ");
				
				model.addAttribute("users.id", null);
			}
			else {
				model.addAttribute("message", "Chúc mừng bạn đã nhập đúng");
				if(users.getChucVu() == 3) {
					users.setCMND("0000000000");
					users.setSDT("0000000000");
				}
				nguoiDungDAO.saveAndFlush(users);
				
			}
			
			
			PageInfo.prepareAndForward(request, PageType.USERF_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/NguoiDung/FormNguoiDung";
	}
	
	@RequestMapping("/user/form/update")
	public String userFormUpdate(@Validated @ModelAttribute("users") NguoiDung user, BindingResult errors, Model model) {
		
		try {
			
			if(errors.hasErrors()) {
				model.addAttribute("error", "Vui lòng sửa các lỗi sau: ");
			}
			else {
				model.addAttribute("message", "Chúc mừng bạn đã nhập đúng");
				if(user.getChucVu() == 3) {
					user.setCMND("0000000000");
					user.setSDT("0000000000");
				}
				
				
				nguoiDungDAO.saveAndFlush(user);
				
			}
			
			
			PageInfo.prepareAndForward(request, PageType.USERF_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/kgbBookstore.com/admin/user/table/edit/" + user.getId();
	}
	
	@RequestMapping("/user/table/edit/{id}")
	public String userTableEdit(Model model, @PathVariable("id") String id) {
		
		try {
			NguoiDung newUser = nguoiDungDAO.findById(id).get();
			model.addAttribute("users", newUser);
			model.addAttribute("readOnly", "true");
			PageInfo.prepareAndForward(request, PageType.USERF_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/NguoiDung/FormNguoiDung";
	}
	
	
	@RequestMapping("/user/table/delete/{id}")
	public String userTableDelete(Model model, @PathVariable("id") String id) {
		
		try {
			NguoiDung newUser = nguoiDungDAO.findById(id).get();
			NguoiDung user = session.get("user");
			if(!newUser.getId().equals(user.getId())) {
		
				newUser.setDaXoa(true);
				nguoiDungDAO.saveAndFlush(newUser);
			}
			
			PageInfo.prepareAndForward(request, PageType.USERT_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/kgbBookstore.com/admin/user/table" ;
	}
	
	@RequestMapping("/user/table")
	public String userTable(Model model) {
		
		try {
			//paginate
			
			Pageable pageable = PageRequest.of(0,5);
			

			///
			Page<NguoiDung> page = nguoiDungDAO.findAll(pageable); 
			model.addAttribute("page1", page);
			
			PageInfo.prepareAndForward(request, PageType.USERT_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/NguoiDung/TableNguoiDung";
	}
	
	@RequestMapping("/user/table/page")
	public String paginate(Model model, @RequestParam("p") Optional<Integer>p) {
		try {
		//paginate
		
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		

		///
		Page<NguoiDung> page = nguoiDungDAO.findAll(pageable); 
		
		model.addAttribute("page1", page);
		
		//model.addAttribute("totalSize", page.getSize());
			PageInfo.prepareAndForward(request, PageType.USERT_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return "admin/NguoiDung/TableNguoiDung";
	}
	
	/////
	
	//quan ly loai san pham
	@RequestMapping("/category/form")
	public String categoryForm(Model model) {
		
		try {
			LoaiSach loaiSach = new LoaiSach();
			model.addAttribute("categories", loaiSach);
			PageInfo.prepareAndForward(request, PageType.TYPEBOOKF_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/LoaiSach/FormLoaiSach";
	}
	
	@RequestMapping("/category/form/create")
	public String categoryForm(@Validated @ModelAttribute("categories") LoaiSach loaiSach, BindingResult errors, Model model) {
		
		try {
			
			if(errors.hasErrors()) {
				model.addAttribute("error", "Vui lòng sửa các lỗi sau: ");
			}
			else {
				model.addAttribute("message", "Chúc mừng bạn đã nhập đúng");
				loaiSachDAO.saveAndFlush(loaiSach);
			}
			
			
			
			PageInfo.prepareAndForward(request, PageType.TYPEBOOKF_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/LoaiSach/FormLoaiSach";
	}
	
	@RequestMapping("/category/form/update")
	public String categoryFormUpdate(@Validated @ModelAttribute("categories") LoaiSach loaiSach, BindingResult errors, Model model) {
		
		try {
			if(errors.hasErrors()) {
				model.addAttribute("error", "Vui lòng sửa các lỗi sau: ");
			}
			else {
				model.addAttribute("message", "Chúc mừng bạn đã nhập đúng");
				loaiSachDAO.saveAndFlush(loaiSach);
			}
			PageInfo.prepareAndForward(request, PageType.TYPEBOOKF_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/kgbBookstore.com/admin/category/table/edit/" + loaiSach.getId();
	}
	
	@RequestMapping("/category/table/edit/{id}")
	public String categoryTableEdit(Model model, @PathVariable("id") Integer id) {
		
		try {
			LoaiSach newloaiSach = loaiSachDAO.findById(id).get();
			model.addAttribute("categories", newloaiSach);
			PageInfo.prepareAndForward(request, PageType.TYPEBOOKF_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/LoaiSach/FormLoaiSach";
	}
	
	
	@RequestMapping("/category/table/delete/{id}")
	public String categoryTableDelete(Model model, @PathVariable("id") Integer id) {
		
		try {
			loaiSachDAO.deleteById(id);
			PageInfo.prepareAndForward(request, PageType.TYPEBOOKT_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/kgbBookstore.com/admin/category/table" ;
	}
	
	@RequestMapping("/category/table")
	public String categoryTable(Model model) {
		
		try {
			//paginate
			
			Pageable pageable = PageRequest.of(0,5);
			

			///
			Page<LoaiSach> page = loaiSachDAO.findAll(pageable); 
			model.addAttribute("page1", page);
			PageInfo.prepareAndForward(request, PageType.TYPEBOOKT_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/LoaiSach/TableLoaiSach";
	}
	
	@RequestMapping("/category/table/page")
	public String categoryPaginate(Model model, @RequestParam("p") Optional<Integer>p) {
		try {
		//paginate
		
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		

		///
		Page<LoaiSach> page = loaiSachDAO.findAll(pageable); 
		
		model.addAttribute("page1", page);
		
			PageInfo.prepareAndForward(request, PageType.TYPEBOOKT_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return "admin/LoaiSach/TableLoaiSach";
	}
	/////////////
	
	//quan ly nha xuat ban
	
	
	
	@RequestMapping("/nha-xuat-ban/form")
	public String nxbForm(Model model) {
		
		try {
			NhaXuatBan nxb = new NhaXuatBan();
			model.addAttribute("nxb", nxb);
			PageInfo.prepareAndForward(request, PageType.NXBF_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/NXB/FormNXB";
	}
	
	@RequestMapping("/nha-xuat-ban/form/create")
	public String nxbForm(@Validated @ModelAttribute("nxb") NhaXuatBan nxb, BindingResult errors, Model model) {
		
		try {
			
			if(errors.hasErrors()) {
				model.addAttribute("error", "Vui lòng sửa các lỗi sau: ");
			}
			else {
				model.addAttribute("message", "Chúc mừng bạn đã nhập đúng");
				nxbDAO.saveAndFlush(nxb);
			}
			
			
			
			PageInfo.prepareAndForward(request, PageType.NXBF_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/NXB/FormNXB";
	}
	
	@RequestMapping("/nha-xuat-ban/form/update")
	public String nxbFormUpdate(@Validated @ModelAttribute("nxb") NhaXuatBan nxb, BindingResult errors, Model model) {
		
		try {
			if(errors.hasErrors()) {
				model.addAttribute("error", "Vui lòng sửa các lỗi sau: ");
			}
			else {
				model.addAttribute("message", "Chúc mừng bạn đã nhập đúng");
				nxbDAO.saveAndFlush(nxb);
			}
			PageInfo.prepareAndForward(request, PageType.NXBF_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/kgbBookstore.com/admin/nha-xuat-ban/table/edit/" + nxb.getId();
	}
	
	@RequestMapping("/nha-xuat-ban/table/edit/{id}")
	public String nxbTableEdit(Model model, @PathVariable("id") Integer id) {
		
		try {
			NhaXuatBan newNxb = nxbDAO.findById(id).get();
			model.addAttribute("nxb", newNxb);
			PageInfo.prepareAndForward(request, PageType.NXBF_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/NXB/FormNXB";
	}
	
	
	@RequestMapping("/nha-xuat-ban/table/delete/{id}")
	public String nxbTableDelete(Model model, @PathVariable("id") Integer id) {
		
		try {
			nxbDAO.deleteById(id);
			PageInfo.prepareAndForward(request, PageType.NXBT_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/kgbBookstore.com/admin/nha-xuat-ban/table" ;
	}
	
	@RequestMapping("/nha-xuat-ban/table")
	public String nxbTable(Model model) {
		
		try {
			//paginate
			
			Pageable pageable = PageRequest.of(0,5);
			

			///
			Page<NhaXuatBan> page = nxbDAO.findAll(pageable); 
			model.addAttribute("page1", page);
			PageInfo.prepareAndForward(request, PageType.NXBT_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/NXB/TableNXB";
	}
	
	@RequestMapping("/nha-xuat-ban/table/page")
	public String nxbPaginate(Model model, @RequestParam("p") Optional<Integer>p) {
		try {
		//paginate
		
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		

		///
		Page<NhaXuatBan> page = nxbDAO.findAll(pageable); 
		
		model.addAttribute("page1", page);
		
			PageInfo.prepareAndForward(request, PageType.NXBT_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return "admin/NXB/TableNXB";
	}
	
	//Tac gia
	
	@RequestMapping("/tac-gia/form")
	public String tacGiaForm(Model model) {
		
		try {
			TacGia tacGia = new TacGia();
			model.addAttribute("tacGia", tacGia);
			PageInfo.prepareAndForward(request, PageType.TACGIAF_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/TacGia/FormTacGia";
	}
	
	@RequestMapping("/tac-gia/form/create")
	public String tacGiaForm(@Validated @ModelAttribute("tacGia") TacGia tacGia, BindingResult errors, Model model) {
		
		try {
			
			if(errors.hasErrors()) {
				model.addAttribute("error", "Vui lòng sửa các lỗi sau: ");
			}
			else {
				model.addAttribute("message", "Chúc mừng bạn đã nhập đúng");
				tacGiaDAO.saveAndFlush(tacGia);
			}
			
			
			
			PageInfo.prepareAndForward(request, PageType.TACGIAF_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/TacGia/FormTacGia";
	}
	
	@RequestMapping("/tac-gia/form/update")
	public String tacGiaFormUpdate(@Validated @ModelAttribute("tacGia") TacGia tacGia, BindingResult errors, Model model) {
		
		try {
			if(errors.hasErrors()) {
				model.addAttribute("error", "Vui lòng sửa các lỗi sau: ");
			}
			else {
				model.addAttribute("message", "Chúc mừng bạn đã nhập đúng");
				tacGiaDAO.saveAndFlush(tacGia);
			}
			PageInfo.prepareAndForward(request, PageType.TACGIAF_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/kgbBookstore.com/admin/tac-gia/table/edit/" + tacGia.getId();
	}
	
	@RequestMapping("/tac-gia/table/edit/{id}")
	public String tacGiaTableEdit(Model model, @PathVariable("id") Integer id) {
		
		try {
			TacGia newTacGia = tacGiaDAO.findById(id).get();
			model.addAttribute("tacGia", newTacGia);
			PageInfo.prepareAndForward(request, PageType.TACGIAF_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/TacGia/FormTacGia";
	}
	
	
	@RequestMapping("/tac-gia/table/delete/{id}")
	public String tacGiaTableDelete(Model model, @PathVariable("id") Integer id) {
		
		try {
			tacGiaDAO.deleteById(id);
			PageInfo.prepareAndForward(request, PageType.TACGIAT_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/kgbBookstore.com/admin/tac-gia/table" ;
	}
	
	@RequestMapping("/tac-gia/table")
	public String tacGiaTable(Model model) {
		
		try {
			//paginate
			
			Pageable pageable = PageRequest.of(0,5);
			

			///
			Page<TacGia> page = tacGiaDAO.findAll(pageable); 
			model.addAttribute("page1", page);
			PageInfo.prepareAndForward(request, PageType.TACGIAT_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/TacGia/TableTacGia";
	}
	
	@RequestMapping("/tac-gia/table/page")
	public String tacGiaPaginate(Model model, @RequestParam("p") Optional<Integer>p) {
		try {
		//paginate
		
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		

		///
		Page<TacGia> page = tacGiaDAO.findAll(pageable); 
		
		model.addAttribute("page1", page);
		
			PageInfo.prepareAndForward(request, PageType.TACGIAT_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return "admin/TacGia/TableTacGia";
	}
	
	///////////
	//quan li khach hang
	
	@RequestMapping("/customer/table")
	public String customerTable(Model model) {
		
		try {
			//paginate
			
			Pageable pageable = PageRequest.of(0,5);
			

			///
			Page<KhachHang> page = khachHangDAO.findAll(pageable); 
			model.addAttribute("page1", page);
			PageInfo.prepareAndForward(request, PageType.CUSTOMERS_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/KhachHang/TableCustomer";
	}
	
	@RequestMapping("/customer/table/page")
	public String customerPaginate(Model model, @RequestParam("p") Optional<Integer>p) {
		try {
		//paginate
		
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		

		///
		Page<KhachHang> page = khachHangDAO.findAll(pageable); 
		
		model.addAttribute("page1", page);
		
			PageInfo.prepareAndForward(request, PageType.CUSTOMERS_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return "admin/KhachHang/TableCustomer";
	}
	
	//quan li nha cung cap
	@RequestMapping("/nha-cung-cap/form")
	public String nccForm(Model model) {
		
		try {
			NhaCungCap ncc = new NhaCungCap();
			model.addAttribute("ncc", ncc);
			PageInfo.prepareAndForward(request, PageType.NCCF_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/NCC/FormNCC";
	}
	
	@RequestMapping("/nha-cung-cap/form/create")
	public String nccForm(@Validated @ModelAttribute("ncc") NhaCungCap ncc, BindingResult errors, Model model) {
		
		try {
			
			if(errors.hasErrors()) {
				model.addAttribute("error", "Vui lòng sửa các lỗi sau: ");
			}
			else {
				model.addAttribute("message", "Chúc mừng bạn đã nhập đúng");
				nccDAO.saveAndFlush(ncc);
			}
			
			
			
			PageInfo.prepareAndForward(request, PageType.NCCF_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/NCC/FormNCC";
	}
	
	@RequestMapping("/nha-cung-cap/form/update")
	public String tacGiaFormUpdate(@Validated @ModelAttribute("ncc") NhaCungCap ncc, BindingResult errors, Model model) {
		
		try {
			if(errors.hasErrors()) {
				model.addAttribute("error", "Vui lòng sửa các lỗi sau: ");
			}
			else {
				model.addAttribute("message", "Chúc mừng bạn đã nhập đúng");
				nccDAO.saveAndFlush(ncc);
			}
			PageInfo.prepareAndForward(request, PageType.NCCF_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/NCC/FormNCC";
	}
	
	@RequestMapping("/nha-cung-cap/table/edit/{id}")
	public String nccTableEdit(Model model, @PathVariable("id") Integer id) {
		
		try {
			NhaCungCap newNcc = nccDAO.findById(id).get();
			model.addAttribute("ncc", newNcc);
			PageInfo.prepareAndForward(request, PageType.NCCF_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/NCC/FormNCC";
	}
	
	
	@RequestMapping("/nha-cung-cap/table/delete/{id}")
	public String nccTableDelete(Model model, @PathVariable("id") Integer id) {
		
		try {
			nccDAO.deleteById(id);
			PageInfo.prepareAndForward(request, PageType.NCCT_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/kgbBookstore.com/admin/nha-cung-cap/table" ;
	}
	
	@RequestMapping("/nha-cung-cap/table")
	public String nccTable(Model model) {
		
		try {
			//paginate
			
			Pageable pageable = PageRequest.of(0,5);
			

			///
			Page<NhaCungCap> page = nccDAO.findAll(pageable); 
			model.addAttribute("page1", page);
			PageInfo.prepareAndForward(request, PageType.NCCT_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/NCC/TableNCC";
	}
	
	@RequestMapping("/nha-cung-cap/table/page")
	public String nccPaginate(Model model, @RequestParam("p") Optional<Integer>p) {
		try {
		//paginate
		
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		

		///
		Page<NhaCungCap> page = nccDAO.findAll(pageable); 
		
		model.addAttribute("page1", page);
		
			PageInfo.prepareAndForward(request, PageType.NCCT_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return "admin/NCC/TableNCC";
	}
	
	
	//quan li don hang
	////form don hang
	@RequestMapping("/don-hang/form")
	public String donHangForm(Model model) {
		
		try {
			DonHang donHang = new DonHang();
			model.addAttribute("donhang", donHang);
			

			PageInfo.prepareAndForward(request, PageType.ORDER_FORM_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/DonHang/FormDonHang";
	}
	
	
	 
	
	@RequestMapping("/don-hang/form/update")
	public String donHangFormUpdate(@Validated @ModelAttribute("donhang") DonHang donHang, BindingResult errors, Model model) {
		
		try {
			if(errors.hasErrors()) {
				model.addAttribute("error", "Vui lòng sửa các lỗi sau: ");
			}
			else {
			
			
				donHangDAO.saveAndFlush(donHang);
				return "redirect:/kgbBookstore.com/admin/don-hang/danh-sach-cho";
			}
			PageInfo.prepareAndForward(request, PageType.ORDER_FORM_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/DonHang/FormDonHang";
	}
	
	@RequestMapping("/don-hang/danh-sach-cho/edit/{id}")
	public String donHangTableEdit(Model model, @PathVariable("id") String id) {
		
		try {
			DonHang newDonHang = donHangDAO.findById(id).get();
			

			model.addAttribute("donhang", newDonHang);
			model.addAttribute("tongTien", newDonHang.getTongTien());
			if(newDonHang.isTrangThai()==true) {
				model.addAttribute("done",true);
			}
			PageInfo.prepareAndForward(request, PageType.ORDER_FORM_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/DonHang/FormDonHang";
	}
	
	
	@RequestMapping("/don-hang/danh-sach-cho/delete/{id}")
	public String donHangTableDelete(Model model, @PathVariable("id") String id) {
		
		try {
			DonHang newDonHang = donHangDAO.findById(id).get();
			List<DonHangCT> dhCT = donHangCTDAO.findAllBydonHang(newDonHang.getId());
			for(DonHangCT dh : dhCT) {
				Sach sach = sachDAO.findById(dh.getSach().getId()).get();
				int a = dh.getSoLuong() + sach.getSoLuong();
				sach.setSoLuong(a);
				sachDAO.save(sach);
			}
			newDonHang.setDaXoa(true);
			donHangDAO.saveAndFlush(newDonHang);
			PageInfo.prepareAndForward(request, PageType.ORDER_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/kgbBookstore.com/admin/don-hang/danh-sach-cho" ;
	}
	
	@RequestMapping("/don-hang/danh-sach-cho")
	public String donHangTable(Model model) {
		
		try {
			//paginate
			
			Pageable pageable = PageRequest.of(0,5);

			
			
			///
			Page<DonHang> page = donHangDAO.findAll(pageable); 
			model.addAttribute("page1", page);
			PageInfo.prepareAndForward(request, PageType.ORDER_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/DonHang/TableDH";
	}
	
	@RequestMapping("/don-hang/danh-sach-cho/page")
	public String donHangPaginate(Model model, @RequestParam("p") Optional<Integer>p) {
		try {
		//paginate
		
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		

		///
		Page<DonHang> page = donHangDAO.findAll(pageable); 
		
		model.addAttribute("page1", page);
		
			PageInfo.prepareAndForward(request, PageType.ORDER_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return "admin/DonHang/TableDH";
	}
	
	@RequestMapping("/don-hang/danh-sach-huy")
	public String donHangHuyTable(Model model) {
		
		try {
			//paginate
			
			Pageable pageable = PageRequest.of(0,5);

			
			
			///
			Page<DonHang> page = donHangDAO.findAll(pageable); 
			model.addAttribute("page1", page);
			PageInfo.prepareAndForward(request, PageType.ORDER_DELETED_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/DonHang/TableDHHuy";
	}
	
	@RequestMapping("/don-hang/danh-sach-huy/page")
	public String donHangHuyPaginate(Model model, @RequestParam("p") Optional<Integer>p) {
		try {
		//paginate
		
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		

		///
		Page<DonHang> page = donHangDAO.findAll(pageable); 
		
		
		model.addAttribute("page1", page);
		
			PageInfo.prepareAndForward(request, PageType.ORDER_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return "admin/DonHang/TableDHHuy";
	}
	//quan ly sach
	
	
	@RequestMapping("/sach/form")
	public String sachForm(Model model) {
		
		try {
			Sach sach = new Sach();
			
			
			
			model.addAttribute("sachs", sach);
			model.addAttribute("readonlyC", "false");
			model.addAttribute("readonlyU", "true");
			
			PageInfo.prepareAndForward(request, PageType.BOOKF_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/Sach/FormSach";
	}
	
	@ModelAttribute("loaiSachs")
	public List<LoaiSach> getlSach(){
		
		return loaiSachDAO.findAll(); 
		
		
	}
	
	@ModelAttribute("nxbs")
	public List<NhaXuatBan> getNxb(){
		
		return nxbDAO.findAll(); 
		
		
	}
	
	@ModelAttribute("nccs")
	public List<NhaCungCap> getNcc(){
		
		return nccDAO.findAll(); 
		
		
	}
	
	@ModelAttribute("tgs")
	public List<TacGia> getTg(){
		
		return tacGiaDAO.findAll(); 
		
		
	}
	
	
	
	@RequestMapping("/sach/form/create")
	public String sachForm( @ModelAttribute("sachs") Sach sach, Model model, 
			@RequestParam("img") Optional<MultipartFile> fileAnh) {
		//, RequestParam("imgs") List<MultipartFile>galeries
		try {
				
				
			long countSach = sachDAO.count();
			System.out.println(countSach);
				
				
				if(countSach <=9) {
					sach.setId("SACH0"+ (countSach+1));
				}
				else {
					sach.setId("SACH"+ (countSach +1));
				}
				sach.setDvt("Đ");
				sach.getNgayTao();
				try {
				if (fileAnh.isPresent()) {
					System.out.println("Asd");
						MultipartFile fileImage = fileAnh.get(); 
						if(!fileImage.isEmpty()) {
							sach.setImages(upload.save(fileImage, "img"));
							model.addAttribute("message", "Đã thêm mới");
						}
						else {
							sach.setImages("No-Image.png");
						}
						
						
				}
				
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					model.addAttribute("error", "Thêm mới thất bại");
				}
				model.addAttribute("readonlyC", "false");
				model.addAttribute("readonlyU", "true");
				sachDAO.saveAndFlush(sach);
				/*for(MultipartFile img: galeries) {
					File file = upload.save(img, "img");
					Galery image = new Galery();
					image.setUrlImage(file.getName());
					image.setSach(sach);
					
				}*/

			PageInfo.prepareAndForward(request, PageType.BOOKF_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("error", "Thêm mới thất bại");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("error", "Thêm mới thất bại");
		}
		return "admin/Sach/FormSach";
	}
	
	@RequestMapping("/sach/form/update")
	public String sachFormUpdate( @ModelAttribute("sachs") Sach sach,  Model model, @RequestParam("img") Optional<MultipartFile> fileAnh) {
		
		try {
			
				Sach sachs = sachDAO.findById(sach.getId()).get();
				String oldImage = sachs.getImages();
				model.addAttribute("message", "Chúc mừng bạn đã nhập đúng");
				float a = sach.getDiscount();
				float roundOff =(float) (Math.round(a*100))/100;
				sach.setDvt("Đ");
				sach.setDiscount(roundOff);
				try {
					if (fileAnh.isPresent()) {
							MultipartFile fileImage = fileAnh.get(); 
							if(!fileImage.isEmpty()) {
								sach.setImages(upload.save(fileImage, "img"));
							}
							else {
								sach.setImages(oldImage);
							}
		
					}
					
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						model.addAttribute("error", "Thêm mới thất bại");
					}
				
				sachDAO.saveAndFlush(sach);
				model.addAttribute("readonlyC", "true");
				model.addAttribute("readonlyU", "false");

			PageInfo.prepareAndForward(request, PageType.BOOKF_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/Sach/FormSach";
	}
	
	@RequestMapping("/sach/table/edit/{id}")
	public String sachTableEdit(Model model,@PathVariable("id") String id) {
		
		try {
			Sach newSach = sachDAO.findById(id).get();
		
			model.addAttribute("readonlyC", "true");
			model.addAttribute("readonlyU", "false");
			model.addAttribute("sachs", newSach);
			PageInfo.prepareAndForward(request, PageType.BOOKF_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/Sach/FormSach";
	}
	
	
	@RequestMapping("/sach/table/delete/{id}")
	public String sachTableDelete(Model model, @PathVariable("id") String id) {
		
		try {
			Sach sach = sachDAO.findById(id).get();
			sach.setDaXoa(true);
			sachDAO.saveAndFlush(sach);
			PageInfo.prepareAndForward(request, PageType.BOOKT_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/kgbBookstore.com/admin/sach/table" ;
	}
	
	@RequestMapping("/sach/table")
	public String sachTable(Model model) {
		
		try {
			//paginate
			
			Pageable pageable = PageRequest.of(0,5);
			

			///
			Page<Sach> page = sachDAO.findAll(pageable); 
			model.addAttribute("page1", page);
			PageInfo.prepareAndForward(request, PageType.BOOKT_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/Sach/TableSach";
	}
	
	@RequestMapping("/sach/table/page")
	public String sachPaginate(Model model, @RequestParam("p") Optional<Integer>p) {
		try {
		//paginate
		
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		

		///
		Page<Sach> page = sachDAO.findAll(pageable); 
		
		model.addAttribute("page1", page);
		
			PageInfo.prepareAndForward(request, PageType.BOOKT_ADMIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return "admin/Sach/TableSach";
	}
	/////////////
	
	@RequestMapping("/report-by-category")
	public String reportTable(Model model) {
		
		try {
			//paginate
			
			Pageable pageable = PageRequest.of(0,20);
			

			///
			Page<Report> page = donHangCTDAO.revenueByCate(pageable); 
			model.addAttribute("page1", page);
			PageInfo.prepareAndForward(request, PageType.REPORT_CATEGORY_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/Report/TableReport";
	}
	
	@RequestMapping("/report-by-category/page")
	public String reportPaginate(Model model, @RequestParam("p") Optional<Integer>p) {
		try {
		//paginate
		
		Pageable pageable = PageRequest.of(p.orElse(0), 20);
		

		///
		Page<Report> page = donHangCTDAO.revenueByCate(pageable); 
		
		model.addAttribute("page1", page);
		
			PageInfo.prepareAndForward(request, PageType.REPORT_CATEGORY_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return "admin/Report/TableReport";
	}
	
	@RequestMapping("/logout")
	public String adminLogout() {
		
		session.remove("user");
		
		return "redirect:/kgbBookstore.com";
	}
	
	//
}
