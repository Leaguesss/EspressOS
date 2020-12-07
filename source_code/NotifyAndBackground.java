import java.util.*;
public class NotifyAndBackground extends BackgroundApp implements NotifyApp{
	private List<Notification> notifications;
	
	public NotifyAndBackground(String appName) {
		super(appName);
		notifications = new ArrayList<Notification>();
	}
	
	public void notifyOS(Notification notification) {
		if(notification == null) {
			throw new RuntimeException("Invaild Value");
		}
		notifications.add(notification);
	}
	
	public List<Notification> getNotifications() {
		return notifications;
	}
	
}
