<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri = "http://java.sun.com/jstl/core_rt" prefix ="c"%>
	<%@taglib uri = "http://java.sun.com/jstl/fmt_rt" prefix ="fmt"%>       
		<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
		
		<c:url var="url" value ="/kgbBookstore.com"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${page.title} - Nhà sách KGB</title>
<!-- css-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
 <link rel="stylesheet" href="/css/base.css"/>
 <link rel="stylesheet" href="/css/index.css"/>
 <link rel="stylesheet" href="/css/product.css"/>
  <link rel="stylesheet" href="/css/DangKy.css"/>
  <link rel="stylesheet" href="/css/DangNhap.css"/>
  <link rel="stylesheet" href="/css/DoiMatKhau.css"/>
  <link rel="stylesheet" href="/css/QuenMatKhau.css"/>
   <link rel="stylesheet" href="/css/cart.css"/>
 
  <link rel="stylesheet" href="/fontawesome/css/all.min.css"/>
 
 <!--  -->
 <!-- js -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</head>
<body>
<div class="loader_bg">
    	<div class="loader"></div>
	</div>

    <div  class="container-fluid" >
    
        <header id="header" class="header ">
           <div class="header-wrapper">
			    <div class="row">
			    	<tiles:insertAttribute name="menu" />
			    
			        
			        
			    </div>
					    <!--<div class="row header-banner">
					        <div id="carouselExampleControls" class="mb-2 carousel slide auto" data-bs-ride="carousel" width="800">
					            <div class="carousel-indicators">
					                <button type="button" data-bs-target="#carouselExampleControls" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
					                <button type="button" data-bs-target="#carouselExampleControls" data-bs-slide-to="1" aria-label="Slide 2"></button>
					                <button type="button" data-bs-target="#carouselExampleControls" data-bs-slide-to="2" aria-label="Slide 3"></button>
					              </div>
					            <div class="carousel-inner">
					              <div class="carousel-item active">
					                <div class="card bg-dark text-white text-center">
					                    <img src="/img/event1.jpg" class="card-img d-block" alt="movie"  height="800px">
					                    
					                  </div>
					              </div>
					              <div class="carousel-item">
					                <div class="card bg-dark text-white text-center">
					                    <img src="/img/event2.png" class="card-img d-block  " alt="movie"   height="800px">
					                    
					                  </div>
					              </div>
					              <div class="carousel-item">
					                <div class="card bg-dark text-white text-center">
					                    <img src="/img/event3.jpg" class="card-img d-block " alt="anime" height="800px">
					                    
					                  </div>
					              </div>
					            </div>
					            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
					              <span class="carousel-control-prev-icon" aria-hidden="true"></span>
					              <span class="visually-hidden">Previous</span>
					            </button>
					            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
					              <span class="carousel-control-next-icon" aria-hidden="true"></span>
					              <span class="visually-hidden">Next</span>
					            </button>
					          </div>  
					    </div>-->
					    <!-- banner -->
					</div>
        </header>
        <!-- header wrapper -->
        <div id="main" class="container-fluid">
            <div class="row">
                <!-- full thì 9/12 - ipad thì 9/12 - dt thì 12/12 -->
                <div class="col-12" >
	            	<tiles:insertAttribute name="body" />
                </div>
               
            </div>
        </div>
        <!-- #main -->
        <!--footer-->
        	<tiles:insertAttribute name="footer" />
        	
             
          
        <!-- .footer-wrapper -->
    </div>
    <!-- #wrapper -->

    <!-- Bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    
    
    <!--AnguScript cac file-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script>
        setTimeout(function(){
            $('.loader_bg').fadeToggle();
        }, 1500);
    </script>
</body>
</html>