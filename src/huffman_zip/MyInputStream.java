package huffman_zip;

import java.io.*;

public class MyInputStream {
    private InputStream in;//�ļ�������
    private int buffer;//��λ�Ļ���������in�����һ���ֽ������ȷ�������
    private int bufferPos;//��������־


    public MyInputStream( InputStream is ){
        in = is;
        bufferPos = 8;
    }
    
    //�ӻ������ж�һλ����,��������λ�����ȶ�,��λ���,�������������ݶ���ʱ,�ٴ�in�ж�һ�ֽڷ��뻺����
    public int readBit( ) throws IOException{
        if ( bufferPos == 8 ){
            buffer = in.read( );//���������ж�һ���ֽ�
            if( buffer == -1 )
                return -1;
            bufferPos = 0;
        }
        return getBit( buffer, bufferPos++ );    
    }
    
    public void close( ) throws IOException{
        in.close( );
    }
    
    private static int getBit( int pack, int pos ){
        return ( pack & ( 1 << pos ) ) != 0 ? 1 : 0;
    }
}
