<jsp:include page="header.jsp" flush="true"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <ul class="breadcrumb">
        <li><a href="/">Home</a>
        </li>
        <li><a href="/catalog/${category.shortName}">${category.name}</a>
        </li>
    </ul>
    <div class="booking-item-details">
        <div class="row">
            <div class="col-md-6">
                <div class="tabbable booking-details-tabbable">
                    <ul class="nav nav-tabs" id="myTab">
                        <li class="active"><a href="#tab-1" data-toggle="tab"><i class="fa fa-camera"></i>Photos</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane fade in active" id="tab-1">
                            <div class="fotorama" data-allowfullscreen="true" data-nav="thumbs">
                                <c:forEach items="${productImages}" var="image">
                                    <img src="data:;base64,${image.image}" alt="${image.id}" title="${product.name}" />
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="booking-item-meta">
                    <h3 class="lh1em mt40">${product.name}</h3>

                    <div class="col-md-3">
                        <p class="booking-item-header-price"><span class="text-lg">$${product.price}</span></p>
                    </div>

                    <div class="gap"></div>

                    <h5 class="lh1em"> ${product.description}</h5>

                    <div class="gap"></div>
                    <p id="message" style="display: none; color: red;">Please choose size to add product to shopping cart!</p>
                    <p><div class="btn-group btn-group-select-num" data-toggle="buttons">
                        <c:forEach items="${sizes}" var="size">
                        <label class="btn btn-primary">
                            <input type="radio" name="size" value="${size.size}" />${size.size}</label>
                        </c:forEach>
                    </div></p>


                    <div class="gap"></div>
                    <a href="#" class="btn btn-primary" onClick="javascript: add_to_cart()">Add to cart</a>

                    </div>
                </div>

            </div>
        </div>
        <div class="gap"></div>

    </div>
    <div class="gap gap-small"></div>
</div>

<script>
    function add_to_cart() {
        var size = $('input[name="size"]:checked').val();
        var product_id = '${product.id}';
        if (!size) {
            $("#message").text("Please choose size to add product to shopping cart!");
            $("#message").show();
        } else {
            var formData = {}
            formData['id'] = product_id;
            formData['size'] = size;
            console.log("id="+product_id+"   size="+size);
            $.ajax({
                url:'/shoppingCart/addProduct',
                contentType: "application/json; charset=utf-8",
                data : JSON.stringify(formData),
                dataType : 'json',
                type: "POST",
                success: function (data) {
                    console.log(data);
                    if (data.message = 'OK') {
                        window.location = '/shoppingCart';
                    } else {
                        $("#message").text(data.message);
                        $("#message").show();
                    }
                }
            });

        }
    }
</script>
<jsp:include page="footer.jsp"  flush="true"></jsp:include>