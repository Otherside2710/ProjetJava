package com.esiea.tp4A.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private RoverInterface roverInterface;
    public Server (int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.roverInterface = null;
    }
    public void setRoverInterface(RoverInterface roversServer) {
        this.roverInterface = roversServer;
    }
    public void start () {
        if (this.roverInterface == null) return;
        Thread thread = new Thread(() -> boucle());
        thread.start();
    }
    private void boucle() {
        while(true) {
            try {
                client(this.serverSocket.accept());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static String OuvrirPageHtml(String lienPageHtml) throws IOException
    {
        BufferedReader lecteurPageHtml = new BufferedReader(new FileReader(lienPageHtml));                    //LecteurPage html permet de lire la page html qui se trouve au lien spécifié en argumenet
        String line;
        String lines="";
        while ((line = lecteurPageHtml.readLine()) != null)
        {
            lines=lines+line;
        }
        lecteurPageHtml.close();
        return lines;
    }
    private void client(Socket socket) throws IOException {
        String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + OuvrirPageHtml("src/main/java/com/esiea/tp4A/server/Page.html");
        socket.getOutputStream().write(httpResponse.getBytes());
        InputStreamReader isr = new InputStreamReader(socket.getInputStream());
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        BufferedReader reader = new BufferedReader(isr);
        AnalyseAndCompare.ressource(writer, reader, this.roverInterface);
        String line = reader.readLine();
        while (!line.isEmpty()) {
            System.out.println(line);
            line = reader.readLine();
        }
    }
}
