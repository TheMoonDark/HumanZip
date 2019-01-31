package huffman_zip;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 自定义缓存输出流
 * 
 * @author 郝德琛
 *
 */
public class MyOutputStream {

	private OutputStream output;
	private int buffer;
	private int bufferPos;

	public MyOutputStream(OutputStream ops) {
		bufferPos = 0;
		buffer = 0;
		output = ops;
	}

	public void writeBit(int value) throws IOException {
		buffer = setBit(buffer, bufferPos++, value);// 将数据写入缓存区
		if (bufferPos == 8)// 缓存区满时,清空并写入out文件
			flush();
	}

	public void writeBits(int[] value) throws IOException {
		for (int v : value)
			writeBit(v);
	}

	public void flush() throws IOException {
		if (bufferPos == 0)
			return;
		output.write(buffer);
		bufferPos = 0;
		buffer = 0;
	}

	public void close() throws IOException {
		flush();
		output.close();
	}

	private int setBit(int pack, int pos, int value) {
		if (value == 1)
			pack |= (value << pos);
		return pack;
	}
}
