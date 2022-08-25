<jsp:include page="header.jsp"  flush="true"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">

    <h3 class="booking-title">Users</h3>
    <form class="booking-item-dates-change mb30">
        <input type="hidden" name="sortBy" value=""/>
        <input type="hidden" name="order" value=""/>
        <div class="row">
            <div class="col-md-3">
                <div class="form-group form-group-icon-left"><i class="fa fa-map-marker input-icon input-icon-hightlight"></i>
                    <label>E-Mail</label>
                    <input class="typeahead form-control" name="searchEmail" value="${param.searchEmail}" placeholder="" type="text" />
                </div>
            </div>
            <div class="col-md-3">
                <div class="form-group form-group-icon-left"><i class="fa fa-map-marker input-icon input-icon-hightlight"></i>
                    <label>Login</label>
                    <input class="typeahead form-control" name="searchLogin" value="${param.searchLogin}" placeholder="" type="text" />
                </div>
            </div>
            <div class="col-md-2">
                <div class="form-group form-group-icon-left">
                    <label><br/></label>
                    <input type="submit" value="SEARCH" style="font-size: 14px; padding: 6px 12px;" />
                </div>
            </div>
            <div class="col-md-2">
                <div class="form-group form-group-select-plus">
                    <label>Paging</label>
                    <div class="btn-group btn-group-select-num" data-toggle="buttons">
                        <c:forEach begin="${startpage}" end="${endpage}" var="p">
                            <label class="btn btn-primary <c:if test="${currentPage == p}">active</c:if>"
                                   onClick="window.location='/admin/users?searchLogin=${param.searchLogin}&searchName=${param.searchName}&sortBy=${param.sortBy}&order=${param.order}&page=${p}';">
                                <input type="radio" name="options"  />${p}</label>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">

            <div class="col-md-9" style="width: 100%!important">
                <div class="nav-drop booking-sort">
                    <h5 class="booking-sort-title">
                        <a href="#">
                            Sort:
                            <c:choose>
                                <c:when test="${param.sortBy=='firstName' && param.order=='asc'}">
                                    First Name
                                </c:when>
                                <c:when test="${param.sortBy=='login' && param.order=='asc'}">
                                    Login
                                </c:when>
                                <c:otherwise>
                                    Id (low to high)
                                </c:otherwise>
                            </c:choose>
                            <i class="fa fa-angle-down"></i>
                            <i class="fa fa-angle-up"></i>
                        </a></h5>
                    <ul class="nav-drop-menu">
                        <li onClick="window.location='/admin/users?searchEmail=${param.searchEmail}&searchLogin=${param.searchLogin}&sortBy=id&order=asc';"><a href="">Id (low to high)</a>
                        </li>
                        <li onClick="window.location='/admin/users?searchEmail=${param.searchEmail}&searchLogin=${param.searchLogin}&sortBy=firstName&order=asc';"><a href="">Name</a>
                        </li>
                        <li onClick="window.location='/admin/users?searchEmail=${param.searchEmail}&searchLogin=${param.searchLogin}&sortBy=login&order=asc';"><a href="#">Login</a>
                        </li>
                    </ul>
                </div>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="btn btn-primary" href="/admin/users/new">Add new</a>&nbsp;
                <ul class="booking-list">
                    <c:if test="${empty users}">
                        We have not found any user yet. Please try again.
                    </c:if>

                    <c:forEach items="${users}" var="user">
                        <li>
                            <div class="booking-item-container">
                                <div class="booking-item">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="booking-item-airline-logo">
                                                <p class="booking-item-date" style="font-size: 14px;!important">${user.firstName} ${user.lastName}</p>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="booking-item-flight-details">
                                                <div class="booking-item-arrival">
                                                    <p class="booking-item-date" style="font-size: 14px;!important">${user.email}</p>
                                                </div>
                                                <div class="booking-item-departure">
                                                    <p class="booking-item-date" style="font-size: 14px;!important">${user.login}</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-2">
                                            <a class="btn btn-primary" href="/admin/users/update/${user.id}">Edit</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                            <c:if test="${user.id != 1}">
                                            <a class="btn btn-primary" href="#"
                                               onClick="javascript: if (confirm('Are you sure you want to delete this user?')) window.location='/admin/users/delete/${user.id}';">Delete</a>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </c:forEach>

                </ul>
            </div>
        </div>
        <div class="gap"></div>
    </form>
</div>

<jsp:include page="footer.jsp"  flush="true"></jsp:include>