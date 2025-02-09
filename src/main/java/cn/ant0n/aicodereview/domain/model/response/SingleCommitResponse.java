package cn.ant0n.aicodereview.domain.model.response;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SingleCommitResponse {
    private String sha;
    private Commit commit;
    private List<Files> files;


    @Data
    public static class Commit{
        private String message;
        private Committer committer;

        @Data
        public static class Committer{
            private String name;
            private String email;
            private Date date;
        }
    }

    @Data
    public static class Files{
        private String filename;
        private String patch;
    }
}
