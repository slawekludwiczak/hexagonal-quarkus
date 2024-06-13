package pl.beautify.schedule.adapter.out.persistence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import pl.beautify.schedule.application.port.out.DailyScheduleRepository;
import pl.beautify.schedule.domain.state.DailyScheduleState;

@ApplicationScoped
class DailyScheduleRepositoryAdapter implements DailyScheduleRepository {
    private final PanacheDailyScheduleRepository panacheDailyScheduleRepository;

    @Inject
    public DailyScheduleRepositoryAdapter(PanacheDailyScheduleRepository panacheDailyScheduleRepository) {
        this.panacheDailyScheduleRepository = panacheDailyScheduleRepository;
    }

    @Override
    public void save(DailyScheduleState dailySchedule) {
        DailyScheduleEntity dailyScheduleEntity = new DailyScheduleEntity(
                dailySchedule.getId(),
                dailySchedule.getDate(),
                dailySchedule.getBusinessId()
        );
        panacheDailyScheduleRepository.persist(dailyScheduleEntity);
        System.out.println("Saved ");
    }
}
