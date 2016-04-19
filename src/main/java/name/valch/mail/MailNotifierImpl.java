package name.valch.mail;

import name.valch.entity.SerialsWithDates;
import name.valch.parser.Parser;
import name.valch.service.SerialsWithDatesService;
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
    SerialsWithDatesService serialsWithDatesService;

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
    @Scheduled(cron = "5 * * * * *")
//    @Scheduled(fixedRate = 10000)
    public void checkAndSend() {
        seasonvarParser.parse();
        List<SerialsWithDates> serials = serialsWithDatesService.findByDate(LocalDateTime.now().minusHours(Long.parseLong("1")), LocalDateTime.now());
//        System.out.println(myList);
        if (!serials.isEmpty()) {
            for (SerialsWithDates serial : serials) {
                mailManager.sendNotification(myList, serial);
                System.out.println("sent");
            }
        }
    }
}
