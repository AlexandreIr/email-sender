package com.afmail.mail_sender;

import java.util.Objects;

public class FileClass {
	private String fileName;
	private String filePath;
	
	public FileClass() {}

	public FileClass(String fileName, String filePath) {
		super();
		this.fileName = fileName;
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fileName, filePath);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileClass other = (FileClass) obj;
		return Objects.equals(fileName, other.fileName) && Objects.equals(filePath, other.filePath);
	}
}
