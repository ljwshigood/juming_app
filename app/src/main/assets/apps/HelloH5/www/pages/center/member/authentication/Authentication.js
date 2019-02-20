
      !(function(){
        var uniAppViewReadyCallback = function(){
          setCssToHead([".",[1],"auth-title { border-bottom: ",[0,1]," solid #e2e2e2; padding-bottom: ",[0,30],"; margin-bottom: ",[0,40],"; }\n.",[1],"auth-content wx-image { width: ",[0,250],"; height: ",[0,200],"; }\n",],undefined,{path:"./pages/center/member/authentication/Authentication.wxss"})();
document.dispatchEvent(new CustomEvent("generateFuncReady", { detail: { generateFunc: $gwx('./pages/center/member/authentication/Authentication.wxml') } }));
        }
        if(window.__uniAppViewReady__){
          uniAppViewReadyCallback()
        }else{
          document.addEventListener('uniAppViewReady',uniAppViewReadyCallback)
        }
      })();
      