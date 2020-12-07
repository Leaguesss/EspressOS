public class DirectTest {
		public static void main(String[] args) throws InterruptedException {
		EspressOSMobile mo = new EspressOSMobile();
		

		EspressOS es = new EspressOS();
		NotifyApp a = new NotifyApps("notify1");
		NotifyApp b = new NotifyApps("notify2");
		NotifyAndBackground c = new NotifyAndBackground("NotifyAndBackground1");
		
		es.install(a);
		es.install(b);
		es.install(c);
		
		es.run("NotifyAndBackground1");
		Thread.sleep(50);
		es.close("NotifyAndBackground1");
		
		
			
		AntennaImpl an = new AntennaImpl();
		an.setSignalStrength(-1);
		System.out.println(mo.changeAntenna(an)); // false
		a.notifyOS(new Notification("Sydney","is raining"));
		a.notifyOS(new Notification("Sydney", "is sunny"));
		b.notifyOS(new Notification("China","is raining"));
		b.notifyOS(new Notification("China","is sunny"));
		
		System.out.println(es.getInstalledApps().size()); // 3
		System.out.println(es.getNotificationApp().size()); // 3
		System.out.println(es.getBackgroundApps().size()); //1
		System.out.println(es.uninstall("notify1")); // uninstall
		System.out.println(es.getInstalledApps().size()); // 2
		System.out.println(es.getNotificationApp().size()); // 2
		System.out.println(es.getBackgroundApps().size()); //1
		
		for(Notification s: es.getNotifications()) {
			System.out.println(s.getTitle()+":"+s.getContent());
		}
			//China:is raining
			//China:is sunny
	}
}
