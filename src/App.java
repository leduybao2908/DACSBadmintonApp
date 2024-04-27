import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) throws IOException, URISyntaxException {
        String content = getContentFrom("https://batdongsan.com.vn");
        // Regex
        Pattern p = Pattern.compile("<li class='lv1'><a href='(.*?)' class='haslink '>");
        Matcher m = p.matcher(content);
        while (m.find()) {
            System.out.println(m.group(1));
        }
    }

    private static String getContentFrom(String link) throws IOException, URISyntaxException {
        // Gởi HTTP request và nhận về kết quả là chuỗi các thẻ HTML
        URI uri = new URI(link);
        URL url = uri.toURL();
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        Scanner scanner = new Scanner(new InputStreamReader(connection.getInputStream()));
        scanner.useDelimiter("\\Z");
        String content = scanner.next();
        scanner.close();
        // xoá các ký tự ngắt dòng (xuống dòng)
        content = content.replaceAll("\\R", "");
        return content;
    }
}
