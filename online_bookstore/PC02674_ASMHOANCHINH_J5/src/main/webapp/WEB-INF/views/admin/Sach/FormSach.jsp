<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
        <%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>

 			<c:url var="url" value="/kgbBookstore.com/admin/sach/form" />
	
	<div class="row">
				<c:if test="${not empty message}">
					<div class="alert alert-success">${message}</div>
				</c:if>
				<c:if test="${not empty error}">
				
				<div class="alert alert-danger">${error}</div>
				</c:if>
			</div>



	<form:form class="row mt-3" action="${url}" method="post" modelAttribute="sachs" enctype="multipart/form-data">
		<div class="col-4 ">
                   <img src="/img/${sachs.images}" id="image" width="500" height="500">
        </div>
        
        <div class="col-8">    
		  <div class="row">
		    <div class="col-md-6">
		      <form:label path="id">ID</form:label>
		      <form:input path="id" type = "text" class="form-control" readonly="true"/>		      
		    </div>
		    <div class="col-md-6">
		      <form:label path="loaiSach">Loại sách</form:label>
				<form:select class="form-select" path="loaiSach.id" >
					<c:forEach var="lSach" items="${loaiSachs}">
						<form:option value="${lSach.id}"  >${lSach.tenLoai}</form:option>
					</c:forEach>		  
				</form:select>			    
			</div>
		  </div>
		  
		  <div class="row">
		    <div class="col-md-6">
		      <form:label path="nxb">Nhà xuất bản</form:label>
				<form:select class="form-select" path="nxb.id" >
					<c:forEach var="nxb" items="${nxbs}">
						<form:option value="${nxb.id}"  >${nxb.tenNXB}</form:option>
					</c:forEach>		  
				</form:select>			    
			</div>
		    <div class="col-md-6">
		      <form:label path="ncc">Nhà cung cấp</form:label>
				<form:select class="form-select" path="ncc.id" >
					<c:forEach var="ncc" items="${nccs}">
						<form:option value="${ncc.id}"  >${ncc.ten}</form:option>
					</c:forEach>		  
				</form:select>			    
			</div>
		  </div>
		  
		  <div class="form-group">
		   <form:label path="tacGia">Tác giả</form:label>
				<form:select class="form-select" path="tacGia.id" >
					<c:forEach var="tg" items="${tgs}">
						<form:option value="${tg.id}"  >${tg.ten}</form:option>
					</c:forEach>		  
				</form:select>	
		  </div>
		  
		  <div class="form-group">
				<form:label path="tenSach">Tên sách:</form:label>
		      <form:input path="tenSach" class="form-control" required="required"/>
		      	
		  </div>
		  
		  <div class="row">
		    <div class="col-md-6">
		      <form:label path="soLuong">Số lượng:</form:label>
		      <form:input path="soLuong" class="form-control" type="number" min="1" step="1"/>
		      		      
		    </div>
		    <div class="col-md-6">
		      <form:label path="gia">Giá:</form:label>
		      <form:input path="gia" class="form-control" type="number" min="5000" step="100"/>
		      		      
			</div>
		  </div>
		  <div class="row">
		  		<label >Hình ảnh:</label>
		    	<input name=img type="file" onchange="previewFile()" >
		    	
		    	
		  </div>
		  <div class="form-group">
		    
		      <form:label path="discount">Discount:</form:label>
		      <form:input path="discount" class="form-control" type="number" min="0" max="0.99" step="0.01"/>
		    
		  </div>
		  <div class="form-group">
		    
		      <form:label path="mota">Mô tả:</form:label>
		      <form:textarea path="mota" class="form-control" required="required"/>
		    	
		  </div>
		  
		  <div class="row">
		    <div class="col-md-6">
		      <form:label path="ngayTao">Ngày Tạo</form:label>
		      <form:input path="ngayTao" type="date" readonly="${readonlyC}" class="form-control"  placeholder="Ngày Tạo" required="required"/>
		    </div>
		    <div class="col-md-6">
		       <form:label path="ngaySua">Ngày Sửa</form:label>
		      <form:input path="ngaySua" type="date" readonly="${readonlyU}" class="form-control" placeholder="Ngày Sửa" required="required"/>
			</div>
		  </div>
		  </div>
		  
		  <div class="mt-5">
			<button class="btn btn-outline-primary me-5" <c:if test="${sachs.id != null}">disabled</c:if> style="width:200px;height: 100px;" formaction="${url}/create">Create</button>
			<button class="btn btn-outline-success me-5 "  <c:if test="${sachs.id ==null}">disabled</c:if> style="width:200px;height: 100px;" formaction="${url}/update">Update</button>
				<a class="btn btn-outline-warning me-5 justify-content-center text-center" style="width:200px;height: 100px;" href="${url}">Reset</a>
			</div>
			
			 
	</form:form>
