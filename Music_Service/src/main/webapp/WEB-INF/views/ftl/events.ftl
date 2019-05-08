<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script src="/resources/js/scripts.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/resources/css/menu.css">
<head>
    <title>events</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<#include "dropdown.ftl" parse=true>
<div class="container-fluid" >
    <div class="row">
        <#include "navbar.ftl" parse=true>
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