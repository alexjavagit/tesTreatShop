<jsp:include page="header.jsp"  flush="true"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="container">
    <h3 class="booking-title">Category</h3>
    <form id="myform" method="post" action="${action}" modelAttribute="theCategory">
        <div class="row">
            <div class="col-md-9">
                <div class="row">
                    <div class="col-md-5">

                        <input type="hidden" name="id" value="${theCategory.id}"/>

                        <p style="color: red">${errorMessage}</p>
                        <div class="form-group form-group-icon-left"><i class="fa fa-user input-icon"></i>
                            <label>Name</label>
                            <input class="form-control" name="name" value="${theCategory.name}" type="text" required />
                        </div>
                        <div class="form-group form-group-icon-left"><i class="fa fa-user input-icon"></i>
                            <label>Short Name</label>
                            <input class="form-control" name="shortName" value="${theCategory.shortName}" type="text" required pattern="[a-zA-Z0-9\_]{1,50}"  />
                        </div>
                        <div class="form-group form-group-icon-left"><i class="fa fa-envelope input-icon"></i>
                            <label>Description</label>
                            <textarea class="form-control" name="description">${theCategory.description}</textarea>
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


<jsp:include page="footer.jsp"  flush="true"></jsp:include>