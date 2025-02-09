package cn.ant0n.aicodereview.context.provider.impl;

import cn.ant0n.aicodereview.context.enums.CodeReviewContextConfigEnums;
import cn.ant0n.aicodereview.context.model.CodeReviewContext;
import cn.ant0n.aicodereview.context.provider.CodeReviewContextProvider;
import org.apache.commons.lang3.StringUtils;

public class CodeCommitMessageContextProvider implements CodeReviewContextProvider {


    @Override
    public CodeReviewContextConfigEnums getType() {
        return CodeReviewContextConfigEnums.COMMIT_MESSAGE;
    }

    @Override
    public String generate(CodeReviewContext context) {
        String commitMessage = context.getContexts().get("COMMIT_MESSAGE");
        if(StringUtils.isBlank(commitMessage)) return null;

        StringBuilder sb = new StringBuilder();
        sb.append("<提交信息>\n");
        sb.append("本次GIT提交，程序员的提交信息为:");
        sb.append(commitMessage);
        sb.append("\n");
        sb.append("<提交信息>\n");
        return sb.toString();
    }
}
