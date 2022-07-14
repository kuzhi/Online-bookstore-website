<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri = "http://www.springframework.org/tags/form" prefix = "form" %>  
  <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
	<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>

 			<c:url var="url" value="/kgbBookstore.com/admin/don-hang/form" />


	<div class="row mt-2">
				<c:if test="${not empty message}">
					<div class="alert alert-success">${message}</div>
				</c:if>
				<c:if test="${not empty error}">
				
				<div class="alert alert-danger">${error}</div>
				</c:if>
			</div>
	<form:form class="row mt-2" action="${url}" method="post" modelAttribute="donhang">
		  <div class="row">
		    <div class=" col-md-6">
		      <form:label path="id">ID</form:label>
		      <form:input path="id" type="text" class="form-control"  readonly="true" />
		      <form:errors class="text-danger" path="id" element="li" delimiter=";"/>
		      
		    </div>
		    <div class="col-md-6">
		      <form:label path="khachhang.id">Tên khách hàng </form:label>
			<form:select class="form-select" path="khachhang.id" >
				<form:option value="${donhang.khachhang.id}">${donhang.khachhang.ten}</form:option>		  
			</form:select>			      
		    </div>
		  </div>
		  <div class="form-group">
		    <form:label path="ngay" >Ngày đặt</form:label>
		      <form:input path="ngay" type="date" class="form-control"/>
		      <form:errors class="text-danger" path="ngay" element="li" delimiter=";"/>
			</div>
		  
		  <div class="form-group">
		    <form:label path="nguoiDung.id" >Người dùng</form:label>
		      <form:select class="form-select" path="nguoiDung.id"  >
				<form:option value="${donhang.nguoiDung.id}" ></form:option>		  
			</form:select>		
			</div>
			
		  <div class="form-group">
		  <form:label path="trangThai" class="me-2" >Trạng thái đơn hàng: </form:label>
		    <div class="form-check form-check-inline">
			  <form:radiobutton class="form-check-input " path="trangThai"  disabled="${done}"  value = "false" />
			  <form:label class="form-check-label" path="trangThai" >Đang chờ</form:label>
			  
			</div>
			 <div class="form-check form-check-inline">
			  <form:radiobutton class="form-check-input" path="trangThai"   value = "true" />
			  <form:label class="form-check-label" path="trangThai" >Hoàn thành</form:label>
			  
			</div>
			</div>
			
			<div class="form-group">
		    <form:label path="tongTien" >Tổng tiền</form:label>
		    <fmt:setLocale value="vi_VN"/> 
		      <form:input path="tongTien"  type="hidden" class="form-control" readonly="true" />
		      <div class="form-control" readonly><fmt:formatNumber value="${tongTien}" type="currency" /> </div>
			</div>
			
			
			
  			<div class="mt-5">
			<button class="btn btn-outline-success me-5 "  <c:if test="${id==null}">disabled</c:if> style="width:200px;height: 100px;" formaction="${url}/update">Update</button>
			<a class="btn btn-outline-warning me-5 justify-content-center text-center" style="width:200px;height: 100px;" href="${url}">Reset</a>
			<a class="btn btn-outline-danger me-5 justify-content-center text-center <c:if test="${id==null || donhang.trangThai==true}">disabled</c:if>" style="width:200px;height: 100px;" href="/kgbBookstore.com/admin/don-hang/danh-sach-cho/delete/${id}">Xóa</a>
			
			</div>
	</form:form>
</body>
</html>