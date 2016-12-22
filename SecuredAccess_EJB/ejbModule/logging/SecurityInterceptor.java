package logging;

import java.io.File;
import java.io.FileWriter;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class SecurityInterceptor {
	
	public static final String LOG_PATH = "/EJB_Security_Logging/";
	
	@Resource
	private SessionContext sessionContext;
	
	/**
	 * Logs EJB method calls as a CSV-file in the format
	 * callerPrincipal,EJBTarget,method,timestamp
	 * @param context InvocationContext
	 * @return
	 * @throws Exception
	 */
	@AroundInvoke
	public Object log(InvocationContext context) throws Exception{
		
		StringBuilder sb = new StringBuilder();
		//adding user principal to trace
		sb.append(sessionContext.getCallerPrincipal().getName());
		sb.append(',');
		//adding calling EJB to trace
		sb.append(context.getTarget().toString());
		sb.append(',');
		//adding called method name to trace
		sb.append(context.getMethod().getName());
		sb.append(',');
		//adding current time to trace
		sb.append(System.currentTimeMillis());
		sb.append('\n');
		
		new File(LOG_PATH).mkdirs();
		try(FileWriter file = new FileWriter(LOG_PATH + "user_trace.txt", true)){
			file.write(sb.toString());
		}
		
		System.out.println("user action logged");
		//proceed method execution
		return context.proceed();
	}

}
