package by.piskunou.solvdlaba.persistence;

import by.piskunou.solvdlaba.domain.event.BaseEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<BaseEvent, Long> {

}
