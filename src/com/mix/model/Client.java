package com.mix.model;
import com.mix.utils.IP_Address;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Client {
    String ipAdress;
    String readerId;
    String content;

    public Client() {
    }

    public Client(String readerId) {
        this.ipAdress = new IP_Address().getIP();
        this.readerId = readerId;
    }
    public Client(String ipAdress, String readerId) {
        this.ipAdress = ipAdress;
        this.readerId = readerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public String getReaderId() {
        return readerId;
    }

    public void setReaderId(String readerId) {
        this.readerId = readerId;
    }



}
