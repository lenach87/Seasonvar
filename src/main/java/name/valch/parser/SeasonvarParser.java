package name.valch.parser;

import name.valch.SeasonvarApplication;
import name.valch.entity.Serial;
import name.valch.entity.SerialWithDates;
import name.valch.service.SerialService;
import name.valch.service.SerialWithDatesService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SeasonvarParser implements Parser  {

    public SeasonvarParser() {
    }

    private SerialService serialService;
    private SerialWithDatesService serialWithDatesService;

    @Autowired
    public void setSerialService (SerialService serialService) {
        this.serialService=serialService;

    }
    @Autowired
    public void setSerialWithDatesService(SerialWithDatesService serialWithDatesService) {
        this.serialWithDatesService = serialWithDatesService;

    }

    public SeasonvarParser(SerialService serialService, SerialWithDatesService serialWithDatesService) {
        super();
        this.serialService = serialService;
        this.serialWithDatesService = serialWithDatesService;
    }

    private static final Logger log = LoggerFactory.getLogger(SeasonvarApplication.class);

    @Transactional
    @Override
    public void parse() {
        try {
            URL url1 = new URL("http://seasonvar.ru");
            String lineResult;
            StringBuilder builder = new StringBuilder();
            URLConnection yc = url1.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            while ((lineResult = in.readLine()) != null) {
                builder.append(lineResult);
            }
            in.close();
            String text = builder.toString();
            Document doc = Jsoup.parse(text);
            Element current = doc.select("div.film-list-block").first();
            Element current1 = current.children().select("div.film-list-block-content").first();
            Elements titles = current1.children().select("div.film-list-item");
          //  log.info(titles.html());
            List<String> names = new ArrayList<>();
            List<Serial> ser = serialService.findAll();
            for (Element c:titles) {
                String url = c.attr("abs:href");
            //    log.info(url)
                for (Serial s:ser) {
                    if (s.getName().equals(url)) {
                        names.add(s.getName());
                    }
                }
            }
            //log.info(names.toString());
            for (String str:names) {
                SerialWithDates swd = serialWithDatesService.findByName(str);
                swd.setDate(LocalDateTime.now());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
