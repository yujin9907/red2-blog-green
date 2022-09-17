package site.metacoding.red.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.metacoding.red.domain.loves.Loves;
import site.metacoding.red.domain.loves.LovesDao;

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
}
