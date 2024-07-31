package com.paspa;

import com.jcraft.jsch.*;
import java.io.InputStream;
import java.util.Scanner;

public class RemoteFileSearcher {

    public boolean searchFiles(String searchPattern) {
        boolean fileFound = false;

        String user = "root";
        String password = "Pagano01";
        String host = "192.168.1.99";
        int port = 22;

        String remoteDirectory = "/var/www/snipe-it/storage/private_uploads/assets";

        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            session.setPassword(password);

            // Configura le proprietà della sessione per accettare tutte le chiavi host
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            session.connect();

            ChannelExec channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand("find " + remoteDirectory + " -type f -name \"" + searchPattern + "\"");

            channel.setInputStream(null);
            channel.setErrStream(System.err);

            InputStream in = channel.getInputStream();
            channel.connect();

            Scanner scanner = new Scanner(in);
            if (scanner.hasNextLine()) {
                fileFound = true;
            }

            scanner.close();
            channel.disconnect();
            session.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileFound;
    }
}
