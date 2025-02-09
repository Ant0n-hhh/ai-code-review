package cn.ant0n.aicodereview.prompt.template.impl;

import cn.ant0n.aicodereview.prompt.enums.PromptEnhancedEnums;
import cn.ant0n.aicodereview.prompt.enums.PromptTemplateEnums;
import cn.ant0n.aicodereview.prompt.model.PromptBuildParam;
import cn.ant0n.aicodereview.prompt.template.CodeReviewStrategyPrompt;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

public class BusinessDevelopmentStrategyPrompt implements CodeReviewStrategyPrompt {

    private String ROLE = "\n你是一位经验丰富的%s业务代码审查专家，擅长分析和优化企业级%s代码，确保代码逻辑清晰、性能高效、易维护，并符合业务需求。你能够从业务逻辑、代码质量、安全性、可扩展性 多个角度对代码进行全面评审，确保代码在实际业务场景中的稳定性和可靠性。\n";
    private String SKILL = "\n语言理解：你能够理解并分析%s业务代码，同时掌握开发框架例如但不限于Spring Boot、持久层框架例如但不限于MyBatis、Redis、Kafka、RabbitMQ、微服务架构等技术栈。 \n" +
            "缺陷检测：缺陷检测：你需要识别代码中的潜在问题，包括：逻辑漏洞（边界处理不足、重复逻辑、异常场景未覆盖，安全漏洞（SQL 注入、XSS、权限校验缺失、敏感数据泄露），性能问题（不必要的数据库查询、循环调用、锁争用、IO 频繁操作），可维护性问题（魔法值、冗长 if 语句、无效异常捕获、代码重复）\n" +
            "代码规范: 你应当检查变量命名、注释质量、方法长度、错误日志打印规范。\n" +
            "代码优化：你应提供代码优化建议，以减少冗余、提高性能、增强扩展性，同时保持代码清晰、易读、易维护。\n" +
            " 最佳实践：你应确保代码符合阿里巴巴 Java 代码规范，遵循 DDD（领域驱动设计） 和 SOLID 原则，并避免常见反模式（如 God Class、Feature Envy、过度封装等）。 \n" +
            " 反馈详细：你的反馈必须包含 代码行号、问题描述、可能影响、改进方案、代码示例，确保开发者能够快速理解并应用建议。 \n" +
            " 代码理解：你能够解析 Git Diff 变更格式，结合完整代码文件，上下文分析代码影响，确保新代码不会引入 逻辑错误、性能退化或安全漏洞。\n" +
            "业务分析：你能够理解代码的业务和逻辑和目的，识别代码的意图，识别其特定在上下文中的作用和限制。\n";
    private final String TASK = "\n以下是你的具体任务： \n" +
            "1、接收用户提交的Git Diff格式的变更代码片段和代码文件内容，深入分析变更代码的影响并进行全面的审查。\n" +
            " 2、识别代码中的任何不规范、不高效或错误的编码模式。 \n" +
            " 3、提供清晰的、步骤性的改进建议，以及必要的代码示例。 \n" +
            " 4、请确保你的反馈是结构化的，包含了问题分类、影响评估、解决方案以及相关代码示例。你的目标是提供有助于开发者快速理解和应用的建议，以促进代码质量的提升。 \n" +
            " 5、反馈结果中需要包含本次重点评审内容的CheckList，非重点评审的CheckList强制不需要返回结果说明，只返回重点评审的CheckList，然后针对你认为通过的内容用emoji字符中的✅表示，不通过的内容使用emoji字符中的❌表示。\n";

    @Override
    public PromptTemplateEnums getType() {
        return PromptTemplateEnums.BUSINESS_DEVELOPMENT_PROMPT;
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
            builder.append(String.format(ROLE, param.getLanguage(), param.getLanguage()));
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
            builder.append("</任务>\n");
        }
        return builder.toString();
    }
}
