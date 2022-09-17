package site.metacoding.red.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.metacoding.red.domain.loves.Loves;
import site.metacoding.red.domain.loves.LovesDao;
import site.metacoding.red.web.response.loves.GroupByDto;
import site.metacoding.red.web.response.loves.webDto;

@RequiredArgsConstructor
@Service
public class LovesService {

    private final LovesDao lovesDao;

    public void 좋아요클릭(Integer boardsId, Integer usersId){
        Loves loves = new Loves();
        loves.toEntity(boardsId, usersId);
        lovesDao.insert(loves);
    }
    public void 좋아요취소(Integer boardsId, Integer usersId){
        lovesDao.delete(boardsId, usersId);
    }
    public GroupByDto 좋아요수확인(Integer boardsId, Integer usersId){
        GroupByDto loveCount = lovesDao.findGroupBy(boardsId);

        return loveCount;
    }
    public Loves 좋아요체크(Integer boardsId, Integer usersId){
        return lovesDao.findByPrimary(boardsId, usersId);
    }
}
