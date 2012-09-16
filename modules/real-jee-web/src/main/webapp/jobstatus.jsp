<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
	<h1>Job status&nbsp;&nbsp;(<a href="javascript:void(0)" onclick="openNewWindow();">-> Open new window</a>)</h1>
	<h3>
		Job id: <span id="jobId"><c:out value="${jobId}" /></span>
	</h3>
	<input id="jobStatus" type="text" /><input id="jobStatusButton" type="button" value="Send job status" onclick="putStatus()" />
	<hr />
	<table id="out" cellspacing="0"><tbody></tbody></table>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery-1.8.1.js"></script>
	<script type="text/javascript">
		var tableCapFunc = function(lines) {
			var children = lines.childNodes;
			if (children.length + 1 <= 200) {
				return;
			}
			lines.removeChild(children.item(1));
			tableCapFunc(lines);
		}
	
		$._handleJobStatus = function() {
			$.ajax({
				type : 'get',
// 				url : '${pageContext.request.contextPath}/resources/job/watch/${jobId}',
				url : '${pageContext.request.contextPath}/jobstatus?jobId=${jobId}',
				cache : false,
				dataType : 'html',
				timeout : 1000 * 60 * 60 * 1, // 1H
				beforeSend: function(xhr){
					xhr.overrideMimeType("text/html;charset=utf-8");
				},
				success: function(resText) {
					var out = document.getElementById('out');
	
					var _doc = document, scroll = false;
	
					if (window.scrollY + _doc.documentElement.clientHeight >= _doc.documentElement.scrollHeight) {
						scroll = true;
					}
	
					tableCapFunc(out);
	
					var tr = _doc.createElement('tr');
					out.appendChild(tr);
					var td = _doc.createElement( "td" );
					tr.appendChild(td);
					td.innerHTML = resText;
	
					if (scroll) {
						window.scrollTo(0, _doc.body.scrollHeight);
					}
	
					setTimeout($._handleJobStatus, 1000);
				}
			});
		}
		setTimeout($._handleJobStatus, 1000);
		$("#jobStatus").keyup(function(event){
			if (event.keyCode == 13) {
				$("#jobStatusButton").click();
			}
		});
	</script>
	<script type="text/javascript">
		var putStatus = function() {
			var jobId = document.getElementById('jobId').innerText;
			$.ajax({
				type : 'put',
				url : '${pageContext.request.contextPath}/resources/simpletask/' + jobId + '/status',
				cache : false,
				timeout : 1000 * 10, // 10sec
				contentType : 'text/plain; charset=utf-8',
				data : document.getElementById('jobStatus').value
			});
		};
		var openNewWindow = function() {
			window.open(location.href,'_blank');
		};
	</script>
</body>
</html>
