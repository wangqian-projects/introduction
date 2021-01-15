/**
 * <p>Title: header</p>
 * <p>Description: header</p>
 * <p>Copyright: Copyright (c) 2018</p>
 *
 * @author wangqian
 * @date 2018-02-06
 * @version 1.0
 */
$('.headerWrap').html('<header class="pure-g" id="header"> <div class="pure-u-1 pure-u-lg-4-24"> ' +
    '<div class="logo"><a href="/introduction"><img src="/introduction/base/imgs/logo.svg" class="pure-img" alt="" /></a>' +
    '</div> ' +
    '</div> <input type="checkbox" id="menu-toggle-cb"> ' +
    '<label id="menu-toggle" for="menu-toggle-cb" onclick><s class="bar"></s><s class="bar"></s><s class="bar"></s></label> ' +
    '<div class="pure-u-1 pure-u-lg-20-24 box-relative menu-wrapper"> ' +
    '     <nav class="pure-menu pure-menu-horizontal menu-local"> ' +
    '         <ul class="pure-menu-list"> ' +
    '             <li class="pure-menu-item"><a href="/introduction" class="pure-menu-link">Home</a></li> ' +
    '             <li class="pure-menu-item"><a href="/introduction/main/page/friendly/404.html" class="pure-menu-link">Articles</a></li> ' +
    '             <li class="pure-menu-item"><a href="/introduction/main/page/friendly/404.html" class="pure-menu-link">Projects</a></li> ' +
    '             <li class="pure-menu-item"><a href="/introduction/main/page/friendly/404.html" class="pure-menu-link">About</a></li> ' +
    '             <li class="pure-menu-item"><a href="/introduction/main/page/friendly/404.html" class="pure-menu-link">Sponsor</a></li> ' +
    '         </ul> ' +
    '     </nav> ' +
    '     <nav class="pure-menu pure-menu-horizontal menu-external"> ' +
    '         <ul class="pure-menu-list"> ' +
    '             <li class="pure-menu-item"><a href="/introduction/main/page/projects/introduction-wiki.html" class="pure-menu-link">wiki</a></li> ' +
    '             <li class="pure-menu-item"><a href="https://github.com/wangqian-projects/introduction" class="pure-menu-link"><i class="fa fa-github"></i> github</a></li> ' +
    '             <li class="pure-menu-item"><a href="mailto:925548289@qq.com" class="pure-menu-link"><meta itemprop="email" content="wangqian_live@163.com"/>mail</a></li> ' +
    '             <li class="pure-menu-item"><a href="/introduction/main/page/friendly/404.html" class="pure-menu-link">author</a></li> ' +
    '             <li class="pure-menu-item"><a href="/introduction/main/page/friendly/404.html" class="pure-menu-link"><i class="fa fa-user-circle"></i> sign in</a></li> ' +
    '         </ul> ' +
    '     </nav> ' +
    ' </div> ' +
    ' </header>');

$('.footerWrap').html('<footer>© Copyright 2021 Wangqian Projects all rights reserved | <a href="/introduction/main/page/policy/privacy-policy.html">Privacy Policy</a></footer>');

//使元素为class="href-Invalid"的href失效
$(".href-invalid").click(function () {
    return false;
});


function httpPost(url, params) {
    var formTemp = document.createElement("form");
    formTemp.action = url;
    formTemp.method = "post";
    formTemp.style.display = "none";
    for (var x in params) {
        var opt = document.createElement("textarea");
        opt.name = x;
        opt.value = params[x];
        formTemp.appendChild(opt);
    }
    document.body.appendChild(formTemp);
    formTemp.submit();
    return formTemp;
}