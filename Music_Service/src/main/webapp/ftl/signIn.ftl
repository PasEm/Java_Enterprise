<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/css/menu.css">
<head>
    <title>Log In</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<form method='post' style="position: absolute; padding-top: 4%; padding-left: 30%; padding-right: 30%; width: 100%;">
    <div class="container">
        <div class="form-group row">
            <div class="col">
                <div class="card shadow-lg">
                    <div class="card-header" style="background-color: #A779E9; color:white; text-align: center">
                        <h1>${locale["signin.authorisation"]}</h1>
                    </div>
                    <div class="card-body">
                        <div class="input-group mb-2">
                            <div class="input-group-prepend">
                                <span class="input-group-text" style="background-color: #A779E9; color: white">${locale["signin.login"]}</span>
                            </div>
                            <input type='text' class='form-control' name='login' placeholder='${locale["signin.login.message"]}'>
                        </div>
                        <div class="input-group mb-4">
                            <div class="input-group-prepend">
                                <span class="input-group-text" style="background-color: #A779E9; color: white">${locale["signin.password"]}</span>
                            </div>
                            <input type='password' class='form-control' name='password' placeholder='${locale["signin.password.message"]}'>
                        </div>
                        <div class="input-group mb-1">
                            <button class="btn btn-lg active" role="button"
                                    style="margin: auto; float: none; background-color: #A779E9; color: white;" type="submit" aria-pressed="true">
                                <b>${locale["signin.signin"]}</b>
                            </button>
                        </div>
                    </div>
                    <div class="card-footer text-muted-centered" style="text-align: center">
                        <a href="/signUp/ftl">${locale["signin.signup"]}</a>
                        <br>
                        <a href="/signIn/ftl?lang=Ru">RU</a>|<a href="/signIn/ftl?lang=En">EN</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>