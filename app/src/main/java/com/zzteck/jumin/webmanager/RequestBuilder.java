package com.zzteck.jumin.webmanager;

import android.content.Context;
import android.webkit.MimeTypeMap;

import com.zzteck.jumin.bean.User;
import com.zzteck.jumin.db.UserDAO;
import com.zzteck.jumin.utils.UtilsTools;

import java.io.File;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by root on 6/16/17.
 *
 */

public class RequestBuilder {

    //Login request body
    public static RequestBody LoginBody(String username, String password, String token) {
        return new FormBody.Builder()
                .add("action", "login")
                .add("format", "json")
                .add("username", username)
                .add("password", password)
                .add("logintoken", token)
                .build();
    }
    //App.Info.Upvideo


    public static MultipartBody uploadRequestBody4(Context context, File file) {

        String content_type = getMimeType(file.getPath());
        String file_path = file.getAbsolutePath();
        RequestBody file_body = RequestBody.create(MediaType.parse(content_type), file);

        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("type", content_type)
                .addFormDataPart("s", "App.Info.Upvideo")
                .addFormDataPart("sign", UtilsTools.getSign(context,"App.Info.Upvideo"))
                .addFormDataPart("video", file_path.substring(file_path.lastIndexOf("/") + 1), file_body).build();

    }

    public static MultipartBody uploadRequestBody3(Context context, File file) {

        String content_type = getMimeType(file.getPath());
        String file_path = file.getAbsolutePath();
        RequestBody file_body = RequestBody.create(MediaType.parse(content_type), file);

        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("type", content_type)
                .addFormDataPart("s", "App.Info.Upimg")
                .addFormDataPart("sign", UtilsTools.getSign(context,"App.Info.Upimg"))
                .addFormDataPart("img", file_path.substring(file_path.lastIndexOf("/") + 1), file_body).build();

    }

    public static MultipartBody uploadRequestBody5(Context context,String cname,String idc, File file) {

        String content_type = getMimeType(file.getPath());
        String file_path = file.getAbsolutePath();
        RequestBody file_body = RequestBody.create(MediaType.parse(content_type), file);

        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("type", content_type)
                .addFormDataPart("s", "App.Member.Comcer")
                .addFormDataPart("cname", cname)
                .addFormDataPart("idc", idc)
                .addFormDataPart("sign", UtilsTools.getSign(context,"App.Member.Comcer"))
                .addFormDataPart("fimage", file_path.substring(file_path.lastIndexOf("/") + 1), file_body).build();

    }


    public static MultipartBody uploadRequestBody(Context context, File file) {

        String content_type = getMimeType(file.getPath());
        String file_path = file.getAbsolutePath();
        RequestBody file_body = RequestBody.create(MediaType.parse(content_type), file);

        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("type", content_type)
                .addFormDataPart("s", "App.Member.Upface")
                .addFormDataPart("sign", UtilsTools.getSign(context,"App.Member.Upface"))
                .addFormDataPart("logo", file_path.substring(file_path.lastIndexOf("/") + 1), file_body).build();

    }


    public static MultipartBody uploadRequestBody3(Context context, String cname, String idc, File ffile,File bimage) {

        String content_type = getMimeType(ffile.getPath());
        String file_path = ffile.getAbsolutePath();
        RequestBody file_body = RequestBody.create(MediaType.parse(content_type), ffile);

        String content_type1 = getMimeType(bimage.getPath());
        String file_path1 = ffile.getAbsolutePath();
        RequestBody file_body1 = RequestBody.create(MediaType.parse(content_type1), bimage);

        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("type", content_type)
                .addFormDataPart("cname",cname)
                .addFormDataPart("s", "App.Member.Personcer")
                .addFormDataPart("idc",idc)
                .addFormDataPart("sign", UtilsTools.getSign(context,"App.Member.Personcer"))
                .addFormDataPart("bimage", file_path1.substring(file_path.lastIndexOf("/") + 1), file_body1)
                .addFormDataPart("fimage", file_path.substring(file_path.lastIndexOf("/") + 1), file_body).build();

    }

    public static MultipartBody uploadRequestBody2(Context context, String cname, String idc, File ffile,File bimage) {

        String content_type = getMimeType(ffile.getPath());
        String file_path = ffile.getAbsolutePath();
        RequestBody file_body = RequestBody.create(MediaType.parse(content_type), ffile);

        String content_type1 = getMimeType(bimage.getPath());
        String file_path1 = ffile.getAbsolutePath();
        RequestBody file_body1 = RequestBody.create(MediaType.parse(content_type1), bimage);

        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("type", content_type)
                .addFormDataPart("cname",cname)
                .addFormDataPart("s", "App.Member.Personcer")
                .addFormDataPart("idc",idc)
                .addFormDataPart("sign", UtilsTools.getSign(context,"App.Member.Personcer"))
                .addFormDataPart("bimage", file_path1.substring(file_path.lastIndexOf("/") + 1), file_body1)
                .addFormDataPart("fimage", file_path.substring(file_path.lastIndexOf("/") + 1), file_body).build();

    }

    private static String getMimeType(String path) {
        String extension = MimeTypeMap.getFileExtensionFromUrl(path);
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);

    }
}
