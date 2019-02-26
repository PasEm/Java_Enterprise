// Events
function searchParticipants(query) {
    $.ajax({
        type: 'get',
        url: '/events/ftl',
        data: {
            participants: query
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
                    document.getElementById("inputSearch").value = data[i].login;
                    $("#participants_table").html("");
                };
                element.innerText = data[i].login;
                contentTable.append(element);
            }
            $("#participants_table").append(contentTable);
        } else {
            $("#participants_table").html("");
        }
    });
}
//-------------------------------------------------------------------
//ProfileDropdown
function start() {
    getProfileDropdown("load");
    loadTracks();
}

function getProfileDropdown(status) {
    $.ajax({
        type: 'get',
        url: '/main/ftl',
        data: {
            dropdownProfile : status
        }
    }).done(function (data) {
        if (data) {
            let btnLogin = document.createElement("button");
            btnLogin.className = "btn btn-light dropdown-toggle";
            btnLogin.type = "button";
            btnLogin.role = "button";
            btnLogin.id = "profileDropdown";
            btnLogin.setAttribute("data-toggle", "dropdown");
            btnLogin.setAttribute("aria-haspopup", "true");
            btnLogin.setAttribute("aria-expanded", "false");
            btnLogin.setAttribute("data-offset", "10,20");
            btnLogin.innerText = data.login;

            let menu = document.createElement("div");
            menu.className = "dropdown-menu";
            menu.setAttribute("aria-labelledby","profileDropdown");

            let editProfile = document.createElement("a");
            editProfile.className = "dropdown-item";
            editProfile.href = "/profile/ftl";
            editProfile.innerText = "Edit profile";

            let divider = document.createElement("div");
            divider.className = "dropdown-divider";

            let signOut = document.createElement("a");
            signOut.className = "dropdown-item";
            signOut.href = "/main/ftl";
            signOut.innerText = "Sign Out";

            signOut.onclick = function() {
                $("#dropdownMenu").html("");
                getProfileDropdown("signOut")
            };

            menu.append(editProfile);
            menu.append(divider);
            menu.append(signOut);

            $("#dropdownMenu").append(btnLogin);
            $("#dropdownMenu").append(menu);
        } else {
            let btn = document.createElement("div");
            btn.className = "btn btn-light btn-block";
            btn.type = "button";
            btn.setAttribute("role", "button");

            let signIn = document.createElement("a");
            signIn.href = "/signIn/ftl";
            signIn.innerText = "Sign In";

            btn.append(signIn);

            $("#dropdownMenu").append(btn);
        }
    });
}
//
function getProfileDropdown(status) {
    $.ajax({
        type: 'get',
        url: '/my_collection/ftl',
        data: {
            dropdownProfile : status
        }
    }).done(function (data) {
        if (data) {
            let btnLogin = document.createElement("button");
            btnLogin.className = "btn btn-light dropdown-toggle";
            btnLogin.type = "button";
            btnLogin.role = "button";
            btnLogin.id = "profileDropdown";
            btnLogin.setAttribute("data-toggle", "dropdown");
            btnLogin.setAttribute("aria-haspopup", "true");
            btnLogin.setAttribute("aria-expanded", "false");
            btnLogin.setAttribute("data-offset", "10,20");
            btnLogin.innerText = data.login;

            let menu = document.createElement("div");
            menu.className = "dropdown-menu";
            menu.setAttribute("aria-labelledby","profileDropdown");

            let editProfile = document.createElement("a");
            editProfile.className = "dropdown-item";
            editProfile.href = "/profile/ftl";
            editProfile.innerText = "Edit profile";

            let divider = document.createElement("div");
            divider.className = "dropdown-divider";

            let signOut = document.createElement("a");
            signOut.className = "dropdown-item";
            signOut.href = "/main/ftl";
            signOut.innerText = "Sign Out";

            signOut.onclick = function() {
                $("#dropdownMenu").html("");
                getProfileDropdown("signOut")
            };

            menu.append(editProfile);
            menu.append(divider);
            menu.append(signOut);

            $("#dropdownMenu").append(btnLogin);
            $("#dropdownMenu").append(menu);
        } else {
            let btn = document.createElement("div");
            btn.className = "btn btn-light btn-block";
            btn.type = "button";
            btn.setAttribute("role", "button");

            let signIn = document.createElement("a");
            signIn.href = "/signIn/ftl";
            signIn.innerText = "Sign In";

            btn.append(signIn);

            $("#dropdownMenu").append(btn);
        }
    });
}
//
function getProfileDropdown(status) {
    $.ajax({
        type: 'get',
        url: '/profile/ftl',
        data: {
            dropdownProfile : status
        }
    }).done(function (data) {
        if (data) {
            let btnLogin = document.createElement("button");
            btnLogin.className = "btn btn-light dropdown-toggle";
            btnLogin.type = "button";
            btnLogin.role = "button";
            btnLogin.id = "profileDropdown";
            btnLogin.setAttribute("data-toggle", "dropdown");
            btnLogin.setAttribute("aria-haspopup", "true");
            btnLogin.setAttribute("aria-expanded", "false");
            btnLogin.setAttribute("data-offset", "10,20");
            btnLogin.innerText = data.login;

            let menu = document.createElement("div");
            menu.className = "dropdown-menu";
            menu.setAttribute("aria-labelledby","profileDropdown");

            let editProfile = document.createElement("a");
            editProfile.className = "dropdown-item";
            editProfile.href = "/profile/ftl";
            editProfile.innerText = "Edit profile";

            let divider = document.createElement("div");
            divider.className = "dropdown-divider";

            let signOut = document.createElement("a");
            signOut.className = "dropdown-item";
            signOut.href = "/main/ftl";
            signOut.innerText = "Sign Out";

            signOut.onclick = function() {
                $("#dropdownMenu").html("");
                getProfileDropdown("signOut")
            };

            menu.append(editProfile);
            menu.append(divider);
            menu.append(signOut);

            $("#dropdownMenu").append(btnLogin);
            $("#dropdownMenu").append(menu);
        } else {
            let btn = document.createElement("div");
            btn.className = "btn btn-light btn-block";
            btn.type = "button";
            btn.setAttribute("role", "button");

            let signIn = document.createElement("a");
            signIn.href = "/signIn/ftl";
            signIn.innerText = "Sign In";

            btn.append(signIn);

            $("#dropdownMenu").append(btn);
        }
    });
}
//
function getProfileDropdown(status) {
    $.ajax({
        type: 'get',
        url: '/events/ftl',
        data: {
            dropdownProfile : status
        }
    }).done(function (data) {
        if (data) {
            let btnLogin = document.createElement("button");
            btnLogin.className = "btn btn-light dropdown-toggle";
            btnLogin.type = "button";
            btnLogin.role = "button";
            btnLogin.id = "profileDropdown";
            btnLogin.setAttribute("data-toggle", "dropdown");
            btnLogin.setAttribute("aria-haspopup", "true");
            btnLogin.setAttribute("aria-expanded", "false");
            btnLogin.setAttribute("data-offset", "10,20");
            btnLogin.innerText = data.login;

            let menu = document.createElement("div");
            menu.className = "dropdown-menu";
            menu.setAttribute("aria-labelledby","profileDropdown");

            let editProfile = document.createElement("a");
            editProfile.className = "dropdown-item";
            editProfile.href = "/profile/ftl";
            editProfile.innerText = "Edit profile";

            let divider = document.createElement("div");
            divider.className = "dropdown-divider";

            let signOut = document.createElement("a");
            signOut.className = "dropdown-item";
            signOut.href = "/main/ftl";
            signOut.innerText = "Sign Out";

            signOut.onclick = function() {
                $("#dropdownMenu").html("");
                getProfileDropdown("signOut")
            };

            menu.append(editProfile);
            menu.append(divider);
            menu.append(signOut);

            $("#dropdownMenu").append(btnLogin);
            $("#dropdownMenu").append(menu);
        } else {
            let btn = document.createElement("div");
            btn.className = "btn btn-light btn-block";
            btn.type = "button";
            btn.setAttribute("role", "button");

            let signIn = document.createElement("a");
            signIn.href = "/signIn/ftl";
            signIn.innerText = "Sign In";

            btn.append(signIn);

            $("#dropdownMenu").append(btn);
        }
    });
}
//-------------------------------------------------------------------
function clearSearch() {
    $.ajax({
        type: 'post',
        url: '/events/ftl',
        data: {
            clearing : document.getElementById("clearList").value
        }
    }).done(function (data) {
        let contentTableHTML = "<table class='table table-hover'><thead class='thead-light'>";
        contentTableHTML +=
            "<tr>" +
            "<th scope='col'>#</th>" +
            "<th scope='col'>Participant</th>" +
            "</tr></thead><tbody>";
        contentTableHTML += "</tbody></table></div>";
        let contentTableDiv = document.getElementById("tableParticipant");
        contentTableDiv.innerHTML = contentTableHTML;
        let contentEventTableDiv = document.getElementById("tableEvent");
        contentEventTableDiv.innerHTML = null;
    });
}
function seeEvents() {
    $.ajax({
        type: 'post',
        url: '/events/ftl',
        data: {
            events : document.getElementById("seeEvents").value
        }
    }).done(function (data) {
        let contentTableHTML = "<table class='table table-hover'><thead class='thead-light'>";
        contentTableHTML +=
            "<tr>" +
            "<th scope='col'>#</th>" +
            "<th scope='col'>Event</th>" +
            "<th scope='col'>Country</th>" +
            "<th scope='col'>City</th>" +
            "<th scope='col'>Date</th>" +
            "<th scope='col'>Sale site</th>" +
            "</tr></thead><tbody>";
        if (data !== null) {
            for (let i = 0; i < data.length; i++) {
                contentTableHTML += "<tr>";
                contentTableHTML += "<th scope='row'>" + (i + 1) + "</th>";
                contentTableHTML += "<td>" + data[i].name + "</td>";
                contentTableHTML += "<td>" + data[i].country + "</td>";
                contentTableHTML += "<td>" + data[i].city + "</td>";
                contentTableHTML += "<td>" + data[i].date + "</td>";
                contentTableHTML += "<td>" + data[i].saleSite + "</td>";
                contentTableHTML += "</tr>";
            }
        }
        contentTableHTML += "</tbody></table></div>";
        let contentTableDiv = document.getElementById("tableEvent");
        contentTableDiv.innerHTML = contentTableHTML;
    });
}
function addParticipant() {
    $.ajax({
        type: 'post',
        url: '/events/ftl',
        data: {
            currentParticipant : document.getElementById("inputSearch").value
        }
    }).done(function (data) {
        let contentTableHTML = "<table class='table table-hover'><thead class='thead-light'>";
        contentTableHTML +=
            "<tr>" +
            "<th scope='col'>#</th>" +
            "<th scope='col'>Participant</th>" +
            "</tr></thead><tbody>";
        for (let i = 0; i < data.length; i++) {
            contentTableHTML += "<tr>";
            contentTableHTML += "<th scope='row'>" + (i + 1) + "</th>";
            contentTableHTML += "<td>" + data[i].login + "</td>";
            contentTableHTML += "</tr>";
        }
        contentTableHTML += "</tbody></table></div>";
        let contentTableDiv = document.getElementById("tableParticipant");
        contentTableDiv.innerHTML = contentTableHTML;
    });
}
//-------------------------------------
//Main
function loadTracks() {
    $.ajax({
        type: 'post',
        url: '/main/ftl',
        data: {
            trackList : document.getElementById("mainList").innerHTML
        }
    }).done(function (data) {
        let contentTableHTML = "";
        for (let i = 0; i < data.length;) {
            contentTableHTML += "<div class='row'>";
            for (let j = 0; j < 4, i < data.length; j++, i++) {
                contentTableHTML += "<div class='col mb-3'><div class='card lg-2 md-3' id='" + data[i].id +"'>";
                contentTableHTML += "<img class='card-img-top' style='width: 250px; height: 120px;' src='#' alt='" + data[i].name+ "'>";
                contentTableHTML += "<div class='card-body'>";
                contentTableHTML += "<h5 class='card-title'>" + data[i].author.login + "</h5>";
                contentTableHTML += "<p class='card-text'>" + data[i].name + "</p>";
                contentTableHTML += "</div><div class='card-footer'>";1
                contentTableHTML +=  "<small class='text-muted'>Last updated ";
                contentTableHTML +=  data[i].releaseDate.dayOfMonth + ".";
                contentTableHTML +=  data[i].releaseDate.monthValue + ".";
                contentTableHTML +=  data[i].releaseDate.year + "</small>";
                contentTableHTML += "</div></div></div>";
            }
            contentTableHTML += "</div></div>";
        }
        contentTableHTML +="";
        let contentTableDiv = document.getElementById("mainList");
        contentTableDiv.innerHTML = contentTableHTML;
    });
}
//--------------------------------
//Playlists
function seePlaylistTracks() {
    $.ajax({
        type: 'post',
        url: '/my_collection/ftl',
        data: {
            //currentPlaylistName : document.getElementById("inputPlaylist").options[document.getElementById("inputPlaylist").selectedIndex].value
            currentPlaylistName: document.getElementById("searchPlaylist").value
        }
    }).done(function (data) {
        let searchTableHtml = "";
        $("#playlist_table").html(searchTableHtml);
        let contentTableHTML = "<table class='table table-hover'><thead class='thead-light'>";
        contentTableHTML +=
            "<tr>" +
            "<th scope='col'>#</th>" +
            "<th scope='col'>Track name</th>" +
            "<th scope='col'>Author</th>" +
            "<th scope='col'>Genre</th>" +
            "<th scope='col'>Duration</th>" +
            "</tr></thead><tbody>";
        for (let i = 0; i < data.length; i++) {
            contentTableHTML += "<tr>";
            contentTableHTML += "<th scope='row'>" + (i + 1) + "</th>";
            contentTableHTML += "<td>" + data[i].name + "</td>";
            contentTableHTML += "<td>" + data[i].author.login + "</td>";
            contentTableHTML += "<td>" + data[i].genre + "</td>";
            let time = parseInt(data[i].duration / 60) + ":";
            if (data[i].duration % 60 < 10)
                time += "0";
            time += data[i].duration % 60;
            contentTableHTML += "<td>" + time + "</td>";
            contentTableHTML += "</tr>";
        }
        contentTableHTML += "</tbody></table>";
        let contentTableDiv = document.getElementById("table");
        contentTableDiv.innerHTML = contentTableHTML;
    });
}

function searchPlaylist(query) {
    $.ajax({
        type: 'get',
        url: '/my_collection/ftl',
        data: {
            searchPlaylist: query
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
                    document.getElementById("searchPlaylist").value = data[i].name;
                    $("#playlist_table").html("");
                };
                element.innerText = data[i].name;
                contentTable.append(element);
            }
            $("#playlist_table").append(contentTable);
        } else {
            $("#playlist_table").html("");
        }
    });
}
//----------------
//Profile
function saveData() {
    $.ajax({
        type: 'post',
        url: '/profile/ftl',
        data: {

        }
    }).done(function (data) {
        if (data)
            alert("You previous password isn`t correct");
    });
}
//---------------------
//Player
function playPlaylist() {
    $.ajax({
        type: 'post',
        url: '/my_collection/ftl',
        data: {
            playlist: document.getElementById("searchPlaylist").value
        }
    }).done(function (data) {
        if (data) {
            $("#table").html("");
            let contentTableHTML = "<div id='title'>" +
                "<span id='track'></span>" +
                "<div id='timer'>0:00</div>" +
                "<div id='duration'>0:00</div>" +
                "</div>" +
                " <div class='controlsOute'>" +
                "<div class='controlsInner'>" +
                "<div id='loading'></div>" +
                "<div class='btn' id='playBtn'></div>" +
                "<div class='btn' id='pauseBtn'></div>" +
                "<div class='btn' id='prevBtn'></div>" +
                "<div class='btn' id='nextBtn'></div>" +
                "</div>" +
                "<div class='btn' id='playlistBtn'></div>" +
                "<div class='btn' id='volumeBtn'></div>" +
                "</div>" +
                "<div id='waveform'></div>" +
                "<div id='bar'></div>" +
                "<div id='progress'></div>" +
                "<div id='playlist'>" +
                "<div id='list'></div>" +
                "</div>" +
                "<div id='volume' class='fadeout'>" +
                "<div id='barFull' class='bar'></div>" +
                "<div id='barEmpty' class='bar'></div>" +
                "<div id='sliderBtn'></div>" +
                "</div>";
            $("#playlist").html(contentTableHTML);
        }
    });
}