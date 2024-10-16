<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="statics/css/main.css"/>
    <title>Delicious Food Delivery</title>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
            integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
            crossorigin="anonymous"></script>
</head>
<header>
    <nav class="navbar navbar-expand-lg navbar-light">
        <div class="col-4">
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <sec:authorize access="!isAuthenticated()">
                            <a class="text-white nav-link" tabindex="-1"
                               href="${pageContext.request.contextPath}/DeliciousFoodDelivery_war_exploded/login">Log In</a>
                        </sec:authorize>
                        <sec:authorize access="isAuthenticated()">
                            <a class="text-white nav-link" href="${pageContext.request.contextPath}/DeliciousFoodDelivery_war_exploded/logout"
                               th:href="@{/logout}">Log Out</a>
                        </sec:authorize>
                    </li>
                </ul>
            </div>
        </div>
        <div class="col-4">
            <h1 class="text-white text-center"><a class="main-logo-link" href="${pageContext.request.contextPath}/DeliciousFoodDelivery_war_exploded/"
                                                  th:href="@{/DeliciousFoodDelivery_war_exploded/}">DFD</a></h1>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>
        <div class="col-4 d-flex justify-content-end">
            <form class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
        </div>
    </nav>
</header>
<body>
<div class="container mh-100 mw-100 m-0 p-0">
    <div class="row m-0 p-0">
        <div id="left_col_main" class="col-6 m-0 p-0">
        </div>
        <div class="col-6 right_column m-auto">
            <h1 class="display-3 text-center">The best choise ever!</h1>
            <p class="text-center font-italic">Enjoy of food or choose the best opportunity for earning. Delicious Food
                Delivery allows you to excite by the best dishes from your favourite restaurants, to get a job of
                courier or to get online order platform for restaurants.</p>
        </div>
    </div>
</div>
</body>
</html>
