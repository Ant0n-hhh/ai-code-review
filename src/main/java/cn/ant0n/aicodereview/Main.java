package cn.ant0n.aicodereview;


import cn.ant0n.aicodereview.domain.model.valobj.CodeReviewFactor;
import cn.ant0n.aicodereview.domain.model.valobj.CodeReviewResult;
import cn.ant0n.aicodereview.domain.service.factory.DefaultCodeReviewStrategyFactory;
import cn.ant0n.aicodereview.domain.service.node.StartNode;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        StartNode startNode = new StartNode();
        CodeReviewFactor codeReviewFactor = new CodeReviewFactor();
        codeReviewFactor.setOwner(getEnv("OWNER"));
        codeReviewFactor.setRepository(getEnv("REPOSITORY"));
        codeReviewFactor.setBranch("master");
        codeReviewFactor.setAccessKey(getEnv("GIT"));
        codeReviewFactor.setBaseUrl(getEnv("GIT_BASE_URL"));
        codeReviewFactor.setBigModelAccessKey(getEnv("BIG_MODEL"));
        codeReviewFactor.setBigModelBaseUrl(getEnv("BIG_MODEL_BASE_URL"));
        codeReviewFactor.setLanguage(getEnv("LANGUAGE"));
        codeReviewFactor.setTemplateType(getEnv("TEMPLATE"));
        String prompt = getEnv("P_OPTION");
        List<String> promptOption = Arrays.asList(prompt.split(","));
        codeReviewFactor.setPromptOption(promptOption);
        codeReviewFactor.setWebhook(getEnv("WEB_HOOK"));
        codeReviewFactor.setInformBaseUrl(getEnv("INFORM_BASE_URL"));

        String context = getEnv("C_OPTION");
        List<String> contextOption = Arrays.asList(context.split(","));
        codeReviewFactor.setContextOption(contextOption);

        try{
            CodeReviewResult codeReviewResult = startNode.apply(codeReviewFactor, new DefaultCodeReviewStrategyFactory.DynamicContext());
            if(codeReviewResult != null){
                System.out.println("流程结束");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.exit(0);
    }

    private static String getEnv(String key) {
        String value = System.getenv(key);
        if (null == value || value.isEmpty()) {
            throw new RuntimeException("该值:" + key + "为空，请先设置");
        }
        return value;
    }

}