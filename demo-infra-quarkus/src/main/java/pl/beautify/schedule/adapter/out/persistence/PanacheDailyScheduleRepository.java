package pl.beautify.schedule.adapter.out.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
class PanacheDailyScheduleRepository implements PanacheRepository<DailyScheduleEntity> {
}
