package solutions;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
    private int value;
    private BinaryTree left;
    private BinaryTree right;

    public BinaryTree(int value, BinaryTree left, BinaryTree right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public Integer findLowestCommonAncestor(int first, int second) {
        List<Integer> firstPath = findPath(first);
        List<Integer> secondPath = findPath(second);

        int smallerSize = Math.min(firstPath.size(), secondPath.size());

        int i = 0;
        for (; i < smallerSize; i++) {
            if (!firstPath.get(i).equals(secondPath.get(i))) {
                break;
            }
        }
        return firstPath.get(i - 1);
    }

    public List<Integer> topView() {
        List<Integer> result = new ArrayList<>();

        BinaryTree currentLeftSide = this;
        while (currentLeftSide != null) {
            result.add(currentLeftSide.value);
            currentLeftSide = currentLeftSide.left;
        }

        BinaryTree currentRightSide = this;
        int count = 0;

        while (currentRightSide != null) {
            if (count == 0){
                currentRightSide = currentRightSide.right;
            }
            result.add(currentRightSide.value);
            currentRightSide = currentRightSide.right;
            count++;
        }

        return result;
    }

    private List<Integer> findPath(int element) {
        List<Integer> result = new ArrayList<>();
        findNodePath(this, element, result);

        return result;
    }

    private boolean findNodePath(BinaryTree binaryTree, int element, List<Integer> currentPath) {
        if (binaryTree == null) {
            return false;
        }

        if (binaryTree.value == element) {
            return true;
        }

        currentPath.add(binaryTree.value);

        boolean leftResult = findNodePath(binaryTree.left, element, currentPath);
        if (leftResult) {
            return true;
        }

        boolean rightResult = findNodePath(binaryTree.right, element, currentPath);
        if (rightResult) {
            return true;
        }

        currentPath.remove(Integer.valueOf(binaryTree.value));
        return false;
    }
}
