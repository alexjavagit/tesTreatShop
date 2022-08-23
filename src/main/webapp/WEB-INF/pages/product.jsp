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

                    <p><div class="btn-group btn-group-select-num" data-toggle="buttons">
                        <c:forEach items="${sizes}" var="size">
                        <label class="btn btn-primary">
                            <input type="radio" name="size" value="${size.size}" />${size.size}</label>
                        </c:forEach>
                    </div></p>


                    <div class="gap"></div>
                    <a href="#" class="btn btn-primary">Add to cart</a>

                    </div>
                </div>

            </div>
        </div>
        <div class="gap"></div>

    </div>
    <div class="gap gap-small"></div>
</div>
<jsp:include page="footer.jsp"  flush="true"></jsp:include>