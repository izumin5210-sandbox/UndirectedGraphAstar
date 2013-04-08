package info.izumin.java.undirectedgraphastar;

import java.util.ArrayList;

public class WeightedGraph extends ArrayList<Node> {

    private static final long serialVersionUID = 1L;

    public String getPath() { return getPath(get(size()-1)); }

    public String getPath(Node node) {
        if (node.getParent() == null) return node.toString();
        else return getPath(node.getParent()) + ", " + node.toString();
    }
}
