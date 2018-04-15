import java.util.ArrayList;
/**Есть бинарное дерево, заданное указателем на вершину. Нужно написать функцию, которая печатает все пути в дереве от корня до листьев. Например, есть такое дерево:

         A
        / \
       B   C
     / \    \
    D   E    F

        На экран нужно вывести:

        A B D
        A B E
        A C F
 **/

public class Main {

    static class Node {
        String name;
        Node left;
        Node right;

        public Node(String name, Node left, Node right) {
            this.name = name;
            this.left = left;
            this.right = right;
        }
    }

    static String bottom = "";
    static ArrayList<String> pairs = new ArrayList<>();
    static Node F = new Node("F", null, null);
    static Node E = new Node("E", null, null);
    static Node D = new Node("D", null, null);
    static Node C = new Node("C", null, F);
    static Node B = new Node("B", D, E);
    static Node A = new Node("A", B, C);

    public static void main(String[] args) {
        findAllPairs(A);
        makeWords(pairs, bottom);
    }

    private static void makeWords(ArrayList<String> pairs, String bottom) {
        ArrayList<String> words = new ArrayList<>();
        String word;
        char end;
        for (int i = 0; i < bottom.length(); i++) {
            end = bottom.charAt(i);
            word = "";
            word = word.concat(String.valueOf(end));
            for (int j = pairs.size() - 1; j > -1; j--) {
                if ((pairs.get(j).contains((String.valueOf(word.charAt(word.length() - 1)))))) {
                    if (!word.contains(String.valueOf(pairs.get(j).charAt(0)))) {
                        word = word.concat(String.valueOf(pairs.get(j).charAt(0)));
                    }
                    if (word.charAt(word.length() - 1) == 'A') {
                        words.add(word);
                        break;
                    }
                }
            }

        }
        for (String word1 : words) {
            System.out.println(word1);
        }
    }

    static void findAllPairs(Node current) {
        if (current.right != null) {
            if (!has(pairs, current.name + current.right.name)) pairs.add(current.name + current.right.name);
            findAllPairs(current.right);
        }
        if (current.left != null) {
            if (!has(pairs, current.name + current.left.name)) pairs.add(current.name + current.left.name);
            findAllPairs(current.left);
        }
        if (current.right == null && current.left == null) {
            if (!bottom.contains(current.name)) {
                bottom = bottom.concat(current.name);
                findAllPairs(A);
            }
        }
    }

    static boolean has(ArrayList<String> pairs, String s) {
        for (String pair : pairs) {
            if (pair.equals(s)) return true;
        }
        return false;
    }
}
