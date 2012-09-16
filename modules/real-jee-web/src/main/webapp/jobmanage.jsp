<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<body>
	<h1>Job manager</h1>
	<h3>
		1. Add new job. <input type="button" onclick="createNewJob();" value="Click" /><span id="createdJobId"></span>
	</h3>
	<h3>
		2. open job status view. <a href="javascript:void(0)" onclick="showJobStatus();">Open</a> (job id <input id="jobIdInput" type="text" maxlength="5" />)
	</h3>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.1.js"></script>
	<script type="text/javascript">
		var createNewJob = function() {
			$.ajax({
				type : 'post',
				url : '${pageContext.request.contextPath}/resources/simpletask',
				cache : false,
				dataType : 'html',
				timeout : 1000 * 60, // 1min
				success: function(resText) {
					var createdJobId = document.getElementById('createdJobId');
					createdJobId.innerText = " New job id is " + resText;
					document.getElementById('jobIdInput').value = resText;
				}
			});
		};
		var showJobStatus = function() {
			location.href = '${pageContext.request.contextPath}/showjobstatus?jobId=' + document.getElementById('jobIdInput').value;
		};
	</script>
</body>
</html>
