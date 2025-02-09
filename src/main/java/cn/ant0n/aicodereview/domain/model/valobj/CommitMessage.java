package cn.ant0n.aicodereview.domain.model.valobj;

import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class CommitMessage {
    private String sha;
    private String message;
    private String commiterName;
    private String commitEmail;
    private Date commitDate;
    private Map<String, String> diff = new HashMap<>();

    public void put(String filename, String patch){
        diff.put(filename, patch);
    }
}
