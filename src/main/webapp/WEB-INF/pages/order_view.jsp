<jsp:include page="header.jsp" flush="true"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <ul class="breadcrumb">
        <li><a href="/">Home</a>
        </li>
        <li><a href="/admin/orders">Orders</a>
        </li>
    </ul>
    <div id="message" style="color: navy!important;"></div>
    <form method="post" action="" id="formStatus">
        <input type="hidden" name="id" value="${shopOrder.id}"/>
        <h3 class="booking-title">Order # ${shopOrder.id}</h3>
        <div style="margin-bottom: 20px;"><label style="font-weight: bold;font-size: large;">Status : <select
                name="order_status" id="order_status">
            <c:forEach items="${orderStatuses}" var="orderStatus">
                <option value="${orderStatus.name()}"
                        <c:if test="${shopOrder.status == orderStatus.name()}">Selected</c:if>>${orderStatus.name()}</option>
            </c:forEach>
        </select>
            <input class="btn btn-primary" type="submit" value=" CHANGE "/>
        </label>
        </div>
    </form>
    <div class="row">
        <div class="col-md-5">
            <div class="form-group form-group-icon-left"><i class="fa fa-customUser input-icon input-icon-show"></i>
                <label style="font-weight: bold;font-size: large;">Shipping address: </label>
                ${shopOrder.shippingAddress}
            </div>
            <div class="form-group form-group-icon-left"><i class="fa fa-customUser input-icon input-icon-show"></i>
                <label style="font-weight: bold;font-size: large;">First Name: </label>
                ${shopOrder.firstName}
            </div>
            <div class="form-group form-group-icon-left"><i class="fa fa-customUser input-icon input-icon-show"></i>
                <label style="font-weight: bold;font-size: large;">Last Name: </label>
                ${shopOrder.lastName}
            </div>
            <div class="form-group form-group-icon-left"><i class="fa fa-envelope input-icon input-icon-show"></i>
                <label style="font-weight: bold;font-size: large;">Email: </label>
                ${shopOrder.email}
            </div>
            <div class="form-group form-group-icon-left"><i class="fa fa-envelope input-icon input-icon-show"></i>
                <label style="font-weight: bold;font-size: large;">Phone: </label>
                ${shopOrder.phone}
            </div>

        </div>
        <div class="col-md-4">

            <ul class="booking-list">
                <c:forEach items="${shopOrder.orderItems}" var="orderItem">
                    <li id="li_${orderItem.id}">
                        <div class="row">
                            <div class="col-md-3">
                                <div class="booking-item-img-wrap">
                                    <a class="hover-img" href="/product/${orderItem.product.id}">
                                        <img src="data:;base64,${orderItem.product.productImages[0].image}"
                                             alt="${orderItem.product.name}" title="${orderItem.product.name}"/></a>
                                    <div class="booking-item-img-num"></div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <h5 class="booking-item-title"><a class="hover-img"
                                                                  href="/product/${orderItem.product.id}">${orderItem.product.name}</a>
                                </h5>
                                <p class="booking-item-address" style="font-size: 16px;!important;">
                                    Size: ${orderItem.size}</p>
                                <p class="booking-item-address" style="font-size: 16px;!important;">Price:
                                    $${orderItem.price.intValue()}</p>
                                <p class="booking-item-address" style="font-size: 16px;!important;">
                                    Qty: ${orderItem.qty.intValue()}
                                </p>
                            </div>
                            <div class="booking-item-title">
                                $${orderItem.price.intValue()*orderItem.qty.intValue()}</div>
                        </div>
                    </li>
                    <c:set var="total" value="${total + orderItem.price*orderItem.qty}"/>
                </c:forEach>
                <li id="totalli">
                    <div class="row">
                        <div class="col-md-8"></div>
                        <div class="booking-item-title"><label style="font-weight: bold;font-size: large;">Total:
                            $${total.intValue()}</label></div>
                    </div>
                </li>

            </ul>
            <c:if test="${shopOrder.orderItems.size() eq 0}">
                <div class="row" id="cart_empty">
                    <div class="col-md-6 text-center">
                        <p>Empty shopOrder...</p>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
    <div class="gap"></div>
</div>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
    $("#formStatus").submit(function (event) {
        event.preventDefault();
        var formData = {}
        formData['id'] = $("input[name=id]").val();
        formData['status'] = $('#order_status option:selected').val();

        $.ajax({
            url: '/admin/orders/changeStatus',
            dataType: 'json',
            type: "post",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(formData),
            success: function (data) {
                console.log('success');
                console.log(data);
                $("#message").html(data.message);
            }
        });
    });

</script>

<jsp:include page="footer.jsp" flush="true"></jsp:include>