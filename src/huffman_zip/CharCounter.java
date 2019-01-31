package huffman_zip;

import java.io.IOException;
import java.io.InputStream;

public class CharCounter {
	private int[] count=new int[256];
	public CharCounter() {
		// TODO Auto-generated constructor stub
	}
	/*统计字符个数*/
	public CharCounter(InputStream ips) {
		// TODO Auto-generated constructor stub
		int i;
		try {
			while((i=ips.read())!=-1) {
				count[i]++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getCount(int i) {
		return count[i& 0xff];
	}
	public void setCount(int i,int num) {
		this.count[i& 0xff] = num;
	}
	/*求总字节数*/
	public long charCountSum() {
		long sum = 0;
		for(int i=0;i<count.length;i++) {
			sum+=count[i];
		}
		return sum;
	}
	
}
