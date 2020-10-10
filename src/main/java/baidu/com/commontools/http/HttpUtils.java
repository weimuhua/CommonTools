package baidu.com.commontools.http;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * @author Wayne
 */
public class HttpUtils {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static final OkHttpClient sClient = new OkHttpClient();

    /**
     * 同步方式执行网络请求
     *
     * @param request http connect request
     * @return Response
     * @throws IOException
     */
    public static Response execute(Request request) throws IOException {
        return sClient.newCall(request).execute();
    }


    public static String commonGet(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        return execute(request).body().string();
    }

    public static String commonPost(String url, String body) throws IOException {
        RequestBody requestBody = RequestBody.create(JSON, body);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        return execute(request).body().string();
    }
}
