/**
 * <p>Title: header</p>
 * <p>Description: header</p>
 * <p>Copyright: Copyright (c) 2018</p>
 *
 * @author wangqian
 * @date 2018-02-06
 * @version 1.0
 */
$('.headerWrap').html('<header class="pure-g" id="header"> <div class="pure-u-1 pure-u-lg-4-24"> '+
    '<div class="logo"><a href="https://wangqiantra.github.io/QianWalteWang/src/main/webapp/html/github_pages/page/static-home.html"><img src="https://wangqiantra.github.io/QianWalteWang/src/main/webapp/html/base/imgs/qwaltewang-logo.svg" class="pure-img" alt="" /></a></div> '+
    '</div> '+
    '<input type="checkbox" id="menu-toggle-cb"> '+
    '<label id="menu-toggle" for="menu-toggle-cb" onclick><s class="bar"></s><s class="bar"></s><s class="bar"></s></label> '+
    '<div class="pure-u-1 pure-u-lg-20-24 box-relative menu-wrapper"> '+
    '     <nav class="pure-menu pure-menu-horizontal menu-local"> '+
    '         <ul class="pure-menu-list"> '+
    '             <li class="pure-menu-item"><a href="https://wangqiantra.github.io/QianWalteWang/src/main/webapp/html/github_pages/page/static-home.html" class="pure-menu-link">Home</a></li> '+
    '             <li class="pure-menu-item"><a href="https://wangqiantra.github.io/QianWalteWang/src/main/webapp/html/github_pages/page/friendly-404.html" class="pure-menu-link">Downloads</a></li> '+
    '             <li class="pure-menu-item"><a href="https://wangqiantra.github.io/QianWalteWang/src/main/webapp/html/github_pages/page/friendly-404.html" class="pure-menu-link">About</a></li> '+
    '             <li class="pure-menu-item"><a href="https://wangqiantra.github.io/QianWalteWang/src/main/webapp/html/github_pages/page/friendly-404.html" class="pure-menu-link">Community</a></li> '+
    '             <li class="pure-menu-item"><a href="https://wangqiantra.github.io/QianWalteWang/src/main/webapp/html/github_pages/page/friendly-404.html" class="pure-menu-link">Sponsors</a></li> '+
    '         </ul> '+
    '     </nav> '+
    '     <nav class="pure-menu pure-menu-horizontal menu-external"> '+
    '         <ul class="pure-menu-list"> '+
    '             <li class="pure-menu-item"><a href="https://wangqiantra.github.io/QianWalteWang/src/main/webapp/html/github_pages/page/walte-wiki.html" class="pure-menu-link">wiki</a></li> '+
    '             <li class="pure-menu-item"><a href="https://github.com/wangqiantra/QianWalteWang" class="pure-menu-link"><i class="fa fa-github"></i> github</a></li> '+
    '             <li class="pure-menu-item"><a href="mailto:wangqian_live@163.com" class="pure-menu-link"><meta itemprop="email" content="wangqian_live@163.com"/>mail</a></li> '+
    '             <li class="pure-menu-item"><a href="https://wangqiantra.github.io/QianWalteWang/src/main/webapp/html/github_pages/page/friendly-404.html" class="pure-menu-link">author</a></li> '+
    '             <li class="pure-menu-item"><a href="https://wangqiantra.github.io/QianWalteWang/src/main/webapp/html/github_pages/page/friendly-404.html" class="pure-menu-link"><i class="fa fa-user-circle"></i> sign in</a></li> '+
    '         </ul> '+
    '     </nav> '+
    ' </div> '+
    ' </header>');

$('.footerWrap').html('<footer>© Copyright 2018 Q . Walte Wang all rights reserved | <a href="https://wangqiantra.github.io/QianWalteWang/src/main/webapp/html/github_pages/page/privacy-policy.html">Privacy Policy</a></footer>');

//使元素为class="href-Invalid"的href失效
$(".href-invalid").click(function () {
    return false;
});

//执行动态切换北京
IntervalBackImg();

//functions
function IntervalBackImg() {
    window.setInterval(RandomBackImg, 10000);
}

function RandomBackImg() {
    var imgArr = ["banner0.jpg", "banner1.jpg" ,"banner2.jpg", "banner3.jpg"];
    var index = RandomNum(0, imgArr.length);
    $('.banner').css("background-image", "url(https://wangqiantra.github.io/QianWalteWang/src/main/webapp/html/main/imgs/"+imgArr[index]+")");
}

function RandomNum(Min, Max) {
    var Range = Max - Min;
    var Rand = Math.random();
    var num = Min + Math.floor(Rand * Range);
    return num;
}