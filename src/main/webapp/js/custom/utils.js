/**
 * [print a message to console]
 * @param  {[type]} msg [description]
 */
function out(msg) {
  console.log(msg);
}

/**
 * [emptyTest description]
 * @param  {[type]} input [description]
 * @return {[type]} result [description]
 */
function emptyTest(input) {
  var result = false;
  input.each(function(index, el) {
    if (jQuery(this).val() == '') {
      result = true;
    }
  });
  return result;
}

/**
 * [setMessage description]
 * @param {[type]} msg        [description]
 * @param {[type]} type       [description]
 * @param {[type]} canBeClose [description]
 */
function setMessage(msg, type, canBeClose) {
  Messenger().post({
    message: msg,
    type: type,
    showCloseButton: canBeClose,
    hideAfter: 3,
    id: 'oneMsg'
  });
}

/**
 * [Format description]
 * @param {[type]} fmt [description]
 */
Date.prototype.format =function(format) { //author: meizz
  var o = {
    "M+" : this.getMonth()+1, //month
    "d+" : this.getDate(),    //day
    "h+" : this.getHours(),   //hour
    "m+" : this.getMinutes(), //minute
    "s+" : this.getSeconds(), //second
    "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
    "S" : this.getMilliseconds() //millisecond
  }
  if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
  (this.getFullYear()+"").substr(4- RegExp.$1.length));
  for(var k in o)if(new RegExp("("+ k +")").test(format))
  format = format.replace(RegExp.$1,
  RegExp.$1.length==1? o[k] :
  ("00"+ o[k]).substr((""+ o[k]).length));
  return format;
}

