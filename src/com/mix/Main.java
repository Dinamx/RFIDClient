package com.mix;

public class Main {

    public static void main(String[] args) {
        String reader = "Lecteur1";
        String hostIP = "localhost";
        Client c = new Client(reader,hostIP);
        System.out.println("CardID is " +  c.getCard());
    }
}
