package seedu.address.logic.autocomplete.graphs.misc;

import java.util.List;

import seedu.address.logic.autocomplete.graphs.GraphWithPreamble;
import seedu.address.model.Model;
import seedu.address.model.order.Order;

/**
 * Represents a {@code Graph} used to support autocomplete for {@code CompleteOrderCommand}.
 */
public class CompleteOrderGraph extends GraphWithPreamble {

    public CompleteOrderGraph(Model model) {
        super(model);
    }

    @Override
    protected void build(Model model) {
        List<Order> orderList = model.getFilteredOrderList();
        setDataList(orderList);
    }

}
