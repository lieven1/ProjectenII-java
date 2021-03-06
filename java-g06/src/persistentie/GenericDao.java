package persistentie;

import java.util.List;

public interface GenericDao<T> {
    public List<T> findAll();
    public T get(String id);
    public T update(T object);
    public void delete(T object);
    public void insert(T object);
    public boolean exists(String id);
}
