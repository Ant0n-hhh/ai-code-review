package cn.ant0n.aicodereview.prompt.model;

import cn.ant0n.aicodereview.context.model.CodeReviewContext;
import cn.ant0n.aicodereview.prompt.enums.PromptEnhancedEnums;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PromptBuildParam {
    private String language;
    private CodeReviewContext context;
    private Set<PromptEnhancedEnums> enhancedEnums;
}
