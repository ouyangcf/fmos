window.onload = function() {
    /* Act on the event */
    jQuery(document).ready(function($) {
        $('.login-form input').change(function(event) {
            /* Act on the event */
            var contents = $(this).val();
            if (contents == '') {
                $(this).addClass('has-error');
                $(this).next().addClass('has-error');
            } else {
                $(this).removeClass('has-error');
                $(this).next().removeClass('has-error');
            }
        });

        var loginEvent = function(event) {
            /* Act on the event */
            event.preventDefault();
            var inputs = $('.login-form input');
            if (emptyTest(inputs)) {
                setMessage('账号密码不能为空', 'error', true);
                return false;
            } else {
                setMessage('正在登录', 'info', true);
                $(this).off('click');
                var userid = $(inputs[0]).val().trim();
                var userpassword = $(inputs[1]).val().trim();
                var msg_str = { "userId": userid, "userPassword": userpassword };
                $.ajax({
                        url: 'user/log/in',
                        type: 'POST',
                        contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                        dataType: 'json',
                        data: msg_str
                    })
                    .done(function(data) {
                        if (data.retCode == -1) {
                            setMessage(data.errDesc, 'error', true);
                            return;
                        }
                        if (data.retCode == 1) {
                            var $a = $('<a href="home.jsp"></a>');
                            var $span = $('<span>home</span>');
                            $a.append($span);
                            $span.click();
                        }
                    })
                    .fail(function(data) {
                        var $a = $('<a href="error.html"></a>');
                        var $span = $('<span>error</span>');
                        $a.append($span);
                        $span.click();
                    })
                    .always(function(data) {
                        $('#btn_login').on('click', loginEvent);
                    });
            }
        }

        $('#btn_login').on('click', loginEvent);
    });
};
