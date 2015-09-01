package com.ghf.core.socket.impl;

import com.ghf.core.socket.ISocketClient;
import com.ghf.core.socket.SocketRequest;
import com.ghf.core.socket.SocketResponse;
import com.ghf.core.socket.SocketResponseHandler;

public class SocketClientNIOImpl implements ISocketClient{

	private String host;  
    private int port; 
    
    public SocketClientNIOImpl(String host,int port){
    	this.host = host;
    	this.port = port;
    }
	
	@Override
	public <T> T execute(SocketRequest request, SocketResponseHandler<T> handler)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SocketResponse execute(SocketRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
