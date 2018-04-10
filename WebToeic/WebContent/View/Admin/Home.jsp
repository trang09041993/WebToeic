<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home Admin</title>


		<link rel="stylesheet" href="Template/Backend/assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="Template/Backend/assets/css/colorbox.min.css" />
		
		<!-- font -->
		<link rel="stylesheet" href="Template/Backend/assets/font-awesome/4.5.0/css/font-awesome.min.css" />	
		<link rel="stylesheet" href="Template/Backend/font/font-awesome.min.css" />
		<link rel="stylesheet" href="Template/Backend/assets/css/fonts.googleapis.com.css" />

		<link rel="stylesheet" href="Template/Backend/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />	
		<link rel="stylesheet" href="Template/Backend/assets/css/ace-skins.min.css" />
		<link rel="stylesheet" href="Template/Backend/assets/css/ace-rtl.min.css" />

		<script src="Template/Backend/assets/js/ace-extra.min.js"></script>
		
		<style type="text/css">
		
			.paddingtop-image
			{
				 padding-top: 75px;
			}
		
		</style>

		
</head>
<body class="no-skin">
<!-- HEADER BEGIN -->
		<jsp:include page="Header.jsp"/>
<!-- HEADER END-->

 <!-- MENU BEGIN -->
 	<jsp:include page="Menu.jsp"/>
 <!-- MENU END -->

<!-- MAIN CONTENT BEGIN -->
<div class="main-content">
	<div class="main-content-inner">
		<div class="breadcrumbs ace-save-state" id="breadcrumbs">
			<ul class="breadcrumb">
				<li>
					<i class="ace-icon fa fa-home home-icon"></i>
						<a href="#">Home</a>
				</li>
			</ul><!-- /.breadcrumb -->
			<!-- /.page-header -->
			
			<div class="row">
				<div class="col-xs-12">		
					<a href="Template/Backend/assets/images/gallery/image-1.png" title="Photo Title" >
						<center class="paddingtop-image">
								<img width="400" height="200" alt="150x150" src="Template/Backend/assets/images/gallery/image-1.png" />
						</center>
					</a>					
				</div>
			</div>
		
		</div>
	</div>
</div>

<!-- MAIN CONTENT END -->



 <!-- FOOTER BEGIN -->
 		<jsp:include page="Footer.jsp"/>
 <!-- FOOTER END -->
 
 <!-- basic scripts -->

		<!--[if !IE]> -->
		<script src="Template/Backend/assets/js/jquery-2.1.4.min.js"></script>

		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='Template/Backend/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="Template/Backend/assets/js/bootstrap.min.js"></script>

		<!-- page specific plugin scripts -->
		<script src="Template/Backend/assets/js/jquery.colorbox.min.js"></script>

		<!-- ace scripts -->
		<script src="Template/Backend/assets/js/ace-elements.min.js"></script>
		<script src="Template/Backend/assets/js/ace.min.js"></script>

		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			jQuery(function($) {
	var $overflow = '';
	var colorbox_params = {
		rel: 'colorbox',
		reposition:true,
		scalePhotos:true,
		scrolling:false,
		previous:'<i class="ace-icon fa fa-arrow-left"></i>',
		next:'<i class="ace-icon fa fa-arrow-right"></i>',
		close:'&times;',
		current:'{current} of {total}',
		maxWidth:'100%',
		maxHeight:'100%',
		onOpen:function(){
			$overflow = document.body.style.overflow;
			document.body.style.overflow = 'hidden';
		},
		onClosed:function(){
			document.body.style.overflow = $overflow;
		},
		onComplete:function(){
			$.colorbox.resize();
		}
	};

	$('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
	$("#cboxLoadingGraphic").html("<i class='ace-icon fa fa-spinner orange fa-spin'></i>");//let's add a custom loading icon
	
	
	$(document).one('ajaxloadstart.page', function(e) {
		$('#colorbox, #cboxOverlay').remove();
   });
})
		</script>
</body>
</html>























