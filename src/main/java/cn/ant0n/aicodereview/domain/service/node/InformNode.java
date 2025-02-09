package cn.ant0n.aicodereview.domain.service.node;

import cn.ant0n.aicodereview.domain.model.request.InformRequest;
import cn.ant0n.aicodereview.domain.model.valobj.CodeReviewFactor;
import cn.ant0n.aicodereview.domain.model.valobj.CodeReviewResult;
import cn.ant0n.aicodereview.domain.service.AbstractCodeReviewSupport;
import cn.ant0n.aicodereview.domain.service.factory.DefaultCodeReviewStrategyFactory;
import cn.ant0n.aicodereview.framework.StrategyHandler;
import cn.ant0n.aicodereview.utils.interfaces.IChatModelService;
import cn.ant0n.aicodereview.utils.interfaces.IInformService;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class InformNode extends AbstractCodeReviewSupport<CodeReviewFactor, DefaultCodeReviewStrategyFactory.DynamicContext, CodeReviewResult> {


    @Override
    public CodeReviewResult apply(CodeReviewFactor requestParameter, DefaultCodeReviewStrategyFactory.DynamicContext dynamicContext) throws ExecutionException, InterruptedException, TimeoutException {
        System.out.println("触发消息通知节点");
        OkHttpClient client = new OkHttpClient.Builder().
                connectTimeout(1200, TimeUnit.SECONDS).
                readTimeout(1200, TimeUnit.SECONDS).
                writeTimeout(1200, TimeUnit.SECONDS).build();

        IInformService informService = new Retrofit.Builder()
                .baseUrl(requestParameter.getInformBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build().create(IInformService.class);

        InformRequest request = new InformRequest();
        request.build(requestParameter.getRepository(), requestParameter.getBranch());
        try{
            Call<Void> call = informService.inform(request, requestParameter.getWebhook());
            call.execute().body();
            System.out.println("消息已经发送!");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new CodeReviewResult();
    }

    @Override
    public StrategyHandler<CodeReviewFactor, DefaultCodeReviewStrategyFactory.DynamicContext, CodeReviewResult> get(CodeReviewFactor requestParameter, DefaultCodeReviewStrategyFactory.DynamicContext dynamicContext) {
        return null;
    }
}
