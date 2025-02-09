package cn.ant0n.aicodereview.domain.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CodeReviewFactor {
    private String owner;
    private String repository;
    private String branch;
    private String accessKey;
    private String baseUrl;
    private String bigModelBaseUrl;
    private String bigModelAccessKey;
    private String webhook;
    private String informBaseUrl;

    private List<String> promptOption;
    private List<String> contextOption;
    private String language;
    private String templateType;
}
