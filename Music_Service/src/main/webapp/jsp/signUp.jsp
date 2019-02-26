<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<head>
    <title>Registration</title>
</head>
<body>
    <form method='post' style="position: absolute; padding-top: 4%; padding-left: 30%; padding-right: 30%; width: 100%;">
        <div class="container">
            <div class="form-group row">
                <div class="col">
                    <div class="card shadow-lg">
                        <div class="card-header" style="background-color: #A779E9; color:white; text-align: center">
                            <h1>${locale.get("signup.registration")}</h1>
                        </div>
                        <div class="card-body">
                            <div class="input-group mb-2">
                               <div class="input-group-prepend">
                                    <span class="input-group-text" style="background-color: #A779E9; color: white">${locale.get("signup.email")}</span>
                               </div>
                                <input type='email' class='form-control' name='email' placeholder='${locale.get("signup.email.message")}'>
                            </div>
                            <div class="input-group mb-2">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" style="background-color: #A779E9; color: white">${locale.get("signup.login")}</span>
                                </div>
                                <input type='text' class='form-control' name='login' placeholder='${locale.get("signup.login.message")}'>
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" style="background-color: #A779E9; color: white">${locale.get("signup.password")}</span>
                                </div>
                                <input type='password' class='form-control' name='password' placeholder='${locale.get("signup.password.message")}'>
                            </div>
                            <div class="card mb-3">
                                <div class="card-header" style="background-color: #A779E9; color: white; text-align: center;">
                                    ${locale.get("signup.personalData")}
                                </div>
                                <div class="card-body" id="personalData">
                                    <div class="row mb-2">
                                        <div class="col">
                                            <input type='text' class='form-control' name='first_name' placeholder='${locale.get("signup.first_name")}'>
                                        </div>
                                        <div class="col">
                                            <input type='text' class='form-control' name='surname' placeholder='${locale.get("signup.surname")}'>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col">
                                            <input type='text' class='form-control' name='country' placeholder='${locale.get("signup.country")}'>
                                        </div>
                                        <div class="col">
                                            <input type='date' class='form-control' name='date' placeholder=''>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="input-group mb-0">
                                <button class="btn btn-lg active" role="button"
                                        style="margin: auto; float: none; background-color: #A779E9; color: white;" type="submit" aria-pressed="true">
                                    <b>${locale.get("signup.signup")}</b>
                                </button>
                            </div>
                        </div>
                        <div class="card-footer text-muted-centered" style="text-align: center">
                            <a href="/signIn">${locale.get("signup.signin")}</a>
                            <br>
                            <a href="/signUp?lang=Ru">RU</a>|<a href="/signUp?lang=En">EN</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</body>
</html>