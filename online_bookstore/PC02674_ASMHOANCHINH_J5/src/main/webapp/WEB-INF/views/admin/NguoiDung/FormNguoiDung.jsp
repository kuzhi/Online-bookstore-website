<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri = "http://www.springframework.org/tags/form" prefix = "form" %>  
  <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
	<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>

 			<c:url var="url" value="/kgbBookstore.com/admin/user/form" />

 

	<div class="row">
				<c:if test="${not empty message}">
					<div class="alert alert-success">${message}</div>
				</c:if>
				<c:if test="${not empty error}">
				
				<div class="alert alert-danger">${error}</div>
				</c:if>
			</div>
	<form:form class="row" action="${url}" method="post" modelAttribute="users">
		  <div class="row">
		    <div class=" col-md-6">
		      <form:label path="id">User name</form:label>
		      <form:input path="id" type="text" class="form-control" readonly="${readOnly}" placeholder="User name" />
		      <form:errors class="text-danger" path="id" element="li" delimiter=";"/>
		      
		    </div>
		    <div class="col-md-6">
		      <form:label path="password">Password</form:label>
		      <form:input path="password" type="password" class="form-control"  placeholder="Password" />
		      <form:errors class="text-danger" path="password" element="li" delimiter=";"/>
		      
		    </div>
		  </div>
		  <div class="row">
		    <div class="col-md-6">
		      <form:label path="ho">Họ</form:label>
		      <form:input path="ho" type="text" class="form-control"  placeholder="Họ" />
		      <form:errors class="text-danger" path="ho" element="li" delimiter=";"/>

		    </div>
		    <div class="col-md-6">
		      <form:label path="ten">Tên</form:label>
		      <form:input path="ten" type="text" class="form-control"  placeholder="Tên" />
		      <form:errors class="text-danger" path="ten" element="li" delimiter=";"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <form:label path="email" >Email</form:label>
		      <form:input path="email" type="email" class="form-control" placeholder="Email"/>
		      <form:errors class="text-danger" path="email" element="li" delimiter=";"/>
			</div>
			
		  <div class="form-group">
		  <form:label path="gioitinh" class="me-2" >Giới tính: </form:label>
		    <div class="form-check form-check-inline">
			  <form:radiobutton class="form-check-input" path="gioitinh"   value = "true" />
			  <form:label class="form-check-label" path="gioitinh" >Nam</form:label>
			  
			</div>
			<div class="form-check form-check-inline">
			  <form:radiobutton class="form-check-input"  path="gioitinh"  value = "false" />
			  <form:label  path="gioitinh" class="form-check-label">Nữ</form:label>
			</div>
			<form:errors class="text-danger" path="gioitinh" element="li" delimiter=";"/>
		  </div>
		  <div class="form-group">
		    <form:label path="ngaySinh" >Ngày Sinh</form:label>
		      <form:input path="ngaySinh" type="date" class="form-control" placeholder="Ngày Sinh"/>
		      <form:errors class="text-danger" path="ngaySinh" element="li" delimiter=";"/>
			</div>
		   <div class="form-group">
		   
		   	<form:label path="chucVu" class="me-2" >Chức vụ: </form:label>
		   	
		    <div class="form-check form-check-inline">
			  <form:radiobutton class="form-check-input" path="chucVu"   value = "1" />
			  <form:label class="form-check-label" path="chucVu" >Admin</form:label>
			</div>
			<div class="form-check form-check-inline">
			  <form:radiobutton class="form-check-input"  path="chucVu"   value = "2" />
			  <form:label  path="chucVu" class="form-check-label">Nhân viên</form:label>
			</div>
			<div class="form-check form-check-inline">
			  <form:radiobutton class="form-check-input"  path="chucVu"   value = "3" />
			  <form:label  path="chucVu" class="form-check-label">Người dùng</form:label>
			</div>
				<form:errors class="text-danger" path="chucVu" element="li" delimiter=";"/>
		  </div>
		  
		  <div class="row">
		    <div class="col-md-6">
		      <form:label path="CMND">Chứng minh nhân dân</form:label>
		      <form:input path="CMND" type="text" class="form-control"  placeholder="Chứng minh nhân dân" />
		      <form:errors class="text-danger" path="CMND" element="li" delimiter=";"/>
		    </div>
		    <div class="col-md-6">
		      <form:label path="SDT">SDT</form:label>
		      <form:input path="SDT" type="text" class="form-control"  placeholder="SDT" />
		      <form:errors class="text-danger" path="SDT" element="li" delimiter=";"/>
		    </div>
		  </div>
		  	<div class="col-12">
			    <form:label path="diaChi" class="form-label">Địa chỉ </form:label>
			    <form:input path="diaChi" type="text" class="form-control"  placeholder="1234 Main St" />
			    <form:errors class="text-danger" path="diaChi" element="li" delimiter=";"/>
  			</div>
  			<div class="form-group">
		   
		   	<form:label path="trangThai" class="me-2" >Trạng thái: </form:label>
		   	
		    <div class="form-check form-check-inline">
			  <form:radiobutton class="form-check-input" path="trangThai"   value = "True" />
			  <form:label class="form-check-label" path="trangThai" >Đã kích hoạt</form:label>
			</div>
			<div class="form-check form-check-inline">
			  <form:radiobutton class="form-check-input"  path="trangThai"   value = "False" />
			  <form:label  path="trangThai" class="form-check-label">Chưa kích hoạt</form:label>
			</div>
			
		  </div>
  			<div class="mt-5">
			<button class="btn btn-outline-primary me-5" <c:if test="${users.id != null}">disabled</c:if> style="width:200px;height: 100px;" formaction="${url}/create">Create</button>
			<button class="btn btn-outline-success me-5 "  <c:if test="${users.id ==null}">disabled</c:if> style="width:200px;height: 100px;" formaction="${url}/update">Update</button>
				<a class="btn btn-outline-warning me-5 justify-content-center text-center" style="width:200px;height: 100px;" href="${url}">Reset</a>
			</div>
	</form:form>
