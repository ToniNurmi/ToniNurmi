package app;

public class DataExchange extends Thread {
	
	private boolean obstacle = false;
	private int CMD = 1;
	
	public DataExchange() {
		
	}
	
	public boolean getObstacle() {
		return obstacle;
	}

	public void setObstacle(boolean status) {
		this.obstacle = status;
	}

	public int getCMD() {
		return CMD;
	}

	public void setCMD(int command) {
		CMD = command;
	}

	@Override
	public void run() {
		
	}

}
