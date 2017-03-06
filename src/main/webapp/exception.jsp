<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <title>云制造设备管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Loading Bootstrap UI -->
    <link href="css/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Loading Flat UI -->
    <link href="css/flat-ui.min.css" rel="stylesheet">
    <link rel="shortcut icon" href="img/favicon.ico">
    <!-- Loading Custom UI -->
    <link href="css/custom/error-ui.css" rel="stylesheet" type="text/css">
  </head>
  <body>
    <div class="text-center">
      <h1>
        ERROR &nbsp;&nbsp;<%=response.getStatus()%>
      </h1>
      <p><c:out value="${reason}"/></p>
      <p>页面一不小心发生了一点错误</p>
      <a class="btn btn-primary" href="index.html">返回</a>
    </div>
    <!-- Loading Jquery -->
    <script src="js/vendor/jquery.min.js"></script>
    <!-- Loading Flat-UI -->
    <script src="js/flat-ui.min.js"></script>
  </body>
</html>
