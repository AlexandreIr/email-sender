package com.afmail.mail_sender;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

public class AttachmentClass {
	private String path;
	private String fileExtension;
	private String name;
	
	public AttachmentClass() {}

	public AttachmentClass(String path, String name) {
		super();
		this.path = path;
		this.name = name;
		this.fileExtension = setFileExtension();
	}

	
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public String setFileExtension() {
		return path.substring(path.lastIndexOf('.'), path.length());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(fileExtension, name, path);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AttachmentClass other = (AttachmentClass) obj;
		return Objects.equals(fileExtension, other.fileExtension) && Objects.equals(name, other.name)
				&& Objects.equals(path, other.path);
	}

	public FileInputStream fileSender() throws FileNotFoundException {
		File file = new File(path);
		return new FileInputStream(file);
	}
}
