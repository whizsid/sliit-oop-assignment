<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/admin/search.css">
<div>
    <div class="table-wrapper">
        <form method="POST">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
                        <h2>Create <b>Songs</b></h2>
                    </div>
                    <div class="col-sm-6">
                        <button type="submit" class="btn btn-success">
                            <i class="fa fa-plus"></i>
                            <span>Success</span>
                        </button>
                        <a href="${pageContext.request.contextPath}/admin/user/search" class="btn btn-danger"><i
                                class="fa fa-ban"></i> <span>Cancel</span></a>
                    </div>
                </div>
            </div>
            <c:if test="${error!=null}">
                <div class="alert alert-danger">
                    ${error}
                </div>
            </c:if>
            <c:if test="${success!=null}">
                <div class="alert alert-success">
                    ${success}
                </div>
            </c:if>
            <div class="row">
                <div class="col">
                    <input name="name" type="text" class="form-control" placeholder="Name">
                </div>
                <div class="col">
                    <select name="user" class="form-control" >
                        <option value="0" >User</option>
                        <c:forEach items="${users}" var="user">
                            <option value="${user.getId()}">${user.getName()}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="row pt-4">
                <div class="col">
                    <select name="genre" class="form-control" >
                        <option value="0" >Genre</option>
                        <c:forEach items="${genres}" var="genre">
                            <option value="${genre.getId()}">${genre.getName()}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col">
                    <input name="file" type="file" class="form-control">
                </div>
            </div>
        </form>
    </div>
</div>
<%@ include file="../layout/footer.jsp" %>