package com.bxh.algorithms.OtherAlgorithms;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by bxh on 7/27/17.
 */

public class BFS {
    int[][] maze = {
            {0, 1, 0, 0, 0},
            {0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 1, 0}
    };
    Node[][] nodes = new Node[maze.length][maze[0].length];

    int[][] dir = {
            {0, 1},//右
            {1, 0},//下
            {0, -1},//左
            {-1, 0}//上
    };

    public void test() {
        //init nodes arrays
        initNodes();
        boolean x = getPath(nodes[0][0], nodes[4][4]);
    }

    public void initNodes() {
        //init nodes
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                nodes[i][j] = new Node(i, j, maze[i][j]);
            }
        }
    }

    public boolean getPath(Node start, Node end) {
        //real calculation
        Queue<Node> queue = new LinkedBlockingDeque();
        start.visted = true;
        queue.add(start);
        while (queue.size() > 0) {
            Node cur = queue.poll();
            if (cur.equals(end)) {
                System.out.println("---new cur ，end !");
                print(cur);
                return true;
            }
            //四个方向
            for (int i = 0; i < 4; i++) {
                //相邻节点
                int lx = cur.x + dir[i][0];
                int ly = cur.y + dir[i][1];
                System.out.println("---lx=" + lx + "---ly=" + ly + "--i=" + i);
                System.out.println("---cur.x=" + cur.x + "---cur.y=" + cur.y + "--i=" + i);
                if (lx < 0 || ly < 0 || lx >= 5 || ly >= 5) {
                    continue;
                }
                Node lNode = nodes[lx][ly];
                //已经访问过，不走回头路
                if (lNode.visted) {
                    System.out.println("---already visited");
                    print(lNode);
                    continue;
                }
                lNode.visted = true;
                if (lNode.v == 1) {
                    continue;
                }
                if (lNode.equals(cur)) {
                    System.out.println("---this is end node");
                    print(lNode);
                    return true;
                } else {
                    queue.add(lNode);
                    System.out.println("----add to queue");
                    print(lNode);
                }
            }


        }
        return false;
    }

    public void print(Node n) {
        System.out.println("pos is (" + n.x + "," + n.y + ")");
    }

    static class Node {
        int x;
        int y;
        int v;
        boolean visted;

        Node(int[] a) {
            x = a[0];
            y = a[1];
        }

        Node(int a, int b) {
            x = a;
            y = b;
        }

        Node(int a, int b, int c) {
            x = a;
            y = b;
            v = c;
        }

        @Override
        public boolean equals(Object obj) {
            Node n = (Node) obj;
            if (n.x == this.x && n.y == this.y && n.v == this.v) {
                return true;
            }
            return super.equals(obj);
        }
    }

}
