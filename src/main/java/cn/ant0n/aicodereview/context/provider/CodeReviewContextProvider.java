package cn.ant0n.aicodereview.context.provider;

import cn.ant0n.aicodereview.context.enums.CodeReviewContextConfigEnums;
import cn.ant0n.aicodereview.context.model.CodeReviewContext;
import cn.ant0n.aicodereview.context.model.CodeReviewSwitchConfig;

public interface CodeReviewContextProvider {

    CodeReviewContextConfigEnums getType();

    String generate(CodeReviewContext context);
}
