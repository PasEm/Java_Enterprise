<%@ page import="ru.itis.models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/menu.css">
<head>
    <title>profile</title>
</head>
    <script>
        function saveData() {
            $.ajax({
                type: 'post',
                url: '/profile',
                data: {

                }
            }).done(function (data) {
                if (data)
                    alert("You previous password isn`t correct");
            });
        }
    </script>
<body>
<nav class="navbar navbar-light sticky-top flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-sm-2 col-md-2" href="/main">MusicService</a>
    <div class="input-group input-group-sm mr-5">
        <input type="search" class="search-form-control form-control" aria-label="Search" placeholder='${locale.get("navbar.search")}'>
        <a class="input-group-prepend" href="#">
            <span class="input-group-text  search-form" ><img height="24" width="24" src="../svg/baseline-search-24px.svg"/></span>
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
        <nav class="col-md-2 d-none d-md-block sidebar">
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
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-3 mb-5 border-bottom">
                <h1 class="h2 text-align-center">${locale.get("profile.profile")}</h1>
            </div>
            <form method="post" class="col-md-6 ml-sm-auto mx-auto col-lg-6" id="data">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon1">@</span>
                    </div>
                    <input type="email" class="form-control" name="email" placeholder="<%=currentUser.getEmail()%>" aria-label="Username" aria-describedby="basic-addon1">
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="addon_name">${locale.get("profile.firstname")}</span>
                    </div>
                    <input type="text" class="form-control" name="first_name" placeholder="<%=currentUser.getFirstName()%>" aria-label="username">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="addon_surname">${locale.get("profile.surname")}</span>
                    </div>
                    <input type="text" class="form-control" name="surname" placeholder="<%=currentUser.getSurname()%>" aria-label="surname">
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="addon_country">${locale.get("profile.country")}</span>
                    </div>
                    <input type="text" class="form-control" name="country" placeholder="<%=currentUser.getCountry()%>" aria-label="country">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="addon_birthdate">${locale.get("profile.birthdate")}</span>
                    </div>
                    <input type="date" class="form-control" name="birthdate" placeholder="" aria-label="birthdate">
                </div>
                <p class="text-center">Your password</p>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon3">${locale.get("profile.last.password")}</span>
                    </div>
                    <input type="password" class="form-control" name="last_password" id="password_last" aria-describedby="basic-addon3">
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">${locale.get("profile.new.password")}</span>
                    </div>
                    <input type="password" class="form-control" name="new_password" id="password_new" aria-describedby="basic-addon3">
                </div>
                <button type="submit" role="button" class="btn btn-secondary btn-lg btn-block">${locale.get("profile.confirm")}</button>
            </form>
        </main>
    </div>
</div>
</body>
</html>
