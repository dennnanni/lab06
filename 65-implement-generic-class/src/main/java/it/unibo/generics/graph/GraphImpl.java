package it.unibo.generics.graph;

import java.util.*;

import it.unibo.generics.graph.api.Graph;

public class GraphImpl<N> implements Graph<N> {

    private final static int NODE_UNDEF = -1;
    private final static int WHITE = 0;
    private final static int GREY = 1;
    private final static int BLACK = 2;

    private final List<N> nodes;
    private final List<Edge<N>> edges;

    public GraphImpl() {
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    public void addNode(final N node) {
        if (this.nodes.contains(node)) {
            return;
        }

        this.nodes.add(node);
    }

    public void addEdge(final N source, final N target) {
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

    public Set<N> linkedNodes(final N node) {
        final Set<N> linkedNodes = new TreeSet<>();

        for (Edge<N> edge : this.edges) {
            if (edge.getSource() == node) {
                linkedNodes.add(edge.getDestination());
            }
        }

        return linkedNodes;
    }

    public List<N> getPath(final N source, final N target) {
        final Queue<N> queue = new LinkedList<>();
        final Map<N, Integer> colors = new TreeMap<>();
        final Map<N, Integer> distances = new HashMap<>();
        final Map<N, N> predecessors = new HashMap<>();
        boolean sourceReached = false;

        for (N n : this.nodes) {
            colors.put(n, WHITE);
            distances.put(n, NODE_UNDEF);
            predecessors.put(n, null);
        }
        
        queue.add(source);

        while (!queue.isEmpty()) {
            N node = queue.poll();

            Set<N> linked = this.linkedNodes(node);
            for (N n : linked) {
                if (colors.get(n) == WHITE) {
                    queue.add(n);
                    colors.put(n, GREY);
                    predecessors.put(n, node);
                    distances.put(n, distances.get(node) + 1);
                }
            }

            colors.put(node, BLACK);
        }

        if (distances.get(target) == NODE_UNDEF) {
            return null;
        }

        final List<N> path = new LinkedList<>();
        N current = target;
        path.add(target);

        while (!sourceReached) {
            N el = predecessors.get(current);
            path.add(0, el);
            current = el;

            if (el == source) {
                sourceReached = true;
            }
        }

        return path;
    }
    
}
