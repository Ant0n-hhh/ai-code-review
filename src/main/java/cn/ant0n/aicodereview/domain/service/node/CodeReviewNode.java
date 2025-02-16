package cn.ant0n.aicodereview.domain.service.node;

import cn.ant0n.aicodereview.context.factory.CodeReviewContextBuildFactory;
import cn.ant0n.aicodereview.context.model.CodeReviewContext;
import cn.ant0n.aicodereview.domain.model.request.ChatCompletionsRequest;
import cn.ant0n.aicodereview.domain.model.response.ChatCompletionsResponse;
import cn.ant0n.aicodereview.domain.model.valobj.CodeReviewFactor;
import cn.ant0n.aicodereview.domain.model.valobj.CodeReviewResult;
import cn.ant0n.aicodereview.domain.model.valobj.CommitMessage;
import cn.ant0n.aicodereview.domain.service.AbstractCodeReviewSupport;
import cn.ant0n.aicodereview.domain.service.factory.DefaultCodeReviewStrategyFactory;
import cn.ant0n.aicodereview.framework.StrategyHandler;
import cn.ant0n.aicodereview.prompt.enums.PromptEnhancedEnums;
import cn.ant0n.aicodereview.prompt.factory.CodeReviewPromptBuildFactory;

import cn.ant0n.aicodereview.utils.interfaces.IChatModelService;
import cn.ant0n.sdk.core.data.text.ChatMessageText;
import cn.ant0n.sdk.core.data.text.impl.SystemMessageText;
import cn.ant0n.sdk.core.data.text.impl.UserMessageText;
import cn.ant0n.sdk.core.model.chat.ChatLanguageModel;
import cn.ant0n.sdk.core.model.chat.request.ChatCompletionsConfigContext;
import cn.ant0n.sdk.core.model.output.Response;
import cn.ant0n.sdk.qwen.QwenChatModel;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import java.util.Map;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CodeReviewNode extends AbstractCodeReviewSupport<CodeReviewFactor, DefaultCodeReviewStrategyFactory.DynamicContext, CodeReviewResult> {

    private PushCommentNode pushCommentNode = new PushCommentNode();


    @Override
    public CodeReviewResult apply(CodeReviewFactor requestParameter, DefaultCodeReviewStrategyFactory.DynamicContext dynamicContext) throws ExecutionException, InterruptedException, TimeoutException {
        System.out.println("触发AI评审节点");
        OkHttpClient client = new OkHttpClient.Builder().
                connectTimeout(1200, TimeUnit.SECONDS).
                readTimeout(1200, TimeUnit.SECONDS).
                writeTimeout(1200, TimeUnit.SECONDS).build();

        IChatModelService chatModelService = new Retrofit.Builder()
                .baseUrl(requestParameter.getBigModelBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build().create(IChatModelService.class);

        ChatLanguageModel chatLanguageModel = new QwenChatModel();
        ChatCompletionsConfigContext chatCompletionsConfigContext = new ChatCompletionsConfigContext();
        chatCompletionsConfigContext.setApiKey(requestParameter.getBigModelAccessKey());
        chatCompletionsConfigContext.setStream(false);
        chatCompletionsConfigContext.setModel("qwen-coder-plus");
        chatCompletionsConfigContext.setTopP((float)0.5);
        chatCompletionsConfigContext.setTemperature((float)0.5);

        CommitMessage commitMessage = dynamicContext.getCommitMessage();
        String prompt = CodeReviewPromptBuildFactory.build(dynamicContext.getType(), dynamicContext.getParam());
        for(Map.Entry<String, String> entry : commitMessage.getDiff().entrySet()){
            CodeReviewContext codeReviewContext = new CodeReviewContext();
            codeReviewContext.put("CODE_DIFF", entry.getValue());
            codeReviewContext.put("FILE_TYPE", entry.getKey());
            codeReviewContext.put("COMMIT_MESSAGE", commitMessage.getMessage());
            String context = CodeReviewContextBuildFactory.build(dynamicContext.getSet(), codeReviewContext);

            SystemMessageText systemMessageText = SystemMessageText.from(prompt);
            UserMessageText userMessageText = UserMessageText.from(context);

            Response<ChatMessageText> response = chatLanguageModel.generate(chatCompletionsConfigContext, systemMessageText, userMessageText);

            dynamicContext.getReviewResult().put(entry.getKey(), response.content().text());
        }
        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<CodeReviewFactor, DefaultCodeReviewStrategyFactory.DynamicContext, CodeReviewResult> get(CodeReviewFactor requestParameter, DefaultCodeReviewStrategyFactory.DynamicContext dynamicContext) {
        return pushCommentNode;
    }
}
