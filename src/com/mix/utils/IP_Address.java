package com.mix.utils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
public class IP_Address
{
    public IP_Address() {
    }

    public static String getIP() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

            String wifiIP = null;
            String cableIP = null;

            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();

                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();

                    if (inetAddress instanceof Inet4Address && !inetAddress.isLoopbackAddress()) {
                        if (networkInterface.getDisplayName().contains("Wi-Fi") && wifiIP == null) {
                            wifiIP = inetAddress.getHostAddress();
                        } else if (networkInterface.getDisplayName().contains("Ethernet") && cableIP == null) {
                            cableIP = inetAddress.getHostAddress();
                        }
                    }
                }
            }

            // Prioritize WiFi IP if available, otherwise use cable IP
            if (cableIP != null) {
                return cableIP;
            } else if (wifiIP != null) {
                return wifiIP;
            } else {
                throw  new Exception("Aucune connexion réseau active.");
            }
        } catch (Exception e) {
            return "Erreur lors de la récupération de l'adresse IP.";
        }
    }








public static void main(String args[]) throws Exception
    {
        String ipAddress = getIP();
        System.out.println("Adresse IP du PC : " + ipAddress);

//        try {
//            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
//
//            while (networkInterfaces.hasMoreElements()) {
//                NetworkInterface networkInterface = networkInterfaces.nextElement();
//                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
//
//                while (inetAddresses.hasMoreElements()) {
//                    InetAddress inetAddress = inetAddresses.nextElement();
//
//                    if (inetAddress instanceof Inet4Address && !inetAddress.isLoopbackAddress()) {
//                        System.out.println("Interface: " + networkInterface.getDisplayName());
//                        System.out.println("IPv4 Address: " + inetAddress.getHostAddress());
//                    }
//                }
//            }
//        } catch (SocketException e) {
//            e.printStackTrace();
//        }
    }
}