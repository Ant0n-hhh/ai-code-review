package cn.ant0n.aicodereview.domain.model.response;

import java.io.Serializable;
import java.util.List;

public class ChatCompletionsResponse implements Serializable {
    private static final long serialVersionUID = -7988151926241837899L;
    private String model;
    private List<Choice> choices;

    public static class Choice implements Serializable {
        private static final long serialVersionUID = -7988151926241837899L;
        private Integer index;
        private Message message;

        public static class Message implements Serializable {
            private static final long serialVersionUID = -7988151926241837899L;
            private String role = "user";
            private String content;

            public String getRole() {
                return role;
            }

            public String getContent() {
                return content;
            }

            public Message(String content) {
                this.content = content;

            }

            public Message() {
            }

            public void setRole(String role) {
                this.role = role;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }

        public Integer getIndex() {
            return index;
        }

        public Message getMessage() {
            return message;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        public void setMessage(Message message) {
            this.message = message;
        }
    }

    public ChatCompletionsResponse(String model) {
        this.model = model;
    }

    public ChatCompletionsResponse() {}

    public String getModel() {
        return model;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
}
