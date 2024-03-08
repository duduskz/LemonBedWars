package cn.lemonnetwork.lemonbedwars.Utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class getBungeeServerInfo {
    public static String getMotd(String address) {
        try {

            String[] parts = address.split(":");
            String ip = parts[0];
            String port = parts[1];
            Socket sock = new Socket(ip, Integer.parseInt(port));
            DataOutputStream out = new DataOutputStream(sock.getOutputStream());
            DataInputStream in = new DataInputStream(sock.getInputStream());

            out.write(0xFE);

            int b;
            StringBuffer str = new StringBuffer();
            while ((b = in.read()) != -1) {
                if (b != 0 && b > 16 && b != 255 && b != 23 && b != 24) {
                    str.append((char) b);
                }
            }

            String[] data = str.toString().split("§");
            String serverMotd = data[0];
            return serverMotd;

        } catch (Exception e) {
            return "错误";
        }
    }

}
