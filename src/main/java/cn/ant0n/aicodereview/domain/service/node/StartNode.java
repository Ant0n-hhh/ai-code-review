package cn.ant0n.aicodereview.domain.service.node;

import cn.ant0n.aicodereview.context.enums.CodeReviewContextConfigEnums;
import cn.ant0n.aicodereview.context.model.CodeReviewContext;
import cn.ant0n.aicodereview.domain.model.valobj.CodeReviewFactor;
import cn.ant0n.aicodereview.domain.model.valobj.CodeReviewResult;
import cn.ant0n.aicodereview.domain.service.AbstractCodeReviewSupport;
import cn.ant0n.aicodereview.domain.service.factory.DefaultCodeReviewStrategyFactory;
import cn.ant0n.aicodereview.framework.StrategyHandler;
import cn.ant0n.aicodereview.prompt.enums.PromptEnhancedEnums;
import cn.ant0n.aicodereview.prompt.enums.PromptTemplateEnums;
import cn.ant0n.aicodereview.prompt.model.PromptBuildParam;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class StartNode extends AbstractCodeReviewSupport<CodeReviewFactor, DefaultCodeReviewStrategyFactory.DynamicContext, CodeReviewResult> {

    private QueryCommitNode queryCommitNode = new QueryCommitNode();

    @Override
    public CodeReviewResult apply(CodeReviewFactor requestParameter, DefaultCodeReviewStrategyFactory.DynamicContext dynamicContext) throws ExecutionException, InterruptedException, TimeoutException {
        System.out.println("触发开始节点");
        List<String> promptOption = requestParameter.getPromptOption();
        List<String> contextOption = requestParameter.getContextOption();
        String templateType = requestParameter.getTemplateType();

        // 组建 PromptBuildParam
        PromptBuildParam param = new PromptBuildParam();
        param.setLanguage(requestParameter.getLanguage());
        Set<PromptEnhancedEnums> enhancedEnums = new HashSet<>();
        for (String promptOptionValue : promptOption) {
            enhancedEnums.add(PromptEnhancedEnums.valueOf(promptOptionValue));
        }
        param.setEnhancedEnums(enhancedEnums);
        dynamicContext.setParam(param);

        //组建 PromptTemplateEnums
        dynamicContext.setType(PromptTemplateEnums.valueOf(templateType));

        // 组建 CodeReviewContextConfigEnums
        Set<CodeReviewContextConfigEnums> codeReviewContextConfigEnums = new HashSet<>();
        for (String contextOptionValue : contextOption) {
            codeReviewContextConfigEnums.add(CodeReviewContextConfigEnums.valueOf(contextOptionValue));
        }
        dynamicContext.setSet(codeReviewContextConfigEnums);

        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<CodeReviewFactor, DefaultCodeReviewStrategyFactory.DynamicContext, CodeReviewResult> get(CodeReviewFactor requestParameter, DefaultCodeReviewStrategyFactory.DynamicContext dynamicContext) {
        return queryCommitNode;
    }
}
