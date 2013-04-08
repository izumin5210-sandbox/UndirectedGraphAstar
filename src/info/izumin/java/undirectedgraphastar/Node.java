package info.izumin.java.undirectedgraphastar;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Node {

    private Node parent;
    private Map<Integer, Integer> child = new HashMap<Integer, Integer>();
    private final boolean isTerminal;
    private final int id, h;
    private int g;

    public Node(int id, int h) {
        this.id = id;
        this.h = h;
        isTerminal = false;
    }

    public Node(int id, int h, boolean isTerminal) {
        this.id = id;
        this.h = h;
        this.isTerminal = isTerminal;
    }

    public boolean isTerminal() { return isTerminal; }

    public int getHeuristic() { return h; }

    public void setParent(Node parent) { this.parent = parent; }

    public Node getParent() { return parent; }

    public void setChild(int id, int weight) { child.put(id, weight); }

    public Set<Map.Entry<Integer, Integer>> getChildSet() { return child.entrySet(); }

    public void setCost(int g) { this.g = g; }

    public int getCost() { return g; }

    public int getEstimatedCost() { return g+h; }

    @Override
    public int hashCode() { 
        return (31 * (31 + id) + h) + child.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return (this.getClass() == o.getClass()) && (this.hashCode() == o.hashCode());
    }

    @Override
    public String toString() {
        return "[h(" + String.valueOf(id) + ") = " + String.valueOf(h) + "]";
    }
}
