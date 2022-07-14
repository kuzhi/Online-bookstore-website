<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri = "http://java.sun.com/jstl/core_rt" prefix ="c"%>
	<%@taglib uri = "http://java.sun.com/jstl/fmt_rt" prefix ="fmt"%>
			<c:url var="url" value ="/kgbBookstore.com/admin"/>
					<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
			
	      
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${page.title}</title>

   <!-- CSS -->

	 <link rel="stylesheet" href="/css/base.css"/>
 	<link rel="stylesheet" href="/css/admin.css"/>
   <link rel="stylesheet" href="/fontawesome/css/all.min.css"/>
 	
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    
    <!-- Javascript -->
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
   
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    
    
    <style>
        .selector-for-some-widget {
            box-sizing: content-box;
        }
    </style>
</head>
       
<body >
	
		<div class="loader_bg">
    		<div class="loader"></div>
		</div>

    <div id="wrapper" class="container-fluid" >
    	<tiles:insertAttribute name="header_admin" />
    	
         
		  
		
        <!-- header wrapper -->
        <div class="row">
            	<tiles:insertAttribute name="menu_admin" />
        
        	
        </div>
        <div id="main" class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
           
            	<tiles:insertAttribute name="body_admin" />
               
        </div>
        <!-- #main -->
        <!--footer-->
        	
        
        <!-- .footer-wrapper -->
    </div>
    <!-- #wrapper -->

    
    <!-- Bootstrap -->
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

      <script src="https://cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js" integrity="sha384-uO3SXW5IuS1ZpFPKugNNWqTZRRglnUJK6UAZ/gxOX80nxEkN9NcGZTftn6RzhGWE" crossorigin="anonymous"></script><script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js" integrity="sha384-zNy6FEbO50N+Cg5wap8IKA4M/ZnLJgzc6w2NqACZaK0u0FXfOWRRJOnQtpZun8ha" crossorigin="anonymous"></script>
    <!--AnguScript cac file-->
   <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script type="text/javascript">
    function previewFile() {
  	  const preview = document.querySelector('#image');
  	  const file = document.querySelector('input[type=file]').files[0];
  	  const reader = new FileReader();

  	  reader.addEventListener("load", function () {
  	    // convert image file to base64 string
  	    preview.src = reader.result;
  	  }, false);

  	  if (file) {
  	    reader.readAsDataURL(file);
  	  }
  	}    
    
        setTimeout(function(){
            $('.loader_bg').fadeToggle();
        }, 1500);
    </script>
</body>
</html>