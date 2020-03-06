<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<%@include file="../../include/head.jsp" %>

<body class="hold-transition skin-blue sidebar-mini layout-boxed">
<div class="wrapper">

    <!-- Main Header -->
    <%@include file="../../include/main_header.jsp" %>

    <!-- Left side column. contains the logo and sidebar -->
    <%@include file="../../include/left_column.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                게시판
                <small>목록페이지</small>
            </h1>
            <ol class="breadcrumb">
                <li><i class="fa fa-edit"></i> article</li>
                <li class="active"><a href="${path}/article/list"> list</a></li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content container-fluid">

            <!--------------------------
              | Your Page Content Here |
              -------------------------->
            <div class="col-lg-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">게시글 목록</h3>
                    </div>
                    <div class="box-body">
                        <table class="table table-bordered">
                            <tbody>
                            <tr>
                                <th style="width: 30px">#</th>
                                <th>제목</th>
                                <th style="width: 100px">작성자</th>
                                <th style="width: 150px">작성시간</th>
                                <th style="width: 60px">조회</th>
                            </tr>
                            <c:forEach items="${articles}" var="article">
                                <tr>
                                    <td>${article.articleNo}</td>
                                        <%--                                    <td><a href="${path}/article/read?articleNo=${article.articleNo}">${article.title}</a></td>--%>
                                    <td>
                                        <a href="${path}/article/read${pageMaker.makeQuery(pageMaker.criteria.page)}&articleNo=${article.articleNo}">${article.title}</a>
                                    </td>
                                    <td>${article.writer}</td>
                                    <td><fmt:formatDate value="${article.regDate}" pattern="yyyy-MM-dd a HH:mm"/></td>
                                    <td><span class="badge bg-red">${article.viewCnt}</span></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="box-footer">
                        <div class="text-center">
                            <form id="listPageForm">
                                <input type="hidden" name="page" value="${pageMaker.criteria.page}">
                                <input type="hidden" name="perPageNum" value="${pageMaker.criteria.perPageNum}">
                            </form>
                            <ul class="pagination">

                                <%--                                &lt;%&ndash; JSTL의 c if 조건문을 통해 '이전' 링크 활성/비활성 처리 &ndash;%&gt;--%>
                                <c:if test="${pageMaker.prev}">
                                    <%--                                    <li><a href="${path}/article/listPaging?page=${pageMaker.startPage - 1}">이전</a></li>--%>
                                    <%--                                    <li><a href="${path}/article/listPaging${pageMaker.makeQuery(pageMaker.startPage -1)}">이전</a></li>--%>
                                    <li><a href="${pageMaker.startPage - 1}">이전</a></li>
                                </c:if>
                                <%--                                &lt;%&ndash; c:forEach 반복문을 통해 pageMaker 클래스에서 계산된 페이지 번호를 출력 &ndash;%&gt;--%>
                                <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
                                    <li <c:out value="${pageMaker.criteria.page == idx ? 'class=active' : ''}"/>>
                                            <%--                                    &lt;%&ndash; c out에서 삼항연산자를 통해 li 태그의 속성을 제어하여 페이지 번호들 중에서 현재 페이지 번호임을 알 수 있도록 색 변경 &ndash;%&gt;--%>
                                            <%--                                        <a href="${path}/article/listPaging?page=${idx}">${idx}</a>--%>
                                            <%--&lt;%&ndash;                                                <a href="${path}/article/listPaging${pageMaker.makeQuery(idx)}">${idx}</a>&ndash;%&gt;--%>
                                        <a href="${idx}">${idx}</a>
                                    </li>
                                </c:forEach>
                                <%--                                &lt;%&ndash; JSTL의 c if 조건문을 통해 '다음' 링크 활성/비활성 처리 &ndash;%&gt;--%>
                                <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
                                    <%--                                        <li><a href="${path}/article/listPaging?page=${pageMaker.endPage + 1}">다음</a></li>--%>
                                    <%--                                        <li><a href="${path}/article/listPaging?${pageMaker.makeQuery(pageMaker.endPage +1)}">다음</a></li>--%>
                                    <li><a href="${pageMaker.endPage + 1}">다음</a></li>
                                </c:if>
                            </ul>
                        </div>
                    </div>
                    <div class="box-footer">
                        <div class="pull-right">
                            <button type="button" class="btn btn-success btn-flat" id="writeBtn">
                                <i class="fa fa-pencil"></i>글쓰기
                            </button>
                        </div>
                    </div>
                </div>
            </div>


        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- Main Footer -->
    <%@include file="../../include/main_footer.jsp" %>

</div>
<!-- ./wrapper -->
<%@ include file="../../include/plugin_js.jsp" %>
<!-- modelAttribute 값 가져오기
        ArticleContorller 단에서 model.addAttribute("article", articleService.listAll());
        -->
<%--<script type="text/javascript">--%>
<%--    var OK1 = "${articles}";--%>
<%--    alert(OK1);--%>
<%--    var OK2 = "${pageMaker}";--%>
<%--    alert(OK2);--%>
<%--</script>--%>
<script>
    var result = "${msg}";
    if (result == "regSuccess") {
        alert("게시글 등록이 완료되었습니다.");
    } else if (result == "modSuccess") {
        alert("게시글 수정이 완료되었습니다.");
    } else if (result == "delSuccess") {
        alert("게시글 삭제가 완료되었습니다.");
    }

    $(".pagination li a").on("click", function (event) {
        event.preventDefault();
        var targetPage = $(this).attr("href");
        var listPageForm = $("#listPageForm");
        listPageForm.find("[name='page']").val(targetPage);
        listPageForm.attr("action", "/article/listPaging").attr("method", "get");
        listPageForm.submit();
    });
</script>


</body>
</html>