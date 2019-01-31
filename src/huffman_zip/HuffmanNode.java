package huffman_zip;
/**
 * 哈夫曼树结点
 * @author 郝德琛
 *
 */
public class HuffmanNode implements Comparable<HuffmanNode>{
	private int value;//数值
	private int weight;//权
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
	/*实现Comparable接口，使HuffmanNode排序*/
	@Override
	public int compareTo(HuffmanNode o) {
		// TODO Auto-generated method stub
		if(weight<=o.weight)
			return -1;
		else
			return 1;
	}
	
	

}
