function searchCourse(query) {
    $.ajax({
        type: 'get',
        url: '/main',
        data: {
            courses: query
        }
    }).done(function (data) {
        if (data) {
            let contentTable = document.createElement("div");
            contentTable.className = "dropdown-menu show";
            contentTable.style = "width: 90%; margin-left: 5%; margin-top: 3%;";
            contentTable.setAttribute("aria-describedby", "searchDropdownToggle");
            for (let i = 0; i < data.length; i++) {
                let element = document.createElement("a");
                element.className = "dropdown-item py-1 px-2";
                element.href = "#";
                element.onclick = function() {
                    document.getElementById("searchInput").value = data[i].name;
                    $("#course_table").html("");
                };
                element.innerText = data[i].name;
                contentTable.append(element);
            }
            $("#course_table").append(contentTable);
        } else {
            $("#course_table").html("");
        }
    });
}

function createAttendance() {
    $.ajax({
        type: 'post',
        url: '/main',
        data: {
            currentCourse: document.getElementById("searchInput").value
        }
    }).done(function (data) {
        let contentTableHTML = document.createElement("table");
        contentTableHTML.className = "table table-hover table-bordered";
        let header = document.createElement("thead");
        header.className = "thead-light";

        contentTableHTML.append(header);

        let title = document.createElement("tr");
        let titlePerson = document.createElement("th");
        titlePerson.scope = "col";
        titlePerson.innerText = "Initials";
        titlePerson.setAttribute("rowspan", "2");
        let titleAttendance = document.createElement("th");
        titleAttendance.scope = "col";
        titleAttendance.innerText = "Attendance";
        titleAttendance.colSpan = data[1].length;

        title.append(titlePerson);
        title.append(titleAttendance);
        header.append(title);

        let schedule = document.createElement("tr");
        for (let i = 0; i < data[1].length; i++) {
            let lesson = document.createElement("th");
            lesson.innerText = data[1][i].timeBegin.hour + ':' +
                data[1][i].timeBegin.minute + ' ' +  data[1][i].dateUpdate.dayOfWeek;
            schedule.append(lesson);
        }
        header.append(schedule);

        let body = document.createElement("tbody");
        for (let j = 0; j < data[0].length;) {
            let newRow = document.createElement("tr");
            let newStudent = document.createElement("td");
            newStudent.innerText = data[0][j].student.firstName + ' ' + data[0][j].student.surname;
            let id = data[0][j].student.id;

            newRow.append(newStudent);
            while(j < data[0].length && data[0][j].student.id === id) {
                let newCol = document.createElement("td");
                newCol.innerText = (data[0][j++].presence ? '+' : '-');
                newRow.append(newCol);
            }
            body.append(newRow);
        }
        contentTableHTML.append(body);
        let contentTableDiv = document.getElementById("visits_table");
        contentTableDiv.append(contentTableHTML);
    });
}