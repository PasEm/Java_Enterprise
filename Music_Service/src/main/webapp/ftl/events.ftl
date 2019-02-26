<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script src="/js/scripts.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/css/menu.css">
<head>
    <title>events</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body onload="getProfileDropdown('load')">
<nav class="navbar navbar-light sticky-top flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-sm-2 col-md-2" href="/main/ftl">MusicService</a>
    <div class="input-group input-group-sm">
        <input type="search" class="search-form-control form-control" aria-label="Search" placeholder='${locale["navbar.search"]}'>
        <a class="input-group-prepend" href="#">
            <span class="input-group-text  search-form"><img height="24" width="24" src="../svg/baseline-search-24px.svg"/></span>
        </a>
    </div>
    <div class="d-flex px-3 col-md-0">
        <div class="dropdown mr-1 dropleft" id="dropdownMenu">

        </div>
    </div>
</nav>
<div class="container-fluid" >
    <div class="row">
        <nav class="col-md-2 d-none d-md-block sidebar">
            <div class="sidebar-sticky">
                <ul class="nav flex-column mb-3 border-bottom">
                    <li class="nav-item">
                        <a class="nav-link " href="/main/ftl">
                            <img height="24" width="24" src="../svg/baseline-home-24px.svg"/>
                        ${locale["navbar.main"]}<span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link " href="#">
                            <img height="24" width="24" src="../svg/baseline-place-24px.svg"/>
                        ${locale["navbar.events"]}<span class="sr-only">(current)</span>
                        </a>
                    </li>
                </ul>
                <ul class="nav flex-column mb-3 border-bottom">
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <img height="24" width="24" src="../svg/baseline-schedule-24px.svg"/>
                        ${locale["navbar.history"]}<span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link " href="/my_collection/ftl">
                            <img height="24" width="24" src="../svg/baseline-library_music-24px.svg"/>
                        ${locale["navbar.playlists"]}<span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link " href="#">
                            <img height="24" width="24" src="../svg/baseline-subscriptions-24px.svg"/>
                        ${locale["navbar.subscriptions"]}<span class="sr-only">(current)</span>
                        </a>
                    </li>
                </ul>
                <ul class="nav flex-column mb-3">
                    <li class="nav-item">
                        <a class="nav-link" href="/profile/ftl">
                            <img height="24" width="24" src="../svg/baseline-schedule-24px.svg"/>
                        ${locale["navbar.settings"]}<span class="sr-only">(current)</span>
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-4 border-bottom">
                <h1 class="h2 text-align-center">${locale["events.events"]}</h1>
            </div>
            <div class="form-group row">
                <label for="inputSearch" class="col-sm-2 col-form-label col-form-label-lg">${locale["events.participant"]}</label>
                <div class="col-sm-4 mb-3">
                    <input type="search" class="form-control dropdown-toggle" style="height: 120%" aria-label="Search"
                           id="inputSearch" onkeyup="searchParticipants(document.getElementById('inputSearch').value)">
                    <div id="participants_table">

                    </div>
                </div>
                <div class="col">
                    <button class='btn btn-lg btn-secondary dropdown-toggle mb-2' name="eventButton"
                            type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    ${locale["event.button"]}
                    </button>
                    <div class="dropdown-menu" id="dropdownMenu" aria-labelledby="profileDropdown">
                        <button class="dropdown-item disabled" type="button" onclick="addParticipant()">${locale["event.search"]}</button>
                        <button class="dropdown-item disabled" type="button" id='clearList' value="clearList" onclick="clearSearch()">
                        ${locale["event.clear"]}
                        </button>
                        <div class="dropdown-divider"></div>
                        <button class="dropdown-item disabled" type="button" id='seeEvents' value='seeEvents' onclick='seeEvents()'>
                        ${locale["event.find"]}
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