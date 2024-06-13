package pl.beautify;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import pl.beautify.schedule.application.DailyScheduleService;
import pl.beautify.schedule.application.port.out.DailyScheduleRepository;
import pl.beautify.schedule.application.port.out.TransactionPort;

@ApplicationScoped
class AppConfig {

    @Produces
    DailyScheduleService dailyScheduleService(DailyScheduleRepository repository, TransactionPort transactionPort) {
        return new DailyScheduleService(repository, transactionPort);
    }

}
