package com.mix.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mix.model.Client;
import com.mix.model.ReaderContent;
import org.glassfish.jersey.client.ClientProperties;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Web_service {

    private static final int TIMEOUT_MS = 5000; // 10 seconds
    String serveur_ip;
    String reader;

    public Web_service(String reader, String serveur_ip) {
        this.reader  = reader;
        this.serveur_ip = serveur_ip;
    }

    public Web_service() {

    }
    //    private static void getCardId(WebTarget target){
//        com.mix.model.Client c = new com.mix.model.Client("192.1.1","Lecteur1");
//        String response = target.request(MediaType.APPLICATION_JSON)
//                .accept(MediaType.TEXT_PLAIN_TYPE)
//                .post(Entity.json(c), String.class);
//        System.out.println("response = " + response);
//    }


    public String getCard()  {
        try {
            // Créer l'URL de destination
            String urlStr = "http://" + this.serveur_ip + ":8080/Client";
            URL url = new URL(urlStr);
            // Ouvrir une connexion HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "text/plain");

            connection.setDoOutput(true);

            // Construire le corps de la requête JSON
            String rawJson = "{\"ipAdress\": \"" + new IP_Address().getIP() + "\",\"readerId\": \"" + this.reader + "\"}";

            // Envoyer les données JSON en tant que flux de sortie
            try (DataOutputStream os = new DataOutputStream(connection.getOutputStream())) {
                os.writeBytes(rawJson);
            }

            // Lire la réponse du serveur
            StringBuilder response = new StringBuilder();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
            }
            // Fermer la connexion
            connection.disconnect();

            // Traiter et renvoyer la réponse
            System.out.println("response.append" + response.toString());
            return new Web_service().getContent(response.toString());

        }
        catch (ConnectException e){
            System.out.println("Bonjour " + e.getMessage());
            return e.getMessage();
        }
        catch (IOException e) {
//            e.printStackTrace();
            System.out.println("IOExxception " + e.getMessage());
            return e.getMessage();
        }
    }
//    public String getCard() {
//        javax.ws.rs.client.Client client = ClientBuilder.newClient();
//        WebTarget target = client.target("http://" + this.serveur_ip + ":8080/Client");
//        String rawJson = "{\"ipAdress\": \"" + new IP_Address().getIP() + "\",\"readerId\": \"" + this.reader + "\"}";
//        try {
//            String response = target.request(MediaType.APPLICATION_JSON)
//                    .accept(MediaType.TEXT_PLAIN_TYPE)
//                    .property(ClientProperties.CONNECT_TIMEOUT, TIMEOUT_MS)
//                    .property(ClientProperties.READ_TIMEOUT, TIMEOUT_MS)
//                    .post(Entity.json(rawJson), String.class);
//
//            return new Web_service().getContent(response);
//        } catch (ProcessingException e) {
//            e.printStackTrace();
//            return "NOCARD";
//        }
//    }







    private String getCardWithoutTimeOut() {
        javax.ws.rs.client.Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://"+ this.serveur_ip +":8080/Client");
//        String rawJson = "{\"id\":\"id\",\"eona\":\"dina\"}";
        String rawJson = "{\"ipAdress\": \""+ new IP_Address().getIP() +"\",\"readerId\": \""+ this.reader +"\"}";
//                "{\"id\":\"id\",\"eona\":\"dina\"}";
        String response = target.request(MediaType.APPLICATION_JSON)
                .accept(MediaType.TEXT_PLAIN_TYPE)
                .post(Entity.json(rawJson), String.class);
//        System.out.println("response = " + response);
        return new Web_service().getContent(response);
    }



    public String getContent(String response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
//        String content = "{\"content\":\"ABE491D\\n\",\"readerId\":\"Lecteur1\"}";
            String content = response;
            ReaderContent readerContent = mapper.readValue(content, ReaderContent.class);
            System.out.println(readerContent.getContent());
            return readerContent.getContent();
        } catch (Exception e) {
            e.printStackTrace();
            return "No ID";
        }
    }

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String content = "{\"content\":\"ABE491D\\n\",\"readerId\":\"Lecteur1\"}";
        Client client = mapper.readValue(content, Client.class);
        System.out.println(client.getContent());
        new Web_service().getContent("{\"content\":\"ABE491D\\n\",\"readerId\":\"Lecteur1\"}");
    }


}
