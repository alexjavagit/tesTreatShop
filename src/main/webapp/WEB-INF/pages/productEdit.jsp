<jsp:include page="header.jsp" flush="true"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

<div class="container">
    <h3 class="booking-title">Product Settings</h3>

    <div class="row">
        <p id="message"></p>
        <div class="col-md-9" style="width: 90%!important">
            <div class="row">
                <div class="col-md-5">
                    <form id="formData" method="post" action="" modelAttribute="theProduct">
                        <input type="hidden" name="id" value="${theProduct.id}"/>

                        <p style="color: red">${errorMessage}</p>
                        <div class="form-group form-group-icon-left"><i class="fa fa-user input-icon"></i>
                            <label>Name</label>
                            <input class="form-control" name="name" value="${theProduct.name}" type="text" required/>
                        </div>
                        <div class="form-group form-group-icon-left"><i class="fa fa-folder input-icon"></i>
                            <label>Category</label>
                            <select name="category" class="form-control" required>
                                <option <c:if test="${theProduct.category.id == ''}"> Selected</c:if>></option>
                                <c:forEach items="${categories}" var="category">
                                    <option value="${category.id}" <c:if
                                            test="${theProduct.category.id == category.id}"> Selected</c:if>>${category.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group form-group-icon-left"><i class="fa fa-dollar input-icon"></i>
                            <label>Price</label>
                            <input class="form-control" name="price" value="${theProduct.price}" type="text" required
                                   pattern="[0-9\.]{2,6}"/>
                        </div>
                        <div class="form-group form-group-icon-left"><i class="fa fa-percent input-icon"></i>
                            <label>Discount</label>
                            <input class="form-control" name="discount" value="${theProduct.discount}" type="text"
                                   pattern="[0-9]{0,2}"/>
                        </div>

                        <div class="form-group form-group-icon-left">
                            <label>Description</label>
                            <textarea name="description" class="form-control">${theProduct.description}</textarea>
                        </div>
                        <div class="gap gap-small"></div>

                        <hr>
                        <input type="submit" class="btn btn-primary" value="Submit">
                        <br/><br/>
                    </form>
                </div>
                <div id="formImagesDiv" class="col-md-7">
                    <form id="formImages" method="post" action="" enctype="multipart/form-data">
                        <div id="divImagesButt" class="row row-no-gutter"
                             <c:if test="${theProduct.id eq null}">style="display: none"</c:if>>
                            <c:forEach items="${theProduct.productImages}" var="image">
                                <div id="div${image.id}" class="col-md-4" style="padding: 4px!important">
                                    <div class="thumb">
                                        <a class="hover-img" href="#">
                                            <img src="data:;base64,${image.base64Image}" alt="${image.id}"
                                                 style="width: 182px; height: 140px;"/>
                                            <div class="hover-inner hover-inner-block hover-inner-bottom hover-inner-bg-black hover-inner-sm hover-hold">
                                                <div class="text-small">
                                                    <a href="#"
                                                       onClick="javascript: delete_pic('${image.id}');">Delete</a>
                                                </div>
                                            </div>
                                        </a>
                                    </div>

                                </div>
                            </c:forEach>

                            <div id="btnAdd" class="col-md-4" style="padding: 4px!important">
                                <input id="fileInput" type="file" style="display:none;" accept="image/png, image/jpeg"/>
                                <a href="#" onClick="javascript: document.getElementById('fileInput').click();"
                                   class="btn btn-primary mb20"><i class="fa fa-plus-circle"></i>Add new photo</a>
                            </div>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    </div>
    </form>
</div>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#fileInput").change(function (event) {
            //stop submit the form, we will post it manually.
            event.preventDefault();
            fire_ajax_submit();
        });
    });

    function fire_ajax_submit() {
        console.log('2');

        var file = $('#fileInput')[0].files[0];
        console.log(file);
        // var formData = {}
        var form = $('#formImages')[0];
        var data = new FormData(form);
        console.log(data + '------' + $("input[name=id]").val());
        data.append("id", $("input[name=id]").val());
        data.append("file", file);
        $.ajax({
            url: "/admin/products/updateFile",
            type: 'post',
            data: data,
            processData: false, // tell jQuery not to process the data
            contentType: false, // tell jQuery not to set contentType
            cache: false,
            success: function (data) {
                console.log(data);
                //$('#result').html(data);
                html = "<div class=\"col-md-4\" style=\"padding: 4px!important\">" +
                    "<div class=\"thumb\">" +
                    "<a class=\"hover-img\" href=\"#\" >" +
                    "<img src=\"" + URL.createObjectURL(file) + "\" style=\"width: 182px; height: 140px;\" />" +
                    "<div class=\"hover-inner hover-inner-block hover-inner-bottom hover-inner-bg-black hover-inner-sm hover-hold\">" +
                    "<div class=\"text-small\">" +
                    "<a href=\"#\" onClick=\"javascript: delete_pic('" + data + "');\">Delete</a>" +
                    "</div>" +
                    "</div>" +
                    "</a>" +
                    "</div>" +
                    "</div>";

                $("div#btnAdd").before(html);
            }
        });

    };


    $("#formData").submit(function (event) {
        event.preventDefault();
        var formData = {};
        var form = this;
        var formData = {}
        formData['id'] = $("input[name=id]").val();
        formData['name'] = $("input[name=name]").val();
        formData['category'] = $("select[name=category] option:selected").val();
        formData['price'] = $("input[name=price]").val();
        formData['discount'] = $("input[name=discount]").val();
        formData['description'] = $("textarea[name=description]").val();
        $.ajax({
            url: '/admin/products/updateData',
            dataType: 'json',
            type: "post",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(formData),
            success: function (data) {
                console.log('success');
                console.log(data);
                if (formData['id'] == '') {
                    $("#message").html("Product successfully added. Please add product images.");
                    $("#divImagesButt").show();
                    $("input[name=id]").val(data.id);
                } else {
                    window.location = "/admin/products";
                }
            }
        });
    });

    function delete_pic(pid) {
        var formData = {}
        formData['id'] = pid;
        $('#div' + pid).hide();
        $.ajax({
            url: '/admin/products/deleteFile',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(formData),
            dataType: 'json',
            type: "POST",
            success: function (data) {
                console.log(data);
            }
        });

    }
</script>

<jsp:include page="footer.jsp" flush="true"></jsp:include>