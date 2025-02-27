package dao;
import beans.Register;
import beans.Task;
import java.util.List;

public interface ToDoIntf {
	public abstract int register(Register register);
	int login(String email,String pass);
	public int addTask(int regId,Task task);
	public List<Task> findAllTasksByRegId(int regId);
	public boolean markTaskCompleted(int regId, int taskId);
	public String getFnameByRegId(int regId);

}

