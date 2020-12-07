import java.util.*;

public class EspressOS {
 
	private List<App> apps;

	public EspressOS() {
		apps = new ArrayList<App>();
	}
	public<T extends App> boolean install(App app) {
		if(app == null) {
			return false;
		}
		for(App a: apps) {
			if(a.getAppName().equals(app.getAppName())) {
				return false;
			}
		}
		apps.add(app);
		return true;
		
	}
	public boolean uninstall(String appName) {
		if(appName == null) {
			return false;
		}
		for(int i = 0; i < apps.size();i++) {
			if(apps.get(i).getAppName().equals(appName)) {
				apps.remove(i);
				return true;	
			}	
		}
		return false;
	}
	
	public List<App> getRunningApps() {
		List<App> runningapps = new ArrayList<App>();
		for(int i = 0;i <apps.size();i++) {
			if(apps.get(i) != null) {
				if(apps.get(i).isRunning()) {
					runningapps.add(apps.get(i));
				}
			}	
		}
		return runningapps;
	}
	
	public List<App> getInstalledApps() {
		return apps;
	}
	
	public List<BackgroundApp> getBackgroundApps() {
		List<BackgroundApp> backgroundapps = new ArrayList<BackgroundApp>();
		for(int i = 0; i < apps.size();i++) {
			if (apps.get(i) instanceof BackgroundApp) {
				backgroundapps.add((BackgroundApp)apps.get(i));
			}
		}
		return backgroundapps;
	}
	
	public List<NotifyApp> getNotificationApp() {
		List<NotifyApp> notifyapps = new ArrayList<NotifyApp>();
		for(int i = 0; i < apps.size();i++) {
			if (apps.get(i) instanceof NotifyApp) {
				notifyapps.add((NotifyApp)apps.get(i));
			}
		}
		return notifyapps;
	}
	
	public List<Notification> getNotifications() {
		List<Notification> notification = new ArrayList<Notification>(); 
		for(NotifyApp obj : getNotificationApp()){
			for(Notification koj: obj.getNotifications()){
				notification.add(koj);
			}
		}
		return notification;
 	}
	public boolean run(String appName) {
		if(appName == null) {
			return false;
		}
		for(int i = 0; i<apps.size(); i++){
			if(appName.equals(apps.get(i).getAppName())){
				apps.get(i).start();
				return true;
			}
		}
		return false;
	}
	
	public void close(String appName) {
		if(appName == null) {
			return;
		}
		for(int i = 0; i < apps.size(); i++){			
			if(appName.equals(apps.get(i).getAppName())){
				apps.get(i).exit();
				return;
			}
		}
	}

}
