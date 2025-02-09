package cn.ant0n.aicodereview.prompt.template;

import cn.ant0n.aicodereview.prompt.enums.PromptTemplateEnums;
import cn.ant0n.aicodereview.prompt.model.PromptBuildParam;

public interface CodeReviewStrategyPrompt {

    /**
     * 获取提示词类型
     * @return 提示词类型
     */
    public PromptTemplateEnums getType();

    /**
     * 提示词构建
     * @return 提示词构建+上下文
     */
    public String executePromptBuild(PromptBuildParam param);

}
