<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://www.springframework.org/tags/form" prefix = "form" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>


 
 			<c:url var="url" value="/kgbBookstore.com/admin/don-hang/danh-sach-cho" />
 

                <div class="table-responsive  mt-2 ">
                <table class="table table-light table-striped ">
                    <tr class="table-primary">
                        <th>ID</th>
                        <th>Tên khách hàng</th>
                        <th>Ngày đặt</th>
                        <th>Username</th>
                        <th>Trạng thái</th>
                        <th>Tổng tiền</th>
                       <th></th>
                        <th></th>
                    </tr>
                    <c:forEach var ="u" items="${page1.content}">
                   <c:if test="${u.daXoa == false}">
					    <tr>
								    
					      <td>${u.id}</td>
					      <td>${u.khachhang.ten}</td>
					      <td>${u.ngay}</td>
					      <td>${u.nguoiDung.id}</td>
					     
					     
					      <c:choose>
					      	<c:when test="${u.trangThai==true}">
					      		<td>Hoàn thành</td>
					      	</c:when>
					      	<c:when test="${u.trangThai==false}">
					      		<td>Đang chờ</td>
					      	</c:when>	
					      </c:choose>
					       <td> <fmt:setLocale value="vi_VN"/>
					      <fmt:formatNumber type="currency" value="${u.tongTien}" />
					       </td>
					      <td><a href="${url}/edit/${u.id}" class="nav-link " >Edit</a></td>
					   	  <td><a href="${url}/delete/${u.id}" class="nav-link ">Xóa</a></td>
					      
					    </tr>
					   </c:if>
			   		</c:forEach>
                </table> 
            </div>
            <div class="row bg-light">
            	
            	
            	<div class="col-lg-5">
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
            
   


   



