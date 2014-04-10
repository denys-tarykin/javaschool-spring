<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>

    <style>
        body {
            width: 950px;
            margin: 0 auto;
        }

        #header-backend {
            border: 2px solid;
            height: 25px;
            margin: 0 auto;
        }

        #menu {
            width: 240px;
            border: 2px solid;
            float: left;
        }

        #menu ul {
            list-style: none;
            padding: 0px;
        }

        #menu ul li a {
            padding-left: 5px;
            color: black;
            text-decoration: none;
        }

        #menu ul li:hover {
            background-color: #808080;
            color: #ffffff
        }

        .inner-content {
            float: left;
            margin: 15px 0px 8px 20px
        }

        ul{border:0; margin:0; padding:0;}

        #pagination-flickr li{
            border:0; margin:0; padding:0;
            font-size:11px;
            list-style:none;
        }
        #pagination-flickr a{
            border:solid 1px #DDDDDD;
            margin-right:2px;
        }
        #pagination-flickr .previous-off,
        #pagination-flickr .next-off {
            color:#000;
            display:block;
            float:left;
            font-weight:bold;
            padding:3px 4px;
        }
        #pagination-flickr .next a,
        #pagination-flickr .previous a {
            font-weight:bold;
            border:solid 1px #FFFFFF;
        }
        #pagination-flickr .active{
            color:#ff0084;
            font-weight:bold;
            display:block;
            float:left;
            padding:4px 6px;
        }
        #pagination-flickr a:link,
        #pagination-flickr a:visited {
            color:#000;
            display:block;
            float:left;
            padding:3px 6px;
            text-decoration:none;
        }
        #pagination-flickr a:hover{
            border:solid 1px #666666;
        }
    </style>

</head>
<body>
<div id="header-backend"></div>