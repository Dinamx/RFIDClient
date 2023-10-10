package com.mix.service;

import com.mix.model.Client;
import com.mix.model.ReaderContent;

public class ClientService {
    private String serverIp;


    public ClientService(String serverIp) {
        this.serverIp = serverIp;
    }

    private Client c = new Client("192.1.1","Lecteur1");
    public ReaderContent getCardId(Client c){
        ReaderContent rc = new ReaderContent();
        rc.setContent("");
        rc.setReaderId("");
        return rc;
    }
}
