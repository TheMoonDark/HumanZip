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
	 * ����Ϊ�������Ĺ��췽��
	 */
	public HuffmanTree(CharCounter charCounter) {
		// TODO Auto-generated constructor stub
		this.charCounter=charCounter;
		huffmanNodes=new HuffmanNode[256];
		rootNode=null;
		creatTree();
		charCountSum=charCountSum();
	}
	/*������*/
	public void creatTree() {
		PriorityQueue<HuffmanNode> queue=new PriorityQueue<HuffmanNode>();//�������ȶ���  ��������㰴�ַ����ֵĴ�������
		for(int i=0;i<256;i++) {
			if(charCounter.getCount(i)>0) {
				HuffmanNode node=new HuffmanNode(i, charCounter.getCount(i), null, null,null);//�������ڵ�
				huffmanNodes[i]=node;//����㴢��
				queue.add(node);//�������������ȶ���
			}
		}
		while(queue.size()>1) {
			HuffmanNode n1=queue.remove();//��������ж�Ӧ�ַ����ִ�����С�Ľ��
			HuffmanNode n2=queue.remove();//��������ж�Ӧ�ַ����ִ����ڶ�С�Ľ��
			HuffmanNode N=new HuffmanNode(TEMPORARY_VALUE, n1.getWeight()+n2.getWeight(), n1, n2, null);//����µĽ��
			n1.parent=N;
			n2.parent=N;//��������С�Ľ��ĸ������Ϊ�½��
			queue.add(N);//���½��������ȶ���
		}
		rootNode=queue.element();//������ͷ���Ľ����Ϊ�����
	}
	
	/*ѹ��*/
	/*�õ��ֽڵı���*/

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
	
	/*��ѹ*/
	/*����Ϊ�ֽڵı��룬����ֵΪ�ֽ�*/
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
	/*����Ϊ�Զ����ֽ���������ֵΪ�ֽ�*/
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
	/*��������*/
	public void writeEncodingTable(DataOutputStream dos) throws IOException {
		for(int i=0;i<256;i++)
			if(charCounter.getCount(i)>0) {
				dos.writeByte(i);
				dos.writeInt(charCounter.getCount(i));
			}
		dos.write(0);
		dos.writeInt(0);
	}
	/*��ȡ�����*/
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
