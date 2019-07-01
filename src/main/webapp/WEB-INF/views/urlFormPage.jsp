<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<style>
    .header img {
        float: left;
        width: 44px;
        height: 44px;
    }

    .header h1 {
        width:100%;
        text-align: Center;
        background: #1abc9c;
        color: white;
        font-size: 20px;
        padding: 10px;
        font-family: sans-serif;
        font-weight: 800;
    }
    .select-css {
        display: block;
        font-size: 16px;
        font-family: sans-serif;
        font-weight: 700;
        color: #444;
        line-height: 1.3;
        padding: .6em 1.4em .5em .8em;
        width: 100%;
        max-width: 100%;
        box-sizing: border-box;
        margin: 0;
        border: 1px solid #aaa;
        box-shadow: 0 1px 0 1px rgba(0,0,0,.04);
        border-radius: .5em;
        -moz-appearance: none;
        -webkit-appearance: none;
        appearance: none;
        background-color: #fff;
        background-image: url('data:image/svg+xml;charset=US-ASCII,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%22292.4%22%20height%3D%22292.4%22%3E%3Cpath%20fill%3D%22%23007CB2%22%20d%3D%22M287%2069.4a17.6%2017.6%200%200%200-13-5.4H18.4c-5%200-9.3%201.8-12.9%205.4A17.6%2017.6%200%200%200%200%2082.2c0%205%201.8%209.3%205.4%2012.9l128%20127.9c3.6%203.6%207.8%205.4%2012.8%205.4s9.2-1.8%2012.8-5.4L287%2095c3.5-3.5%205.4-7.8%205.4-12.8%200-5-1.9-9.2-5.5-12.8z%22%2F%3E%3C%2Fsvg%3E'),
        linear-gradient(to bottom, #ffffff 0%,#e5e5e5 100%);
        background-repeat: no-repeat, repeat;
        background-position: right .7em top 50%, 0 0;
        background-size: .65em auto, 100%;
    }
    .select-css::-ms-expand {
        display: none;
    }
    .select-css:hover {
        border-color: #888;
    }
    .select-css:focus {
        border-color: #aaa;
        box-shadow: 0 0 1px 3px rgba(59, 153, 252, .7);
        box-shadow: 0 0 0 3px -moz-mac-focusring;
        color: #222;
        outline: none;
    }
    .select-css option {
        font-weight:normal;
    }

    body {
        padding: 3rem;
    }
</style>

<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Colorlib Templates">
    <meta name="author" content="Colorlib">
    <meta name="keywords" content="Colorlib Templates">

    <!-- Title Page-->
    <title>Url Forms</title>

    <!-- Icons font CSS-->
    <link href="vendorForm/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
    <link href="vendorForm/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
    <!-- Font special for pages-->
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Vendor CSS-->
    <link href="vendorForm/select2/select2.min.css" rel="stylesheet" media="all">
    <link href="vendorForm/datepicker/daterangepicker.css" rel="stylesheet" media="all">

    <!-- Main CSS-->
    <link href="cssForm/main.css" rel="stylesheet" media="all">
    <div class="header">
        <h1>Database And Web Techniques</h1>
    </div>
</head>

<body>
<div class="page-wrapper bg-gra-02 p-t-130 p-b-100 font-poppins">
    <div class="wrapper wrapper--w680">
        <div class="card card-4">
            <div class="card-body">
                <label class="label">Please Select Number of Feeds to Display</label>
                        <select class="select-css" name="textDropdown" id="textDropdown">
                            <option disabled="disabled" selected="selected">Choose option</option>
                            <option>One</option>
                            <option>Two</option>
                            <option>Three</option>
                            <option>Four</option>
                            <option>Five</option>
                        </select>
                <br></br>
                <form method="POST" action="/saveDetails">
                    <div class="row row-space">
                        <div class="col-2">
                            <div class="input-group">
                                <label class="label" id="param1Label" style="display: none">Enter First URL</label>
                                <input class="input--style-4" type="text" name="urlParam1" id="param1" style="display: none"/>
                            </div>
                            <div class="input-group">
                                <label class="label" id="param2Label" style="display: none">Enter Second URL</label>
                                <input class="input--style-4" type="text" name="urlParam2" id="param2" style="display: none"/>
                            </div>
                            <div class="input-group">
                                <label class="label" id="param3Label" style="display: none">Enter Third URL</label>
                                <input class="input--style-4" type="text" name="urlParam3" id="param3" style="display: none"/>
                            </div>
                            <div class="input-group">
                                <label class="label" id="param4Label" style="display: none">Enter Fourth URL</label>
                                <input class="input--style-4" type="text" name="urlParam4"  id="param4" style="display: none"/>
                            </div>
                            <div class="input-group">
                                <label class="label" id="param5Label" style="display: none">Enter Fifth URL</label>
                                <input class="input--style-4" type="text" name="urlParam5"  id="param5" style="display: none"/>
                            </div>
                        </div>
                    </div>
                    <div class="p-t-15">
                        <button class="btn btn--radius-2 btn--blue" type="submit" id="submitButton" style="display:none" onclick="validateUrl()">Submit</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Jquery JS-->
<script src="vendorForm/jquery/jquery.min.js"></script>
<!-- Vendor JS-->
<script src="vendorForm/select2/select2.min.js"></script>
<script src="vendorForm/datepicker/moment.min.js"></script>
<script src="vendorForm/datepicker/daterangepicker.js"></script>

<!-- Main JS-->
<script src="jsForm/global.js"></script>

<!-- for the dynamic url selection -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script>

    var textDropdownInput = $('#textDropdown');

    textDropdownInput.on('change', function () {

        var param1Input = $('#param1');
        var param1Label = $('#param1Label');
        var param2Input = $('#param2');
        var param2Label = $('#param2Label');
        var param3Input = $('#param3');
        var param3Label = $('#param3Label');
        var param4Input = $('#param4');
        var param4Label = $('#param4Label');
        var param5Input = $('#param5');
        var param5Label = $('#param5Label');
        var submitButtonInput = $('#submitButton');

        switch ($(this).val()) {

            case 'One':
                param1Input.show();
                param1Label.show();
                param2Input.hide();
                param2Label.hide();
                param3Input.hide();
                param3Label.hide();
                param4Input.hide();
                param4Label.hide();
                param5Input.hide();
                param5Label.hide();
                submitButtonInput.show();
                break;
            case 'Two':
                param1Input.show();
                param1Label.show();
                param2Input.show();
                param2Label.show();
                param3Input.hide();
                param3Label.hide();
                param4Input.hide();
                param4Label.hide();
                param5Input.hide();
                param5Label.hide();
                submitButtonInput.show();
                break;
            case 'Three':
                param1Input.show();
                param1Label.show();
                param2Input.show();
                param2Label.show();
                param3Input.show();
                param3Label.show();
                param4Input.hide();
                param4Label.hide();
                param5Input.hide();
                param5Label.hide();
                submitButtonInput.show();
                break;
            case 'Four':
                param1Input.show();
                param1Label.show();
                param2Input.show();
                param2Label.show();
                param3Input.show();
                param3Label.show();
                param4Input.show();
                param4Label.show();
                param5Input.hide();
                param5Label.hide();
                submitButtonInput.show();
                break;
            case 'Five':
                param1Input.show();
                param1Label.show();
                param2Input.show();
                param2Label.show();
                param3Input.show();
                param3Label.show();
                param4Input.show();
                param4Label.show();
                param5Input.show();
                param5Label.show();
                submitButtonInput.show();
                break;

        }

    });
</script>
</body>

</html>