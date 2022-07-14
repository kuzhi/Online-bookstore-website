<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri = "http://java.sun.com/jstl/core_rt" prefix ="c"%>
	<%@taglib uri = "http://java.sun.com/jstl/fmt_rt" prefix ="fmt"%>      
  
       <c:url var = "url" value = "/kgbBookstore.com/forgot-password" />
    
  
    <div class="container">
        
            <div class="row g-0 bg-light ">
                <div class="col-lg-5 ps-0">
                     <img src="/img/background_formdn.jpg" alt="logo" class="img-fluid" style = "width: 700px; height: 400px;" >
                </div>
                
                <div class="col-lg-7 px-5 pt-5">

                            <h1 class="fs-2  fw-bold  text-center font ">
                                QUÊN MẬT KHẨU
                            </h1>
                            <h5 class="text-center font_2" >
                                Lấy lại mật khẩu của bạn tại đây
                            </h5>
                    <form method="post" action="${url}" >
                    
                                <div class=" offset-1 col-lg-10" >
                                    <div class="input-group ">
                                    
                                    <span class="input-group-text" >
                                        
                                        <i class="fas fa-envelope" style="width:16px; height:16px;"></i>
                                    </span>
                                    
                                    <div class="form-floating col-10">

                                        <input type="email"  name="email"  class="form-control "  placeholder="Nhập địa chỉ email của bạn" required>
                                        <label for="email">Nhập địa chỉ email của bạn</label>

                                    </div>
                                     <c:if test="${not (empty errorEmail)}">
                                    <small  id="email" class="form-text text-danger">${errorEmail}</small>
									</c:if>
                                </div>
                                
                           

                                <div class="col-lg-10">
                                    <div class="input-group mt-3">
                                        <span class="input-group-text " >
                                            <i class="fas fa-barcode " style="width:16px; height:16px;"> </i>
                                                            
                                        </span>
                                        
                                        <div class="form-floating col-10">

                                            <input type="text" name="id"  class="form-control " placeholder="Nhập tên đăng nhập của bạn của bạn" style="width: 300px;" required>
                                            <label for="text">Tên đăng nhập</label>
    
                                            </div>
                                           <c:if test="${not (empty errorId)}">
                                    		<small  id="id" class="form-text text-danger">${errorId}</small>
											</c:if>

                                        </div>
                                    </div>
                                </div>

                                <div class="offset-1 col-10">
                                    
                                    <div class="offset-3 col-lg-5 mt-5">
                                        <button   class="btn btn-primary btn-1 align-center px-3 fs-5">
                                            Lấy lại
                                        </button>
                                    </div>
                                     <c:if test="${not (empty errordnID)}">
                                			<h1 class="mt-2 text-center form-text text-success">${errordnID}</h1>
    								</c:if>
                                </div>
                     		</form>
 
                            </div> 
                    
                 </div>
     
            </div>
        
      
    </div>


    


