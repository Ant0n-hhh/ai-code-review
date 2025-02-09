package cn.ant0n.aicodereview.context.model;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class CodeReviewContext {
    private Map<String, String> contexts;

    public CodeReviewContext() {
        contexts = new HashMap<>();
    }

    public void put(String key, String context){
        contexts.put(key, context);
    }
}
