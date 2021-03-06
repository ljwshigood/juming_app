package com.zzteck.jumin.bean;


import java.io.Serializable;
import java.util.List;

import javax.net.ssl.SSLEngineResult;

import me.yokeyword.indexablerv.IndexableEntity;

/**
 * Created by Administrator on 2018/6/30.
 * Describe:
 */

public class LinkCat implements Serializable {

    /**
     * ret : 200
     * data : [{"catid":"2","catname":"奥迪","icon":null,"parentid":"1","catorder":"2","firstletter":"A","catshort":"ad","catpy":"aodi"},{"catid":"1096","catname":"阿尔法·罗密欧","icon":null,"parentid":"1","catorder":"1096","firstletter":"A","catshort":"aef-lmo","catpy":"aerfa-luomiou"},{"catid":"1117","catname":"阿斯顿·马丁","icon":null,"parentid":"1","catorder":"1117","firstletter":"A","catshort":"asd-md","catpy":"asidun-mading"},{"catid":"1202","catname":"AC Schnitzer","icon":null,"parentid":"1","catorder":"1202","firstletter":"A","catshort":"AC-Schnitzer","catpy":"AC-Schnitzer"},{"catid":"1207","catname":"安凯客车","icon":null,"parentid":"1","catorder":"1207","firstletter":"A","catshort":"akkc","catpy":"ankaikeche"},{"catid":"1212","catname":"ALPINA","icon":null,"parentid":"1","catorder":"1212","firstletter":"A","catshort":"ALPINA","catpy":"ALPINA"},{"catid":"1215","catname":"比速汽车","icon":null,"parentid":"1","catorder":"1215","firstletter":"B","catshort":"bsqc","catpy":"bisuqiche"},{"catid":"1239","catname":"宝沃","icon":null,"parentid":"1","catorder":"1239","firstletter":"B","catshort":"bw","catpy":"baowo"},{"catid":"1301","catname":"北汽道达","icon":null,"parentid":"1","catorder":"1301","firstletter":"B","catshort":"bqdd","catpy":"beiqidaoda"},{"catid":"1306","catname":"宝骐汽车","icon":null,"parentid":"1","catorder":"1306","firstletter":"B","catshort":"bqqc","catpy":"baoqiqiche"},{"catid":"1309","catname":"北汽新能源","icon":null,"parentid":"1","catorder":"1309","firstletter":"B","catshort":"bqxny","catpy":"beiqixinnenyuan"},{"catid":"1369","catname":"北汽幻速","icon":null,"parentid":"1","catorder":"1369","firstletter":"B","catshort":"bqhs","catpy":"beiqihuansu"},{"catid":"1477","catname":"北汽绅宝","icon":null,"parentid":"1","catorder":"1477","firstletter":"B","catshort":"bqsb","catpy":"beiqishenbao"},{"catid":"1603","catname":"宝骏","icon":null,"parentid":"1","catorder":"1603","firstletter":"B","catshort":"bj","catpy":"baojun"},{"catid":"1830","catname":"巴博斯","icon":null,"parentid":"1","catorder":"1830","firstletter":"B","catshort":"bbs","catpy":"babosi"},{"catid":"1853","catname":"北汽威旺","icon":null,"parentid":"1","catorder":"1853","firstletter":"B","catshort":"bqww","catpy":"beiqiweiwang"},{"catid":"2001","catname":"北汽制造","icon":null,"parentid":"1","catorder":"2001","firstletter":"B","catshort":"bqzz","catpy":"beiqizhizao"},{"catid":"2135","catname":"奔驰","icon":null,"parentid":"1","catorder":"2135","firstletter":"B","catshort":"bc","catpy":"benchi"},{"catid":"3235","catname":"布加迪","icon":null,"parentid":"1","catorder":"3235","firstletter":"B","catshort":"bjd","catpy":"bujiadi"},{"catid":"3241","catname":"别克","icon":null,"parentid":"1","catorder":"3241","firstletter":"B","catshort":"bk","catpy":"bieke"},{"catid":"3815","catname":"宾利","icon":null,"parentid":"1","catorder":"3815","firstletter":"B","catshort":"bl","catpy":"binli"},{"catid":"3907","catname":"保时捷","icon":null,"parentid":"1","catorder":"3907","firstletter":"B","catshort":"bsj","catpy":"baoshijie"},{"catid":"4175","catname":"比亚迪","icon":null,"parentid":"1","catorder":"4175","firstletter":"B","catshort":"byd","catpy":"biyadi"},{"catid":"4756","catname":"北汽昌河","icon":null,"parentid":"1","catorder":"4756","firstletter":"B","catshort":"bqch","catpy":"beiqichanghe"},{"catid":"4923","catname":"奔腾","icon":null,"parentid":"1","catorder":"4923","firstletter":"B","catshort":"bt","catpy":"benteng"},{"catid":"5168","catname":"标致","icon":null,"parentid":"1","catorder":"5168","firstletter":"B","catshort":"bz","catpy":"biaozhi"},{"catid":"5641","catname":"本田","icon":null,"parentid":"1","catorder":"5641","firstletter":"B","catshort":"bt","catpy":"bentian"},{"catid":"6230","catname":"宝马","icon":null,"parentid":"1","catorder":"6230","firstletter":"B","catshort":"bm","catpy":"baoma"},{"catid":"7296","catname":"北京","icon":null,"parentid":"1","catorder":"7296","firstletter":"B","catshort":"bj","catpy":"beijing"},{"catid":"7367","catname":"长安","icon":null,"parentid":"1","catorder":"7367","firstletter":"C","catshort":"ca","catpy":"changan"},{"catid":"7968","catname":"长城","icon":null,"parentid":"1","catorder":"7968","firstletter":"C","catshort":"cc","catpy":"changcheng"},{"catid":"8378","catname":"长安欧尚","icon":null,"parentid":"1","catorder":"8378","firstletter":"C","catshort":"caos","catpy":"changanoushang"},{"catid":"8621","catname":"成功汽车","icon":null,"parentid":"1","catorder":"8621","firstletter":"C","catshort":"cgqc","catpy":"chenggongqiche"},{"catid":"8663","catname":"长安跨越","icon":null,"parentid":"1","catorder":"8663","firstletter":"C","catshort":"caky","catpy":"changankuayue"},{"catid":"8690","catname":"长安轻型车","icon":null,"parentid":"1","catorder":"8690","firstletter":"C","catshort":"caqxc","catpy":"changanqingxingche"},{"catid":"8978","catname":"电咖","icon":null,"parentid":"1","catorder":"8978","firstletter":"D","catshort":"dk","catpy":"dianka"},{"catid":"8986","catname":"东风风光","icon":null,"parentid":"1","catorder":"8986","firstletter":"D","catshort":"dffg","catpy":"dongfengfengguang"},{"catid":"9124","catname":"东风·瑞泰特","icon":null,"parentid":"1","catorder":"9124","firstletter":"D","catshort":"df-rtt","catpy":"dongfeng-ruitaite"},{"catid":"9129","catname":"大乘汽车","icon":null,"parentid":"1","catorder":"9129","firstletter":"D","catshort":"dcqc","catpy":"dachengqiche"},{"catid":"9149","catname":"东风风行","icon":null,"parentid":"1","catorder":"9149","firstletter":"D","catshort":"dffx","catpy":"dongfengfengxing"},{"catid":"9693","catname":"DS","icon":null,"parentid":"1","catorder":"9693","firstletter":"D","catshort":"DS","catpy":"DS"},{"catid":"9802","catname":"东风风度","icon":null,"parentid":"1","catorder":"9802","firstletter":"D","catshort":"dffd","catpy":"dongfengfengdu"},{"catid":"9826","catname":"东风小康","icon":null,"parentid":"1","catorder":"9826","firstletter":"D","catshort":"dfxk","catpy":"dongfengxiaokang"},{"catid":"10034","catname":"东风风神","icon":null,"parentid":"1","catorder":"10034","firstletter":"D","catshort":"dffs","catpy":"dongfengfengshen"},{"catid":"10233","catname":"东南","icon":null,"parentid":"1","catorder":"10233","firstletter":"D","catshort":"dn","catpy":"dongnan"},{"catid":"10521","catname":"道奇","icon":null,"parentid":"1","catorder":"10521","firstletter":"D","catshort":"dq","catpy":"daoqi"},{"catid":"10555","catname":"大发","icon":null,"parentid":"1","catorder":"10555","firstletter":"D","catshort":"df","catpy":"dafa"},{"catid":"10570","catname":"东风","icon":null,"parentid":"1","catorder":"10570","firstletter":"D","catshort":"df","catpy":"dongfeng"},{"catid":"10964","catname":"大众","icon":null,"parentid":"1","catorder":"10964","firstletter":"D","catshort":"dz","catpy":"dazhong"},{"catid":"13079","catname":"丰田","icon":null,"parentid":"1","catorder":"13079","firstletter":"F","catshort":"ft","catpy":"fengtian"},{"catid":"14128","catname":"福特","icon":null,"parentid":"1","catorder":"14128","firstletter":"F","catshort":"ft","catpy":"fute"},{"catid":"14938","catname":"菲亚特","icon":null,"parentid":"1","catorder":"14938","firstletter":"F","catshort":"fyt","catpy":"feiyate"},{"catid":"15075","catname":"福田","icon":null,"parentid":"1","catorder":"15075","firstletter":"F","catshort":"ft","catpy":"futian"},{"catid":"16140","catname":"法拉利","icon":null,"parentid":"1","catorder":"16140","firstletter":"F","catshort":"fll","catpy":"falali"},{"catid":"16182","catname":"福迪","icon":null,"parentid":"1","catorder":"16182","firstletter":"F","catshort":"fd","catpy":"fudi"},{"catid":"16290","catname":"福汽启腾","icon":null,"parentid":"1","catorder":"16290","firstletter":"F","catshort":"fqqt","catpy":"fuqiqiteng"},{"catid":"16314","catname":"观致","icon":null,"parentid":"1","catorder":"16314","firstletter":"G","catshort":"gz","catpy":"guanzhi"},{"catid":"16416","catname":"GMC","icon":null,"parentid":"1","catorder":"16416","firstletter":"G","catshort":"GMC","catpy":"GMC"},{"catid":"16526","catname":"广汽吉奥","icon":null,"parentid":"1","catorder":"16526","firstletter":"G","catshort":"gqja","catpy":"guangqijiao"},{"catid":"16710","catname":"光冈","icon":null,"parentid":"1","catorder":"16710","firstletter":"G","catshort":"gg","catpy":"guanggang"},{"catid":"16717","catname":"广汽传祺","icon":null,"parentid":"1","catorder":"16717","firstletter":"G","catshort":"gqcq","catpy":"guangqichuanqi"},{"catid":"16964","catname":"广汽集团","icon":null,"parentid":"1","catorder":"16964","firstletter":"G","catshort":"gqjt","catpy":"guangqijituan"},{"catid":"16979","catname":"广汽新能源","icon":null,"parentid":"1","catorder":"16979","firstletter":"G","catshort":"gqxny","catpy":"guangqixinnenyuan"},{"catid":"16996","catname":"Genesis","icon":null,"parentid":"1","catorder":"16996","firstletter":"G","catshort":"Genesis","catpy":"Genesis"},{"catid":"17010","catname":"国金汽车","icon":null,"parentid":"1","catorder":"17010","firstletter":"G","catshort":"gjqc","catpy":"guojinqiche"},{"catid":"17019","catname":"合众汽车","icon":null,"parentid":"1","catorder":"17019","firstletter":"H","catshort":"hzqc","catpy":"hezhongqiche"},{"catid":"17025","catname":"华泰新能源","icon":null,"parentid":"1","catorder":"17025","firstletter":"H","catshort":"htxny","catpy":"huataixinnenyuan"},{"catid":"17045","catname":"华利","icon":null,"parentid":"1","catorder":"17045","firstletter":"H","catshort":"hl","catpy":"huali"},{"catid":"17048","catname":"汉腾汽车","icon":null,"parentid":"1","catorder":"17048","firstletter":"H","catshort":"htqc","catpy":"hantengqiche"},{"catid":"17094","catname":"华凯","icon":null,"parentid":"1","catorder":"17094","firstletter":"H","catshort":"hk","catpy":"huakai"},{"catid":"17112","catname":"红星汽车","icon":null,"parentid":"1","catorder":"17112","firstletter":"H","catshort":"hxqc","catpy":"hongxingqiche"},{"catid":"17118","catname":"悍马","icon":null,"parentid":"1","catorder":"17118","firstletter":"H","catshort":"hm","catpy":"hanma"},{"catid":"17126","catname":"黄海","icon":null,"parentid":"1","catorder":"17126","firstletter":"H","catshort":"hh","catpy":"huanghai"},{"catid":"17364","catname":"红旗","icon":null,"parentid":"1","catorder":"17364","firstletter":"H","catshort":"hq","catpy":"hongqi"},{"catid":"17437","catname":"华普","icon":null,"parentid":"1","catorder":"17437","firstletter":"H","catshort":"hp","catpy":"huapu"},{"catid":"17486","catname":"海马","icon":null,"parentid":"1","catorder":"17486","firstletter":"H","catshort":"hm","catpy":"haima"},{"catid":"17950","catname":"华泰","icon":null,"parentid":"1","catorder":"17950","firstletter":"H","catshort":"ht","catpy":"huatai"},{"catid":"18108","catname":"哈飞","icon":null,"parentid":"1","catorder":"18108","firstletter":"H","catshort":"hf","catpy":"hafei"},{"catid":"18217","catname":"海格","icon":null,"parentid":"1","catorder":"18217","firstletter":"H","catshort":"hg","catpy":"haige"},{"catid":"18285","catname":"华颂","icon":null,"parentid":"1","catorder":"18285","firstletter":"H","catshort":"hs","catpy":"huasong"},{"catid":"18293","catname":"华骐","icon":null,"parentid":"1","catorder":"18293","firstletter":"H","catshort":"hq","catpy":"huaqi"},{"catid":"18296","catname":"哈弗","icon":null,"parentid":"1","catorder":"18296","firstletter":"H","catshort":"hf","catpy":"hafu"},{"catid":"18956","catname":"恒天","icon":null,"parentid":"1","catorder":"18956","firstletter":"H","catshort":"ht","catpy":"hengtian"},{"catid":"18979","catname":"Icona","icon":null,"parentid":"1","catorder":"18979","firstletter":"I","catshort":"Icona","catpy":"Icona"},{"catid":"18982","catname":"金旅","icon":null,"parentid":"1","catorder":"18982","firstletter":"J","catshort":"jl","catpy":"jinlv"},{"catid":"19013","catname":"江铃集团轻汽","icon":null,"parentid":"1","catorder":"19013","firstletter":"J","catshort":"jljtqq","catpy":"jianglingjituanqingqi"},{"catid":"19333","catname":"九龙","icon":null,"parentid":"1","catorder":"19333","firstletter":"J","catshort":"jl","catpy":"jiulong"},{"catid":"19396","catname":"金龙","icon":null,"parentid":"1","catorder":"19396","firstletter":"J","catshort":"jl","catpy":"jinlong"},{"catid":"19515","catname":"江铃","icon":null,"parentid":"1","catorder":"19515","firstletter":"J","catshort":"jl","catpy":"jiangling"},{"catid":"19684","catname":"吉利汽车","icon":null,"parentid":"1","catorder":"19684","firstletter":"J","catshort":"jlqc","catpy":"jiliqiche"},{"catid":"20497","catname":"Jeep","icon":null,"parentid":"1","catorder":"20497","firstletter":"J","catshort":"Jeep","catpy":"Jeep"},{"catid":"20869","catname":"捷豹","icon":null,"parentid":"1","catorder":"20869","firstletter":"J","catshort":"jb","catpy":"jiebao"},{"catid":"21097","catname":"金杯","icon":null,"parentid":"1","catorder":"21097","firstletter":"J","catshort":"jb","catpy":"jinbei"},{"catid":"21712","catname":"江淮","icon":null,"parentid":"1","catorder":"21712","firstletter":"J","catshort":"jh","catpy":"jianghuai"},{"catid":"22608","catname":"捷途","icon":null,"parentid":"1","catorder":"22608","firstletter":"J","catshort":"jt","catpy":"jietu"},{"catid":"22658","catname":"钧天","icon":null,"parentid":"1","catorder":"22658","firstletter":"J","catshort":"jt","catpy":"juntian"},{"catid":"22662","catname":"江铃集团新能源","icon":null,"parentid":"1","catorder":"22662","firstletter":"J","catshort":"jljtxny","catpy":"jianglingjituanxinnenyuan"},{"catid":"22691","catname":"君马汽车","icon":null,"parentid":"1","catorder":"22691","firstletter":"J","catshort":"jmqc","catpy":"junmaqiche"},{"catid":"22738","catname":"科尼赛克","icon":null,"parentid":"1","catorder":"22738","firstletter":"K","catshort":"knsk","catpy":"kenisaike"},{"catid":"22746","catname":"开瑞","icon":null,"parentid":"1","catorder":"22746","firstletter":"K","catshort":"kr","catpy":"kairui"},{"catid":"22998","catname":"凯迪拉克","icon":null,"parentid":"1","catorder":"22998","firstletter":"K","catshort":"kdlk","catpy":"kaidilake"},{"catid":"23208","catname":"克莱斯勒","icon":null,"parentid":"1","catorder":"23208","firstletter":"K","catshort":"klsl","catpy":"kelaisile"},{"catid":"23250","catname":"KTM","icon":null,"parentid":"1","catorder":"23250","firstletter":"K","catshort":"KTM","catpy":"KTM"},{"catid":"23253","catname":"卡尔森","icon":null,"parentid":"1","catorder":"23253","firstletter":"K","catshort":"kes","catpy":"kaersen"},{"catid":"23263","catname":"开沃汽车","icon":null,"parentid":"1","catorder":"23263","firstletter":"K","catshort":"kwqc","catpy":"kaiwoqiche"},{"catid":"23302","catname":"凯翼","icon":null,"parentid":"1","catorder":"23302","firstletter":"K","catshort":"ky","catpy":"kaiyi"},{"catid":"23375","catname":"卡升","icon":null,"parentid":"1","catorder":"23375","firstletter":"K","catshort":"ks","catpy":"kasheng"},{"catid":"23392","catname":"卡威","icon":null,"parentid":"1","catorder":"23392","firstletter":"K","catshort":"kw","catpy":"kawei"},{"catid":"23466","catname":"陆地方舟","icon":null,"parentid":"1","catorder":"23466","firstletter":"L","catshort":"ldfz","catpy":"ludifangzhou"},{"catid":"23483","catname":"Lorinser","icon":null,"parentid":"1","catorder":"23483","firstletter":"L","catshort":"Lorinser","catpy":"Lorinser"},{"catid":"23511","catname":"理念","icon":null,"parentid":"1","catorder":"23511","firstletter":"L","catshort":"ln","catpy":"linian"},{"catid":"23530","catname":"雷诺","icon":null,"parentid":"1","catorder":"23530","firstletter":"L","catshort":"ln","catpy":"leinuo"},{"catid":"23732","catname":"兰博基尼","icon":null,"parentid":"1","catorder":"23732","firstletter":"L","catshort":"lbjn","catpy":"lanbojini"},{"catid":"23776","catname":"路虎","icon":null,"parentid":"1","catorder":"23776","firstletter":"L","catshort":"lh","catpy":"luhu"},{"catid":"24104","catname":"路特斯","icon":null,"parentid":"1","catorder":"24104","firstletter":"L","catshort":"lts","catpy":"lutesi"},{"catid":"24123","catname":"林肯","icon":null,"parentid":"1","catorder":"24123","firstletter":"L","catshort":"lk","catpy":"linken"},{"catid":"24255","catname":"雷克萨斯","icon":null,"parentid":"1","catorder":"24255","firstletter":"L","catshort":"lkss","catpy":"leikesasi"},{"catid":"24610","catname":"铃木","icon":null,"parentid":"1","catorder":"24610","firstletter":"L","catshort":"lm","catpy":"lingmu"},{"catid":"25116","catname":"劳斯莱斯","icon":null,"parentid":"1","catorder":"25116","firstletter":"L","catshort":"lsls","catpy":"laosilaisi"},{"catid":"25147","catname":"陆风","icon":null,"parentid":"1","catorder":"25147","firstletter":"L","catshort":"lf","catpy":"lufeng"},{"catid":"25366","catname":"莲花汽车","icon":null,"parentid":"1","catorder":"25366","firstletter":"L","catshort":"lhqc","catpy":"lianhuaqiche"},{"catid":"25436","catname":"力帆汽车","icon":null,"parentid":"1","catorder":"25436","firstletter":"L","catshort":"lfqc","catpy":"lifanqiche"},{"catid":"25697","catname":"猎豹汽车","icon":null,"parentid":"1","catorder":"25697","firstletter":"L","catshort":"lbqc","catpy":"liebaoqiche"},{"catid":"25936","catname":"领克","icon":null,"parentid":"1","catorder":"25936","firstletter":"L","catshort":"lk","catpy":"lingke"},{"catid":"25993","catname":"LOCAL MOTORS","icon":null,"parentid":"1","catorder":"25993","firstletter":"L","catshort":"LOCAL-MOTORS","catpy":"LOCAL-MOTORS"},{"catid":"25996","catname":"LITE","icon":null,"parentid":"1","catorder":"25996","firstletter":"L","catshort":"LITE","catpy":"LITE"},{"catid":"26002","catname":"领途汽车","icon":null,"parentid":"1","catorder":"26002","firstletter":"L","catshort":"ltqc","catpy":"lingtuqiche"},{"catid":"26011","catname":"理想","icon":null,"parentid":"1","catorder":"26011","firstletter":"L","catshort":"lx","catpy":"lixiang"},{"catid":"26015","catname":"罗夫哈特","icon":null,"parentid":"1","catorder":"26015","firstletter":"L","catshort":"lfht","catpy":"luofuhate"},{"catid":"26019","catname":"零跑汽车","icon":null,"parentid":"1","catorder":"26019","firstletter":"L","catshort":"lpqc","catpy":"lingpaoqiche"},{"catid":"26025","catname":"迈巴赫","icon":null,"parentid":"1","catorder":"26025","firstletter":"M","catshort":"mbh","catpy":"maibahe"},{"catid":"26031","catname":"MINI","icon":null,"parentid":"1","catorder":"26031","firstletter":"M","catshort":"MINI","catpy":"MINI"},{"catid":"26263","catname":"玛莎拉蒂","icon":null,"parentid":"1","catorder":"26263","firstletter":"M","catshort":"msld","catpy":"mashaladi"},{"catid":"26328","catname":"马自达","icon":null,"parentid":"1","catorder":"26328","firstletter":"M","catshort":"mzd","catpy":"mazida"},{"catid":"26654","catname":"名爵","icon":null,"parentid":"1","catorder":"26654","firstletter":"M","catshort":"mj","catpy":"mingjue"},{"catid":"26902","catname":"迈凯伦","icon":null,"parentid":"1","catorder":"26902","firstletter":"M","catshort":"mkl","catpy":"maikailun"},{"catid":"26933","catname":"摩根","icon":null,"parentid":"1","catorder":"26933","firstletter":"M","catshort":"mg","catpy":"mogen"},{"catid":"26953","catname":"纳智捷","icon":null,"parentid":"1","catorder":"26953","firstletter":"N","catshort":"nzj","catpy":"nazhijie"},{"catid":"27093","catname":"欧朗","icon":null,"parentid":"1","catorder":"27093","firstletter":"O","catshort":"ol","catpy":"oulang"},{"catid":"27105","catname":"欧宝","icon":null,"parentid":"1","catorder":"27105","firstletter":"O","catshort":"ob","catpy":"oubao"},{"catid":"27234","catname":"欧拉","icon":null,"parentid":"1","catorder":"27234","firstletter":"O","catshort":"ol","catpy":"oula"},{"catid":"27250","catname":"欧尚汽车","icon":null,"parentid":"1","catorder":"27250","firstletter":"O","catshort":"osqc","catpy":"oushangqiche"},{"catid":"27265","catname":"Polestar极星","icon":null,"parentid":"1","catorder":"27265","firstletter":"P","catshort":"Polestarjx","catpy":"Polestarjixing"},{"catid":"27271","catname":"帕加尼","icon":null,"parentid":"1","catorder":"27271","firstletter":"P","catshort":"pjn","catpy":"pajiani"},{"catid":"27274","catname":"起亚","icon":null,"parentid":"1","catorder":"27274","firstletter":"Q","catshort":"qy","catpy":"qiya"},{"catid":"28022","catname":"奇瑞","icon":null,"parentid":"1","catorder":"28022","firstletter":"Q","catshort":"qr","catpy":"qirui"},{"catid":"28798","catname":"启辰","icon":null,"parentid":"1","catorder":"28798","firstletter":"Q","catshort":"qc","catpy":"qichen"},{"catid":"28957","catname":"乔治·巴顿","icon":null,"parentid":"1","catorder":"28957","firstletter":"Q","catshort":"qz-bd","catpy":"qiaozhi-badun"},{"catid":"28963","catname":"全球鹰","icon":null,"parentid":"1","catorder":"28963","firstletter":"Q","catshort":"qqy","catpy":"quanqiuying"},{"catid":"28984","catname":"前途","icon":null,"parentid":"1","catorder":"28984","firstletter":"Q","catshort":"qt","catpy":"qiantu"},{"catid":"28987","catname":"庆铃汽车","icon":null,"parentid":"1","catorder":"28987","firstletter":"Q","catshort":"qlqc","catpy":"qinglingqiche"},{"catid":"28995","catname":"瑞驰新能源","icon":null,"parentid":"1","catorder":"28995","firstletter":"R","catshort":"rcxny","catpy":"ruichixinnenyuan"},{"catid":"29003","catname":"容大智造","icon":null,"parentid":"1","catorder":"29003","firstletter":"R","catshort":"rdzz","catpy":"rongdazhizao"},{"catid":"29007","catname":"如虎","icon":null,"parentid":"1","catorder":"29007","firstletter":"R","catshort":"rh","catpy":"ruhu"},{"catid":"29012","catname":"瑞麒","icon":null,"parentid":"1","catorder":"29012","firstletter":"R","catshort":"rq","catpy":"ruiqi"},{"catid":"29069","catname":"荣威","icon":null,"parentid":"1","catorder":"29069","firstletter":"R","catshort":"rw","catpy":"rongwei"},{"catid":"29364","catname":"日产","icon":null,"parentid":"1","catorder":"29364","firstletter":"R","catshort":"rc","catpy":"richan"},{"catid":"30139","catname":"萨博","icon":null,"parentid":"1","catorder":"30139","firstletter":"S","catshort":"sb","catpy":"sabo"},{"catid":"30157","catname":"斯巴鲁","icon":null,"parentid":"1","catorder":"30157","firstletter":"S","catshort":"sbl","catpy":"sibalu"},{"catid":"30433","catname":"世爵","icon":null,"parentid":"1","catorder":"30433","firstletter":"S","catshort":"sj","catpy":"shijue"},{"catid":"30436","catname":"斯柯达","icon":null,"parentid":"1","catorder":"30436","firstletter":"S","catshort":"skd","catpy":"sikeda"},{"catid":"30929","catname":"三菱","icon":null,"parentid":"1","catorder":"30929","firstletter":"S","catshort":"sl","catpy":"sanling"},{"catid":"31297","catname":"双龙","icon":null,"parentid":"1","catorder":"31297","firstletter":"S","catshort":"sl","catpy":"shuanglong"},{"catid":"31456","catname":"smart","icon":null,"parentid":"1","catorder":"31456","firstletter":"S","catshort":"smart","catpy":"smart"},{"catid":"31569","catname":"双环","icon":null,"parentid":"1","catorder":"31569","firstletter":"S","catshort":"sh","catpy":"shuanghuan"},{"catid":"31657","catname":"陕汽通家","icon":null,"parentid":"1","catorder":"31657","firstletter":"S","catshort":"sqtj","catpy":"shanqitongjia"},{"catid":"31684","catname":"上汽大通MAXUS","icon":null,"parentid":"1","catorder":"31684","firstletter":"S","catshort":"sqdtMAXUS","catpy":"shangqidatongMAXUS"},{"catid":"32182","catname":"思铭","icon":null,"parentid":"1","catorder":"32182","firstletter":"S","catshort":"sm","catpy":"siming"},{"catid":"32189","catname":"赛麟","icon":null,"parentid":"1","catorder":"32189","firstletter":"S","catshort":"sl","catpy":"sailin"},{"catid":"32207","catname":"SRM鑫源","icon":null,"parentid":"1","catorder":"32207","firstletter":"S","catshort":"SRMxy","catpy":"SRMxinyuan"},{"catid":"32237","catname":"斯达泰克","icon":null,"parentid":"1","catorder":"32237","firstletter":"S","catshort":"sdtk","catpy":"sidataike"},{"catid":"32254","catname":"SWM斯威汽车","icon":null,"parentid":"1","catorder":"32254","firstletter":"S","catshort":"SWMswqc","catpy":"SWMsiweiqiche"},{"catid":"32314","catname":"天际汽车","icon":null,"parentid":"1","catorder":"32314","firstletter":"T","catshort":"tjqc","catpy":"tianjiqiche"},{"catid":"32318","catname":"泰卡特","icon":null,"parentid":"1","catorder":"32318","firstletter":"T","catshort":"tkt","catpy":"taikate"},{"catid":"32331","catname":"腾势","icon":null,"parentid":"1","catorder":"32331","firstletter":"T","catshort":"ts","catpy":"tengshi"},{"catid":"32340","catname":"特斯拉","icon":null,"parentid":"1","catorder":"32340","firstletter":"T","catshort":"tsl","catpy":"tesila"},{"catid":"32383","catname":"五菱汽车","icon":null,"parentid":"1","catorder":"32383","firstletter":"W","catshort":"wlqc","catpy":"wulingqiche"},{"catid":"32546","catname":"五十铃","icon":null,"parentid":"1","catorder":"32546","firstletter":"W","catshort":"wsl","catpy":"wushiling"},{"catid":"32694","catname":"潍柴英致","icon":null,"parentid":"1","catorder":"32694","firstletter":"W","catshort":"wcyz","catpy":"weichaiyingzhi"},{"catid":"32743","catname":"威麟","icon":null,"parentid":"1","catorder":"32743","firstletter":"W","catshort":"wl","catpy":"weilin"},{"catid":"33280","catname":"蔚来","icon":null,"parentid":"1","catorder":"32767","firstletter":"W","catshort":"wl","catpy":"weilai"},{"catid":"32785","catname":"威兹曼","icon":null,"parentid":"1","catorder":"32767","firstletter":"W","catshort":"wzm","catpy":"weiziman"},{"catid":"32795","catname":"沃尔沃","icon":null,"parentid":"1","catorder":"32767","firstletter":"W","catshort":"wew","catpy":"woerwo"},{"catid":"33236","catname":"威马汽车","icon":null,"parentid":"1","catorder":"32767","firstletter":"W","catshort":"wmqc","catpy":"weimaqiche"},{"catid":"33252","catname":"WEY","icon":null,"parentid":"1","catorder":"32767","firstletter":"W","catshort":"WEY","catpy":"WEY"},{"catid":"27160","catname":"讴歌","icon":null,"parentid":"1","catorder":"27160","firstletter":"X","catshort":"xg","catpy":"xuge"},{"catid":"33292","catname":"小鹏汽车","icon":null,"parentid":"1","catorder":"32767","firstletter":"X","catshort":"xpqc","catpy":"xiaopengqiche"},{"catid":"33297","catname":"新特汽车","icon":null,"parentid":"1","catorder":"32767","firstletter":"X","catshort":"xtqc","catpy":"xinteqiche"},{"catid":"33303","catname":"星途","icon":null,"parentid":"1","catorder":"32767","firstletter":"X","catshort":"xt","catpy":"xingtu"},{"catid":"33819","catname":"雪铁龙","icon":null,"parentid":"1","catorder":"32767","firstletter":"X","catshort":"xtl","catpy":"xuetielong"},{"catid":"35100","catname":"西雅特","icon":null,"parentid":"1","catorder":"32767","firstletter":"X","catshort":"xyt","catpy":"xiyate"},{"catid":"33311","catname":"雪佛兰","icon":null,"parentid":"1","catorder":"32767","firstletter":"X","catshort":"xfl","catpy":"xuefulan"},{"catid":"35117","catname":"新凯","icon":null,"parentid":"1","catorder":"32767","firstletter":"X","catshort":"xk","catpy":"xinkai"},{"catid":"34373","catname":"现代","icon":null,"parentid":"1","catorder":"32767","firstletter":"X","catshort":"xd","catpy":"xiandai"},{"catid":"35587","catname":"野马汽车","icon":null,"parentid":"1","catorder":"32767","firstletter":"Y","catshort":"ymqc","catpy":"yemaqiche"},{"catid":"35883","catname":"永源","icon":null,"parentid":"1","catorder":"32767","firstletter":"Y","catshort":"yy","catpy":"yongyuan"},{"catid":"35130","catname":"一汽","icon":null,"parentid":"1","catorder":"32767","firstletter":"Y","catshort":"yq","catpy":"yiqi"},{"catid":"36178","catname":"云雀汽车","icon":null,"parentid":"1","catorder":"32767","firstletter":"Y","catshort":"yqqc","catpy":"yunqueqiche"},{"catid":"36184","catname":"御捷","icon":null,"parentid":"1","catorder":"32767","firstletter":"Y","catshort":"yj","catpy":"yujie"},{"catid":"36187","catname":"驭胜","icon":null,"parentid":"1","catorder":"32767","firstletter":"Y","catshort":"ys","catpy":"yusheng"},{"catid":"35679","catname":"依维柯","icon":null,"parentid":"1","catorder":"32767","firstletter":"Y","catshort":"ywk","catpy":"yiweike"},{"catid":"35973","catname":"英菲尼迪","icon":null,"parentid":"1","catorder":"32767","firstletter":"Y","catshort":"yfnd","catpy":"yingfeinidi"},{"catid":"36277","catname":"云度","icon":null,"parentid":"1","catorder":"32767","firstletter":"Y","catshort":"yd","catpy":"yundu"},{"catid":"36300","catname":"宇通客车","icon":null,"parentid":"1","catorder":"32767","firstletter":"Y","catshort":"ytkc","catpy":"yutongkeche"},{"catid":"36314","catname":"裕路","icon":null,"parentid":"1","catorder":"32767","firstletter":"Y","catshort":"yl","catpy":"yulu"},{"catid":"36544","catname":"中华","icon":null,"parentid":"1","catorder":"32767","firstletter":"Z","catshort":"zh","catpy":"zhonghua"},{"catid":"37321","catname":"之诺","icon":null,"parentid":"1","catorder":"32767","firstletter":"Z","catshort":"zn","catpy":"zhinuo"},{"catid":"37324","catname":"知豆","icon":null,"parentid":"1","catorder":"32767","firstletter":"Z","catshort":"zd","catpy":"zhidou"},{"catid":"36318","catname":"中兴","icon":null,"parentid":"1","catorder":"32767","firstletter":"Z","catshort":"zx","catpy":"zhongxing"},{"catid":"36850","catname":"众泰","icon":null,"parentid":"1","catorder":"32767","firstletter":"Z","catshort":"zt","catpy":"zhongtai"}]
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

    public static class DataBean implements IndexableEntity,Serializable{
        /**
         * catid : 2
         * catname : 奥迪
         * icon : null
         * parentid : 1
         * catorder : 2
         * firstletter : A
         * catshort : ad
         * catpy : aodi
         */

        private String catid;
        private String catname;
        private Object icon;
        private String parentid;
        private String catorder;
        private String firstletter;
        private String catshort;
        private String catpy;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        private String type ;

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

        public Object getIcon() {
            return icon;
        }

        public void setIcon(Object icon) {
            this.icon = icon;
        }

        public String getParentid() {
            return parentid;
        }

        public void setParentid(String parentid) {
            this.parentid = parentid;
        }

        public String getCatorder() {
            return catorder;
        }

        public void setCatorder(String catorder) {
            this.catorder = catorder;
        }

        public String getFirstletter() {
            return firstletter;
        }

        public void setFirstletter(String firstletter) {
            this.firstletter = firstletter;
        }

        public String getCatshort() {
            return catshort;
        }

        public void setCatshort(String catshort) {
            this.catshort = catshort;
        }

        public String getCatpy() {
            return catpy;
        }

        public void setCatpy(String catpy) {
            this.catpy = catpy;
        }

        @Override
        public String getFieldIndexBy() {
            return catname;
        }

        @Override
        public void setFieldIndexBy(String indexField) {
            this.catname = indexField ;
        }

        @Override
        public void setFieldPinyinIndexBy(String pinyin) {

        }
    }
}
