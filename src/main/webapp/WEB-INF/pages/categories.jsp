<jsp:include page="header.jsp"  flush="true"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">

    <h3 class="booking-title">Categories</h3>
    <form class="booking-item-dates-change mb30">
        <input type="hidden" name="sortBy" value=""/>
        <input type="hidden" name="order" value=""/>
        <div class="row">
            <div class="col-md-3">
                <div class="form-group form-group-icon-left"><i class="fa fa-map-marker input-icon input-icon-hightlight"></i>
                    <label>Name</label>
                    <input class="typeahead form-control" name="searchName" value="${param.searchName}" placeholder="" type="text" />
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
                                   onClick="window.location='/admin/categories?searchName=${param.searchName}&sortBy=${param.sortBy}&order=${param.order}&page=${p}';">
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
                                <c:when test="${param.sortBy=='name' && param.order=='asc'}">
                                    Name
                                </c:when>
                                <c:otherwise>
                                    Id (low to high)
                                </c:otherwise>
                            </c:choose>
                            <i class="fa fa-angle-down"></i>
                            <i class="fa fa-angle-up"></i>
                        </a></h5>
                    <ul class="nav-drop-menu">
                        <li onClick="window.location='/admin/categories?searchName=${param.searchName}&sortBy=id&order=asc';"><a href="#">Id (low to high)</a>
                        </li>
                        <li onClick="window.location='/admin/categories?searchName=${param.searchName}&sortBy=name&order=asc';"><a href="#">Name</a>
                        </li>
                    </ul>
                </div>
                <a class="btn btn-primary" href="/admin/categories/new">Add new</a>
                <ul class="booking-list">
                    <c:if test="${empty categories}">
                        We have not found any category yet. Please try again.
                    </c:if>

                    <c:forEach items="${categories}" var="category">
                        <li>
                            <div class="booking-item-container">
                                <div class="booking-item">
                                    <div class="row">
                                        <div class="col-md-2">
                                            <div class="booking-item-airline-logo">
                                                ${category.id}
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="booking-item-flight-details">
                                                <div class="booking-item-arrival">
                                                    <p class="booking-item-date" style="font-size: 14px;!important">${category.name}</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-2">
                                            <a class="btn btn-primary" href="/admin/categories/update/${category.id}">Edit</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                            <a class="btn btn-primary" href="#"
                                               onClick="javascript: if (confirm('Are you sure you want to delete this category?')) window.location='/admin/categories/delete/${category.id}';">Delete</a>
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