import static org.junit.Assert.assertEquals;
import org.junit.rules.ExpectedException;
import org.junit.Rule;
import java.util.*;
import org.junit.Before;
import org.junit.Test;

// javac -cp .:junit-4.12.jar:hamcrest-core-1.3.jar AppTest.java
// java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore AppTest
public class AppTest {
	private EspressOS espressOS;
	private BackgroundApp backgroundapp1;
	private BackgroundApp backgroundapp2;
	private NotifyApps notifyapp1;
	private NotifyApps notifyapp2;
	private Notification noti1;
	private Notification noti2;
	private Notification noti3;
	private Notification noti4;
	private NotifyAndBackground notifyback1;
	private NotifyAndBackground notifyback2;
	@Before
	public void setup(){
		espressOS = new EspressOS();
		backgroundapp1 = new BackgroundApp("backgroundapp1");
		backgroundapp2 = new BackgroundApp("backgroundapp2");
		notifyapp1 = new NotifyApps("notifyapp1");
		notifyapp2 = new NotifyApps("notifyapp2");
		notifyback1 = new NotifyAndBackground("notifyback1");
		notifyback2 = new NotifyAndBackground("notifyback2");
		noti1 = new Notification("Sydney","is raining");
		noti2 = new Notification("Sydney", "is sunny");
		noti3 = new Notification("China","is raining");
		noti4 = new Notification("China","is sunny");
		
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testAllInvaildcase() {
		thrown.expect(RuntimeException.class);
		thrown.expectMessage("Invaild Value");
		BackgroundApp backgroundapp = new BackgroundApp(null);
		NotifyApps notifyapp = new NotifyApps(null);
		NotifyAndBackground notification = new NotifyAndBackground(null);
		Notification no = new Notification(null,null);
		notifyapp2.notifyOS(null);
		notifyback2.notifyOS(null);
		backgroundapp2.getData(null);
		notifyback2.getData(null);
	}
	
	@Test
	public void testNotification() {
		assertEquals(noti1.getTitle(),"Sydney"); //test noti1 title
		assertEquals(noti2.getContent(),"is sunny"); //test noti2 content
		assertEquals(noti3.getTitle()+" "+noti4.getContent(),"China is sunny"); // test noti3 title+ noti4 content.
		noti1.setTitle("News");
		noti1.setContent("killing");
		assertEquals(noti1.getTitle()+" "+noti1.getContent(),"News killing");
		
		espressOS.install(notifyapp1);
		espressOS.install(notifyapp2);
		espressOS.install(notifyback1);
		espressOS.install(notifyback2);
		assertEquals(espressOS.getNotificationApp().size(),4); // test notificationapp size.
		
		espressOS.uninstall("notifyapp1");						//notifyapp1 uninstall
		assertEquals(espressOS.getNotificationApp().size(),3); //notifyapp1
		
		
		
		espressOS.getNotificationApp().get(0).notifyOS(noti3); // add noti3 into notifyapp2 because notifyapp1 uninstalled
		espressOS.getNotificationApp().get(1).notifyOS(noti4); // add noti4 into notifyback1
		assertEquals(espressOS.getNotificationApp().get(1).getAppName(),"notifyback1"); //test if it's notifyback1
		
		

		Notification notif = espressOS.getNotifications().get(0); // get the index 0 notification in the notificationslist
		assertEquals("China:is raining",notif.getTitle()+":"+notif.getContent());

		
		Notification notif1 = espressOS.getNotifications().get(1);
		assertEquals("China:is sunny",notif.getTitle()+":"+notif1.getContent());
		
		
	}
	
	@Test
	public void testNotifyApps() {
		assertEquals(notifyapp1.getAppName(),"notifyapp1");
		assertEquals(notifyapp1.isRunning(),false);
		notifyapp1.start();
		assertEquals(notifyapp1.isRunning(),true);
		assertEquals(notifyapp1.exit(),0);
		assertEquals(notifyapp1.isRunning(),false);
		
		
		
		notifyapp1.notifyOS(noti1);   // same address noti1
		List<Notification> notifications = new ArrayList<Notification>();
		notifications.add(noti1);
		
		assertEquals(notifyapp1.getNotifications(),notifications);
		
	}
	
	@Test
	public void testBackgroundApp(){
		
		assertEquals(backgroundapp1.getAppName(),"backgroundapp1");
		assertEquals(backgroundapp1.isRunning(),false);
		backgroundapp1.start();
		assertEquals(backgroundapp1.isRunning(),true);
		assertEquals(backgroundapp1.exit(),0);
		assertEquals(backgroundapp1.getData("s"),"s");
	}
	@Test
	public void testNotifyAndBackgroundApp(){
		assertEquals(notifyback1.getAppName(),"notifyback1");
		assertEquals(notifyback1.isRunning(),false);
		notifyback1.start();
		assertEquals(notifyback1.isRunning(),true);
		assertEquals(notifyback1.exit(),0);
		assertEquals(notifyback1.getData("s"),"s");
		
		
		notifyback1.notifyOS(noti1);   // same address noti1
		List<Notification> notifications = new ArrayList<Notification>();
		notifications.add(noti1);
		
		assertEquals(notifyback1.getNotifications(),notifications);
	}
	@Test
	public void testInstallAndUninstall(){
		assertEquals(espressOS.install(backgroundapp1),true);
		assertEquals(espressOS.install(backgroundapp2),true);
		assertEquals(espressOS.install(notifyapp1),true);
		assertEquals(espressOS.install(notifyapp2),true);
		assertEquals(espressOS.install(notifyback1),true);
		assertEquals(espressOS.install(notifyback2),true);
		assertEquals(espressOS.install(notifyback2),false); // faile to install same app
		
		assertEquals(espressOS.getInstalledApps().size(),6);
		
		assertEquals(espressOS.uninstall("backgroundapp1"),true);
		assertEquals(espressOS.uninstall("backgroundapp2"),true);
		assertEquals(espressOS.uninstall("notifyapp1"),true);
		assertEquals(espressOS.uninstall("notifyapp2"),true);
		assertEquals(espressOS.uninstall("notifyback1"),true);
		assertEquals(espressOS.uninstall("notifyback2"),true);
		assertEquals(espressOS.uninstall("notifynotexitst"),false); // faile to uninstall same app
		
		assertEquals(espressOS.getInstalledApps().size(),0);
		
		
	}
	@Test
	public void testGetRunningApps(){
		espressOS.install(backgroundapp1);
		espressOS.install(notifyapp1);
		espressOS.install(notifyback1); //install apps
		
		espressOS.run("backgroundapp1");
		espressOS.run("notifyapp1");
		espressOS.run("notifyback1"); //run them
		
		
		List<App> runningapps = new ArrayList<App>(); 
		runningapps.add(backgroundapp1);
		runningapps.add(notifyapp1);
		runningapps.add(notifyback1); //actual
		
		
		assertEquals(espressOS.getRunningApps(),runningapps); //compare with expect
		
		espressOS.close("backgroundapp1"); // stop printing and close them.
		espressOS.close("notifyapp1");
		espressOS.close("notifyback1");
		
	}
	
	@Test
	public void testGetInstalledApps(){
		espressOS.install(backgroundapp1);
		espressOS.install(notifyapp1);
		espressOS.install(notifyback1); //install apps
		
		List<App> apps = new ArrayList<App>(); 
		apps.add(backgroundapp1);
		apps.add(notifyapp1);
		apps.add(notifyback1); //actual
		
		assertEquals(espressOS.getInstalledApps(),apps);
	}
	
	@Test
	public void testGetBackgroundApps(){
		espressOS.install(backgroundapp1);
		espressOS.install(notifyapp1);
		espressOS.install(notifyback1);
		espressOS.install(notifyback2);
		
		List<BackgroundApp> backgroundapps = new ArrayList<BackgroundApp>();
		backgroundapps.add(backgroundapp1);
		backgroundapps.add(notifyback1);
		backgroundapps.add(notifyback2);  //actual
		
		assertEquals(espressOS.getBackgroundApps(),backgroundapps);
		
		espressOS.uninstall("notifyback1");   //test with uninstall
		backgroundapps.remove(1);
		
		assertEquals(espressOS.getBackgroundApps(),backgroundapps);
	}
	
	@Test
	public void testGetNotificationApp(){
		espressOS.install(backgroundapp1);
		espressOS.install(notifyapp1);
		espressOS.install(notifyapp2);
		espressOS.install(notifyback1);
		espressOS.install(notifyback2);
	
		List<NotifyApp> notifyapps = new ArrayList<NotifyApp>();
		notifyapps.add(notifyapp1);
		notifyapps.add(notifyapp2);
		notifyapps.add(notifyback1);
		notifyapps.add(notifyback2);
		
		assertEquals(espressOS.getNotificationApp(),notifyapps);
		
		espressOS.uninstall("backgroundapp1"); // check if backgroundapp1 will influence 
		espressOS.uninstall("notifyapp1");
		espressOS.uninstall("notifyback1");
		notifyapps.remove(0);
		notifyapps.remove(1); // the index 2 became index 1 because last remove
		
		
		assertEquals(espressOS.getNotificationApp(),notifyapps);
		
	}
	
	
	@Test
	public void testGetNotifications(){

		espressOS.install(notifyapp1);
		espressOS.install(notifyapp2);
		espressOS.install(notifyback1);
		espressOS.install(notifyback2);
		
		notifyapp1.notifyOS(noti1);
		notifyapp1.notifyOS(noti2);
		notifyapp2.notifyOS(noti2);
		notifyapp2.notifyOS(noti3);
		notifyapp2.notifyOS(noti4);
		
		
		List<Notification> notification = new ArrayList<Notification>();
		notification.add(noti1);
		notification.add(noti2);
		notification.add(noti2);
		notification.add(noti3);
		notification.add(noti4);
		
		assertEquals(espressOS.getNotifications(),notification);
		
		Notification test = espressOS.getNotifications().get(0);
		assertEquals(test.getTitle() +" " +test.getContent(),"Sydney is raining");
	}
	
	@Test
	public void testRunAndClose(){
		espressOS.install(backgroundapp1);
		espressOS.install(notifyapp1);
		espressOS.install(notifyback1);
		
		
		assertEquals(espressOS.run("backgroundapp1"),true);
		espressOS.close("backgroundapp1");
		
		assertEquals(espressOS.run("notifyapp1"),true);
		espressOS.close("notifyapp1");
		
		assertEquals(espressOS.run("notifyback1"),true);
		espressOS.close("notifyback1");
		
		assertEquals(espressOS.run("randomname"),false); //no app
	}
	

	

	
}
