<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@taglib uri = "http://java.sun.com/jstl/core_rt" prefix ="c"%>
	<%@taglib uri = "http://java.sun.com/jstl/fmt_rt" prefix ="fmt"%>      
      <%@ taglib uri = "http://www.springframework.org/tags/form" prefix = "form" %>  
     <c:url var="url" value="/kgbBookstore.com/customer/checkout" />
      <div class="row">
     
        
			<c:if test="${not empty message}">
							<div class="alert alert-success">${message}</div>
						</c:if>
						<c:if test="${not empty error}">
						
						<div class="alert alert-danger">${error}</div>
						</c:if>
					
							
                            <h1 class="text-center text-primary fs-3">
							ĐIỀN THÔNG TIN KHÁCH HÀNG
							</h1>
                            <h5 class="fs-5 text-center"></h5>
                           <form:form  method="post" modelAttribute="customers">
                           
                               <div class="form-group">

                                    <form:label path="ten">Họ và tên</form:label>
                                    
                                    <form:input path="ten" type="text" class="form-control" placeholder="Họ và tên" />
                                     
                                </div>
                                   
              						<div>
                                    <form:errors class="text-danger" path="ten" element="li" delimiter=";"/>
                                    </div>
                            	
	                            <div class="form-group">
	                                    <form:label path="diaChi">Địa chỉ</form:label>
	                                    <form:input  path="diaChi"  class="form-control" placeholder="Địa chỉ" />	                                      
	                                    </div> 
	                                
	                            		<div>
	                                        <form:errors class="text-danger" path="diaChi" element="li" delimiter=";"/>
	                                    </div>
	                            <div class="form-group">
                                    <form:label path="SDT">Số điện thoại</form:label>
	                                <form:input  path="SDT"  class="form-control" placeholder="Số điện thoại" />
                                   
                                </div>
                                	<div>
									      <form:errors class="text-danger" path="SDT" element="li" delimiter=";"/>
									</div>
                                 <div class="form-group">
                                    <form:label path="city">Tỉnh/Thành phố</form:label>
	                                <form:input  path="city"  class="form-control" placeholder="Tỉnh/Thành phố" />
                                   
                                </div>
                            			<div>
								      <form:errors class="text-danger" path="city" element="li" delimiter=";"/>
						               </div> 
                                             
                                <div class="mt-5">
									<button class="btn btn-outline-primary me-5"  style="width:200px;height: 100px;">Xác nhận thanh toán </button>
									<a class="btn btn-outline-warning me-5 justify-content-center text-center" style="width:200px;height: 100px;" href="/kgbBookstore.com/customer/checkout">Reset</a>
								</div>
                                </form:form>

    
</div>

