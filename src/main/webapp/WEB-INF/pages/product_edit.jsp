<jsp:include page="header.jsp"  flush="true"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="container">
    <h3 class="booking-title">Product Settings</h3>
    <form id="myform" method="post" action="${action}" modelAttribute="theProduct">
        <div class="row">
            <c:if test="${theProduct.id ne null}">
                <div class="col-md-3">
                    <aside class="user-profile-sidebar">
                        <img src="data:;base64,${product.productImages[0].image}" alt="${theProduct.name}" title="${theProduct.name}" />
                       <!-- <div class="user-profile-avatar text-center">
                            <h5>${theUser.login}</h5>
                            <p>Member Since ${dateAdded}</p>
                        </div>
                        <ul class="list user-profile-nav">
                            <li><a href="/admin/orders/user/${theUser.id}"><i class="fa fa-user"></i>User Orders</a>
                            </li>
                        </ul>-->
                    </aside>
                </div>
            </c:if>
            <div class="col-md-9" style="width: 90%!important">
                <div class="row">
                    <div class="col-md-5">

                        <input type="hidden" name="id" value="${theProduct.id}"/>

                        <p style="color: red">${errorMessage}</p>
                        <div class="form-group form-group-icon-left"><i class="fa fa-user input-icon"></i>
                            <label>Name</label>
                            <input class="form-control" name="name" value="${theProduct.name}" type="text" required />
                        </div>
                        <div class="form-group form-group-icon-left"><i class="fa fa-folder input-icon"></i>
                            <label>Category</label>
                            <select name="category" class="form-control" required>
                                <option <c:if test="${theProduct.category.id == ''}"> Selected</c:if>></option>
                                <c:forEach items="${categories}" var="category">
                                    <option value="${category.id}" <c:if test="${theProduct.category.id == category.id}"> Selected</c:if>>${category.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group form-group-icon-left"><i class="fa fa-dollar input-icon"></i>
                            <label>Price</label>
                            <input class="form-control" name="price" value="${theProduct.price}" type="text" required pattern="[0-9]{2,6}" />
                        </div>
                        <div class="form-group form-group-icon-left"><i class="fa fa-pencil input-icon"></i>
                            <label>Discount</label>
                            <input class="form-control" name="discount" value="${theProduct.discount}" type="text" pattern="[0-9]{0,2}" />
                        </div>

                        <div class="form-group form-group-icon-left">
                            <label>Description</label>
                            <textarea name="description" class="form-control">
                                ${theProduct.description}
                            </textarea>
                        </div>
                        <div class="gap gap-small"></div>

                        <hr>
                        <input type="submit" class="btn btn-primary" value="Submit">
                        <br/><br/>

                    </div>
                    <div class="col-md-7">

                        <a href="#" class="btn btn-primary mb20"><i class="fa fa-plus-circle"></i> Add new photo</a>
                        <div class="row row-no-gutter">
                            <div class="col-md-4">
                                <div class="thumb">
                                    <a class="hover-img" href="#">
                                        <img src="/img/400x300.png" alt="Image Alternative text" title="the journey home" />
                                        <div class="hover-inner hover-inner-block hover-inner-bottom hover-inner-bg-black hover-inner-sm hover-hold">
                                            <div class="text-small">

                                            </div>
                                        </div>
                                    </a>
                                </div>
                            </div>
                            <div class="row row-no-gutter">
                                <div class="col-md-4">
                                    <div class="thumb">
                                        <a class="hover-img" href="#">
                                            <img src="/img/400x300.png" alt="Image Alternative text" title="the journey home" />
                                            <div class="hover-inner hover-inner-block hover-inner-bottom hover-inner-bg-black hover-inner-sm hover-hold">
                                                <div class="text-small">
                                                    <p><i class="fa fa-map-marker"></i> New York, NY (Midtown East)</p><small class="text-white">July 10, 2014</small>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                                <div class="row row-no-gutter">
                                    <div class="col-md-4">
                                        <div class="thumb">
                                            <a class="hover-img" href="#">
                                                <img src="/img/400x300.png" alt="Image Alternative text" title="the journey home" />
                                                <div class="hover-inner hover-inner-block hover-inner-bottom hover-inner-bg-black hover-inner-sm hover-hold">
                                                    <div class="text-small">
                                                        <p><i class="fa fa-map-marker"></i> New York, NY (Midtown East)</p><small class="text-white">July 10, 2014</small>
                                                    </div>
                                                </div>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="row row-no-gutter">
                                        <div class="col-md-4">
                                            <div class="thumb">
                                                <a class="hover-img" href="#">
                                                    <img src="/img/400x300.png" alt="Image Alternative text" title="the journey home" />
                                                    <div class="hover-inner hover-inner-block hover-inner-bottom hover-inner-bg-black hover-inner-sm hover-hold">
                                                        <div class="text-small">
                                                            <p><i class="fa fa-map-marker"></i> New York, NY (Midtown East)</p><small class="text-white">July 10, 2014</small>
                                                        </div>
                                                    </div>
                                                </a>
                                            </div>
                                        </div>
                                        <div class="row row-no-gutter">
                                            <div class="col-md-4">
                                                <div class="thumb">
                                                    <a class="hover-img" href="#">
                                                        <img src="/img/400x300.png" alt="Image Alternative text" title="the journey home" />
                                                        <div class="hover-inner hover-inner-block hover-inner-bottom hover-inner-bg-black hover-inner-sm hover-hold">
                                                            <div class="text-small">
                                                                <p><i class="fa fa-map-marker"></i> New York, NY (Midtown East)</p><small class="text-white">July 10, 2014</small>
                                                            </div>
                                                        </div>
                                                    </a>
                                                </div>
                                            </div>
                    </div>

                </div>

            </div>
        </div>
    </form>
</div>

<jsp:include page="footer.jsp"  flush="true"></jsp:include>