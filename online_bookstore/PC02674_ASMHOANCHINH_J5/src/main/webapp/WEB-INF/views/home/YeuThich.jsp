<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri = "http://java.sun.com/jstl/core_rt" prefix ="c"%>
	<%@taglib uri = "http://java.sun.com/jstl/fmt_rt" prefix ="fmt"%>    
	  
<c:url var = "url" value = "/kgbBookstore.com/customer/favorite" />
<!-- Bootstrap -->
<div class="container-fluid">
    
    <div class="row row-cols-auto justify-content-md-center" style="margin-top: 15px;">
        <c:forEach var = "u" items="${page1.content}">
        <div id="box">
            <div class="card shadow-sm text-center my-2">
            
                <div class="card-body">
                	
                    <div id="image" >
                        <a  href="/kgbBookstore.com/detail-product/${u.sach.id}">
                           
                        
                            <img src="/img/${u.sach.images}"  class="card-img-top anh" alt="">
                           
                        </a>
                    </div>
                   
                        <h5 class="card-title text-center mt-4 text-primary " >${u.sach.tenSach}</h5>
                        <h5 class="card-title text-center text-danger" >Giá: <fmt:setLocale value="vi_VN"/>
					      								<fmt:formatNumber type="currency" value="${u.sach.gia}"/></h5>
					    <c:if test="${u.sach.discount >0}"><h5 class="card-title text-center text-danger" >Giá: <fmt:setLocale value="vi_VN"/>
					      								<fmt:formatNumber type="currency" value="${u.sach.gia * u.discount}"/></h5></c:if> 
                      								
					      								
					      								
                        <c:if test="${u.sach.discount >0}"><h5 class="card-title text-center text-decoration-line-through" >Giá: <fmt:setLocale value="vi_VN"/>
					      								<fmt:formatNumber type="currency" value="${u.sach.gia}"/></h5></c:if> 
                    
                </div>
                <div class="card-footer bg-light  ">
                <a class="btn btn-outline-dark btn-lg d-block me-5" style="background-color: plum; color: white; width: 30%;"   role="button" href="${url}/remove/${u.id}">
                   <i class="fas fa-thumbs-down"></i></a>  
              
             
                </div>
				<c:if test="${u.sach.discount >0}">
	                <div class="banner position-absolute ">
	                <span>Giảm giá</span>
	                    
	                </div>
                </c:if> 
            </div>
            
        </div>
      </c:forEach>
     <!-- Button -->
     <div class="row text-center float-none" style="margin-top: 20px;">
		  <div class="row  float-none" style="margin-top: 10px;">
		  				<ul class="pagination justify-content-center">
				<li class="page-item ${page1.number == 0?'disabled':''}"><a
					class="page-link" href="${url}/page?p=0">First</a></li>
				<li class="page-item ${page1.number == 0?'disabled':''}"><a
					class="page-link" href="${url}/page?p=${page1.number-1}">Previous</a></li>
				<li
					class="page-item ${page1.number+1 == page1.totalPages ?'disabled':''}"><a
					class="page-link" href="${url}/page?p=${page1.number+1}">Next</a></li>
				<li class="page-item ${page1.number+1 == page1.totalPages?'disabled':''}"><a
					class="page-link" href="${url}/page?p=${page1.totalPages-1}">Last</a></li>
			</ul>
    				</div>
    </div>
</div>
</div>    