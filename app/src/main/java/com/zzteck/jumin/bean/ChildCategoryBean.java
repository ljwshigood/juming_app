package com.zzteck.jumin.bean;

import java.io.Serializable;
import java.util.List;

public class ChildCategoryBean implements Serializable {


    /**
     * ret : 200
     * data : [{"catid":"4","catname":"全职招聘","usecoin":"0","catorder":"5","if_view":"2","dir_typename":"zhaopin","children":[{"catid":"51","catname":"营业员/促销/零售","if_view":"2","color":null,"dir_typename":"lingshou","usecoin":"0"}],"icon":""},{"catid":"4","catname":"全职招聘","usecoin":"0","catorder":"5","if_view":"2","dir_typename":"zhaopin","children":[{"catid":"51","catname":"营业员/促销/零售","if_view":"2","color":null,"dir_typename":"lingshou","usecoin":"0"},{"catid":"52","catname":"服务员/收银员","if_view":"2","color":null,"dir_typename":"fuwuyuan","usecoin":"0"}],"icon":""},{"catid":"4","catname":"全职招聘","usecoin":"0","catorder":"5","if_view":"2","dir_typename":"zhaopin","children":[{"catid":"51","catname":"营业员/促销/零售","if_view":"2","color":null,"dir_typename":"lingshou","usecoin":"0"},{"catid":"52","catname":"服务员/收银员","if_view":"2","color":null,"dir_typename":"fuwuyuan","usecoin":"0"},{"catid":"53","catname":"销售/市场/业务员","if_view":"2","color":null,"dir_typename":"yewu","usecoin":"0"}],"icon":""},{"catid":"4","catname":"全职招聘","usecoin":"0","catorder":"5","if_view":"2","dir_typename":"zhaopin","children":[{"catid":"51","catname":"营业员/促销/零售","if_view":"2","color":null,"dir_typename":"lingshou","usecoin":"0"},{"catid":"52","catname":"服务员/收银员","if_view":"2","color":null,"dir_typename":"fuwuyuan","usecoin":"0"},{"catid":"53","catname":"销售/市场/业务员","if_view":"2","color":null,"dir_typename":"yewu","usecoin":"0"},{"catid":"54","catname":"文员/客服/助理","if_view":"2","color":null,"dir_typename":"wenyuan","usecoin":"0"}],"icon":""},{"catid":"4","catname":"全职招聘","usecoin":"0","catorder":"5","if_view":"2","dir_typename":"zhaopin","children":[{"catid":"51","catname":"营业员/促销/零售","if_view":"2","color":null,"dir_typename":"lingshou","usecoin":"0"},{"catid":"52","catname":"服务员/收银员","if_view":"2","color":null,"dir_typename":"fuwuyuan","usecoin":"0"},{"catid":"53","catname":"销售/市场/业务员","if_view":"2","color":null,"dir_typename":"yewu","usecoin":"0"},{"catid":"54","catname":"文员/客服/助理","if_view":"2","color":null,"dir_typename":"wenyuan","usecoin":"0"},{"catid":"55","catname":"保姆/家政","if_view":"2","color":null,"dir_typename":"baomu","usecoin":"0"}],"icon":""},{"catid":"4","catname":"全职招聘","usecoin":"0","catorder":"5","if_view":"2","dir_typename":"zhaopin","children":[{"catid":"51","catname":"营业员/促销/零售","if_view":"2","color":null,"dir_typename":"lingshou","usecoin":"0"},{"catid":"52","catname":"服务员/收银员","if_view":"2","color":null,"dir_typename":"fuwuyuan","usecoin":"0"},{"catid":"53","catname":"销售/市场/业务员","if_view":"2","color":null,"dir_typename":"yewu","usecoin":"0"},{"catid":"54","catname":"文员/客服/助理","if_view":"2","color":null,"dir_typename":"wenyuan","usecoin":"0"},{"catid":"55","catname":"保姆/家政","if_view":"2","color":null,"dir_typename":"baomu","usecoin":"0"},{"catid":"56","catname":"司机/驾驶员","if_view":"2","color":null,"dir_typename":"jiashi","usecoin":"0"}],"icon":""},{"catid":"4","catname":"全职招聘","usecoin":"0","catorder":"5","if_view":"2","dir_typename":"zhaopin","children":[{"catid":"51","catname":"营业员/促销/零售","if_view":"2","color":null,"dir_typename":"lingshou","usecoin":"0"},{"catid":"52","catname":"服务员/收银员","if_view":"2","color":null,"dir_typename":"fuwuyuan","usecoin":"0"},{"catid":"53","catname":"销售/市场/业务员","if_view":"2","color":null,"dir_typename":"yewu","usecoin":"0"},{"catid":"54","catname":"文员/客服/助理","if_view":"2","color":null,"dir_typename":"wenyuan","usecoin":"0"},{"catid":"55","catname":"保姆/家政","if_view":"2","color":null,"dir_typename":"baomu","usecoin":"0"},{"catid":"56","catname":"司机/驾驶员","if_view":"2","color":null,"dir_typename":"jiashi","usecoin":"0"},{"catid":"57","catname":"保安/保洁","if_view":"2","color":null,"dir_typename":"baoan","usecoin":"0"}],"icon":""},{"catid":"4","catname":"全职招聘","usecoin":"0","catorder":"5","if_view":"2","dir_typename":"zhaopin","children":[{"catid":"51","catname":"营业员/促销/零售","if_view":"2","color":null,"dir_typename":"lingshou","usecoin":"0"},{"catid":"52","catname":"服务员/收银员","if_view":"2","color":null,"dir_typename":"fuwuyuan","usecoin":"0"},{"catid":"53","catname":"销售/市场/业务员","if_view":"2","color":null,"dir_typename":"yewu","usecoin":"0"},{"catid":"54","catname":"文员/客服/助理","if_view":"2","color":null,"dir_typename":"wenyuan","usecoin":"0"},{"catid":"55","catname":"保姆/家政","if_view":"2","color":null,"dir_typename":"baomu","usecoin":"0"},{"catid":"56","catname":"司机/驾驶员","if_view":"2","color":null,"dir_typename":"jiashi","usecoin":"0"},{"catid":"57","catname":"保安/保洁","if_view":"2","color":null,"dir_typename":"baoan","usecoin":"0"},{"catid":"58","catname":"厨师/切配","if_view":"2","color":null,"dir_typename":"chushi","usecoin":"0"}],"icon":""},{"catid":"4","catname":"全职招聘","usecoin":"0","catorder":"5","if_view":"2","dir_typename":"zhaopin","children":[{"catid":"51","catname":"营业员/促销/零售","if_view":"2","color":null,"dir_typename":"lingshou","usecoin":"0"},{"catid":"52","catname":"服务员/收银员","if_view":"2","color":null,"dir_typename":"fuwuyuan","usecoin":"0"},{"catid":"53","catname":"销售/市场/业务员","if_view":"2","color":null,"dir_typename":"yewu","usecoin":"0"},{"catid":"54","catname":"文员/客服/助理","if_view":"2","color":null,"dir_typename":"wenyuan","usecoin":"0"},{"catid":"55","catname":"保姆/家政","if_view":"2","color":null,"dir_typename":"baomu","usecoin":"0"},{"catid":"56","catname":"司机/驾驶员","if_view":"2","color":null,"dir_typename":"jiashi","usecoin":"0"},{"catid":"57","catname":"保安/保洁","if_view":"2","color":null,"dir_typename":"baoan","usecoin":"0"},{"catid":"58","catname":"厨师/切配","if_view":"2","color":null,"dir_typename":"chushi","usecoin":"0"},{"catid":"59","catname":"送货/快递/仓管","if_view":"2","color":null,"dir_typename":"kuaidi","usecoin":"0"}],"icon":""},{"catid":"4","catname":"全职招聘","usecoin":"0","catorder":"5","if_view":"2","dir_typename":"zhaopin","children":[{"catid":"51","catname":"营业员/促销/零售","if_view":"2","color":null,"dir_typename":"lingshou","usecoin":"0"},{"catid":"52","catname":"服务员/收银员","if_view":"2","color":null,"dir_typename":"fuwuyuan","usecoin":"0"},{"catid":"53","catname":"销售/市场/业务员","if_view":"2","color":null,"dir_typename":"yewu","usecoin":"0"},{"catid":"54","catname":"文员/客服/助理","if_view":"2","color":null,"dir_typename":"wenyuan","usecoin":"0"},{"catid":"55","catname":"保姆/家政","if_view":"2","color":null,"dir_typename":"baomu","usecoin":"0"},{"catid":"56","catname":"司机/驾驶员","if_view":"2","color":null,"dir_typename":"jiashi","usecoin":"0"},{"catid":"57","catname":"保安/保洁","if_view":"2","color":null,"dir_typename":"baoan","usecoin":"0"},{"catid":"58","catname":"厨师/切配","if_view":"2","color":null,"dir_typename":"chushi","usecoin":"0"},{"catid":"59","catname":"送货/快递/仓管","if_view":"2","color":null,"dir_typename":"kuaidi","usecoin":"0"},{"catid":"60","catname":"工人/技工","if_view":"2","color":null,"dir_typename":"gongren","usecoin":"0"}],"icon":""},{"catid":"4","catname":"全职招聘","usecoin":"0","catorder":"5","if_view":"2","dir_typename":"zhaopin","children":[{"catid":"51","catname":"营业员/促销/零售","if_view":"2","color":null,"dir_typename":"lingshou","usecoin":"0"},{"catid":"52","catname":"服务员/收银员","if_view":"2","color":null,"dir_typename":"fuwuyuan","usecoin":"0"},{"catid":"53","catname":"销售/市场/业务员","if_view":"2","color":null,"dir_typename":"yewu","usecoin":"0"},{"catid":"54","catname":"文员/客服/助理","if_view":"2","color":null,"dir_typename":"wenyuan","usecoin":"0"},{"catid":"55","catname":"保姆/家政","if_view":"2","color":null,"dir_typename":"baomu","usecoin":"0"},{"catid":"56","catname":"司机/驾驶员","if_view":"2","color":null,"dir_typename":"jiashi","usecoin":"0"},{"catid":"57","catname":"保安/保洁","if_view":"2","color":null,"dir_typename":"baoan","usecoin":"0"},{"catid":"58","catname":"厨师/切配","if_view":"2","color":null,"dir_typename":"chushi","usecoin":"0"},{"catid":"59","catname":"送货/快递/仓管","if_view":"2","color":null,"dir_typename":"kuaidi","usecoin":"0"},{"catid":"60","catname":"工人/技工","if_view":"2","color":null,"dir_typename":"gongren","usecoin":"0"},{"catid":"61","catname":"财务/会计","if_view":"2","color":null,"dir_typename":"caiwu","usecoin":"0"}],"icon":""},{"catid":"4","catname":"全职招聘","usecoin":"0","catorder":"5","if_view":"2","dir_typename":"zhaopin","children":[{"catid":"51","catname":"营业员/促销/零售","if_view":"2","color":null,"dir_typename":"lingshou","usecoin":"0"},{"catid":"52","catname":"服务员/收银员","if_view":"2","color":null,"dir_typename":"fuwuyuan","usecoin":"0"},{"catid":"53","catname":"销售/市场/业务员","if_view":"2","color":null,"dir_typename":"yewu","usecoin":"0"},{"catid":"54","catname":"文员/客服/助理","if_view":"2","color":null,"dir_typename":"wenyuan","usecoin":"0"},{"catid":"55","catname":"保姆/家政","if_view":"2","color":null,"dir_typename":"baomu","usecoin":"0"},{"catid":"56","catname":"司机/驾驶员","if_view":"2","color":null,"dir_typename":"jiashi","usecoin":"0"},{"catid":"57","catname":"保安/保洁","if_view":"2","color":null,"dir_typename":"baoan","usecoin":"0"},{"catid":"58","catname":"厨师/切配","if_view":"2","color":null,"dir_typename":"chushi","usecoin":"0"},{"catid":"59","catname":"送货/快递/仓管","if_view":"2","color":null,"dir_typename":"kuaidi","usecoin":"0"},{"catid":"60","catname":"工人/技工","if_view":"2","color":null,"dir_typename":"gongren","usecoin":"0"},{"catid":"61","catname":"财务/会计","if_view":"2","color":null,"dir_typename":"caiwu","usecoin":"0"},{"catid":"62","catname":"老师/培训师","if_view":"2","color":null,"dir_typename":"laoshi","usecoin":"0"}],"icon":""},{"catid":"4","catname":"全职招聘","usecoin":"0","catorder":"5","if_view":"2","dir_typename":"zhaopin","children":[{"catid":"51","catname":"营业员/促销/零售","if_view":"2","color":null,"dir_typename":"lingshou","usecoin":"0"},{"catid":"52","catname":"服务员/收银员","if_view":"2","color":null,"dir_typename":"fuwuyuan","usecoin":"0"},{"catid":"53","catname":"销售/市场/业务员","if_view":"2","color":null,"dir_typename":"yewu","usecoin":"0"},{"catid":"54","catname":"文员/客服/助理","if_view":"2","color":null,"dir_typename":"wenyuan","usecoin":"0"},{"catid":"55","catname":"保姆/家政","if_view":"2","color":null,"dir_typename":"baomu","usecoin":"0"},{"catid":"56","catname":"司机/驾驶员","if_view":"2","color":null,"dir_typename":"jiashi","usecoin":"0"},{"catid":"57","catname":"保安/保洁","if_view":"2","color":null,"dir_typename":"baoan","usecoin":"0"},{"catid":"58","catname":"厨师/切配","if_view":"2","color":null,"dir_typename":"chushi","usecoin":"0"},{"catid":"59","catname":"送货/快递/仓管","if_view":"2","color":null,"dir_typename":"kuaidi","usecoin":"0"},{"catid":"60","catname":"工人/技工","if_view":"2","color":null,"dir_typename":"gongren","usecoin":"0"},{"catid":"61","catname":"财务/会计","if_view":"2","color":null,"dir_typename":"caiwu","usecoin":"0"},{"catid":"62","catname":"老师/培训师","if_view":"2","color":null,"dir_typename":"laoshi","usecoin":"0"},{"catid":"63","catname":"美工/设计/程序员","if_view":"2","color":null,"dir_typename":"meigong","usecoin":"0"}],"icon":""},{"catid":"4","catname":"全职招聘","usecoin":"0","catorder":"5","if_view":"2","dir_typename":"zhaopin","children":[{"catid":"51","catname":"营业员/促销/零售","if_view":"2","color":null,"dir_typename":"lingshou","usecoin":"0"},{"catid":"52","catname":"服务员/收银员","if_view":"2","color":null,"dir_typename":"fuwuyuan","usecoin":"0"},{"catid":"53","catname":"销售/市场/业务员","if_view":"2","color":null,"dir_typename":"yewu","usecoin":"0"},{"catid":"54","catname":"文员/客服/助理","if_view":"2","color":null,"dir_typename":"wenyuan","usecoin":"0"},{"catid":"55","catname":"保姆/家政","if_view":"2","color":null,"dir_typename":"baomu","usecoin":"0"},{"catid":"56","catname":"司机/驾驶员","if_view":"2","color":null,"dir_typename":"jiashi","usecoin":"0"},{"catid":"57","catname":"保安/保洁","if_view":"2","color":null,"dir_typename":"baoan","usecoin":"0"},{"catid":"58","catname":"厨师/切配","if_view":"2","color":null,"dir_typename":"chushi","usecoin":"0"},{"catid":"59","catname":"送货/快递/仓管","if_view":"2","color":null,"dir_typename":"kuaidi","usecoin":"0"},{"catid":"60","catname":"工人/技工","if_view":"2","color":null,"dir_typename":"gongren","usecoin":"0"},{"catid":"61","catname":"财务/会计","if_view":"2","color":null,"dir_typename":"caiwu","usecoin":"0"},{"catid":"62","catname":"老师/培训师","if_view":"2","color":null,"dir_typename":"laoshi","usecoin":"0"},{"catid":"63","catname":"美工/设计/程序员","if_view":"2","color":null,"dir_typename":"meigong","usecoin":"0"},{"catid":"64","catname":"保健师/美容师","if_view":"2","color":null,"dir_typename":"baojianshi","usecoin":"0"}],"icon":""},{"catid":"4","catname":"全职招聘","usecoin":"0","catorder":"5","if_view":"2","dir_typename":"zhaopin","children":[{"catid":"51","catname":"营业员/促销/零售","if_view":"2","color":null,"dir_typename":"lingshou","usecoin":"0"},{"catid":"52","catname":"服务员/收银员","if_view":"2","color":null,"dir_typename":"fuwuyuan","usecoin":"0"},{"catid":"53","catname":"销售/市场/业务员","if_view":"2","color":null,"dir_typename":"yewu","usecoin":"0"},{"catid":"54","catname":"文员/客服/助理","if_view":"2","color":null,"dir_typename":"wenyuan","usecoin":"0"},{"catid":"55","catname":"保姆/家政","if_view":"2","color":null,"dir_typename":"baomu","usecoin":"0"},{"catid":"56","catname":"司机/驾驶员","if_view":"2","color":null,"dir_typename":"jiashi","usecoin":"0"},{"catid":"57","catname":"保安/保洁","if_view":"2","color":null,"dir_typename":"baoan","usecoin":"0"},{"catid":"58","catname":"厨师/切配","if_view":"2","color":null,"dir_typename":"chushi","usecoin":"0"},{"catid":"59","catname":"送货/快递/仓管","if_view":"2","color":null,"dir_typename":"kuaidi","usecoin":"0"},{"catid":"60","catname":"工人/技工","if_view":"2","color":null,"dir_typename":"gongren","usecoin":"0"},{"catid":"61","catname":"财务/会计","if_view":"2","color":null,"dir_typename":"caiwu","usecoin":"0"},{"catid":"62","catname":"老师/培训师","if_view":"2","color":null,"dir_typename":"laoshi","usecoin":"0"},{"catid":"63","catname":"美工/设计/程序员","if_view":"2","color":null,"dir_typename":"meigong","usecoin":"0"},{"catid":"64","catname":"保健师/美容师","if_view":"2","color":null,"dir_typename":"baojianshi","usecoin":"0"},{"catid":"65","catname":"人才招聘会","if_view":"2","color":null,"dir_typename":"zhaopinhui","usecoin":"0"}],"icon":""},{"catid":"4","catname":"全职招聘","usecoin":"0","catorder":"5","if_view":"2","dir_typename":"zhaopin","children":[{"catid":"51","catname":"营业员/促销/零售","if_view":"2","color":null,"dir_typename":"lingshou","usecoin":"0"},{"catid":"52","catname":"服务员/收银员","if_view":"2","color":null,"dir_typename":"fuwuyuan","usecoin":"0"},{"catid":"53","catname":"销售/市场/业务员","if_view":"2","color":null,"dir_typename":"yewu","usecoin":"0"},{"catid":"54","catname":"文员/客服/助理","if_view":"2","color":null,"dir_typename":"wenyuan","usecoin":"0"},{"catid":"55","catname":"保姆/家政","if_view":"2","color":null,"dir_typename":"baomu","usecoin":"0"},{"catid":"56","catname":"司机/驾驶员","if_view":"2","color":null,"dir_typename":"jiashi","usecoin":"0"},{"catid":"57","catname":"保安/保洁","if_view":"2","color":null,"dir_typename":"baoan","usecoin":"0"},{"catid":"58","catname":"厨师/切配","if_view":"2","color":null,"dir_typename":"chushi","usecoin":"0"},{"catid":"59","catname":"送货/快递/仓管","if_view":"2","color":null,"dir_typename":"kuaidi","usecoin":"0"},{"catid":"60","catname":"工人/技工","if_view":"2","color":null,"dir_typename":"gongren","usecoin":"0"},{"catid":"61","catname":"财务/会计","if_view":"2","color":null,"dir_typename":"caiwu","usecoin":"0"},{"catid":"62","catname":"老师/培训师","if_view":"2","color":null,"dir_typename":"laoshi","usecoin":"0"},{"catid":"63","catname":"美工/设计/程序员","if_view":"2","color":null,"dir_typename":"meigong","usecoin":"0"},{"catid":"64","catname":"保健师/美容师","if_view":"2","color":null,"dir_typename":"baojianshi","usecoin":"0"},{"catid":"65","catname":"人才招聘会","if_view":"2","color":null,"dir_typename":"zhaopinhui","usecoin":"0"},{"catid":"66","catname":"KTV/酒吧","if_view":"2","color":null,"dir_typename":"ktv","usecoin":"0"}],"icon":""},{"catid":"4","catname":"全职招聘","usecoin":"0","catorder":"5","if_view":"2","dir_typename":"zhaopin","children":[{"catid":"51","catname":"营业员/促销/零售","if_view":"2","color":null,"dir_typename":"lingshou","usecoin":"0"},{"catid":"52","catname":"服务员/收银员","if_view":"2","color":null,"dir_typename":"fuwuyuan","usecoin":"0"},{"catid":"53","catname":"销售/市场/业务员","if_view":"2","color":null,"dir_typename":"yewu","usecoin":"0"},{"catid":"54","catname":"文员/客服/助理","if_view":"2","color":null,"dir_typename":"wenyuan","usecoin":"0"},{"catid":"55","catname":"保姆/家政","if_view":"2","color":null,"dir_typename":"baomu","usecoin":"0"},{"catid":"56","catname":"司机/驾驶员","if_view":"2","color":null,"dir_typename":"jiashi","usecoin":"0"},{"catid":"57","catname":"保安/保洁","if_view":"2","color":null,"dir_typename":"baoan","usecoin":"0"},{"catid":"58","catname":"厨师/切配","if_view":"2","color":null,"dir_typename":"chushi","usecoin":"0"},{"catid":"59","catname":"送货/快递/仓管","if_view":"2","color":null,"dir_typename":"kuaidi","usecoin":"0"},{"catid":"60","catname":"工人/技工","if_view":"2","color":null,"dir_typename":"gongren","usecoin":"0"},{"catid":"61","catname":"财务/会计","if_view":"2","color":null,"dir_typename":"caiwu","usecoin":"0"},{"catid":"62","catname":"老师/培训师","if_view":"2","color":null,"dir_typename":"laoshi","usecoin":"0"},{"catid":"63","catname":"美工/设计/程序员","if_view":"2","color":null,"dir_typename":"meigong","usecoin":"0"},{"catid":"64","catname":"保健师/美容师","if_view":"2","color":null,"dir_typename":"baojianshi","usecoin":"0"},{"catid":"65","catname":"人才招聘会","if_view":"2","color":null,"dir_typename":"zhaopinhui","usecoin":"0"},{"catid":"66","catname":"KTV/酒吧","if_view":"2","color":null,"dir_typename":"ktv","usecoin":"0"},{"catid":"67","catname":"其他招聘","if_view":"2","color":null,"dir_typename":"qitazhaopin","usecoin":"0"}],"icon":""}]
     * msg :
     * debug : {"stack":["[#0 - 0ms - PHALAPI_INIT]/datas/wwwroot/api/public/index.php(8)","[#1 - 0.3ms - PHALAPI_RESPONSE]/datas/wwwroot/api/vendor/phalapi/kernal/src/PhalApi.php(46)","[#2 - 5.7ms - PHALAPI_FINISH]/datas/wwwroot/api/vendor/phalapi/kernal/src/PhalApi.php(74)"],"sqls":["[1 - 0.28ms]SELECT parentid FROM `my_category` WHERE catid = '4';","[2 - 0.2ms]SELECT count(catid) FROM `my_category` WHERE parentid = '4';","[3 - 0.35ms]SELECT a.*, b.catid AS childid, b.catname AS childname, b.catorder AS childorder,b.dir_typename AS child_dir_typename,b.htmlpath AS child_htmlpath,b.dir_typename AS child_dir_typename FROM `my_category` AS a LEFT JOIN `my_category` AS b ON b.parentid = a.catid  AND b.if_view = '2' WHERE a.catid = '4' ORDER BY catorder ASC , childorder ASC;"],"version":"2.5.0"}
     */

    private int ret;
    private String msg;
    private DebugBean debug;
    private List<DataBean> data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DebugBean getDebug() {
        return debug;
    }

    public void setDebug(DebugBean debug) {
        this.debug = debug;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DebugBean {
        /**
         * stack : ["[#0 - 0ms - PHALAPI_INIT]/datas/wwwroot/api/public/index.php(8)","[#1 - 0.3ms - PHALAPI_RESPONSE]/datas/wwwroot/api/vendor/phalapi/kernal/src/PhalApi.php(46)","[#2 - 5.7ms - PHALAPI_FINISH]/datas/wwwroot/api/vendor/phalapi/kernal/src/PhalApi.php(74)"]
         * sqls : ["[1 - 0.28ms]SELECT parentid FROM `my_category` WHERE catid = '4';","[2 - 0.2ms]SELECT count(catid) FROM `my_category` WHERE parentid = '4';","[3 - 0.35ms]SELECT a.*, b.catid AS childid, b.catname AS childname, b.catorder AS childorder,b.dir_typename AS child_dir_typename,b.htmlpath AS child_htmlpath,b.dir_typename AS child_dir_typename FROM `my_category` AS a LEFT JOIN `my_category` AS b ON b.parentid = a.catid  AND b.if_view = '2' WHERE a.catid = '4' ORDER BY catorder ASC , childorder ASC;"]
         * version : 2.5.0
         */

        private String version;
        private List<String> stack;
        private List<String> sqls;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public List<String> getStack() {
            return stack;
        }

        public void setStack(List<String> stack) {
            this.stack = stack;
        }

        public List<String> getSqls() {
            return sqls;
        }

        public void setSqls(List<String> sqls) {
            this.sqls = sqls;
        }
    }

    public static class DataBean {
        /**
         * catid : 4
         * catname : 全职招聘
         * usecoin : 0
         * catorder : 5
         * if_view : 2
         * dir_typename : zhaopin
         * children : [{"catid":"51","catname":"营业员/促销/零售","if_view":"2","color":null,"dir_typename":"lingshou","usecoin":"0"}]
         * icon :
         */

        private String catid;
        private String catname;
        private String usecoin;
        private String catorder;
        private String if_view;
        private String dir_typename;
        private String icon;
        private List<ChildrenBean> children;

        public String getCatid() {
            return catid;
        }

        public void setCatid(String catid) {
            this.catid = catid;
        }

        public String getCatname() {
            return catname;
        }

        public void setCatname(String catname) {
            this.catname = catname;
        }

        public String getUsecoin() {
            return usecoin;
        }

        public void setUsecoin(String usecoin) {
            this.usecoin = usecoin;
        }

        public String getCatorder() {
            return catorder;
        }

        public void setCatorder(String catorder) {
            this.catorder = catorder;
        }

        public String getIf_view() {
            return if_view;
        }

        public void setIf_view(String if_view) {
            this.if_view = if_view;
        }

        public String getDir_typename() {
            return dir_typename;
        }

        public void setDir_typename(String dir_typename) {
            this.dir_typename = dir_typename;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public List<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }

        public static class ChildrenBean {
            /**
             * catid : 51
             * catname : 营业员/促销/零售
             * if_view : 2
             * color : null
             * dir_typename : lingshou
             * usecoin : 0
             */

            private String catid;
            private String catname;
            private String if_view;
            private Object color;
            private String dir_typename;
            private String usecoin;

            public String getCatid() {
                return catid;
            }

            public void setCatid(String catid) {
                this.catid = catid;
            }

            public String getCatname() {
                return catname;
            }

            public void setCatname(String catname) {
                this.catname = catname;
            }

            public String getIf_view() {
                return if_view;
            }

            public void setIf_view(String if_view) {
                this.if_view = if_view;
            }

            public Object getColor() {
                return color;
            }

            public void setColor(Object color) {
                this.color = color;
            }

            public String getDir_typename() {
                return dir_typename;
            }

            public void setDir_typename(String dir_typename) {
                this.dir_typename = dir_typename;
            }

            public String getUsecoin() {
                return usecoin;
            }

            public void setUsecoin(String usecoin) {
                this.usecoin = usecoin;
            }
        }
    }
}
