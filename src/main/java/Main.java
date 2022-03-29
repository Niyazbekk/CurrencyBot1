import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;


public class Main{
    public static void main(String[] args) throws IOException {
        new Bot().server();
    }

    public static String getCurr(String currCode) throws IOException {
        Document doc = Jsoup.connect(currCode).get();
        StringBuilder strB = new StringBuilder("   Дата     Количество   Курс     Изменения\n\n");
        Elements table = doc.select(".noborder");
        for(int i = 0; i < 11 ; i ++ ){
            strB.append(table.text().replaceAll(" ", "      ")).append(" \n\n");
            table = table.prev();
        }
        return strB.toString();
    }

}
