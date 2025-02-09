package cn.ant0n.aicodereview.prompt.factory;


import cn.ant0n.aicodereview.prompt.enums.PromptTemplateEnums;
import cn.ant0n.aicodereview.prompt.model.PromptBuildParam;
import cn.ant0n.aicodereview.prompt.template.CodeReviewStrategyPrompt;
import cn.ant0n.aicodereview.prompt.template.impl.BusinessDevelopmentStrategyPrompt;
import cn.ant0n.aicodereview.prompt.template.impl.DefaultStrategyPrompt;


import java.util.HashMap;
import java.util.Map;


public class CodeReviewPromptBuildFactory {

    private static Map<PromptTemplateEnums, CodeReviewStrategyPrompt> REGISTER_MAP = new HashMap<>();

    static {
        REGISTER_MAP.put(PromptTemplateEnums.DEFAULT_PROMPT, new DefaultStrategyPrompt());
        REGISTER_MAP.put(PromptTemplateEnums.BUSINESS_DEVELOPMENT_PROMPT, new BusinessDevelopmentStrategyPrompt());
    }

    public static String build(PromptTemplateEnums promptTemplateEnums, PromptBuildParam param){
        CodeReviewStrategyPrompt prompt = REGISTER_MAP.get(promptTemplateEnums);
        if(prompt == null){
            throw new RuntimeException("暂时不支持该提示词模板");
        }
        return prompt.executePromptBuild(param);
    }
}
