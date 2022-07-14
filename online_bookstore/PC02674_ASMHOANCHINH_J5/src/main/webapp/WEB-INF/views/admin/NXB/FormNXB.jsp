<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri = "http://www.springframework.org/tags/form" prefix = "form" %>  
  <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
	<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>

 			<c:url var="url" value="/kgbBookstore.com/admin/nha-xuat-ban/form" />

 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="row">
				<c:if test="${not empty message}">
					<div class="alert alert-success">${message}</div>
				</c:if>
				<c:if test="${not empty error}">
				
				<div class="alert alert-danger">${error}</div>
				</c:if>
			</div>
	<form:form class="row" action="${url}" method="post" modelAttribute="nxb">
		  <div class="row">
		    <div class=" form-group">
		      <form:label path="id">Id</form:label>
		      <form:input path="id"  class="form-control" readonly="true" />
		     		       
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <form:label path="tenNXB" >Tên loại</form:label>
		      <form:input path="tenNXB" type="text" class="form-control" placeholder="Tên nhà xuất bản"/>
		      <form:errors class="text-danger" path="tenNXB" element="li" delimiter=";"/>
			</div>
		  
  			<div class="mt-5">
			<button class="btn btn-outline-primary me-5" <c:if test="${nxb.id >0}">disabled</c:if> style="width:200px;height: 100px;" formaction="${url}/create">Create</button>
			<button class="btn btn-outline-success me-5 "  <c:if test="${nxb.id <1}">disabled</c:if> style="width:200px;height: 100px;" formaction="${url}/update">Update</button>
				<a class="btn btn-outline-warning me-5 justify-content-center text-center" style="width:200px;height: 100px;" href="${url}">Reset</a>
			</div>
	</form:form>
</body>
</html>