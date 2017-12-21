<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>manager-setroomtype</title>
</head>
<body>
<form>
<table>
	<tr>
		<td>客房类型</td>
		<td><input id="roomtype" type="text" name="roomtype" required></td>
	</tr>
	<tr>
		<td>客房价格</td>
		<td><input id="price" type="text" name="price" required></td>
	</tr>
	<tr>
		<td>空余数量</td>
		<td><input id="surplus" type="number" name="surplus" required></td>
	</tr>
	<tr>
		<td><input type="reset" value="重置"></td>
		<td><button id="submit">添加</button></td>
	</tr>
</table>
</form>
<script src="js/jquery.js"></script>
<script>
$('#submit').click(function() {
	$.ajax({
	    url: 'SetRoomType',
	    method: 'post',
	    data: {
	    	roomtype: $('#roomtype').val(),
	    	surplus: $('#surplus').val(),
	    	price: $('#price').val()
	    },
	    success: function(res) {
	    	if (res.status == 200) alert('添加成功！');
	    	else if (res.status  == 1062) alert('该房间类型已存在！');
	    	else alert('添加失败！');
	    },
	    error: function(req, sta, err) {
	    	alert('服务端错误!', sta, err)
	    }
	});
});
</script>
</body>
</html>