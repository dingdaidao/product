// 首页文字特效

(function ($) {
    $.fn.textyleF = function (options) {
        var target = this;
        var targetTxt = target.contents();
        var defaults = {
            duration: 1000,
            delay: 150,
            easing: 'ease',
            callback: null
        };
        var setting = $.extend(defaults, options);
        //split txt & wrap txt by span
        targetTxt.each(function () {
            var texts = $(this);
            if (this.nodeType === 3) {
                mkspn(texts);
            }
        });
        function mkspn(texts) {
            texts.replaceWith(texts.text().replace(/(\S)/g, '<span>$1</span>'));
        }
        //txt animation
        return this.each(function (i) {
            var child = target.children('span');
            target.css('opacity', 1);
            child.each(function (i) {
                $(this).delay(setting.delay * i)
                    .queue(function (next) {
                        $(this).css({
                            display: 'inline-block',
                            transform: 'rotateY(0deg) rotateX(0deg)',
                            opacity: 1,
                            transitionDuration: setting.duration + 'ms',
                            transitionTimingFunction: setting.easing
                        })
                        next();
                    });
                
            });
        });
    };
}(jQuery));



$(window).on('load', function () {

    $('.ex1').textyleF(
        {
            duration: 1000,
            delay: 150,
            easing: 'cubic-bezier(0.77, 0, 0.175, 1)',
            callback: function () {
                $(this).css({
                    color: '#fff',
                    transition: '0.5s',
                });
                $('.desc').css('opacity', 1);
            }
        }
    );

    $('.ex2').textyleF({
        duration: 1000,
        delay: 150,
        easing: 'cubic-bezier(0.77, 0, 0.175, 1)',
        callback: function () {
            $(this).css({
                color: '#fff',
                transition: '0.5s',
            });
            $('.desc').css('opacity', 1);
        }
    });
});
// 首页文字特效



// 首页背景切换
// 首页背景切换
