<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta content="ie=edge" http-equiv="x-ua-compatible" />
        <title>${pageTitle}</title>

        <!--Bootsrap 4 CDN-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" >
        
        <!--Fontawesome CDN-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/fontawesome.css" >
    
        <!--Custom styles-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/admin/common.css">
    
    </head>
    <body>
        <div class="container-fluid page-wrapper toggled">
            <div class="row flex-xl-nowrap">
              <main class="col-md-12 col-xl-12 py-md-3 pl-md-5 bd-content" role="main">
        <header class="navbar  fixed-top navbar-expand flex-column flex-md-row bd-navbar header">
            <a class="navbar-brand mr-0 mr-md-2" href="/" aria-label="Bootstrap">
                <img width="54" src="${pageContext.request.contextPath}/assets/images/logo.png" />
            </a>

            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="#">General</a></li>
                    <li class="breadcrumb-item active" aria-current="page">${pageName}</li>
                </ol>
            </nav>

            <ul class="nav navbar-nav navbar-right ml-auto">
                <li class="nav-item"><a href="#" class="nav-link"><i class="fa fa-briefcase"></i><span>Careers</span></a></li>
                <li class="nav-item"><a href="#" class="nav-link"><i class="fa fa-envelope"></i><span>Messages</span></a></li>
                <li class="nav-item"><a href="#" class="nav-link"><i class="fa fa-bell"></i><span>Notifications</span></a></li>
                <li class="nav-item dropdown">
                    <a href="#" data-toggle="dropdown" class="nav-link dropdown-toggle user-action">
                        <img width="32" src="${pageContext.request.contextPath}/assets/images/avatar.png" class="avatar" alt="Avatar">
                        ${user.name} <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="#" class="dropdown-item"><i class="fa fa-user-o"></i> Profile</a></li>
                        <li><a href="#" class="dropdown-item"><i class="fa fa-calendar-o"></i> Calendar</a></li>
                        <li><a href="#" class="dropdown-item"><i class="fa fa-sliders"></i> Settings</a></li>
                        <li class="divider dropdown-divider"></li>
                        <li><a href="${pageContext.request.contextPath}/admin/logout" class="dropdown-item"><i class="material-icons"></i> Logout</a></li>
                    </ul>
                </li>
            </ul>
        </header>

        <nav id="sidebar" class="sidebar-wrapper">
            <div class="sidebar-content">
                <!-- sidebar-header  -->
                <div class="sidebar-search">
                    <div>
                        <div class="input-group">
                            <input type="text" class="form-control search-menu" placeholder="Search...">
                            <div class="input-group-append">
                                <span class="input-group-text">
                                    <i class="fa fa-search" aria-hidden="true"></i>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- sidebar-search  -->
                <div class="sidebar-menu">
                    <ul>
                        <li class="header-menu">
                            <span>General</span>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin">
                                <i class="fa fa-tachometer"></i>
                                <span>Dashboard</span>
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/user/search">
                                <i class="fa fa-user"></i>
                                <span>Users</span>
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/music/search">
                                <i class="fa fa-music"></i>
                                <span>Songs</span>
                            </a>
                        </li>
                        <li class="header-menu">
                            <span>Reports</span>
                        </li>
                        <li>
                            <a href="#">
                                <i class="fa fa-dollar"></i>
                                <span>Transactions</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <i class="fa fa-table"></i>
                                <span>Charts</span>
                            </a>
                        </li>
                    </ul>
                </div>
                <!-- sidebar-menu  -->
            </div>
            <!-- sidebar-content  -->
            <div class="sidebar-footer">
                Music Store All Rights Reserved
            </div>
        </nav>