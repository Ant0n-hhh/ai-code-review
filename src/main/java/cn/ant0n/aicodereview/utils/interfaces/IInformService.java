package cn.ant0n.aicodereview.utils.interfaces;

import cn.ant0n.aicodereview.domain.model.request.InformRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

// https://open.feishu.cn/open-apis/bot/v2/hook/48db3d6e-b971-483c-a733-7c4816be2c46
public interface IInformService {
    @POST("open-apis/bot/v2/hook/{webhook}")
    @Headers({
            "Content-Type: application/json",
            "User-Agent: Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)"
    })
    Call<Void> inform(@Body InformRequest request, @Path("webhook")String webhook);
}
