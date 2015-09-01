package com.ghf.core.socket;

public interface ISocketClient {
	public <T> T execute(SocketRequest request,SocketResponseHandler<T> handler)throws Exception;
	
	public SocketResponse execute(SocketRequest request);
}
