<jsp:include page="header.jsp" flush="true"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="container">

    <h3 class="booking-title">Orders</h3>
    <div id="message" style="color: navy!important;"></div>

    <form class="booking-item-dates-change mb30">
        <input type="hidden" name="sortBy" value=""/>
        <input type="hidden" name="order" value=""/>
        <div class="row">
            <div class="col-md-3">
                <div class="form-group form-group-icon-left"><i
                        class="fa fa-map-marker input-icon input-icon-hightlight"></i>
                    <label>Order id</label>
                    <input class="typeahead form-control" value="${searchId}" placeholder="" name="searchId"
                           type="text"/>
                </div>
            </div>
            <div class="col-md-3">
                <div class="form-group form-group-icon-left"><i
                        class="fa fa-map-marker input-icon input-icon-hightlight"></i>
                    <label>Customer Name</label>
                    <input class="typeahead form-control" value="${searchName}" placeholder="" name="searchName"
                           type="text"/>
                </div>
            </div>
            <div class="col-md-2">
                <div class="form-group form-group-icon-left"><i
                        class="fa fa-calendar input-icon input-icon-hightlight"></i>
                    <label>Customer E-Mail</label>
                    <input class="typeahead form-control" value="${searchEmail}" type="text" name="searchEmail"/>
                </div>
            </div>
            <div class="col-md-2">
                <div class="form-group form-group-icon-left">
                    <label><br/></label>
                    <input type="submit" value="SEARCH" style="font-size: 14px; padding: 6px 12px;"/>
                </div>
            </div>
            <div class="col-md-2">
                <div class="form-group form-group-select-plus">
                    <label>Paging</label>
                    <div class="btn-group btn-group-select-num" data-toggle="buttons">
                        <c:forEach begin="${startpage}" end="${endpage}" var="p">
                            <label class="btn btn-primary <c:if test="${currentPage == p}">active</c:if>"
                                   onClick="window.location='/admin/orders?searchId=${searchId}&searchName=${searchName}&searchEmail=${searchEmail}&sortBy=${param.sortBy}&order=${param.order}&page=${p}';">
                                <input type="radio" name="options"/>${p}</label>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <div class="row">

        <div class="col-md-12">
            <div class="nav-drop booking-sort">
                <h5 class="booking-sort-title"><a href="#">Sort: <c:choose>
                    <c:when test="${param.sortBy=='id' && param.order=='asc'}">
                        Id (low to high)
                    </c:when>
                    <c:when test="${param.sortBy=='name' && param.order=='asc'}">
                        Name (low to high)
                    </c:when>
                    <c:when test="${param.sortBy=='name' && param.order=='desc'}">
                        Name (high to low)
                    </c:when>
                    <c:when test="${param.sortBy=='total' && param.order=='asc'}">
                        Total (low to high)
                    </c:when>
                    <c:when test="${param.sortBy=='total' && param.order=='desc'}">
                        Total (high to low)
                    </c:when>
                    <c:otherwise>
                        Id (high to low)
                    </c:otherwise>
                </c:choose>
                    <i class="fa fa-angle-down"></i>
                    <i class="fa fa-angle-up"></i>
                </a></h5>
                <ul class="nav-drop-menu">
                    <li onClick="window.location='/admin/orders?searchId=${searchId}&searchName=${searchName}&searchEmail=${searchEmail}&sortBy=id&order=asc';">
                        <a href="">Id (low to high)</a>
                    </li>
                    <li onClick="window.location='/admin/orders?searchId=${searchId}&searchName=${searchName}&searchEmail=${searchEmail}&sortBy=name&order=asc';">
                        <a href="">Name (low to high)</a>
                    </li>
                    <li onClick="window.location='/admin/orders?searchId=${searchId}&searchName=${searchName}&searchEmail=${searchEmail}&sortBy=name&order=desc';">
                        <a href="#">Name (high to low)</a>
                    </li>
                    <li onClick="window.location='/admin/orders?searchId=${searchId}&searchName=${searchName}&searchEmail=${searchEmail}&sortBy=total&order=asc';">
                        <a href="#">Total (low to high)</a>
                    <li onClick="window.location='/admin/orders?searchId=${searchId}&searchName=${searchName}&searchEmail=${searchEmail}&sortBy=total&order=desc';">
                        <a href="#">Total (high to low)</a>
                    </li>
                </ul>
            </div>
            <ul class="booking-list">
                <c:if test="${empty orders}">
                    We have not found any order yet. Please try again.
                </c:if>
                <c:forEach items="${orders}" var="order">
                    <li id="li${order.id}">
                        <div class="booking-item-container">
                            <div class="booking-item">
                                <div class="row">
                                    <div class="col-md-1">${order.id}</div>
                                    <div class="col-md-3">
                                            ${order.firstName} ${order.lastName}
                                    </div>
                                    <div class="col-md-2" nowrap>
                                        <i class="fa fa-phone"></i>&nbsp;${order.phone}<br/>
                                        <i class="fa fa-envelope"></i>&nbsp;${order.email}
                                    </div>
                                    <div class="col-md-1">
                                        $${order.total}
                                    </div>
                                    <div class="col-md-1">
                                        <fmt:formatDate pattern="MM/dd/yyyy" value="${order.dateAdded}"/>
                                    </div>
                                    <div class="col-md-1">
                                            ${order.status}
                                    </div>
                                    <div class="col-md-2">
                                        <a class="btn btn-primary" href="/admin/orders/view/${order.id}">View</a>&nbsp;&nbsp;&nbsp;
                                        <a class="btn btn-primary" href="#"
                                           onClick="javascript: if (confirm('Are you sure you want to delete this order?')) delete_order(${order.id});">Delete</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>
                </c:forEach>

            </ul>

            </p>
        </div>
    </div>
    <div class="gap"></div>
</div>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
    function delete_order(id) {
        var formData = {}
        formData['id'] = id;
        $.ajax({
            url: '/admin/orders/delete',
            dataType: 'json',
            type: "post",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(formData),
            success: function (data) {
                console.log('success');
                console.log(data);
                $("#message").html(data.message);
                $("#li" + id).hide();
            }
        });
    }
</script>
<jsp:include page="footer.jsp" flush="true"></jsp:include>