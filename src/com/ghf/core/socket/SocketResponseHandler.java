package com.ghf.core.socket;

public interface SocketResponseHandler<T> {
	public T handler(SocketResponse response);
}
