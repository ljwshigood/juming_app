package com.zzteck.jumin.utils;

import android.content.Context;
import android.text.TextUtils;

import com.zzteck.jumin.bean.User;
import com.zzteck.jumin.db.UserDAO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2019/2/15 0015.
 */

public class UtilsTools {


    public static String getSign(Context context, String interfaces){
        List<User> userList = UserDAO.getInstance(context).selectUserList() ;
        if(userList != null && userList.size() > 0){
            return UtilsTools.md5("jumin_"+interfaces)+userList.get(0).getWanjiaToken() ;
        }else{
            return UtilsTools.md5("jumin_"+interfaces) ;
        }
    }

    private static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


    //参数类型是Map<String,String> 因为支付只能用string的参数。如果诸君还需要修改的话，那也可以适当的做调整
    /**
     *
     * map转str
     * @param map
     * @return
     */
    public static String getMapToString(Map<String,String> map){
        Set<String> keySet = map.keySet();
        //将set集合转换为数组
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        //给数组排序(升序)
        Arrays.sort(keyArray);
        //因为String拼接效率会很低的，所以转用StringBuilder。博主会在这篇博文发后不久，会更新一篇String与StringBuilder开发时的抉择的博文。
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keyArray.length; i++) {
            // 参数值为空，则不参与签名 这个方法trim()是去空格
            if (map.get(keyArray[i]).trim().length() > 0) {
                sb.append(keyArray[i]).append("=").append(map.get(keyArray[i]).trim());
            }
            if(i != keyArray.length-1){
                sb.append("&");
            }
        }
        return sb.toString();
    }
}
