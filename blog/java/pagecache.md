公共地方创建变量
session.setAttribute("version",Math.random());
js文件引用的时候获取即可
<script src="${ss }/static/js/page-common.js?v=<%= session.getAttribute("version")%>"></script>