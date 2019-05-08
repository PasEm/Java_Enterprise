<%@ page import="ru.itis.models.Playlist" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ru.itis.models.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/menu.css">
<head>
    <script
            src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous"></script>
    <title>my_collection</title>
</head>
<script>
    function seePlaylistTracks() {
        $.ajax({
            type: 'post',
            url: '/my_collection',
            data: {
                currentPlaylistName : document.getElementById("inputPlaylist").options[document.getElementById("inputPlaylist").selectedIndex].value
            }
        }).done(function (data) {
            let contentTableHTML = "<table class='table table-hover'><thead class='thead-light'>";
            contentTableHTML +=
                "<tr>" +
                "<th scope='col'>#</th>" +
                "<th scope='col'>Track name</th>" +
                "<th scope='col'>Author</th>" +
                "<th scope='col'>Genre</th>" +
                "<th scope='col'>Duration</th>" +
                "</tr></thead><tbody>";
            for (let i = 0; i < data.length; i++) {
                contentTableHTML += "<tr>";
                contentTableHTML += "<th scope='row'>" + (i + 1) + "</th>";
                contentTableHTML += "<td>" + data[i].name + "</td>";
                contentTableHTML += "<td>" + data[i].author.login + "</td>";
                contentTableHTML += "<td>" + data[i].genre + "</td>";
                let time = parseInt(data[i].duration / 60) + ":";
                if (data[i].duration % 60 < 10)
                    time += "0";
                time += data[i].duration % 60;
                contentTableHTML += "<td>" + time + "</td>";
                contentTableHTML += "</tr>";
            }
            contentTableHTML += "</tbody></table>";
            let contentTableDiv = document.getElementById("table");
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
            <span class="input-group-text search-form"><img height="24" width="24" src="../svg/baseline-search-24px.svg"/></span>
        </a>
    </div>
    <ul class="navbar-nav px-3 col-md-0">
        <li class="nav-item text-nowrap">
            <button class="btn btn-light" type="submit">
                <%
                    User currentUser = (User) request.getAttribute("creator");
                    String login = (currentUser == null) ? "Sign in" : currentUser.getLogin();
                %> <%=login%>
            </button>
        </li>
    </ul>
</nav>
<div class="container-fluid" >
    <div class="row">
        <nav class="col-md-2 d-none d-md-block sidebar sticky">
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
                        <a class="nav-link " href="#">
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
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4"><div class="chartjs-size-monitor" style="position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;"><div class="chartjs-size-monitor-expand" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;"><div style="position:absolute;width:1000000px;height:1000000px;left:0;top:0"></div></div><div class="chartjs-size-monitor-shrink" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;"><div style="position:absolute;width:200%;height:200%;left:0; top:0"></div></div></div>
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-4 border-bottom">
                <h1 class="h2">${locale.get("usercollection.title")}</h1>
            </div>
            <div class="form-group row">
                <label for="inputPlaylist" class="col-sm-1 col-form-label col-form-label-lg">${locale.get("usercollection.playlist")}</label>
                <div class="col-sm-4 mb-3">
                    <select id="inputPlaylist" class="form-control form-control-lg" name="playlistName">
                        <%
                            ArrayList<Playlist> playlists = (ArrayList<Playlist>) request.getAttribute("playlists");
                            for (Playlist playlist: playlists) {
                                %>
                                    <option> <%= playlist.getName() %> </option>
                                <%
                            }
                        %>
                    </select>
                </div>
                <div class="col">
                    <button type="submit" class="btn btn-lg btn-secondary mb-2" onclick="seePlaylistTracks()">${locale.get("usercollection.see")}</button>
                </div>
            </div>
            <div id="table">

            </div>
        </main>
    </div>
</div>
</body>
</html>