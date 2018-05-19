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
    <meta name="author" content="Daniel Fiećko">

    <!-- Bootstrap css -->

    <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss"/>
    <spring:url value="/resources/css/bootstrap.techie.css" var="techieCss"/>

    <spring:url value="/resources/img/profile-avatar.png" var="profileAvatar"/>

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
        <p>It allows you to analyse profiles which can be found on the <a href="http://instagram.com">instagram</a></p>
        <p><a class="btn btn-primary btn-lg"
              href="https://pg.edu.pl/rekrutacja/oferta-studiow/studia-i-stopnia/inzynieria-biomedyczna" role="button">Learn
            more</a></p>
    </div>

    <p class="lead text-muted">Profile Analyser</p>
    <form:form class="form-group row" action="analyseProfile" method="post" modelAttribute="userAlgorithmSettings">
        <ul class="media-list">
            <li class="media">
                <a class="pull-left" href="#" style="width: 340px; height: 340px;">
                    <img class="media-object img-thumbnail" data-src="holder.js/64x64" alt="profile-avatar"
                         src=${profileAvatar}>
                </a>
                <div class="col-xs-6">

                    <div class="media-body">
                        <ul>
                            <li>
                                <h5 class="media-heading"><a href="#">Algorithm types</a></h5>
                                <form:select class="form-control" path="algorithmType">
                                    <form:option value="NaiveBayes">Naive Bayes</form:option>
                                    <form:option value="DecisionTree">Decision Tree</form:option>
                                    <form:option value="Apriori">A priori</form:option>
                                </form:select>
                                <p>Selected option from upper menu define which data will be analysed. Naive bayes
                                    estimates
                                    context of photo on base tags below photo. Decision tree.... . A priori ..... .</p>
                                <br/>
                            </li>
                            <li>
                                <h5 class="media-heading"><a href="#">Parameters</a></h5>
                                <div class="input-group">
                                    <span class="input-group-addon">Threshold</span>
                                    <form:input type="text" class="form-control" path="threshold"/>
                                </div>
                                <br/>
                                <div class="input-group">
                                    <span class="input-group-addon">Confidence</span>
                                    <form:input type="text" class="form-control" path="confidence"/>
                                    <span class="input-group-addon">%</span>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </li>
        </ul>
        <br/>
        <div class="col-xs-9">
            <form:input type="text" class="form-control" placeholder="profile url" path="url"/>
        </div>
        <form:button type="submit" class="btn btn-primary">Analyse</form:button>
    </form:form>

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
