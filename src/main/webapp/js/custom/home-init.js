jQuery.noConflict();
jQuery(document).ready(function($) {

  /*loading Messenger*/
  Messenger.options = {
    extraClasses: 'messenger-fixed messenger-on-bottom',
    theme: 'flat'
  }; /*loading Messenger*/


  $('#menu-container ul').empty();
  var resizePage = function() {
    /*resetting the height*/
    var height = window.screen.availHeight;
    var setHeight = height*0.9-127-20;
    $('#tabcontent-container').css('height', setHeight + 'px');
    /*resetting the height*/
  };

  $(window).resize(function() {
    resizePage();
  });

  resizePage();
  var roleLineId = $('#tmpUserId').text().trim();
  setMessage('菜单加载中','info',true);
  $.ajax({
    url: 'user/businiess/privilege/prvltree',
    type: 'GET',
    dataType: 'json',
    data: {roleLineId: roleLineId}
  })
      .done(function(data) {
        //init menu
        $('#menu-container ul').empty();
        var menuSetting = {
          edit: {
            showRemoveBtn: false,
            showRenameBtn: false
          },
          check: {
            enable: true
          }
        };
        $.fn.zTree.init($("#treeTmp"), menuSetting, data.menuTree);
        var treeObj = $.fn.zTree.getZTreeObj("treeTmp");
        var prvl = data.privilege;
        for (var i = 0; i < prvl.length; i++) {
          var resourceId = prvl[i].resourceLineId;
          var node = treeObj.getNodesByFilter(function(node) {
            return node.id == resourceId;
          }, true);

          if (node.length != 0 ) {
            treeObj.checkNode(node, true, false);
          }
        }
        var nodes = treeObj.getNodesByFilter(function(node) {
          return node.checked == false;
        });
        if (nodes.length != 0 ) {
          for (var i = 0; i < nodes.length; i++) {
            treeObj.removeNode(nodes[i]);
          }
        }
        var arrNodes = treeObj.transformToArray(treeObj.getNodes());
        for (var i = 0; i < arrNodes.length; i++) {
          var tmpNode = arrNodes[i];
          if (tmpNode.parentTId == null) {
            var liId = 'mc_' + tmpNode.number;
            var $li = $('<li><a class="text-primary" ' + 'href="#' + tmpNode.path + '"><span class="glyphicon '+ tmpNode.menuIcon +'"></span>&nbsp;' + tmpNode.name + '</a></li>');
            $li.attr('id', liId);
            $('#menu-container div').children('ul').append($li);
          } else {
            var parentTId = tmpNode.parentTId;
            var nodes = treeObj.getNodesByFilter(function(node) {
              return node.tId == parentTId;
            },true);
            var parentid = 'mc_' + nodes.number;
            var liId = 'mc_' + tmpNode.number;
            var $li = $('<li><a class="text-primary" ' + 'href="#' + tmpNode.path + '"><span class="glyphicon '+ tmpNode.menuIcon +'"></span>&nbsp;' + tmpNode.name + '</a></li>');
            $li.attr('id', liId);
            var itsParent = $('#' + parentid);
            if (itsParent.children('ul').length != 0) {
              itsParent.children('ul').append($li);
            } else {
              var $ul = $('<ul></ul>');
              $ul.append($li);
              itsParent.append($ul);
            }
          }
        }

        //setting search typehead
        var searchSource = [];
        for (var i = 0; i < arrNodes.length; i++) {
          var tmpNode = arrNodes[i];
          var json = {
            id: 'mc_' + tmpNode.number,
            name: tmpNode.name
          }
          searchSource.push(json);
        }
        var $input = $('#iptMenuSearch');
        $input.typeahead({ source:searchSource });
        //end setting search typehead

        $('#menu-container li').each(function(index, el) {
          if ($(this).children('ul').length != 0) {
            if ($(this).parent('ul').length != 0 && $(this).parent().parent('li').length != 0) {
              $(this).append('<span class="indicator">&gt;</span>');
            }
          }
        });
        $('#menu-container li').first().children().first().addClass('item-selected');
        //end init menu

        /*loading niceScroll*/
        $("#menu-container").niceScroll({cursorcolor:"#C6C8CA"});
        $("#tabcontent-container").niceScroll({cursorcolor:"#C6C8CA"});/*loading niceScroll*/

        /*setting tab close button*/
        $('#tablist-container a').not('a[href="#home"]').hover(function(event) {
          $(this).children().first().children().last().css('visibility', 'visible');
        }, function(event) {
          $(this).children().first().children().last().css('visibility', 'hidden');
        });/*setting tab close button*/

        /*loading menu*/
        $('#menu-container').SuiNav({
          toggleName: '.MenuToggle',
          direction: 'left',
          trigger: 'click',
          openingSpeed: 300,
          closingSpeed: 300,
          closingCascade: true,
          destroy: true
        });
        $('#menu-container a').addClass('text-primary');
        $('#menu-container li').click(function(event) {
          var children = $(this).children();
          if (children.length==1) {
            var text=$(children[0]).text();
            var href=$(children[0]).attr('href');
            var id =href.substr(1,href.length);
            var selector='#tablist-container li a[href='+'"'+href+'"'+']';

            // if the tab does not exist create it
            // then show it
            if($(selector).length == 0) {
              // create the tablist
              $tab_li = $('<li role="presentation" data-toggle="context" data-target="#tab-context-menu"></li>');
              $tab_a = $('<a role="tab" data-toggle="tab"></a>');
              $tab_span = $('<span class="glyphicon glyphicon-remove x-small-icon"></span>');
              $tab_span.css('visibility', 'hidden');

              $tab_a.attr('href', href);
              $tab_a.attr('aria-controls', id);
              $tab_a.text(text);
              $tab_a.addClass('hoverflag')

              $tab_a.hover(tabAHoverEvent,tabAOutEvent);
              $tab_span.on('click', spanClickEvent);

              $tab_a.append($tab_span);
              $tab_li.append($tab_a);
              $('#tablist-container').append($tab_li);

              // create the tab content
              $tab_content = $('<div role="tabpanel" class="tab-pane"></div>');
              $tab_content.attr('id', id);
              $('#tabcontent-container').append($tab_content);
              loadingResource(id);
            }

            $(selector).tab('show');
            $('#menu-container a').removeClass('item-selected');
            $(children[0]).addClass('item-selected');
            $(children[0]).blur();
          } else {
          }

        });
        setMessage('菜单加载完成','success',true);
      })
      .fail(function() {
        setMessage('菜单加载失败,请重试', 'error', true);
      })
      .always(function() {
        console.log("complete");
      });

  /*tablist click event binding*/
  $('#tablist-container').click(function(event) {
    $tab_a = $(event.target);
    var href=$tab_a.attr('href');
    $menuSelector = $('#menu-container a[href='+'"'+href+'"'+']');
    $('#menu-container a').removeClass('item-selected');
    $menuSelector.addClass('item-selected');
  });/*tablist click event binding*/



  /*loading contextmenu*/
  $('.context').contextmenu();
  /*loading contextmenu*/

  /*setting menu right click event*/
  $('#tablist-container').mousedown(function(event) {
    var href=$(event.target).attr('href');
    var span=$(event.target).children('span');
    var $target = $('#tab-context-menu ul');
    $target.empty();
    var $btn_refresh = $('<li><a tabindex="-1">刷新</a></li>');
    $btn_refresh.children().first().attr('href', href);
    $btn_refresh.children().first().attr('id', 'btn_refresh');
    $target.append($btn_refresh);
    if (href== '#home') {
      return;
    } else {

      var $btn_close_itself = $('<li><a tabindex="-1" >关闭标签页</a></li>');
      var $btn_close_other = $('<li><a tabindex="-1">关闭其他标签页</a></li>');
      var $btn_close_all = $('<li><a tabindex="-1">关闭所有标签页</a></li>');

      $btn_close_itself.children().first().attr('href', href);
      $btn_close_other.children().first().attr('href', href);
      $btn_close_all.children().first().attr('href', href);

      $btn_close_itself.children().first().attr('id', 'btn_close_itself');
      $btn_close_other.children().first().attr('id', 'btn_close_other');
      $btn_close_all.children().first().attr('id', 'btn_close_all');



      //if tab is locked
      if (!span.hasClass('glyphicon-remove')) {
        var $btn_unlock = $('<li><a tabindex="-1" >取消锁定标签页</a></li>');
        $btn_unlock.children().first().attr('href', href);
        $btn_unlock.children().first().attr('id', 'btn_unlock');
        $target.append($btn_unlock);
      } else {
        var $btn_lock = $('<li><a tabindex="-1" >锁定标签页</a></li>');
        $btn_lock.children().first().attr('href', href);
        $btn_lock.children().first().attr('id', 'btn_lock');

        $target.append($btn_close_itself);
        $target.append($btn_lock);
        $target.append($btn_close_all);
      }

      $target.append($btn_close_other);


    }
  });/*setting menu right click event*/

  $('#tab-context-menu').on('click', contextMenuClickEvent);

  /**
   * [contextMenuClickEvent description]
   * @param  {[type]} event [description]
   * @return {[type]}       [description]
   */
  function contextMenuClickEvent(event) {
    event.preventDefault();
    var href = $(event.target).attr('href');
    var id = $(event.target).attr('id');
    var tab_a_selector = '#tablist-container a[href='+'"'+href+'"'+']';
    var tab_span_selector = '#tablist-container a[href='+'"'+href+'"'+'] span';
    var tab_a = $(tab_a_selector);
    var tab_span = $(tab_span_selector);
    switch(id) {
      case 'btn_refresh':
        $(href).empty();
        loadingResource(href.substring(1,href.length));
        break;
      case 'btn_close_itself':
        tab_span.click();
        break;
      case 'btn_lock':
        tab_span.removeClass('glyphicon-remove');
        tab_span.addClass('glyphicon-pushpin');
        break;
      case 'btn_unlock':
        tab_span.removeClass('glyphicon-pushpin');
        tab_span.addClass('glyphicon-remove');
        break;
      case 'btn_close_other':
        var tab_span_selector = '#tablist-container a[href!='+'"'+href+'"'+'] span.glyphicon-remove';
        var spans = $(tab_span_selector);
        spans.each(function(index, el) {
          $(this).click();
        });
        break;
      case 'btn_close_all':
        var tab_span_selector = '#tablist-container span.glyphicon-remove';
        var spans = $(tab_span_selector);
        spans.each(function(index, el) {
          $(this).click();
        });
        break;
    }
  }

  /**
   * [tabAHoverEvent description]
   * @param  {[type]} event [description]
   * @return {[type]}       [description]
   */
  function tabAHoverEvent(event) {
    var $target = $(event.target);
    if ($target.is('span')) {
      return;
    }
    event.preventDefault();
    $tab_a = $(event.target);
    var href=$tab_a.attr('href');
    if (href == undefined) {
      return;
    }
    $tab_a.children().first().css('visibility', 'visible');
    var selector = '#tablist-container a[href!='+'"'+href+'"'+']';
    var tab_as = $(selector);
    tab_as.each(function(index, el) {
      $(this).children().first().css('visibility', 'hidden');
    });
    event.stopPropagation();
  }

  /**
   * [tabAOutEvent description]
   * @param  {[type]} event [description]
   * @return {[type]}       [description]
   */
  function tabAOutEvent(event) {
    var $target = $(event.target);
    if ($target.is('span')) {
      return;
    }
    if ($target.is('li')) {
      return;
    }
    if( $target.is("a") ) {
      event.preventDefault();
      $tab_a = $(event.target);
      var href=$tab_a.attr('href');
      if (href == undefined) {
        return;
      }
      $tab_a.children().first().css('visibility', 'hidden');
      event.stopPropagation();
      return false;
    }
  }

  /**
   * [spanClickEvent description]
   * @param  {[type]} event [description]
   * @return {[type]}       [description]
   */
  function spanClickEvent(event) {
    if ($(event.target).hasClass('glyphicon-pushpin')) {
      return false;
    }
    var $prev_tab_a = $(this).parent().parent().prev().children().first();
    var $tab_li = $(this).parent().parent();
    $tab_li.remove();
    var tab_div_id = $(this).parent().attr('href');
    $(tab_div_id).remove();
    var href=$prev_tab_a.attr('href');
    var selector='#menu-container a[href='+'"'+href+'"'+']';
    $(selector).parent().click();
    event.stopPropagation();
  }
  /**
   * [setLoader description]
   * @param {[type]} id [description]
   */
  var setLoader = function(id) {
    $('#' + id).empty();
    $('#' + id).append('<div class="loader-inner ball-pulse" style = "margin-top:20%;margin-left:45%"><div></div><div></div><div></div></div>');
  }

  /**
   * [removeLoader description]
   * @param  {[type]} id [description]
   * @return {[type]}    [description]
   */
  var removeLoader = function(id) {
    $('#' + id).empty();
  }

  /**
   * [loadingResource description]
   * @param  {[type]} id [description]
   * @return {[type]}    [description]
   */
  var loadingResource = function(id) {
    var url = 'views/' + id + '.html';
    $.ajax({
      url: url,
      type: 'GET',
      dataType: 'html',
      beforeSend: function(XHR) {
        setLoader(id);
      }
    })
        .done(function(data) {
          removeLoader(id);
          $('#' + id).append(data);
        })
        .fail(function() {
        })
        .always(function() {
        });
  }

  var btnMenuItemSearchClick = function(event) {
    event.preventDefault();
    var current = $('#iptMenuSearch').typeahead("getActive");
    if (current) {
      // Some item from your model is active!
      if (current.name == $('#iptMenuSearch').val()) {
        // This means the exact match is found. Use toLowerCase() if you want case insensitive match.
        var id = current.id;
        $('#' + id).click();
      } else {
        // This means it is only a partial match, you can either add a new item
        // or take the active if you don't want new items
        setMessage('当前条件没有找到相应资源', 'info', true);
      }
    } else {
      // Nothing is active so it is a new value (or maybe empty value)
      // Some item from your model is active!
      if ($('#iptMenuSearch').val() == '') {
        // This means the exact match is found. Use toLowerCase() if you want case insensitive match.
        setMessage('请输入资源名称', 'info', true);
      } else {
        // This means it is only a partial match, you can either add a new item
        // or take the active if you don't want new items
        setMessage('当前条件没有找到相应资源', 'info', true);
      }
    }
    event.stopPropagation();
  }
  $('#btnMenuItemSearch').on('click', btnMenuItemSearchClick);

  var msgYourMsgClick = function(event) {
    event.preventDefault();
    $('#mc_home').click();
    $(this).children().first().remove();
    event.stopPropagation();
  }
  $('#msgYourMsg').on('click', msgYourMsgClick);

  /*loading homepage*/
  loadingResource('home');
  /*loading homepage*/
});