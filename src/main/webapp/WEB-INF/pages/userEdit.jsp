<jsp:include page="header.jsp"  flush="true"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="container">
    <h3 class="booking-title">User Settings</h3>
    <form id="myform" method="post" action="${action}" modelAttribute="theUser">
    <div class="row">
        <c:if test="${theUser.id ne null}">
        <div class="col-md-3">
            <aside class="user-profile-sidebar">
                <div class="user-profile-avatar text-center">
                    <h5>${theUser.login}</h5>
                    <p>Member Since ${dateAdded}</p>
                </div>
                <ul class="list user-profile-nav">
                    <li><a href="/admin/orders/user/${theUser.id}"><i class="fa fa-user"></i>User Orders</a>
                    </li>
                </ul>
            </aside>
        </div>
        </c:if>
        <div class="col-md-9">
            <div class="row">
                <div class="col-md-5">

                        <input type="hidden" name="id" value="${theUser.id}"/>
                        <h4>Personal Infomation</h4>
                        <p style="color: red">${errorMessage}</p>
                        <div class="form-group form-group-icon-left"><i class="fa fa-user input-icon"></i>
                            <label>First Name</label>
                            <input class="form-control" name="firstName" value="${theUser.firstName}" type="text" required />
                        </div>
                        <div class="form-group form-group-icon-left"><i class="fa fa-user input-icon"></i>
                            <label>Last Name</label>
                            <input class="form-control" name="lastName" value="${theUser.lastName}" type="text" required />
                        </div>
                        <div class="form-group form-group-icon-left"><i class="fa fa-envelope input-icon"></i>
                            <label>E-mail</label>
                            <input class="form-control" name="email" value="${theUser.email}" type="email" required pattern="[^@]+@[^@]+\.[a-zA-Z]{2,6}" />
                        </div>
                        <div class="form-group form-group-icon-left"><i class="fa fa-phone input-icon"></i>
                            <label>Phone Number</label>
                            <input class="form-control" id="phone" name="phone" value="${theUser.phone}" type="phone" required pattern="\([0-9]{3}\) [0-9]{3}[-][0-9]{4}" />
                        </div>

                        <div class="form-group form-group-icon-left">
                            <label>Address</label>
                            <input class="form-control" name="address" value="${theUser.address}" type="text" required />
                        </div>
                        <div class="gap gap-small"></div>
                        <div class="form-group">
                            <label>Login</label>
                            <input class="form-control" name="login" value="${theUser.login}" type="text" required pattern="[a-zA-Z0-9]{1,15}" />
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <input class="form-control" name="password" value="" type="password" required />
                        </div>

                        <hr>
                        <input type="submit" class="btn btn-primary" value="Submit">
                        <br/><br/>

                </div>

            </div>

        </div>
    </div>
    </form>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://rawgit.com/RobinHerbots/jquery.inputmask/3.x/dist/jquery.inputmask.bundle.js"></script>
<script>
    $('#phone').inputmask("(999) 999-9999");
</script>



<jsp:include page="footer.jsp"  flush="true"></jsp:include>