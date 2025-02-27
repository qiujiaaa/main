package seedu.address.logic.autocomplete.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import seedu.address.logic.autocomplete.AutoCompleteResultProvider;
import seedu.address.logic.autocomplete.edges.AutoCompleteEdge;
import seedu.address.logic.autocomplete.nodes.AutoCompleteNode;
import seedu.address.logic.autocomplete.nodes.Node;

/**
 * Represents a {@code Graph} that implements {@code AutoCompleteResultProvider}.
 */
public abstract class AutoCompleteGraph<T> implements Graph<T>, AutoCompleteResultProvider {

    protected final List<AutoCompleteEdge<T, ?, ?>> edgeList;

    protected AutoCompleteGraph() {
        this.edgeList = new ArrayList<>();
    }

    public List<T> getWeights(Node<?> sourceNode) {
        List<T> weights = new ArrayList<>();
        for (AutoCompleteEdge<T, ?, ?> edge : edgeList) {
            if (edge.hasSource(sourceNode)) {
                weights.add(edge.getWeight());
            }
        }
        return weights;
    }

    /**
     * Traverse the edge list from a source node along an edge of {@code weight}.
     * @param sourceNode A source node.
     * @param weight An edge weight.
     * @return An {@code AutoCompleteNode}.
     */
    public Optional<AutoCompleteNode<?>> traverse(Node<?> sourceNode, T weight) {
        for (AutoCompleteEdge<T, ?, ?> edge : edgeList) {
            if (edge.hasSource(sourceNode) && edge.hasWeight(weight)) {
                return Optional.of(edge.getDestination());
            }
        }
        return Optional.empty();
    }

}
