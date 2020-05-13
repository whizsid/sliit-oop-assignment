<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/admin/search.css">
<div>
    <div class="table-wrapper">
        <form method="POST">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
                        <h2>Update <b>User</b></h2>
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
                    <input name="name" type="text" value="${name}" class="form-control" placeholder="Name">
                </div>
                <div class="col">
                    <input name="email" type="email" value="${email}" class="form-control" placeholder="Email">
                </div>
            </div>
            <div class="row pt-4">
                <div class="col">
                    <select name="userType" class="form-control" >
                        <option value="0" >User Type</option>
                        <c:forEach items="${userTypes}" var="type">
                            <option ${userType==type.getId()?"selected":""} value="${type.getId()}">${type.getName()}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col">
                    <input name="password" type="password" class="form-control" placeholder="Password">
                </div>
            </div>
        </form>
    </div>
</div>
<%@ include file="../layout/footer.jsp" %>