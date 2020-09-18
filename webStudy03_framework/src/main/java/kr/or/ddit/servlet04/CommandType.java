package kr.or.ddit.servlet04;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public enum CommandType {
	 
	COPY(new FileCommandProcess() {
		@Override
		public boolean commandProcess(File srcFile, File destFolder) {
			try {
				FileUtils.copyFileToDirectory(srcFile, destFolder);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}
	}), 
	DELETE(new FileCommandProcess() {
		@Override
		public boolean commandProcess(File srcFile, File destFolder) {
			return FileUtils.deleteQuietly(srcFile);
		}
	}),
	MOVE(new FileCommandProcess() {
		@Override
		public boolean commandProcess(File srcFile, File destFolder) {
			boolean result = false;
			if(COPY.commandProcess(srcFile, destFolder)) {
				result = DELETE.commandProcess(srcFile, null);
			}
			return result;
		}
	});
	
	private FileCommandProcess commandProcess;
	
	private CommandType(FileCommandProcess commandProcess) {
		this.commandProcess = commandProcess;
	}
	
	public boolean commandProcess(File srcFile, File destFolder) {
		return commandProcess.commandProcess(srcFile, destFolder);
	}
}