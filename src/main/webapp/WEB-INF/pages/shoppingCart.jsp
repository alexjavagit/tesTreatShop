<jsp:include page="header.jsp" flush="true"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <ul class="breadcrumb">
        <li><a href="/">Home</a>
        </li>
        <li><a href="#">Shopping cart</a>
        </li>
    </ul>
    <h3 class="booking-title">Shopping cart</h3>
    <div class="row">

        <div class="col-md-9">
            <ul class="booking-list">
            <c:forEach items="${products.entrySet()}" var="product">
                <li>
                        <div class="row">
                            <div class="col-md-3">
                                <div class="booking-item-img-wrap">
                                    <a class="hover-img" href="/product/${product.getKey().id}">
                                        <img src="data:;base64,${product.getKey().productImages[0].image}" alt="${product.getKey().name}" title="${product.getKey().name}" /></a>
                                    <div class="booking-item-img-num"></div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <h5 class="booking-item-title"><a class="hover-img" href="/product/${product.getKey().id}">${product.getKey().name}</a></h5>
                                <p class="booking-item-address" style="font-size: 16px;!important;">Size: ${product.getKey().selectedSize}</p>
                                <p class="booking-item-address" style="font-size: 16px;!important;">Price: $${product.getKey().price}</p>
                                <p class="booking-item-address" style="font-size: 16px;!important;">Qty:
                                    <a href="#" onClick="" class="fa fa-minus-circle" style="font-size: 18px;!important;"></a> <span id="size${product.getKey().id}${product.getKey().selectedSize}">${product.getValue()}</span> <a href="#" onClick="" class="fa fa-plus-circle" style="font-size: 18px;!important;"></a>
                                </p>
                            </div>
                            <div class="booking-item-title">$${product.getKey().price}</div>
                        </div>
                </li>
                <c:set var = "total" scope = "session" value = "${total + product.getKey().price}"/>
            </c:forEach>
                <li>
                    <div class="row">
                        <div class="col-md-3">
                            <div class="booking-item-img-wrap">
                                <div class="booking-item-img-num"></div>
                            </div>
                        </div>
                        <div class="col-md-6">

                        </div>
                        <div class="booking-item-title" id="total">Total: $<c:out value="${total}"/></div>
                    </div>
                </li>
            </ul>
            <c:if test="${products.entrySet().size() eq 0}">
            <div class="row">
                <div class="col-md-6 text-center">
                    <p>Your shopping cart is empty.</p>
                </div>
            </div>
            </c:if>
        </div>
    </div>
    <div class="gap"></div>
</div>






<jsp:include page="footer.jsp"  flush="true"></jsp:include>