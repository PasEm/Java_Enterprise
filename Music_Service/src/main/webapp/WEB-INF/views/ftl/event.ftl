<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script src="/resources/js/scripts.js"></script>
<script src="/resources/howler.js-master/src/howler.core.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/resources/css/menu.css">
<head>
    <title>event</title>
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
                <h1 class="h2">${event.name}</h1>
            </div>
            <div class="jumbotron p-3 p-md-5 text-white rounded bg-secondary">
                <div class="col-md-6 px-0">
                    <h1 class="display-4 font-italic">${event.name}</h1>
                    <p class="lead my-3">${event.city}, ${event.country}</p>
                    <p class="lead my-3">${event.date}</p>
                    <p class="lead mb-0"><a href="#" class="text-white font-weight-bold">${event.saleSite}</a></p>
                </div>
            </div>
            <#if event.participants ??>
                <ul class="list-unstyled">
                    <h3>${locale["event.participants"]}:</h3>
                    <#list event.participants as participant>
                        <li class="media p-3">
                            <img class="mr-3" style="height: 64px; width: 64px;"
                                 src="${participant.avatar}" alt='${participant.login}'>
                            <div class="media-body">
                                <h5 class="mt-2 mb-1">${participant.login}</h5>
                            </div>
                        </li>
                    </#list>
                </ul>
            </#if>
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-4 border-bottom">
                <h3 class="h2 text-align-center">${locale["comment.comments"]}</h3>
            </div>
            <#if event.comments ??>
                <ul class="list-unstyled" id="comments">
                    <#list event.comments as comment>
                        <li class="media p-3">
                            <img class="mr-3" style="height: 64px; width: 64px;" src="${comment.user.avatar}"
                                    alt='${comment.user.login}'>
                            <div class="media-body">
                                <h5 class="mt-1 mb-1">${comment.user.login}</h5>
                                ${comment.description}
                            </div>
                        </li>
                    </#list>
                </ul>
            </#if>
            <div class="form-group">
                <textarea class="form-control" id="formControl" rows="2" placeholder='${locale["comment.writing"]}'></textarea>
            </div>
                <div class="input-group-append mb-3">
                    <button class="btn btn-outline-secondary btn-block"
                            type="button" id="button-addon2" onclick="addComment('1105')">${locale["comment.send"]}</button>
                </div>
            </div>
        </main>
    </div>
</div>
</body>
</html>