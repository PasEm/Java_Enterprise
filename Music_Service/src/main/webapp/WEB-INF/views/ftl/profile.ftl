<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script src="/resources/js/scripts.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/resources/css/menu.css">
<head>
    <title>profile</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<#include "dropdown.ftl" parse=true>
<div class="container-fluid" >
    <div class="row">
        <#include "navbar.ftl" parse=true>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-3 mb-5 border-bottom">
                <h1 class="h2 text-align-center">${locale["profile.profile"]}</h1>
            </div>
            <form method="post" class="col-md-6 ml-sm-auto mx-auto col-lg-6" id="data">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon1">@</span>
                    </div>
                    <input type="email" class="form-control" name="email" placeholder="${user.email}"
                           aria-label="Username" aria-describedby="basic-addon1">
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="addon_name">${locale["profile.firstname"]}</span>
                    </div>
                    <input type="text" class="form-control" name="first_name"
                        <#if user.firstName??>
                           placeholder="${user.firstName}"
                           <#else>placeholder=""
                        </#if>
                    aria-label="username">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="addon_surname">${locale["profile.surname"]}</span>
                    </div>
                    <input type="text" class="form-control" name="surname"
                        <#if user.surname??>
                           placeholder="${user.surname}"
                           <#else>placeholder=""
                        </#if>
                    aria-label="surname">
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="addon_country">${locale["profile.country"]}</span>
                    </div>
                    <input type="text" class="form-control" name="country"
                        <#if user.country??>
                           placeholder="${user.country}"
                           <#else>placeholder=""
                        </#if>
                    aria-label="country">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="addon_birthdate">${locale["profile.birthdate"]}</span>
                    </div>
                    <input type="date" class="form-control" name="birthdate" placeholder="" aria-label="birthdate">
                </div>
                <p class="text-center">Your password</p>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon3">${locale["profile.last.password"]}</span>
                    </div>
                    <input type="password" class="form-control" name="lastPassword" id="password_last" aria-describedby="basic-addon3">
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">${locale["profile.new.password"]}</span>
                    </div>
                    <input type="password" class="form-control" name="newPassword" id="password_new" aria-describedby="basic-addon3">
                </div>
                <button type="submit" role="button" class="btn btn-secondary btn-lg btn-block">${locale["profile.confirm"]}</button>
            </form>
        </main>
    </div>
</div>
</body>
</html>