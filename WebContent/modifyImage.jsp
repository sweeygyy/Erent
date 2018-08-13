<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
  <head>
    <title>modify.jsp</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->

  </head>
  <!-- enctype="multipart/form-data" -->
  <body>
    <form name="f1" id="f1" action="ModifyServlet" method="post" enctype="multipart/form-data">
    <input type="hidden" name="method" value="modify" />
      <table border="0">
        <tr>
          <td>buy_num</td>
          <td><input type="text" name="customer_id" id="customer_id" value=""></td>
        </tr>
        <tr>
          <td>pImage:</td>
          <img alt="" src="" />
          <td><input type="file" name="pImage" id="pImage"></td>
        </tr> 
        <tr>
          <td colspan="2" align="center"><input type="submit"></td>
        </tr>
      </table>
    </form>
  </body>
</html>
