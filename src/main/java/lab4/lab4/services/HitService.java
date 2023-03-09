package lab4.lab4.services;

import lab4.lab4.dto.request.CreateHitDTO;
import lab4.lab4.entity.Hit;
import lab4.lab4.repo.HitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HitService {
    private final HitRepository hitRepository;
    private final TimerService timerService;

    @Autowired
    public HitService(HitRepository hitRepository, TimerService timerService) {
        this.hitRepository = hitRepository;
        this.timerService = timerService;
    }

    private boolean leftTop(float x, float y, int r) {
        return false;
    }

    private boolean rightTop(float x, float y, int r) {
        return (Math.pow(x, 2) + Math.pow(y, 2)) <= Math.pow(r, 2);
    }

    private boolean leftBot(float x, float y, int r) {
        return (x > -r && y > -r);
    }

    private boolean rightBot(float x, float y, int r) {
        return (y > 0.5 * (x - r));
    }

    public Boolean checkHit(float x, float y, int r) {
        if (x <= 0 && y >= 0) {
            return leftTop(x, y, r);
        }
        if (x >= 0 && y >= 0) {
            return rightTop(x, y, r);
        }
        if (x <= 0 && y <= 0) {
            return leftBot(x, y, r);
        }
        return rightBot(x, y, r);
    }

    public List<Hit> getMyHits(Long userId) {
        return hitRepository.findAllPointsByUserId(userId);
    }

    public Hit createHit(Long userID, CreateHitDTO createHitDTO) {
        timerService.restart();
        boolean status = this.checkHit(createHitDTO.getX(), createHitDTO.getY(), createHitDTO.getR());
        float executionTime = timerService.getTimerValue();
        return hitRepository.save(new Hit(createHitDTO.getX(), createHitDTO.getY(), createHitDTO.getR(),
                status, timerService.getCurrentTime(), executionTime, userID));
    }
}
