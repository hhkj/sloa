package cn.slkj.hbsl.util.javaUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @作者 Goofy
 * @邮件 252878950@qq.com
 * @日期 2014-1-25
 * @描述 文件解压缩
 */
public class FileUnzip {

	/**
	 * 文件解压缩 注意 不能有中文文件名
	 * 
	 * @param zipSrc
	 *            zip文件路径
	 * @param dest
	 *            解压的路径，无需以“\\”或者“/”结尾
	 * @throws IOException
	 */
	public static void unzip(String zipSrc, String dest) throws IOException {

		// 为了适配不同操作系统的文件路径的分隔符
		String systemSeparator = System.getProperty("file.separator");
		System.out.println(systemSeparator);

		CheckedInputStream cis = new CheckedInputStream(new FileInputStream(new File(zipSrc)), new CRC32());
		ZipInputStream zis = new ZipInputStream(cis);
		ZipEntry ze = null;
		FileOutputStream fos = null;
		byte[] buffer = new byte[1024];
		int length = 0;

		if (!new File(dest).exists()) new File(dest).mkdirs();

		while ((ze = zis.getNextEntry()) != null) {
			String fileName = dest + File.separator + ze.getName();
			// 都替换一遍省心
			fileName = fileName.replace("\\", systemSeparator).replace("/", systemSeparator);
			if (fileName.lastIndexOf(systemSeparator) != -1) new File(fileName.substring(0, fileName.lastIndexOf(systemSeparator))).mkdirs();
			if (fileName.lastIndexOf(systemSeparator) == fileName.length() - 1) continue;
			System.out.println(fileName);
			fos = new FileOutputStream(fileName);
			while ((length = zis.read(buffer)) != -1) {
				fos.write(buffer, 0, length);
			}
			fos.close();
		}
		zis.closeEntry();
		zis.close();
	}

}