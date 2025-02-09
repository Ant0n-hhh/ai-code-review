package cn.ant0n.aicodereview.domain.model.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatCompletionsRequest{

    private String model = "qwen-coder-plus";
    private List<Message> messages = new ArrayList<>();

    @Data
    @NoArgsConstructor
    public static class Message{

        private String role;
        private String content;

        public Message(String role, String content) {
            this.content = content;
            this.role = role;
        }
    }

    public void add(String role, String content){
        messages.add(new Message(role, content));
    }
}
