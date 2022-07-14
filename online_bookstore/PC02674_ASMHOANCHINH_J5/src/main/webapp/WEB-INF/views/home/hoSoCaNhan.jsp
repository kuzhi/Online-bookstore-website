<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri = "http://java.sun.com/jstl/core_rt" prefix ="c"%>
	<%@taglib uri = "http://java.sun.com/jstl/fmt_rt" prefix ="fmt"%>      
   <c:url var = "url" value = "/kgbBookstore.com" />
  <%@ taglib uri = "http://www.springframework.org/tags/form" prefix = "form" %>  

    
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

    <div class="container-fluid">
    	
        <div class="row justify-content-center">
            <div class="col">
            
                    <div class="col-12 d-flex">
                        <div class="col-2 title-header">
                            <div class="form-label fs-6 fw-bold">Hồ sơ cá nhân</div>
                        </div>
                        <div class="col-10 title-body"></div>
                    </div>
                    <div class="col-12">
                        <!-- Button trigger modal -->
                        

                        
                    <div class="col-md-6">
                        <div  class="form-label fw-bold text-dark">Họ và tên</div>
		      		<div class="form-control" >${sessionScope.user.ho} ${sessionScope.user.ten}</div>
                        <!-- <div class="valid-feedback">
                            Looks good!
                        </div> -->
                    </div>
                    <div class="col-md-6">
                        <label for="validationCustom02" class="form-label fw-bold text-dark">Tên đăng nhập</label>
                        <div class="form-control" >${sessionScope.user.id} </div>

                    </div>
                    <div class="col-md-6">
                        <label for="validationCustomUsername" class="form-label fw-bold text-dark">Email</label>
                        <div class="form-control" >${sessionScope.user.email} </div>

                    </div>
                     <div class="col-12">
                        <label for="validationCustom03" class="form-label fw-bold text-dark">Ngày sinh</label>
                        <input type="date" class="form-control" id="validationCustom03" name="birthDay" value="${sessionScope.user.ngaySinh}" required>
                   
                    </div>
                    
                    <div class="col-12">
                        <label for="validationCustom05" class="form-label fw-bold text-dark">Giới tính: </label>
                        <div class="form-control" >
                        <c:if test="${sessionScope.user.gioitinh == true}">
                        	Nam
                        </c:if>
                        <c:if test="${sessionScope.user.gioitinh == false}">
                        	Nữ
                        </c:if>
                        
                        </div>
                                                                                                 

                    </div>
                   
               
            </div>
        </div>
    </div>
    </div>



