<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri = "http://java.sun.com/jstl/core_rt" prefix ="c"%>
	<%@taglib uri = "http://java.sun.com/jstl/fmt_rt" prefix ="fmt"%>       
    		<c:url var="url" value ="/kgbBookstore.com"/>
    
<nav  class="navbar  navbar-expand-lg bg-light">
			            
			                <!-- Logo -->
			                <a class="navbar-brand"  href="${url}">
			                    <img src="/img/logo.png" width="60px"  height="50px" alt="">
			                </a>
			                <button class="navbar-toggler " type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
			                    <span class="navbar-toggler-icon"></span>
			                </button>
			                <!-- Menu -->
			                <div class="collapse navbar-collapse" id="navbarNavDropdown">
			                    <ul class="navbar-nav me-auto" style="margin-left: 12px;">
			                      
			                        <li class="nav-item dropdown">
			                            <a id="list__item" class="menu-item nav-link dropdown-toggle" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
			                            Sách
			                            </a>
			                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
			                            	<c:forEach var="u" items="${loaiSachs}">
								            <li><a id="list__item" class="dropdown-item" href="${url}/The-loai-sach/${u.id}">${u.tenLoai}</a></li>
								           
								            </c:forEach>
								          </ul>
			                        </li>
			                        <form class="d-flex" action="${url}/search" method="post">
			        						<input name="searchBook"  class="form-control me-2" style="width:1000px" type="text" placeholder="Tìm kiếm theo tên sách" required>
			        						<button class="btn btn-outline-success" type="submit">Search</button>
			      					</form>
			      					<li class="nav-item dropdown mx-3">
			                            <a id="list__item" class="menu-item nav-link " href="${url}/customer/cart" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
			                            	<i class="fa fa-shopping-cart"></i>
			                            
			                           
			                            
			                            <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
										      <c:if test="${not (empty sessionScope.carts)}">
										     	${count}
										     	</c:if>	
										     	<c:if test="${count == null}">
										     	0
										     	</c:if>
										    <span class="visually-hidden">unread messages</span>
										  </span>
										
										  
			                            </a>
			                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
								          <div class="card " style="width:500px">
												  <div class="card-header">
												   	<i class="fa fa-shopping-cart"></i>
												    Giỏ hàng
												  </div>
												  <c:if test="${not (empty sessionScope.user)}">
												  <c:forEach var="cart" items="${carts}">

												  <div class="card-body">
												  	<div class="col">
												         <img  src="/img/${cart.sach.images}" style="width: 200px; height:200px" class="card-img-top anh" alt="">
												     </div>
												     <div class="col">
												     	<p class="fs-5">${cart.sach.tenSach}</p>
												     	<p class="fs-5">
													     	<c:if test="${cart.sach.discount ==0}">
															<fmt:setLocale value="vi_VN"/>
									      					<fmt:formatNumber type="currency" value="${cart.sach.gia}"/>
																	
															</c:if>
														
															<c:if test="${cart.sach.discount >0}">
																<fmt:setLocale value="vi_VN"/>
									      						<fmt:formatNumber type="currency" value="${cart.sach.gia * cart.sach.discount}"/>
																	
															</c:if>
												     	
												     	 x ${cart.soLuong}</p>
												     	
												     </div>
												         <hr class="dropdown-divider">
												         
                            							
												  </div>
												  </c:forEach>
												  <div class="card-footer">
												  		<div class="float-start">
												  		<h1>Tổng cộng</h1>
												  		<h1 class="text-danger">
															<span class="price"><fmt:setLocale value="vi_VN"/>
					      									<fmt:formatNumber type="currency" value="${totalPrice}"/></span>
														</h1>
												  		
												  		</div>
												  		<div>
															<a class="ms-3 btn btn-outline-danger" href="${url}/customer/cart" style="width:200px">
															XEM GIỎ HÀNG
															</a>
														</div>
												  		
												  </div>
												  </c:if>
											</div>
								          </ul>
			                        </li>
			                      	
			                        
			                           			          		                      			                        
			                    </ul>
			                    	<ul class="navbar-nav me-auto" style="margin-left: 12px;">
			                    
										<!-- Chưa đăng nhập -->
			                      	<c:choose>
					
										<c:when test="${empty sessionScope.user}">
			                            <li class="nav-item dropdown">
			                                <a class="nav-link dropdown-toggle" href="#"
			                                    style="color: black; font-weight: 600 !important; font-size: 1.1rem;"
			                                    id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown"
			                                    aria-expanded="false">
			                                    Tài khoản 
			                                </a>
			                                <ul class="dropdown-menu list-item-user" style="margin-top: -10px;"
			                                    aria-labelledby="navbarDropdownMenuLink">
			                                    <li><a class="dropdown-item" href="${url}/login" >Đăng nhập</a></li>
			                                    <li><a class="dropdown-item" href="${url}/sign-up" >Đăng ký</a></li>
			                                    <li><a class="dropdown-item" href="${url}/forgot-password">Quên mật khẩu</a>
			                                    </li>
			                                </ul>
			                            </li>
			                       		 </c:when>
                        				
                        				
                        				<c:when test="${sessionScope.user.chucVu == 1 || sessionScope.user.chucVu == 2}">
				                       		<li class="nav-item dropdown">
				                                <a class="nav-link dropdown-toggle" href="#"
				                                    style="color: black; font-weight: 600 !important; font-size: 1.1rem;"
				                                    id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown"
				                                    aria-expanded="false">
				                                     Xin Chào Tài khoản ${user.id}
				                                </a>
				                                <ul class="dropdown-menu list-item-user" style="margin-top: -10px;"
				                                    aria-labelledby="navbarDropdownMenuLink">
				                                    <li><a class="dropdown-item" href="${url}/customer/change-password" >Đổi mật khẩu</a></li>
				                                    <li><a class="dropdown-item" href="${url}/customer/account" >Hồ sơ cá nhân</a></li>
				                                    <li><a class="dropdown-item" href="${url}/customer/favorite">Yêu thích</a></li>
								                    <li><a class="dropdown-item" href="${url}/customer/cart">Giỏ hàng</a></li>
				                                    <li><a class="dropdown-item" href="${url}/admin/index">Quản lí admin</a></li>
				                                     <li><a class="dropdown-item" href="${url}/customer/history">Lịch sử và chi tiết mua hàng</a></li>
				                                    <li><hr class="dropdown-divider"></li>
				                                    <li><a class="dropdown-item" href="${url}/log-out">Đăng xuất</a></li>
				                                </ul>
				                            </li>
			                         	</c:when>
			                         	
										<c:when test="${sessionScope.user.chucVu == 3}">
				                       		<li class="nav-item dropdown">
				                                <a class="nav-link dropdown-toggle" href="#"
				                                    style="color: black; font-weight: 600 !important; font-size: 1.1rem;"
				                                    id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown"
				                                    aria-expanded="false">
				                                     Xin Chào Tài khoản ${user.id}
				                                </a>
				                                <ul class="dropdown-menu list-item-user" style="margin-top: -10px;"
				                                    aria-labelledby="navbarDropdownMenuLink">
				                                    <li><a class="dropdown-item" href="${url}/customer/change-password" >Đổi mật khẩu</a></li>
				                                    <li><a class="dropdown-item" href="${url}/customer/account" >Hồ sơ cá nhân</a></li>
				                                    <li><a class="dropdown-item" href="${url}/customer/favorite">Yêu thích</a></li>
				                                    <li><a class="dropdown-item" href="${url}/customer/cart">Giỏ hàng</a></li>
				                                     <li><a class="dropdown-item" href="${url}/customer/history">Lịch sử và chi tiết mua hàng</a></li>
				                                    <li><hr class="dropdown-divider"></li>
				                                    <li><a class="dropdown-item" href="${url}/log-out">Đăng xuất</a></li>
				                                </ul>
				                            </li>
			                         	</c:when>
		                        
		                        
		                        </c:choose>	
			                            </ul>
			                </div>

			        </nav>