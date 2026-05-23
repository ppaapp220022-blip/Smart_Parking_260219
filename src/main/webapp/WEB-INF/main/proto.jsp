<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String ctx = request.getContextPath(); %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<%= ctx %>/CSS/style.css">
</head>
<body>
<!-- Navigation -->
<%@ include file="/main/menu.jsp" %>
<div class="main-content">
  <!-- 제목 -->
  <div id="entry" class="page">
    <h2>  </h2>
   <!-- 내용 -->
    <div class="form-group">

    </div>
  </div>
</div>
    <script src="<%= ctx %>/JS/menu.js"></script>
    <script src="<%= ctx %>/JS/function.js"></script>
</body>
</html>
