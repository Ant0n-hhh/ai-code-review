package cn.ant0n.aicodereview.prompt.template.impl;

import cn.ant0n.aicodereview.prompt.enums.PromptEnhancedEnums;
import cn.ant0n.aicodereview.prompt.enums.PromptTemplateEnums;
import cn.ant0n.aicodereview.prompt.model.PromptBuildParam;
import cn.ant0n.aicodereview.prompt.template.CodeReviewStrategyPrompt;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;


public class DefaultStrategyPrompt implements CodeReviewStrategyPrompt {

    private String ROLE = "\n你是一个经验丰富的%s代码审查专家，具备深度技术洞察力，能够对代码进行全面评审，确保代码的高性能、可维护性和安全性，具备以下能力和职责:\n";
    private String SKILL = "\n语言理解：你能够理解和分析多种编程语言，包括但不限于Python, Java, C++, JavaScript等，但更擅长%s语言。 \n" +
            "缺陷检测：你需要能够识别代码中的潜在缺陷，包括逻辑错误、内存泄漏、安全漏洞、边界处理、注释完整、魔法值、if判断和多重嵌套、空指针、异常处理、函数方法长度不超过100行等。 \n" +
            "代码优化：你应当提供代码优化的建议，以改善性能和可扩展性，同时保持代码的可读性和维护性。\n" +
            " 最佳实践：你应当确保代码遵循当前的最佳编程实践和行业阿里巴巴代码规范标准。 \n" +
            " 反馈详细：你的反馈需要详细且具体，包括问题所在的代码行号、问题描述、可能的影响，以及推荐的解决方案。 \n" +
            " 代码理解：你能够理解用户提供的Git Diff格式的变更字符串，每一行的开头如果是'+'号，表示该行是新增变动的代码，而'-'减号开头的行则表示被替换或删除的代码，同时用户会将原始代码文件作为上下文给你。\n" +
            "业务分析：你能够理解代码的业务和逻辑和目的，识别代码的意图，识别其特定在上下文中的作用和限制。\n";
    private final String TASK = "\n以下是你的具体任务： \n" +
            "1、接收用户提交的Git Diff格式的变更代码片段和代码文件内容，深入分析变更代码的影响并进行全面的审查。\n" +
            " 2、识别代码中的任何不规范、不高效或错误的编码模式。 \n" +
            " 3、提供清晰的、步骤性的改进建议，以及必要的代码示例。 \n" +
            " 4、请确保你的反馈是结构化的，包含了问题分类、影响评估、解决方案以及相关代码示例。你的目标是提供有助于开发者快速理解和应用的建议，以促进代码质量的提升。 \n" +
            " 5、反馈结果中需要包含本次重点评审内容的CheckList，非重点评审的CheckList强制不需要返回结果说明，只返回重点评审的CheckList，然后针对你认为通过的内容用emoji字符中的✅表示，不通过的内容使用emoji字符中的❌表示。\n";


    @Override
    public PromptTemplateEnums getType() {
        return PromptTemplateEnums.DEFAULT_PROMPT;
    }

    @Override
    public String executePromptBuild(PromptBuildParam param) {
        if(StringUtils.isBlank(param.getLanguage())){
            throw new RuntimeException("请设置关注的编程语言");
        }
        Set<PromptEnhancedEnums> enhancedEnums = param.getEnhancedEnums();
        StringBuilder builder = new StringBuilder();
        // 核心提示词部分构建
        if(enhancedEnums.contains(PromptEnhancedEnums.ROLE)){
            builder.append("<角色>");
            builder.append(String.format(ROLE, param.getLanguage()));
            builder.append("</角色>");
            builder.append("\n");
        }
        if(enhancedEnums.contains(PromptEnhancedEnums.SKILL)){
            builder.append("<技能>");
            builder.append(String.format(SKILL, param.getLanguage()));
            builder.append("</技能>");
            builder.append("\n");
        }
        if(enhancedEnums.contains(PromptEnhancedEnums.TASK)){
            builder.append("<任务>");
            builder.append(TASK);
            builder.append("</任务>");
        }
        return builder.toString();
    }


}
