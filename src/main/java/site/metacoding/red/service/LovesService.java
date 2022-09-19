package site.metacoding.red.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.metacoding.red.domain.loves.Loves;
import site.metacoding.red.domain.loves.LovesDao;
import site.metacoding.red.web.response.loves.GroupByDto;

@RequiredArgsConstructor
@Service
public class LovesService {

    private final LovesDao lovesDao;

    public void 좋아요클릭(Integer boardsId, Integer usersId){
        Loves loves = new Loves(boardsId, usersId);
        lovesDao.insert(loves);
    }
    public void 좋아요취소(Integer boardsId, Integer usersId){
        lovesDao.delete(boardsId, usersId);
    }

    public GroupByDto 좋아요확인(Integer boardsId){
        GroupByDto lovePS = new GroupByDto(0);

        GroupByDto loveCount = lovesDao.findGroupBy(boardsId);
        if(loveCount!=null){
            lovePS.setLoveCount(loveCount.getLoveCount());
        }

        return loveCount;
    }

    public boolean 좋아요체크(Integer boardsId, Integer usersId){
        Loves lovesPS = lovesDao.findByPrimary(boardsId, usersId);
        if(lovesPS==null){
            return false;
        } else {
            return true;
        }
    }
}
