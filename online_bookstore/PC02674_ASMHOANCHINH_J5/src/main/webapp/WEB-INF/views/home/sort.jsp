<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
                  

<%@taglib uri = "http://java.sun.com/jstl/core_rt" prefix ="c"%>
	<%@taglib uri = "http://java.sun.com/jstl/fmt_rt" prefix ="fmt"%>    
	  
<!-- Bootstrap -->
<div class="container-fluid">
	<div class="row mt-2">
	<div class="col-md-6">
		     
	<button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
    		Sắp xếp theo tên
  	</button>
			     <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
					<li><a id="list__item" class="dropdown-item" href="/kgbBookstore.com/sort/A-Z">Từ A-Z</a></li>
					<li><a id="list__item" class="dropdown-item" href="/kgbBookstore.com/sort/Z-A">Từ Z-A</a></li>
								           
			</ul>
			   		    
			</div>
	</div>
    <c:if test="${not empty messageP}">
							<div class="alert alert-success">${messageP}</div>
						</c:if>
						<c:if test="${not empty errorP}">
						
						<div class="alert alert-danger">${errorP}</div>
						</c:if>
        
    <div class="row row-cols-auto justify-content-md-center" style="margin-top: 15px;">
 						
        <c:forEach var = "u" items="${page1.content}">
        <div id="box">
            <div class="card shadow-sm text-center my-2">
            
                <div class="card-body">
                	
                    <div id="image" >
                        <a  href="${url}/detail-product/${u.id}">
                           
                        
                            <img src="/img/${u.images}"  class="card-img-top anh" alt="">
                           
                        </a>
                    </div>
                   
                        <h5 class="card-title text-center mt-4 text-primary " >${u.tenSach}</h5>
                        
                        <h5 class="card-title text-center text-danger" >Giá:
                        <c:if test="${u.discount ==0}"> 
                        
                        <fmt:setLocale value="vi_VN"/>
					    <fmt:formatNumber type="currency" value="${u.gia}"/>
					   
					    </c:if>
					    <c:if test="${u.discount >0}"><fmt:setLocale value="vi_VN"/>
					      								<fmt:formatNumber type="currency" value="${u.gia * u.discount}"/></c:if> 
                      								
					      								
					      								
                        <c:if test="${u.discount >0}"><span class="card-title text-center text-decoration-line-through text-dark" ><fmt:setLocale value="vi_VN"/>
					      								<fmt:formatNumber type="currency" value="${u.gia}"/></span></c:if> 
                     </h5>
                </div>
                <div class="card-footer bg-light  ">
                <c:choose>
					<c:when test="${empty sessionScope.user}">
	                    <a href="${url}/login"  class="ms-5 btn btn-outline-dark btn-lg d-block " style="width: 300px;"  >
	                		<i class="fa fa-shopping-cart me-2"></i>
	                		THÊM VÀO GIỎ HÀNG
	           		 	</a>
           		 	</c:when> 
                    <c:otherwise>
	                    <a href="${url}/customer/cart/add/${u.id}"  class="ms-5 btn btn-outline-dark btn-lg d-block " style="width: 300px;"  >
	                		<i class="fa fa-shopping-cart me-2"></i>
	                		THÊM VÀO GIỎ HÀNG
	           		 	</a>
                    </c:otherwise>
                </c:choose>  
              
             
                </div>
				<c:if test="${u.discount >0}">
	                <div class="banner position-absolute ">
	                <span>Giảm giá</span>
	                    
	                </div>
                </c:if> 
            </div>
            
        </div>
      </c:forEach>

    </div>
     
</div>    