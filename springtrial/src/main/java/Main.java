import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        var notificationManager = context.getBean(NotificationManager.class);
        notificationManager.sendNotification();
        context.close();
    }
}
