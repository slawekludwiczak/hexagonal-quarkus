package pl.beautify;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import pl.beautify.schedule.application.DailyScheduleService;
import pl.beautify.schedule.application.port.in.CreateDailyScheduleUseCase.CreateDailyScheduleCommand;
import pl.beautify.schedule.domain.state.DailyScheduleState;

import java.time.LocalDate;
import java.util.UUID;

@Path("/hello")
public class ExampleResource {
    private final DailyScheduleService dailyScheduleService;

    @Inject
    public ExampleResource(DailyScheduleService dailyScheduleService) {
        this.dailyScheduleService = dailyScheduleService;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public UUID hello() {
        CreateDailyScheduleCommand command = new CreateDailyScheduleCommand(LocalDate.now(), UUID.randomUUID());
        DailyScheduleState dailySchedule = dailyScheduleService.createDailySchedule(command);
        return dailySchedule.getId();
    }
}
