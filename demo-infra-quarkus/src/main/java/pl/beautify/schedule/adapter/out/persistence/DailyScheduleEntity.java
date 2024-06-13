package pl.beautify.schedule.adapter.out.persistence;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.UUID;

@Entity
class DailyScheduleEntity {
    @Id
    private UUID id;
    private LocalDate scheduleDate;
    private UUID businessId;

    public DailyScheduleEntity() {
    }

    public DailyScheduleEntity(UUID id, LocalDate scheduleDate, UUID businessId) {
        this.id = id;
        this.scheduleDate = scheduleDate;
        this.businessId = businessId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(LocalDate scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public UUID getBusinessId() {
        return businessId;
    }

    public void setBusinessId(UUID businessId) {
        this.businessId = businessId;
    }
}
