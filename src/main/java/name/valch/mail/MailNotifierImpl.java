package name.valch.mail;

import name.valch.entity.SerialWithDates;
import name.valch.parser.Parser;
import name.valch.service.SerialWithDatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class MailNotifierImpl implements MailNotifier {

    @Value("#{'${emails.list}'.split(',')}")
    private List<String> myList;

    @Autowired
    MailManager mailManager;
    @Autowired
    SerialWithDatesService serialWithDatesService;

    public MailNotifierImpl () {

    }

    private Parser seasonvarParser;

    @Autowired
    public void setParser (Parser seasonvarParser) {
        this.seasonvarParser = seasonvarParser;
    }

    public MailNotifierImpl (Parser seasonvarParser) {
        super();
        this.seasonvarParser=seasonvarParser;
    }

    @Override
    @Transactional
    @Scheduled(cron = "1 * * * * *")
    public void checkAndSend() {
//        SerialWithDates swd = serialWithDatesService.findById(Long.parseLong("1"));
//        mailManager.sendNotification(myList, swd);

        seasonvarParser.parse();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime before = now.minusMinutes(Long.parseLong("59"));
        List<SerialWithDates> serials = serialWithDatesService.findByDateBetween(before, now);
        if (!serials.isEmpty()) {
            for (SerialWithDates serial : serials) {
                serial.setDate(now);
                mailManager.sendNotification(myList, serial);
              //  System.out.println("sent");
            }
        }
    }
}
