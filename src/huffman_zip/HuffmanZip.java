package huffman_zip;

import java.io.*;

public class HuffmanZip {

	// ѹ���ļ�
	public static void compress(String inFile) throws IOException, Exception {
		// ��ȡ�����ļ�
		if (inFile.equals("")) {
			throw new FileNotFoundException("ѡ���ļ�Ϊ��");
		}
		
		FileInputStream fis = new FileInputStream(inFile);
		CharCounter charCounter = new CharCounter(fis);
		HuffmanTree tree = new HuffmanTree(charCounter);

		fis.close();

		String compressedFile = inFile + ".huf";// ѹ���ļ�����չ��Ϊ.huf
		OutputStream fout = new FileOutputStream(compressedFile);

		DataOutputStream out = new DataOutputStream(fout);
		tree.writeEncodingTable(out); // ��ѹ���ļ���д�����(�ֽ�--���ִ���)��Ϣ

		fis = new FileInputStream(inFile);// ԭ�ļ�
		MyOutputStream hzout = new MyOutputStream(fout);// ѹ������ļ�

		int ch;
		while ((ch = fis.read()) != -1)
		{
			hzout.writeBits(tree.getCode(ch));
		}
		fis.close();

		hzout.close();
		fout.close();
	}

	// ��ѹ���ļ�
	@SuppressWarnings("resource")
	public static void uncompress(String compressedFile) throws IOException, Exception {
		if (compressedFile.equals("")) {
			throw new Exception("ѡ���ļ�Ϊ��");
		}
		
		String outFile;
		String extension;
		InputStream fin = new FileInputStream(compressedFile);
		DataInputStream in = new DataInputStream(fin);

		outFile = compressedFile.substring(0, compressedFile.length() - 4);
		extension = compressedFile.substring(compressedFile.length() - 4);

		if (!extension.equals(".huf"))// ���ж�Ҫ��ѹ�ļ�����չ��
			throw new Exception("����huffmanѹ���ļ�");


		HuffmanTree tree = new HuffmanTree();

		// �ȴ���ѹ���ļ��ж������(�ֽ�---�ֽڳ��ֵĴ���)��Ϣ,���ɴ���Ϣ������������
		tree.readEncodingTable(in);

		// ���룬��ʼ����ѹ���ļ�
		MyInputStream hzin = new MyInputStream(in);

		OutputStream fout = new FileOutputStream(outFile);// ׼������ļ�
		int ch;
		long count = 0;

		// tree.getChar(hzin)������BitInputStream����һλһλ�Ķ�(Ȼ���������ҽڵ�)��
		// ֱ������һ������������,���ش˹����������Ӧ���ֽ�ֵ
		while ((ch = tree.getChar(hzin)) != -1) {
			fout.write(ch);// ����ѹ����ֽ�д������ļ�
			count++;
			if (count >= tree.charCountSum())
				break;
		}
		fin.close();
		hzin.close();
		fout.close();
	}

}
