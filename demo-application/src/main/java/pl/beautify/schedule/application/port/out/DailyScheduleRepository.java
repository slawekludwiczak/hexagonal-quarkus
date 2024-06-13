package pl.beautify.schedule.application.port.out;

import pl.beautify.schedule.domain.state.DailyScheduleState;

public interface DailyScheduleRepository {
    void save(DailyScheduleState dailySchedule);
}
