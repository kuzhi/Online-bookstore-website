<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://www.springframework.org/tags/form" prefix = "form" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>


 
 			<c:url var="url" value="/kgbBookstore.com/admin/sach/table" />
 

                <div class="table-responsive  mt-2 ">
                <table class="table table-light table-striped ">
                    <tr class="table-primary">
                        <th>ID</th>
                        <th>Loại sách</th>
                        <th>Nhà xuất bản</th>
                        <th>Nhà cung cấp</th>
                        <th>Tác giả</th>
                        <th>Tên sách</th>
                        <th>Số lượng</th>
                        <th>Giá</th>
                        <th>Discount</th>
                        <th>Ngày Tạo</th>
                        <th>Ngày Sửa</th>
                        
                       <th></th>
                        <th></th>
                    </tr>
                    <c:forEach var ="u" items="${page1.content}">
                    <c:if test="${u.daXoa == false}">
					    <tr>
								    
					      <td>${u.id}</td>
					      <td>${u.loaiSach.tenLoai}</td>
					      <td>${u.nxb.tenNXB}</td>
					      <td>${u.ncc.ten}</td>
					      <td>${u.tacGia.ten}</td>
					      <td>${u.tenSach}</td>
					      <td>${u.soLuong}</td>
					      <td> <fmt:setLocale value="vi_VN"/>
					      <fmt:formatNumber type="currency" value="${u.gia}" />
					      <td>${u.discount}</td>
					      <td>${u.ngayTao}</td>
					      <td>${u.ngaySua}</td>
					      
					      <td><a href="${url}/edit/${u.id}" class="nav-link" >Edit</a></td>
					   	  <td><a href="${url}/delete/${u.id}" class="nav-link">Xóa</a></td>
					      
					    </tr>
					    </c:if>
			   		</c:forEach>
                </table> 
            </div>
            <div class="row bg-light">

					<div class="row" style="margin-top: 10px;">
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
            
   


   



