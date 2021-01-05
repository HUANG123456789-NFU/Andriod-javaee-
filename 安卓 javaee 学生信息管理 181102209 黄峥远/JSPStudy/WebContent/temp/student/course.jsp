<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resource/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resource/dist/css/AdminLTE.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resource/dist/css/skins/_all-skins.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resource/font-awesome-4.4.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resource/datatables/dataTables.bootstrap.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resource/mycss/mystlye.css">
<style type="text/css">
table {
      text-align:center; /*设置水平居中*/
      vertical-align:middle;/*设置垂直居中*/
      font-size: 12px;
}
th {
      text-align:center; /*设置水平居中*/
      vertical-align:middle;/*设置垂直居中*/
      font-size: 14px;
}
</style>
<title>课程信息管理</title>
</head>
<body class="hold-transition skin-yellow layout-top-nav">
	<div class="wrapper">
		<div class="main-header">
			<div class="navbar navbar-static-top">
				<div class="navbar-header">
					<a href="#" class="navbar-brand"><b>Admin</b>课程管理</a>
				</div>
				<!--顶部导航栏菜单按钮-->
				<div class="collapse navbar-collapse pull-left" id="navbar-collapse">
					<ul class="nav navbar-nav">
						<li class="dropdown"><a href="student.jsp">学生信息 <span
								class="sr-only">(current)</span></a></li>
						<li class="active"><a href="# "id="courseshow">课程信息 <span 
								class="sr-only">(current)</span></a></li>
						<li class="dropdown"><a href="grade.jsp">成绩管理 <span 
						class="sr-only">(current)</span></a></li>
					</ul>

				</div>
				<!-- /.navbar-collapse -->
				<!--顶部导航栏右侧的-->
			</div>
		</div>
	<div class="content-wrapper">
			<div class="content" style="min-height: 560px">
				<div class="box-header with-border">
					<i class="fa fa-th-list"></i>
					<h4 class="box-title">课程管理</h4>
				</div>
				<div class="box-body">
					<div class="box-header with-border">
						<button type="button" name="add" id="add-btn"
							class="btn btn-info pull-left addBtn" title="添加">
							<i class="fa fa-plus-circle"></i>添加
						</button>
						<button type="button" name="cancel" id="cancel-btn"
							class="btn btn-info pull-left cancelBtn" title="刷新">
							<i class="fa fa-refresh"></i>刷新
						</button>
						<div class="form-inline pull-right">
							<button type="button" name="search" id="search-btn"
								class="btn btn-default pull-right searchBtn" title="查询">
								<i class="fa fa-search"></i>
							</button>
							<input type="text"
								class="form-control pull-right name-search position-search"
								id="keyword" placeholder="课程名" />
						</div>
					</div>
					<table width="100%" id="dataTable" class="table">
						<thead>
							<tr>
								<th width="10%">序号</th>
								<th width="10%">课程编号</th>
								<th width="15%">课程名称</th>
								<th width="15%">课程学分</th>
								<th width="20%">操作</th>
							</tr>
						</thead>
						<tbody id="stuInfoList">
						</tbody>
					</table>
				</div>
			</div>

		</div>
		<div class="main-footer">
			<div class="pull-right hidden-xs">
				<strong>Copyright &copy; 2016-2017 <a href="#">重庆交通大学</a></strong>
			</div>
			<b>Version</b> 1.0.0

		</div>
		<!-- 课程信息弹窗 -->
		<div class="modal" id="courseModal">
			<div class="modal-dialog" role="dialog">
				<div class="modal-content">
					<form id="userEditForm">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title">
								<strong>课程信息</strong>
							</h4>
						</div>
						<div class="modal-body">
							<input type="hidden" name="no" id="no" />
							<div class="form-horizontal">
								<div class="row">
									<div class="form-group">
										<label class="col-sm-2 control-label">课程名称</label>
										<div class="col-sm-4">
											<div class="col-md-10">
												<select style="width: 100%" class="form-control"
													name="name" id="name">
													<option>java</option>
													<option>数据库管理技术</option>
													<option>大数据开发</option>
													<option>高等数学</option>
													<option>大学物理</option>
													<option>大学英语</option>
													<option>单片机设计</option>
												</select>
											</div>
											<div class="col-md-2">
												<span style="color: #F00; fontsize: 30px">*</span>
											</div>
										</div>
										<label class="col-sm-2 control-label">课程学分</label>
										<div class="col-sm-4">
											<div class="col-md-11">
												<input class="form-control" type="text" name="score" id="score" />
											</div>
										</div>
									</div>

								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary  saveBtn">保存</button>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">返回</button>
						</div>
					</form>
				</div>

			</div>
		</div>
		<!-- 课程信息弹窗 -->
	</div>
	<!-- jQuery 2.1.4 -->
	<script
		src="<%=request.getContextPath()%>/resource/jQuery/jQuery-2.1.4.min.js"></script>
	<!-- Bootstrap 3.3.5 -->
	<script
		src="<%=request.getContextPath()%>/resource/bootstrap/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
		//加载页面自动获得所有课程信息
		jQuery(document).ready(function() {
			findCourse("");
		
		})
		//打开增加课程信息的弹窗
		$('.addBtn').on('click',function(){
			jQuery("#no").val('');
			jQuery("#name").val('');
			jQuery("#score").val('');
			jQuery("#courseModal").modal('show');
		});
		//保存课程信息
		$('.saveBtn').on('click',function(){
			saveCourse();
		});
		//查询课程信息
    	$('.searchBtn').on('click',function(){
    		var key=$('#keyword').val();
    		findCourse(key);	
    	})
    	//刷新表格
    	$('.cancelBtn').on('click',function(){
    		findCourse('');
	   	 })
	    ///保存课程信息
		function saveCourse(){
			var url='<%=request.getContextPath()%>/CourseInsertServlet';
			var no = $('#no').val();
			var name = $('#name').val();
			var score = $('#score').val();
			///传到后台保存的JSON数据
			var data = {
				'no' : no,
				'name' : name,
				'score' : score,
			}
			$.ajax({
				type : "post",
				url : url,
				dataType : "json",
				data:data,
				success : function(data) {
					if (data.code == 1) {
						findCourse('');
						jQuery("#courseModal").modal('hide');
					} else {
						alert('保存失败')
					}
				}
			})
		}
    	function findCourse(key){
			var url='<%=request.getContextPath()%>/CourseQuery?key='+key;
			var t1=document.getElementById("dataTable");
			$.ajax({
				type : "get",
				url : url,
				dataType : "json",
				contentType: "application/x-www-form-urlencoded; charset=utf-8",
				success : function(data) {//根据返回的数据画出表格
                	if(data!=null){
                		/*加载表格前先删除除第一行的所有行 */
		            	var rows=t1.rows;
                		for(var i=rows.length-1;i>0;i--){
                			rows[i].remove();
                		}
                		for(var i=0;i<data.courses.length;i++){
                			var row=document.createElement('tr');
                			row.innerHTML="<td>"+(i+1)+"</td>"+"<td>"
                			+ data.courses[i].no
							+ "</td>"
							+ "<td>"
							+ data.courses[i].name
							+ "</td>"
							+ "<td>"
							+ data.courses[i].score
							+ "</td>"
                			+"<td><button class='btn btn-sm btn-default delBtn' onclick='deleteCourse(this)' value='"+data.courses[i].no+"'>删除</button>"
                			+"<button class='btn btn-sm btn-default editBtn' onclick='editCourse(this)' value='"+(i+1)+"'>修改</button></td>";
                			t1.appendChild(row);
                		}
                	
					} 
            	}
	   		})
		}
  		//编辑课程信息
		function editCourse(d){
			jQuery("#courseModal").modal('show');
  			var i=d.value;
			var table=document.getElementById("dataTable");
			var rows=table.rows;
			var no=rows[i].cells[1].innerHTML;
			var name=rows[i].cells[2].innerHTML;
			var score=rows[i].cells[3].innerHTML;
			$('#no').val(no);
			$('#score').val(score);
			///下拉框显示课程的名称
			showSelectOption('#name', selectValue);
		}
		//删除课程信息
		function deleteCourse(d){
			if(!confirm("是否删除该课程信息")) return;
			var no=d.value
			var url='<%=request.getContextPath()%>/CourseDelServlet?no='+ no;
			$.ajax({
				type : "get",
				url : url,
				dataType : "json",
				success : function(data) {
					if (data.code == 1) {
						var row = d.parentNode.parentNode;//得到当前元素父节点的父节点(tr)
						row.parentNode.removeChild(row);//删除当前行
					} else {
						alert("课程有学生选择,不能删除！")
					}
				}
			})
		}
   </script>
</body>
</html>