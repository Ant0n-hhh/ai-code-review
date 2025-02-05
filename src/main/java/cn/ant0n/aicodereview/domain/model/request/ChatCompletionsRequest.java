package cn.ant0n.aicodereview.domain.model.request;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChatCompletionsRequest implements Serializable {
    private static final long serialVersionUID = -7988151926241837899L;
    private String model = "qwen2.5:14b";
    private List<Message> messages;

    public static class Message implements Serializable {
        private static final long serialVersionUID = -7988151926241837899L;
        private String role = "user";
        private String content;

        public Message(String content) {
            this.content = content;
        }

    }

    public ChatCompletionsRequest() {
        messages = new ArrayList<Message>();
    }

    public String getModel() {
        return model;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void add(String content){
        messages.add(new Message(content));
    }
}
