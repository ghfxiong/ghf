package com.ghf.core.socket.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.ghf.core.socket.ISocketServer;
import com.ghf.core.socket.SocketRequest;
import com.ghf.core.socket.SocketResponse;
import com.ghf.core.socket.utils.SerializableUtil;

public class SocketServerNIOImpl implements ISocketServer{
	
	private final static Logger logger = Logger.getLogger(SocketServerNIOImpl.class.getName());  
	  
    private int port;  
  
    public SocketServerNIOImpl(int port) {  
        this.port = port;  
    }
    

	@Override
	public void startup() throws Exception {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Selector selector = null;  
                ServerSocketChannel serverSocketChannel = null;
                
                try {
					selector = Selector.open();
					serverSocketChannel = ServerSocketChannel.open();
					serverSocketChannel.configureBlocking(false);
					
					serverSocketChannel.socket().setReuseAddress(true);
					serverSocketChannel.socket().bind(new InetSocketAddress(port));
					
					serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
					
					while(selector.select()>0){
						Iterator<SelectionKey> it = selector.selectedKeys().iterator();
						while(it.hasNext()){
							try {
								SelectionKey key = it.next();
								it.remove();
								invoke((ServerSocketChannel)key.channel());
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {  
                        selector.close();  
                    } catch(Exception ex) {}  
                    try {  
                        serverSocketChannel.close();  
                    } catch(Exception ex) {}
				}
			}
		}).start();
	}

	private void invoke(ServerSocketChannel channel) throws Exception{
		SocketChannel socketChannel = null;
		try {
			socketChannel = channel.accept();
			SocketRequest request = receiveData(socketChannel); 
			SocketResponse response = execute(request);
			sendData(socketChannel, response);
		} finally{
			socketChannel.close();
		}
		
	}
	private SocketRequest receiveData(SocketChannel socketChannel) throws IOException {  
		SocketRequest myRequest = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {  
            byte[] bytes;  
            int size = 0;  
            while ((size = socketChannel.read(buffer)) >= 0) {  
                buffer.flip();  
                bytes = new byte[size];  
                buffer.get(bytes);  
                baos.write(bytes);  
                buffer.clear();  
            }  
            bytes = baos.toByteArray();  
            Object obj = SerializableUtil.toObject(bytes);  
            myRequest = (SocketRequest)obj;  
        } finally {  
            try {  
                baos.close();  
            } catch(Exception ex) {}  
        }  
        return myRequest;  
    }
	private SocketResponse execute(SocketRequest request)throws Exception{
		Class clazz = request.getRequestClass();  
		  
        String methodName = request.getRequestMethod();  
        Class<?>[] parameterTypes = request.getRequestParameterTypes();  
        Method method = clazz.getDeclaredMethod(methodName, parameterTypes);  
  
        Object[] parameterValues = request.getRequestParameterValues();  
        final Object obj = method.invoke(clazz.newInstance(), parameterValues);  
        //TODO uncheck!
        return (SocketResponse) obj;
	}
	
	private void sendData(SocketChannel socketChannel, SocketResponse response) throws IOException {  
		byte[] bytes = SerializableUtil.toBytes(response);  
        ByteBuffer buffer = ByteBuffer.wrap(bytes);  
        socketChannel.write(buffer);  
    }
	
	@Override
	public void shutdown() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
