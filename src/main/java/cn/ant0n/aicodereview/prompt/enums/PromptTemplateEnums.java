package cn.ant0n.aicodereview.prompt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PromptTemplateEnums {
    DEFAULT_PROMPT("DEFAULT_PROMPT", "默认提示词,适合大部分普通业务"),

    BUSINESS_DEVELOPMENT_PROMPT("BUSINESS_DEVELOPMENT_PROMPT", "专注业务开发的提示词"),

    MACHINE_LEARNING_PROMPT("MACHINE_LEARNING_PROMPT", "专注机器/深度学习的提示词");

    private final String code;
    private final String info;
}
