<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>Treat Shoes shop</title>


    <meta content="text/html;charset=utf-8" http-equiv="Content-Type">
    <meta name="keywords" content="Template, html, premium, themeforest"/>
    <meta name="description" content="Traveler - Premium template for travel companies">
    <meta name="author" content="Tsoy">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- GOOGLE FONTS -->
    <link href='https://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400italic,400,300,600' rel='stylesheet'
          type='text/css'>
    <!-- /GOOGLE FONTS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/icomoon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mystyles.css">
    <script src="${pageContext.request.contextPath}/js/modernizr.js"></script>


</head>
<body>
<div class="global-wrap">
    <header id="main-header">
        <div class="header-top">
            <div class="container">
                <div class="row">
                    <div class="col-md-3">
                        <a class="logo" href="/">
                            <img src="${pageContext.request.contextPath}/img/logo-invert2.png" alt="Treat Shoes Shop"
                                 title="Treat Shoes Shop"/>
                        </a>
                    </div>

                    <div class="col-md-3 col-md-offset-2">
                        <security:authorize access="!hasAnyAuthority('ADMIN')">
                            <form id="search" class="main-header-search" action="/catalog/search/" method="get">
                                <div class="form-group form-group-icon-left">
                                    <i class="fa fa-search input-icon" onClick="javascript: $('#search').submit();"></i>
                                    <input name="search_for" type="text" class="form-control">
                                </div>
                            </form>
                        </security:authorize>
                    </div>

                    <div class="col-md-4">
                        <div class="top-customUser-area clearfix">
                            <ul class="top-customUser-area-list list list-horizontal list-border">
                                <security:authorize access="isAuthenticated()">
                                    <li class="top-customUser-area-avatar">
                                        <a href="/user/profile">
                                            Hi, <security:authentication property="principal.username"/></a>
                                    </li>
                                    <li><a href="/logout">Sign Out</a>
                                    </li>
                                </security:authorize>
                                <security:authorize access="!isAuthenticated()">
                                    <li class="top-customUser-area-avatar">
                                        <a href="/login">Login</a>
                                    </li>
                                    <li>
                                        <a href="/user/register">Register</a>
                                    </li>
                                </security:authorize>
                                <security:authorize access="!hasAnyAuthority('ADMIN')">
                                    <li>
                                        <a href="/shoppingCart"><i class="fa fa-shopping-cart" aria-hidden="true"></i>
                                            Cart <c:if
                                                    test="${cartCount > 0}">(<span id="cartCount">${cartCount}</span>)</c:if></a>
                                    </li>
                                </security:authorize>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="nav">
                <security:authorize access="hasAnyAuthority('ADMIN')">
                    <ul class="slimmenu" id="slimmenu">
                        <li
                                <c:if test="${pageContext.request.requestURI == '/WEB-INF/pages/orders.jsp'}">class="active"</c:if> >
                            <a href="/admin/orders">Orders</a>
                        </li>
                        <li
                                <c:if test="${pageContext.request.requestURI == '/WEB-INF/pages/users.jsp'}">class="active"</c:if> >
                            <a href="/admin/users">Users</a>
                        </li>
                        <li
                                <c:if test="${pageContext.request.requestURI == '/WEB-INF/pages/categories.jsp'}">class="active"</c:if> >
                            <a href="/admin/categories">Categories</a>
                        </li>
                        <li
                                <c:if test="${pageContext.request.requestURI == '/WEB-INF/pages/products.jsp'}">class="active"</c:if> >
                            <a href="/admin/products">Products</a>
                        </li>
                    </ul>
                </security:authorize>
                <security:authorize access="!hasAnyAuthority('ADMIN')">
                    <ul class="slimmenu" id="slimmenu">
                        <li
                                <c:if test="${empty category}">class="active"</c:if> ><a href="/">Home</a>
                        </li>
                        <li
                                <c:if test="${category == 'female'}">class="active"</c:if> ><a href="/catalog/female">Female
                            Shoes</a>
                        </li>
                        <li
                                <c:if test="${category == 'male'}">class="active"</c:if> ><a href="/catalog/male">Male
                            Shoes</a>
                        </li>
                        <li
                                <c:if test="${category == 'children'}">class="active"</c:if> ><a
                                href="/catalog/children">Children Shoes</a>
                        </li>
                        <li
                                <c:if test="${category == 'sale'}">class="active"</c:if> ><a
                                href="/catalog/sale">Sale</a>
                        </li>
                    </ul>
                </security:authorize>
            </div>
        </div>
    </header>