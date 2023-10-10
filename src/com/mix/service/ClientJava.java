package com.mix.service;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class    ClientJava {
//    public static void main(String[] args) {
//        ClientConfig config = new ClientConfig();
//
//        Client client = ClientBuilder.newClient((Configuration) config);//Creating an Instance of a Client
//
//        WebTarget target = client.target(getBaseURI());// Creating a WebTarget using the URI of the targeted web resource:
//
//        String response = target.path("aleatoire").path("oper1").request().accept(MediaType.TEXT_PLAIN).get(String.class);
//
//        System.out.println(response);
//        String response2 = target.path("aleatoire").path("oper2").request().accept(MediaType.TEXT_PLAIN).get(String.class);
//
//        System.out.println(response2);
//
//    }

    private static URI getBaseURI() {//chemin de l'application
        return UriBuilder.fromUri("http://localhost:8082/WSalea").build();
    }
}