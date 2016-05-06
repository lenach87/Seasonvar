package name.valch.parser;

import name.valch.SeasonvarApplication;
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
            Document doc = Jsoup.parse(text, "http://seasonvar.ru");

/*          File input = new ClassPathResource("seasonvar1.htm").getFile();
            Document doc = Jsoup.parse(input, "UTF-8", "");*/
            Element current = doc.select("div.film-list-block").first();
            Element current1 = current.children().select("div.film-list-block-content").first();
         //   log.info(current1.html());
            Elements titles = current1.children().select("div.film-list-item");
            List<String> names = new ArrayList<>();
            List<SerialWithDates> ser = serialWithDatesService.findAll();
            for (Element c:titles) {
                Element individuallink = c.select("a[href]").first();
                StringBuilder strbuild = new StringBuilder();
                strbuild.append("http://seasonvar.ru");
                strbuild.append(individuallink.attr("href"));
                String urllink = strbuild.toString();
               // log.info ("Link = "+ individuallink.attr("href"));
                String serialName = c.text();
               // log.info ("Text found "+ c.text());
            //    log.info(url)
                for (SerialWithDates s:ser) {
                    if (serialName.toLowerCase().contains(s.getName().toLowerCase())&&(s.getDate().isBefore(LocalDateTime.now().minusMinutes(Long.parseLong("61"))))) {
                        names.add(s.getName());
                        s.setLink(urllink);
                    }
                }
            }
            //log.info(names.toString());
            for (String str:names) {
                SerialWithDates swd = serialWithDatesService.findByName(str);
                log.info("Serial with new date found " + str);
                swd.setDate(LocalDateTime.now());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
