<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script src="/js/main.js"></script>

<link rel="stylesheet" type="text/css" href="/css/menu.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">

<head>
    <title>main</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<nav class="navbar navbar-light sticky-top flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-sm-2 col-md-2" href="#">Schedule</a>
    <div class="d-flex px-3 col-md-0">
        <button type="button" class="btn" data-toggle="modal"
                data-target="#modalLesson" data-whatever="">Change lessons</button>

        <div class="modal fade" id="modalLesson" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="modalLabel"> </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="form-group">
                                <label for="lesson-name" class="col-form-label">Lesson:</label>
                                <input type="text" class="form-control" id="lesson-name">
                            </div>
                            <div class="form-group">
                                <label for="date" class="col-form-label">Date:</label>
                                <textarea class="form-control" id="date"></textarea>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Send message</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <main role="main" class="col-md-10 ml-sm-auto col-lg-10 px-0">
        <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-4 border-bottom">
            <h1 class="h2 text-align-center">Schedule</h1>
        </div>
        <div class="form-group row">
            <label for="searchCourse" class="col-sm-2 col-form-label col-form-label-lg">Course</label>
            <div class="col-sm-4 mb-3">
                <input type="search" class="form-control dropdown-toggle" style="height: 120%" aria-label="Search"
                       id="searchInput" onkeyup="searchCourse(document.getElementById('searchInput').value)">
                <div id="course_table">

                </div>
            </div>
            <div class="col">
                <button class='btn btn-lg btn-secondary mb-2' name="visitButton"
                        type="button" id="visitButton" onclick="createAttendance()">
                See attendance
                </button>
            </div>
        </div>
        <div class="col-mb-3 text-center">
            <div id="visits_table" class="table-responsive">

            </div>
        </div>
    </main>
</div>
</body>
