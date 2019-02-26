<%@ page import="ru.itis.models.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/menu.css">
<head>
    <title>events</title>
</head>
    <script>
        function clearSearch() {
            $.ajax({
                type: 'post',
                url: '/events',
                data: {
                    clearing : document.getElementById("clearList").value
                }
            }).done(function (data) {
                let contentTableHTML = "<table class='table table-hover'><thead class='thead-light'>";
                contentTableHTML +=
                    "<tr>" +
                    "<th scope='col'>#</th>" +
                    "<th scope='col'>Participant</th>" +
                    "</tr></thead><tbody>";
                contentTableHTML += "</tbody></table></div>";
                let contentTableDiv = document.getElementById("tableParticipant");
                contentTableDiv.innerHTML = contentTableHTML;
                let contentEventTableDiv = document.getElementById("tableEvent");
                contentEventTableDiv.innerHTML = null;
            });
        }
        function seeEvents() {
            $.ajax({
                type: 'post',
                url: '/events',
                data: {
                    events : document.getElementById("seeEvents").value
                }
            }).done(function (data) {
                let contentTableHTML = "<table class='table table-hover'><thead class='thead-light'>";
                contentTableHTML +=
                    "<tr>" +
                    "<th scope='col'>#</th>" +
                    "<th scope='col'>Event</th>" +
                    "<th scope='col'>Country</th>" +
                    "<th scope='col'>City</th>" +
                    "<th scope='col'>Date</th>" +
                    "<th scope='col'>Sale site</th>" +
                    "</tr></thead><tbody>";
                if (data !== null) {
                    for (let i = 0; i < data.length; i++) {
                        contentTableHTML += "<tr>";
                        contentTableHTML += "<th scope='row'>" + (i + 1) + "</th>";
                        contentTableHTML += "<td>" + data[i].name + "</td>";
                        contentTableHTML += "<td>" + data[i].country + "</td>";
                        contentTableHTML += "<td>" + data[i].city + "</td>";
                        contentTableHTML += "<td>" + data[i].date + "</td>";
                        contentTableHTML += "<td>" + data[i].saleSite + "</td>";
                        contentTableHTML += "</tr>";
                    }
                }
                contentTableHTML += "</tbody></table></div>";
                let contentTableDiv = document.getElementById("tableEvent");
                contentTableDiv.innerHTML = contentTableHTML;
            });
        }
        function addParticipant() {
            $.ajax({
                type: 'post',
                url: '/events',
                data: {
                    currentParticipant : document.getElementById("inputParticipant").options[document.getElementById("inputParticipant").selectedIndex].value
                }
            }).done(function (data) {
                let contentTableHTML = "<table class='table table-hover'><thead class='thead-light'>";
                contentTableHTML +=
                    "<tr>" +
                    "<th scope='col'>#</th>" +
                    "<th scope='col'>Participant</th>" +
                    "</tr></thead><tbody>";
                for (let i = 0; i < data.length; i++) {
                    contentTableHTML += "<tr>";
                    contentTableHTML += "<th scope='row'>" + (i + 1) + "</th>";
                    contentTableHTML += "<td>" + data[i].login + "</td>";
                    contentTableHTML += "</tr>";
                }
                contentTableHTML += "</tbody></table></div>";
                let contentTableDiv = document.getElementById("tableParticipant");
                contentTableDiv.innerHTML = contentTableHTML;
            });
        }
    </script>
<body>
<nav class="navbar navbar-light sticky-top flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-sm-2 col-md-2" href="/main">MusicService</a>
    <div class="input-group input-group-sm">
        <input type="search" class="search-form-control form-control" aria-label="Search" placeholder='${locale.get("navbar.search")}'>
        <a class="input-group-prepend" href="#">
            <span class="input-group-text  search-form"><img height="24" width="24" src="../svg/baseline-search-24px.svg"/></span>
        </a>
    </div>
    <ul class="navbar-nav px-3 col-md-0">
        <li class="nav-item text-nowrap">
            <button class="btn btn-light" type="submit">
                <%
                    User currentUser = (User) request.getAttribute("user");
                    String login = (currentUser == null) ? "Sign in" : currentUser.getLogin();
                %> <%=login%> <%
                %>
            </button>
        </li>
    </ul>
</nav>
<div class="container-fluid" >
    <div class="row">
        <nav class="col-md-2 d-none d-md-block sidebar">
            <div class="sidebar-sticky">
                <ul class="nav flex-column mb-3 border-bottom">
                    <li class="nav-item">
                        <a class="nav-link " href="/main">
                            <img height="24" width="24" src="../svg/baseline-home-24px.svg"/>
                            ${locale.get("navbar.main")}<span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link " href="#">
                            <img height="24" width="24" src="../svg/baseline-place-24px.svg"/>
                            ${locale.get("navbar.events")}<span class="sr-only">(current)</span>
                        </a>
                    </li>
                </ul>
                <ul class="nav flex-column mb-3 border-bottom">
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <img height="24" width="24" src="../svg/baseline-schedule-24px.svg"/>
                            ${locale.get("navbar.history")}<span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link " href="/my_collection">
                            <img height="24" width="24" src="../svg/baseline-library_music-24px.svg"/>
                            ${locale.get("navbar.playlists")}<span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link " href="#">
                            <img height="24" width="24" src="../svg/baseline-subscriptions-24px.svg"/>
                            ${locale.get("navbar.subscriptions")}<span class="sr-only">(current)</span>
                        </a>
                    </li>
                </ul>
                <ul class="nav flex-column mb-3">
                    <li class="nav-item">
                        <a class="nav-link" href="/profile">
                            <img height="24" width="24" src="../svg/baseline-schedule-24px.svg"/>
                            ${locale.get("navbar.settings")}<span class="sr-only">(current)</span>
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-4 border-bottom">
                <h1 class="h2 text-align-center">${locale.get("events.events")}</h1>
            </div>
            <div class="form-group row">
                <label for="inputParticipant" class="col-sm-2 col-form-label col-form-label-lg">${locale.get("events.participant")}</label>
                <div class="col-sm-4 mb-3">
                    <select id="inputParticipant" class="form-control form-control-lg" name="addParticipant">
                        <%
                            ArrayList<User> participants = (ArrayList<User>) request.getAttribute("participants");
                            for (User user: participants) {
                        %>
                        <option> <%= user.getLogin() %> </option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <div class="col">
                    <button class='btn btn-lg btn-secondary dropdown-toggle mb-2' name="eventButton"
                            type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        ${locale.get("event.button")}
                    </button>
                    <div class="dropdown-menu" id="dropdownMenu" aria-labelledby="profileDropdown">
                        <button class="dropdown-item disabled" type="button" onclick="addParticipant()">${locale.get("event.search")}</button>
                        <button class="dropdown-item disabled" type="button" id='clearList' value="clearList" onclick="clearSearch()">
                            ${locale.get("event.clear")}
                        </button>
                        <div class="dropdown-divider"></div>
                        <button class="dropdown-item disabled" type="button" id='seeEvents' value='seeEvents' onclick='seeEvents()'>
                            ${locale.get("event.find")}
                        </button>
                    </div>
                </div>
            </div>
            <div id="tableParticipant" class="col-mb-3">

            </div>
            <div id="tableEvent">

            </div>
        </main>
    </div>
</div>
</body>
</html>
