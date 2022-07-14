<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri = "http://java.sun.com/jstl/core_rt" prefix ="c"%>
	<%@taglib uri = "http://java.sun.com/jstl/fmt_rt" prefix ="fmt"%>      
   <c:url var = "url" value = "/kgbBookstore.com/customer/history" />

    
    <style>
        .title-header {
            border-width: 2px;
            border-style: solid;
            border-bottom: 0;
            border-top-left-radius: 3px;
            border-top-right-radius: 3px;
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 21px;
        }

        .title-body {
            border-width: 2px;
            border-style: solid;
            border-top: 0;
            border-left: 0;
            border-right: 0;
        }
    </style>

    <div class="container-fluid mt-3">
    	<div class="justify-content-center text-center">
    		<p class="fs-1 fw-bold">Lịch sử mua hàng</p>
    	</div>
        <div class="row justify-content-center">
            <table class="table table-striped">
			  	<thead>
			    <tr>
			      <th scope="col">Mã đơn hàng</th>
			      <th scope="col">Ngày</th>
			      <th scope="col">Trạng thái</th>
			      <th scope="col">Tổng giá</th>
			      <th scope="col"></th>
			    </tr>
			  </thead>
			  <tbody>
			  <c:forEach var ="u" items="${page1.content}">
			    <tr>
			      
					      <td>${u.id}</td>
					      <td>${u.ngay}</td>
					     
					     
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
			    </tr>
			    </c:forEach>
			  </tbody>						
			</table>
        </div>
        
       
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



