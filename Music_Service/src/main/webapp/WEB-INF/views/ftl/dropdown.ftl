<nav class="navbar navbar-light sticky-top flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-sm-2 col-md-2" href="/main/ftl">MusicService</a>
    <div class="input-group input-group-sm">
        <input type="search" class="search-form-control form-control" aria-label="Search" placeholder='${locale["navbar.search"]}'>
        <a class="input-group-prepend" href="#">
            <span class="input-group-text  search-form"><img height="24" width="24" src="/resources/svg/baseline-search-24px.svg"/></span>
        </a>
    </div>
    <div class="d-flex px-3 col-md-0">
        <div class="dropdown mr-1 dropleft" id="dropdownMenu">
            <#if user ??>
                <button class="btn btn-light dropdown-toggle" type="button"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" data-offset="10,20">
                        ${user.login}
                </button>
                <div class="dropdown-menu" aria-labelledby="profileDropdown">
                    <a class="dropdown-item" href="/profile">Edit profile</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="/signOut" onclick="getProfileDropdown('signOut')">Sign Out</a>
                </div>
            <#else>
                <button type="button" class="btn btn-light btn-block">
                    <a href="/login">Login</a>
                </button>
            </#if>
        </div>
    </div>
</nav>