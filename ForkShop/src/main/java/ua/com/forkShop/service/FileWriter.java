package ua.com.forkShop.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileWriter {

	enum Folder {
		ITEM
	}

	boolean write(Folder folder, MultipartFile file, int i);
}
