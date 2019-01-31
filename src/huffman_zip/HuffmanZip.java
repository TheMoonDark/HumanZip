package huffman_zip;

import java.io.*;

public class HuffmanZip {

	// 压缩文件
	public static void compress(String inFile) throws IOException, Exception {
		// 读取数据文件
		if (inFile.equals("")) {
			throw new FileNotFoundException("选择文件为空");
		}
		
		FileInputStream fis = new FileInputStream(inFile);
		CharCounter charCounter = new CharCounter(fis);
		HuffmanTree tree = new HuffmanTree(charCounter);

		fis.close();

		String compressedFile = inFile + ".huf";// 压缩文件的扩展名为.huf
		OutputStream fout = new FileOutputStream(compressedFile);

		DataOutputStream out = new DataOutputStream(fout);
		tree.writeEncodingTable(out); // 往压缩文件中写入码表(字节--出现次数)信息

		fis = new FileInputStream(inFile);// 原文件
		MyOutputStream hzout = new MyOutputStream(fout);// 压缩后的文件

		int ch;
		while ((ch = fis.read()) != -1)
		{
			hzout.writeBits(tree.getCode(ch));
		}
		fis.close();

		hzout.close();
		fout.close();
	}

	// 解压缩文件
	@SuppressWarnings("resource")
	public static void uncompress(String compressedFile) throws IOException, Exception {
		if (compressedFile.equals("")) {
			throw new Exception("选择文件为空");
		}
		
		String outFile;
		String extension;
		InputStream fin = new FileInputStream(compressedFile);
		DataInputStream in = new DataInputStream(fin);

		outFile = compressedFile.substring(0, compressedFile.length() - 4);
		extension = compressedFile.substring(compressedFile.length() - 4);

		if (!extension.equals(".huf"))// 先判断要解压文件的扩展名
			throw new Exception("不是huffman压缩文件");


		HuffmanTree tree = new HuffmanTree();

		// 先从已压缩文件中读出码表(字节---字节出现的次数)信息,并由此信息创建哈夫曼树
		tree.readEncodingTable(in);

		// 解码，开始解码压缩文件
		MyInputStream hzin = new MyInputStream(in);

		OutputStream fout = new FileOutputStream(outFile);// 准备输出文件
		int ch;
		long count = 0;

		// tree.getChar(hzin)方法从BitInputStream流中一位一位的读(然后在树中找节点)，
		// 直到读到一个哈夫曼编码,返回此哈夫曼编码对应的字节值
		while ((ch = tree.getChar(hzin)) != -1) {
			fout.write(ch);// 将解压后的字节写入输出文件
			count++;
			if (count >= tree.charCountSum())
				break;
		}
		fin.close();
		hzin.close();
		fout.close();
	}

}
