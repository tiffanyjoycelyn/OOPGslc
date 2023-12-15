package repo;

import java.util.List;

public interface Repository {
	public void read();
	public void insert(String str);
	public void find(String columnName, String condition, String joinBool, String joinTableName);

}
