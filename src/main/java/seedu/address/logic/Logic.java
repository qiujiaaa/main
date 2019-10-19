package seedu.address.logic;

import java.nio.file.Path;
import java.util.Calendar;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import jfxtras.scene.layout.HBox;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.util.StatsPayload;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyDataBook;
import seedu.address.model.ScheduleTime;
import seedu.address.model.customer.Customer;
import seedu.address.model.order.Order;
import seedu.address.model.person.Person;
import seedu.address.model.phone.Phone;
import seedu.address.model.schedule.Schedule;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the AddressBook.
     *
     * @see seedu.address.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns the OrderBook.
     *
     * @see seedu.address.model.Model#getOrderBook()
     */
    ReadOnlyDataBook<Order> getOrderBook();

    /**
     * Returns the PhoneBook.
     *
     * @see seedu.address.model.Model#getPhoneBook()
     */
    ReadOnlyDataBook<Phone> getPhoneBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered list of customers */
    ObservableList<Customer> getFilteredCustomerList();

    /** Returns an unmodifiable view of the filtered list of phone */
    ObservableList<Phone> getFilteredPhoneList();

    /** Returns an unmodifiable view of the filtered list of order */
    ObservableList<Order> getFilteredOrderList();

    /** Returns an unmodifiable view of the filtered list of schedule */
    ObservableList<Schedule> getFilteredScheduleList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Gets logic to calculate total profit based on user input
     */
    String calculateTotalProfit(StatsPayload statsPayload);

    /**
     * Gets logic to calculate Total Revenue based on user input
     */
    String calculateTotalRevenue(StatsPayload statsPayload);


    /**
     * Gets logic to calculate total profit based on user input
     */
    String calculateTotalCost(StatsPayload statsPayload);

    ScheduleTime getScheduleTime();
}
