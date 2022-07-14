<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@taglib uri = "http://java.sun.com/jstl/core_rt" prefix ="c"%>
	<%@taglib uri = "http://java.sun.com/jstl/fmt_rt" prefix ="fmt"%>      
      <%@ taglib uri = "http://www.springframework.org/tags/form" prefix = "form" %>  
     <c:url var="url" value="/kgbBookstore.com/sign-up" />
     
        
 <div class="row g-0 bg-light py-5">
                <!-- Hình -->
                <div class="col-lg-5 ps-0">
                     <img src="/img/background_formdk.jpg" alt="logo" class="img-fluid" style = "width: 700px; height: 400px;">
                </div>
                <!-- ////-->
                <div class="col-lg-7 px-5 pt-2">
                    <div class="row">
                    
						<c:if test="${not empty message}">
							<div class="alert alert-success">${message}</div>
						</c:if>
						<c:if test="${not empty error}">
						
						<div class="alert alert-danger">${error}</div>
						</c:if>
					</div>
							
                            <h1 class="fs-2  fw-bold my-1 text-center">
                                ĐĂNG KÝ
                            </h1>
                            <h5 class="fs-5 text-center"></h5>
                           <form:form  method="post" modelAttribute="dkUser">
                           
                                <div class=" offset-1 col-lg-10" >
                                    <div class="input-group ">
                                    
                                    <span class="input-group-text" >
                                        
                                        <i class="fas fa-user" style="width:16px; height:16px;"> </i>
                                    </span>
                                    
                                    <form:input path="id" type="text" class="form-control" placeholder="Tên đăng nhập" />
                                     
                                    </div>
                                   
                                </div>
                            		<div>
                                    <form:errors class="text-danger" path="id" element="li" delimiter=";"/>
                                    </div>
                            	
	                            <div class="offset-1 col-lg-10">
	                                    <div class="input-group mt-3">
	                                        <span class="input-group-text " >
	                                            <i class="fas fa-lock" style="width:16px; height:16px;"> </i>
	                                                            
	                                        </span>
	                                        <form:password path="password"  class="form-control" placeholder="Mật khẩu" />
	                                        
	                                        
	
	                                    </div>
	                                  
	                                </div>
	                            		<div>
	                                        <form:errors class="text-danger" path="password" element="li" delimiter=";"/>
	                                    </div>
	                            <div class="offset-1 col-lg-10">
                                    <div class="input-group mt-3">
                                        <span class="input-group-text " >
	                                            <i class="fas fa-user" style="width:16px; height:16px;"> </i>
	                                                            
	                                        </span>
									      <form:input path="ho" type="text" class="form-control"  placeholder="Họ" />
									      
                                    </div>
                                   
                                </div>
                                	<div>
									      <form:errors class="text-danger" path="ho" element="li" delimiter=";"/>
									      </div>
                                 <div class="offset-1 col-lg-10">
                                    <div class="input-group mt-3">
                                        <span class="input-group-text " >
	                                            <i class="fas fa-user" style="width:16px; height:16px;"> </i>
	                                                            
	                                        </span>
								      <form:input path="ten" type="text" class="form-control"  placeholder="Tên" />
								                               
                                    </div>
                                   
                                </div>
                            			<div>
								      <form:errors class="text-danger" path="ten" element="li" delimiter=";"/>
						               </div> 
                                <div class="offset-1 col-lg-10">
                                    <div class="input-group mt-3">
                                        <span class="input-group-text " >
                                            <i class="fas fa-envelope" style="width:16px; height:16px;"> </i>
                                                            
                                        </span>
                                        <form:input type="email"  path="email"  class="form-control " placeholder="VD: khoa@gmail.com"   />
                                        
                                        
                                    </div>
                                    
                                </div>
                           				<div>
                                        <form:errors class="text-danger" path="email" element="li" delimiter=";"/>
                                        </div>
                            	<div class="offset-1 col-lg-10 mt-2">
                                    <label class="label-form">
                                        Giới tính:
                                    </label>
                                    
                                    <form:radiobutton path="gioitinh"   value = "true"  class="ms-3"  />
                                    <form:label path="gioitinh" class="label-form me-5">
                                        Nam
                                    </form:label>

                                        <form:radiobutton path="gioitinh"   value = "false"   />
                                    <form:label  path="gioitinh" class="label-form" >
   											Nữ
                                    </form:label>
                                    <div>
                                  
                                    </div>

                                </div>
                            	<div class="offset-1 col-lg-10 mt-2">
									    <div class="form-check form-check-inline">
										  <form:hidden class="form-check-input" path="chucVu"  value="3"/>
										 <form:hidden path="CMND"  class="form-control"   value="0000000000" />
										 <form:hidden path="SDT"  class="form-control"   value="0000000000"/>
										 <form:hidden path="diaChi"  class="form-control"   value="0" />
										</div>
 								</div>
										   
                                <div class="offset-1 col-lg-10">
                                    <div class="input-group mt-3">
                                        <span class="input-group-text " >
                                            <i class="fas fa-calendar-alt" style="width:16px; height:16px;"> </i>
                                                            
                                        </span>
                                        <form:input type="date" path="ngaySinh" placeholder="VD: 1995-12-02"  class="form-control "   />
                                        
                                    </div>
                                   
                                </div> 
                                 		<div>
                                       <form:errors class="text-danger" path="ngaySinh" element="li" delimiter=";"/>
                                        </div>               
                                <div class="offset-3 mt-4 col-lg-7 mt-3">
                                    <button  class="btn btn-primary btn-1 align-center px-3 fs-5" >
                                        Đăng ký
                                    </button>
                                    
                                    <a  class="mt-3 btn btn-primary btn-1 align-center px-3 fs-5" href="${url}" >
                                       Reset
                                    </a>
                                </div>
                                </form:form>
                            </div>
                            
                    
                    
                 </div>
     
            </div>
        
      
    


