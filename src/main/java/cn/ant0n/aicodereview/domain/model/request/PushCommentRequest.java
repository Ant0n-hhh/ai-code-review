package cn.ant0n.aicodereview.domain.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PushCommentRequest {
    private String body;
    private String path;
    private Integer position;
}
