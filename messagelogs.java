package logs;

public class messagelogs {
	private Message m;
	private String GUC;
	private String GC;

	
	public messagelogs(Message m) {
		super();
		this.m = m;
	}


	public Message getM() {
		return m;
	}


	public void setM(Message m) {
		this.m = m;
	}

	public void LogMessage() {
		if(m.getDate().isEmpty()) {
			return ;
			
			
		}
		
		
		
	}
	public void sendLogs() {
		if (m.getData().isEmpty()) {
			
		}
		m.getToClient();
	}

	public String getGC() {
		return GC;
	}


	public void setGC(String gC) {
		GC = gC;
	}


	public String getGUC() {
		return GUC;
	}


	public void setGUC(String gUC) {
		GUC = gUC;
	}
	

	

}
