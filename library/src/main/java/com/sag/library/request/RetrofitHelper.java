package com.sag.library.request;

import android.content.Context;

import com.sag.library.api.Constant;
import com.sag.library.util.DESUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Retrofit网络访问
 * <p>
 * Created by SAG on 2017/6/12
 */

class RetrofitHelper implements Helper {

    private static ApiService mService;

    public static void init(Context context) {
        //初始化OkHttp，为OkHttp设置拦截器，拦截请求到的字符串进行解密、以及打印日志等操作
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
            Response response = chain.proceed(chain.request());
            ResponseBody responseBody = response.body();
            String result = responseBody.string();

            Response newResponse;
            if (response.request().header("key").equals(key)) {
                ResponseBody newResponseBody = ResponseBody.create(responseBody.contentType(), message);
                newResponse = response.newBuilder().body(newResponseBody).build();
            } else {
                ResponseBody newResponseBody = ResponseBody.create(responseBody.contentType(), result);
                newResponse = response.newBuilder().body(newResponseBody).build();
            }

            return newResponse;
        }).build();

        //初始化Retrofit，拼接http请求字符串主干，设置OkHttp，以及数据转化工具
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.baseURL)
                .client(client)
                .addConverterFactory(new ConverterFactory())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        //创建网络发送器，RetrofitHelper初始化完成
        mService = retrofit.create(ApiService.class);
    }

    @Override
    public void post(String method, HashMap<String, Object> parameters, ClientHelper subscriber) {
        Subscription subscription = mService.post(method, method, transitionObj(parameters))
                .delay(500, TimeUnit.MILLISECONDS)
                .timeout(30000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())//线程调度，在支线程中访问网络
                .observeOn(AndroidSchedulers.mainThread())//线程调度，将网络请求到的数据合并到主线程中
                .subscribe(subscriber);
        subscriber.setSubscription(subscription);
    }

    @Override
    public void get(String method, HashMap<String, Object> parameters, ClientHelper subscriber) {
        Subscription subscription = mService.get(method, method, transitionObj(parameters))
                .delay(500, TimeUnit.MILLISECONDS)
                .timeout(30000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())//线程调度，在支线程中访问网络
                .observeOn(AndroidSchedulers.mainThread())//线程调度，将网络请求到的数据合并到主线程中
                .subscribe(subscriber);
        subscriber.setSubscription(subscription);
    }

    private Map<String, Object> transitionObj(HashMap<String, Object> object) {
        HashMap<String, Object> map;
        if (Constant.isEncrypt) {
            switch (Constant.Encrypt_TYPE) {
                case Constant.Encrypt_ONE:
                    map = object;
                    Set<String> keys = map.keySet();
                    Iterator<String> iterator = keys.iterator();
                    while (iterator.hasNext()) {
                        String key = iterator.next();
                        Object value = map.get(key);
                        if (value instanceof String) {
                            map.replace(key, DESUtils.encryptAsDoNet((String) map.get(key)));
                        }
                    }
                    break;
                case Constant.Encrypt_TWO:
                    map = new HashMap<>();
                    String json = ClientHelper.json.toJson(object);
                    map.put(Constant.Encrypt_KEY, DESUtils.encryptAsDoNet(json));
                    break;
            }
        } else {
            map = object;
        }
        return map;
    }

    interface ApiService {

        @FormUrlEncoded
        @POST()
        Observable<String> post(@Header("key") String key, @Url String method, @FieldMap Map<String, Object> fieldMap);

        @GET()
        Observable<String> get(@Header("key") String key, @Url String method, @QueryMap Map<String, Object> fieldMap);

    }

    static class ConverterFactory extends Converter.Factory {

        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            return new StringResponseConverter();
        }

        @Override
        public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
            return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
        }

        private class StringResponseConverter implements Converter<ResponseBody, String> {

            @Override
            public String convert(ResponseBody value) throws IOException {
                String result = value.string();
                return result;
            }
        }

    }

    private static final String key = "";

    private static final String message = "";

}
