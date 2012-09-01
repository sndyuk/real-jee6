<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<body>
	<table padding="4">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Created date</th>
		</tr>
		<c:forEach var="user" items="${users}">
			<tr>
				<td><c:out value="${user.id}" /></td>
				<td><c:out value="${user.name}" /></td>
				<td><fmt:formatDate dateStyle="short" value="${user.createDatetime}" /></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>