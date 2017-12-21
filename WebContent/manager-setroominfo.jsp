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
		<td><input id="roomnum" type="number" name="roomnum" required></td>
	</tr>
	<tr>
		<td>楼层号</td>
		<td><input id="floornum" type="number" name="floornum" required></td>
	</tr>
	<tr>
		<td>房间类型</td>
		<td>
		<select name="roomtype">
		<option v-for="roomtype in roomTypes" value="roomtype">{{roomtype}}</option>
		</select>
		</td>
	</tr>
	<tr>
		<td>朝向</td>
		<td>
		<select name="orientation">
		<option v-for="orientation in orientations" value="orientation">{{orientation}}</option>
		</select>
		</td>
	</tr>
	<tr>
		<td>房间特征描述</td>
		<td><textarea name="description"></textarea></td>
	</tr>
	<tr>
		<td>是否空置</td>
		<td>
		<label>是</label>
		<input type="radio" name="available" value="available" required>
		<label>否</label>
		<input type="radio" name="available" value="unavailable" required>
		</td>
	</tr>
	<tr>
		<td><input type="reset" value="重置"></td>
		<td><button id="submit" @click="submit">添加</button></td>
	</tr>
</table>
</form>
</div>
<script src="js/vue.js"></script>
<script src="js/jquery.js"></script>
<script>
new Vue({
	el: '#vue',
	data: function() {
		return {
			roomTypes: [],
			orientations: ['南', '北']
		}
	},
	mounted: function() {
		$.ajax({
		    url: 'SetRoomInfo',
		    method: 'get',
		    success: (res) => {
		    	if (res.roomtypes) this.roomTypes = res.roomtypes;
		    },
		    error: (req, sta, err) => {
		    	alert('服务端错误!', sta, err)
		    }
		});
	},
	methods: {
		submit: function() {
			
		}
	}
});
</script>
</body>
</html>