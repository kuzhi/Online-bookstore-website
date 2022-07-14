<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>



<c:url var="url" value="/kgbBookstore.com/admin/report-by-category" />


<div class="table-responsive  mt-2 ">
	<table class="table table-light table-striped ">
		<tr class="table-primary">
			<th>Loại hàng</th>
			<th>Doanh thu</th>
			<th>Số lượng bán</th>
			<th></th>
			<th></th>
		</tr>
		<c:forEach var="u" items="${page1.content}">
			<tr>

				<td>${u.loaiSachs.tenLoai}</td>
				<td>
					<fmt:formatNumber value="${u.sum}" pattern="###,###,###,###" />
					VND
				</td>
				<td>${u.count}</td>
			</tr>
		</c:forEach>
	</table>
</div>
<div class="row bg-light">



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








