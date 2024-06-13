package pl.beautify.schedule.domain;

import pl.beautify.schedule.domain.state.DailyScheduleState;

import java.time.LocalDate;
import java.util.*;

public class DailySchedule implements DailyScheduleState {
    private UUID id;
    private LocalDate date;
    private UUID businessId;
    private SortedSet<Interval> workingSlots = new TreeSet<>();
    private List<Appointment> appointments = new ArrayList<>();

    public DailySchedule(UUID id, LocalDate date, UUID businessId) {
        this.id = id;
        this.date = date;
        this.businessId = businessId;
    }

    void addWorkingIntervalSlot(Interval interval) {
        if (interval.overlapsWithAny(workingSlots)) {
            Interval overlappingInterval = findOverlappingInterval(interval);
            Interval joinedInterval = overlappingInterval.join(interval);
            workingSlots.remove(overlappingInterval);
            workingSlots.add(joinedInterval);
        } else {
            workingSlots.add(interval);
        }
    }

    private Interval findOverlappingInterval(Interval interval) {
        for (Interval workingInterval : workingSlots) {
            if (workingInterval.isOverlapping(interval)) {
                return workingInterval;
            }
        }
        return null;
    }

    void makeAppointment(Appointment appointment) {
        if (!fitsToWorkingHours(appointment)) {
            throw new DailyScheduleException("pl.beautify.schedule.domain.Appointment does not fit to any working slot");
        }
        if (overlapsWithOtherAppointment(appointment)) {
            throw new DailyScheduleException("pl.beautify.schedule.domain.Appointment collide with another appointment");
        }
        appointments.add(appointment);
    }

    void cancelAppointment(Appointment appointment) {
        boolean removed = appointments.remove(appointment);
        appointment.setCancelled(true);
    }

    private boolean fitsToWorkingHours(Appointment appointment) {
        Interval appointmentInterval = appointment.getInterval();
        for (Interval workingSlot : workingSlots) {
            if (appointmentInterval.fitsTo(workingSlot)) {
                return true;
            }
        }
        return false;
    }

    private boolean overlapsWithOtherAppointment(Appointment appointment) {
        Interval appointmentInterval = appointment.getInterval();
        for (Appointment scheduledAppointment : appointments) {
            Interval scheduledAppointmentInterval = scheduledAppointment.getInterval();
            if (appointmentInterval.isOverlapping(scheduledAppointmentInterval)) {
                return true;
            }
        }
        return false;
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public UUID getBusinessId() {
        return businessId;
    }

    public SortedSet<Interval> getWorkingSlots() {
        return Collections.unmodifiableSortedSet(workingSlots);
    }

    public List<Appointment> getAppointments() {
        return Collections.unmodifiableList(appointments);
    }
}
