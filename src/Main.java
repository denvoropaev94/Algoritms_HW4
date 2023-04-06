public class Main {
    public static void main(String[] args) {
        Tree tree = new Tree();

        tree.add(7);
        tree.add(3);
        tree.add(9);
        tree.add(2);
        tree.add(1);
        tree.add(5);
        tree.add(4);
        tree.add(6);
        tree.add(8);
        tree.add(14);
        tree.add(11);
        tree.add(20);
        tree.add(10);
        tree.add(17);
        tree.add(25);

        System.out.println(tree.dfs());
        System.out.println(tree.bfs());
        System.out.println(tree.findLast());
        System.out.println(tree.getChildrenCount());
    }
}
