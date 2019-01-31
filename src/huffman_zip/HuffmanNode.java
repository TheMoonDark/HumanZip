package huffman_zip;
/**
 * �����������
 * @author �µ��
 *
 */
public class HuffmanNode implements Comparable<HuffmanNode>{
	private int value;//��ֵ
	private int weight;//Ȩ
	protected HuffmanNode lChild;
	protected HuffmanNode rChild;
	protected HuffmanNode parent;
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public HuffmanNode(int value,int weight,HuffmanNode lChild,HuffmanNode rChild,HuffmanNode parent) {
		
		this.value=value;
		this.weight=weight;
		this.lChild=lChild;
		this.rChild=rChild;
		this.parent=parent;
		
	}
	/*ʵ��Comparable�ӿڣ�ʹHuffmanNode����*/
	@Override
	public int compareTo(HuffmanNode o) {
		// TODO Auto-generated method stub
		if(weight<=o.weight)
			return -1;
		else
			return 1;
	}
	
	

}
