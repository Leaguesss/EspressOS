import java.util.*;
public interface NotifyApp extends App {
	public List<Notification> getNotifications();
	public void notifyOS(Notification notification);
}
