package repositories;

import java.util.List;

public interface Repository<T> {
    //nota: funciones CRUD // hace que todas las clases tengan estas funciones y con <T> permitimos que trabaje con cualquier objeto a la hora de implementar esta interfaz

    void save(T t);

    List<T> findAll();

    T findOneById(long id);

    void update(T t);

    void delete(T t);

}
