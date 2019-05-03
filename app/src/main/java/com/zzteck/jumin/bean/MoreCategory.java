package com.zzteck.jumin.bean;


import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class MoreCategory implements Serializable{


    /**
     * ret : 200
     * data : [{"catid":"1","catname":"二手转让","color":"","if_view":"2","dir_typename":"ershou","usecoin":"0","icon":"http://www.jumin.cn/template/default/images/index/icon_ershou.gif","children":[{"catid":"11","catname":"二手手机","if_view":"2","color":"#ff0000","dir_typename":"shouji","usecoin":"0"},{"catid":"13","catname":"台式电脑","if_view":"2","color":"","dir_typename":"diannao","usecoin":"0"},{"catid":"246","catname":"笔记本","if_view":"2","color":"","dir_typename":"bijiben","usecoin":"0"},{"catid":"12","catname":"平板电脑","if_view":"2","color":"","dir_typename":"pingban","usecoin":"0"},{"catid":"14","catname":"数码产品","if_view":"2","color":"#46a200","dir_typename":"shuma","usecoin":"0"},{"catid":"21","catname":"母婴用品","if_view":"2","color":"","dir_typename":"yingyou","usecoin":"0"},{"catid":"16","catname":"家用电器","if_view":"2","color":"#ff9900","dir_typename":"jiadian","usecoin":"0"},{"catid":"15","catname":"二手家具","if_view":"2","color":"","dir_typename":"jiaju","usecoin":"0"},{"catid":"20","catname":"服饰箱包","if_view":"2","color":"","dir_typename":"fushi","usecoin":"0"},{"catid":"23","catname":"门票卡券","if_view":"2","color":"","dir_typename":"piao","usecoin":"0"},{"catid":"247","catname":"照相机","if_view":"2","color":"","dir_typename":"zhaoxiangji","usecoin":"0"},{"catid":"296","catname":"手机配件","if_view":"2","color":"","dir_typename":"shoujipeijian","usecoin":"0"},{"catid":"17","catname":"家居日用","if_view":"2","color":"","dir_typename":"riyongpin","usecoin":"0"},{"catid":"22","catname":"乐器/运动","if_view":"2","color":"","dir_typename":"wenti","usecoin":"0"},{"catid":"248","catname":"食品农产","if_view":"2","color":"","dir_typename":"shipinnongchan","usecoin":"0"},{"catid":"297","catname":"图书音像","if_view":"2","color":"","dir_typename":"tushuyinying","usecoin":"0"},{"catid":"18","catname":"办公用品","if_view":"2","color":"","dir_typename":"bangong","usecoin":"0"},{"catid":"24","catname":"办公家具","if_view":"2","color":"","dir_typename":"gongyeshebei","usecoin":"0"},{"catid":"19","catname":"工艺/收藏","if_view":"2","color":"","dir_typename":"shoucang","usecoin":"0"},{"catid":"25","catname":"奇石盆景","if_view":"2","color":"","dir_typename":"penjing","usecoin":"0"},{"catid":"26","catname":"闲置真心送","if_view":"2","color":"","dir_typename":"zulin","usecoin":"0"},{"catid":"249","catname":"网游/虚拟物品","if_view":"2","color":"","dir_typename":"wangyou-xuniwupin","usecoin":"0"},{"catid":"27","catname":"其他物品","if_view":"2","color":"","dir_typename":"qitaershou","usecoin":"0"}]},{"catid":"2","catname":"车辆买卖","color":"","if_view":"2","dir_typename":"che","usecoin":"0","icon":"http://www.jumin.cn/template/default/images/index/icon_che.gif","children":[{"catid":"28","catname":"二手轿车","if_view":"2","color":"","dir_typename":"jiaoche","usecoin":"0"},{"catid":"267","catname":"货车","if_view":"2","color":"","dir_typename":"huoche","usecoin":"0"},{"catid":"29","catname":"工程车","if_view":"2","color":"","dir_typename":"gongchengche","usecoin":"0"},{"catid":"30","catname":"面包车/客车","if_view":"2","color":"","dir_typename":"keche","usecoin":"0"},{"catid":"31","catname":"拖拉机/收割机","if_view":"2","color":"","dir_typename":"tuolaji","usecoin":"0"},{"catid":"37","catname":"二手摩托车","if_view":"2","color":"","dir_typename":"motuoche","usecoin":"0"},{"catid":"38","catname":"二手电动车","if_view":"2","color":"","dir_typename":"diandongche","usecoin":"0"},{"catid":"39","catname":"二手自行车","if_view":"2","color":"","dir_typename":"zixingche","usecoin":"0"},{"catid":"274","catname":"贷款购车","if_view":"2","color":"","dir_typename":"daikuangouche","usecoin":"0"},{"catid":"33","catname":"新车优惠","if_view":"2","color":"","dir_typename":"xincheyouhui","usecoin":"0"},{"catid":"40","catname":"本地下线车","if_view":"2","color":"","dir_typename":"xiaxianche","usecoin":"0"},{"catid":"273","catname":"汽车用品","if_view":"2","color":"","dir_typename":"qicheyongpin","usecoin":"0"},{"catid":"32","catname":"拼车/顺风车","if_view":"2","color":"","dir_typename":"pinche","usecoin":"0"},{"catid":"35","catname":"汽修保养","if_view":"2","color":"","dir_typename":"qixiu","usecoin":"0"},{"catid":"275","catname":"过户/验车","if_view":"2","color":"","dir_typename":"guohuyanche","usecoin":"0"},{"catid":"36","catname":"高价收车","if_view":"2","color":"","dir_typename":"cheliangsg","usecoin":"0"},{"catid":"276","catname":"抵押车/不过户车辆","if_view":"2","color":"","dir_typename":"shiguchejiqita","usecoin":"0"},{"catid":"277","catname":"其他车辆","if_view":"2","color":"","dir_typename":"qitacheliang","usecoin":"0"},{"catid":"34","catname":"汽车配件","if_view":"2","color":"","dir_typename":"peijian","usecoin":"0"}]},{"catid":"3","catname":"房屋租售","color":"","if_view":"2","dir_typename":"fang","usecoin":"0","icon":"http://www.jumin.cn/template/default/images/index/icon_fang.gif","children":[{"catid":"41","catname":" 租房/出租","if_view":"2","color":"","dir_typename":"chuzu","usecoin":"0"},{"catid":"42","catname":"日租/短租","if_view":"2","color":"","dir_typename":"duanzu","usecoin":"0"},{"catid":"43","catname":"二手房出售","if_view":"2","color":"","dir_typename":"ershoufang","usecoin":"0"},{"catid":"45","catname":"商铺出租","if_view":"2","color":"","dir_typename":"shangpucz","usecoin":"0"},{"catid":"251","catname":"商铺转让","if_view":"2","color":"","dir_typename":"shangpuzhuanrang","usecoin":"0"},{"catid":"46","catname":"商铺出售","if_view":"2","color":"","dir_typename":"shangpu","usecoin":"0"},{"catid":"47","catname":"写字楼出租","if_view":"2","color":"","dir_typename":"xieziloucz","usecoin":"0"},{"catid":"48","catname":"写字楼出售","if_view":"2","color":"","dir_typename":"xieziloucs","usecoin":"0"},{"catid":"49","catname":"厂房/仓库/土地","if_view":"2","color":"","dir_typename":"changfang","usecoin":"0"},{"catid":"50","catname":"求租/求购","if_view":"2","color":"","dir_typename":"qiufang","usecoin":"0"},{"catid":"298","catname":"新房出售","if_view":"2","color":"","dir_typename":"xinfangchushou","usecoin":"0"},{"catid":"299","catname":"找室友","if_view":"2","color":"","dir_typename":"zhaoshiyou","usecoin":"0"},{"catid":"300","catname":"其他房屋","if_view":"2","color":"","dir_typename":"qitafangwu","usecoin":"0"}]},{"catid":"4","catname":"全职招聘","color":"","if_view":"2","dir_typename":"zhaopin","usecoin":"0","icon":"http://www.jumin.cn/template/default/images/index/icon_zhaopin.gif","children":[{"catid":"60","catname":"工人/技工","if_view":"2","color":"","dir_typename":"jigongzp","usecoin":"0"},{"catid":"56","catname":"司机","if_view":"2","color":"","dir_typename":"sijizp","usecoin":"0"},{"catid":"58","catname":"餐饮","if_view":"2","color":"","dir_typename":"jiudiancanyinzp","usecoin":"0"},{"catid":"57","catname":"物业管理/保安","if_view":"2","color":"","dir_typename":"baoanrenyuanzp","usecoin":"0"},{"catid":"53","catname":"销售","if_view":"2","color":"#ff0000","dir_typename":"xiaoshourenyuan","usecoin":"0"},{"catid":"268","catname":"房产中介","if_view":"2","color":"","dir_typename":"fangchanzhongjiezp","usecoin":"0"},{"catid":"269","catname":"人事","if_view":"2","color":"","dir_typename":"renshizhuanyuanzp","usecoin":"0"},{"catid":"270","catname":"行政/后勤","if_view":"2","color":"","dir_typename":"xingzhenghouqin","usecoin":"0"},{"catid":"271","catname":"酒店","if_view":"2","color":"","dir_typename":"jiudianzp","usecoin":"0"},{"catid":"59","catname":"运输/物流/仓管","if_view":"2","color":"","dir_typename":"kuaidisonghuoyuan","usecoin":"0"},{"catid":"51","catname":"超市/百货/零售","if_view":"2","color":"","dir_typename":"shenghuochaoshizp","usecoin":"0"},{"catid":"54","catname":"客服","if_view":"2","color":"","dir_typename":"kefuzp","usecoin":"0"},{"catid":"272","catname":"医生/护士","if_view":"2","color":"","dir_typename":"yiyuanzp","usecoin":"0"},{"catid":"63","catname":"美工/设计","if_view":"2","color":"","dir_typename":"meigongzp","usecoin":"0"},{"catid":"52","catname":"翻译","if_view":"2","color":"","dir_typename":"fanyizp","usecoin":"0"},{"catid":"55","catname":"家政/保洁","if_view":"2","color":"","dir_typename":"jiazhengbaojie","usecoin":"0"},{"catid":"61","catname":"财务/会计","if_view":"2","color":"","dir_typename":"caiwuzp","usecoin":"0"},{"catid":"62","catname":"教育培训","if_view":"2","color":"","dir_typename":"laoshizp","usecoin":"0"},{"catid":"279","catname":"机械/仪器仪表","if_view":"2","color":"","dir_typename":"jixieshejigongchengshi","usecoin":"0"},{"catid":"280","catname":"汽车美容/维修","if_view":"2","color":"","dir_typename":"qichemeirong","usecoin":"0"},{"catid":"281","catname":"贸易/采购","if_view":"2","color":"","dir_typename":"caigou","usecoin":"0"},{"catid":"282","catname":"金融/银行/证券","if_view":"2","color":"","dir_typename":"jinrong","usecoin":"0"},{"catid":"283","catname":"保险","if_view":"2","color":"","dir_typename":"baoxianzp","usecoin":"0"},{"catid":"284","catname":"计算机/网络/通讯","if_view":"2","color":"","dir_typename":"chengxuyuanzp","usecoin":"0"},{"catid":"64","catname":"美容/美发","if_view":"2","color":"","dir_typename":"baojianshi","usecoin":"0"},{"catid":"285","catname":"市场/公关/媒介","if_view":"2","color":"","dir_typename":"shichang-gongguan-meijie","usecoin":"0"},{"catid":"287","catname":"旅游","if_view":"2","color":"","dir_typename":"lvyouzp","usecoin":"0"},{"catid":"286","catname":"淘宝招聘","if_view":"2","color":"","dir_typename":"taobaozhaopin","usecoin":"0"},{"catid":"288","catname":"运动健身","if_view":"2","color":"","dir_typename":"yundongjianshen","usecoin":"0"},{"catid":"289","catname":"法律","if_view":"2","color":"","dir_typename":"falvzp","usecoin":"0"},{"catid":"290","catname":"影视娱乐","if_view":"2","color":"","dir_typename":"yingshiyule","usecoin":"0"},{"catid":"291","catname":"电子/电气","if_view":"2","color":"","dir_typename":"dianzidianqi","usecoin":"0"},{"catid":"292","catname":"农/林/牧/渔业","if_view":"2","color":"","dir_typename":"nong-lin-mu-yuye","usecoin":"0"},{"catid":"65","catname":"人才招聘会","if_view":"2","color":"","dir_typename":"zhaopinhui","usecoin":"0"},{"catid":"293","catname":"出国劳务","if_view":"2","color":"","dir_typename":"chuguolaowu","usecoin":"0"},{"catid":"294","catname":"网络直播","if_view":"2","color":"","dir_typename":"wangluozhibo","usecoin":"0"},{"catid":"295","catname":"生产管理","if_view":"2","color":"","dir_typename":"shengchanguanli","usecoin":"0"},{"catid":"66","catname":"KTV/酒吧","if_view":"2","color":"","dir_typename":"ktv","usecoin":"0"},{"catid":"67","catname":"其他招聘","if_view":"2","color":"","dir_typename":"qitazhaopin","usecoin":"0"}]},{"catid":"5","catname":"兼职招聘","color":"","if_view":"2","dir_typename":"jianzhi","usecoin":"0","icon":"http://www.jumin.cn/template/default/images/index/icon_jzzhaopin.gif","children":[{"catid":"68","catname":"派发/促销","if_view":"2","color":"","dir_typename":"jzcuxiao","usecoin":"0"},{"catid":"69","catname":"家教/老师","if_view":"2","color":"","dir_typename":"jzjiajiao","usecoin":"0"},{"catid":"70","catname":"餐厅/服务员","if_view":"2","color":"","dir_typename":"jzfuwuyuan","usecoin":"0"},{"catid":"71","catname":"模特/礼仪","if_view":"2","color":"","dir_typename":"jzmote","usecoin":"0"},{"catid":"72","catname":"网站/设计","if_view":"2","color":"","dir_typename":"jzwangzhan","usecoin":"0"},{"catid":"73","catname":"会计/财务","if_view":"2","color":"","dir_typename":"jzcaiwu","usecoin":"0"},{"catid":"74","catname":"其他兼职","if_view":"2","color":"","dir_typename":"qitajianzhi","usecoin":"0"}]},{"catid":"6","catname":"求职简历","color":"","if_view":"2","dir_typename":"qiuzhi","usecoin":"1","icon":"http://www.jumin.cn/template/default/images/index/icon_jianli.gif","children":[{"catid":"75","catname":"销售","if_view":"2","color":"","dir_typename":"qzxiaoshou","usecoin":"1"},{"catid":"76","catname":"客服","if_view":"2","color":"","dir_typename":"qzkefu","usecoin":"1"},{"catid":"77","catname":"人事/行政/后勤","if_view":"2","color":"","dir_typename":"qzhouqin","usecoin":"1"},{"catid":"78","catname":"酒店/餐饮/旅游","if_view":"2","color":"","dir_typename":"qzcanyin","usecoin":"1"},{"catid":"79","catname":"美容/美发/保健/健身","if_view":"2","color":"","dir_typename":"qzmeirong","usecoin":"1"},{"catid":"80","catname":"计算机/网络/通信","if_view":"2","color":"","dir_typename":"qzcomputer","usecoin":"1"},{"catid":"81","catname":"建筑/房产/装修/物业","if_view":"2","color":"","dir_typename":"qzfangdichan","usecoin":"1"},{"catid":"82","catname":"普工/技工/生产","if_view":"2","color":"","dir_typename":"qzjigong","usecoin":"1"},{"catid":"83","catname":"司机","if_view":"2","color":"","dir_typename":"qzsiji","usecoin":"1"},{"catid":"84","catname":"家政保洁/安保","if_view":"2","color":"","dir_typename":"qzjiazheng","usecoin":"1"},{"catid":"85","catname":"影视/娱乐/KTV","if_view":"2","color":"","dir_typename":"qzktv","usecoin":"1"},{"catid":"86","catname":"编辑/出版/印刷","if_view":"2","color":"","dir_typename":"qzyinshua","usecoin":"1"},{"catid":"87","catname":"教育培训/翻译","if_view":"2","color":"","dir_typename":"qzjiaoyu","usecoin":"1"},{"catid":"88","catname":"财务/审计/统计","if_view":"2","color":"","dir_typename":"qzshenji","usecoin":"1"},{"catid":"89","catname":"其他职位","if_view":"2","color":"","dir_typename":"qitazhiwei","usecoin":"1"}]},{"catid":"7","catname":"交友活动","color":"","if_view":"2","dir_typename":"jiaoyou","usecoin":"0","icon":"http://www.jumin.cn/template/default/images/index/icon_love.gif","children":[{"catid":"90","catname":"婚介服务","if_view":"2","color":"","dir_typename":"hunjie","usecoin":"0"},{"catid":"91","catname":"同城征婚","if_view":"2","color":"","dir_typename":"zhenghun","usecoin":"0"},{"catid":"92","catname":"同城活动","if_view":"2","color":"","dir_typename":"juhui","usecoin":"0"},{"catid":"93","catname":"技能交换","if_view":"2","color":"","dir_typename":"jineng","usecoin":"0"},{"catid":"94","catname":"找人/寻物","if_view":"2","color":"","dir_typename":"zhaoren","usecoin":"0"},{"catid":"301","catname":"同城交友","if_view":"2","color":"","dir_typename":"jiaoyouyuehui","usecoin":"0"},{"catid":"302","catname":"交友群","if_view":"2","color":"","dir_typename":"jiaoyouqun","usecoin":"0"}]},{"catid":"8","catname":"宠物","color":"","if_view":"2","dir_typename":"chongwu","usecoin":"0","icon":"http://www.jumin.cn/template/default/images/index/icon_pet.gif","children":[{"catid":"95","catname":"狗狗","if_view":"2","color":"","dir_typename":"dog","usecoin":"0"},{"catid":"96","catname":"猫猫","if_view":"2","color":"","dir_typename":"chongwumao","usecoin":"0"},{"catid":"97","catname":"宠物免费赠送","if_view":"2","color":"","dir_typename":"chongwulingyang","usecoin":"0"},{"catid":"98","catname":"宠物用品/食品","if_view":"2","color":"","dir_typename":"chongwuyongpin","usecoin":"0"},{"catid":"99","catname":"宠物服务/配种","if_view":"2","color":"","dir_typename":"chongwupeizhong","usecoin":"0"},{"catid":"303","catname":"花鸟虫鱼","if_view":"2","color":"","dir_typename":"huaniaoshichang","usecoin":"0"},{"catid":"304","catname":"寻宠启事","if_view":"2","color":"","dir_typename":"xunchongqishi","usecoin":"0"}]},{"catid":"9","catname":"生活服务","color":"","if_view":"2","dir_typename":"shenghuo","usecoin":"0","icon":"http://www.jumin.cn/template/default/images/index/icon_life.gif","children":[{"catid":"100","catname":"驾校服务","if_view":"2","color":"","dir_typename":"jiaxiao","usecoin":"0"},{"catid":"101","catname":"陪驾/代驾","if_view":"2","color":"","dir_typename":"daijia","usecoin":"0"},{"catid":"102","catname":"婚庆/化妆","if_view":"2","color":"","dir_typename":"hunqing","usecoin":"0"},{"catid":"103","catname":"摄影摄像","if_view":"2","color":"","dir_typename":"sheying","usecoin":"0"},{"catid":"104","catname":"美容纤体","if_view":"2","color":"","dir_typename":"meirong","usecoin":"0"},{"catid":"105","catname":"房屋装修","if_view":"2","color":"","dir_typename":"zhuangxiu","usecoin":"0"},{"catid":"106","catname":"建材装饰","if_view":"2","color":"","dir_typename":"jiancai","usecoin":"0"},{"catid":"108","catname":"保姆/月嫂","if_view":"2","color":"","dir_typename":"yuesao","usecoin":"0"},{"catid":"109","catname":"保洁/清洗","if_view":"2","color":"","dir_typename":"baojie","usecoin":"0"},{"catid":"110","catname":"搬家服务","if_view":"2","color":"","dir_typename":"banjia","usecoin":"0"},{"catid":"111","catname":"家电维修","if_view":"2","color":"","dir_typename":"xiujiandian","usecoin":"0"},{"catid":"112","catname":"电脑维修","if_view":"2","color":"","dir_typename":"xiudiannao","usecoin":"0"},{"catid":"113","catname":"家居维修","if_view":"2","color":"","dir_typename":"jiajuweixiu","usecoin":"0"},{"catid":"114","catname":"管道疏通","if_view":"2","color":"","dir_typename":"tongguandao","usecoin":"0"},{"catid":"115","catname":"外卖/送水","if_view":"2","color":"","dir_typename":"waimai","usecoin":"0"},{"catid":"116","catname":"开锁/修锁","if_view":"2","color":"","dir_typename":"kaisuo","usecoin":"0"},{"catid":"117","catname":"租车服务","if_view":"2","color":"","dir_typename":"zuche","usecoin":"0"},{"catid":"118","catname":"旅游度假","if_view":"2","color":"","dir_typename":"lvyou","usecoin":"0"},{"catid":"119","catname":"休闲娱乐","if_view":"2","color":"","dir_typename":"yule","usecoin":"0"},{"catid":"120","catname":"酒店/宾馆","if_view":"2","color":"","dir_typename":"jiudian","usecoin":"0"},{"catid":"136","catname":"其它生活服务","if_view":"2","color":"","dir_typename":"qitafuwu","usecoin":"0"}]},{"catid":"10","catname":"教育培训","color":"","if_view":"2","dir_typename":"jiaoyu","usecoin":"0","icon":"http://www.jumin.cn/template/default/images/index/icon_edu.gif","children":[{"catid":"137","catname":"职业培训","if_view":"2","color":"","dir_typename":"zhiyepeixun","usecoin":"0"},{"catid":"138","catname":"外语培训","if_view":"2","color":"","dir_typename":"waiyu","usecoin":"0"},{"catid":"139","catname":"学历教育","if_view":"2","color":"","dir_typename":"xueli","usecoin":"0"},{"catid":"140","catname":"家教","if_view":"2","color":"","dir_typename":"jiajiao","usecoin":"0"},{"catid":"141","catname":"IT培训","if_view":"2","color":"","dir_typename":"jisuanji","usecoin":"0"},{"catid":"142","catname":"留学签证","if_view":"2","color":"","dir_typename":"liuxue","usecoin":"0"},{"catid":"143","catname":"高等教育","if_view":"2","color":"","dir_typename":"gaodengjiaoyu","usecoin":"0"},{"catid":"144","catname":"文体培训","if_view":"2","color":"","dir_typename":"techang","usecoin":"0"},{"catid":"145","catname":"婴幼儿教育","if_view":"2","color":"","dir_typename":"youjiao","usecoin":"0"},{"catid":"146","catname":"中小学教育","if_view":"2","color":"","dir_typename":"zhongxiaoxue","usecoin":"0"},{"catid":"147","catname":"其他培训","if_view":"2","color":"","dir_typename":"qitapeixun","usecoin":"0"}]},{"catid":"244","catname":"商务服务","color":"","if_view":"2","dir_typename":"shangwufuwu","usecoin":"0","icon":"http://www.jumin.cn/template/default/images/index/icon_business.gif","children":[{"catid":"107","catname":"投资理财","if_view":"2","color":"","dir_typename":"licai","usecoin":"0"},{"catid":"121","catname":"机票/签证","if_view":"2","color":"","dir_typename":"qianzheng","usecoin":"0"},{"catid":"122","catname":"招商加盟","if_view":"2","color":"","dir_typename":"zhaoshang","usecoin":"0"},{"catid":"123","catname":"担保/贷款","if_view":"2","color":"","dir_typename":"daikuan","usecoin":"0"},{"catid":"124","catname":"公司注册","if_view":"2","color":"","dir_typename":"gongsizhuce","usecoin":"0"},{"catid":"125","catname":"会计/审计","if_view":"2","color":"","dir_typename":"huiji","usecoin":"0"},{"catid":"126","catname":"网站建设","if_view":"2","color":"","dir_typename":"wangzhan","usecoin":"0"},{"catid":"127","catname":"快递/物流","if_view":"2","color":"","dir_typename":"wuliu","usecoin":"0"},{"catid":"128","catname":"庆典/演出","if_view":"2","color":"","dir_typename":"yanchu","usecoin":"0"},{"catid":"129","catname":"印刷/喷绘","if_view":"2","color":"","dir_typename":"yinshua","usecoin":"0"},{"catid":"130","catname":"设计策划","if_view":"2","color":"","dir_typename":"cehua","usecoin":"0"},{"catid":"131","catname":"律师服务","if_view":"2","color":"","dir_typename":"lvshi","usecoin":"0"},{"catid":"132","catname":"翻译/速记","if_view":"2","color":"","dir_typename":"fanyi","usecoin":"0"},{"catid":"133","catname":"鲜花/盆景","if_view":"2","color":"","dir_typename":"xianhua","usecoin":"0"},{"catid":"134","catname":"礼品定制","if_view":"2","color":"","dir_typename":"lipin","usecoin":"0"},{"catid":"135","catname":"本地名站","if_view":"2","color":"","dir_typename":"mingzhan","usecoin":"0"},{"catid":"245","catname":"其它商务服务","if_view":"2","color":"","dir_typename":"qitashangwu","usecoin":"0"}]}]
     * msg :
     */

    private int ret;
    private String msg;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * catid : 1
         * catname : 二手转让
         * color :
         * if_view : 2
         * dir_typename : ershou
         * usecoin : 0
         * icon : http://www.jumin.cn/template/default/images/index/icon_ershou.gif
         * children : [{"catid":"11","catname":"二手手机","if_view":"2","color":"#ff0000","dir_typename":"shouji","usecoin":"0"},{"catid":"13","catname":"台式电脑","if_view":"2","color":"","dir_typename":"diannao","usecoin":"0"},{"catid":"246","catname":"笔记本","if_view":"2","color":"","dir_typename":"bijiben","usecoin":"0"},{"catid":"12","catname":"平板电脑","if_view":"2","color":"","dir_typename":"pingban","usecoin":"0"},{"catid":"14","catname":"数码产品","if_view":"2","color":"#46a200","dir_typename":"shuma","usecoin":"0"},{"catid":"21","catname":"母婴用品","if_view":"2","color":"","dir_typename":"yingyou","usecoin":"0"},{"catid":"16","catname":"家用电器","if_view":"2","color":"#ff9900","dir_typename":"jiadian","usecoin":"0"},{"catid":"15","catname":"二手家具","if_view":"2","color":"","dir_typename":"jiaju","usecoin":"0"},{"catid":"20","catname":"服饰箱包","if_view":"2","color":"","dir_typename":"fushi","usecoin":"0"},{"catid":"23","catname":"门票卡券","if_view":"2","color":"","dir_typename":"piao","usecoin":"0"},{"catid":"247","catname":"照相机","if_view":"2","color":"","dir_typename":"zhaoxiangji","usecoin":"0"},{"catid":"296","catname":"手机配件","if_view":"2","color":"","dir_typename":"shoujipeijian","usecoin":"0"},{"catid":"17","catname":"家居日用","if_view":"2","color":"","dir_typename":"riyongpin","usecoin":"0"},{"catid":"22","catname":"乐器/运动","if_view":"2","color":"","dir_typename":"wenti","usecoin":"0"},{"catid":"248","catname":"食品农产","if_view":"2","color":"","dir_typename":"shipinnongchan","usecoin":"0"},{"catid":"297","catname":"图书音像","if_view":"2","color":"","dir_typename":"tushuyinying","usecoin":"0"},{"catid":"18","catname":"办公用品","if_view":"2","color":"","dir_typename":"bangong","usecoin":"0"},{"catid":"24","catname":"办公家具","if_view":"2","color":"","dir_typename":"gongyeshebei","usecoin":"0"},{"catid":"19","catname":"工艺/收藏","if_view":"2","color":"","dir_typename":"shoucang","usecoin":"0"},{"catid":"25","catname":"奇石盆景","if_view":"2","color":"","dir_typename":"penjing","usecoin":"0"},{"catid":"26","catname":"闲置真心送","if_view":"2","color":"","dir_typename":"zulin","usecoin":"0"},{"catid":"249","catname":"网游/虚拟物品","if_view":"2","color":"","dir_typename":"wangyou-xuniwupin","usecoin":"0"},{"catid":"27","catname":"其他物品","if_view":"2","color":"","dir_typename":"qitaershou","usecoin":"0"}]
         */

        private String catid;
        private String catname;
        private String color;
        private String if_view;
        private String dir_typename;
        private String usecoin;
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

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
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

        public String getUsecoin() {
            return usecoin;
        }

        public void setUsecoin(String usecoin) {
            this.usecoin = usecoin;
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
             * catid : 11
             * catname : 二手手机
             * if_view : 2
             * color : #ff0000
             * dir_typename : shouji
             * usecoin : 0
             */

            private String catid;
            private String catname;
            private String if_view;
            private String color;
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

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
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
