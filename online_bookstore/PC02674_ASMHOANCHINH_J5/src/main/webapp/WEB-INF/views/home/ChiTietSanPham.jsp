<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jstl/core_rt" prefix ="c"%>
	<%@taglib uri = "http://java.sun.com/jstl/fmt_rt" prefix ="fmt"%>      

<c:url var ="url" value="/kgbBookstore.com" />
    <div class="row border border-1 bg-light p-3">
        <div class="col-md-4 col-lg-6 ">
            <div class="col p-1 border-bottom">
            <div class="row">
            	
            	 
            	
            		<img src="/img/${sachs.images}" style="width: 400px;">
            	</div>
            	
            </div>
            	
                
            </div>
			
      
        <div class="col-md-7 col-lg-6 p-2">
            <p class="fs-3 fw-bold">${sachs.tenSach}</p>

            <p class="fs-3"> <b>Nhà xuất bản: </b>${sachs.nxb.tenNXB}</p>
            <p class="fs-3"> <b>Nhà cung cấp: </b>${sachs.ncc.ten}</p>
			<p class="fs-3"> <b>Tác giả: </b>${sachs.tacGia.ten}</p>
			<p class="fs-3" ><b>Giá: </b> 
					<span class="text-danger"><c:if test="${sachs.discount ==0}">
						<fmt:setLocale value="vi_VN"/>
					  <fmt:formatNumber type="currency" value="${sachs.gia}"/>
													
						</c:if>
														
						<c:if test="${sachs.discount >0}">
							<fmt:setLocale value="vi_VN"/>
					      	<fmt:formatNumber type="currency" value="${sachs.gia * sachs.discount}"/>
													
							</c:if>
					</span> 
			<span class="ms-3 fs-3 text-dark text-decoration-line-through" > 
					<c:if test="${sachs.discount >0}">
							<fmt:setLocale value="vi_VN"/>
					      	<fmt:formatNumber type="currency" value="${sachs.gia}"/>
													
							</c:if>
			</span></p>
            
            <br>
           <div>
             <label class="fs-3" ><b>Số lượng: </b></label>
            
            <form action="${url}/detail-product/add/${sachs.id}" method="post">
				<input class="form-control" name="soluong2" type="number" onblur="this.form.submit()"
				 style="width: 80px;" min=0 >
			</form>
			
           </div>
            
          
			<div class="card-footer bg-light d-flex justify-content-center ">
	            
	            <div class="col">
				  <c:choose>
					<c:when test="${empty sessionScope.user}">
	                    <a href="${url}/login"  class="ms-5 btn btn-outline-dark btn-lg d-block " style="width: 300px;"  >
	                		<i class="fa fa-shopping-cart me-2"></i>
	                		THÊM VÀO GIỎ HÀNG
	           		 	</a>
           		 	</c:when> 
                    <c:otherwise>
	                    <a  href="${url}/detail-product/add/${sachs.id}" class="ms-5 btn btn-outline-dark btn-lg d-block " style="width: 300px;"  >
	                		<i class="fa fa-shopping-cart me-2"></i>
	                		THÊM VÀO GIỎ HÀNG
	           		 	</a>
                    </c:otherwise>
                </c:choose>  
 
              
				</div>
	    
	            <c:if test="${empty sessionScope.user}">
                    <a class="btn btn-outline-dark btn-lg d-block me-5" style="background-color: plum; color: white; width: 30%;"   role="button" href="${url}/login" > 
                    <i class="fas fa-heart"></i>
                    </a>
                    
                    </c:if>
                    <c:if test="${not (empty sessionScope.user)}">
                    <a class="btn btn-outline-dark btn-lg d-block me-5" style="background-color: plum; color: white; width: 30%;"   role="button" href="${url}/customer/favorite/add/${sachs.id}" > 
                    <i class="fas fa-heart"></i>
                    </a>
                    
                    </c:if>
	           
            </div>
                
        </div>
  </div>
  
  <div class="row align-center mt-2">
  		<h1 class="text-center text-primary fw-bold">MÔ TẢ </h1>
  		<p class="fs-3">${sachs.mota}</p>
  </div>