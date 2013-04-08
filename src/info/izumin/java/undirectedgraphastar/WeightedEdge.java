package info.izumin.java.undirectedgraphastar;

public class WeightedEdge {

    private final int[] v;
    private final int w;

    public WeightedEdge(int v1, int v2, int w) {
        v = new int[]{v1, v2};
        this.w = w;
    }

    public int[] getVertex() { return v; }
    public int getWeight() { return w; }
}
