package cn.ant0n.aicodereview.context.provider.impl;

import cn.ant0n.aicodereview.context.enums.CodeReviewContextConfigEnums;
import cn.ant0n.aicodereview.context.model.CodeReviewContext;
import cn.ant0n.aicodereview.context.provider.CodeReviewContextProvider;

public class CodeFileTypeContextProvider implements CodeReviewContextProvider {

    @Override
    public CodeReviewContextConfigEnums getType() {
        return CodeReviewContextConfigEnums.FILE_TYPE;
    }

    @Override
    public String generate(CodeReviewContext context) {
        String fileType = context.getContexts().get("FILE_TYPE");
        StringBuilder sb = new StringBuilder();
        sb.append("<特定文件类型说明>\n");
        if(fileType.endsWith(".java")){
            sb.append("当前评审文件为Java格式文件\n");
        }
        else if(fileType.endsWith(".xml")){
            sb.append("当前评审文件为XML格式文件，涉及Mybatis框架的SQL语句，请查看是否会存在慢查询等问题\n");
        }
        else if(fileType.endsWith(".html")){
            sb.append("当前评审文件为HTML格式文件， 请用前端开发的眼光评审该代码\n");
        }
        sb.append("<特定文件类型说明>\n");
        return sb.toString();
    }
}
