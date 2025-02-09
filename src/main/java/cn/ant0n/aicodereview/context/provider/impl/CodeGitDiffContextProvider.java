package cn.ant0n.aicodereview.context.provider.impl;

import cn.ant0n.aicodereview.context.enums.CodeReviewContextConfigEnums;
import cn.ant0n.aicodereview.context.model.CodeReviewContext;
import cn.ant0n.aicodereview.context.provider.CodeReviewContextProvider;
import org.apache.commons.lang3.StringUtils;

public class CodeGitDiffContextProvider implements CodeReviewContextProvider {
    @Override
    public CodeReviewContextConfigEnums getType() {
        return CodeReviewContextConfigEnums.CODE_DIFF;
    }

    @Override
    public String generate(CodeReviewContext context) {
        String diff = context.getContexts().get("CODE_DIFF");
        if(StringUtils.isBlank(diff)) return null;

        StringBuilder sb = new StringBuilder();
        sb.append("<GIT DIFF变更记录>\n");
        sb.append(diff);
        sb.append("\n");
        sb.append("<GIT DIFF变更记录>\n");
        return sb.toString();
    }
}
