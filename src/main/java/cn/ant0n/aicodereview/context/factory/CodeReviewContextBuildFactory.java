package cn.ant0n.aicodereview.context.factory;

import cn.ant0n.aicodereview.context.enums.CodeReviewContextConfigEnums;
import cn.ant0n.aicodereview.context.model.CodeReviewContext;
import cn.ant0n.aicodereview.context.provider.CodeReviewContextProvider;
import cn.ant0n.aicodereview.context.provider.impl.CodeCommitMessageContextProvider;
import cn.ant0n.aicodereview.context.provider.impl.CodeFileTypeContextProvider;
import cn.ant0n.aicodereview.context.provider.impl.CodeGitDiffContextProvider;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CodeReviewContextBuildFactory {
    private static Map<CodeReviewContextConfigEnums, CodeReviewContextProvider> REGISTER_MAP = new HashMap<>();

    static {
        REGISTER_MAP.put(CodeReviewContextConfigEnums.COMMIT_MESSAGE, new CodeCommitMessageContextProvider());
        REGISTER_MAP.put(CodeReviewContextConfigEnums.FILE_TYPE, new CodeFileTypeContextProvider());
        REGISTER_MAP.put(CodeReviewContextConfigEnums.CODE_DIFF, new CodeGitDiffContextProvider());
    }

    public static String build(Set<CodeReviewContextConfigEnums> set, CodeReviewContext context){
        StringBuilder sb = new StringBuilder();
        if(!set.contains(CodeReviewContextConfigEnums.CODE_DIFF)){
            throw new RuntimeException("未添加diff记录");
        }
        CodeReviewContextProvider provider = REGISTER_MAP.get(CodeReviewContextConfigEnums.CODE_DIFF);
        sb.append(provider.generate(context));
        for (CodeReviewContextConfigEnums key : set) {
            if(key.equals(CodeReviewContextConfigEnums.CODE_DIFF)) continue;
            provider = REGISTER_MAP.get(key);
            if (provider == null) {
                throw new RuntimeException("暂时不支持该上下文构建器");
            }
            sb.append(provider.generate(context));
        }
        return sb.toString();
    }
}
