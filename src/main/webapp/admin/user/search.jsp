<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/admin/search.css">
<div>
    <div class="table-wrapper">
        <div class="table-title">
            <div class="row">
                <div class="col-sm-6">
                    <h2>Manage <b>Users</b></h2>
                </div>
                <div class="col-sm-6">
                    <a href="${pageContext.request.contextPath}/admin/user/create" class="btn btn-success"><i class="fa fa-plus"></i> <span>Add New User</span></a>
                    <a href="#deleteEmployeeModal" class="btn btn-danger"><i class="fa fa-ban"></i> <span>Delete</span></a>						
                </div>
            </div>
        </div>
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>
                        <span class="custom-checkbox">
                            <input type="checkbox" id="selectAll">
                            <label for="selectAll"></label>
                        </span>
                    </th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Type</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach varStatus="loop" items="${results}" var="user">
                    <tr>
                        <td>
                            <span class="custom-checkbox">
                                <input type="checkbox" id="checkbox${loop.index}" class="checkbox" name="options[]" value="${user.getId()}">
                                <label for="checkbox${loop.index}"></label>
                            </span>
                        </td>
                        <td>${user.getName()}</td>
                        <td>${user.getEmail()}</td>
                        <td>${user.getType()!=null? user.getType().getName() : "N/A"}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/user/update?userId=${user.getId()}" class="search-action"><i class="fa fa-edit"></i></a>
                            <a href="${pageContext.request.contextPath}/admin/user/delete?userId=${user.getId()}" class="search-action"><i class="fa fa-ban"></i></a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="clearfix">
            <div class="hint-text">Showing <b>${ perPage * page > count? count - (perPage * (page-1)) : perPage}</b> out of <b>${count}</b> entries</div>
            <ul class="pagination">
                <li class='page-item ${page -1 >= 1? "":"disabled" }'><a href="#">Previous</a></li>
                <c:forEach begin="${(page-2) < 1 ? 1 : (page - 2)}" end="${page+2}" var="index">
                    <c:if test="${ index >= 1 && index <= Math.ceil(count/perPage) }">
                        <li class='page-item ${index==page? "active" : ""}'>
                            <a href="${pageContext.request.contextPath}/admin/user/search?page=${index}&perPage=${perPage}" class="page-link">${index}</a>
                        </li>
                    </c:if>
                </c:forEach>
                <li class='page-item ${page + 1 <= Math.ceil(count/perPage)? "":"disabled" }'><a href="#" class="page-link">Next</a></li>
            </ul>
        </div>
    </div>
</div>
<%@ include file="../layout/footer.jsp" %>
