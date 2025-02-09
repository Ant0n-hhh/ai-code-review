package cn.ant0n.aicodereview.domain.service.factory;

import cn.ant0n.aicodereview.context.enums.CodeReviewContextConfigEnums;
import cn.ant0n.aicodereview.context.model.CodeReviewContext;
import cn.ant0n.aicodereview.domain.model.valobj.CommitMessage;
import cn.ant0n.aicodereview.prompt.enums.PromptTemplateEnums;
import cn.ant0n.aicodereview.prompt.model.PromptBuildParam;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DefaultCodeReviewStrategyFactory {



    @Data
    public static class DynamicContext{
        private CommitMessage commitMessage;
        private PromptBuildParam param;
        private PromptTemplateEnums type;
        private Set<CodeReviewContextConfigEnums> set;

        private Map<String, String> reviewResult = new HashMap<>();


        public void put(String filename, String review){
            reviewResult.put(filename, review);
        }
    }
}
