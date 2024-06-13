package pl.beautify.schedule.application;

import pl.beautify.schedule.application.port.in.CreateDailyScheduleUseCase;
import pl.beautify.schedule.application.port.out.DailyScheduleRepository;
import pl.beautify.schedule.application.port.out.TransactionPort;
import pl.beautify.schedule.domain.DailySchedule;
import pl.beautify.schedule.domain.state.DailyScheduleState;

import java.util.UUID;

public class DailyScheduleService implements CreateDailyScheduleUseCase {
    private final DailyScheduleRepository dailyScheduleRepository;
    private final TransactionPort transactionPort;

    public DailyScheduleService(DailyScheduleRepository dailyScheduleRepository, TransactionPort transactionPort) {
        this.dailyScheduleRepository = dailyScheduleRepository;
        this.transactionPort = transactionPort;
    }

    @Override
    public DailyScheduleState createDailySchedule(CreateDailyScheduleCommand createCommand) {
        transactionPort.begin();
        DailySchedule dailySchedule = new DailySchedule(
                UUID.randomUUID(),
                createCommand.date(),
                createCommand.businessId());
        dailyScheduleRepository.save(dailySchedule);
        transactionPort.commit();
        return dailySchedule;
    }
}
