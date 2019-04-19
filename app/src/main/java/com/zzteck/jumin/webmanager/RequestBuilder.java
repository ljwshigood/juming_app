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

    public static MultipartBody uploadRequestBody2(Context context, String title, String imageFormat, String token, File fileFront,File fileRear) {

        String content_type = getMimeType(fileFront.getPath());
        String file_path = fileFront.getAbsolutePath();
        RequestBody file_body = RequestBody.create(MediaType.parse(content_type), fileFront);

        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("type", content_type)
                .addFormDataPart("s", "App.Member.Upface")
                .addFormDataPart("sign", UtilsTools.getSign(context,"App.Member.Upface"))
                .addFormDataPart("logo", file_path.substring(file_path.lastIndexOf("/") + 1), file_body).build();

    }

    public static MultipartBody uploadRequestBody(Context context, String title, String imageFormat, String token, File file) {

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
    private static String getMimeType(String path) {
        String extension = MimeTypeMap.getFileExtensionFromUrl(path);
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);

    }
}
