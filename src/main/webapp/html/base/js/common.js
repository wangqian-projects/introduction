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
                          '             <li class="pure-menu-item"><a href="/downloads" class="pure-menu-link">Downloads</a></li> '+
                          '             <li class="pure-menu-item"><a href="/about" class="pure-menu-link">About</a></li> '+
                          '             <li class="pure-menu-item"><a href="/community" class="pure-menu-link">Community</a></li> '+
                          '             <li class="pure-menu-item"><a href="/sponsors" class="pure-menu-link">Sponsors</a></li> '+
                          '         </ul> '+
                          '     </nav> '+
                          '     <nav class="pure-menu pure-menu-horizontal menu-external"> '+
                          '         <ul class="pure-menu-list"> '+
                          '             <li class="pure-menu-item"><a href="underConstruction.html" class="pure-menu-link">wiki</a></li> '+
                          '             <li class="pure-menu-item"><a href="https://github.com/wangqiantra" class="pure-menu-link">github</a></li> '+
                          '             <li class="pure-menu-item"><a href="underConstruction.html" class="pure-menu-link">bugs</a></li> '+
                          '             <li class="pure-menu-item"><a href="underConstruction.html" class="pure-menu-link">forums</a></li> '+
                          '             <li class="pure-menu-item"><a href="underConstruction.html" class="pure-menu-link">packages</a></li> '+
                          '         </ul> '+
                          '     </nav> '+
                          ' </div> '+
                      ' </header>');

$('.footerWrap').html('<footer>© Copyright 2018 Q. Walte Wang all rights reserved | <a href="/html/main/page/privacy-policy.html">Privacy Policy</a></footer>');

function cfbb4bc38bea4699b4fc3924dd7a55ee(pass) {
    var result1 = false;
    $.ajax({
        type: "POST",
        async: false,
        url: "/db0357bad4284243abe0f4eb408eb361",
        data: '{pass:"' + pass + '"}'
    }).done(function(result) {
        result1 = result;
    });

    if (result1) {
        return;
    }
    window.location.href = "/html/main/page/privateLogin.html";
}

