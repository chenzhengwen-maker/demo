package com.example.demo.algorithm;

import java.util.concurrent.Callable;

public class BinaryTreeTraversal {
    public static void main(String[] args) {
        /**
         * 二叉树如下：
         *                    A
         *                  |   \
         *                |       \
         *              B          C
         *            |   \       |  \
         *           D     E     F   G
         *          | \    |
         *         H   I  J
         *
         * 先序遍历结果（根左右）：A、B、D、H、I、E、J、C、F、G
         * 中序遍历结果（左根右）：H、D、I、B、J、E、A、F、C、G
         * 后序遍历结果（左右根）：H、I、D、J、E、B、F、G、C、A
         */

        TreeNode nodeA = new TreeNode("A");
        TreeNode nodeB = new TreeNode("B");
        TreeNode nodeC = new TreeNode("C");
        TreeNode nodeD = new TreeNode("D");
        TreeNode nodeE = new TreeNode("E");
        TreeNode nodeF = new TreeNode("F");
        TreeNode nodeG = new TreeNode("G");
        TreeNode nodeH = new TreeNode("H");
        TreeNode nodeI = new TreeNode("I");
        TreeNode nodeJ = new TreeNode("J");
        nodeA.left = nodeB;
        nodeA.right = nodeC;
        nodeB.left = nodeD;
        nodeB.right = nodeE;
        nodeD.left = nodeH;
        nodeD.right = nodeI;
        nodeE.left = nodeJ;
        nodeC.left = nodeF;
        nodeC.right = nodeG;
        System.out.println("前序遍历");
        preOrder(nodeA);
        System.out.println();
        System.out.println("中序遍历");
        middleOrder(nodeA);
        System.out.println();
        System.out.println("后序遍历");
        afterOrder(nodeA);

    }
    public static void preOrder(TreeNode root){
        if(root == null){
            return;
        }
        System.out.print(root.value+"->");
        preOrder(root.left);
        preOrder(root.right);
    }
    public static void middleOrder(TreeNode root){
        if(root == null){
            return;
        }
        middleOrder(root.left);
        System.out.print(root.value+"->");
        middleOrder(root.right);
    }
    public static void afterOrder(TreeNode root){
        if(root == null){
            return;
        }
        afterOrder(root.left);
        afterOrder(root.right);
        System.out.print(root.value+"->");
    }
}
class TreeNode{
    TreeNode left;
    TreeNode right;
    String value;
    public TreeNode(String value) {
        this.value = value;
    }
}

