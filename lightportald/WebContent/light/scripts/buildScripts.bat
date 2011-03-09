set version=1.4.1

del ..\..\cache\init\*.js
copy json2.js ..\..\cache\init\a1.tjs
copy rsh.js ..\..\cache\init\a2.tjs
copy template.js ..\..\cache\init\a3.tjs
copy jquery.min.js ..\..\cache\init\a4.tjs
FOR %%1 in (..\..\cache\init\*.tjs) do type %%1 >> ..\..\cache\init\tmp.mjs
java -jar yuicompressor-2.4.2.jar --type js ..\..\cache\init\tmp.mjs -o ..\..\cache\init\all%version%.js
del ..\..\cache\init\*.tjs
del ..\..\cache\init\*.mjs

del ..\..\cache\main\*.js
copy json2.js ..\..\cache\main\a1.tjs
copy jquery.min.js ..\..\cache\main\a2.tjs
copy rsh.js ..\..\cache\main\a3.tjs
copy template.js ..\..\cache\main\a4.tjs
copy light.js ..\..\cache\main\light.tjs
copy lightExt.js ..\..\cache\main\lightExt.tjs
copy lightAjax.js ..\..\cache\main\lightAjax.tjs
copy lightPortal.js ..\..\cache\main\lightPortal.tjs
copy lightPortalCore.js ..\..\cache\main\lightPortalCore.tjs
copy lightPortalFooter.js ..\..\cache\main\lightPortalFooter.tjs
copy lightPortalHeader.js ..\..\cache\main\lightPortalHeader.tjs
copy lightPortalMenu.js ..\..\cache\main\lightPortalMenu.tjs
copy lightPortalMenuMain.js ..\..\cache\main\lightPortalMenuMain.tjs
copy lightPortalTab.js ..\..\cache\main\lightPortalTab.tjs
copy lightPortlet.js ..\..\cache\main\lightPortlet.tjs
copy lightService.js ..\..\cache\main\lightService.tjs
copy lightUtil.js ..\..\cache\main\lightUtil.tjs
copy lightWindow.js ..\..\cache\main\lightWindow.tjs
FOR %%1 in (..\..\cache\main\*.tjs) do type %%1 >> ..\..\cache\main\tmp.mjs
java -jar yuicompressor-2.4.2.jar --type js ..\..\cache\main\tmp.mjs -o ..\..\cache\main\all%version%.js
del ..\..\cache\main\*.tjs
del ..\..\cache\main\*.mjs

del ..\..\cache\mainMobile\*.js
copy json2.js ..\..\cache\mainMobile\a1.tjs
copy jquery.min.js ..\..\cache\mainMobile\a2.tjs
copy rsh.js ..\..\cache\mainMobile\a3.tjs
copy template.js ..\..\cache\mainMobile\a4.tjs
copy light.js ..\..\cache\mainMobile\light.tjs
copy lightExt.js ..\..\cache\mainMobile\lightExt.tjs
copy lightAjax.js ..\..\cache\mainMobile\lightAjax.tjs
copy lightPortalMobile.js ..\..\cache\mainMobile\lightPortalMobile.tjs
copy lightPortalCore.js ..\..\cache\mainMobile\lightPortalCore.tjs
copy lightPortalMobileCore.js ..\..\cache\mainMobile\lightPortalMobileCore.tjs
copy lightPortalFooter.js ..\..\cache\mainMobile\lightPortalFooter.tjs
copy lightPortalHeader.js ..\..\cache\mainMobile\lightPortalHeader.tjs
copy lightPortalMenu.js ..\..\cache\mainMobile\lightPortalMenu.tjs
copy lightPortalMenuMobile.js ..\..\cache\mainMobile\lightPortalMenuMobile.tjs
copy lightPortalTab.js ..\..\cache\mainMobile\lightPortalTab.tjs
copy lightPortlet.js ..\..\cache\mainMobile\lightPortlet.tjs
copy lightService.js ..\..\cache\mainMobile\lightService.tjs
copy lightUtil.js ..\..\cache\mainMobile\lightUtil.tjs
copy lightWindow.js ..\..\cache\mainMobile\lightWindow.tjs
FOR %%1 in (..\..\cache\mainMobile\*.tjs) do type %%1 >> ..\..\cache\mainMobile\tmp.mjs
java -jar yuicompressor-2.4.2.jar --type js ..\..\cache\mainMobile\tmp.mjs -o ..\..\cache\mainMobile\all%version%.js
del ..\..\cache\mainMobile\*.tjs
del ..\..\cache\mainMobile\*.mjs

del ..\..\cache\mainGroup\*.js
copy jquery.min.js ..\..\cache\mainGroup\a2.tjs
copy json2.js ..\..\cache\mainGroup\a1.tjs
copy rsh.js ..\..\cache\mainGroup\a3.tjs
copy template.js ..\..\cache\mainGroup\a4.tjs
copy light.js ..\..\cache\mainGroup\light.tjs
copy lightExt.js ..\..\cache\mainGroup\lightExt.tjs
copy lightAjax.js ..\..\cache\mainGroup\lightAjax.tjs
copy lightPortal.js ..\..\cache\mainGroup\lightPortal.tjs
copy lightPortalCore.js ..\..\cache\mainGroup\lightPortalCore.tjs
copy lightPortalGroup.js ..\..\cache\mainGroup\lightPortalGroup.tjs
copy lightPortalFooter.js ..\..\cache\mainGroup\lightPortalFooter.tjs
copy lightPortalHeader.js ..\..\cache\mainGroup\lightPortalHeader.tjs
copy lightPortalMenu.js ..\..\cache\mainGroup\lightPortalMenu.tjs
copy lightPortalMenuView.js ..\..\cache\mainGroup\lightPortalMenuView.tjs
copy lightPortalTab.js ..\..\cache\mainGroup\lightPortalTab.tjs
copy lightPortlet.js ..\..\cache\mainGroup\lightPortlet.tjs
copy lightService.js ..\..\cache\mainGroup\lightService.tjs
copy lightUtil.js ..\..\cache\mainGroup\lightUtil.tjs
copy lightWindow.js ..\..\cache\mainGroup\lightWindow.tjs
FOR %%1 in (..\..\cache\mainGroup\*.tjs) do type %%1 >> ..\..\cache\mainGroup\tmp.mjs
java -jar yuicompressor-2.4.2.jar --type js ..\..\cache\mainGroup\tmp.mjs -o ..\..\cache\mainGroup\all%version%.js
che\mainGroup\all%version%.js
del ..\..\cache\mainGroup\*.tjs
del ..\..\cache\mainGroup\*.mjs

del ..\..\cache\mainSub\*.js
copy json2.js ..\..\cache\mainSub\a1.tjs
copy jquery.min.js ..\..\cache\mainSub\a2.tjs
copy rsh.js ..\..\cache\mainSub\a3.tjs
copy template.js ..\..\cache\mainSub\a4.tjs
copy light.js ..\..\cache\mainSub\light.tjs
copy lightExt.js ..\..\cache\mainSub\lightExt.tjs
copy lightAjax.js ..\..\cache\mainSub\lightAjax.tjs
copy lightPortal.js ..\..\cache\mainSub\lightPortal.tjs
copy lightPortalCore.js ..\..\cache\mainSub\lightPortalCore.tjs
copy lightPortalGroup.js ..\..\cache\mainSub\lightPortalGroup.tjs
copy lightPortalFooter.js ..\..\cache\mainSub\lightPortalFooter.tjs
copy lightPortalHeader.js ..\..\cache\mainSub\lightPortalHeader.tjs
copy lightPortalMenu.js ..\..\cache\mainSub\lightPortalMenu.tjs
copy lightPortalMenuView.js ..\..\cache\mainSub\lightPortalMenuSub.tjs
copy lightPortalTab.js ..\..\cache\mainSub\lightPortalTab.tjs
copy lightPortlet.js ..\..\cache\mainSub\lightPortlet.tjs
copy lightService.js ..\..\cache\mainSub\lightService.tjs
copy lightUtil.js ..\..\cache\mainSub\lightUtil.tjs
copy lightWindow.js ..\..\cache\mainSub\lightWindow.tjs
FOR %%1 in (..\..\cache\mainSub\*.tjs) do type %%1 >> ..\..\cache\mainSub\tmp.mjs
java -jar yuicompressor-2.4.2.jar --type js ..\..\cache\mainSub\tmp.mjs -o ..\..\cache\mainSub\all%version%.js
del ..\..\cache\mainSub\*.tjs
del ..\..\cache\mainSub\*.mjs

del ..\..\cache\mainPage\*.js
copy json2.js ..\..\cache\mainPage\a1.tjs
copy jquery.min.js ..\..\cache\mainPage\a2.tjs
copy rsh.js ..\..\cache\mainPage\a3.tjs
copy template.js ..\..\cache\mainPage\a4.tjs
copy light.js ..\..\cache\mainPage\light.tjs
copy lightExt.js ..\..\cache\mainPage\lightExt.tjs
copy lightAjax.js ..\..\cache\mainPage\lightAjax.tjs
copy lightPortal.js ..\..\cache\mainPage\lightPortal.tjs
copy lightPortalCore.js ..\..\cache\mainPage\lightPortalCore.tjs
copy lightPortalPage.js ..\..\cache\mainPage\lightPortalPage.tjs
copy lightPortalFooter.js ..\..\cache\mainPage\lightPortalFooter.tjs
copy lightPortalHeader.js ..\..\cache\mainPage\lightPortalHeader.tjs
copy lightPortalMenu.js ..\..\cache\mainPage\lightPortalMenu.tjs
copy lightPortalMenuView.js ..\..\cache\mainPage\lightPortalMenuView.tjs
copy lightPortalTab.js ..\..\cache\mainPage\lightPortalTab.tjs
copy lightPortlet.js ..\..\cache\mainPage\lightPortlet.tjs
copy lightService.js ..\..\cache\mainPage\lightService.tjs
copy lightUtil.js ..\..\cache\mainPage\lightUtil.tjs
copy lightWindow.js ..\..\cache\mainPage\lightWindow.tjs
FOR %%1 in (..\..\cache\mainPage\*.tjs) do type %%1 >> ..\..\cache\mainPage\tmp.mjs
java -jar yuicompressor-2.4.2.jar --type js ..\..\cache\mainPage\tmp.mjs -o ..\..\cache\mainPage\all%version%.js
del ..\..\cache\mainPage\*.tjs
del ..\..\cache\mainPage\*.mjs

del ..\..\cache\mainView\*.js
copy json2.js ..\..\cache\mainView\a1.tjs
copy jquery.min.js ..\..\cache\mainView\a2.tjs
copy rsh.js ..\..\cache\mainView\a3.tjs
copy template.js ..\..\cache\mainView\a4.tjs
copy light.js ..\..\cache\mainView\light.tjs
copy lightExt.js ..\..\cache\mainView\lightExt.tjs
copy lightAjax.js ..\..\cache\mainView\lightAjax.tjs
copy lightPortal.js ..\..\cache\mainView\lightPortal.tjs
copy lightPortalCore.js ..\..\cache\mainView\lightPortalCore.tjs
copy lightPortalView.js ..\..\cache\mainView\lightPortalView.tjs
copy lightPortalFooter.js ..\..\cache\mainView\lightPortalFooter.tjs
copy lightPortalHeader.js ..\..\cache\mainView\lightPortalHeader.tjs
copy lightPortalMenu.js ..\..\cache\mainView\lightPortalMenu.tjs
copy lightPortalMenuView.js ..\..\cache\mainView\lightPortalMenuView.tjs
copy lightPortalTab.js ..\..\cache\mainView\lightPortalTab.tjs
copy lightPortlet.js ..\..\cache\mainView\lightPortlet.tjs
copy lightService.js ..\..\cache\mainView\lightService.tjs
copy lightUtil.js ..\..\cache\mainView\lightUtil.tjs
copy lightWindow.js ..\..\cache\mainView\lightWindow.tjs
FOR %%1 in (..\..\cache\mainView\*.tjs) do type %%1 >> ..\..\cache\mainView\tmp.mjs
java -jar yuicompressor-2.4.2.jar --type js ..\..\cache\mainView\tmp.mjs -o ..\..\cache\mainView\all%version%.js
del ..\..\cache\mainView\*.tjs
del ..\..\cache\mainView\*.mjs


del ..\..\cache\mainChat\*.js
copy json2.js ..\..\cache\mainChat\a1.tjs
copy rsh.js ..\..\cache\mainChat\a3.tjs
copy template.js ..\..\cache\mainChat\a4.tjs
copy lightPortletChatPop.js ..\..\cache\mainChat\light.tjs
copy lightAjax.js ..\..\cache\mainChat\lightAjax.tjs
copy lightUtil.js ..\..\cache\mainChat\lightUtil.tjs
FOR %%1 in (..\..\cache\mainChat\*.tjs) do type %%1 >> ..\..\cache\mainChat\tmp.mjs
java -jar yuicompressor-2.4.2.jar --type js ..\..\cache\mainChat\tmp.mjs -o ..\..\cache\mainChat\all%version%.js
del ..\..\cache\mainChat\*.tjs
del ..\..\cache\mainChat\*.mjs





del ..\..\cache\portal\*.js
copy light.js ..\..\cache\portal\light.tjs
copy lightExt.js ..\..\cache\portal\lightExt.tjs
copy lightAjax.js ..\..\cache\portal\lightAjax.tjs
copy lightPortal.js ..\..\cache\portal\lightPortal.tjs
copy lightPortalCore.js ..\..\cache\portal\lightPortalCore.tjs
copy lightPortalFooter.js ..\..\cache\portal\lightPortalFooter.tjs
copy lightPortalHeader.js ..\..\cache\portal\lightPortalHeader.tjs
copy lightPortalMenu.js ..\..\cache\portal\lightPortalMenu.tjs
copy lightPortalMenuMain.js ..\..\cache\portal\lightPortalMenuMain.tjs
copy lightPortalTab.js ..\..\cache\portal\lightPortalTab.tjs
copy lightPortlet.js ..\..\cache\portal\lightPortlet.tjs
copy lightService.js ..\..\cache\portal\lightService.tjs
copy lightUtil.js ..\..\cache\portal\lightUtil.tjs
copy lightWindow.js ..\..\cache\portal\lightWindow.tjs
FOR %%1 in (..\..\cache\portal\*.tjs) do type %%1 >> ..\..\cache\portal\tmp.mjs
jsmin <..\..\cache\portal\tmp.mjs> ..\..\cache\portal\all%version%.js
del ..\..\cache\portal\*.tjs
del ..\..\cache\portal\*.mjs

del ..\..\cache\portalMobile\*.js
copy light.js ..\..\cache\portalMobile\light.tjs
copy lightExt.js ..\..\cache\portalMobile\lightExt.tjs
copy lightAjax.js ..\..\cache\portalMobile\lightAjax.tjs
copy lightportalMobile.js ..\..\cache\portalMobile\lightportalMobile.tjs
copy lightPortalCore.js ..\..\cache\portalMobile\lightPortalCore.tjs
copy lightportalMobileCore.js ..\..\cache\portalMobile\lightportalMobileCore.tjs
copy lightPortalFooter.js ..\..\cache\portalMobile\lightPortalFooter.tjs
copy lightPortalHeader.js ..\..\cache\portalMobile\lightPortalHeader.tjs
copy lightPortalMenu.js ..\..\cache\portalMobile\lightPortalMenu.tjs
copy lightPortalMenuMobile.js ..\..\cache\portalMobile\lightPortalMenuMobile.tjs
copy lightPortalTab.js ..\..\cache\portalMobile\lightPortalTab.tjs
copy lightPortlet.js ..\..\cache\portalMobile\lightPortlet.tjs
copy lightService.js ..\..\cache\portalMobile\lightService.tjs
copy lightUtil.js ..\..\cache\portalMobile\lightUtil.tjs
copy lightWindow.js ..\..\cache\portalMobile\lightWindow.tjs
FOR %%1 in (..\..\cache\portalMobile\*.tjs) do type %%1 >> ..\..\cache\portalMobile\tmp.mjs
jsmin <..\..\cache\portalMobile\tmp.mjs> ..\..\cache\portalMobile\all%version%.js
del ..\..\cache\portalMobile\*.tjs
del ..\..\cache\portalMobile\*.mjs

del ..\..\cache\portalGroup\*.js
copy light.js ..\..\cache\portalGroup\light.tjs
copy lightExt.js ..\..\cache\portalGroup\lightExt.tjs
copy lightAjax.js ..\..\cache\portalGroup\lightAjax.tjs
copy lightPortal.js ..\..\cache\portalGroup\lightPortal.tjs
copy lightPortalCore.js ..\..\cache\portalGroup\lightPortalCore.tjs
copy lightPortalGroup.js ..\..\cache\portalGroup\lightPortalGroup.tjs
copy lightPortalFooter.js ..\..\cache\portalGroup\lightPortalFooter.tjs
copy lightPortalHeader.js ..\..\cache\portalGroup\lightPortalHeader.tjs
copy lightPortalMenu.js ..\..\cache\portalGroup\lightPortalMenu.tjs
copy lightPortalMenuView.js ..\..\cache\portalGroup\lightPortalMenuView.tjs
copy lightPortalTab.js ..\..\cache\portalGroup\lightPortalTab.tjs
copy lightPortlet.js ..\..\cache\portalGroup\lightPortlet.tjs
copy lightService.js ..\..\cache\portalGroup\lightService.tjs
copy lightUtil.js ..\..\cache\portalGroup\lightUtil.tjs
copy lightWindow.js ..\..\cache\portalGroup\lightWindow.tjs
FOR %%1 in (..\..\cache\portalGroup\*.tjs) do type %%1 >> ..\..\cache\portalGroup\tmp.mjs
jsmin <..\..\cache\portalGroup\tmp.mjs> ..\..\cache\portalGroup\all%version%.js
del ..\..\cache\portalGroup\*.tjs
del ..\..\cache\portalGroup\*.mjs

del ..\..\cache\portalSub\*.js
copy light.js ..\..\cache\portalSub\light.tjs
copy lightExt.js ..\..\cache\portalSub\lightExt.tjs
copy lightAjax.js ..\..\cache\portalSub\lightAjax.tjs
copy lightPortal.js ..\..\cache\portalSub\lightPortal.tjs
copy lightPortalCore.js ..\..\cache\portalSub\lightPortalCore.tjs
copy lightPortalGroup.js ..\..\cache\portalSub\lightPortalGroup.tjs
copy lightPortalFooter.js ..\..\cache\portalSub\lightPortalFooter.tjs
copy lightPortalHeader.js ..\..\cache\portalSub\lightPortalHeader.tjs
copy lightPortalMenu.js ..\..\cache\portalSub\lightPortalMenu.tjs
copy lightPortalMenuView.js ..\..\cache\portalSub\lightPortalMenuSub.tjs
copy lightPortalTab.js ..\..\cache\portalSub\lightPortalTab.tjs
copy lightPortlet.js ..\..\cache\portalSub\lightPortlet.tjs
copy lightService.js ..\..\cache\portalSub\lightService.tjs
copy lightUtil.js ..\..\cache\portalSub\lightUtil.tjs
copy lightWindow.js ..\..\cache\portalSub\lightWindow.tjs
FOR %%1 in (..\..\cache\portalSub\*.tjs) do type %%1 >> ..\..\cache\portalSub\tmp.mjs
jsmin <..\..\cache\portalSub\tmp.mjs> ..\..\cache\portalSub\all%version%.js
del ..\..\cache\portalSub\*.tjs
del ..\..\cache\portalSub\*.mjs

del ..\..\cache\portalPage\*.js
copy light.js ..\..\cache\portalPage\light.tjs
copy lightExt.js ..\..\cache\portalPage\lightExt.tjs
copy lightAjax.js ..\..\cache\portalPage\lightAjax.tjs
copy lightPortal.js ..\..\cache\portalPage\lightPortal.tjs
copy lightPortalCore.js ..\..\cache\portalPage\lightPortalCore.tjs
copy lightPortalPage.js ..\..\cache\portalPage\lightPortalPage.tjs
copy lightPortalFooter.js ..\..\cache\portalPage\lightPortalFooter.tjs
copy lightPortalHeader.js ..\..\cache\portalPage\lightPortalHeader.tjs
copy lightPortalMenu.js ..\..\cache\portalPage\lightPortalMenu.tjs
copy lightPortalMenuView.js ..\..\cache\portalPage\lightPortalMenuView.tjs
copy lightPortalTab.js ..\..\cache\portalPage\lightPortalTab.tjs
copy lightPortlet.js ..\..\cache\portalPage\lightPortlet.tjs
copy lightService.js ..\..\cache\portalPage\lightService.tjs
copy lightUtil.js ..\..\cache\portalPage\lightUtil.tjs
copy lightWindow.js ..\..\cache\portalPage\lightWindow.tjs
FOR %%1 in (..\..\cache\portalPage\*.tjs) do type %%1 >> ..\..\cache\portalPage\tmp.mjs
jsmin <..\..\cache\portalPage\tmp.mjs> ..\..\cache\portalPage\all%version%.js
del ..\..\cache\portalPage\*.tjs
del ..\..\cache\portalPage\*.mjs

del ..\..\cache\portalView\*.js
copy light.js ..\..\cache\portalView\light.tjs
copy lightExt.js ..\..\cache\portalView\lightExt.tjs
copy lightAjax.js ..\..\cache\portalView\lightAjax.tjs
copy lightPortal.js ..\..\cache\portalView\lightPortal.tjs
copy lightPortalCore.js ..\..\cache\portalView\lightPortalCore.tjs
copy lightPortalView.js ..\..\cache\portalView\lightPortalView.tjs
copy lightPortalFooter.js ..\..\cache\portalView\lightPortalFooter.tjs
copy lightPortalHeader.js ..\..\cache\portalView\lightPortalHeader.tjs
copy lightPortalMenu.js ..\..\cache\portalView\lightPortalMenu.tjs
copy lightPortalMenuView.js ..\..\cache\portalView\lightPortalMenuView.tjs
copy lightPortalTab.js ..\..\cache\portalView\lightPortalTab.tjs
copy lightPortlet.js ..\..\cache\portalView\lightPortlet.tjs
copy lightService.js ..\..\cache\portalView\lightService.tjs
copy lightUtil.js ..\..\cache\portalView\lightUtil.tjs
copy lightWindow.js ..\..\cache\portalView\lightWindow.tjs
FOR %%1 in (..\..\cache\portalView\*.tjs) do type %%1 >> ..\..\cache\portalView\tmp.mjs
jsmin <..\..\cache\portalView\tmp.mjs> ..\..\cache\portalView\all%version%.js
del ..\..\cache\portalView\*.tjs
del ..\..\cache\portalView\*.mjs

del ..\..\cache\portalChat\*.js
copy lightPortletChatPop.js ..\..\cache\portalChat\light.tjs
copy lightAjax.js ..\..\cache\portalChat\lightAjax.tjs
copy lightUtil.js ..\..\cache\portalChat\lightUtil.tjs
FOR %%1 in (..\..\cache\portalChat\*.tjs) do type %%1 >> ..\..\cache\portalChat\tmp.mjs
jsmin <..\..\cache\portalChat\tmp.mjs> ..\..\cache\portalChat\all%version%.js
del ..\..\cache\portalChat\*.tjs
del ..\..\cache\portalChat\*.mjs

del ..\..\cache\lazyLoading\*.js
copy lightFunctions.js ..\..\cache\lazyLoading\lightFunctions.tjs
copy lightFunctionsExt.js ..\..\cache\lazyLoading\lightFunctionsExt.tjs
copy lightPortletChat.js ..\..\cache\lazyLoading\lightPortletChat.tjs
FOR %%1 in (..\..\cache\lazyLoading\*.tjs) do type %%1 >> ..\..\cache\lazyLoading\tmp.mjs
java -jar yuicompressor-2.4.2.jar --type js ..\..\cache\lazyLoading\tmp.mjs -o ..\..\cache\lazyLoading\all%version%.js
del ..\..\cache\lazyLoading\*.tjs
del ..\..\cache\lazyLoading\*.mjs

del ..\..\cache\jquery*.js
copy jquery.Jcrop.min.js ..\..\cache\a2.tjs
FOR %%1 in (..\..\cache\*.tjs) do type %%1 >> ..\..\cache\tmp.mjs
java -jar yuicompressor-2.4.2.jar --type js ..\..\cache\tmp.mjs -o ..\..\cache\jquery.plugins.js
del ..\..\cache\*.tjs
del ..\..\cache\*.mjs