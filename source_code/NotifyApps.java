import java.util.*;
public class NotifyApps implements NotifyApp{
	private String appName;
	private List<Notification> notifications;
	private boolean appStatus;
	
	public NotifyApps(String appName) {
		if(appName == null) {
			throw new RuntimeException("Invaild Value");
		}
		this.appName = appName;
		notifications = new ArrayList<Notification>();
		appStatus = false;
	}
	public boolean isRunning(){
		return appStatus;
	}
	
	public String getAppName() {
		return appName;
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
	public void start() {
		appStatus = true;
	}
	public int exit() {
		appStatus = false;
		return 0;
	}
}
