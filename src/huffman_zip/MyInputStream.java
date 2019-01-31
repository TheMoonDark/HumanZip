package huffman_zip;

import java.io.*;

public class MyInputStream {
    private InputStream in;//文件输入流
    private int buffer;//八位的缓存区，从in读入的一个字节数据先放在这里
    private int bufferPos;//缓存区标志


    public MyInputStream( InputStream is ){
        in = is;
        bufferPos = 8;
    }
    
    //从缓存区中读一位数据,缓存区低位数据先读,高位后读,当缓存区的数据读完时,再从in中读一字节放入缓存区
    public int readBit( ) throws IOException{
        if ( bufferPos == 8 ){
            buffer = in.read( );//从输入流中读一个字节
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
