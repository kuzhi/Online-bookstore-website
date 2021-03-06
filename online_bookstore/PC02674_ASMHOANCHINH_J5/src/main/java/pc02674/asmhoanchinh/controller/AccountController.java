package pc02674.asmhoanchinh.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pc02674.asmhoanchinh.DAO.LoaiSachDAO;
import pc02674.asmhoanchinh.DAO.NguoiDungDAO;
import pc02674.asmhoanchinh.common.PageInfo;
import pc02674.asmhoanchinh.common.PageType;
import pc02674.asmhoanchinh.entity.LoaiSach;
import pc02674.asmhoanchinh.entity.NguoiDung;
import pc02674.asmhoanchinh.services.CookieService;
import pc02674.asmhoanchinh.services.MailerService;
import pc02674.asmhoanchinh.services.ParamService;
import pc02674.asmhoanchinh.services.SessionService;

@Controller
public class AccountController {

	@Autowired
	HttpServletResponse response;
	@Autowired
	HttpServletRequest request;
	//DAO
	@Autowired
	NguoiDungDAO nguoiDungDAO;
	@Autowired
	LoaiSachDAO loaiSachDAO;
	
	@Autowired
	SessionService session;
	
	@Autowired
	ParamService paramService;
	
	@Autowired
	CookieService cookieService;
	
	@Autowired
	MailerService mailer;
	
	@Autowired
	HomeController home;
	
	public String makichhoat2=null;
	public NguoiDung getUser = null;
	
	@RequestMapping("/kgbBookstore.com/login")
	public String dangNhap( Model model) 
	{
		// TODO Auto-generated constructor stub
		
		try {
			home.checkGioHang(model);
				
				String un = session.get("un");
				String pw = session.get("pw");
				request.setAttribute("uid", un);
				request.setAttribute("upass", pw);
		
			
			PageInfo.prepareAndForward(request, PageType.SITE_LOGIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "home/DangNhap";
	}
	
	
	@PostMapping("/kgbBookstore.com/login")
	public String dangNhapPost( Model model) 
	{
		// TODO Auto-generated constructor stub
		
		try {
			
			String un = paramService.getString("id", "");
			String pw = paramService.getString("password", "");
			String rm = request.getParameter("remember");
			
			
			if(un.equalsIgnoreCase("")) {
				model.addAttribute("errorID", "Kh??ng ????? tr???ng username");
			}
			else if(pw.isEmpty()){
				model.addAttribute("errorPass", "Kh??ng ????? tr???ng m???t kh???u");
			}
			else if(un.equalsIgnoreCase("") && pw.equalsIgnoreCase("")) {
				model.addAttribute("errorID", "Kh??ng ????? tr???ng username");
				model.addAttribute("errorPass", "Kh??ng ????? tr???ng m???t kh???u");
			}
			else {
				try {
					NguoiDung findUser = nguoiDungDAO.findById(un).get();
					
					if(findUser != null) {
						if(pw.equals(findUser.getPassword().trim())){
							if(!findUser.getDaXoa()) {
								if(findUser.getTrangThai()){
								session.set("user", findUser);
								
								if(rm != null) {
									cookieService.add("id", un, 1);
									session.set("un", un);
									session.set("pw", pw);
								}
								else {
									cookieService.remove("id");
									
								}
									return "redirect:/kgbBookstore.com";
								}
								else {
									model.addAttribute("error", "T??i kho???n n??y ch??a k??ch ho???t");
								}
							}
						
							else {
								model.addAttribute("error", "T??i kho???n n??y ???? b??? x??a");
							}
						}
						else {
							model.addAttribute("errorPass", "Sai m???t kh???u");

						}
						
						
					}

				} catch (Exception e) {
					model.addAttribute("error", "T??i kho???n n??y kh??ng c?? trong h??? th???ng");
				}
				
			}
			
			
			PageInfo.prepareAndForward(request, PageType.SITE_LOGIN_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "home/DangNhap";
	}
	
	
	
	@RequestMapping("/kgbBookstore.com/forgot-password")
	public String quenMK(Model model) {
		// TODO Auto-generated constructor stub
		
		try {
			home.checkGioHang(model);
			PageInfo.prepareAndForward(request, PageType.SITE_FORGOT_PASSWORD_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "home/QuenMatKhau";
	}
	
	@PostMapping("/kgbBookstore.com/forgot-password")
	public String quenMKP(Model model) throws MessagingException {
		// TODO Auto-generated constructor stub
		
		try {
			home.checkGioHang(model);
			String username = request.getParameter("id");
			String email = request.getParameter("email");
			
				try {
					NguoiDung findUser = nguoiDungDAO.findById(username).get();
					
					if(findUser != null) {
						if(email.equals(findUser.getEmail().trim())){
							mailer.send(email, 
									"[KGB BOOKSTORE] [No-reply] M???t kh???u c???a b???n"
									
									, "????y l?? m???t kh???u c???a b???n: "+ findUser.getPassword());
							
							model.addAttribute("PassID", "???? g???i m???t kh???u trong email");

						}
						else {
							model.addAttribute("errorEmail", "Sai Email");

						}
						
						
					}
				} catch (Exception e) {
					model.addAttribute("errorId", "T??i kho???n n??y kh??ng c?? trong h??? th???ng");

				}
				
			PageInfo.prepareAndForward(request, PageType.SITE_FORGOT_PASSWORD_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "home/QuenMatKhau";
	}
	
	
	@RequestMapping("/kgbBookstore.com/customer/change-password")
	public String doiMK(Model model) {
		// TODO Auto-generated constructor stub
		
		try {
			home.checkGioHang(model);
			PageInfo.prepareAndForward(request, PageType.SITE_CHANGE_PASSWORD_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "home/DoiMatKhau";
	}
	
	@PostMapping("/kgbBookstore.com/customer/change-password")
	public String doiMKP(Model model, @RequestParam("repass") String repass, 
			@RequestParam("newpass") String newpass, 
			@RequestParam("oldpass") String oldpass) {
		// TODO Auto-generated constructor stub
		
		try {
			NguoiDung user = session.get("user");
			home.checkGioHang(model);
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			
			if(oldpass.length()<5 ) {
				model.addAttribute("errorOldpass", "Vui l??ng nh???p tr??n 5 ch??? s???");
				return "home/DoiMatKhau";
			}
			if(newpass.length()<5 ) {
				model.addAttribute("errorNewpass", "Vui l??ng nh???p tr??n 5 ch??? s???");
				return "home/DoiMatKhau";
			}
			if(oldpass.equals(user.getPassword().trim())) {
				if(newpass.equals(repass)) {
					user.setPassword(newpass);
					mailer.send(user.getEmail(), 
							"[KGB BOOKSTORE] [No-reply] M???t kh???u c???a b???n ???? ???????c thay ?????i! ", 
							"M???t kh???u c???a b???n ???? ???????c thay ?????i v??o l??c " + formatter.format(calendar.getTime()));
				}
				else {
					model.addAttribute("error", "Sai x??c nh???n m???t kh???u");
					return "home/DoiMatKhau";
				}
			}else {
				model.addAttribute("errorOldpass", "Sai m???t kh???u");
				return "home/DoiMatKhau";
			}
			
			
			PageInfo.prepareAndForward(request, PageType.SITE_CHANGE_PASSWORD_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "home/DoiMatKhau";
	}
	
	@RequestMapping("/kgbBookstore.com/log-out")
	public String logout(Model model) {
		
		
		session.remove("user");
		
		home.checkGioHang(model);
		
		
		return "redirect:/kgbBookstore.com";

	}
	//dang ky
	
	@RequestMapping("/kgbBookstore.com/sign-up")
	public String dangKyF( Model model) {
		// TODO Auto-generated constructor stub
		
		try {
			NguoiDung user = new NguoiDung();
			model.addAttribute("dkUser", user);
			
			
			PageInfo.prepareAndForward(request, PageType.SITE_SIGNUP_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "home/DangKy";
	}
	
	
	@PostMapping("/kgbBookstore.com/sign-up")
	public String dangKy(@Validated @ModelAttribute("dkUser") Optional<NguoiDung> newUser, 
						BindingResult errors, Model model) {
		// TODO Auto-generated constructor stub
		
		try {
			home.checkGioHang(model);
			
			NguoiDung user = newUser.get();
			
			user.setSDT("0000000000");
			user.setDiaChi("0");
			user.setCMND("0000000000");
			user.setChucVu(3);
			user.setTrangThai(false);
			user.setDaXoa(false);
			if(errors.hasErrors()) {
				model.addAttribute("error", "Vui l??ng s???a c??c l???i sau: ");
			}
			else {
				
				Boolean trungUser = nguoiDungDAO.existsById(user.getId());
				if(trungUser ==true) {
					model.addAttribute("error", "Tr??ng t??n ????ng nh???p");
				}
				else {
					model.addAttribute("message", "Ch??c m???ng b???n ???? ????ng k?? th??nh c??ng, Vui l??ng ki???m tra mail ????? k??ch ho???t t??i kho???n");
					
					nguoiDungDAO.saveAndFlush(user);
					
					 String makichhoat = mailer.layma();
					String to = user.getEmail();
					String subject = "[KGB Bookstore] Th?? ch??c m???ng!";
					String url = request.getRequestURL().toString().replace("sign-up", "kich-hoat");
					String body = "Ch??c m???ng b???n ???? ????ng k?? th??nh c??ng! Mong b???n s??? c?? nh???ng tr???i nghi???m tuy???t v???i t???i website c???a ch??ng t??i!!"+ "<br>"
							+ "M?? k??ch ho???t: "+ makichhoat + "<br> "	
							+ "Vui l??ng nh???n v??o <a href='" + url
							+ "'>Activate</a> ????? nh???p m?? k??ch ho???t t??i kho???n.";
					
					mailer.send(to, 
							subject, 
							body);
					this.makichhoat2 = makichhoat;
					this.getUser= user;
					session.set("makichhoat", makichhoat);
					session.set("newUser", user);
					return "redirect:/kgbBookstore.com";
				}
				
			}
			
			PageInfo.prepareAndForward(request, PageType.SITE_SIGNUP_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "home/DangKy";
	}
	///kich hoat tai khoan
	@RequestMapping("/kgbBookstore.com/kich-hoat")
	public String kichHoat( Model model) {
		// TODO Auto-generated constructor stub
		
		try {
			
			
			
			PageInfo.prepareAndForward(request, PageType.VALIDATION_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "home/kichhoat";
	}
	
	@PostMapping("/kgbBookstore.com/kich-hoat")
	public String dokichHoat( Model model, @RequestParam("maKichHoat") String maKichHoat) {
		// TODO Auto-generated constructor stub
		
		try {
			//NguoiDung newUser =session.get("newUser");
			//String makichhoat = session.get("makichhoat");
			try {
				NguoiDung user = nguoiDungDAO.findById(getUser.getId()).get();
				if(user !=null) {
					if(maKichHoat.equals(makichhoat2)) {
						
						user.setTrangThai(true);
						model.addAttribute("message", "T??i kho???n ???? ???????c k??ch ho???t");
						nguoiDungDAO.saveAndFlush(user);
					}
					else {
						model.addAttribute("error", "Nh???p sai m?? k??ch ho???t");
					}
				}
			} catch (Exception e) {
				model.addAttribute("error", "L???i h??? th???ng");
			}
			
			
			PageInfo.prepareAndForward(request, PageType.VALIDATION_PAGE);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "home/kichhoat";
	}
	
	@ModelAttribute("loaiSachs")
	public List<LoaiSach> getlSach(){
		
		return loaiSachDAO.findAll(); 
		
		
	}
}
