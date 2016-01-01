package com.thevolume360.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import com.thevolume360.domain.enums.FileType;

public class FileUtils {
	public static final int FILE_NAME_MAX_SIZE = 60;
	private static final Map<String, String> extensionContentTypeMap;

	static {
		extensionContentTypeMap = new HashMap<>();
		extensionContentTypeMap.put("jpg", "image/jpeg");
		extensionContentTypeMap.put("jpeg", "image/jpeg");
		extensionContentTypeMap.put("png", "image/png");
		extensionContentTypeMap.put("pdf", "application/pdf");
		extensionContentTypeMap.put("doc", "application/msword");
		extensionContentTypeMap.put("docx", "application/msword");
	}

	public static boolean isValidFile(MultipartFile file, String[] fileTypeList) {
		String fileName = file.getOriginalFilename();
		String extension = getExtensionInLowerCase(fileName);
		for (String validFileType : fileTypeList) {
			if (extension.equalsIgnoreCase(validFileType)) {
				return true;
			}
		}
		return false;
	}

	public static String getContentType(String extension) {
		return extensionContentTypeMap.get(extension.toLowerCase());
	}

	public static String getExtensionInLowerCase(String fileName) {
		return FilenameUtils.getExtension(fileName).toLowerCase();
	}

	public static String getExtensionFromContentType(String contentType) {
		String key = "";
		for (Map.Entry<String, String> entry : extensionContentTypeMap.entrySet()) {
			if ((entry.getValue().equalsIgnoreCase(contentType))) {
				key = entry.getKey();
			}
		}
		return key;
	}

	/**
	 * Get file name trimmed to FILE_NAME_MAX_SIZE and also extension to lower
	 * case (bcoz, people tend to have file in uppercase extension like JPG,
	 * jpg)
	 *
	 * @param fileName
	 *            User's file name
	 * @return
	 */
	public static String getFilteredFileName(String fileName) {
		String extension = getExtensionInLowerCase(fileName);
		String baseName = FilenameUtils.getBaseName(fileName);
		baseName = baseName.replaceAll(" ", "-");
		return StringUtils.getTrimmedString(baseName, FILE_NAME_MAX_SIZE - extension.length()) + "." + extension;
	}

	public static String[] getFileType() {
		FileType[] values = FileType.values();
		String[] fileTypes = new String[values.length];
		int i = 0;
		for (FileType fileType : values) {
			fileTypes[i++] = fileType.getExtention();
		}
		return fileTypes;
	}

	public static String getFilteredFileName(String originalFilename, Class<?> class1) {
		String extension = getExtensionInLowerCase(originalFilename);
		String baseName = FilenameUtils.getBaseName(originalFilename);
		String className = class1.getSimpleName().toLowerCase();
		Date date = new GregorianCalendar().getTime();
		String uniqueTime = Long.toString(date.getTime());
		baseName = baseName.replaceAll(" ", "_");
		return className + "_" + baseName + "_" + uniqueTime + "." + extension;
	}
}
