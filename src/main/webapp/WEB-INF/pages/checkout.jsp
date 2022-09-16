<jsp:include page="header.jsp" flush="true"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <ul class="breadcrumb">
        <li><a href="/">Home</a>
        </li>
        <li><a href="#">Checkout</a>
        </li>
    </ul>
    <h3 class="booking-title">Ordering</h3>
    <div class="row">
        <c:if test="${products.size() ne 0}">
            <div class="col-md-5">
                <p style="color: ${errorMessageColor}">${errorMessage}</p>
                <form action="/shoppingCart/checkout" method="POST" modelAttribute="formData">
                    <div class="form-group form-group-icon-left"><i
                            class="fa fa-customUser input-icon input-icon-show"></i>
                        <label>Shipping address *</label>
                        <textarea name="shippingAddress" class="form-control" required></textarea>
                    </div>
                    <div class="form-group form-group-icon-left"><i
                            class="fa fa-customUser input-icon input-icon-show"></i>
                        <label>First Name *</label>
                        <input class="form-control" value="${customUser.firstName}" type="text" name="firstName"
                               required/>
                    </div>
                    <div class="form-group form-group-icon-left"><i
                            class="fa fa-customUser input-icon input-icon-show"></i>
                        <label>Last Name *</label>
                        <input class="form-control" value="${customUser.lastName}" type="text" name="lastName"
                               required/>
                    </div>
                    <div class="form-group form-group-icon-left"><i
                            class="fa fa-envelope input-icon input-icon-show"></i>
                        <label>Email *</label>
                        <input class="form-control" value="${customUser.email}" type="text" name="email" required
                               pattern="[^@]+@[^@]+\.[a-zA-Z]{2,6}"/>
                    </div>
                    <div class="form-group form-group-icon-left"><i
                            class="fa fa-envelope input-icon input-icon-show"></i>
                        <label>Phone *</label>
                        <input class="form-control" type="text" id="phone" name="phone" value="${customUser.phone}"
                               required pattern="\([0-9]{3}\) [0-9]{3}[-][0-9]{4}"/>
                    </div>

                    <input id="submit" class="btn btn-primary" type="submit" value=" SUBMIT ORDER "/>
                </form>
            </div>
        </c:if>
        <div class="col-md-4">

            <ul class="booking-list">
                <c:forEach items="${products.entrySet()}" var="product">
                    <c:if test="${product.getValue().discount > 0}">
                        <c:set var="dPrice" value="${product.getValue().price - product.getValue().price*(product.getValue().discount/100)}"/>
                    </c:if>
                    <c:if test="${product.getValue().discount == 0}">
                        <c:set var="dPrice" value="${product.getValue().price}"/>
                    </c:if>
                    <li id="li_${product.getKey()}">
                        <div class="row">
                            <div class="col-md-3">
                                <div class="booking-item-img-wrap">
                                    <a class="hover-img" href="/product/${product.getValue().id}">
                                        <img src="data:;base64,${product.getValue().productImages[0].image}"
                                             alt="${product.getValue().name}" title="${product.getValue().name}"/></a>
                                    <div class="booking-item-img-num"></div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <h5 class="booking-item-title"><a class="hover-img"
                                                                  href="/product/${product.getValue().id}">${product.getValue().name}</a>
                                </h5>
                                <p class="booking-item-address" style="font-size: 16px;!important;">
                                    Size: ${productsSizes.get(product.getKey())}</p>
                                <p class="booking-item-address" style="font-size: 16px;!important;">Price:
                                    $${dPrice.intValue()}</p>
                                <p class="booking-item-address" style="font-size: 16px;!important;">
                                    Qty: ${productsQty.get(product.getKey())}
                                </p>
                            </div>
                            <div class="booking-item-title" id="subtotal_${product.getKey()}">
                                $${dPrice.intValue()*productsQty.get(product.getKey())}</div>
                        </div>
                    </li>
                    <c:set var = "total" value = "${total + dPrice.intValue()*productsQty.get(product.getKey())}"/>
                </c:forEach>
                <li id="totalli" <c:if test="${products.size() eq 0}">style="display: none"</c:if>>
                    <div class="row">
                        <div class="col-md-9"></div>
                        <div class="booking-item-title"><b>Total: $<span id="total">${total}</span></b></div>
                    </div>
                </li>

            </ul>
            <c:if test="${products.size() eq 0}">
                <div class="row" id="cart_empty">
                    <div class="col-md-6 text-center">
                        <p>Your shopping cart is empty.</p>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
    <div class="gap"></div>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://rawgit.com/RobinHerbots/jquery.inputmask/3.x/dist/jquery.inputmask.bundle.js"></script>
<script>
    $('#phone').inputmask("(999) 999-9999");
</script>


<jsp:include page="footer.jsp" flush="true"></jsp:include>