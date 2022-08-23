<jsp:include page="header.jsp"></jsp:include>
<div class="container">
    <h1 class="page-title">Register on Treat Shoes</h1>
</div>

<div class="gap"></div>

<c:url value="/newuser" var="regUrl" />
<div class="container">
    <div class="row" data-gutter="60">
        <div class="col-md-4">
            <h3>Welcome to Treat Shoes</h3>
            <p>Euismod nunc porta magna elementum penatibus amet gravida sit ligula odio id nunc proin sem augue quis posuere interdum in sapien congue rutrum scelerisque sociosqu cubilia ridiculus et luctus mollis</p>
            <p>Praesent est semper massa lobortis quisque lectus ridiculus hac eget</p>
        </div>
          <div class="col-md-4">
            <h3>New To Treat Shoes?</h3>
            <c:if test="${exists ne null}">
                <p>User already exists!</p>
            </c:if>
            <form action="${regUrl}" method="POST">
                <div class="form-group form-group-icon-left"><i class="fa fa-customUser input-icon input-icon-show"></i>
                    <label>First Name *</label>
                    <input class="form-control" placeholder="e.g. John" type="text" name="first_name" />
                </div>
                <div class="form-group form-group-icon-left"><i class="fa fa-customUser input-icon input-icon-show"></i>
                    <label>Last Name *</label>
                    <input class="form-control" placeholder="e.g. Doe" type="text" name="last_name" />
                </div>
                <div class="form-group form-group-icon-left"><i class="fa fa-envelope input-icon input-icon-show"></i>
                    <label>Email *</label>
                    <input class="form-control" placeholder="e.g. johndoe@gmail.com" type="text" name="email" />
                </div>
                <div class="form-group form-group-icon-left"><i class="fa fa-envelope input-icon input-icon-show"></i>
                    <label>Phone *</label>
                    <input class="form-control" placeholder="e.g. +380999999999" type="text" name="phone" />
                </div>
                <div class="form-group form-group-icon-left"><i class="fa fa-envelope input-icon input-icon-show"></i>
                    <label>Address</label>
                    <input class="form-control" placeholder="e.g. Kiev, Sikorskogo Str 23/12" type="text" name="address" />
                </div>
                <div class="form-group form-group-icon-left"><i class="fa fa-envelope input-icon input-icon-show"></i>
                    <label>Login *</label>
                    <input class="form-control" placeholder="e.g. john11" type="text" name="login" />
                </div>
                <div class="form-group form-group-icon-left"><i class="fa fa-lock input-icon input-icon-show"></i>
                    <label>Password *</label>
                    <input class="form-control" type="password" placeholder="my secret password" name="password" />
                </div>
                <input class="btn btn-primary" type="submit" value="Sign up for Treat Shoes" />
            </form>
        </div>
    </div>
</div>

<div class="gap"></div>
<jsp:include page="footer.jsp"></jsp:include>


<!--<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Prog.kiev.ua</title>
</head>
<body>
<div align="center">
    <c:url value="/newuser" var="regUrl" />

    <form action="${regUrl}" method="POST">
        Login:<br/><input type="text" name="login"><br/>
        Password:<br/><input type="password" name="password"><br/>
        E-mail:<br/><input type="text" name="email"><br/>
        Phone:<br/><input type="text" name="phone"><br/>
        <input type="submit" />

        <c:if test="${exists ne null}">
            <p>User already exists!</p>
        </c:if>
    </form>
</div>
</body>
</html>-->
