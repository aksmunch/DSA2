import java.util.*;

// Water Consumer Record Management using BST

// BST Node Class
class Node {

    int consumerId;
    String consumerName;
    int waterUsage;

    Node left, right;

    public Node(int consumerId, String consumerName, int waterUsage) {

        this.consumerId = consumerId;
        this.consumerName = consumerName;
        this.waterUsage = waterUsage;

        left = right = null;
    }
}

// BST Class
class WaterBST {

    Node root;

    // Insert Method
    Node insert(Node root, int consumerId,
                String consumerName, int waterUsage) {

        if (root == null) {
            return new Node(consumerId, consumerName, waterUsage);
        }

        if (consumerId < root.consumerId) {

            root.left = insert(root.left,
                    consumerId,
                    consumerName,
                    waterUsage);
        }

        else if (consumerId > root.consumerId) {

            root.right = insert(root.right,
                    consumerId,
                    consumerName,
                    waterUsage);
        }

        return root;
    }

    // Search Method
    Node search(Node root, int consumerId) {

        if (root == null || root.consumerId == consumerId)
            return root;

        if (consumerId < root.consumerId)
            return search(root.left, consumerId);

        return search(root.right, consumerId);
    }

    // Minimum Node
    Node minValue(Node root) {

        Node current = root;

        while (current.left != null)
            current = current.left;

        return current;
    }

    // Delete Method
    Node delete(Node root, int consumerId) {

        if (root == null)
            return root;

        if (consumerId < root.consumerId) {

            root.left = delete(root.left, consumerId);
        }

        else if (consumerId > root.consumerId) {

            root.right = delete(root.right, consumerId);
        }

        else {

            if (root.left == null)
                return root.right;

            if (root.right == null)
                return root.left;

            Node temp = minValue(root.right);

            root.consumerId = temp.consumerId;
            root.consumerName = temp.consumerName;
            root.waterUsage = temp.waterUsage;

            root.right = delete(root.right, temp.consumerId);
        }

        return root;
    }

    // Inorder Traversal
    void inorder(Node root) {

        if (root != null) {

            inorder(root.left);

            System.out.println("Consumer ID   : "
                    + root.consumerId);

            System.out.println("Consumer Name : "
                    + root.consumerName);

            System.out.println("Water Usage   : "
                    + root.waterUsage + " Liters");

            System.out.println("--------------------------");

            inorder(root.right);
        }
    }

    // Count Consumers
    int countConsumers(Node root) {

        if (root == null)
            return 0;

        return 1 +
                countConsumers(root.left) +
                countConsumers(root.right);
    }
}

// Main Class
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        WaterBST bst = new WaterBST();

        System.out.print("Enter Number of Consumers: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {

            System.out.println("\nConsumer " + (i + 1));

            System.out.print("Enter Consumer ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Consumer Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Water Usage (Liters): ");
            int usage = sc.nextInt();

            bst.root = bst.insert(
                    bst.root,
                    id,
                    name,
                    usage);
        }

        System.out.println(
                "\n===== WATER CONSUMER RECORDS =====");

        bst.inorder(bst.root);

        // Search
        System.out.print(
                "\nEnter Consumer ID to Search: ");

        int searchId = sc.nextInt();

        Node result = bst.search(
                bst.root,
                searchId);

        if (result != null) {

            System.out.println("\nCONSUMER FOUND");

            System.out.println(
                    "Name : "
                            + result.consumerName);

            System.out.println(
                    "Usage : "
                            + result.waterUsage
                            + " Liters");
        }

        else {

            System.out.println(
                    "\nCONSUMER NOT FOUND");
        }

        // Delete
        System.out.print(
                "\nEnter Consumer ID to Delete: ");

        int deleteId = sc.nextInt();

        bst.root = bst.delete(
                bst.root,
                deleteId);

        System.out.println(
                "\n===== UPDATED RECORDS =====");

        bst.inorder(bst.root);

        int total =
                bst.countConsumers(bst.root);

        System.out.println(
                "\nTotal Active Consumers : "
                        + total);

        sc.close();
    }
}
