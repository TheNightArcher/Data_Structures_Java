package implementations;

import interfaces.AbstractTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class Tree<E> implements AbstractTree<E> {
    private E key;
    private Tree<E> parent;
    private List<Tree<E>> children;

    public Tree(E key, Tree<E>... children) {
        this.key = key;
        this.children = new ArrayList<>();

        for (Tree<E> child : children) {
            child.setParent(this);
            this.children.add(child);
        }
    }

    @Override
    public void setParent(Tree<E> parent) {
        this.parent = parent;
    }

    @Override
    public void addChild(Tree<E> child) {
        this.children.add(child);
    }

    @Override
    public Tree<E> getParent() {
        return this.parent;
    }

    @Override
    public E getKey() {
        return this.key;
    }

    @Override
    public String getAsString() {
        StringBuilder result = new StringBuilder();

        crawlingByDFS(this, result, 0);

        return result.toString().trim();
    }

    @Override
    public List<E> getLeafKeys() {
        return crawlingByBFS()
                .stream()
                .filter(tree -> tree.children.isEmpty())
                .map(Tree::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public List<E> getMiddleKeys() {
        return crawlingByBFS()
                .stream()
                .filter(tree -> tree.parent != null && !tree.children.isEmpty())
                .map(Tree::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public Tree<E> getDeepestLeftmostNode() {
        return crawlingByBFS()
                .stream()
                .filter(tree -> tree.children.isEmpty() && tree.parent != this)
                .findFirst().orElse(null);
    }

    private void findDeepestLeft(Tree<E> tree, int levelOfDeep, Tree<E> deepestNode) {
        int currentLevel = 1;

        for (Tree<E> child : tree.children) {
            findDeepestLeft(child, levelOfDeep++, deepestNode);
            if (levelOfDeep > currentLevel){
                currentLevel = levelOfDeep;
                deepestNode = child;
            }
        }
    }

    @Override
    public List<E> getLongestPath() {
        return null;
    }

    @Override
    public List<List<E>> pathsWithGivenSum(int sum) {
        return null;
    }

    @Override
    public List<Tree<E>> subTreesWithGivenSum(int sum) {
        return null;
    }

    private void crawlingByDFS(Tree<E> tree, StringBuilder result, int indent) {

        result.append(this.getPadding(indent))
                .append(tree.key)
                .append(System.lineSeparator());

        for (Tree<E> child : tree.children) {
            crawlingByDFS(child, result, indent + 2);
        }
    }

    private String getPadding(int size) {
        StringBuilder sb = new StringBuilder();

        sb.append(" ".repeat(Math.max(0, size)));

        return sb.toString();
    }

    private List<Tree<E>> crawlingByBFS() {
        Deque<Tree<E>> queue = new ArrayDeque<>();

        queue.offer(this);

        List<Tree<E>> allNodes = new ArrayList<>();

        while (!queue.isEmpty()) {
            Tree<E> tree = queue.poll();
            allNodes.add(tree);
            for (Tree<E> child : tree.children) {
                queue.offer(child);
            }
        }
        return allNodes;
    }
}



