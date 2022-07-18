package middleware.requests;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public interface IRequest {
    HttpURLConnection getConByURL(URL url) throws IOException;
}
