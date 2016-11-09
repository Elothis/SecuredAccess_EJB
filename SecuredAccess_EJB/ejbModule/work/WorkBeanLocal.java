package work;

import javax.ejb.Local;

@Local
public interface WorkBeanLocal {
	public String administrationTask();
	public String managingTask();
	public String basicTask();
}
