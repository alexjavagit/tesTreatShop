<jsp:include page="header.jsp"  flush="true"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">

    <h3 class="booking-title">Products</h3>
    <form class="booking-item-dates-change mb30">
        <input type="hidden" name="sortBy" value=""/>
        <input type="hidden" name="order" value=""/>
        <div class="row">
            <div class="col-md-3">
                <div class="form-group form-group-icon-left"><i class="fa fa-map-marker input-icon input-icon-hightlight"></i>
                    <label>Category</label>
                    <select name="searchCategory" class="form-control">
                        <option <c:if test="${param.searchCategory == ''}"> Selected</c:if>></option>
                        <c:forEach items="${categories}" var="category">
                            <option value="${category.id}" <c:if test="${param.searchCategory == category.id}"> Selected</c:if>>${category.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
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
                               onClick="window.location='/admin/products?searchCategory=${param.searchCategory}&searchName=${param.searchName}&sortBy=${param.sortBy}&order=${param.order}&page=${p}';">
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
                        <c:when test="${param.sortBy=='price' && param.order=='desc'}">
                            Price (high to low)
                        </c:when>
                        <c:when test="${param.sortBy=='price' && param.order=='asc'}">
                            Price (low to high)
                        </c:when>
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
                    <li onClick="window.location='/admin/products?searchCategory=${param.searchCategory}&searchName=${param.searchName}&sortBy=price&order=desc';"><a href="">Price (high to low)</a>
                    </li>
                    <li onClick="window.location='/admin/products?searchCategory=${param.searchCategory}&searchName=${param.searchName}&sortBy=price&order=asc';"><a href="#">Price (low to high)</a>
                    </li>
                    <li onClick="window.location='/admin/products?searchCategory=${param.searchCategory}&searchName=${param.searchName}&sortBy=name&order=asc';"><a href="#">Name</a>
                    </li>
                </ul>
            </div>
            <ul class="booking-list">
                <c:if test="${empty products}">
                    We have not found any product yet. Please try again.
                </c:if>

                <c:forEach items="${products}" var="product">
                    <li>
                        <div class="booking-item-container">
                            <div class="booking-item">
                                <div class="row">
                                    <div class="col-md-2">
                                        <div class="booking-item-airline-logo">
                                            <img src="data:;base64,${product.productImages[0].image}" alt="${product.name}" title="${product.name}" />
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="booking-item-flight-details">
                                            <div class="booking-item-arrival">
                                                <p class="booking-item-date" style="font-size: 14px;!important">${product.category.name}</p>
                                            </div>
                                            <div class="booking-item-departure">
                                                <p class="booking-item-date" style="font-size: 14px;!important">${product.name}</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <p><b>Sizes:</b> <c:forEach items="${product.categorySizes}" var="size">${size.size} </c:forEach></p>
                                    </div>
                                    <div class="col-md-2"><span class="booking-item-price" style="font-size: 24px;!important">$${product.price}</span>
                                    </div>
                                    <div class="col-md-2">
                                        <a class="btn btn-primary" href="/admin/products/edit/${product.id}">Edit</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                        <a class="btn btn-primary" href="#"
                                           onClick="javascript: if (confirm('Are you sure you want to delete this product?')) window.location='/admin/products/delete/${product.id}';">Delete</a>
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