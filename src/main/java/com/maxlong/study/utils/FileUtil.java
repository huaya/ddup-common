package com.maxlong.study.utils;

import com.google.common.base.Splitter;
import com.maxlong.study.exception.MyOwnRuntimeException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 文件操作常用功能
 *
 * @author Nano
 * @version 1.0.0
 * @since 2015/5/25 14:01
 * abacus-parent
 */
public class FileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    private FileUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 拷贝文件
     *
     * @param fromFile
     * @param outputStream
     * @return
     */
    public static boolean copyFile(String fromFile, OutputStream outputStream) {
        boolean copy = false;
        try {
            File srcFile = new File(fromFile);
            FileUtils.copyFile(srcFile, outputStream);
            copy = true;
        } catch (IOException ioe) {
            LOGGER.warn("copy file io exception, fromFile {} :{}", fromFile, ioe.getMessage());
        }
        return copy;
    }

    /**
     * 拷贝文件
     *
     * @param fromFile 来源文件或目录
     * @param toFile   目的目录
     * @return
     */
    public static boolean copyFile(String fromFile, String toFile) {
        boolean copy = false;
        try {
            File srcFile = new File(fromFile);
            File destFile = new File(toFile);
            FileUtils.copyFile(srcFile, destFile);
            copy = true;
        } catch (IOException ioe) {
            LOGGER.warn("copy file io exception, fromFile {}, toFile {} :{}", fromFile, toFile, ioe.getMessage());
        }
        return copy;
    }

    /**
     * 拷贝文件到目录
     *
     * @param fromFile
     * @param toFile
     * @return
     */
    public static boolean copyFileToDirectory(String fromFile, String toFile) {
        boolean copy = false;
        try {
            File srcFile = new File(fromFile);
            File destFile = new File(toFile);
            FileUtils.copyFileToDirectory(srcFile, destFile);
            copy = true;
        } catch (IOException ioe) {
            LOGGER.warn("copy file to directory io exception, fromFile {}, toFile {} :{}", fromFile, toFile, ioe.getMessage());
        }
        return copy;
    }

    /**
     * 拷贝目录到目录
     *
     * @param fromFile
     * @param toFile
     * @param fileFilter
     * @return
     */
    public static boolean copyDirToDir(String fromFile, String toFile, IOFileFilter fileFilter) {
        boolean copy = false;
        try {
            File srcFile = new File(fromFile);
            File destFile = new File(toFile);
            if (fileFilter != null)
                FileUtils.copyDirectory(srcFile, destFile, fileFilter);
            else
                FileUtils.copyDirectory(srcFile, destFile);
            copy = true;
        } catch (IOException ioe) {
            LOGGER.warn("copy file to directory io exception, fromFile {}, toFile {} :{}", fromFile, toFile, ioe.getMessage());
        }
        return copy;
    }


    /**
     * 移动文件
     *
     * @param fromFile 来源文件或目录
     * @param toFile   目的目录
     * @return
     */
    public static boolean moveFile(String fromFile, String toFile) {
        boolean move = false;
        try {
            File srcFile = new File(fromFile);
            File destFile = new File(toFile);
            if (destFile.exists()) {
                Files.delete(destFile.toPath());
            }
            FileUtils.moveFile(srcFile, destFile);
            move = true;
        } catch (IOException ioe) {
            LOGGER.warn("move file io exception, fromFile {}, toFile {} :{}", fromFile, toFile, ioe.getMessage());
        }
        return move;
    }

    /**
     * 移动文件或目录到另外一个目录
     *
     * @param fromFile
     * @param toFile
     * @param createDestDir 是否创建目录
     * @return
     */
    public static boolean moveToDirectory(String fromFile, String toFile, boolean createDestDir) {
        boolean move = false;
        try {
            File srcFile = new File(fromFile);
            File destFile = new File(toFile);
            FileUtils.moveToDirectory(srcFile, destFile, createDestDir);
            move = true;
        } catch (IOException ioe) {
            LOGGER.warn("move file to directory io exception, fromFile {}, toFile {} :{}", fromFile, toFile, ioe.getMessage());
        }
        return move;
    }

    /**
     * 移动目录 重名名
     *
     * @param fromFile
     * @param toFile
     * @return
     */
    public static boolean moveDirectory(String fromFile, String toFile) {
        boolean move = false;
        try {
            File srcFile = new File(fromFile);
            File destFile = new File(toFile);
            FileUtils.moveDirectory(srcFile, destFile);
            move = true;
        } catch (IOException ioe) {
            LOGGER.warn("move directory io exception, fromFile {}, toFile {} :{}", fromFile, toFile, ioe.getMessage());
        }
        return move;
    }

    /**
     * 获得整个文件字节内容
     *
     * @param file
     * @return
     */
    public static byte[] readAllBytesFromFile(File file) {
        byte[] fileBytes = null;
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
            randomAccessFile.seek(0);
            int length = (int) randomAccessFile.length();
            fileBytes = new byte[length];
            randomAccessFile.readFully(fileBytes);
        } catch (IOException ioe) {
            LOGGER.warn("read all bytes from file io exception, fromFile {} :{}", file.getAbsolutePath(), ioe.getMessage());
        }
        return fileBytes;
    }

    /**
     * 获得整个文件字节内容,默认UTF-8
     *
     * @param filePath
     * @return
     */
    public static String readFileToStr(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new MyOwnRuntimeException("file is not found.");
        }
        return readFileToStr(file, "UTF-8");
    }

    /**
     * 获得整个文件字节内容,默认UTF-8
     *
     * @param filePath
     * @return
     */
    public static String readFileToStr(String filePath, String encode) {
        File file = new File(filePath);
        return readFileToStr(file, encode);
    }

    /**
     * 获得整个文件字节内容,默认UTF-8
     *
     * @param file
     * @return
     */
    public static String readFileToStr(File file) {
        return readFileToStr(file, "UTF-8");
    }

    /**
     * 获得整个文件字节内容
     *
     * @param file
     * @param encode
     * @return
     */
    public static String readFileToStr(File file, String encode) {
        byte[] bytes = readAllBytesFromFile(file);
        try {
            return new String(bytes, encode);
        } catch (UnsupportedEncodingException e) {
            throw new MyOwnRuntimeException("readFileToStr error:" + e);
        }
    }


    /**
     * 一行一行读取文件内容 (过滤空行)
     *
     * @param file 获得的文件
     * @param pos  行的起始位置
     * @return 所有行的内容
     */
    public static List<String> readLineFromFile(File file, int pos, String encode) {
        List<String> lines = new ArrayList<>();
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
            randomAccessFile.seek(pos);
            String line;
            while ((line = randomAccessFile.readLine()) != null) {
                if (line.trim().length() > 0)
                    lines.add(new String(line.getBytes("ISO-8859-1"), encode));
            }
        } catch (IOException ioe) {
            throw new MyOwnRuntimeException("readLineFromFile error:" + ioe);
        }
        return lines;
    }

    /**
     * 一行一行读取文件内容 (过滤空行)
     *
     * @param filePath 获得的文件
     * @param pos      行的起始位置
     * @return 所有行的内容
     */
    public static List<String> readLineFromFile(String filePath, int pos, String encode) {
        File file = new File(filePath);
        return readLineFromFile(file, pos, encode);
    }

    /**
     * 打开文件访问通道
     *
     * @param file   文件
     * @param encode 文件编码
     * @return
     */
    public static BufferedReader openBufferReaderFromFile(File file, String encode) {
        BufferedReader reader;
        try (FileInputStream fileInputStream = new FileInputStream(file);
             InputStreamReader input = new InputStreamReader(fileInputStream, encode)) {
            reader = new BufferedReader(input);
        } catch (IOException e) {
            throw new MyOwnRuntimeException("openBufferReaderFromFile error:" + e.getMessage());
        }
        return reader;
    }

    /**
     * 获取按行读取文件记录
     *
     * @param reader
     * @param batchNum
     * @return
     */
    public static List<String> readBatchLineWithReader(BufferedReader reader, int batchNum) {
        List<String> lines = new ArrayList<>(batchNum);
        try {
            if (reader != null) {
                String line;
                int count = 0;
                while (count < batchNum && (line = reader.readLine()) != null) {
                    if (line.trim().length() > 0) {
                        lines.add(line);
                        count = count + 1;
                    }
                }
            }
        } catch (Exception e) {
            throw new MyOwnRuntimeException("readBatchLineFromReader error:" + e.getMessage());
        }
        return lines;
    }

    /**
     * 关闭文件访问通道
     *
     * @param reader
     */
    public static void closeBufferReader(BufferedReader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                throw new MyOwnRuntimeException("closeBufferReader error:" + e.getMessage());
            }
        }
    }

    /**
     * 统计文件行数，过滤空行
     *
     * @param file 文件
     * @param pos  读取起始位置
     * @return
     */
    public static int countLineFromFile(File file, int pos) {
        int count = 0;
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
            randomAccessFile.seek(pos);
            String line;
            while ((line = randomAccessFile.readLine()) != null) {
                if (line.trim().length() > 0)
                    count = count + 1;
            }
        } catch (IOException ioe) {
            LOGGER.warn("count line from file, fromFile {} :{}", file.getAbsolutePath(), ioe.getMessage());
        }
        return count;
    }

    /**
     * 获得文件第一行内容
     *
     * @param file   获得的文件
     * @param encode 字符编码
     * @return 行的内容
     */
    public static String readFirstLineFromFile(File file, String encode) {
        String first = "";
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
            randomAccessFile.seek(0);
            first = new String(randomAccessFile.readLine().getBytes("ISO-8859-1"), encode);
        } catch (IOException ioe) {
            LOGGER.warn("read first line  from file, fromFile {} :{}", file.getAbsolutePath(), ioe.getMessage());
        }
        return first;
    }

    /**
     * 写文件第一行内容
     *
     * @param file   获得的文件
     * @param encode 字符编码
     * @return 行的内容
     */
    public static boolean writeFirstLineToFile(File file, String line, String encode) {
        boolean wfirst = false;
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw")) {
            randomAccessFile.seek(0);
            line = line + "\r\n";
            randomAccessFile.write(line.getBytes(encode));
            wfirst = true;
        } catch (IOException ioe) {
            LOGGER.warn("write first line  from file, fromFile {} :{}", file.getAbsolutePath(), ioe.getMessage());
        }
        return wfirst;
    }

    /**
     * 打开写文件通道
     *
     * @param file   文件
     * @param encode 文件编码
     * @return
     */
    public static BufferedWriter openBufferWriterToFile(File file, String encode) {
        BufferedWriter writer;
        try (FileOutputStream fileOutputStream = new FileOutputStream(file, false);
             OutputStreamWriter out = new OutputStreamWriter(fileOutputStream, encode)) {
            writer = new BufferedWriter(out);
        } catch (IOException e) {
            throw new MyOwnRuntimeException("openBufferWriterFromFile error:" + e.getMessage());
        }
        return writer;
    }

    /**
     * 按行写入文件记录
     *
     * @param writer
     * @param lines
     * @return
     */
    public static boolean writeBatchLineWithWriter(BufferedWriter writer, List<String> lines) {
        boolean write = false;
        try {
            int count = 0;
            if (writer != null && lines != null && !lines.isEmpty()) {
                for (String line : lines) {
                    if (line != null && line.trim().length() > 0) {
                        writer.write(line + "\r\n");
                        count++;
                    }
                }
                if (count <= lines.size()) {/**空行时可能小于*/
                    writer.flush();
                    write = true;
                }
            }
        } catch (Exception e) {
            throw new MyOwnRuntimeException("writeBatchLineWithWriter error:" + e.getMessage());
        }
        return write;
    }

    /**
     * 关闭写文件通道
     *
     * @param writer
     */
    public static void closeBufferWriter(BufferedWriter writer) {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                throw new MyOwnRuntimeException("closeBufferWriter error:" + e.getMessage());
            }
        }
    }

    /**
     * 取代文件某部分的数据内容
     *
     * @param file       被替换的文件
     * @param pos        替换的起始位置
     * @param replaceStr 替换的内容
     * @return
     */
    public static boolean replaceStrToFile(File file, int pos, String replaceStr, String encode) {
        boolean replace = false;
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw")) {
            randomAccessFile.seek(pos);
            randomAccessFile.write(replaceStr.getBytes(encode));
            replace = true;
        } catch (IOException ioe) {
            LOGGER.warn("replace string to file, toFile {} :{}", file.getAbsolutePath(), ioe.getMessage());
        }
        return replace;
    }

    private static final int SIGN_FILE_BUFF_SIZE = 1024 * 1024;//1M

    /**
     * 签名文件
     *
     * @param file      文件
     * @param algorithm 签名算法
     * @return
     */
    public static String signFile(File file, String algorithm) {
        try (InputStream inputStream = new FileInputStream(file)) {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            byte[] buff = new byte[SIGN_FILE_BUFF_SIZE];
            int length = 0;
            while ((length = inputStream.read(buff, 0, buff.length)) != -1) {
                messageDigest.update(buff, 0, length);
            }

            return ByteUtil.bytes2hex(messageDigest.digest());
        } catch (Exception e) {
            throw new MyOwnRuntimeException("readAllBytesFromFile error: " + e.getMessage());
        }
    }

    public static String signReadAllFile(File file, String algorithm) {
        try {
            byte[] fileBytes = FileUtil.readAllBytesFromFile(file);
            if (fileBytes != null) {
                MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
                messageDigest.update(fileBytes);
                return ByteUtil.bytes2hex(messageDigest.digest());
            } else {
                throw new MyOwnRuntimeException("read file bytes is null");
            }
        } catch (Exception e) {
            throw new MyOwnRuntimeException("readAllBytesFromFile error: " + e.getMessage());
        }

    }

    /**
     * 文件的重命名
     *
     * @param file 原文件
     * @param dest 目标文件
     * @return
     */
    public static boolean rename(File file, File dest) {
        if (file.exists()) {
            return file.renameTo(dest);
        }
        return false;
    }

    public static boolean verifyFile(File localFile, String key) {
        return verifyFile(localFile, key, "UTF-8");
    }

    public static boolean verifyFile(File localFile, String key, String encode) {
        String localFilePathWithFileName = localFile.getAbsolutePath();
        return verifyFile(localFilePathWithFileName, key, encode);
    }

    public static boolean verifyFile(String localFilePathWithFileName, String key) {
        return verifyFile(localFilePathWithFileName, key, "UTF-8");
    }

    public static boolean verifyFile(String localFilePathWithFileName, String key, String encode) {
        boolean result = false;
        try {
            File file = new File(localFilePathWithFileName);
            if (!file.exists()) {
                LOGGER.warn("verifyFile local file {} not exists", localFilePathWithFileName);
            } else {
                String firstLine = readFirstLineFromFile(file, encode);
                List<String> lineSplitList = Splitter.on("|").splitToList(firstLine);
                String lineNum = lineSplitList.get(0);
                String orgSignData = lineSplitList.get(1);
                boolean replace = FileUtil.replaceStrToFile(file, 9, key, encode);
                if (replace) {
                    String signData = FileUtil.signFile(file, "SHA-1");
                    if (!signData.equalsIgnoreCase(orgSignData)) {
                        /**标记本地文件失败*/
                        FileUtil.rename(file, new File(localFilePathWithFileName + "." + "verifyfailed." + DateUtil.dateToStr(new Date(), "yyyyMMddHHmmss")));
                        LOGGER.warn("verifyFile verify failed, local file {} orgSignData {}, calcSignData {}", localFilePathWithFileName, orgSignData, signData);
                    } else {
                        replace = FileUtil.replaceStrToFile(file, 9, orgSignData, encode);
                        LOGGER.info("verifyFile recovery local file {} first line result:{}", localFilePathWithFileName, replace ? "successed" : "failed");
                        int countLines = FileUtil.countLineFromFile(file, 0);
                        if (Integer.parseInt(lineNum) != countLines - 1) {
                            LOGGER.info("verifyFile countline failed local file {}, count lineNums:{}, remark lineNums:{}", localFilePathWithFileName, countLines, lineNum);
                        } else {
                            result = true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("verifyFile failed,cause by:{}", e);
        }
        return result;
    }

    public static boolean mkdir(String path) {
        File absLocalFilePathDirectory = new File(path);
        if (!absLocalFilePathDirectory.exists()) {
            try {
                return absLocalFilePathDirectory.mkdirs();
            } catch (Exception e) {
                try {
                    if (!absLocalFilePathDirectory.exists()) {
                        return absLocalFilePathDirectory.mkdirs();
                    }
                } catch (Exception ee) {
                    LOGGER.error("mkdir create file path {} failed,cause by:{}", path, ee);
                }
            }
        } else {
            return true;
        }
        return false;
    }

}
