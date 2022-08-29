package com.eternal.framework.http.upload;

import com.eternal.framework.http.factory.ApiFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import p014io.reactivex.Observable;

public class UploadHelper {
    public static Observable<ResponseBody> uploadImage(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str2);
        return uploadFilesWithParams(str, "uploaded_file", (Map<String, Object>) null, arrayList);
    }

    public static Observable<ResponseBody> uploadImages(String str, List<String> list) {
        return uploadFilesWithParams(str, "uploaded_file", (Map<String, Object>) null, list);
    }

    public static Observable<ResponseBody> uploadFilesWithParams(String str, String str2, Map<String, Object> map, List<String> list) {
        MultipartBody.Builder type = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (map != null) {
            for (String next : map.keySet()) {
                type.addFormDataPart(next, (String) map.get(next));
            }
        }
        for (int i = 0; i < list.size(); i++) {
            File file = new File(list.get(i));
            type.addFormDataPart(str2, file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
        }
        return ((UploadFileApi) ApiFactory.getInstance().createApi(UploadFileApi.class)).uploadFiles(str, type.build().parts());
    }
}
