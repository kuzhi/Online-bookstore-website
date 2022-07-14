package pc02674.asmhoanchinh.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo {
		

	private String title;
	
	public static Map<PageType, PageInfo> pageRoute = new HashMap<PageType, PageInfo>();
	static {
		//ADMIN
		pageRoute.put(PageType.ADMIN_PAGE, new PageInfo("Quản Trị viên"));
		
		
		//NGUOI DUNG VA TRANG CHINH
		pageRoute.put(PageType.SITE_HOME_PAGE, new PageInfo("Trang Chủ"));
		pageRoute.put(PageType.SITE_LOGIN_PAGE, new PageInfo("Đăng nhập"));
		pageRoute.put(PageType.SITE_SIGNUP_PAGE, new PageInfo("Đăng ký"));
		pageRoute.put(PageType.SITE_CHANGE_PASSWORD_PAGE, new PageInfo("Thay đổi mật khẩu"));
		pageRoute.put(PageType.SITE_FORGOT_PASSWORD_PAGE, new PageInfo("Quên mật khẩu"));
		pageRoute.put(PageType.SITE_EDIT_PROFILE_PAGE, new PageInfo("Thông tin tài khoản"));
		pageRoute.put(PageType.SITE_FAVORITE_PAGE, new PageInfo("Yêu thích"));		
		pageRoute.put(PageType.SITE_HISTORY_BUY, new PageInfo("Lịch sử mua hàng"));		
		pageRoute.put(PageType.SITE_ORDER, new PageInfo("Đơn hàng"));		
		pageRoute.put(PageType.SITE_CART_PAGE, new PageInfo("Giỏ hàng"));		
		pageRoute.put(PageType.SITE_FAVORITE_PAGE, new PageInfo("Sách yêu thích"));		
		pageRoute.put(PageType.SITE_PRODUCT_DETAIL_PAGE, new PageInfo("Chi tiết sản phẩm"));		
		pageRoute.put(PageType.TEST_PAGE, new PageInfo("TEST"));		
		pageRoute.put(PageType.USERF_ADMIN_PAGE, new PageInfo("QUẢN LÝ NGƯỜI DÙNG"));
		pageRoute.put(PageType.USERT_ADMIN_PAGE, new PageInfo("QUẢN LÝ NGƯỜI DÙNG"));
		pageRoute.put(PageType.BOOKF_ADMIN_PAGE, new PageInfo("QUẢN LÝ SÁCH"));
		pageRoute.put(PageType.BOOKT_ADMIN_PAGE, new PageInfo("QUẢN LÝ SÁCH"));
		pageRoute.put(PageType.TYPEBOOKF_ADMIN_PAGE, new PageInfo("QUẢN LÝ LOẠI SÁCH"));	
		pageRoute.put(PageType.TYPEBOOKT_ADMIN_PAGE, new PageInfo("QUẢN LÝ LOẠI SÁCH"));	
		pageRoute.put(PageType.NCCF_ADMIN_PAGE, new PageInfo("QUẢN LÝ NHÀ CUNG CẤP"));	
		pageRoute.put(PageType.NCCT_ADMIN_PAGE, new PageInfo("QUẢN LÝ NHÀ CUNG CẤP"));	
		pageRoute.put(PageType.NXBF_ADMIN_PAGE, new PageInfo("QUẢN LÝ NHÀ XUẤT BẢN"));	
		pageRoute.put(PageType.NXBT_ADMIN_PAGE, new PageInfo("QUẢN LÝ NHÀ XUẤT BẢN"));	
		pageRoute.put(PageType.TACGIAF_ADMIN_PAGE, new PageInfo("QUẢN LÝ TÁC GIẢ"));	
		pageRoute.put(PageType.TACGIAT_ADMIN_PAGE, new PageInfo("QUẢN LÝ TÁC GIẢ"));	
		pageRoute.put(PageType.CUSTOMERS_ADMIN_PAGE, new PageInfo("QUẢN LÝ KHÁCH HÀNG"));	
		pageRoute.put(PageType.ORDER_ADMIN_PAGE, new PageInfo("QUẢN LÝ ĐƠN HÀNG"));	
		pageRoute.put(PageType.ORDER_DELETED_ADMIN_PAGE, new PageInfo("QUẢN LÝ ĐƠN HÀNG"));	
		pageRoute.put(PageType.ORDER_FORM_ADMIN_PAGE, new PageInfo("QUẢN LÝ ĐƠN HÀNG"));	
		pageRoute.put(PageType.REPORT_CATEGORY_PAGE, new PageInfo("Báo cáo - Thống kê theo loại hàng"));	
		pageRoute.put(PageType.REPORT_MONTH_PAGE, new PageInfo("Báo cáo - Thống kê theo tháng"));	
		pageRoute.put(PageType.VALIDATION_PAGE, new PageInfo("Kích hoạt tài khoản"));	

	}
	
public static void prepareAndForward(HttpServletRequest request,PageType pagetype) throws ServletException, IOException {
	PageInfo page=pageRoute.get(pagetype);
	
	request.setAttribute("page", page);
}






}
