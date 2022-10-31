package it.unibo.generics.graph;

public class Edge<N> {

    private final N src;
    private final N dst;
    
    public Edge(N src, N dst) {
        this.src = src;
        this.dst = dst;
    }

    public N getSource() {
        return this.src;
    }

    public N getDestination() {
        return this.dst;
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((src == null) ? 0 : src.hashCode());
        result = prime * result + ((dst == null) ? 0 : dst.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Edge other = (Edge) obj;
        if (src == null) {
            if (other.src != null)
                return false;
        } else if (!src.equals(other.src))
            return false;
        if (dst == null) {
            if (other.dst != null)
                return false;
        } else if (!dst.equals(other.dst))
            return false;
        return true;
    }
}
