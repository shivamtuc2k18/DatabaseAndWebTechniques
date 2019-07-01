<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.dbconnection.dbconnection.Feed.Feed"
         import= "java.util.ArrayList"   %>

<!-- Page Content -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Database and Web Techniques</title>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="css/mdb.min.css" rel="stylesheet">
    <!-- Your custom styles (optional) -->
    <link href="css/style.css" rel="stylesheet">
    <!-- MDBootstrap Datatables  -->
    <link href="css/addons/datatables.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round|Open+Sans">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    <div class="header">
        Database And Web Techniques
    </div>

</head>
<style>

    .header {
        width:100%;
        text-align: Center;
        background: #1abc9c;
        color: white;
        font-size: 20px;
        padding: 10px;
        font-family: sans-serif;
        font-weight: 800;
    }
    /*.header img {
        float: left;
        width: 44px;
        height: 44px;
    }

    .header h1 {
        padding: 10px;
        text-align: Center;
        background: #1abc9c;
        color: white;
        font-size: 20px;
    }*/
    table th {
        text-align: center;
    }

    .table {
        margin: auto;
        width: 90%;
    }
    div.dataTables_wrapper div.dataTables_length label {

        padding-left: 63px !important;
        color: #FFFFFF !important;
    }
    body{

        font-family: "Poppins", "Arial", "Helvetica Neue", sans-serif;
        background: linear-gradient(to top right, #fc2c77 0%, #6c4079 100%);

    }
    .table-background
    {
        background: #FFFFFF;
    }

    input[type="text"]{

        height: unset;
        margin: unset;
    }

    .select-wrapper input.select-dropdown {

        line-height: unset;
        height: unset;
        margin: unset;

    }
    div.dataTables_wrapper div.dataTables_paginate {

        color: #FFFFFF !important;
        margin-left: 63px !important;
    }

    div.dataTables_wrapper div.dataTables_paginate {

        margin-right: 56px !important;
        margin-top: -50px !important;
        color: #FFFFFF !important;
    }
    .pagination .page-item .page-link {
        color: #FFFFFF !important;
    }
</style>

<body class="page-wrapper bg-gra-02 p-t-130 p-b-100 font-poppins">
<div class="p-t-15" style="margin-left: 525px" >
    <button class="btn btn-primary" type="submit" id="updateButton" onclick="updateFeedData();">Update</button>
    <button class="btn btn-danger" type="submit" id="deleteButton" style="background-color: red"  style="margin-left: 675px" onclick="deleteData();">Delete</button>
</div>

<div class="container-fluid">
    <div>
        <c:set var = "records" value = "${noOfRec}"/>
        <c:if test = "${records > 0}">
            <span style="font-weight: bold; color: #FFFFFF; margin-left: 900px">New Records Found:<c:out value = "${records}"/></span>
        </c:if>
        <c:set var = "newProvi" value = "${newProvider}"/>
        <c:if test = "${records > 0}">
            <span style="font-weight: bold; color: #FFFFFF; margin-left: 900px">New Provider Found:<c:out value = "${newProvi}"/></span>
        </c:if>
    </div>
<table id="dtBasicExample" class="table table-striped table-bordered table-sm table-background" cellspacing="0">
    <thead>
    <tr>
        <th class="th-sm">Title
        </th>
        <th class="th-sm">Link
        </th>
        <th class="th-sm">Publish Date
        </th>
        <th class="th-sm">
            <select id="providerFilter" multiple="true"><option value="">Select</option></select>
        </th>
        <th class="th-sm">Inserted Date
        </th>
        <th class="th-sm">Delete
        </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${feedlist}" var="fdlist">
        <tr id="tableRow">
            <td>${fdlist.title}</td>
            <td><a href="${fdlist.link}">${fdlist.link}</a></td>
            <td>${fdlist.date}</td>
            <td>${fdlist.provider}</td>
            <td>${fdlist.inserteddate}</td>
            <spring:url value="/Feed/${fdlist.id}/delete" var="feedUrl"/>
            <td><button type="submit" id="deleteRowButton" style="background: url(delete.png); width: 32px; height:32px; border: transparent; alignment: center; display: block; margin: auto" onclick="rowDeleteButton('${feedUrl}')">

            </button></td>
        </tr>
    </c:forEach>
    </tbody>
    <tfoot>
    <tr>
        <th>Title
        </th>
        <th>Link
        </th>
        <th>Publish Date
        </th>
        <th>Provider
        </th>
        <th>Inserted Date
        </th>
        <th>DELETE
        </th>
    </tr>
    </tfoot>
</table>
</div>
<div class="p-t-15">
    <button class="btn btn--radius-2 btn--blue" type="submit" id="submitButton" style="display:none">Submit</button>
</div>
<!-- SCRIPTS -->
<!-- JQuery -->
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<!-- Bootstrap tooltips -->
<script type="text/javascript" src="js/popper.min.js"></script>
<!-- Bootstrap core JavaScript -->
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<!-- MDB core JavaScript -->
<script type="text/javascript" src="js/mdb.min.js"></script>
<!-- MDBootstrap Datatables  -->
<script type="text/javascript" src="js/addons/datatables.min.js"></script>

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/js/materialize.min.js"></script>

<script>
    $(document).ready(function () {
        var table = $('#dtBasicExample').DataTable({
            dom: 'lrtip',
            initComplete: function () {
                this.api().columns([3]).every( function () {
                    var column = this;
                    console.log(column);
                    var select = $("#providerFilter");
                    column.data().unique().sort().each( function ( d, j ) {
                        select.append( '<option value="'+d+'">'+d+'</option>' )
                    } );
                } );
                $("#providerFilter").material_select();
            }
        });

        $('#providerFilter').on('change', function(){
            var search = [];

            $.each($('#providerFilter option:selected'), function(){
                search.push($(this).val());
            });

            search = search.join('|');
            table.column(3).search(search, true, false).draw();
        });
        $('.dataTables_length').addClass('bs-select');
    });
</script>

<script>

    var indexvalue = <c:out value="${pagIndex}"/>
        //alert();
    if(indexvalue > 0){

        document.getElementById("updateButton").disabled = true;
    }
    setTimeout(function () {
        document.getElementById("updateButton").disabled = false;
    },600000);

    var updateFeedData =function () {

        document.getElementById("updateButton").disabled = true;


        setTimeout(function () {
            document.getElementById("updateButton").disabled = false;
        },600000);

        window.open("/getForm",'_self');

    }

    var deleteData = function () {

        alert("all the data be deleted");
        $.ajax({
            type:"POST",
            url:"/deleteData",

            success: function(data){
                console.log(data.status);
                ///$('tableRow').remove();
                console.log("removed");
                window.open("/updatedList",'_self');
                //location.reload();

            },
            error: function (errMsg,test) {

                console.log(errMsg.status+"failed");
            }

        });

    }
</script>
<script>
    function addRowHandlers() {
        var table = document.getElementById("dtBasicExample");
        var rows = table.getElementsByTagName("tr");
        for (i = 0; i < rows.length; i++) {
            var currentRow = table.rows[i];
            var createClickHandler =
                function(row)
                {
                    return function() {
                        var cell = row.getElementsByTagName("td")[0];
                        var id = cell.innerHTML;
                        alert("id:" + id);
                    };
                };

            currentRow.onclick = createClickHandler(currentRow);
        }
    }
    window.onload = addRowHandlers();

</script>
<script>
    function rowDeleteButton(feedUrl) {

        $.ajax({

            type:"DELETE",
            url: feedUrl,

            success: function(){
                $('tableRow').remove();

                window.open("/updatedList",'_self');
            },

            failure: function (errMsg) {
                console.log(errMsg.toString())

            }
        });

    }
</script>
<script>
    var urlvalidation = <c:out value="${validVal}"/>
    if(urlvalidation!==false){
        //alert("success");
    }
    else{

        alert("Something Went Wrong! Please check the provided url again");
    }

</script>
</body>

</html>

<!-- /.container -->