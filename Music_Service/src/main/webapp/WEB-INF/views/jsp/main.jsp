<%@ page import="ru.itis.models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/menu.css">
<head>
    <title>main</title>
</head>
<script>
    function loadTracks() {
        $.ajax({
            type: 'post',
            url: '/main',
            data: {
                trackList : document.getElementById("mainList").innerHTML
            }
        }).done(function (data) {
            let contentTableHTML = "";
            for (let i = 0; i < data.length;) {
                contentTableHTML += "<div class='row'>";
                for (let j = 0; j < 4, i < data.length; j++, i++) {
                    contentTableHTML += "<div class='col mb-3'><div class='card lg-2 md-3'>";
                    contentTableHTML += "<img class='card-img-top' style='width: 250px; height: 120px;' src='#' alt='" + data[i].name+ "'>";
                    contentTableHTML += "<div class='card-body'>";
                    contentTableHTML += "<h5 class='card-title'>" + data[i].author.login + "</h5>";
                    contentTableHTML += "<p class='card-text'>" + data[i].name + "</p>";
                    contentTableHTML += "</div><div class='card-footer'>";1
                    contentTableHTML +=  "<small class='text-muted'>Last updated ";
                    contentTableHTML +=  data[i].releaseDate.dayOfMonth + ".";
                    contentTableHTML +=  data[i].releaseDate.monthValue + ".";
                    contentTableHTML +=  data[i].releaseDate.year + "</small>";
                    contentTableHTML += "</div></div></div>";
                }
                contentTableHTML += "</div></div>";
            }
            contentTableHTML +="";
            let contentTableDiv = document.getElementById("mainList");
            contentTableDiv.innerHTML = contentTableHTML;
        });
    }
    function getProfileDropdown(currentUser) {
        let contentMenuHTML = "";
        if (currentUser == null) {
            contentMenuHTML += "<a class='dropdown-item' href='/signIn'>Sign in</a>";
        } else {
            contentMenuHTML += "<a class='dropdown-item' href='/profile'>My profile</a>";
            contentMenuHTML += "<a class='dropdown-item' href='/profile'>Edit profile</a>";
            contentMenuHTML += "<div class='dropdown-divider'></div>";
            contentMenuHTML += "<a class='dropdown-item' href='/main'>Sign out</a>";
        }
        let contentMenuDiv = document.getElementById("dropdownMenu");
        contentMenuDiv.innerHTML = contentMenuHTML;
    }
</script>
<body onload="loadTracks()">
<nav class="navbar navbar-light sticky-top flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-sm-2 col-md-2" href="#">MusicService</a>
    <div class="input-group input-group-sm">
        <input type="search" class="search-form-control form-control" id="search" aria-label="Search" placeholder='${locale.get("navbar.search")}'>
        <a class="input-group-prepend" href="#">
            <span class="input-group-text search-form" ><img height="24" width="24" src="../svg/baseline-search-24px.svg"/></span>
        </a>
    </div>
    <ul class="navbar-nav px-3 col-md-0">
        <li class="nav-item dropdown">
            <button class="btn btn-light dropdown-toggle" id="profileDropdown" type="submit" role="button"
                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <%
                    User currentUser = (User) request.getAttribute("creator");
                    String login = (currentUser == null) ? "Sign in" : currentUser.getLogin();
                %> <%=login%>
            </button>
            <div class="dropdown-menu" id="dropdownMenu" style="margin-top: 10.2rem" aria-labelledby="profileDropdown">
                <a class="dropdown-item" href="/profile">My profile</a>
                <a class="dropdown-item" href="/profile">Edit profile</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="/signIn">Sign in - don`t work correctly</a>
                <a class="dropdown-item" href="#">Log out - don`t work</a>
            </div>
        </li>
    </ul>
</nav>
<div class="container-fluid" >
    <div class="row">
        <nav class="col-md-2 d-none d-md-block sidebar" >
            <div class="sidebar-sticky">
                <ul class="nav flex-column mb-3 border-bottom">
                    <li class="nav-item">
                        <a class="nav-link " href="/main">
                            <img height="24" width="24" src="../svg/baseline-home-24px.svg"/>
                            ${locale.get("navbar.main")}<span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link " href="/events">
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
        <main role="main" class="col-md-10 ml-sm-auto col-lg-10 px-4">
            <div class='container d-flex'>
                <div class="card-deck lg-10 md-10" id="mainList">

                </div>
            </div>
        </main>
    </div>
</div>
</body>
</html>