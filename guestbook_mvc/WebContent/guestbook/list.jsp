<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, com.ssafy.guestbook.model.*"%>
<%@ include file="/template/header.jsp" %>
<%
MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
if(memberDto==null){
	%>
	<script>
	alert("로그인 사용자만 볼 수 있는 페이지입니다.");
	location.href = "<%=root%>/user?act=mvlogin";
	</script>
	<%
} else {
%>
    <style>
        mark.sky {
            background: linear-gradient(to top, #54fff9 20%, transparent 30%);
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#mvRegisterBtn").click(function () {
                location.href = "<%= root %>/article?act=mvregister";
            });
        });
    </script>

    <div class="container text-center mt-3">
        <div class="col-lg-8 mx-auto">
        <%@ include file="/template/confirm.jsp" %>
            <h2 class="p-3 mb-3 shadow bg-light"><mark class="sky">글목록</mark></h2>
            <div class="m-3 text-right">
                <button type="button" id="mvRegisterBtn" class="btn btn-link">글작성</button>
            </div>
<%
List<GuestBookDto> list = (List<GuestBookDto>) request.getAttribute("articles");
if(list.size() != 0) {
	for(GuestBookDto guestBookDto : list) {
%>          
            <table class="table table-active text-left">
                <tbody>
                    <tr class="table-info">
                        <td>작성자 : <%= guestBookDto.getUserName() %></td>
                        <td class="text-right">작성일 : <%= guestBookDto.getRegTime() %></td>
                    </tr>
                    <tr>
                        <td colspan="2" class="table-danger">
                            <strong><%= guestBookDto.getArticleNo() %>. <%= guestBookDto.getSubject() %></strong>
                        </td>
                    </tr>
                    <tr>
                        <td class="p-4" colspan="2"><%= guestBookDto.getContent() %></td>
                    </tr>
                </tbody>
            </table>
<%
	}
} else {
%>
			<table class="table table-active text-left">
                    <tr class="table-info">
                        <td colspan="2">작성한 글이 없습니다.</td>
                    </tr>
            </table>
<%
}
%>         
        </div>
    </div>
    <%} %>
<%@ include file="/template/footer.jsp" %>