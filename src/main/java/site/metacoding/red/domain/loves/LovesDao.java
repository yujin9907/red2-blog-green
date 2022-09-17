package site.metacoding.red.domain.loves;


import site.metacoding.red.web.response.loves.GroupByDto;


public interface LovesDao {
    public void insert(Loves loves);
    public void delete(Integer boardsId, Integer usersId);
    public GroupByDto findGroupBy(Integer boardsId);
    public Loves findByPrimary(Integer boardsId, Integer usersId);
}
