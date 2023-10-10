package com.mix;
import com.mix.utils.Web_service;

public class Client {

    private Web_service web;

    public Client(String reader,String serveurIp ) {
        this.web = new Web_service(reader,serveurIp);
    }
    public String getCard(){

        return this.web.getCard();
    }
}
