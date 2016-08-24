import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by numannsaid on 09/06/16.
 */
public class Connect {

    public static void main(String[] args) {
        InputStream payload = null;
        try {
            URL url = new URL("http://www.auto-motor-und-sport.de/rss/news/");
            final HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
           // printReqHeader(httpURLConnection);

            //printReposneHeaders(httpURLConnection);


            if (httpURLConnection.getResponseCode() != 200) {
                httpURLConnection.disconnect();
                final HttpURLConnection retryConn = (HttpURLConnection) url.openConnection();
                System.out.println("REtry");
                retryConn.setRequestProperty ("User-agent", "Mozilla/5.0 ( compatible ) ");
                printReqHeader(httpURLConnection);
                printReposneHeaders(retryConn);
                payload = retryConn.getInputStream();
            }else{
                payload = httpURLConnection.getInputStream();
            }
            String payloadAsString = "";

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(payload));

            String inputLine;
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
            in.close();

            System.out.println(payloadAsString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (payload != null) {
                try {
                    payload.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void printReqHeader(HttpURLConnection httpURLConnection) {
        Map<String, List<String>> reqheader = httpURLConnection.getRequestProperties();

        for (String k : reqheader.keySet()) {
            System.out.println("***REQUEST**"+k+"*****");
            System.out.println(reqheader.get(k));
        }
    }

    private static void printReposneHeaders(HttpURLConnection httpURLConnection) {
        Map<String, List<String>> hdrs = httpURLConnection.getHeaderFields();

        for (String k : hdrs.keySet()) {
            System.out.println("**RESPONSE***"+k+"*****");
            System.out.println(hdrs.get(k));
        }
    }
}
