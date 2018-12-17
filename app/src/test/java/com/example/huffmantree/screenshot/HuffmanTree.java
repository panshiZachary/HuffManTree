package com.example.huffmantree;

import android.support.annotation.NonNull;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by Zachary
 * on 2018/12/17.
 * 哈夫曼树
 */
public class HuffmanTree {

    // 根节点
    TreeNode root;

    /**
     * 创建一个哈夫曼树
     * @param list
     * @return root;
     */
    public TreeNode createHuffManTree(ArrayList<TreeNode> list){
        while (list.size()>1){
            // 先排好顺序
            Collections.sort(list);
            TreeNode left = list.get(list.size() - 1);
            TreeNode right = list.get(list.size() - 2);
            TreeNode parent = new TreeNode<>("p", left.weight + right.weight);
            parent.leftChild = left;
            left.parent = parent;
            parent.rightChild = right;
            right.parent = parent;
            list.remove(left);
            list.remove(right);
            list.add(parent);

        }
        root = list.get(0);
        return list.get(0);
    }

    /**
     * 显示哈夫曼树
     */
    public void showHuffManTree(TreeNode root){
        LinkedList<TreeNode> list = new LinkedList<>();
        list.offer(root);  // 入队
        while (!list.isEmpty()){
            TreeNode node = list.pop();
            if (node.rightChild==null && node.rightChild==null){
                System.out.println(node.data);
            }
            if (node.leftChild !=null){
               list.offer(node.leftChild);
            }
            if (node.rightChild !=null){
                list.offer(node.rightChild);
            }
        }


    }

    /**
     *测试
     */
    @Test
    public void test(){
        ArrayList<TreeNode> list = new ArrayList<>();
        TreeNode<String> node = new TreeNode("good", 50);
        list.add(node);
        list.add(new TreeNode("morning", 10));
        TreeNode<String> node2 =new TreeNode("afternoon", 20);
        list.add(node2);
        list.add(new TreeNode("hell", 110));
        list.add(new TreeNode("hi", 200));

        HuffmanTree huffmanTree = new HuffmanTree();
        huffmanTree.createHuffManTree(list);
        huffmanTree.showHuffManTree(huffmanTree.root);
        //获得编码
        getCode(node2);

    }

    /**
     *  哈夫曼编码
     * @param node
     */
    private void getCode(TreeNode<String> node) {
        TreeNode tNode = node;
        Stack<String> stack = new Stack<>();
        while (tNode !=null && tNode.parent!=null){
            // left 0  right1
            if (tNode.parent.leftChild ==tNode){
                stack.push("0");
            }else if (tNode.parent.rightChild == tNode){
                stack.push("1");
            }
            tNode = tNode.parent;
        }
        System.out.println();
        while (!stack.empty()){
            System.out.printf(""+ stack.pop());
        }


    }


    /**
     * 节点
     * @param <T>
     */
    private static class TreeNode<T> implements Comparable<TreeNode<T>>{
        T data;
        int weight;
        TreeNode leftChild;
        TreeNode rightChild;
        TreeNode parent;

        public TreeNode(T data, int weight) {
            this.data = data;
            this.weight = weight;
            leftChild = null;
            rightChild = null;
            parent = null;
        }

        @Override
        public int compareTo(@NonNull TreeNode<T> o) {
            if (this.weight > o.weight){
                return -1;
            }else if (this.weight < o.weight){
                return 1;
            }
            return 0;
        }
    }
}
