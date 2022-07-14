<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jstl/core_rt" prefix ="c"%>
	<%@taglib uri = "http://java.sun.com/jstl/fmt_rt" prefix ="fmt"%>      
   <c:url var = "url" value="/kgbBookstore.com/customer"/>
    <div class="container">
        
        
                    <form  action="${url}/change-password" method="post">
                            <img src="/img/lock-solid.png" class="img-fluid offset-4 my-3"/>
                            <h1 class="fs-2  fw-bold  text-center font ">
                                ĐỔI MẬT KHẨU
                            </h1>
                            <h5 class="text-center font_2" >
                                Bạn có thể thay đổi mật khẩu của bạn tại đây
                            </h5>
                            
                                <div class=" offset-3 col-8" >
                                    <div class="input-group ">
                                    
                                    <span class="input-group-text" >
                                        
                                        <i class="fas fa-key" style="width:16px; height:16px;"></i>
                                    </span>
                                    <div class="form-floating col-8">

                                        <input type="text" name="oldpass"  class="form-control "  placeholder="Nhập mật khẩu cũ" required >
                                        <label for="password">Nhập mật khẩu cũ</label>
                                        
                                    </div>
                                    
                                    </div>
                                    
                                    <c:if test="${not (empty errorOldpass)}">
	                                <small   class="form-text text-danger">${errorOldpass}</small>
	    							</c:if> 
                                </div>
                            

                            
                                <div class="offset-3 col-8">
                                    <div class="input-group mt-3">
                                        <span class="input-group-text " >
                                            <i class="fas fa-unlock-alt " style="width:16px; height:16px;"> </i>
                                                            
                                        </span>
                                        <div class="form-floating col-8">

                                            <input type="password" name="newpass" class="form-control " placeholder="Nhập mật khẩu mới" required >
                                            <label for="password">Nhập mật khẩu mới</label>
    
                                        </div>
                                        
                                    </div>
                                        
                                 		<c:if test="${not (empty errorNewpass)}">
	                                	<small   class="form-text text-danger">${errorNewpass}</small>
	    								</c:if>
                                 </div>
                               
                            
                            
                                <div class="offset-3 col-8">
                                    <div class="input-group mt-3">
                                        <span class="input-group-text" >
                                            <i class="fas fa-unlock " style="width:16px; height:16px;"> </i>
                                                            
                                        </span>
                                        
                                        <div class="form-floating col-8">

                                            <input type="password" name="repass" class="form-control " placeholder="Xác nhận  mật khẩu mới" required >
                                            <label for="password">Xác nhận  mật khẩu</label>
    
                                        </div>
                                        
                                    </div>
                                    	<c:if test="${not (empty errorRepass)}">
	                                	<small   class="form-text text-danger">${errorRepass}</small>
	    								</c:if>
                                </div>
                           

                            <div class="form-row ">
								 <c:if test="${not (empty error)}">
										<div class="alert alert-danger">${error}</div>
	    						</c:if>
	    						<c:if test="${not empty message}">
										<div class="alert alert-success">${message}</div>
								</c:if>
                                <div class="offset-3 col-6 mt-5">
                                    <button type="submit"  class="btn btn-primary btn-1 align-center px-3 fs-5">
                                        Lưu thay đổi
                                    </button>
                                </div>
                            </div>
                           
                          
                    
                    </form>

    </div>
   

    



