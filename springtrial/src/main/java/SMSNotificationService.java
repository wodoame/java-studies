import org.springframework.stereotype.Service;

@Service("sms")
public class SMSNotificationService implements NotificationService{
    @Override
    public void send() {
        System.out.println("Sent email using " + SMSNotificationService.class);
    }
}
