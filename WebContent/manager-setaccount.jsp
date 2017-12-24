<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>manager-setaccount</title>
</head>
<body>
<div id="vue">
<h3>添加员工</h3>
<form id="addstaff">
<table>
	<tr>
		<td>员工姓名</td>
		<td><input type="text" name="staffname" required></td>
	</tr>
	<tr>
		<td>员工职位</td>
		<td>
		<select name="role" required>
		<option v-for="role in roles" :value="role">{{role}}</option>
		</select>
		</td>
	</tr>
	<tr>
		<td><input type="reset" value="重置"></td>
		<td><button id="submitaddstaff" @click="submitaddstaff">添加</button></td>
	</tr>
</table>
</form>

<h3>移除员工</h3>
<form id="removestaff">
<table>
	<tr>
		<td>员工号</td>
		<td><input type="number" name="staffid" required></td>
	</tr>
	<tr>
		<td><input type="reset" value="重置"></td>
		<td><button id="submitremovestaff" @click="submitremovestaff">移除</button></td>
	</tr>
</table>
</form>

<h3>注销用户</h3>
<form id="removeuser">
<table>
	<tr>
		<td>用户ID</td>
		<td><input type="number" name="idcard" required></td>
	</tr>
	<tr>
		<td><input type="reset" value="重置"></td>
		<td><button id="submitremoveuser" @click="submitremoveuser">注销</button></td>
	</tr>
</table>
</form>
</div>
</body>
<script src="js/vue.js"></script>
<script src="js/jquery.js"></script>
<script >
var url = 'SetAccount';
new Vue({
	el: '#vue',
	data: function() {
		return {
			roles: ['前台','经理']
		}
	},
	methods: {
		submitaddstaff: function() {
			$.ajax({
				url: url,
				method: 'post',
				data: {
					action: 'addstaff',
					staffname: $("input[name='staffname']").val(),
					role: $("select[name='role']").val()
				},
				success: (res) => {
					if (res.staffid) alert('添加成功！ 员工号是：' + res.staffid);
					else alert(res.status);
				},
				error: (req, sta, err) => {
					alert('可能遇到一些问题！请查询确认');
				}
			});
		},
		submitremovestaff: function() {
			$.ajax({
				url: url,
				method: 'post',
				data: {
					action: 'removestaff',
					staffid: $("input[name='staffid']").val()
				},
				success: (res) => {
					if (res.staffid) alert('移除成功！ 员工号是：' + res.staffid);
					else alert(res.status);
				},
				error: (req, sta, err) => {
					alert('可能遇到一些问题！请查询确认');
				}
			});
		},
		submitremoveuser: function() {
			$.ajax({
				url: url,
				method: 'post',
				data: {
					action: 'removeuser',
					idcard: $("input[name='idcard']").val()
				},
				success: (res) => {
					if (res.idcard) alert('注销成功！ 用户ID是：' + res.idcard);
					else alert(res.status);
				},
				error: (req, sta, err) => {
					alert('可能遇到一些问题！请查询确认');
				}
			});
		}
	}
});
</script>
</html>