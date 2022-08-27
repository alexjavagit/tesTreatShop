<jsp:include page="header.jsp"  flush="true"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="container">
    <h3 class="booking-title">Product Settings</h3>
    <form id="myform" method="post" action="" modelAttribute="theProduct" enctype="multipart/form-data">
        <div class="row">

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
                            <input class="form-control" name="price" value="${theProduct.price}" type="text" required pattern="[0-9\.]{2,6}" />
                        </div>
                        <div class="form-group form-group-icon-left"><i class="fa fa-pencil input-icon"></i>
                            <label>Discount</label>
                            <input class="form-control" name="discount" value="${theProduct.discount}" type="text" pattern="[0-9]{0,2}" />
                        </div>

                        <div class="form-group form-group-icon-left">
                            <label>Description</label>
                            <textarea name="description" class="form-control">${theProduct.description}</textarea>
                        </div>
                        <div class="gap gap-small"></div>

                        <hr>
                        <input type="submit" class="btn btn-primary" value="Submit">
                        <br/><br/>

                    </div>
                    <div class="col-md-7">

                        <div class="row row-no-gutter">
                        <c:forEach items="${theProduct.productImages}" var="image">
                            <div id="div${image.id}" class="col-md-4" style="padding: 4px!important">
                                <div class="thumb">
                                    <a class="hover-img" href="#">
                                        <img src="data:;base64,${image.image}" alt="${image.id}" style="width: 182px; height: 140px;" />
                                        <div class="hover-inner hover-inner-block hover-inner-bottom hover-inner-bg-black hover-inner-sm hover-hold">
                                            <div class="text-small">
                                                <a href="#" onClick="javascript: $('#delete${image.id}').value='${image.id}'; $('#div${image.id}').hide();">Delete</a>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                                <input id="delete${image.id}" type="hidden" name="delete[]" value="${image.id}" />
                            </div>
                        </c:forEach>

                        <div id="btnAdd" class="col-md-4" style="padding: 4px!important">
                            <input id="fileInput" type="file" style="display:none;" accept="image/png, image/jpeg" />
                            <a href="#" onClick="javascript: document.getElementById('fileInput').click();" class="btn btn-primary mb20"><i class="fa fa-plus-circle"></i>Add new photo</a>
                        </div>
                    </div>

                </div>

            </div>
        </div>
        </div>
    </form>
</div>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
    $(function() {
        $("#fileInput").change(function (){
            var fileName = $(this).val();
            console.log(fileName);

            var fd = new FormData();
            var file = $('#fileInput')[0].files[0];

            html = "<div class=\"col-md-4\" style=\"padding: 4px!important\">"+
                "<div class=\"thumb\">"+
                "<a class=\"hover-img\" href=\"#\" >"+
                "<img src=\""+URL.createObjectURL(file)+"\" style=\"width: 182px; height: 140px;\" />"+
                "<div class=\"hover-inner hover-inner-block hover-inner-bottom hover-inner-bg-black hover-inner-sm hover-hold\">"+
                "<div class=\"text-small\">"+
                "<a href=\"#\" onClick=\"javascript: $(this).parent().find('input:hidden:first').value=''; $(this).parent().closest('.col-md-4').hide();\">Delete</a>"+
                "</div>"+
                "</div>"+
                "</a>"+
                "</div>"+
                "<input class=\"newupl\" type=\"hidden\" name=\"upload[]\" value=\""+file+"\" />"+
                "</div>";

            $("div#btnAdd").before(html);


        });
    });


    $( "#myform" ).submit(function( event ) {
        event.preventDefault();
        var formData = {};
        // reference to form object
        var form = this;
        // mapthat will hold form data
        var formData = {}
        formData['id'] = $("input[name=id]").val();
        formData['name'] = $("input[name=name]").val();
        formData['category'] = $("select[name=category] option:selected").val();
        formData['price'] = $("input[name=price]").val();
        formData['discount'] = $("input[name=discount]").val();
        formData['description'] = $("textarea[name=description]").val();
        formData['upload[]'] = $("input:file .newupl").val();
        console.log(formData);
        $.ajax({
            url:'/admin/products/update',
            dataType : 'json',
            type: "post",
            data : JSON.stringify(formData),
            success: function (data) {
                console.log(data);
                //$('#result').html(data);
            }
        });
    });
</script>

<jsp:include page="footer.jsp"  flush="true"></jsp:include>