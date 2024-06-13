package pl.beautify.schedule.application.port.in;

import pl.beautify.schedule.application.DailyScheduleService;
import pl.beautify.schedule.application.port.out.DailyScheduleRepository;
import pl.beautify.schedule.domain.state.DailyScheduleState;

import java.time.LocalDate;
import java.util.ServiceLoader;
import java.util.UUID;

public interface CreateDailyScheduleUseCase {
    DailyScheduleState createDailySchedule(CreateDailyScheduleCommand createCommand);

    record CreateDailyScheduleCommand(LocalDate date, UUID businessId) { }
}
