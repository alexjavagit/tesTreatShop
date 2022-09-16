<jsp:include page="header.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/j_spring_security_check" var="loginUrl"/>
<div class="container">
    <h1 class="page-title">Login/Register on Treat Shoes</h1>
</div>

<div class="gap"></div>


<div class="container">
    <div class="row" data-gutter="60">
        <div class="col-md-4">
            <h3>Welcome to Treat Shoes</h3>
            <p>Euismod nunc porta magna elementum penatibus amet gravida sit ligula odio id nunc proin sem augue quis
                posuere interdum in sapien congue rutrum scelerisque sociosqu cubilia ridiculus et luctus mollis</p>
            <p>Praesent est semper massa lobortis quisque lectus ridiculus hac eget</p>
        </div>
        <div class="col-md-4">
            <h3>Login</h3>
            <c:if test="${param.error ne null}">
                <p>Wrong login or password!</p>
            </c:if>
            <form action="${loginUrl}" method="POST">
                <div class="form-group form-group-icon-left"><i class="fa fa-customUser input-icon input-icon-show"></i>
                    <label>Username</label>
                    <input class="form-control" placeholder="e.g. johndoe@gmail.com" type="text" name="j_login"/>
                </div>
                <div class="form-group form-group-icon-left"><i class="fa fa-lock input-icon input-icon-show"></i>
                    <label>Password</label>
                    <input class="form-control" type="password" placeholder="my secret password" name="j_password"/>
                </div>
                <input class="btn btn-primary" type="submit" value="Sign in"/>
            </form>
        </div>
    </div>
</div>


<div class="gap"></div>


<jsp:include page="footer.jsp"></jsp:include>

