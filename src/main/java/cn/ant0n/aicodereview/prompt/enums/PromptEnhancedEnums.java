package cn.ant0n.aicodereview.prompt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PromptEnhancedEnums {
    ROLE("ROLE", "角色"),
    SKILL("SKILL", "技能"),
    TASK("TASK", "任务");

    private final String code;
    private final String info;

}
