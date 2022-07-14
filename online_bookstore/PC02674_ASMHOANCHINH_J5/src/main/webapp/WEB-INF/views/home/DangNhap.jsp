<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jstl/core_rt" prefix ="c"%>
	<%@taglib uri = "http://java.sun.com/jstl/fmt_rt" prefix ="fmt"%>      
    			<c:url var="url" value="/kgbBookstore.com/login" />
   
   
    
    
    
    <div class="container">
        
            <div class="row  rowdn g-0 bg-light ">
                <div class="col-lg-5 ps-0">
                     <img src="/img/background_formdn.jpg" alt="logo" class="img-fluid " style = "width: 700px; height: 400px;">
                </div>
                
                <div class="col-lg-7 px-5 pt-5">
                	<c:if test="${not empty message}">
						<div class="alert alert-success">${message}</div>
					</c:if>
					<c:if test="${not empty error}">
					
					<div class="alert alert-danger">${error}</div>
					</c:if>
					<c:if test="${not empty sessionScope.errorAuth}">
                	<div class="alert alert-danger">${sessionScope.errorAuth}</div>
                	</c:if>
                    <form action="${url}" method="post" >

                            <h1 class="fs-2  fw-bold my-3 text-center">
                                ĐĂNG NHẬP
                            </h1>
                            <h5 class="fs-5 text-center"></h5>
                            
                                <div class=" offset-1 col-lg-10" >
                                    <div class="input-group ">
                                    
                                    <span class="input-group-text" >
                                            <i class="fas fa-user" style="width:16px; height:16px;"> </i>
                                                            
                                        
                                    </span>
                                    <div class="form-floating col-lg-10">
                                        <input type="text"  name="id" value="${uid}" class="form-control "  placeholder="Tên đăng nhập" required>
                                    <label >Tên đăng nhập</label>
                                </div>
                                
                                <c:if test="${not (empty errorID)}">
                                <small  id="username" class="form-text text-danger">${errorID}</small>
    							</c:if>
                                    </div>
                                </div>
                            

                                <div class="offset-1 col-lg-10">
                                    <div class="input-group mt-3">
                                        <span class="input-group-text " >
                                            <i class="fas fa-lock" style="width:16px; height:16px;"> </i>
                                                            
                                        </span>
                                        <div class="form-floating col-lg-10">

                                        <input type="password"  name="password" id="pass" value="${upass}"  class="form-control " placeholder="Mật khẩu" required>
                                        <label for="password">Mật khẩu</label>

                                        </div>
                                    </div>
                                   <c:if test="${not (empty errorPass)}">
                                    <small  id="pass" class="form-text text-danger">${errorPass}</small>
									</c:if>
                                </div>
							
							<div class="form-check offset-1 mt-2 col-lg-10">
									  <input class="form-check-input" type="checkbox"  id="flexCheckDefault" name="remember" >
									  <label class="form-check-label" for="flexCheckDefault">
									    Remember me?
									  </label>
									</div>
                                
							
                            <div class="form-row ">

                                <div class="mt-2 offset-2 col-lg-7">
                                    <button class="btn btn-primary btn-1 align-center px-3 fs-5">
                                        Đăng nhập
                                    </button>
                                </div>
                            </div>
                            
                            
                    
                    </form>
                 </div>
     
            </div>
        
      
    </div>


    


    


    