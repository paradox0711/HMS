<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>manager-setroominfo</title>
</head>
<body>
<div id="vue">
<form>
<table>
	<tr>
		<td>房间号</td>
		<td><input type="number" name="roomnum" required :disabled="disabled"></td>
	</tr>
	<tr>
		<td>楼层号</td>
		<td><input type="number" name="floornum" required :disabled="disabled"></td>
	</tr>
	<tr>
		<td>房间类型</td>
		<td>
		<select name="roomtype" required :disabled="disabled">
		<option v-for="roomtype in roomTypes" :value="roomtype">{{roomtype}}</option>
		</select>
		</td>
	</tr>
	<tr>
		<td>朝向</td>
		<td>
		<select name="orientation" required :disabled="disabled">
		<option v-for="orientation in orientations" :value="orientation">{{orientation}}</option>
		</select>
		</td>
	</tr>
	<tr>
		<td>房间特征描述</td>
		<td><textarea name="description" :disabled="disabled"></textarea></td>
	</tr>
	<tr>
		<td>是否空置</td>
		<td>
		<label>是</label>
		<input type="radio" name="available" value="1" checked :disabled="disabled">
		<label>否</label>
		<input type="radio" name="available" value="0" :disabled="disabled">
		</td>
	</tr>
	<tr>
		<td><input type="reset" value="重置" :disabled="disabled"></td>
		<td><button id="submit" @click="submit" :disabled="disabled">添加</button></td>
	</tr>
</table>
</form>
</div>
<script src="js/vue.js"></script>
<script src="js/jquery.js"></script>
<script>
var url = 'SetRoomInfo';
new Vue({
	el: '#vue',
	data: function() {
		return {
			roomTypes: [],
			orientations: ['南', '北', '东', '西', '东南', '东北', '西南', '西北'],
			disabled: false
		}
	},
	mounted: function() {
		$.ajax({
		    url: url,
		    method: 'get',
		    success: (res) => {
		    	if (res.roomtypes.length != 0) this.roomTypes = res.roomtypes;
		    	else {
		    		this.disabled = true;
		    		alert('请先添加房间类型！');
		    	}
		    },
		    error: (req, sta, err) => {
		    	alert('无法获取服务端信息，请检查网络设置！', sta, err);
		    }
		});
	},
	methods: {
		submit: function() {
			
			if ($("input[name='roomnum']").val() != ''
					&& $("input[name='floornum']").val() != ''
					&& $("select[name='orientation']").val() != '' )
			$.ajax({
				url: url,
				method: 'post',
				data: {
					roomnum: $("input[name='roomnum']").val(),
					floornum: $("input[name='floornum']").val(),
					roomtype: $("select[name='roomtype']").val(),
					orientation: $("select[name='orientation']").val(),
					description: $("textarea[name='description']").val(),
					available: $("input:radio[name='available']:checked").val(),
				},
				success: (res) => {
					if (res.status == 200) alert('添加成功！');
					else alert('添加失败：' + res.status);
				},
				error: (req, sta, err) => {
					alert('可能遇到一些问题！请查询确认');
				}
			});
		}
	}
});
</script>
</body>
</html>