package site.metacoding.red.domain.loves;


import ch.qos.logback.core.util.InvocationGate;
import site.metacoding.red.web.response.loves.LovesDto;

public interface LovesDao {
    public void insert(Loves loves);
    public void delete(Loves loves);
    //public GroupByDto findGroupBy(Integer boardsId);
    public Loves findByPrimary(Integer boardsId, Integer usersId);

    public LovesDto findByBoardsId(Integer principalId, Integer boardsId);
}
