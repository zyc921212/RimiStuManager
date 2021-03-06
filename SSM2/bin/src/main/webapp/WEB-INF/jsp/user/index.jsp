<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%   
String path = request.getContextPath();   
String basePath = request.getScheme()+"://" +request.getServerName()+":" +request.getServerPort()+path+"/" ;   
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <title>Rimi|用户查询</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <!-- VENDOR CSS -->
    <link rel="stylesheet" href="<%=basePath%>RXF/assets/vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath%>RXF/assets/vendor/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=basePath%>RXF/assets/vendor/linearicons/style.css">
    <link rel="stylesheet" href="<%=basePath%>RXF/assets/vendor/chartist/css/chartist-custom.css">
    <!-- MAIN CSS -->
    <link rel="stylesheet" href="<%=basePath%>RXF/assets/css/main.css">
    <!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
    <link rel="stylesheet" href="<%=basePath%>RXF/assets/css/demo.css">
    <!-- GOOGLE FONTS -->
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">

    <script src="<%=basePath%>RXF/assets/vendor/jquery/jquery.min.js"></script>
    <script src="<%=basePath%>RXF/assets/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>RXF/assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
    <script src="<%=basePath%>RXF/assets/vendor/jquery.easy-pie-chart/jquery.easypiechart.min.js"></script>
    <script src="<%=basePath%>RXF/assets/vendor/chartist/js/chartist.min.js"></script>
    <script src="<%=basePath%>RXF/assets/scripts/klorofil-common.js"></script>
    <script type="text/javascript">
        function checkall() {
            var alls = document.getElementsByName("check");
            var ch = document.getElementById("checkall");
            if (ch.checked) {
                for (var i = 0; i < alls.length; i++) {
                    alls[i].checked = true;
                }
            } else {
                for (var i = 0; i < alls.length; i++) {
                    alls[i].checked = false;
                }
            }
        }

        function delAll() {
            var alls = document.getElementsByName("check");
            var ids = new Array();
            for (var i = 0; i < alls.length; i++) {
                if (alls[i].checked) {
                    ids.push(alls[i].value);
                }
            }
            if (ids.length > 0) {
                if (confirm("确认操作?")) {
                    alert("成功!");
                }
            } else {
                alert("请选中要操作的项");
            }
        }
    </script>
    
    <script type="text/javascript">
		function logout(){
			 var bool = window.confirm("确定注销登录吗");
			 if(bool){//确定
				 window.location="logout.do";
			 }else{//取消
			 	
			 }
		}
	</script>
    
</head>

<body>
    <!-- WRAPPER -->
    <div id="wrapper">
        <!-- NAVBAR -->
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="brand">
                <a href="#"><span style="font-size: 35px;padding-top:20px;">Rimi</span></a>
            </div>
            <div class="container-fluid">
                <div class="navbar-btn">
                    <button type="button" class="btn-toggle-fullwidth"><i class="lnr lnr-arrow-left-circle"></i></button>
                </div>
                <div id="navbar-menu">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><img src="../assets/img/user1.png" class="img-circle" alt="Avatar"> <span>王权富贵</span> <i class="icon-submenu lnr lnr-chevron-down"></i></a>
                            <ul class="dropdown-menu">
                                <li><a href="user.html"><i class="lnr lnr-user"></i> <span>我的信息</span></a></li>
                                <li><a href="#"><i class="lnr lnr-envelope"></i> <span>Message(未实现)</span></a></li>
                                <li><a href=""><i class="lnr lnr-cog"></i> <span>修改密码</span></a></li>
                                <li><a href="javascript:logout()"><i class="lnr lnr-exit"></i> <span>登出</span></a></li>
                            </ul>
                        </li>
                    </ul>
                </div>

            </div>
        </nav>
        <!-- END NAVBAR -->
        <!-- LEFT SIDEBAR -->
        <div id="sidebar-nav" class="sidebar">
            <div class="sidebar-scroll">
                <nav>

                    <ul class="nav">
                        <li>
                            <a href="#subPages1" data-toggle="collapse" class="collapsed">
                                <i class="lnr lnr-home"></i> <span>学员信息</span>
                                <i class="icon-submenu lnr lnr-chevron-left"></i>
                            </a>
                            <div id="subPages1" class="collapse">
                                <ul class="nav">
                                    <li><a href="stuindex.html" class="">学员跟进</a></li>
                                    <li><a href="Student-add.html" class="">添加学员</a></li>
                                </ul>
                            </div>
                        </li>
                        <li>
                            <a href="#subPages" data-toggle="collapse" class="collapsed">
                                <i class="lnr lnr-user"></i> <span>用户管理</span>
                                <i class="icon-submenu lnr lnr-chevron-left"></i>
                            </a>
                            <div id="subPages" class="collapse ">
                                <ul class="nav">
                                    <li><a href="index.html" class="">用户查询</a></li>
                                    <li><a href="add.html" class="">添加用户</a></li>
                                </ul>
                            </div>
                        </li>
                        <li>
                            <a href="#subPages2" data-toggle="collapse" class="collapsed">
                                <i class="glyphicon glyphicon-user"></i> <span>个人信息</span>
                                <i class="icon-submenu lnr  lnr-chevron-left"></i>
                            </a>
                            <div id="subPages2" class="collapse">
                                <ul class="nav">
                                    <li><a href="user.html" class="">我的资料</a></li>
                                    <li><a href="" class="">更改密码</a></li>
                                </ul>
                            </div>
                        </li>


                    </ul>
                </nav>

            </div>
        </div>
        <!-- END LEFT SIDEBAR -->
        <div class="main">
            <div class="panel">
                <div class="panel-heading">
                    <div class="right">
                        <button type="button" class="btn-toggle-collapse"><i class="lnr lnr-chevron-up"></i></button>
                    </div>
                </div>
                <div class="panel-body no-padding">
                    <form action="index.html" method="post" class="definewidth m20">
                        <table class="table table-striped table-hover definewidth m20">
                            <tr>
                                <td width="10%" class="tableleft">用户名称：</td>
                                <td width="23%"><input type="text" name="pname" value="" /></td>

                                <td width="10%" class="tableleft">用户角色：</td>
                                <td width="23%">
                                    <select>
						        		<option value="">--请选择--</option>
						        		<option value="1">管理员</option>
						        		<option value="2">普通用户</option>
						        	</select>
                                </td>

                                <td width="10%" class="tableleft">用户职业：</td>
                                <td width="23%">
                                    <select>
						        		<option value="">--请选择--</option>
						        		<option value="1">线上咨询师</option>
						        		<option value="2">线下咨询师</option>
						        	</select>
                                </td>
                            </tr>
                            <tr>
                                <td width="10%" class="tableleft">工作状态：</td>
                                <td width="23%">
                                    <select>
						        		<option value="">--请选择--</option>
						        		<option value="1">在职</option>
						        		<option value="2">休假</option>
						        		<option value="3">离职</option>
						        	</select>
                                </td>

                                <td colspan="6">
                                    <div class="col-md-10 text-right">
                                        <button type="submit" class="btn btn-primary" type="button">查询</button>
                                        <button type="submit" class="btn btn-primary" type="button">清空</button>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
            <div class="panel">
                <div class="panel-heading">
                    <h3 class="panel-title">用户信息</h3>
                    <div class="right">
                        <button type="button" class="btn-toggle-collapse"><i class="lnr lnr-chevron-up"></i></button>
                    </div>
                </div>
                <div class="panel-body no-padding">
                    <table class="table table-bordered table-hover definewidth m10">
                        <thead>
                            <tr>
                                <th width="5%"><input type="checkbox" id="checkall" onChange="checkall();"></th>
                                <th width="11%">用户账户</th>
                                <th width="11%">真实姓名</th>
                                <th width="11%">用户职业</th>
                                <th width="11%">工作状态</th>
                                <th width="11%">用户电话</th>
                                <th width="11%">用户角色</th>
                                <th width="11%">用户状态</th>
                                <th width="18%">操作</th>
                            </tr>
                        </thead>
                        <tr>
                            <td style="vertical-align:middle;"><input type="checkbox" name="check" value="1"></td>
                            <td>admin</td>
                            <td>管理员</td>
                            <td>线上咨询师</td>
                            <td>休假</td>
                            <td>13684426035</td>
                            <td>普通用户</td>
                            <td>启用</td>
                            <td>
                                <a href='look.html'><span class="label label-primary">详情</span></a>
                                <a href='edit.html'><span class="label label-info">编辑</span></a>
                                <a href=''><span class="label label-danger">删除</span></a>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="16">
                                <div class="col-md-6 text-left inline pull-left page">
                                    <a href='#'>首页</a> &nbsp;&nbsp;&nbsp;
                                    <a href='#'>上一页</a> &nbsp;&nbsp;&nbsp;
                                    <a href='#'>下一页</a> &nbsp;&nbsp;&nbsp;
                                    <a href='#'>尾页</a> &nbsp;&nbsp;&nbsp; 共
                                    <span class='current'>32</span>条记录<span class='current'> 第1/33 </span>页&nbsp;&nbsp;
                                    <input type="text" placeholder="1" id="forword" />
                                    <script type="text/javascript">
                                        $("#forword").keydown(function() {
                                            if (event.keyCode == "13") {
                                                alert('按下了回车键');
                                            }
                                        });
                                    </script>
                                </div>
                                <div class=" col-md-6 text-right">
                                    <button type="submit" class="btn btn-primary" type="button">删除</button>
                                    <button type="submit" class="btn btn-primary" type="button">导出</button>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>

            </div>
        </div>
    </div>
    <footer>
        <div class="container-fluid">
            <p class="copyright">&copy; 2017 <a href="http://www.rimiedu.com/" title="睿峰培训" target="_blank">睿峰培训</a></p>
        </div>
    </footer>
    <!-- END WRAPPER -->
</body>
</html>