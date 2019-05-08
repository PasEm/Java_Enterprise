<!DOCTYPE html>
<html>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script src="/resources/js/scripts.js"></script>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<head>
    <title>main</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/resources/css/menu.css">
</head>
<body onload="loadTracks()">
<#include "dropdown.ftl" parse=true>
<div class="container-fluid" >
    <div class="row">
        <#include "navbar.ftl" parse=true>
        <main role="main" class="col-md-10 ml-sm-auto col-lg-10 px-4">
            <div class='container d-flex'>
                <div class="card-deck lg-10 md-10" id="mainList">

                </div>
            </div>
        </main>
    </div>
</div>
</body>