package cn.ant0n.aicodereview.utils.interfaces;

import cn.ant0n.aicodereview.domain.model.request.ChatCompletionsRequest;
import cn.ant0n.aicodereview.domain.model.response.ChatCompletionsResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IChatModelService {

    @POST("compatible-mode/v1/chat/completions")
    @Headers({
            "Content-Type: application/json",
            "User-Agent: Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)"
    })
    Call<ChatCompletionsResponse> completions(@Body ChatCompletionsRequest request, @Header("Authorization")String authorization);
}
