package kr.or.ddit.utils;

public enum Command {
	INSERT("insert"), UPDATE("update"), DELETE("delete"), SELECT("select");
	
	private String command;

	private Command(String command) {
		this.command = command;
	}
	
	public String getCommand() {
		return command;
	}
}