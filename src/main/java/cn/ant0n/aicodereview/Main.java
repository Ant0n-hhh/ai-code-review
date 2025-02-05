package cn.ant0n.aicodereview;

import cn.ant0n.aicodereview.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;

public class Main {
    public static void main(String[] args) {
        String content = HttpUtils.sendPost("围绕着俗手，妙手，巧手写一篇800字的高考语文作文");
        if(StringUtils.isBlank(content)){
            System.out.println("AI回答失败");
            return;
        }
        System.out.println("AI回答:" + content);
    }
}