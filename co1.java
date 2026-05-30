
class Node {

    int studentId;
    String studentName;
    String course;

    Node left, right;

    public Node(int studentId, String studentName, String course) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.course = course;

        left = right = null;
    }
}

class StudentBST {

    Node root;

 
    Node insert(Node root, int studentId,
                String studentName, String course) {

        if (root == null) {
            return new Node(studentId, studentName, course);
        }

        if (studentId < root.studentId) {
            root.left = insert(root.left, studentId,
                    studentName, course);
        }

        else if (studentId > root.studentId) {
            root.right = insert(root.right, studentId,
                    studentName, course);
        }

        return root;
    }

   
    Node search(Node root, int studentId) {

        if (root == null || root.studentId == studentId) {
            return root;
        }

        if (studentId < root.studentId) {
            return search(root.left, studentId);
        }

        return search(root.right, studentId);
    }

    // Find Minimum
    Node minValue(Node root) {

        Node current = root;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

   
    Node delete(Node root, int studentId) {

        if (root == null) {
            return root;
        }

        if (studentId < root.studentId) {
            root.left = delete(root.left, studentId);
        }

        else if (studentId > root.studentId) {
            root.right = delete(root.right, studentId);
        }

        else {

            if (root.left == null)
                return root.right;

            if (root.right == null)
                return root.left;

            Node temp = minValue(root.right);

            root.studentId = temp.studentId;
            root.studentName = temp.studentName;
            root.course = temp.course;

            root.right = delete(root.right, temp.studentId);
        }

        return root;
    }

   
    void inorder(Node root) {

        if (root != null) {

            inorder(root.left);

            System.out.println("Student ID   : " + root.studentId);
            System.out.println("Student Name : " + root.studentName);
            System.out.println("Course       : " + root.course);
            System.out.println("----------------------------");

            inorder(root.right);
        }
    }

    int countStudents(Node root) {

        if (root == null) {
            return 0;
        }

        return 1 + countStudents(root.left)
                 + countStudents(root.right);
    }
}

// Main Class
public class Main {

    public static void main(String[] args) {

        StudentBST bst = new StudentBST();

        // Insert Student Records
        bst.root = bst.insert(bst.root,
                101, "Akash", "CSE");

        bst.root = bst.insert(bst.root,
                205, "Rahul", "AI&DS");

        bst.root = bst.insert(bst.root,
                150, "Priya", "IT");

        bst.root = bst.insert(bst.root,
                250, "Neha", "ECE");

        bst.root = bst.insert(bst.root,
                175, "Kiran", "CSE");

        // Display Records
        System.out.println("===== STUDENT RECORDS =====");
        bst.inorder(bst.root);

       
        int searchId = 205;

        Node result = bst.search(bst.root, searchId);

        if (result != null) {

            System.out.println("\nSTUDENT FOUND");
            System.out.println("Student ID   : " + result.studentId);
            System.out.println("Student Name : " + result.studentName);
            System.out.println("Course       : " + result.course);

        } else {

            System.out.println("\nSTUDENT NOT FOUND");
        }

     
        System.out.println("\nDeleting Student ID 150...");
        bst.root = bst.delete(bst.root, 150);

        System.out.println("\n===== UPDATED RECORDS =====");
        bst.inorder(bst.root);

        
        int total = bst.countStudents(bst.root);

        System.out.println("\nTotal Students : " + total);
    }
}
