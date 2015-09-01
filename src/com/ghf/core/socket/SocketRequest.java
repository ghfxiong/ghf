package com.ghf.core.socket;

import java.io.Serializable;

public interface SocketRequest extends Serializable{
	Class<?> getRequestClass();  
	  
    String getRequestMethod();  
  
    Class<?>[] getRequestParameterTypes();  
  
    Object[] getRequestParameterValues();
}
