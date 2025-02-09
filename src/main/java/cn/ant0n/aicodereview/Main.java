package cn.ant0n.aicodereview;


import cn.ant0n.aicodereview.domain.model.valobj.CodeReviewFactor;
import cn.ant0n.aicodereview.domain.model.valobj.CodeReviewResult;
import cn.ant0n.aicodereview.domain.service.factory.DefaultCodeReviewStrategyFactory;
import cn.ant0n.aicodereview.domain.service.node.StartNode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        StartNode startNode = new StartNode();
        CodeReviewFactor codeReviewFactor = new CodeReviewFactor();
        codeReviewFactor.setOwner("Ant0n-hhh");
        codeReviewFactor.setRepository("deepseek-code-review");
        codeReviewFactor.setBranch("master");
        codeReviewFactor.setAccessKey("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");
        codeReviewFactor.setBaseUrl("https://api.github.com/");
        codeReviewFactor.setBigModelAccessKey("sk-64dc70df479a4cbab0f1564e67229c7d");
        codeReviewFactor.setBigModelBaseUrl("https://dashscope.aliyuncs.com/");
        codeReviewFactor.setLanguage("Java");
        codeReviewFactor.setTemplateType("BUSINESS_DEVELOPMENT_PROMPT");
        List<String> promptOption = new ArrayList<>();
        promptOption.add("ROLE");
        promptOption.add("SKILL");
        promptOption.add("TASK");
        codeReviewFactor.setPromptOption(promptOption);
        codeReviewFactor.setWebhook("48db3d6e-b971-483c-a733-7c4816be2c46");
        codeReviewFactor.setInformBaseUrl("https://open.feishu.cn/");

        List<String> contextOption =new ArrayList<>();
        contextOption.add("FILE_TYPE");
        contextOption.add("COMMIT_MESSAGE");
        contextOption.add("CODE_DIFF");

        codeReviewFactor.setContextOption(contextOption);
        CodeReviewResult codeReviewResult = startNode.apply(codeReviewFactor, new DefaultCodeReviewStrategyFactory.DynamicContext());
        if(codeReviewResult != null){
            System.out.println("流程结束");
        }
        System.exit(0);
    }
}