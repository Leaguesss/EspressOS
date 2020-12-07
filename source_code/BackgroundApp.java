public class BackgroundApp implements App{
	protected String appName;
	protected BackgroundThread backgroundThread; 
	protected boolean appStatus;
	
	public BackgroundApp(String appName) {
		if(appName == null) {
			throw new RuntimeException("Invaild Value");
		}
		backgroundThread = new BackgroundThread(this);
		this.appName = appName;
		appStatus = false;
	}
	public boolean isRunning(){
		return appStatus;
	}
	public void start() {
		appStatus = true;
		backgroundThread.start();
		
	}
	
	public int exit() {
		appStatus = false;
		backgroundThread.exit();
		return 0;
	}
	public void backgroundStart() {
		System.out.println("The app " + appName + " is running background");
	}
	public Object getData(Object obj) {
		if(obj == null) {
			throw new RuntimeException();
		}
		return obj;	
	}
	public String getAppName() {
		return appName;
	}
}
