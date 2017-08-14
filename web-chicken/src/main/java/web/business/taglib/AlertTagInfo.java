package web.business.taglib;

public class AlertTagInfo {
	public static final int AUTOCLOSE_TIME = 500; // 5s

	private String type;
	private String message;
	private boolean closeable;
	private boolean autoClose;
	private int autoCloseTime;

	private AlertTagInfo() {
	}
	
	public String getTitle() {
		if ("success".equals(type)) {
			return "Success!";
			
		} else if ("info".equals(type)) {
			return "Info!";
			
		} else if ("warning".equals(type)) {
			return "Warning!";
			
		} else if ("danger".equals(type)) {
			return "Error!";
		}
		return "";
	}
	
	public static AlertTagInfoBuilder success(String message) {
		AlertTagInfo info = new AlertTagInfo();
		info.setType("success");
		info.setMessage(message);
		AlertTagInfoBuilder builder = info.new AlertTagInfoBuilder(info);
		return builder;
	}
	
	public static AlertTagInfoBuilder info(String message) {
		AlertTagInfo info = new AlertTagInfo();
		info.setType("info");
		info.setMessage(message);
		AlertTagInfoBuilder builder = info.new AlertTagInfoBuilder(info);
		return builder;
	}
	
	public static AlertTagInfoBuilder warning(String message) {
		AlertTagInfo info = new AlertTagInfo();
		info.setType("warning");
		info.setMessage(message);
		AlertTagInfoBuilder builder = info.new AlertTagInfoBuilder(info);
		return builder;
	}
	
	public static AlertTagInfoBuilder error(String message) {
		AlertTagInfo info = new AlertTagInfo();
		info.setType("danger");
		info.setMessage(message);
		AlertTagInfoBuilder builder = info.new AlertTagInfoBuilder(info);
		return builder;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isCloseable() {
		return closeable;
	}

	public void setCloseable(boolean closeable) {
		this.closeable = closeable;
	}

	public boolean isAutoClose() {
		return autoClose;
	}

	public void setAutoClose(boolean autoClose) {
		this.autoClose = autoClose;
	}

	public int getAutoCloseTime() {
		return autoCloseTime;
	}

	public void setAutoCloseTime(int autoCloseTime) {
		this.autoCloseTime = autoCloseTime;
	}
	
	public class AlertTagInfoBuilder {
		private AlertTagInfo info;
		
		private AlertTagInfoBuilder(AlertTagInfo info) {
			this.info = info;
		}

		public AlertTagInfo build() {
			return info;
		}
		
		public AlertTagInfo autoclose() {
			return autoclose(AUTOCLOSE_TIME);
		}
		
		public AlertTagInfo autoclose(int timeInMiliSecond) {
			info.setAutoClose(true);
			info.setAutoCloseTime(timeInMiliSecond);
			return info;
		}
		
		public AlertTagInfo closeable() {
			info.setCloseable(true);
			return info;
		}
	}

}
