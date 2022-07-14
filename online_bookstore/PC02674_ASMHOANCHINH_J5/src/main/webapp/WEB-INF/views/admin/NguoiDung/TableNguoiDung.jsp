<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<c:url var="url" value="/kgbBookstore.com/admin/user/table" />
<div class="table-responsive  mt-2 ">
	<table class="table table-light table-striped ">
		<tr class="table-primary">
			<th>Tên đăng nhập</th>
			<th>Họ</th>
			<th>Tên</th>
			<th>Email</th>
			<th>Ngày sinh</th>
			<th>Giới tính</th>
			<th>Chức vụ</th>
			<th>CMND</th>
			<th>SDT</th>
			<th>Địa chỉ</th>
			<th>Trạng thái</th>
			<th></th>
			<th></th>
		</tr>
		<c:forEach var="u" items="${page1.content}">
			<c:if test="${not u.daXoa}">
				<tr>
					<td>${u.id}</td>
					<td>${u.ho}</td>
					<td>${u.ten}</td>
					<td>${u.email}</td>
					<td>${u.ngaySinh}</td>
					<c:choose>
						<c:when test="${u.gioitinh}">
							<td>Nam</td>
						</c:when>
						<c:when test="${not u.gioitinh}">
							<td>Nữ</td>
						</c:when>
					</c:choose>

					<c:choose>
						<c:when test="${u.chucVu == 1}">
							<td>Admin</td>
						</c:when>
						<c:when test="${u.chucVu == 2}">
							<td>Nhân viên</td>
						</c:when>
						<c:when test="${u.chucVu == 3}">
							<td>Người dùng</td>
						</c:when>
					</c:choose>
					<td>${u.CMND}</td>
					<td>${u.SDT}</td>
					<td>${u.diaChi}</td>

					<c:choose>
						<c:when test="${u.trangThai}">
							<td>Đã kích hoạt</td>
						</c:when>
						<c:otherwise>
							<td>Chưa kích hoạt</td>
						</c:otherwise>
					</c:choose>

					<td><a href="${url}/edit/${u.id}" class="nav-link">Edit</a></td>
					<td><a href="${url}/delete/${u.id}" class="nav-link">Xóa</a></td>

				</tr>
			</c:if>
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








