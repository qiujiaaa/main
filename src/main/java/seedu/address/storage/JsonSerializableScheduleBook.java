package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyDataBook;
import seedu.address.model.ScheduleBook;
import seedu.address.model.schedule.Schedule;

/**
 * An Immutable ScheduleBook that is serializable to JSON format.
 */
@JsonRootName(value = "schedulebook")
class JsonSerializableScheduleBook {

    public static final String MESSAGE_DUPLICATE_SCHEDULE = "schedules list contains duplicate schedule(s).";

    private final List<JsonAdaptedSchedule> schedules = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableScheduleBook} with the given schedule.
     */
    @JsonCreator
    public JsonSerializableScheduleBook(@JsonProperty("schedules") List<JsonAdaptedSchedule> schedules) {
        this.schedules.addAll(schedules);
    }

    /**
     * Converts a given {@code ReadOnlyScheduleBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableScheduleBook}.
     */
    public JsonSerializableScheduleBook(ReadOnlyDataBook<Schedule> source) {
        schedules.addAll(source.getList().stream().map(JsonAdaptedSchedule::new).collect(Collectors.toList()));
    }

    /**
     * Converts this schedule book into the model's {@code ScheduleBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ScheduleBook toModelType() throws IllegalValueException {
        ScheduleBook scheduleBook = new ScheduleBook();
        for (JsonAdaptedSchedule jsonAdaptedSchedule : schedules) {
            Schedule schedule = jsonAdaptedSchedule.toModelType();
            if (scheduleBook.hasSchedule(schedule)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_SCHEDULE);
            }
            scheduleBook.addSchedule(schedule);
        }
        return scheduleBook;
    }

}