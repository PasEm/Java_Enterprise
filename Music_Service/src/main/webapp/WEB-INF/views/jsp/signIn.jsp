<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<head>
    <title>Log In</title>
</head>
<body>
    <form method='post' style="position: absolute; padding-top: 4%; padding-left: 30%; padding-right: 30%; width: 100%;">
        <div class="container">
            <div class="form-group row">
                <div class="col">
                    <div class="card shadow-lg">
                        <div class="card-header" style="background-color: #A779E9; color:white; text-align: center">
                            <h1>${locale.get("signin.authorisation")}</h1>
                        </div>
                        <div class="card-body">
                            <div class="input-group mb-2">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" style="background-color: #A779E9; color: white">${locale.get("signin.login")}</span>
                                </div>
                                <input type='text' class='form-control' name='login' placeholder='${locale.get("signin.login.message")}'>
                            </div>
                            <div class="input-group mb-4">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" style="background-color: #A779E9; color: white">${locale.get("signin.password")}</span>
                                </div>
                                <input type='password' class='form-control' name='password' placeholder='${locale.get("signin.password.message")}'>
                            </div>
                            <div class="input-group mb-1">
                                <button class="btn btn-lg active" role="button"
                                        style="margin: auto; float: none; background-color: #A779E9; color: white;" type="submit" aria-pressed="true">
                                    <b>${locale.get("signin.signin")}</b>
                                </button>
                            </div>
                        </div>
                        <div class="card-footer text-muted-centered" style="text-align: center">
                            <a href="/signUp">${locale.get("signin.signup")}</a>
                            <br>
                            <a href="/signIn?lang=Ru">RU</a>|<a href="/signIn?lang=En">EN</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</body>
</html>
