package cn.ant0n.aicodereview.domain.service.node;

import cn.ant0n.aicodereview.domain.model.response.SingleCommitResponse;
import cn.ant0n.aicodereview.domain.model.valobj.CodeReviewFactor;
import cn.ant0n.aicodereview.domain.model.valobj.CodeReviewResult;
import cn.ant0n.aicodereview.domain.model.valobj.CommitMessage;
import cn.ant0n.aicodereview.domain.service.AbstractCodeReviewSupport;
import cn.ant0n.aicodereview.domain.service.factory.DefaultCodeReviewStrategyFactory;
import cn.ant0n.aicodereview.framework.StrategyHandler;
import cn.ant0n.aicodereview.utils.interfaces.IGitRestService;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.StringUtils;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class QueryCommitNode extends AbstractCodeReviewSupport<CodeReviewFactor, DefaultCodeReviewStrategyFactory.DynamicContext, CodeReviewResult> {


    private CodeReviewNode codeReviewNode = new CodeReviewNode();

    @Override
    public CodeReviewResult apply(CodeReviewFactor requestParameter, DefaultCodeReviewStrategyFactory.DynamicContext dynamicContext) throws ExecutionException, InterruptedException, TimeoutException {
        System.out.println("触发提交查看节点");
        OkHttpClient client = new OkHttpClient.Builder().
                connectTimeout(1200, TimeUnit.SECONDS).
                readTimeout(1200, TimeUnit.SECONDS).
                writeTimeout(1200, TimeUnit.SECONDS).build();
        IGitRestService gitRestService = new Retrofit.Builder()
                .baseUrl(requestParameter.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build().create(IGitRestService.class);

        String owner = requestParameter.getOwner();
        String repository = requestParameter.getRepository();
        String branch = requestParameter.getBranch();
        String authorization = "Bearer " + requestParameter.getAccessKey();
        try{
            Call<SingleCommitResponse> call = gitRestService.getDiff(owner, repository, branch, authorization);
            SingleCommitResponse response = call.execute().body();
            CommitMessage commitMessage = new CommitMessage();
            commitMessage.setSha(response.getSha());
            commitMessage.setMessage(response.getCommit().getMessage());
            commitMessage.setCommitEmail(response.getCommit().getCommitter().getEmail());
            commitMessage.setCommiterName(response.getCommit().getCommitter().getName());
            commitMessage.setCommitDate(response.getCommit().getCommitter().getDate());

            for(SingleCommitResponse.Files file : response.getFiles()) {
                commitMessage.put(file.getFilename(), file.getPatch());
            }
            dynamicContext.setCommitMessage(commitMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<CodeReviewFactor, DefaultCodeReviewStrategyFactory.DynamicContext, CodeReviewResult> get(CodeReviewFactor requestParameter, DefaultCodeReviewStrategyFactory.DynamicContext dynamicContext) {
        return codeReviewNode;
    }
}
