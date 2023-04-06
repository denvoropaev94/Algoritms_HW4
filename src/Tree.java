import java.util.*;

public class Tree {
    private class Node{
        public Node(int value) {
            this.value = value;
        }

        private int value;
        private Node left;
        private Node right;
    }

    private Node root;


    public Integer getChildrenCount(){
        if (root == null) {
            throw new NoSuchElementException();
        }

        return getChildrenCount(root);
    }

    private Integer getChildrenCount(Node current){
        if (current == null){
            return 0;
        }
        if (current.left == null  && current.right == null){
            return 1;
        }
        return getChildrenCount(current.left) + getChildrenCount(current.right);
    }

    public Integer findLast() {
        List<Integer> result = new ArrayList<>();
        findLast(root,result);
        return Collections.max(result);
    }

    private void findLast(Node current, List<Integer> result ) {
        if (current != null) {
            findLast(current.right, result);
            result.add(current.value);
        }
    }

    public void add(int value){
        root = appendNode(root,value);
    }

    private Node appendNode(Node current, int value){
        if (current == null){
            return new Node(value);
        }
        if(current.value > value){
            current.left = appendNode(current.left, value);
        } else if ( current.value < value){
            current.right = appendNode(current.right, value);
        }
        return current;
    }


    public boolean contains(int value){
        return findNode(root, value) != null;
    }

    private Node findNode(Node current, int value){
        if (current == null){
            return null;
        }
        if (current.value > value){
            return findNode(current.left, value);
        } else if (current.value < value) {
            return findNode(current.right, value);
        }
        return current;
    }

    public void remove(int value) {
        root = removeNode(root, value);
    }


    private Node removeNode(Node current, int value) {
        if (current == null) {
            return null;
        }

        if (current.value > value) {
            current.left = removeNode(current.left, value);
            return current;
        } else if (current.value < value) {
            current.right = removeNode(current.right, value);
            return current;
        }

        // Нужно удалить текущий узел.
        // 3 случая:
        // 1. Нет дочерних узлов
        if (current.left == null && current.right == null) {
            return null;
        }

        // 2. Есть только 1 дочерний узел
        if (current.left == null) { //  && current.right != null
            return current.right;
        }
        if (current.right == null) { // && current.left != null
            return current.left;
        }

        // 3. Есть оба дочерних узла
        // Нужно найти минимальный элемент в правом поддереве
        Node smallestNodeOnTheRight = findFirst(current.right);
        int smallestValueOnTheRight = smallestNodeOnTheRight.value;
        // Вставить его значение в текущий узел
        current.value = smallestValueOnTheRight;
        // И удалить этот найденный минимальный узел у правого поддерева
        current.right = removeNode(current.right, smallestValueOnTheRight);
        return current;
    }

    public int findFirst() {
        if (root == null) {
            throw new NoSuchElementException();
        }

        return findFirst(root).value;
    }

    private Node findFirst(Node current) {
        if (current.left != null) {
            return findFirst(current.left);
        }
        return current;
    }

    public List<Integer> dfs() {
        List<Integer> result = new ArrayList<>();
        dfs(root,result);
        return result;
    }

    private void dfs(Node current, List<Integer> result ) {
        if (current != null) {
            dfs(current.left, result);
            result.add(current.value);
            dfs(current.right, result);
        }
    }

    public List<Integer> bfs() {
        List<Integer> result = new ArrayList<>();
        bfs(result);
        return result;
    }

    private void bfs(List<Integer> result ) {
        if (root == null){
            return;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()){
            Node node = queue.poll();
            result.add(node.value);

            if (node.left != null){
                queue.add(node.left);
            }
            if(node.right != null){
                queue.add(node.right);
            }
        }
    }
}
