package com.github.pozo.question2;

import com.github.pozo.Scenario;
import com.github.pozo.question2.collection.BST;

import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;

public class Question2 {

    public static final int ROOT_ID = 0;

//    public static void main(String[] args) {
//        Scenario scenario = new Scenario1();
//        String solution = solution(scenario);
//
//        assertEquals(scenario.output(), solution);
//
//        scenario = new Scenario2();
//        solution = solution(scenario);
//
//        assertEquals(scenario.output(), solution);
//    }

    private static String solution(Scenario scenario) {
        Scanner in = new Scanner(scenario.input());
        // skip first line
        Optional<String> rawEntryNumber = readLineFrom(in);
        int entryNumber = 0;
        if (rawEntryNumber.isPresent()) {
            entryNumber = Integer.parseInt(rawEntryNumber.get());
        }

        Optional<String> commonAncestor = Optional.empty();

        Optional<String> firstSelectedEmployee = readLineFrom(in);
        Optional<String> secondSelectedEmployee = readLineFrom(in);

        System.out.println("firstSelectedEmployee = " + firstSelectedEmployee.get());
        System.out.println("firstSelectedEmployee = " + secondSelectedEmployee.get());
        System.out.println("entryNumber = " + entryNumber);

        BST<Integer, String> binarySearchTree = new BST<>();
        HashMap<String, Integer> ids = new HashMap<>();

        if (firstSelectedEmployee.isPresent() && secondSelectedEmployee.isPresent()) {
            int key = (entryNumber / 2) + 1;
            int inverseKey = -key;

            while (in.hasNextLine()) {
                final String line = in.nextLine();
                final String[] employees = line.split(" ");
                String firstEmployee = employees[0];
                String secondEmployee = employees[1];

                System.out.println("first = " + firstEmployee);
                System.out.println("second = " + secondEmployee);

                // root
                if (binarySearchTree.isEmpty()) {
                    binarySearchTree.put(0, firstEmployee);
                    binarySearchTree.put(key, secondEmployee);

                    ids.put(firstEmployee, 0);
                    ids.put(secondEmployee, key);
                } else {
                    if (ids.containsKey(firstEmployee)) {
                        Integer employeeId = ids.get(firstEmployee);
                        //binarySearchTree.put(inverseKey, firstEmployee);
                        final int key1 = employeeId - 1;
                        binarySearchTree.put(key1, secondEmployee);

                        ids.put(secondEmployee, key1);
                    } else {
                        binarySearchTree.put(key, firstEmployee);
                        binarySearchTree.put(inverseKey, secondEmployee);

                        ids.put(firstEmployee, key);
                        ids.put(secondEmployee, inverseKey);
                    }

                }
                inverseKey += 1;
                key -= 1;
                //binarySearchTree.add(secondEmployee);
            }
        }
        System.out.println("binarySearchTree = " + binarySearchTree);

        if (commonAncestor.isPresent()) {
            return commonAncestor.get();
        } else {
            return "";
        }

    }

    private static class Node<K, V> {
        K key;
        V value;

        Node left;
        Node right;

        Node(K key, V value) {

            this.key = key;
            this.value = value;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Node<K, V> getLeft() {
            return left;
        }

        public void setLeft(Node<K, V> left) {
            this.left = left;
        }

        public Node<K, V> getRight() {
            return right;
        }

        public void setRight(Node<K, V> right) {
            this.right = right;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    private static class BinaryTree {
        private Node<Integer, String> root;

        public BinaryTree(Node<Integer, String> root) {
            this.root = root;
        }

        public boolean hasRoot() {
            return root != null;
        }

        Node<Integer, String> find(Integer key) {
            if (key == null) {
                return null;
            }
            if (key.equals(root.getKey())) {
                return root;
            }
            return find(root, key);
        }

        private Node<Integer, String> find(Node<Integer, String> node, Integer lookupKey) {
            final Node<Integer, String> left = node.getLeft();
            final Node<Integer, String> right = node.getRight();

            final Integer key = node.getKey();

            // x < value -> left

            if (key.equals(lookupKey)) {
                return node;
            } else if (left != null && lookupKey < node.getKey()) {
                return find(left, lookupKey);
            } else if (right != null && lookupKey > node.getKey()) {
                return find(right, lookupKey);
            } else {
                return null;
            }
        }

        void add(Node<Integer, String> node) {
            if (root == null || node == null) {
                return;
            }
            add(root, node);
        }

        public void add(Node<Integer, String> parent, Node<Integer, String> node) {
            final Node<Integer, String> left = parent.getLeft();
            final Node<Integer, String> right = parent.getRight();

            final Integer key = node.getKey();
            final Integer currentKey = parent.getKey();

            if (left == null && key < currentKey) {
                parent.setLeft(node);
            } else if (right == null && key > currentKey) {
                parent.setRight(node);
            } else if (left != null && key < currentKey) {
                add(left, node);
            } else if (right != null && key > currentKey) {
                add(right, node);
            } else {
                // meh
            }
        }

        public Node lowestCommonAncestor(Node nodeA, Node nodeB) {
            if (nodeA != null && nodeB != null) {

            }
            return lowestCommonAncestor(root, nodeA, nodeB);
        }

        private Node lowestCommonAncestor(Node root, Node nodeA, Node nodeB) {
            if (root == null) {
                return null;
            }

            // If the root is one of a or b, then it is the LCA
            if (root == nodeA || root == nodeB) {
                return root;
            }

            Node left = lowestCommonAncestor(root.left, nodeA, nodeB);
            Node right = lowestCommonAncestor(root.right, nodeA, nodeB);

            // If both nodes lie in left or right then their LCA is in left or right,
            // Otherwise root is their LCA
            if (left != null && right != null) {
                return root;
            }

            return (left != null) ? left : right;
        }
    }

    public static void main(String[] args) {
//        Sarah Fred
//        Sarah Paul
//        Fred Hilary
//        Fred Jenny
//        Jenny James

        Scenario scenario = new Scenario1();
        Scanner in = new Scanner(scenario.input());
        // skip first line
        Optional<String> rawEntryNumber = readLineFrom(in);
        int entryNumber = 0;
        if (rawEntryNumber.isPresent()) {
            entryNumber = Integer.parseInt(rawEntryNumber.get());
        }
        Optional<String> firstSelectedEmployee = readLineFrom(in);
        Optional<String> secondSelectedEmployee = readLineFrom(in);

//        BinaryTree binaryTree = new BinaryTree(null);
//
//        while (in.hasNextLine()) {
//            final String line = in.nextLine();
//            final String[] employees = line.split(" ");
//            String firstEmployee = employees[0];
//            String secondEmployee = employees[1];
//
//            if(!binaryTree.hasRoot()) {
//                Node<Integer, String> rootNode = new Node<>(0, firstEmployee);
//            }
//
//            System.out.println("firstEmployee = " + firstEmployee);
//        }

        Node<Integer, String> sarah = new Node<>(0, "Sarah");
        Node<Integer, String> fred = new Node<>(3, "Fred");
        Node<Integer, String> paul = new Node<>(-3, "Paul");
        Node<Integer, String> hilary = new Node<>(4, "Hilary");
        Node<Integer, String> jenny = new Node<>(2, "Jenny");
        Node<Integer, String> james = new Node<>(1, "James");

        BinaryTree binaryTree = new BinaryTree(null);
        binaryTree.add(fred);
        binaryTree.add(paul);
        binaryTree.add(hilary);
        binaryTree.add(jenny);
        binaryTree.add(james);

        Node meh = binaryTree.lowestCommonAncestor(fred, paul);

        System.out.println("LCA = " + meh);

        System.out.println("meh = " + binaryTree.find(0));
        System.out.println("meh = " + binaryTree.find(3));
        System.out.println("meh = " + binaryTree.find(-3));
        System.out.println("meh = " + binaryTree.find(4));
        System.out.println("meh = " + binaryTree.find(2));
        System.out.println("meh = " + binaryTree.find(1));

    }

//    public static void main(String[] args) {
//        Node<Integer, String> sarah = new Node<>(0, "Sarah");
//        Node<Integer, String> fred = new Node<>(3, "Fred");
//        Node<Integer, String> paul = new Node<>(-3, "Paul");
//        Node<Integer, String> hilary = new Node<>(4, "Hilary");
//        Node<Integer, String> jenny = new Node<>(2, "Jenny");
//        Node<Integer, String> james = new Node<>(1, "James");
//
//        BinaryTree binaryTree = new BinaryTree(sarah);
//        binaryTree.add(fred);
//        binaryTree.add(paul);
//        binaryTree.add(hilary);
//        binaryTree.add(jenny);
//        binaryTree.add(james);
//
//        Node meh = binaryTree.lowestCommonAncestor(fred, paul);
//
//        System.out.println("LCA = " + meh);
//
//        System.out.println("meh = " + binaryTree.find(0));
//        System.out.println("meh = " + binaryTree.find(3));
//        System.out.println("meh = " + binaryTree.find(-3));
//        System.out.println("meh = " + binaryTree.find(4));
//        System.out.println("meh = " + binaryTree.find(2));
//        System.out.println("meh = " + binaryTree.find(1));
//    }

    private static Optional<String> readLineFrom(Scanner in) {
        if (in.hasNextLine()) {
            return Optional.of(in.nextLine());
        } else {
            return Optional.empty();
        }
    }
}
