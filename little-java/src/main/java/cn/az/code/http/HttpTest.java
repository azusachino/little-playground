package cn.az.code.http;

import java.io.IOException;
import java.util.Objects;

import org.springframework.util.Assert;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author az
 * @since 2020-04-17
 */
@Slf4j
public class HttpTest {

    private final OkHttpClient httpClient = new OkHttpClient();

    public static void main(String[] args) {
        HttpTest ht = new HttpTest();
        log.info(ht.get("https://www.zhihu.com"));
        log.info(ht.get("https://cn.bing.com"));
    }

    String get(String url) {
        Request req = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Content-Type", "application/json;charset=utf-8")
                .build();

        try (Response res = httpClient.newCall(req).execute()) {
            Assert.state(res.body() != null, () -> "");
            return Objects.requireNonNull(res.body()).string();
        } catch (Exception e) {
            return null;
        }
    }

    void run(String url) {
        Request req = new Request.Builder()
                .url(url)
                .build();
        httpClient.newCall(req).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }

                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }

                    // System.out.println(responseBody.string());
                }
            }
        });
    }
}
