package huffman_zip;

import java.io.IOException;
import java.io.OutputStream;

/**
 * �Զ��建�������
 * 
 * @author �µ��
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
		buffer = setBit(buffer, bufferPos++, value);// ������д�뻺����
		if (bufferPos == 8)// ��������ʱ,��ղ�д��out�ļ�
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
