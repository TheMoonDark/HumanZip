package huffman_zip;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.PriorityQueue;

public class HuffmanTree {
	private HuffmanNode rootNode;
	private CharCounter charCounter;
	private HuffmanNode[] huffmanNodes;
	private long charCountSum;
	private final static int ERROR=-3;
	private final static int TEMPORARY_VALUE=-2;
	
	public HuffmanTree() {
		// TODO Auto-generated constructor stub
		huffmanNodes=new HuffmanNode[256];
		charCounter=new CharCounter();
		rootNode=null;
	}
	/*
	 * 参数为计数器的构造方法
	 */
	public HuffmanTree(CharCounter charCounter) {
		// TODO Auto-generated constructor stub
		this.charCounter=charCounter;
		huffmanNodes=new HuffmanNode[256];
		rootNode=null;
		creatTree();
		charCountSum=charCountSum();
	}
	/*创建树*/
	public void creatTree() {
		PriorityQueue<HuffmanNode> queue=new PriorityQueue<HuffmanNode>();//创建优先队列  用来给结点按字符出现的次数排序
		for(int i=0;i<256;i++) {
			if(charCounter.getCount(i)>0) {
				HuffmanNode node=new HuffmanNode(i, charCounter.getCount(i), null, null,null);//创建树节点
				huffmanNodes[i]=node;//将结点储存
				queue.add(node);//将树结点加入优先队列
			}
		}
		while(queue.size()>1) {
			HuffmanNode n1=queue.remove();//提出队列中对应字符出现次数最小的结点
			HuffmanNode n2=queue.remove();//提出队列中对应字符出现次数第二小的结点
			HuffmanNode N=new HuffmanNode(TEMPORARY_VALUE, n1.getWeight()+n2.getWeight(), n1, n2, null);//组成新的结点
			n1.parent=N;
			n2.parent=N;//将两个最小的结点的父结点设为新结点
			queue.add(N);//将新结点加入优先队列
		}
		rootNode=queue.element();//将队列头部的结点作为根结点
	}
	
	/*压缩*/
	/*得到字节的编码*/

	public int[] getCode(int num) {
		 HuffmanNode current=huffmanNodes[num];
		 if(current==null)
			 return null;
		 String str="";
		 
		 HuffmanNode par=current.parent;
		 
		 while(par!=null) {
			 if(par.lChild==current)
				 str="0"+str;
			 else
				 str="1"+str;
			 current=par;
			 par=par.parent;
		 }
		 int[] code=new int[str.length()];
		 for(int i=0;i<str.length();i++)
			 code[i]=str.charAt( i ) == '0' ? 0 : 1;
		 
		 return code;
	}
	
	/*解压*/
	/*参数为字节的编码，返回值为字节*/
	public int getChar(String code) {
		HuffmanNode node=rootNode;
		for(int i=0;i<code.length()&&node!=null;i++) {
			if(code.charAt(i)==0)
				node=node.lChild;
			else
				node=node.rChild;
		}
		if(node==null)
			return ERROR;
		
		return node.getValue();
	}
	/*参数为自定义字节流，返回值为字节*/
	public int getChar(MyInputStream stream) throws IOException {
		HuffmanNode node=rootNode;
		while(node!=null) {
			int num=stream.readBit();
			if(num==-1)
				return -1;
			if(num==0)
				node=node.lChild;
			else
				node=node.rChild;
			if(node.lChild==null&&node.rChild==null)
				break;
		}
		if(node==null)
			return ERROR;
		return node.getValue();
		
	}
	/*输出编码表*/
	public void writeEncodingTable(DataOutputStream dos) throws IOException {
		for(int i=0;i<256;i++)
			if(charCounter.getCount(i)>0) {
				dos.writeByte(i);
				dos.writeInt(charCounter.getCount(i));
			}
		dos.write(0);
		dos.writeInt(0);
	}
	/*读取编码表*/
	public void readEncodingTable(DataInputStream dis) throws IOException {
		
		for(int i=0;i<256;i++)
			charCounter.setCount(i, 0);
		int value,weight;
		while(true) {
			value=dis.readByte();
			weight=dis.readInt();
			if(weight==0)
				break;
			charCounter.setCount(value, weight);
		}
		creatTree();
		charCountSum=charCounter.charCountSum();
	}
	public long charCountSum() {
		return charCounter.charCountSum();
	}
	
	
}
