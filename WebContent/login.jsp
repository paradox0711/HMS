<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录;会员登录| 酒店管理系统</title>
    <style>
    	body 
    	{
    	background-image:url('images/loginbackground.jpg');
    	background-repeat:no-repeat;
    	background-position:left top;
    	margin-left:200px;
    	background-color:#cccccc;
    	}

        .gt_holder.popup{z-index: 1000;}
        .gt_holder .gt_bottom{background-image:none !important;background-color:#f2ece1;-webkit-border-bottom-left-radius:15px;-webkit-border-bottom-right-radius:15px;-moz-border-radius-bottomleft:15px;-moz-border-radius-bottomright:15px;border-bottom-left-radius:15px;border-bottom-right-radius:15px;}
    </style>
    <link href="css/nav.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<!-- 头部背景 -->
	<div class="Mhead slim">
		<div class="inner Cwrap">
			<div class="mbox">
				<div class="content Lcfx Ltar">
				</div>
			</div>
		</div>
	</div>
	<div class="rbox Lflr">
		<ul id="myTab" class="nav nav-tabs">
			<li class="active">
				<a href="#private" data-toggle="tab"> 个人登录</a>
			</li>
			<li>
				<a href="#company" data-toggle="tab">公司卡号登录</a>
			</li>
		</ul>
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="private">
				<div class="">
					<div class="">
                        	<br/>
                            <span class=""><i class="">&nbsp;</i>用户名：</span>
                            <input name="username" type="text" placeholder="会员手机号/邮箱" class="" autocomplete="off">
                            <br/><br/> <br/>        
                    </div>
                        <div class="">
                            <span class=""><i class="">&nbsp;</i>密　码：</span>
                                <input type="password" placeholder="请输入密码" class="" maxlength="18" autocomplete="off">
                        </div> 
                 </div>
                    <br/><br/>
                    <div class="container">
                        <button type="button" id="s_submit" class="btn btn-info btn-lg">登录</button>
                    </div>
                    <br/>
                    <div class="">
                        <span class="Lfll">享更多特权，</span>
                        <a href="#" class="Lfll">立即注册</a>
                        <a href="#" class="Lflr">忘记密码？</a>
                    </div>
            </div>
            <div class="tab-pane fade" id="company">
            	<div class="">
            		<br/>
            		<span class=""><i class="">&nbsp;</i>用户名：</span>
                            <input type="text" placeholder="公司卡号" class="" autocomplete="off">  
                 </div>
                 	<br/><br/>
                    <div class="">
                        <span class=""><i class="">&nbsp;</i>密　码：</span>
                            <input type="password" placeholder="请输入密码" class="" maxlength="18" autocomplete="off">
                    </div>
                    <br/><br/>
                    <div class="container">
                        <button type="button" id="s_submit2" class="btn btn-info btn-lg">登录</button>
                    </div>
                    <br/>
                    <div class=""><span class="Lfll">享更多特权，</span><a href="#" class="Lfll">公司卡注册</a><a href="#" class="Lflr">忘记密码？</a>
                    </div>
	</div>
</div>
       </div>
</body>
</html>