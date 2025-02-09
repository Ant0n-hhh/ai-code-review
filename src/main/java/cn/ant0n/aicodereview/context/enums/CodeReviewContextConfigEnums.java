package cn.ant0n.aicodereview.context.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CodeReviewContextConfigEnums {

    FILE_TYPE("FILE_TYPE", "文件类型"),
    COMMIT_MESSAGE("COMMIT_MESSAGE", "提交信息"),
    CODE_DIFF("CODE_DIFF", "git diff记录"),
    FILE_CONTENT("FILE_CONTENT", "评审源代码文件内容");


    private final String code;
    private final String info;
}
