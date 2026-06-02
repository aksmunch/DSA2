import java.util.*;

// Water Consumption Analysis using Segment Tree

class SegmentTree {

    int[] tree;
    int n;

    SegmentTree(int[] waterUsage) {

        n = waterUsage.length;
        tree = new int[4 * n];

        build(waterUsage, 0, 0, n - 1);
    }

    // Build Segment Tree
    void build(int[] arr, int node,
               int start, int end) {

        if (start == end) {

            tree[node] = arr[start];

        } else {

            int mid = (start + end) / 2;

            build(arr, 2 * node + 1,
                    start, mid);

            build(arr, 2 * node + 2,
                    mid + 1, end);

            tree[node] =
                    tree[2 * node + 1]
                    + tree[2 * node + 2];
        }
    }

    // Range Sum Query
    int query(int node,
              int start,
              int end,
              int left,
              int right) {

        if (right < start || left > end)
            return 0;

        if (left <= start && end <= right)
            return tree[node];

        int mid = (start + end) / 2;

        return query(2 * node + 1,
                start,
                mid,
                left,
                right)
                +
                query(2 * node + 2,
                        mid + 1,
                        end,
                        left,
                        right);
    }

    // Update Water Usage
    void update(int node,
                int start,
                int end,
                int index,
                int value) {

        if (start == end) {

            tree[node] = value;

        } else {

            int mid = (start + end) / 2;

            if (index <= mid) {

                update(2 * node + 1,
                        start,
                        mid,
                        index,
                        value);

            } else {

                update(2 * node + 2,
                        mid + 1,
                        end,
                        index,
                        value);
            }

            tree[node] =
                    tree[2 * node + 1]
                    + tree[2 * node + 2];
        }
    }
}

// Main Class
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print(
                "Enter Number of Days: ");

        int n = sc.nextInt();

        int[] waterUsage =
                new int[n];

        System.out.println(
                "\nEnter Daily Water Consumption:");

        for (int i = 0; i < n; i++) {

            System.out.print(
                    "Day " + (i + 1) + ": ");

            waterUsage[i] = sc.nextInt();
        }

        SegmentTree st =
                new SegmentTree(waterUsage);

        System.out.println(
                "\n===== WATER CONSUMPTION RECORDS =====");

        for (int i = 0; i < n; i++) {

            System.out.println(
                    "Day " + (i + 1)
                            + " : "
                            + waterUsage[i]
                            + " Liters");
        }

        // Range Query
        System.out.print(
                "\nEnter Start Day: ");

        int startDay = sc.nextInt();

        System.out.print(
                "Enter End Day: ");

        int endDay = sc.nextInt();

        int totalUsage =
                st.query(0,
                        0,
                        n - 1,
                        startDay - 1,
                        endDay - 1);

        System.out.println(
                "\nTotal Water Consumption from Day "
                        + startDay
                        + " to Day "
                        + endDay
                        + " = "
                        + totalUsage
                        + " Liters");

        // Update
        System.out.print(
                "\nEnter Day to Update: ");

        int updateDay = sc.nextInt();

        System.out.print(
                "Enter New Water Consumption: ");

        int newUsage = sc.nextInt();

        st.update(0,
                0,
                n - 1,
                updateDay - 1,
                newUsage);

        waterUsage[updateDay - 1] =
                newUsage;

        System.out.println(
                "\nUPDATED WATER RECORDS");

        for (int i = 0; i < n; i++) {

            System.out.println(
                    "Day " + (i + 1)
                            + " : "
                            + waterUsage[i]
                            + " Liters");
        }

        int updatedTotal =
                st.query(0,
                        0,
                        n - 1,
                        0,
                        n - 1);

        System.out.println(
                "\nTotal Water Consumption = "
                        + updatedTotal
                        + " Liters");

        sc.close();
    }
}
