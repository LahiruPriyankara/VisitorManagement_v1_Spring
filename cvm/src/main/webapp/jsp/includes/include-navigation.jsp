<%@page import="com.company.common.ApplicationConstants"%>


    <%    String sURLPrefixx = request.getContextPath();    %>

<nav class="navbar navbar-default" style="background-color: #ff8533">
    <div class="container-fluid">
        <div class="navbar-header">
            <a href="#" id="pageTitle" onclick="clickOnTab('<%=sURLPrefixx%>/authonticate/LoadDashboard')" style="text-decoration:none">
                <img style=" border-radius: 10%;margin-right: 2px" alt="plusMark" width="50" height="50" src="${pageContext.request.contextPath}/ui/images/sampathLogo.jpg"/>
                <span style="color: #FFFFFF;"><b><i>&nbsp;&nbsp;&nbsp;Digital Employee Directory &nbsp;&nbsp;&nbsp;</i></b></span>
            </a>

        </div>
        <ul class="nav navbar-nav">
            <li><a class="titleLink" href="#" id="home" onclick="clickOnTab('<%=sURLPrefixx%>/authonticate/LoadDashboard')" style="background-color: #cc5200;margin-right: 1px;color: #ffffff;width: 150px;text-align: center"><b>Home</b></a></li>
            <li><a class="titleLink" href="#" id="allEmployee" onclick="clickOnTab('<%=sURLPrefixx%>/CompanyEmployee/ExistingEmp')" style="background-color: #ff751a;margin-right: 1px;color: #ffffff;width: 150px;text-align: center"><b>Bank Employee</b></a></li>
            <li><a class="titleLink" href="#" id="depEmp" onclick="clickOnTab('<%=sURLPrefixx%>/CompanyDepEmployee/ExistingEmp')" style="background-color: #ff751a;margin-right: 1px;color: #ffffff;width: 150px;text-align: center"><b>DEP Employee</b></a></li>
            <li><a class="titleLink" href="#" id="cmnUsr" onclick="clickOnTab('<%=sURLPrefixx%>/CommonEntererAuth/ExistingCommonEntererAuths')" style="background-color: #ff751a;margin-right: 1px;color: #ffffff;width: 150px;text-align: center"><b>Common User</b></a></li>
            <li><a class="titleLink" href="#" id="fdUsr" onclick="clickOnTab('<%=sURLPrefixx%>/FrontDeskUser/ExistingFrontDeskUsers')" style="background-color: #ff751a;margin-right: 1px;color: #ffffff;width: 150px;text-align: center"><b>FD User</b></a></li>

            <!-- <li><a class="titleLink" href="#" onclick="clickOnTab('4')" style="background-color: #ff751a;margin-right: 1px;color: #ffffff;width: 150px;text-align: center"><b>Password Reset</b></a></li> -->
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="<%=sURLPrefixx%>authonticate/UserLogout" style="background-color: #ff751a;margin-right: 1px;color: #ffffff"><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
        </ul>
    </div>
</nav>