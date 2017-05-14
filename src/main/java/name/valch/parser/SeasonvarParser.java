package name.valch.parser;

import name.valch.SeasonvarApplication;
import name.valch.entity.SerialWithDates;
import name.valch.entity.UserProfile;
import name.valch.service.SerialWithDatesService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SeasonvarParser implements Parser  {

    private static final Logger log = LoggerFactory.getLogger(SeasonvarApplication.class);

    public SeasonvarParser() {
    }
    private SerialWithDatesService serialWithDatesService;


    @Autowired
    public void setSerialWithDatesService(SerialWithDatesService serialWithDatesService) {
        this.serialWithDatesService = serialWithDatesService;

    }

    public SeasonvarParser(SerialWithDatesService serialWithDatesService) {
        super();
        this.serialWithDatesService = serialWithDatesService;
    }

    @Transactional
    @Override
    public void parse() {

            String url = "http://seasonvar.ru/";
            Document doc;
            try {
                doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", "");
                ((Document) doc).outputSettings().charset().forName("UTF-8");
                ((Document) doc).outputSettings().escapeMode(Entities.EscapeMode.xhtml);
               /* Element current = doc.select("div.film-list-block").first();
                Element current1 = current.children().select("div.film-list-block-content").first();
                //   log.info(current1.html());
                Elements titles = current1.children().select("div.film-list-item");*/

                Element current = doc.select("div.news").first();
                Elements titles = current.select("a[href]");
                List<String> names = new ArrayList<>();
                List<SerialWithDates> ser = (ArrayList<SerialWithDates>)serialWithDatesService.findAll();
                for (Element c:titles) {
                //    Element individuallink = c.select("a[href]").first();
                    StringBuilder strbuild = new StringBuilder();
                    strbuild.append("http://seasonvar.ru");
                    strbuild.append(c.attr("href"));
                    String urllink = strbuild.toString();
                    // log.info ("Link = "+ individuallink.attr("href"));
                    Element individuallink = c.children().select("div.news-w").first().select("div.news_n").first();
                    String serialName = individuallink.text();
                    log.info("Serial " + serialName);
                    String fullText = c.text();
               //     log.info("Text " + fullText);
                    for (SerialWithDates s : ser) {
                        if ((serialName.equalsIgnoreCase(s.getName()))) {
                            log.info("We follow " + s.getName());
                      //      if (s.getFullNewText()==null || s.getFullNewText().isEmpty() || !s.getFullNewText().contains(fullText)) {
                                names.add(s.getName());
                                s.setLink(urllink);
                                ArrayList <String> fullTexts = new ArrayList <>();
                                fullTexts.add(fullText);
                                s.setFullNewText(fullTexts);
                                s.setDate(LocalDateTime.now());
                                serialWithDatesService.save(s);
                                log.info("Serial with new date found " + s.getName());
                        }
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            }

/*          File input = new ClassPathResource("seasonvar1.htm").getFile();
            Document doc = Jsoup.parse(input, "UTF-8", "");*/

            //log.info(names.toString());
//            for (String str:names) {
//                SerialWithDates swd = serialWithDatesService.findByName(str);
//                log.info("Serial with new date found " + str);
//                swd.setDate(LocalDateTime.now());
//            }
    }
}
