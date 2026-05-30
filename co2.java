// Student Marks Analysis System using Segment Tree

class SegmentTree {

    int[] tree;
    int n;

    SegmentTree(int[] marks) {

        n = marks.length;
        tree = new int[4 * n];

        build(marks, 0, 0, n - 1);
    }

    void build(int[] arr, int node, int start, int end) {

        if (start == end) {
            tree[node] = arr[start];
        } else {

            int mid = (start + end) / 2;

            build(arr, 2 * node + 1, start, mid);
            build(arr, 2 * node + 2, mid + 1, end);

            tree[node] =
                tree[2 * node + 1] +
                tree[2 * node + 2];
        }
    }

    int query(int node, int start, int end,
              int left, int right) {

        if (right < start || left > end)
            return 0;

        if (left <= start && end <= right)
            return tree[node];

        int mid = (start + end) / 2;

        return query(2 * node + 1,
                start, mid, left, right)
                +
                query(2 * node + 2,
                mid + 1, end, left, right);
    }

    void update(int node, int start,
                int end, int index, int value) {

        if (start == end) {

            tree[node] = value;

        } else {

            int mid = (start + end) / 2;

            if (index <= mid)
                update(2 * node + 1,
                        start, mid,
                        index, value);
            else
                update(2 * node + 2,
                        mid + 1, end,
                        index, value);

            tree[node] =
                tree[2 * node + 1] +
                tree[2 * node + 2];
        }
    }
}

public class Main {

    public static void main(String[] args) {

        int[] marks =
        {85, 90, 78, 92, 88};

        SegmentTree st =
            new SegmentTree(marks);

        System.out.println(
            "===== STUDENT MARKS =====");

        for (int mark : marks)
            System.out.print(mark + " ");

        System.out.println();

        int total =
            st.query(0, 0, marks.length - 1,
                     0, 4);

        System.out.println(
        "\nTotal Marks (Student 1-5) : "
        + total);

        int range =
            st.query(0, 0, marks.length - 1,
                     1, 3);

        System.out.println(
        "Marks from Student 2-4 : "
        + range);

        System.out.println(
        "\nUpdating Student 3 Marks to 95...");

        st.update(0, 0,
                marks.length - 1,
                2, 95);

        int updated =
            st.query(0, 0,
                    marks.length - 1,
                    0, 4);

        System.out.println(
        "Updated Total Marks : "
        + updated);
    }
}