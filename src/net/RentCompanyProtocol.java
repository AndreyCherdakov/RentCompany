package net;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import net.server.ProtocolJava;
import server.model.IRentCompany;

public class RentCompanyProtocol implements ProtocolJava {
	
	IRentCompany company;
	private static Map<Class<?>,Method> PARSERS = primitivesMap();

	public RentCompanyProtocol(IRentCompany company) {
	super();
	this.company = company;
}

	@Override
	public ResponseJava getResponse(RequestJava request) {
		System.out.println(request.requestType + " - " + request.requestData);
		return getData(request);
	}
	
	private ResponseJava getData(RequestJava dataRequest) {
		Class<?>[] clazzParam = null;
		Method method = null;
		Serializable response = null;
		Serializable params = dataRequest.requestData;
		
		for (Method m : company.getClass().getDeclaredMethods()) {
			if (m.getName().equals(dataRequest.requestType)) {
				clazzParam = m.getParameterTypes();
				method = m;
			}
		}
		
		Object[] arrParams = new Object[method.getParameters().length];
		
		if (method.getParameters().length == 1) {
			arrParams[0] = params;
		}
		else if (method.getParameters().length > 1){
			String[] strTemp = new String[method.getParameters().length];
			strTemp = params.toString().split(";");
			arrParams = changeDataType(clazzParam, strTemp);
		}
		method.setAccessible(true);
		try {
			response = (Serializable) method.invoke(company, arrParams);
		} catch (Exception e) {
			new ResponseJava(TcpResponseCode.WRONG_REQUEST, LocalDateTime.now() + ": ERROR - " + e.getMessage());
		}
		return new ResponseJava(TcpResponseCode.OK, response);
	}
	
	private Object[] changeDataType(Class<?>[] clazz, String[] strTemp) {
		Object[] obj = new Object[strTemp.length];
		for (int i = 0; i < obj.length; i++) {
			try {
				obj[i] = PARSERS.get(clazz[i]).invoke(null, strTemp[i]);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					e) {
				throw new RuntimeException(e);
			}
		} 
		return obj;
	}
	
	public static Map<Class<?>, Method> primitivesMap(){
		HashMap<Class<?>, Method> result = new HashMap<>();
		try {
			result.put(byte.class, Byte.class.getMethod("parseByte", String.class));
			result.put(short.class, Short.class.getMethod("parseShort", String.class));
			result.put(char.class, RentCompanyProtocol.class.getMethod("parseChar", String.class));
			result.put(int.class, Integer.class.getMethod("parseInt", String.class));
			result.put(long.class, Long.class.getMethod("parseLong", String.class));
			result.put(float.class, Float.class.getMethod("parseFloat", String.class));
			result.put(double.class, Double.class.getMethod("parseDouble", String.class));
			result.put(boolean.class, Boolean.class.getMethod("parseBoolean", String.class));
			result.put(LocalDate.class, LocalDate.class.getMethod("parse", CharSequence.class));
			result.put(String.class, RentCompanyProtocol.class.getMethod("parseString", String.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return result;
	}
	
	public static char parseChar(String c) {
		return c.charAt(0);
	}
	
	public static String parseString(String c) {
		return c;
	}
}
