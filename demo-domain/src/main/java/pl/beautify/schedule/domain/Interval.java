package pl.beautify.schedule.domain;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public record Interval(LocalDateTime start, LocalDateTime finish) implements Comparable<Interval> {
    public Interval {
        if (!start.isBefore(finish)) {
            throw new IllegalArgumentException("%s is after %s".formatted(start, finish));
        }
    }

    boolean isOverlapping(Interval interval) {
        return start.isBefore(interval.finish) && interval.start.isBefore(finish);
    }

    boolean overlapsWithAny(Collection<Interval> intervals) {
        for (Interval interval : intervals) {
            if (this.isOverlapping(interval)) {
                return true;
            }
        }
        return false;
    }

    boolean fitsTo(Interval interval) {
        return !start.isBefore(interval.start) && !finish.isAfter(interval.finish);
    }

    List<Interval> split(Interval interval) {
        if (!isOverlapping(interval)) {
            return List.of(this);
        }
        if (this.fitsTo(interval)) {
            return Collections.emptyList();
        }
        if (start.isBefore(interval.start) && !finish.isAfter(interval.finish)) {
            return List.of(new Interval(start, interval.start));
        }
        if (!start.isBefore(interval.start) && !finish.isBefore(interval.finish)) {
            return List.of(new Interval(interval.finish, finish));
        }
        return List.of(
                new Interval(start, interval.start),
                new Interval(interval.finish, finish)
        );
    }

    Interval join(Interval other) {
        if (this.isOverlapping(other) || finish.equals(other.start) || other.finish.equals(start)) {
            LocalDateTime minimumStart = start.isBefore(other.start)? start : other.start;
            LocalDateTime maxFinish = finish.isAfter(other.finish)? finish : other.finish;
            return new Interval(minimumStart, maxFinish);
        }
        throw new IllegalArgumentException("Intervals not overlap %s, %s".formatted(this, other));
    }

    @Override
    public int compareTo(Interval other) {
        return this.start.compareTo(other.start);
    }
}
