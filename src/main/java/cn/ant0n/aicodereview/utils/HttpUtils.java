package cn.ant0n.aicodereview.utils;

import cn.ant0n.aicodereview.domain.model.request.ChatCompletionsRequest;
import cn.ant0n.aicodereview.domain.model.response.ChatCompletionsResponse;
import cn.ant0n.aicodereview.utils.interfaces.IChatModelService;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HttpUtils {

    public static String sendPost(String content){
        ChatCompletionsRequest request = new ChatCompletionsRequest();
        request.add(content);
        IChatModelService chatModelService = new Retrofit.Builder()
                .baseUrl("http://117.72.47.33:6006/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(IChatModelService.class);


        try{
            Call<ChatCompletionsResponse> call = chatModelService.completions(request);
            ChatCompletionsResponse response = call.execute().body();
            assert response != null;
            return response.getChoices().get(0).getMessage().getContent();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
