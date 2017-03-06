<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
  if (session.isNew()) {
    session.invalidate();
    response.sendRedirect("/");
  }
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>云制造设备管理系统</title>
    <!-- Loading Bootstrap -->
    <link href="css/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/bootstrap-treeview.min.css">
    <!-- Loading Bootstrap Table UI -->
    <link rel="stylesheet" href="css/bootstrap-table.min.css">
    <!-- Loading Bootstrap Table Extension UI -->
    <link rel="stylesheet" href="css/bootstrap-editable.css">
    <!-- <link rel="stylesheet" href="css/bootstrap-tagsinput.css"> -->
    <!-- Loading Bootstrap Date Range Picker UI -->
    <link rel="stylesheet" type="text/css" href="css/daterangepicker.css" />
    <!-- Loading Flat UI -->
    <link href="css/flat-ui.min.css" rel="stylesheet">
    <link rel="shortcut icon" href="img/favicon.ico">
    <!-- Loading Bootstrap TreeView -->
    <link rel="stylesheet" type="text/css" href="css/bootstrap-treeview.min.css">
    <!-- Loading Bootstrap Select -->
    <link rel="stylesheet" type="text/css" href="css/bootstrap-select.min.css">
    <!-- Loading Messenger UI -->
    <link rel="stylesheet" type="text/css" href="css/messenger.css">
    <link rel="stylesheet" type="text/css" href="css/messenger-theme-flat.css">
    <!-- Loading Loder UI -->
    <link rel="stylesheet" type="text/css" href="css/loaders.min.css">
    <!--  Loading SUI UI -->
    <link rel="stylesheet" type="text/css" href="css/sui.nav.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <!-- Loading Z-tree -->
    <link rel="stylesheet" href="css/zTreeStyle.css">
    <link rel="stylesheet" type="text/css" href="css/jquery.contextMenu.min.css">
    <!-- Loading File-Upload -->
    <link href="css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
    <!-- Loading Custom UI -->
    <link rel="stylesheet" type="text/css" href="css/custom/home-ui.css">
</head>
  <body>

    <!-- navbar -->
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <nav class="navbar navbar-inverse cancelRadius navbar-fixed-top" role="navigation">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse-01">
                <span class="sr-only">Toggle navigation</span>
              </button>
              <a class="navbar-brand" href="#">CMDMS</a>
            </div>
            <div class="collapse navbar-collapse" id="navbar-collapse-01">
              <ul class="nav navbar-nav">
                <li>
                  <a href="#" cx lass="chinese_font" id="msgYourMsg">
                    您的消息
                    <!-- Indicator with number -->
                    <span class="navbar-new">2</span>
                  </a>
                </li>
              </ul>
              <p class="navbar-text navbar navbar-right chinese_font"><c:out value="${user.userName}"/>&nbsp;<a class="navbar-link" href="user/log/out">退出</a></p>
              <form role="search" class="navbar-form navbar-right">
                <div class="form-group">
                  <input type="text" placeholder="搜索..." class="form-control" id="iptMenuSearch" autocomplete="off">
                </div>
                <button class="btn btn-default" id="btnMenuItemSearch">搜索</button>
              </form>
            </div><!-- /navbar-collapse -->
          </nav>
        </div>
      </div>
    </div><!-- /navbar -->

    <!-- main -->
    <div class="container-fluid" id="main-container">
      <div class="row">
        <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
          <div id="menu-container" class="sui-nav">
              <div class="sui-nav-wrapper nav-border nav-line">
                  <ul>
                  </ul>
              </div>
          </div>
        </div>
        <!-- tab -->
        <div class="col-xs-10 col-sm-10 col-md-10 col-lg-10">
          <div role="tabpanel">
            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist" id="tablist-container">
              <li role="presentation" class="active" data-toggle="context" data-target="#tab-context-menu">
                <a href="#home" aria-controls="home" role="tab" data-toggle="tab">首页</a>
              </li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content" id="tabcontent-container">
              <div role="tabpanel" class="tab-pane active" id="home" style="padding: 10px;">
              </div>
            </div>
          </div>
        </div><!-- /tab -->
      </div>
    </div><!-- /main -->

    <!-- context-menu -->
    <div id="tab-context-menu">
      <ul class="dropdown-menu" role="menu">
      </ul>
    </div><!-- /context-menu -->
    <!-- tmpMenu -->
    <div class="hidden" id="tmpTree">
      <ul id="treeTmp" class="ztree"></ul>
    </div>
    <!-- tmpMenu -->
    <div class="hidden" id="tmpUserId">
      <c:out value="${user.roleLineId}"/>
    </div>
    <!-- Loading Jquery -->
    <script src="js/vendor/jquery.min.js"></script>
    <!-- Loading NiceScroll -->
    <script src="js/jquery.nicescroll.min.js"></script>
    <!-- Loading Flat-UI -->
    <script src="js/flat-ui.min.js"></script>
    <!-- Loading Bootstrap TreeView -->
    <script src="js/bootstrap-treeview.min.js"></script>
    <!-- Loading Bootstrap Select -->
    <script src="js/bootstrap-select.min.js"></script>
    <!-- Loading Bootstrap-Table -->
    <script src="js/bootstrap-table.min.js"></script>
    <!-- Loading Bootstrap-Table Extension -->
    <script type="text/javascript" src="js/bootstrap-table-editable.js"></script>
    <script type="text/javascript" src="js/bootstrap-editable.js"></script>
    <!-- Loading Bootstrap-Contextmenu -->
    <script src="js/bootstrap-contextmenu.js"></script>
    <script type="text/javascript" src="css/jquery.ui.position.min.js"></script>
    <script type="text/javascript" src="css/jquery.contextMenu.min.js"></script>
    <!-- Loading Bootstrap-Table-Locales -->
    <script src="js/bootstrap-table-zh-CN.min.js"></script>
    <!-- Loading Bootstrap-Date-Range-Picker -->
    <script type="text/javascript" src="js/moment.min.js"></script>
    <script type="text/javascript" src="js/daterangepicker.js"></script>
    <!-- Loading Bootstrap-Typehead -->
    <script type="text/javascript" src="js/bootstrap3-typeahead.min.js"></script>
    <!-- Loading Echarts -->
    <script src="js/echarts.min.js"></script>
    <!-- Loading Messenger -->
    <script src="js/backbone-0.9.10.js"></script>
    <script src="js/messenger.min.js"></script>
    <script src="js/messenger-theme-flat.js"></script>
    <!-- Loading Loader -->
    <script src="js/loaders.css.js"></script>
    <script src="js/sui.nav.min.js"></script>
    <!-- Loading Z-tree -->
    <script src="js/jquery.ztree.all.min.js"></script>
    <!-- Loading canvas-to-blob -->
    <script src="js/plugins/canvas-to-blob.min.js" type="text/javascript"></script>
    <!-- Loading canvas-to-blob -->
    <script src="js/plugins/sortable.min.js" type="text/javascript"></script>
    <!-- Loading canvas-to-blob -->
    <script src="js/plugins/purify.min.js" type="text/javascript"></script>
    <!-- Loading canvas-to-blob -->
    <!-- the main fileinput plugin file -->
    <script src="js/fileinput.min.js"></script>
    <!-- optionally if you need translation for your language then include
    locale file as mentioned below -->
    <script src="js/locales/LANG.js"></script>
    <!-- Loading Custom JS -->
    <script src="js/custom/utils.js"></script>
    <script src="js/custom/home-init.js"></script>
    <script src="js/custom/home-pocess.js"></script>
  </body>
</html>

