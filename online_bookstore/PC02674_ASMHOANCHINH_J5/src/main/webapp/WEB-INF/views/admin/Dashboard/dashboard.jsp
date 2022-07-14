<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
 <%@taglib uri = "http://java.sun.com/jstl/core_rt" prefix ="c"%>
	<%@taglib uri = "http://java.sun.com/jstl/fmt_rt" prefix ="fmt"%>      


<div class="row mx-5 my-5">

	<div class="col-3 alert alert-success  me-5" role="alert">
		  <h4 class="alert-heading text-center">Tổng người dùng</h4>
		  <hr>
		  <h1 class="text-center">${countUsers}</h1>
	</div>
	<div class="col-3 alert alert-primary me-5" role="alert">
		  <h4 class="alert-heading text-center">Tổng số sách hiện có</h4>
		  <hr>
		  <h1 class="text-center">${countBook}</h1>
	</div>
	<div class="col-3 alert alert-info" role="alert">
		  <h4 class="alert-heading">Tổng đơn hàng đang chờ</h4>
		  <hr>
		  <h1 class="text-center">${countDonHang}</h1>
	</div>
	
</div>