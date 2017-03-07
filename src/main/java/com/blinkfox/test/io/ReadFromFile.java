package com.blinkfox.test.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;

/**
 * 读取文件的类
 */
public class ReadFromFile {

    /**
     * 私有构造方法
     */
    private ReadFromFile() {
        super();
    }

	/**
	 * @param args 数组参数
	 */
	public static void main(String[] args) {
		String fileName = "F:\\test\\test.md";
		readFileByRandomAccess(fileName);
	}
	
	/**
	 * 通过字节读取文件
	 * 以字节为单位读取文件内容，一次读一个字节
	 * @param fileName 文件路径及名称
	 */
	public static void readFileByByte(String fileName) {
		File file = new File(fileName);
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			int len;
			while ((len = is.read()) != -1) {
				System.out.print("len:" + len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 通过字节读取文件
	 * 以字节为单位读取文件内容，一次读多个字节
	 * @param fileName 文件路径及名称
	 */
	public static void readFileByBytes(String fileName) {
		File file = new File(fileName);
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			byte[] tempBytes = new byte[2*1024];
			int len;
			while ((len = is.read(tempBytes)) != -1) {
				System.out.print("len:" + len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 通过字符读取文件
	 * 以字符为单位读取文件，常用于读文本，数字等类型的文件
	 * 以字符为单位读取文件内容，一次读一个字节
	 * @param fileName 文件路径及名称
	 */
	public static void readFileByChar(String fileName) {
		File file = new File(fileName);
		Reader reader = null;
		try {
			reader = new InputStreamReader(new FileInputStream(file));
			int tempChar;
			while ((tempChar = reader.read()) != -1) {
				// 对于windows下，\r\n这两个字符在一起时，表示一个换行。
                // 但如果这两个字符分开显示时，会换两次行。
                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
				if (((char) tempChar) != '\r') {
					System.out.print("char:" + tempChar);
                }
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 通过字符读取文件
	 * 以字符为单位读取文件，常用于读文本，数字等类型的文件
	 * 以字符为单位读取文件内容，一次读多个字节
	 * @param fileName 文件路径及名称
	 */
	public static void readFileByChars(String fileName) {
		File file = new File(fileName);
		Reader reader = null;
		try {
			reader = new InputStreamReader(new FileInputStream(file));
			char[] tempChars = new char[30];
			int charread;
			while ((charread = reader.read(tempChars)) != -1) {
				// 同样屏蔽掉\r不显示
                if ((charread == tempChars.length)
                        && (tempChars[tempChars.length - 1] != '\r')) {
                    System.out.print(tempChars);
                } else {
                    for (int i = 0; i < charread; i++) {
                        if (tempChars[i] == '\r') {
                            continue;
                        } else {
                            System.out.print(tempChars[i]);
                        }
                    }
                }
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 通过行读取文件
	 * 以行为单位读取文件内容，一次读一整行
	 * @param fileName 文件路径及名称
	 */
	public static void readFileByLine(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			int line = 1;
			String tempStr = null;
			while ((tempStr = reader.readLine()) != null) {
				System.out.println("line " + line + ": " + tempStr);
                line++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 随机读取文件内容
	 * @param fileName 文件路径及名称
	 */
    public static void readFileByRandomAccess(String fileName) {
        RandomAccessFile randomFile = null;
        try {
            System.out.println("随机读取一段文件内容：");
            // 打开一个随机访问文件流，按只读方式
            randomFile = new RandomAccessFile(fileName, "r");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            // 读文件的起始位置
            int beginIndex = (fileLength > 4) ? 4 : 0;
            // 将读文件的开始位置移到beginIndex位置。
            randomFile.seek(beginIndex);
            byte[] bytes = new byte[10];
            int byteread = 0;
            // 一次读10个字节，如果文件内容不足10个字节，则读剩下的字节。
            // 将一次读取的字节数赋给byteread
            while ((byteread = randomFile.read(bytes)) != -1) {
                System.out.write(bytes, 0, byteread);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (randomFile != null) {
                try {
                    randomFile.close();
                } catch (IOException e1) {
                }
            }
        }
    }
    
    /**
     * 显示输入流中还剩的字节数
     */
    public static void showAvailableBytes(InputStream in) {
        try {
            System.out.println("当前字节输入流中的字节数为:" + in.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}