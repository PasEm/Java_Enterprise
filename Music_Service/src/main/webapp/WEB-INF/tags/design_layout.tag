<%@tag description="Design template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<html>
<body>
<div id="header">
    <jsp:invoke fragment="header"/>
</div>
<div id="body_area">
    <jsp:doBody/>
</div>
<div id="footer_area">
    <jsp:invoke fragment="footer"/>
</div>
</body>
</html>