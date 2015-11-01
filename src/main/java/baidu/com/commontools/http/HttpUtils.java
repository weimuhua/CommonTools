package baidu.com.commontools.http;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import baidu.com.commontools.LibConfig;

/**
 * Created by baidu on 15/11/1.
 * HttpUtils.
 * @author Wayne
 */
public class HttpUtils {

    private static final String TAG = "HttpUtils";
    private static final boolean DEBUG = LibConfig.DEBUG;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static final OkHttpClient sClient = new OkHttpClient();

    static {
        sClient.setConnectTimeout(20, TimeUnit.SECONDS);
    }

    /**
     * 同步方式执行网络请求
     * @param request http connect request
     * @return Response
     * @throws IOException
     */
    private static Response execute(Request request) throws IOException {
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
