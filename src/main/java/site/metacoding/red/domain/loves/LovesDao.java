package site.metacoding.red.domain.loves;


public interface LovesDao {
    public void insert(Loves loves);
    public void delete(Integer boardsId, Integer usersId);
    public void findGroupBy(Integer boardsId);
}
