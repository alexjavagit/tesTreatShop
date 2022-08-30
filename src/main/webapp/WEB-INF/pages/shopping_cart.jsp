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
<c:forEach items="${products}" var="product">
                <li>
                    <a class="booking-item" href="#">
                        <div class="row">
                            <div class="col-md-3">
                                <div class="booking-item-img-wrap">
                                    <img src="data:;base64,${product.productImages[0].image}" alt="${product.name}" title="${product.name}" />
                                    <div class="booking-item-img-num"><i class="fa fa-picture-o"></i>12</div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <h5 class="booking-item-title">${product.name}</h5>
                                <p class="booking-item-address"><i class="fa fa-map-marker"></i> Size: ${product.size}</p>
                                <p class="booking-item-address"><i class="fa fa-map-marker"></i> Price: $${product.price}</p>
                                <p><div class="btn-group btn-group-select-num" data-toggle="buttons">
                                Quantity:
                                    <label class="btn btn-primary"><input type="radio" name="size" value="" />-</label>
                                ${product.qty}<label class="btn btn-primary"><input type="radio" name="size" value="" />+</label>
                            </div></p>
                            </div>
                            <div class="col-md-3"><span class="booking-item-price">$${product.total}</span>

                                <!--<span class="btn btn-primary">Book Now</span>-->
                            </div>
                        </div>
                    </a>
                </li>
    </c:forEach>



            <div class="row">
                <div class="col-md-6">
                    <p><small>320 vacation rentals found in New York. &nbsp;&nbsp;Showing 1 – 15</small>
                    </p>
                    <ul class="pagination">
                        <li class="active"><a href="#">1</a>
                        </li>
                        <li><a href="#">2</a>
                        </li>
                        <li><a href="#">3</a>
                        </li>
                        <li><a href="#">4</a>
                        </li>
                        <li><a href="#">5</a>
                        </li>
                        <li><a href="#">6</a>
                        </li>
                        <li><a href="#">7</a>
                        </li>
                        <li class="dots">...</li>
                        <li><a href="#">43</a>
                        </li>
                        <li class="next"><a href="#">Next Page</a>
                        </li>
                    </ul>
                </div>
                <div class="col-md-6 text-right">
                    <p>Not what you're looking for? <a class="popup-text" href="#search-dialog" data-effect="mfp-zoom-out">Try your search again</a>
                    </p>
                </div>
            </div>

        </div>
    </div>
    <div class="gap"></div>
</div>




<!--
<h1 class="jumbotron">
    <span sec:authentication="name"></span>'s Shopping Cart
</h1>

<div class="alert alert-info" th:if="${outOfStockMessage}" th:utext="${outOfStockMessage}"></div>

<div class="panel-default well" th:each="product : ${products.entrySet()}">
    <div class="panel-heading">
        <h1><a th:text="${product.getKey().name}" th:href="@{'/product/' + ${product.getKey().id}}">Title</a></h1>
        <h3 th:text="${product.getKey().description}">Description</h3>
    </div>
    <div class="row panel-body">
        <div th:inline="text" class="col-md-2">Price: [[${product.getKey().price}]] $</div>
        <div th:inline="text" class="col-md-9">Quantity: [[${product.getValue()}]]</div>
        <a th:href="@{'/shoppingCart/removeProduct/{id}'(id=${product.getKey().id})}" class="col-md-1">
            <button type="button" class="btn btn-primary" th:text="Remove">Remove</button>
        </a>
    </div>
    <br></br>
</div>

<div class="row panel-body">
    <h2 class="col-md-11" th:inline="text">Total: [[${total}]]</h2>
    <a th:href="@{'/shoppingCart/checkout'}" class="col-md-1">
        <button type="button" class="btn btn-danger" th:text="Checkout">Checkout</button>
    </a>
</div>

</div>-->

<jsp:include page="footer.jsp"  flush="true"></jsp:include>