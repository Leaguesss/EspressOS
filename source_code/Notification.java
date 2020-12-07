public class Notification {
	private String title;
	private String content;
	
	public Notification(String title, String content) {
		if(title == null || content == null) {
			throw new RuntimeException("Invaild Value");
		}
		this.title = title;
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public void setTitle(String newTitle) {
		title = newTitle;
	}
	public void setContent(String newContent) {
		content = newContent;
	}
}
