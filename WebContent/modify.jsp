<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
  <head>
    <title>modify.jsp.alter</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->

  </head>
  <!-- enctype="multipart/form-data" -->
  <body>
    <form name="f1" id="f1" action="CustomerServlet" method="post" >
    <input type="hidden" name="method" value="modify" />
      <table border="0">
        <tr>
          <td>buy_num</td>
          <td><input type="text" name="customer_id" id="customer_id" value="${customer.customer_id}"></td>
        </tr>
        <tr>
          <td>Password:</td>
          <td><input type="password" name="password" id="password" value="${customer.password}"></td>
        </tr> 
        <tr>
          <td>name</td>
          <td><input type="text" name="name" id="name" value="${customer.name}"></td>
        </tr>
        <tr>
          <td>XYD</td>
          <td><input type="text" name="XYD" id="XYD" value="${customer.XYD}"></td>
        </tr>
        <tr>
          <td>sex</td>
          <td>	
          <c:choose>
          	<c:when test="${requestScope.customer.sex eq 0}">
          		<input type="radio" name="sex" id="sex" value="0" checked="checked">男
          		<input type="radio" name="sex" id="sex" value="1">女
          	</c:when>
          	<c:otherwise>
          		<input type="radio" name="sex" id="sex" value="0">男
          		<input type="radio" name="sex" id="sex" value="1" checked="checked">女
          	</c:otherwise>
          </c:choose>   		
          </td>
        </tr> 
        <tr>
          <td>birthday</td>
          <td><input type="text" name="birthday" id="birthday" value='<fmt:formatDate value="${customer.birthday}" pattern="yyyy-MM-dd"/>'></td>
        </tr>
        <tr>
          <td>hometown</td>
          <td><input type="text" name="hometown" id="hometown" value="${customer.hometown}"></td>
        </tr> 
        <tr>
          <td>school</td>
          <td><input type="text" name="school" id="school" value="${customer.school}"></td>
        </tr>
        <tr>
          <td>department:</td>
          <td><input type="text" name="department" id="department" value="${customer.department}"></td>
        </tr> 
        <tr>
          <td>Time_enrolment</td>
          <td><input type="text" name="time_enrolment" id="time_enrolment" value="<fmt:formatDate value="${customer.time_enrolment}" pattern="yyyy-MM-dd"/>"></td>
        </tr>
        <tr>
          <td>classNum:</td>
          <td><input type="text" name="classNum" id="classNum" value="${customer.classNum}"></td>
        </tr> 
        <tr>
          <td>tel</td>
          <td><input type="text" name="tel" id="tel" value="${customer.tel}"></td>
        </tr>
        <tr>
          <td>ImagePath:</td>
          <td><input type="text" name="ImagePath" id="ImagePath" value="${customer.pImage }"></td>
        </tr>
        <tr>
          <td>pImage:</td>
          <img alt="" src="<c:url value='${customer.pImage }' />" />
          <td><input type="file" name="pImage" id="pImage"></td>
        </tr> 
        <tr>
          <td colspan="2" align="center"><input type="submit"></td>
        </tr>
      </table>
    </form>
  </body>
</html>
