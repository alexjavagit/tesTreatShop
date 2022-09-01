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
            <p id="message" style="display: none; color: red;">Error! Please contact administrator!</p>
            <ul class="booking-list">
            <c:forEach items="${products}" var="product">
                <li id="li_${product.id}_${product.cartSize}">
                        <div class="row">
                            <div class="col-md-3">
                                <div class="booking-item-img-wrap">
                                    <a class="hover-img" href="/product/${product.id}">
                                        <img src="data:;base64,${product.productImages[0].image}" alt="${product.name}" title="${product.name}" /></a>
                                    <div class="booking-item-img-num"></div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <h5 class="booking-item-title"><a class="hover-img" href="/product/${product.id}">${product.name}</a></h5>
                                <p class="booking-item-address" style="font-size: 16px;!important;">Size: ${product.cartSize}</p>
                                <p class="booking-item-address" style="font-size: 16px;!important;">Price: $${product.price}</p>
                                <p class="booking-item-address" style="font-size: 16px;!important;">Qty:
                                    <a href="#" onClick="javascript: removeProductQtyFromCart(${product.id},'${product.cartSize}');" class="fa fa-minus-circle" style="font-size: 18px;!important;"></a> <span id="qty_${product.id}_${product.cartSize}">${product.cartQty}</span> <a href="#" onClick="javascript: addProductQtyToCart(${product.id},'${product.cartSize}')" class="fa fa-plus-circle" style="font-size: 18px;!important;"></a>
                                </p>
                            </div>
                            <div class="booking-item-title">$${product.price}</div>
                        </div>
                </li>
                <c:set var = "total" value = "${total + product.price}"/>
            </c:forEach>
                <li id="totalli" <c:if test="${products.size() eq 0}">style="display: none"</c:if>>
                    <div class="row">
                        <div class="col-md-9"></div>
                        <div class="booking-item-title" id="total"><b>Total: $${total}</b></div>
                    </div>
                </li>
                <li id="checkoutli" <c:if test="${products.size() eq 0}">style="display: none"</c:if>>
                    <div class="row">
                        <div class="col-md-9"></div>
                        <div class="booking-item-title"><a href="/shoppingCart/checkout" class="btn btn-primary btn-lg">Checkout >></a></div>
                    </div>
                </li>
            </ul>
            <c:if test="${products.size() eq 0}">
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

<script>
    function removeProductQtyFromCart(pid) {

    }

    function addProductQtyToCart(pid, size) {
        var formData = {}
        formData['id'] = pid;
        formData['size'] = size;
        $.ajax({
            url:'/shoppingCart/addProduct',
            contentType: "application/json; charset=utf-8",
            data : JSON.stringify(formData),
            dataType : 'json',
            type: "POST",
            success: function (data) {
                console.log(data);
                if (data.message = 'OK') {
                    $('#qty_' + pid + "_" + size).text(data.qty);
                    $("#message").hide();
                } else {
                    $("#message").text(data.message);
                    $("#message").show();
                }
            }
        });
    }

    function removeProductQtyFromCart(pid, size) {
        var formData = {}
        formData['id'] = pid;
        formData['size'] = size;
        $.ajax({
            url:'/shoppingCart/removeProduct',
            contentType: "application/json; charset=utf-8",
            data : JSON.stringify(formData),
            dataType : 'json',
            type: "POST",
            success: function (data) {
                console.log(data);
                if (data.message = 'OK') {
                    if (data.qty == 0) {
                        $('#li_'+pid+'_'+size).hide();
                    } else {
                        $('#qty_' + pid + "_" + size).text(data.qty);
                    }
                    $("#message").hide();

                    //$("#totalli").hide();
                    //$("#checkoutli").hide();
                } else {
                    $("#message").text(data.message);
                    $("#message").show();
                }
            }
        });
    }
</script>




<jsp:include page="footer.jsp"  flush="true"></jsp:include>