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
                          '<div class="logo"><a href="/"><img src="/html/base/imgs/qwaltewang-logo.svg" class="pure-img" alt="" /></a></div> '+
                          '</div> '+
                          '<input type="checkbox" id="menu-toggle-cb"> '+
                          '<label id="menu-toggle" for="menu-toggle-cb" onclick><s class="bar"></s><s class="bar"></s><s class="bar"></s></label> '+
                          '<div class="pure-u-1 pure-u-lg-20-24 box-relative menu-wrapper"> '+
                          '     <nav class="pure-menu pure-menu-horizontal menu-local"> '+
                          '         <ul class="pure-menu-list"> '+
                          '             <li class="pure-menu-item"><a href="/" class="pure-menu-link">Home</a></li> '+
                          '             <li class="pure-menu-item"><a href="/article" class="pure-menu-link">Articles</a></li> '+
                          '             <li class="pure-menu-item"><a href="/project" class="pure-menu-link">Projects</a></li> '+
                          '             <li class="pure-menu-item"><a href="/about" class="pure-menu-link">About</a></li> '+
                          '             <li class="pure-menu-item"><a href="/sponsor" class="pure-menu-link">Sponsor</a></li> '+
                          '         </ul> '+
                          '     </nav> '+
                          '     <nav class="pure-menu pure-menu-horizontal menu-external"> '+
                          '         <ul class="pure-menu-list"> '+
                          '             <li class="pure-menu-item"><a href="https://wangqiantra.github.io/QianWalteWang/src/main/webapp/html/github_pages/page/walte-wiki.html" class="pure-menu-link">wiki</a></li> '+
                          '             <li class="pure-menu-item"><a href="https://github.com/wangqiantra/QianWalteWang" class="pure-menu-link"><i class="fa fa-github"></i> github</a></li> '+
                          '             <li class="pure-menu-item"><a href="mailto:wangqian_live@163.com" class="pure-menu-link"><meta itemprop="email" content="wangqian_live@163.com"/>mail</a></li> '+
                          '             <li class="pure-menu-item"><a href="/html/main/page/friendly/000.html" class="pure-menu-link">author</a></li> '+
                          '             <li class="pure-menu-item"><a href="/html/main/page/friendly/000.html" class="pure-menu-link"><i class="fa fa-user-circle"></i> sign in</a></li> '+
                          '         </ul> '+
                          '     </nav> '+
                          ' </div> '+
                      ' </header>');

$('.footerWrap').html('<footer>© Copyright 2018 Q . Walte Wang all rights reserved | <a href="/html/main/page/policy/privacy-policy.html">Privacy Policy</a></footer>');

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