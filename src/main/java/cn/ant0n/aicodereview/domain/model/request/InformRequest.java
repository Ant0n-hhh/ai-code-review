package cn.ant0n.aicodereview.domain.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InformRequest {
    private String msg_type = "text";
    private Content content;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Content{
        private String text;
    }


    public void build(String repository, String branch){
        content = new Content("AI评审完成,项目:" + repository + ",分支:" + branch + "请负责人查看");
    }
}
