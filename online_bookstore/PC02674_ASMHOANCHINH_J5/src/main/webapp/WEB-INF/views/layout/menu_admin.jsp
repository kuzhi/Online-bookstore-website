<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@taglib uri = "http://java.sun.com/jstl/core_rt" prefix ="c"%>
	<%@taglib uri = "http://java.sun.com/jstl/fmt_rt" prefix ="fmt"%>
	
    
    			<c:url var="url" value ="/kgbBookstore.com/admin"/>
    
<nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
		      <div class="position-sticky pt-3">
		        <ul class="nav flex-column">
		          
		          <li class="nav-item">
		            <a class="nav-link btn d-inline-flex align-items-center rounded border-0 collapsed" aria-current="page" href="${url}/index" >
		              <span data-feather="house" class="align-text-bottom"></span>
		              Dashboard
		            </a>

		          </li>
		          
		          <li class="nav-item">
		            <a class="nav-link btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" aria-current="page" data-bs-toggle="collapse" data-bs-target="#users-collapse" aria-expanded="false">
		              <span data-feather="users" class="align-text-bottom"></span>
		              Người dùng 
		            </a>
		            
		            <div class="collapse" id="users-collapse">
			          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
			            <li><a href="/kgbBookstore.com/admin/user/form" class="link-dark d-inline-flex text-decoration-none rounded">Thông tin người dùng</a></li>
			            <li><a href="/kgbBookstore.com/admin/user/table" class="link-dark d-inline-flex text-decoration-none rounded">Danh sách người dùng</a></li>
			           
			          </ul>
		        	</div>
		          </li>
		          
		          <li class="nav-item">
		            <a class="nav-link btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" aria-current="page" data-bs-toggle="collapse" data-bs-target="#product-type-collapse" aria-expanded="false">
		              <span data-feather="list" class="align-text-bottom"></span>
		              Loại sản phẩm
		            </a>
		            
		            <div class="collapse" id="product-type-collapse">
			          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
			            <li><a href="/kgbBookstore.com/admin/category/form" class="link-dark d-inline-flex text-decoration-none rounded">Thông tin loại sản phẩm</a></li>
			            <li><a href="/kgbBookstore.com/admin/category/table" class="link-dark d-inline-flex text-decoration-none rounded">Danh sách loại sản phẩm</a></li>
			          </ul>
		        	</div>
		          </li>
		          
		          <li class="nav-item">
		            <a class="nav-link btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" aria-current="page" data-bs-toggle="collapse" data-bs-target="#shopping-collapse" aria-expanded="false">
		              <span data-feather="shopping-cart" class="align-text-bottom"></span>
		              Sách
		            </a>
		            
		            <div class="collapse" id="shopping-collapse">
			          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
			            <li><a href="${url}/sach/form" class="link-dark d-inline-flex text-decoration-none rounded">Thông tin sách</a></li>
			            <li><a href="${url}/sach/table" class="link-dark d-inline-flex text-decoration-none rounded">Danh sách sách</a></li>
			            <!-- <li><a href="${url}/sach/table/galery" class="link-dark d-inline-flex text-decoration-none rounded">Danh sách hình ảnh sách</a></li> -->
			          </ul>
		        	</div>
		          </li>
		          
		          <li class="nav-item">
		            <a class="nav-link btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" aria-current="page" data-bs-toggle="collapse" data-bs-target="#supplier-collapse" aria-expanded="false">
		              <span data-feather="list" class="align-text-bottom"></span>
		              Nhà cung cấp
		            </a>
		            
		            <div class="collapse" id="supplier-collapse">
			          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
			            <li><a href="/kgbBookstore.com/admin/nha-cung-cap/form" class="link-dark d-inline-flex text-decoration-none rounded">Thông tin nhà cung cấp</a></li>
			            <li><a href="/kgbBookstore.com/admin/nha-cung-cap/table" class="link-dark d-inline-flex text-decoration-none rounded">Danh sách nhà cung cấp</a></li>
			          </ul>
		        	</div>
		          </li>
		          
		          <li class="nav-item">
		            <a class="nav-link btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" aria-current="page" data-bs-toggle="collapse" data-bs-target="#publishing-company-collapse" aria-expanded="false">
		              <span data-feather="file" class="align-text-bottom"></span>
		              Nhà xuất bản
		            </a>
		            
		            <div class="collapse" id="publishing-company-collapse">
			          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
			            <li><a href="/kgbBookstore.com/admin/nha-xuat-ban/form" class="link-dark d-inline-flex text-decoration-none rounded">Thông tin nhà xuất bản</a></li>
			            <li><a href="/kgbBookstore.com/admin/nha-xuat-ban/table" class="link-dark d-inline-flex text-decoration-none rounded">Danh sách nhà xuất bản</a></li>
			          </ul>
		        	</div>
		          </li>
		          
		          <li class="nav-item">
		            <a class="nav-link btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" aria-current="page" data-bs-toggle="collapse" data-bs-target="#author-collapse" aria-expanded="false">
		              <span data-feather="users" class="align-text-bottom"></span>
		              Tác giả
		            </a>
		            
		            <div class="collapse" id="author-collapse">
			          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
			            <li><a href="/kgbBookstore.com/admin/tac-gia/form" class="link-dark d-inline-flex text-decoration-none rounded">Thông tin tác giả</a></li>
			            <li><a href="/kgbBookstore.com/admin/tac-gia/table" class="link-dark d-inline-flex text-decoration-none rounded">Danh sách tác giả</a></li>
			          </ul>
		        	</div>
		          </li>
		          
		          <li class="nav-item">
		            <a class="nav-link btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" aria-current="page" data-bs-toggle="collapse" data-bs-target="#customer-collapse" aria-expanded="false">
		              <span data-feather="users" class="align-text-bottom"></span>
		              Khách hàng
		            </a>
		            
		            <div class="collapse" id="customer-collapse">
			          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
			            <li><a href="/kgbBookstore.com/admin/customer/table" class="link-dark d-inline-flex text-decoration-none rounded">Danh sách</a></li>
			          </ul>
		        	</div>
		          </li>
		          
		          <li class="nav-item">
		            <a class="nav-link btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" aria-current="page" data-bs-toggle="collapse" data-bs-target="#order-collapse" aria-expanded="false">
		              <span data-feather="package" class="align-text-bottom"></span>
		              Đơn hàng
		            </a>
		            
		            <div class="collapse" id="order-collapse">
			          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
			          	<li><a href="${url}/don-hang/form" class="link-dark d-inline-flex text-decoration-none rounded">Thông tin đơn hàng</a></li>			  
			          	<li><a href="${url}/don-hang/danh-sach-cho" class="link-dark d-inline-flex text-decoration-none rounded">Danh sách đơn hàng</a></li>
			            <li><a href="${url}/don-hang/danh-sach-huy" class="link-dark d-inline-flex text-decoration-none rounded">Đã hủy</a></li>
			          </ul>
		        	</div>
		          </li>
		           <c:if test="${sessionScope.user.chucVu == 1}">
		          <li class="nav-item" >
		            <a class="nav-link btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" aria-current="page" data-bs-toggle="collapse" data-bs-target="#reports-collapse" aria-expanded="false">
		              <span data-feather="bar-chart-2" class="align-text-bottom"></span>
		              Reports
		            </a>
		            
		            <div class="collapse" id="reports-collapse">
			          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
			            <li><a href="${url}/report-by-category" class="link-dark d-inline-flex text-decoration-none rounded">Doanh thu theo loại sách</a></li>
			          </ul>
		        	</div>
		          </li>
		          </c:if>
		          </ul>
		         </div>
		        </nav>
		        