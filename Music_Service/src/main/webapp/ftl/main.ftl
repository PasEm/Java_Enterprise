<!DOCTYPE html>
<html>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script src="/js/scripts.js"></script>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/css/menu.css">
<head>
    <title>main</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body onload="start()">
<nav class="navbar navbar-light sticky-top flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-sm-2 col-md-2" href="#">MusicService</a>
    <div class="input-group input-group-sm">
        <input type="search" class="search-form-control form-control" id="search" aria-label="Search" placeholder='${locale['navbar.search']}'>
        <a class="input-group-prepend" href="#">
            <span class="input-group-text search-form" ><img height="24" width="24" src="../svg/baseline-search-24px.svg"/></span>
        </a>
    </div>
    <div class="d-flex px-3 col-md-0">
        <div class="dropdown mr-1 dropleft" id="dropdownMenu">

        </div>
    </div>
</nav>
<div class="container-fluid" >
    <div class="row">
        <nav class="col-md-2 d-none d-md-block sidebar" >
            <div class="sidebar-sticky">
                <ul class="nav flex-column mb-3 border-bottom">
                    <li class="nav-item">
                        <a class="nav-link " href="/main/ftl">
                            <img height="24" width="24" src="../svg/baseline-home-24px.svg"/>
                        ${locale["navbar.main"]}<span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link " href="/events/ftl">
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
        <main role="main" class="col-md-10 ml-sm-auto col-lg-10 px-4">
            <div class='container d-flex'>
                <div class="card-deck lg-10 md-10" id="mainList">

                </div>
            </div>
        </main>
    </div>
</div>
</body>