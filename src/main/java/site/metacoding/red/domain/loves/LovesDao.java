package site.metacoding.red.domain.loves;

import site.metacoding.red.web.response.boards.DetailDto;

public interface LovesDao {
    public void insert(Loves loves);
    public void delete(Integer lovesId);
    //public GroupByDto findGroupBy(Integer boardsId);
    // public Loves findByPrimary(Integer boardsId, Integer usersId);

    public DetailDto findByDetail(Integer principalId, Integer boardsId);
}
