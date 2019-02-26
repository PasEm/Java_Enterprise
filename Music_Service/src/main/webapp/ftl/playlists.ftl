<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script src="/js/scripts.js"></script>
<script src="../howler.js-master/src/howler.core.js"></script>
<script src='/js/siriwave.js'></script>
<script src='/js/player.js'></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/css/menu.css">
<link rel="stylesheet" type="text/css" href="/css/playlist.css">
<head>
    <title>playlists</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body onload="getProfileDropdown('load')">
<nav class="navbar navbar-light sticky-top flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-sm-2 col-md-2" href="/main/ftl">MusicService</a>
    <div class="input-group input-group-sm">
        <input type="search" class="search-form-control form-control" aria-label="Search" placeholder='${locale["navbar.search"]}'>
        <a class="input-group-prepend" href="#">
            <span class="input-group-text search-form"><img height="24" width="24" src="../svg/baseline-search-24px.svg"/></span>
        </a>
    </div>
    <div class="d-flex px-3 col-md-0">
        <div class="dropdown mr-1 dropleft" id="dropdownMenu">

        </div>
    </div>
</nav>
<div class="container-fluid" >
    <div class="row">
        <nav class="col-md-2 d-none d-md-block sidebar sticky">
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
                        <a class="nav-link " href="#">
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
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4"><div class="chartjs-size-monitor" style="position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;"><div class="chartjs-size-monitor-expand" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;"><div style="position:absolute;width:1000000px;height:1000000px;left:0;top:0"></div></div><div class="chartjs-size-monitor-shrink" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;"><div style="position:absolute;width:200%;height:200%;left:0; top:0"></div></div></div>
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-4 border-bottom">
                <h1 class="h2">${locale["usercollection.title"]}</h1>
            </div>
            <div class="form-group row">
                <label for="searchPlaylist" class="col-sm-1 col-form-label col-form-label-lg">${locale["usercollection.playlist"]}</label>
                <div class="col-sm-3 mb-3">
                    <input type="search" class="form-control dropdown-toggle" style="height: 120%" aria-label="Search"
                           id="searchPlaylist" onkeyup="searchPlaylist(document.getElementById('searchPlaylist').value)">
                    <div id="playlist_table">

                    </div>
                </div>
                <div class="col">
                    <button type="submit" class="btn btn-lg btn-secondary mb-2" onclick="seePlaylistTracks()">${locale["usercollection.see"]}</button>
                </div>
                <div class="col">
                    <button type="submit" class="btn btn-lg btn-secondary mb-2" onclick="playPlaylist()">Play</button>
                </div>
            </div>
            <div id="table">

            </div>
            <div id="playlist">

            </div>
        </main>
    </div>
</div>
</body>
</html>