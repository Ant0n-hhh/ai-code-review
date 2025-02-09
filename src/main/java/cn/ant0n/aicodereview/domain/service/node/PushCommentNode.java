package cn.ant0n.aicodereview.domain.service.node;

import cn.ant0n.aicodereview.domain.model.request.PushCommentRequest;
import cn.ant0n.aicodereview.domain.model.response.PushCommentResponse;
import cn.ant0n.aicodereview.domain.model.valobj.CodeReviewFactor;
import cn.ant0n.aicodereview.domain.model.valobj.CodeReviewResult;
import cn.ant0n.aicodereview.domain.service.AbstractCodeReviewSupport;
import cn.ant0n.aicodereview.domain.service.factory.DefaultCodeReviewStrategyFactory;
import cn.ant0n.aicodereview.framework.StrategyHandler;
import cn.ant0n.aicodereview.utils.interfaces.IGitRestService;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class PushCommentNode extends AbstractCodeReviewSupport<CodeReviewFactor, DefaultCodeReviewStrategyFactory.DynamicContext, CodeReviewResult> {

    private InformNode informNode = new InformNode();
    @Override
    public CodeReviewResult apply(CodeReviewFactor requestParameter, DefaultCodeReviewStrategyFactory.DynamicContext dynamicContext) throws ExecutionException, InterruptedException, TimeoutException {
        System.out.println("触发评审结果评论节点");
        OkHttpClient client = new OkHttpClient.Builder().
                connectTimeout(1200, TimeUnit.SECONDS).
                readTimeout(1200, TimeUnit.SECONDS).
                writeTimeout(1200, TimeUnit.SECONDS).build();
        IGitRestService gitRestService = new Retrofit.Builder()
                .baseUrl(requestParameter.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build().create(IGitRestService.class);

        String sha = dynamicContext.getCommitMessage().getSha();
        String owner = requestParameter.getOwner();
        String repository = requestParameter.getRepository();
        String authorization = "Bearer " + requestParameter.getAccessKey();
        Map<String, String> reviewResult = dynamicContext.getReviewResult();
        for(Map.Entry<String, String> entry : reviewResult.entrySet()) {
            PushCommentRequest request = new PushCommentRequest();
            request.setPath(entry.getKey());
            request.setBody(entry.getValue());
            request.setPosition(2);
            try{
                Call<PushCommentResponse> call = gitRestService.pushComment(owner, repository, sha, request, authorization);
                PushCommentResponse response = call.execute().body();
                assert response != null;
                System.out.println("AI评审结果URL:" + response.getHtml_url());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<CodeReviewFactor, DefaultCodeReviewStrategyFactory.DynamicContext, CodeReviewResult> get(CodeReviewFactor requestParameter, DefaultCodeReviewStrategyFactory.DynamicContext dynamicContext) {
        return informNode;
    }
}
