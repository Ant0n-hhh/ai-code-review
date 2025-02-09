package cn.ant0n.aicodereview.domain.service;

import cn.ant0n.aicodereview.domain.model.valobj.CodeReviewResult;
import cn.ant0n.aicodereview.domain.service.factory.DefaultCodeReviewStrategyFactory;
import cn.ant0n.aicodereview.framework.AbstractStrategyRouter;

public abstract class AbstractCodeReviewSupport<CodeReviewFactor, DynamicContext, CodeReviewResult> extends AbstractStrategyRouter<CodeReviewFactor, DefaultCodeReviewStrategyFactory.DynamicContext, CodeReviewResult> {
}
