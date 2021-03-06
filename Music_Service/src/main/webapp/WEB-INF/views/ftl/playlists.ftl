<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script src="/resources/js/scripts.js"></script>
<script src="/resources/howler.js-master/src/howler.core.js"></script>
<script src='/resources/js/siriwave.js'></script>
<script src='/resources/js/player.js'></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/resources/css/menu.css">
<link rel="stylesheet" type="text/css" href="/resources/css/playlist.css">
<head>
    <title>playlists</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<#include "dropdown.ftl" parse=true>
<div class="container-fluid" >
    <div class="row">
        <#include "navbar.ftl" parse=true>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="chartjs-size-monitor" style="position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;">
                <div class="chartjs-size-monitor-expand" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;">
                    <div style="position:absolute;width:1000000px;height:1000000px;left:0;top:0"></div>
                </div>
                <div class="chartjs-size-monitor-shrink" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;">
                    <div style="position:absolute;width:200%;height:200%;left:0; top:0"></div>
                </div>
            </div>
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