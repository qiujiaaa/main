package seedu.address.ui.cards;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.order.Order;
import seedu.address.model.order.Status;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of a {@code Order}.
 */
public class OrderCard extends UiPart<Region> {

    private static final String FXML = "OrderListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Order order;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label customerContactNumber;
    @FXML
    private Label customerName;
    @FXML
    private Label customerEmail;
    @FXML
    private Label phoneIdentityNumber;
    @FXML
    private Label phoneSerialNumber;
    @FXML
    private Label phoneCost;
    @FXML
    private Label phoneName;
    @FXML
    private Label phoneColour;
    @FXML
    private Label phoneCapacity;
    @FXML
    private Label orderId;
    @FXML
    private Label orderPrice;
    @FXML
    private Label orderStatus;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane scheduleTags;
    @FXML
    private FlowPane customerTags;

    public OrderCard(Order order, int displayedIndex) {
        super(FXML);
        this.order = order;
        id.setText(displayedIndex + ". ");

        customerName.setText(order.getCustomer().getCustomerName().fullName);
        customerContactNumber.setText(order.getCustomer().getContactNumber().value);
        customerEmail.setText(order.getCustomer().getEmail().value);

        phoneIdentityNumber.setText(order.getPhone().getIdentityNumber().value);
        phoneSerialNumber.setText(order.getPhone().getSerialNumber().value);
        phoneName.setText(order.getPhone().getPhoneName().fullName);
        phoneColour.setText(order.getPhone().getColour().value);
        phoneCapacity.setText(order.getPhone().getCapacity().value);
        phoneCost.setText(order.getPhone().getCost().value);

        orderId.setText(order.getId().toString());
        orderPrice.setText(order.getPrice().value);

        if (order.getStatus().equals(Status.SCHEDULED)) {
            assert(order.getSchedule().isPresent());
            orderStatus.setText(String.format("%s : %s | Venue: %s ", order.getStatus().toString(),
                    order.getSchedule().get().getCalendarString(), order.getSchedule().get().getVenue()));
        } else {
            orderStatus.setText(order.getStatus().toString());
        }

        if (order.getSchedule().isPresent()) {
            order.getSchedule().get().getTags().stream().sorted(Comparator.comparing(tag -> tag.tagName))
                    .forEach(tag -> scheduleTags.getChildren().add(new Label(tag.tagName)));
        }

        if (order.getCustomer() != null) {
            order.getCustomer().getTags().stream()
                    .sorted(Comparator.comparing(tag -> tag.tagName))
                    .forEach(tag -> customerTags.getChildren().add(new Label(tag.tagName)));
        }
        order.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        customerName.setWrapText(true);
        customerContactNumber.setWrapText(true);
        customerEmail.setWrapText(true);

        phoneIdentityNumber.setWrapText(true);
        phoneSerialNumber.setWrapText(true);
        phoneName.setWrapText(true);
        phoneColour.setWrapText(true);
        phoneCapacity.setWrapText(true);
        phoneCost.setWrapText(true);

        orderId.setWrapText(true);
        orderPrice.setWrapText(true);
        orderStatus.setWrapText(true);

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof OrderCard)) {
            return false;
        }

        // state check
        OrderCard card = (OrderCard) other;
        return id.getText().equals(card.id.getText())
                && order.equals(card.order);
    }
}
