package name.valch.mail;

import name.valch.entity.SerialWithDates;
import name.valch.entity.User;
import name.valch.entity.UserProfile;
import name.valch.parser.Parser;
import name.valch.service.SerialWithDatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Service
public class MailNotifierImpl implements MailNotifier {

  //  @Value("#{'${emails.list}'.split(',')}")
    private List<String> myList = new ArrayList<>();

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
    @Scheduled(cron = "1 56 * * * *")
    public void checkAndSend() {
//        SerialWithDates swd = serialWithDatesService.findById(Long.parseLong("1"));
//        mailManager.sendNotification(myList, swd);

        seasonvarParser.parse();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime before = now.minusMinutes(Long.parseLong("5"));
        ArrayList<SerialWithDates> serials = (ArrayList<SerialWithDates>) serialWithDatesService.findByDateBetween(before, now);
        if (!serials.isEmpty()) {
            for (SerialWithDates serial : serials) {
                if (serial.getLink()!=null) {
                serial.setDate(now);
                    Collection<UserProfile> users = serial.getProfiles().values();
                    for (UserProfile u:users) {
                    myList.add(u.getUserName().getEmail());
                    }
                mailManager.sendNotification(myList, serial);
                }
              //  System.out.println("sent");
            }
        }
    }
}
