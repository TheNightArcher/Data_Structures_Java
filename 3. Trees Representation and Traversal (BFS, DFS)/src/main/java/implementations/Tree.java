package implementations;

import interfaces.AbstractTree;

import java.util.*;

public class Tree<E> implements AbstractTree<E> {
    private E key;
    private Tree<E> parent;
    private List<Tree<E>> children;

    public Tree(E key, Tree<E>... children) {
        this.key = key;
        this.children = new ArrayList<>();

        for (Tree<E> child : children) {
            this.children.add(child);
            child.parent = this;
        }
    }

    @Override
    public List<E> orderBfs() {
        List<E> result = new ArrayList<>();
        if (this.key == null) {
            return result;
        }

        Queue<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(this);

        while (queue.size() > 0) {
            Tree<E> current = queue.poll();

            result.add(current.key);
            for (Tree<E> child : current.children) {
                queue.offer(child);
            }
        }
        return result;
    }

    @Override
    public List<E> orderDfs() {
        List<E> result = new ArrayList<>();
        this.dfs(this, result);

        return result;
    }

    @Override
    public void addChild(E parentKey, Tree<E> child) {
        Tree<E> search = find(parentKey);

        if (search == null) {
            throw new IllegalArgumentException();
        }

        search.children.add(child);
        child.parent = search;
    }

    @Override
    public void removeNode(E nodeKey) {
        Tree<E> search = find(nodeKey);

        if (search == null) {
            throw new IllegalArgumentException();
        }
        search.children.clear();

        Tree<E> parent = search.parent;
        if (parent != null) {
            parent.children.remove(search);
        }

        search.key = null;
    }

    @Override
    public void swap(E firstKey, E secondKey) {

    }

    private void dfs(Tree<E> tree, List<E> result) {
        for (Tree<E> child : tree.children) {
            this.dfs(child, result);
        }
        result.add(tree.key);
    }

    private Tree<E> find(E parentKey) {
        Queue<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(this);

        while (queue.size() > 0) {
            Tree<E> current = queue.poll();

            if (current.key == parentKey) {
                return current;
            }

            for (Tree<E> child : current.children) {
                queue.offer(child);
            }
        }
        return null;
    }
}



