<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
   /* .image {
        position:relative;
        width:400px;
        height:400px;
    }
    .image img {
        width:100%;
        vertical-align:top;
    }
    .image:after {
        content:'\A';
        position:absolute;
        width:100%; height:100%;
        top:0; left:0;
        background:rgba(0,0,0,0.6);
        opacity:0;
        transition: all 0.5s;
        -webkit-transition: all 0.5s;
        opacity:1;
    }
   .image:hover:after {
        opacity:1;
    }*/
</style>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="../includes/include-css-js.jsp"%>
        <script src="<%=request.getContextPath()%>/resources/css/bootstrap/js/bootstrap.min.js"></script>  
        <%@include file="../includes/include-initial-variables.jsp"%>
    </head>
    <%        boolean isPswdReset = objManager.get("isPswdReset") != null ? (boolean) objManager.get("isPswdReset") : false;
    %>
    <body style="background-image: url('${pageContext.request.contextPath}/ui/images/logginPageBackGround.jpg');">
        <div class="container">
            <div class="row" style="margin-top: 1%;">
                <div class="col-sm-2 col-md-2"></div>

                <div class="col-sm-8 col-md-8" style="background-color: #ff751a;padding: 10px;">
                    <div class="row">
                        <div class="col-sm-4 col-md-4">
                            <img alt="plusMark" width="170" height="170" src="${pageContext.request.contextPath}/ui/images/sampathLogo.jpg"/>
                        </div>
                        <div class="col-sm-6 col-md-6">
                            <h3 style="color: #ffffff;text-align: left;padding: 10px"><b>Sampath Bank PLC</b></h3>
                            <h5 style="color: #ffffff;text-align: left;padding: 10px"><b>Digital Employee Directory for Visitor Management</b></h5>
                            <h5></h5>
                        </div>
                    </div>
                </div>
                <div class="col-sm-2 col-md-2" > </div>
            </div>

            <div class="row" style="margin-top: 1%;">
                <div class="col-sm-2 col-md-2"></div>
                <div class="col-sm-8 col-md-8">
                    <%@ include file="../includes/include-notifications.jsp"%>
                </div>
                <div class="col-sm-2 col-md-2" > </div>
            </div>


            <div class="row" style="margin-top: 1%;">
                <div class="col-sm-4 col-md-4" ></div>

                <div class="col-sm-4 col-md-4" style="box-shadow: 0 0 3px 2px #ff751a;">                    
                    <div class="row">
                        <div style="background-color: #ff751a;height: 50px"> 
                            <h3 style="color: #ffffff;text-align: center;padding-top: 10px"><b>Digital Employee Directory</b></h3>
                        </div>
                    </div>

                    <div style="background-color: #ffffff">
                        <%if (!isPswdReset) {%>
                        <form class="panel-body" action="<%=sURLPrefix%>/authonticate/UserLogin" method="post">
                            <hr>
                            <div class="custom-control custom-switch">
                                <input type="checkbox" name="cBoxuserType" checked="true">
                                <label class="custom-control-label" for="switch1">Bank User</label>
                            </div>
                            <div class="form-group text-center">
                                <input type="text" id="txtUserId" name="txtUserId" placeholder="User ID" class="form-control fullWidth">
                            </div>
                            <div class="form-group text-center">
                                <input type="password" id="txtPassword" name="txtPassword" placeholder="Password" class="form-control fullWidth">
                            </div>

                            <br>
                            <div class="form-group text-center">
                                <div class="row">
                                    <button type="submit" name="UserLogin" onclick="hideLogginPage()" class="btn btn-success" style="width: 100%">Login</button> </div>
                            </div>
                        </form>
                        <%} else {%>
                        <form class="panel-body" action="<%=sURLPrefix%>/authonticate/SetPassword" method="post">
                            <hr>
                            <div style="float: right;padding-right: 20px">
                                <a href="<%=sURLPrefix%>/authonticate/RedirectLogin" onclick="clickOnTab('10')" > Log In</a>
                            </div>

                            <div class="form-group text-center">
                                <input type="text" name="txtUserId" placeholder="User ID" class="form-control fullWidth">
                            </div>
                            <div class="form-group text-center">
                                <input type="password" id="txtPassword" name="txtPassword" id="txtPassword" placeholder="New password" class="form-control fullWidth" onkeyup="passwordMatch()">
                            </div>
                            <div class="form-group text-center">
                                <input type="password" id="txtConformPassword" name="txtConformPassword" id="txtConformPassword" placeholder="Confirm password" class="form-control fullWidth" onkeyup="passwordMatch()">
                                <div style="float: left;">
                                    <span style="color: #e62e00;font-size: 10px;margin-left: 10px;display: none" id="pswdMistmatchErrMsg"><i>passwords are not match.</i></span>
                                </div>
                            </div>
                            <br>
                            <div class="form-group text-center">
                                <div class="row">
                                    <button type="submit" name="SetPassword" onclick="hideLogginPage()" class="btn btn-success" style="width: 100%">Login</button> </div>
                            </div>
                        </form>

                        <%}%>



                    </div>
                    <br>
                </div>
                <div class="col-sm-4 col-md-4" ></div>
            </div>

            <%if (isPswdReset) {%>
            <br>
            <div class="row" style="margin-top: 1%;">
                <div class="col-sm-2 col-md-2"></div>

                <div class="col-sm-8 col-md-8" style="text-align: center;">
                    <p><i>The length of the Passwords must be between 8 and 15 characters</i></p>
                    <p><i>There has to be at least one upper case character in the Password i.e. A, B . . . Y, Z</i></p>
                    <p><i>There has to be at least one lower case character in the Password i.e. a, b . . . y, z</i></p>
                    <p><i>There has to be at least one number in the Password i.e. 1, 2 . . . 9, 0</i></p>
                    <p><i>There has to be at least one special character in the Password i.e. ` ~ ! @ # $ % ^ & * ( ) _ + = - [ ] { } ; : ’ ” , < > . > / ?</i></p>
                    <p><i>There cannot be more than 2 consecutive repeated characters i.e. xx is allowed but xxx is not allowed</i></p>                 
                </div>
                <div class="col-sm-2 col-md-2" > </div>
            </div>
            <%}%>


            <div class="row" style="margin-top: 8%;">
                <div class="col-sm-2 col-md-2"></div>

                <div class="col-sm-8 col-md-8">
                    <br><br><br>
                    <%@ include file="../includes/include-footer.jsp"%>
                </div>
            </div>
            <div class="col-sm-2 col-md-2" > </div>
        </div>

    </div>
    <!-- Page loading div -->
    <%@ include file="../includes/pageLoading.jsp"%>
</body>
</html>
<script>
    showPage();
    function hideLogginPage() {
        hidePage();
    }
    function passwordMatch() {
        var pswd = document.getElementById('txtPassword').value;
        var confPswd = document.getElementById('txtConformPassword').value;
        //alert(pswd === confPswd);
        if (pswd === confPswd) {
            document.getElementById("pswdMistmatchErrMsg").style.display = "none";
        } else {
            document.getElementById("pswdMistmatchErrMsg").style.display = "block";
        }
    }
</script>