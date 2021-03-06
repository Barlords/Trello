package middleware.requests;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class APIRequest {

    public static String formatToRequestURL(String str) {
        return str.replace(" ", "%20");
    }

    public static void writeBodyRequest(HttpURLConnection con, String content) throws IOException {
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = content.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
    }

    public static String getResponse(HttpURLConnection con) throws IOException {

        System.out.println("Status : " + con.getResponseCode());
        System.out.println("Result : " + con.getResponseMessage());
        try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return response.toString();
        }
        catch(FileNotFoundException e) {
            return "";
        }

    }

    public static class Create {
        public static HttpURLConnection getConByURL(URL url) throws IOException {
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            return con;
        }
    }

    public static class Delete {
        public static HttpURLConnection getConByURL(URL url) throws IOException {
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("DELETE");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            return con;
        }
    }

    public static class Update {
        public static HttpURLConnection getConByURL(URL url) throws IOException {
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            return con;
        }

    }

    public static class Get {
        public static HttpURLConnection getConByURL(URL url) throws IOException {
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            return con;
        }
    }
}
