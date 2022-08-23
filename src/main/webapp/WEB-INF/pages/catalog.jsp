<jsp:include page="header.jsp" flush="true"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty param.search_for}">
<div class="container">
    <h3 class="page-title">Search results</h3>
</div>
</c:if>
<div class="container">
<c:if test="${not empty param.search_for}">
    <form method="get">
        <div class="row">
            <div class="col-md-4">
                <div class="">
                    <input class="typeahead form-control" name="search_for" type="text" value="${param.search_for}" />
                    <input class="btn btn-primary mt10" type="submit" value="Search" />
                </div>
            </div>
        </div>
    </form>
</c:if>
    <div class="gap gap-small"></div>
    <div class="row row-wrap">
        <c:if test="${empty products}">
            We have not found any product for your search term. Please try again.
        </c:if>
        <c:forEach items="${products}" var="product">
        <div class="col-md-3">
            <div class="thumb">
                <header class="thumb-header">
                    <a class="hover-img" href="/product/${product.id}">
                        <img src="data:;base64,${product.productImages[0].image}" alt="${product.name}" title="${product.name}" />
                    </a>
                </header>
                <div class="thumb-caption">
                    <h5 class="thumb-title"><a class="text-darken" href="#">${product.name}</a></h5>
                    <p class="mb0"><small><i class="fa fa-map-marker"></i> ${product.category.name}</small>
                    </p>
                    <p class="mb0 text-darken"><span class="text-lg lh1em text-color">$${product.price}</span><small> </small>
                    </p>
                </div>
            </div>
        </div>
        </c:forEach>

    </div>
    <div class="gap gap-small"></div>
</div>

<jsp:include page="footer.jsp"  flush="true"></jsp:include>