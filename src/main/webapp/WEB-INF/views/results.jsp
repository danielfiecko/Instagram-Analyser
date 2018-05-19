<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <title>Instagram Analyser</title>

    <link href='https://fonts.googleapis.com/css?family=Lato:400,300,400italic,700,900' rel='stylesheet'
          type='text/css'>

    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="description" content="Instagram Analyser">
    <meta name="keywords" content="instagram,analyser,algorithms">
    <meta name="author" content="Daniel Fie?ko">

    <!-- Bootstrap css -->

    <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss"/>
    <spring:url value="/resources/css/bootstrap.techie.css" var="techieCss"/>

    <link href="${bootstrapCss}" rel="stylesheet">
    <link href="${techieCss}" rel="stylesheet">

    <!-- =======================================================
      Theme Name: Techie
      Theme URL: https://bootstrapmade.com/techie-free-skin-bootstrap-3/
      Author: BootstrapMade
      Author URL: https://bootstrapmade.com
    ======================================================= -->

    <!-- Docs Custom styles -->
    <style>
        body,
        html {
            overflow-x: hidden
        }

        body {
            padding: 60px 20px 0
        }

        footer {
            border-top: 1px solid #ddd;
            padding: 30px;
            margin-top: 50px
        }

        .row > [class*=col-] {
            margin-bottom: 40px
        }

        .navbar-container {
            position: relative;
            min-height: 100px
        }

        .navbar.navbar-fixed-bottom,
        .navbar.navbar-fixed-top {
            position: absolute;
            top: 50px;
            z-index: 0
        }

        .navbar.navbar-fixed-bottom .container,
        .navbar.navbar-fixed-top .container {
            max-width: 90%
        }

        .btn-group {
            margin-bottom: 10px
        }

        .form-inline input[type=password],
        .form-inline input[type=text],
        .form-inline select {
            width: 180px
        }

        .input-group {
            margin-bottom: 10px
        }

        .pagination {
            margin-top: 0
        }

        .navbar-inverse {
            margin: 110px 0
        }
    </style>

</head>

<body>

<div class="container">

    <div class="jumbotron">
        <h1>Instagram Analyser v0.0.2</h1>
        <p>Typed instagram url: <a href${userAlgorithmSettings.url}>${analysedInstagramProfile.fullName}</a></p>
        <p><a class="btn btn-primary btn-lg"
              href="https://pg.edu.pl/rekrutacja/oferta-studiow/studia-i-stopnia/inzynieria-biomedyczna" role="button">Learn
            more</a></p>
    </div>

    <div class="col-sm-12 col-lg-12" data-effect="slide-right">
        <h2>Profile Information</h2>

        <ul class="media-list">
            <li class="media">
                <a class="pull-left" href="#" style="width: 450px; height: 450px;">
                    <img class="media-object img-thumbnail" data-src="holder.js/64x64"
                         alt=${analysedInstagramProfile.fullName}
                                 src=${analysedInstagramProfile.profileImageUrl}>
                </a>
                <div class="media-body">
                    <h5 class="media-heading"><a href="#">${analysedInstagramProfile.fullName}</a></h5>
                    <p>${analysedInstagramProfile.biography}</p>
                </div>
            </li>

            <h2>Media</h2>

            <c:forEach items="${analysedInstagramProfile.imageTagStats.entrySet()}" var="entry">
                <li class="media">
                    <a class="pull-left" href="#" style="width: 350px; height: 350px;">
                        <img class="media-object img-thumbnail" alt=${entry.key} src=${entry.value.imageUrl}>
                    </a>
                    <div class="media-body">
                        <h5 class="media-heading"><a href="#">key = ${entry.key}</a></h5>
                        <p>tags = ${entry.value.tags} </p>
                        <c:choose>
                            <c:when test="${entry.value.tagsContext=='positive'}">
                                <div class="progress ">
                                    <div class="progress-bar progress-bar-success"
                                         style="width: ${entry.value.probability*100}%"><span>.positive</span></div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="progress ">
                                    <div class="progress-bar progress-bar-danger"
                                         style="width: ${entry.value.probability*100}%"><span>.negative</span></div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>


    <div class="row">
        <div class="col-12 col-md-8"></div>
        <div class="col-6 col-md-4"><p><a class="btn btn-primary btn-lg" href="/" role="button">try it again</a></p></div>
    </div>

</div>
<!-- /container -->

<footer class="text-center">
    <p>&copy; Daniel Fiecko 2018</p>
    <div class="credits">
        <!--
          All the links in the footer should remain intact.
          You can delete the links only if you purchased the pro version.
          Licensing information: https://bootstrapmade.com/license/
          Purchase the pro version form: https://bootstrapmade.com/buy/?theme=Techie
        -->
        <a href="https://bootstrapmade.com/">Bootstrap Themes</a> by <a
            href="https://bootstrapmade.com/">BootstrapMade</a>
    </div>
</footer>


<spring:url value="/resources/js/jquery.js" var="jqueryJs"/>
<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs"/>
<spring:url value="/resources/js/typeahead.min.js" var="typeaheadJs"/>


<!-- Main Scripts-->
<script src="${jqueryJs}"></script>
<script src="${mainJs}"></script>

<!-- Bootstrap 3 has typeahead optionally -->
<script src="${typeaheadJs}"></script>
</body>

</html>



