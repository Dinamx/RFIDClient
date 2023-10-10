package com.mix.model;

public class ReaderContent {
    String content;
    String readerId;

    public ReaderContent(String content, String readerId) {
        this.content = content;
        this.readerId = readerId;
    }

    public ReaderContent() {

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReaderId() {
        return readerId;
    }

    public void setReaderId(String readerId) {
        this.readerId = readerId;
    }
}
