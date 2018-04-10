<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home Page</title>
<!-- Bootstrap -->
    <link href="Template/FrontendAdmin/css/bootstrap.css" rel="stylesheet">
    <link href="Template/FrontendAdmin/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="Template/FrontendAdmin/css/style.css" rel="stylesheet"> 
    
    <!--Font-->
    <link href='Template/FrontendAdmin/font/fontDownload.css' rel='stylesheet'>
    
    <!-- SCRIPT 
    ============================================================-->  
    <script src="Template/FrontendAdmin/js/jqueryDownload.js"></script>
    <script src="Template/FrontendAdmin/js/bootstrap.min.js"></script>
    
    <style>
		#size
		{
			padding-top:20px;
			padding-bottom:10px;
		}
		#size1
		{
			padding-top:280px;
			
		}
	</style>
    
    <script type="text/javascript">
		
				function Search()
				{
					var xhttp;
					var grammarname = document.myform.grammarname.value;
					
					if (grammarname != "")
					{
						var url = "SearchController?grammarname="+grammarname;
						
						if (window.XMLHttpRequest) 
						{          
						           xhttp = new XMLHttpRequest();
						} 
						else
						{        
							xhttp = new ActiveXObject("Microsoft.XMLHTTP");
						}
						
						xhttp.onreadystatechange = function()
						{
							if(xhttp.readyState == 4)
							{
								var data = xhttp.responseText;
								document.getElementById("searchresult").innerHTML = data;
							}			
							
						}	
						xhttp.open("POST",url,true);
						xhttp.send();	
					}
					else 
					{
						document.getElementById("searchresult").innerHTML = "Quay lại trang chủ bằng cách click vào logo website";
					}
					
					
								
						
				}
				
	</script>
</head>
<body>

<!--HEADER ROW-->
  <jsp:include page="Header.jsp"/>
  <!-- /HEADER ROW -->

  <!-- begin search -->
  	  <div class="container">
  	  		<div class="row">
  	  			
  	  			<div class="span6">
			
					<div id="size">
						<form name="myform">
							<input type="text" class="form-control" placeholder="Search" style="width:500px" name="grammarname" onkeyup="Search()">							
						</form>
					</div>
			
				</div>
  	  		</div>
	  </div>
	<!-- end search --> 
	
	<div class="container" id="searchresult">


  <!--Carousel
  ==================================================-->

  <div id="myCarousel" class="carousel slide">
    <div class="carousel-inner">
      <div class="active item">
        <div class="container">
          <div class="row">
            
              <div class="span6">

                <div class="carousel-caption">
                      <h1>HƯỚNG DẪN NGHE - ĐỌC TRONG TOEIC</h1>
                      <p class="lead">Cung cấp đầy đủ kiến thức và kỹ năng </p>
                      <a class="btn btn-large btn-primary" href="#">Sign up today</a>
                </div>
              </div>
              <div class="span6"> <img src="Template/FrontendAdmin/img/slide/slide1.png" height= "350 px" width= "350px" ></div>      
          </div>
        </div>
      </div>
      <c:forEach items="${sliderBanner}" var="list">
     	 <div class="item">
        	<div class="container">
          		<div class="row">
              		<div class="span6">
                		<div class="carousel-caption">
                      		<h1>${list.slideName }</h1>
                      		<p class="lead">${list.slideContent }</p>
                      		<a class="btn btn-large btn-primary" href="#">Sign up today</a>
                		</div>
              		</div>
                	<div class="span6"> <img src="Imageupload/${list.slideImage }"></div>
          		</div>
       	 	</div>
      </div>
 	</c:forEach>
 </div>
       <!-- Carousel nav -->
      <a class="carousel-control left " href="#myCarousel" data-slide="prev"><i class="icon-chevron-left"></i></a>
      <a class="carousel-control right" href="#myCarousel" data-slide="next"><i class="icon-chevron-right"></i></a>
        <!-- /.Carousel nav -->

  </div>
  
 
    <!-- /Carousel -->



<!-- Feature 
  ==============================================-->


  <div class="row feature-box">
		      <div class="span12 cnt-title">
		       <h1>Chúng tôi cung cấp cho bạn các giao diện học và thi thân thiện với người dùng</h1> 
		        <span>(--Học thử, Làm bài tập, Thi thử--)</span>        
		      </div>
		
		      <div class="span4">
		        <img src="Template/FrontendAdmin/images/guideline.png">
		        <h2>Hướng dẫn phần từ vựng, ngữ pháp</h2>
		        <p>
		            Cung cấp các bài hướng dẫn sát với đề thi.
		        </p>
		
		        <a href="#" data-toggle="modal" data-target="#myModal">Chi tiết &rarr;</a>
		
		      </div>
		
		      <div class="span4">
		        <img src="Template/FrontendAdmin/images/exercises.png">
		        <h2>Bài tập phần nghe, đọc</h2>
		        <p>
		            Chúng tôi cung cấp các dạng bài tập có trong đề thi Toeic. 
		        </p>   
		          <a href="" data-toggle="modal" data-target="#myModal1">Chi tiết &rarr;</a>    
		      </div>
		
		      <div class="span4">
		        <img src="Template/FrontendAdmin/images/thitoeic.png">
		        <h2>Đề thi thử</h2>
		        <p>
		             Chúng tôi cung cấp cho các bạn đề thi sát với thi thật.
		        </p>
		          <a href="ListExamForward?pageid=1">Chi tiết &rarr;</a>
		      </div>
		  </div>
	

<!-- /.Feature -->

  <div class="hr-divider"></div>

<!-- Row View -->

    
</div>


<!-- /.Row View -->



<!--Footer
==========================-->

<jsp:include page="Footer.jsp" />
<!--/.Footer-->

<!-- start Modal -->
		  <div class="modal fade" id="myModal" role="dialog">
		    <div class="modal-dialog">
		    
		      <!-- Modal content-->
		      <div class="modal-content">
		        <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <h4 class="modal-title">DANH SÁCH LOẠI BÀI HƯỚNG DẪN</h4>
		        </div>
		        <div class="modal-body">
	         	
						<!-- <div class="media">
							<a class="pull-left"><img src="Template/FrontendAdmin/images/loaibtnghe.png" class="media-object" alt='' /></a>
							<div class="media-body">
								<h4>
									<a href="Hienthidshdtuvung?pageid=1">Bài hướng dẫn phần nghe</a>
								</h4> 					
							</div>
						</div> -->
						
						<div class="media">
							<a class="pull-left"><img src="Template/FrontendAdmin/images/loaibtnghe.png" class="media-object" alt='' /></a>
							<div class="media-body">
								<h4>
									<a href="Hienthidshdtuvung?pageid=1">Bài hướng dẫn phần nghe</a>
								</h4> 					
							</div>
						</div>
					
			          	<div class="media">
							<a class="pull-left"><img src="Template/FrontendAdmin/images/loaibtdoc.png" class="media-object" alt='' /></a>
							<div class="media-body">
								<h4>
									<a href="ListGrammarForward?pageid=1">Bài hướng dẫn phần ngữ pháp</a>
								</h4> 					
							</div>
						</div>
		        </div>
		        <div class="modal-footer">
		          <button type="button" class="btn btn-default" data-dismiss="modal">Thoát</button>
		        </div>
		      </div>
		      
		    </div>
		  </div>
		  <!-- end modal -->
		  
		  <!-- start Modal làm bt -->
		  <div class="modal fade" id="myModal1" role="dialog">
		    <div class="modal-dialog">
		    
		      <!-- Modal content-->
		      <div class="modal-content">
		        <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <h4 class="modal-title">DANH SÁCH LOẠI BÀI TẬP</h4>
		        </div>
		        <div class="modal-body">
	         	
						<div class="media">
							<a class="pull-left"><img src="Template/FrontendAdmin/images/loaibtnghe.png" class="media-object" alt='' /></a>
							<div class="media-body">
								<h4>
									<a href="Hienthidsbtnghe?pageid=1">Bài tập phần nghe</a>
								</h4> 					
							</div>
						</div>
					
			          	<div class="media">
							<a class="pull-left"><img src="Template/FrontendAdmin/images/loaibtdoc.png" class="media-object" alt='' /></a>
							<div class="media-body">
								<h4>
									<a href="Hienthidsbtphandoc?pageid=1">Bài tập phần đọc</a>
								</h4> 					
							</div>
						</div>
		        </div>
		        <div class="modal-footer">
		          <button type="button" class="btn btn-default" data-dismiss="modal">Thoát</button>
		        </div>
		      </div>
		      
		    </div>
		  </div>
		  <!-- end modal -->

</body>
</html>