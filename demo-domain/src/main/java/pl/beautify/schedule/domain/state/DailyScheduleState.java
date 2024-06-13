package pl.beautify.schedule.domain.state;

import pl.beautify.schedule.domain.Appointment;
import pl.beautify.schedule.domain.DailySchedule;
import pl.beautify.schedule.domain.Interval;

import java.time.LocalDate;
import java.util.List;
import java.util.SortedSet;
import java.util.UUID;

public interface DailyScheduleState {
    UUID getId();
    LocalDate getDate();
    UUID getBusinessId();
    SortedSet<Interval> getWorkingSlots();
    List<Appointment> getAppointments();

    static DailyScheduleState of(UUID id, LocalDate date, UUID businessId) {
        return new DailySchedule(id, date, businessId);
    }
}
