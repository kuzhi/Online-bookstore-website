<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@taglib uri = "http://java.sun.com/jstl/core_rt" prefix ="c"%>
	<%@taglib uri = "http://java.sun.com/jstl/fmt_rt" prefix ="fmt"%>
 <header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
		  <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3 fs-6" href="/kgbBookstore.com"><img src="/img/logo.png" width="60px"  height="50px" class="d-inline-block align-top me-2" alt=""> KGBBookstore</a>
		    
			<div class="navbar-nav">
					   
					     	<ul class="nav">
			                <li class="nav-item dropdown">
			                    	<a id="user" class="nav-link dropdown-toggle" id="navbarDropdownMenuLink" role="button"
			                             data-bs-toggle="dropdown" aria-expanded="false">
			                        
			                            Xin chào ${sessionScope.user.id}
			                         </a>
			                     <ul class="dropdown-menu list-item-user" style="margin-top: -10px;"
			                                        aria-labelledby="navbarDropdownMenuLink">
			                                            
			                        <li><a class="dropdown-item" href="/kgbBookstore.com/admin/logout">Đăng xuất</a></li>
			                     </ul>
			                 </li>
			                 </ul>
			              
					    
					  </div>
	</header>				  