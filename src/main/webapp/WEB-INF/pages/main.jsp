<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- TOP AREA -->
<div class="top-area show-onload">
    <div class="bg-holder full">
        <div class="bg-front bg-front-mob-rel">
            <div class="container">
                <div class="search-tabs search-tabs-bg search-tabs-abs-bottom">
                    <div class="tabbable">

                        <div class="tab-content">

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="owl-carousel owl-slider owl-carousel-area visible-lg" id="owl-carousel-slider">
            <div class="bg-holder full">
                <div class="bg-mask"></div>
                <div class="bg-img" style="background-image:url(img/carousel1.jpg);"></div>
                <div class="bg-content">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-5 col-md-offset-7">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="bg-holder full">
                <div class="bg-mask"></div>
                <div class="bg-img" style="background-image:url(img/carousel2.jpg);"></div>
                <div class="bg-content">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-5 col-md-offset-7">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="bg-holder full">
                <div class="bg-mask"></div>
                <div class="bg-img" style="background-image:url(img/carousel3.jpg);"></div>
                <div class="bg-content">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-5 col-md-offset-7">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="bg-img hidden-lg" style="background-image:url(img/2048x1365.png);"></div>
        <div class="bg-mask hidden-lg"></div>
    </div>
</div>
<!-- END TOP AREA -->

<div class="gap"></div>
<div class="bg-darken">
    <div class="container">

        <h2>New Products</h2>
        <div class="row row-wrap">
            <c:forEach items="${products}" var="product">
                <div class="col-md-3">
                    <div class="thumb">
                        <header class="thumb-header">
                            <a class="hover-img" href="/product/${product.id}">
                                <img src="data:;base64,${product.productImages[0].image}" alt="${product.name}"
                                     title="${product.name}"/>
                            </a>
                        </header>
                        <div class="thumb-caption">
                            <h5 class="thumb-title"><a class="text-darken" href="#">${product.name}</a></h5>
                            <p class="mb0"><small><i class="fa fa-map-marker"></i>${product.category.name}</small>
                            </p>
                            <p class="mb0 text-darken"><span class="text-lg lh1em text-color">$${product.price}</span>
                            </p>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>
        <div class="gap gap-small"></div>
    </div>
</div>
<div class="container">
    <div class="gap gap-small"></div>
    <div class="gap gap-small"></div>
    <div class="row row-wrap" data-gutter="60">
        <div class="col-md-4">
            <div class="thumb"><i
                    class="fa fa-dollar box-icon-left round box-icon-normal box-icon-black animate-icon-top-to-bottom"></i>
                <div class="thumb-caption">
                    <h5 class="thumb-title">Best Price Guarantee</h5>
                    <p class="thumb-desc">Integer magnis leo eros condimentum parturient lectus quam enim viverra
                        scelerisque tortor natoque sodales ac</p>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="thumb"><i
                    class="fa fa-thumbs-o-up box-icon-left round box-icon-normal box-icon-black animate-icon-top-to-bottom"></i>
                <div class="thumb-caption">
                    <h5 class="thumb-title">Best Shop Ever</h5>
                    <p class="thumb-desc">Scelerisque etiam at adipiscing ante class vulputate felis purus eleifend
                        luctus suspendisse luctus lacinia suspendisse</p>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="thumb"><i
                    class="fa fa-briefcase box-icon-left round box-icon-normal box-icon-black animate-icon-top-to-bottom"></i>
                <div class="thumb-caption">
                    <h5 class="thumb-title">Save with Us</h5>
                    <p class="thumb-desc">Consequat gravida tellus vivamus at urna fames dictumst cursus fusce varius
                        felis imperdiet curae magna</p>
                </div>
            </div>
        </div>
    </div>
</div>
