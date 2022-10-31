package it.unibo.generics.graph;

import java.util.*;

import it.unibo.generics.graph.api.Graph;

public class GraphImpl<N> implements Graph<N> {

    private final static int WHITE = 0;
    private final static int GREY = 1;
    private final static int BLACK = 2;

    private final List<N> nodes;
    private final List<Edge<N>> edges;

    public GraphImpl() {
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    public void addNode(N node) {
        if (this.nodes.contains(node)) {
            return;
        }

        this.nodes.add(node);
    }

    public void addEdge(N source, N target) {
        final Edge<N> newEdge = new Edge<>(source, target);
        
        if (this.edges.contains(newEdge)) {
            return;
        }

        this.edges.add(newEdge);        
    }

    public Set<N> nodeSet() {
        final Set<N> nodeSet = new TreeSet<>(this.nodes);

        return nodeSet;
    }

    public Set<N> linkedNodes(N node) {
        final Set<N> linkedNodes = new TreeSet<>();

        for (Edge<N> edge : this.edges) {
            if (edge.getSource() == node) {
                linkedNodes.add(edge.getDestination());
            }
        }

        return linkedNodes;
    }

    public List<N> getPath(N source, N target) {
        final List<N> queue = new LinkedList<>();
        final Map<N, Integer> colors = new TreeMap<>();
        boolean targetReached = false;

        for (N n : this.nodes) {
            colors.put(n, WHITE);
        }
        
        queue.add(source);

        while (!queue.isEmpty()) {
            N node = queue.remove(0);

            Set<N> linked = this.linkedNodes(node);
            while (!linked.isEmpty()) {

            }
        }

        return null;
    }
    
}
