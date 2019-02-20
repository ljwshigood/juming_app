
      !(function(){
        var uniAppViewReadyCallback = function(){
          setCssToHead([".",[1],"swiper { width: 100%; }\n.",[1],"swiper wx-image { width: 100%; display: block; }\n",],undefined,{path:"./pages/news/news.wxss"})();
document.dispatchEvent(new CustomEvent("generateFuncReady", { detail: { generateFunc: $gwx('./pages/news/news.wxml') } }));
        }
        if(window.__uniAppViewReady__){
          uniAppViewReadyCallback()
        }else{
          document.addEventListener('uniAppViewReady',uniAppViewReadyCallback)
        }
      })();
      