package com.jessrun.platform.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.FileWriterWithEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jessrun.platform.exception.ServiceException;
import com.thoughtworks.xstream.XStream;

public class FileUtil {

    private static final Logger log                      = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 默认字符集编码
     */
    public static final String  DEFAULT_CHARSET_ENCODING = "UTF-8";

    public static final String  LINE_SEPARATOR           = System.getProperty("line.separator");

    /**
     * 获取文件内容
     * 
     * @param file
     * @return
     */
    public static String getFileContent(File file) throws IOException {
        if (!isFile(file)) {
            return StringUtils.EMPTY_STRING;
        }

        StringBuilder sBuilder = new StringBuilder(10240);
        BufferedReader fr = null;

        try {
            fr = new BufferedReader(new FileReader(file));
            String line = null;

            while ((line = fr.readLine()) != null) {
                sBuilder.append(line).append(LINE_SEPARATOR);
            }
        } finally {
            IOUtils.closeQuietly(fr);
        }

        return sBuilder.toString();
    }

    /**
     * 输出内容到文件中
     * 
     * @param content 文件内容
     * @param basePath 文件目录
     * @param fileName 文件名
     * @param append 如果文件存在，是否追加
     * @throws IOException
     */
    public static void writeToFile(String content, String basePath, String fileName, boolean append) throws IOException {
        File file = getFile(basePath, fileName);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file, append));
            bw.write(content);
            bw.flush();
        } finally {
            IOUtils.closeQuietly(bw);
        }
    }

    /**
     * 根据文件目录和文件名获取文件
     * 
     * @param basePath 文件目录
     * @param fileName 文件名
     * @return
     */
    public static File getFile(String basePath, String fileName) {
        File file = null;

        if (StringUtils.isNullOrEmpty(basePath)) {
            file = new File(fileName);
        } else {
            file = new File(basePath, fileName);
        }
        return file;
    }

    /**
     * 判断文件是否存在，且为文件
     * 
     * @param file
     * @return
     */
    public static boolean isFile(File file) {
        return (null != file) && (file.exists()) && (file.isFile());
    }

    /**
     * 将内如写入到filePath里
     * 
     * @author zhouhj 2011-07-29 11:03
     * @param filePath
     * @param content
     * @throws Exception
     */
    public static void writeToFile(String content, String filePath, boolean append) throws ServiceException {
        deleteFileIfExist(filePath);
        FileWriterWithEncoding fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriterWithEncoding(filePath, DEFAULT_CHARSET_ENCODING, append);
            bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write(content);
            bw.flush();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new ServiceException(e);
        } finally {
            IOUtils.closeQuietly(bw);
        }
    }

    /**
     * 将content的XML文件内容写入到filePath
     * 
     * @param filePath
     * @param xstream
     * @param content
     * @throws ServiceException
     */
    public static void writeToFilePath(String filePath, XStream xstream, Object content) throws ServiceException {
        OutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = new FileOutputStream(new File(filePath));
            writer = new BufferedWriter(new OutputStreamWriter(out, DEFAULT_CHARSET_ENCODING));
            xstream.toXML(content, writer);
        } catch (Exception e) {
            log.error("method exportCateAttrTreeData error", e);
            throw new ServiceException(e);
        } finally {
            if (null != writer) {
                IOUtils.closeQuietly(writer);
            }
        }
    }

    /**
     * 判断文件是否存在，如果存在，则先删除
     * 
     * @author zhouhj 2011-07-29 11:03
     * @param filePath
     */
    private static void deleteFileIfExist(String filePath) {
        File file = new File(filePath);
        if (isFile(file)) {
            file.delete();
        }
    }

    /**
     * 文件复制
     * 
     * @author tss 2012-03-05 22:18
     * @param srcFilePath 来源文件
     * @param destFilePath 目标文件
     */
    public synchronized static void copyFile(String srcFilePath, String destFilePath) {

        // 参数校验
        if (StringUtils.isNullOrEmpty(srcFilePath) || StringUtils.isNullOrEmpty(destFilePath)) {
            return;
        }
        File srcFile = new File(srcFilePath);
        if (!srcFile.isFile()) {
            return;
        }
        // 创建目标文件
        File destFile = new File(destFilePath);
        File parentDestFile = destFile.getParentFile();
        if (!parentDestFile.exists()) {
            parentDestFile.mkdirs();
        }
        if (!FileUtil.isFile(destFile)) {
            try {
                destFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 文件复制处理
        InputStream fis = null;
        OutputStream fos = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            // 定义文件字节流对象
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            // 定义字节缓冲对象
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);
            byte b[] = new byte[1024 * 1024]; // 1M
            int len = b.length;
            int off = 0;
            while ((len = bis.read(b, off, len)) != -1) {
                bos.write(b, off, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭流,关闭顺序 （写入到读出，高级流到低级流）
            try {
                if (bos != null) {
                    bos.flush();
                    bos.close();
                }
            } catch (Exception e) {
            }
            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (Exception e) {
            }
            try {
                if (fos != null) {
                    fos.flush();
                    fos.close();
                }
            } catch (Exception e) {
            }
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (Exception e) {
            }

        }
    }

}
