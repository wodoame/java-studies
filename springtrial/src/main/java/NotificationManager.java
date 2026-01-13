import org.springframework.stereotype.Component;

@Component
public class NotificationManager{
    NotificationService notificationService;
    public NotificationManager(NotificationService notificationService) {
       this.notificationService = notificationService;
    }

    public void sendNotification(){
        this.notificationService.send();
    }
}
