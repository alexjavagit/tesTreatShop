<jsp:include page="header.jsp"></jsp:include>
<div class="container">
    <h1 class="page-title">Register on Treat Shoes</h1>
</div>

<div class="gap"></div>

<c:url value="/user/new" var="regUrl"/>
<div class="container">
    <div class="row" data-gutter="60">
        <div class="col-md-4">
            <h3>Welcome to Treat Shoes</h3>
            <p>Euismod nunc porta magna elementum penatibus amet gravida sit ligula odio id nunc proin sem augue quis
                posuere interdum in sapien congue rutrum scelerisque sociosqu cubilia ridiculus et luctus mollis</p>
            <p>Praesent est semper massa lobortis quisque lectus ridiculus hac eget</p>
        </div>
        <div class="col-md-4">
            <h3>New To Treat Shoes?</h3>
            <p style="color: red">${errorMessage}</p>
            <form action="/user/new" method="POST" modelAttribute="customUser">
                <input type="hidden" name="id" value=""/>
                <div class="form-group form-group-icon-left"><i class="fa fa-customUser input-icon input-icon-show"></i>
                    <label>First Name *</label>
                    <input class="form-control" value="${customUser.firstName}" type="text" name="firstName" required/>
                </div>
                <div class="form-group form-group-icon-left"><i class="fa fa-customUser input-icon input-icon-show"></i>
                    <label>Last Name *</label>
                    <input class="form-control" value="${customUser.lastName}" type="text" name="lastName" required/>
                </div>
                <div class="form-group form-group-icon-left"><i class="fa fa-envelope input-icon input-icon-show"></i>
                    <label>Email *</label>
                    <input class="form-control" value="${customUser.email}" type="text" name="email" required
                           pattern="[^@]+@[^@]+\.[a-zA-Z]{2,6}"/>
                </div>
                <div class="form-group form-group-icon-left"><i class="fa fa-envelope input-icon input-icon-show"></i>
                    <label>Phone *</label>
                    <input class="form-control" type="text" id="phone" name="phone" value="${customUser.phone}" required
                           pattern="\([0-9]{3}\) [0-9]{3}[-][0-9]{4}"/>
                </div>
                <div class="form-group form-group-icon-left"><i class="fa fa-envelope input-icon input-icon-show"></i>
                    <label>Address</label>
                    <input class="form-control" value="${customUser.address}" type="text" name="address" required/>
                </div>
                <div class="form-group form-group-icon-left"><i class="fa fa-envelope input-icon input-icon-show"></i>
                    <label>Login *</label>
                    <input class="form-control" value="${customUser.login}" type="text" name="login" required
                           pattern="[a-zA-Z0-9]{1,15}"/>
                </div>
                <div class="form-group form-group-icon-left"><i class="fa fa-lock input-icon input-icon-show"></i>
                    <label>Password *</label>
                    <input class="form-control" type="password" placeholder="my secret password" name="password"
                           required/>
                </div>
                <input class="btn btn-primary" type="submit" value="Sign up for Treat Shoes"/>
            </form>
        </div>
    </div>
</div>

<div class="gap"></div>
<jsp:include page="footer.jsp"></jsp:include>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://rawgit.com/RobinHerbots/jquery.inputmask/3.x/dist/jquery.inputmask.bundle.js"></script>
<script>
    $('#phone').inputmask("(999) 999-9999");
</script>
