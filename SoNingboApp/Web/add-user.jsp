<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>My JSP 'add-user.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

	</head>

	<body>
		<form name="form1" method="post" action="/app/user/add">
			<p>

				key
				<br>
				<label for="key"></label>
				<input type="text" name="key" id="key">
				<br>
				<br>


				username
				<br>
				<label for="username"></label>
				<input type="text" name="username" id="username">
				<br>
				<br>


				password
				<br>
				<label for="password"></label>
				<input type="text" name="password" id="password">
				<br>
				<br>


				email
				<br>
				<label for="email"></label>
				<input type="text" name="email" id="email">
				<br>
				<br>

				<!-- 
  head_photo<br>
  <label for="head_photo"></label>
  <input type="file" name="head_photo" id="head_photo">
  <br>
  <br>
   -->


				<input type="submit" name="POST" id="POST" value="Submit">
				<br>
				<br>
			</p>
		</form>

	</body>
</html>
