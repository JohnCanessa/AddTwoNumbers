import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * Definition for singly-linked list.
 */
class ListNode {

    // **** class members ****
    int val;
    ListNode next;

    // **** constructors ****
    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val    = val;
        this.next   = next;
    }
}


/**
 * LeetCode 2. Add Two Numbers 
 * https://leetcode.com/problems/add-two-numbers/
 */
public class AddTwoNumbers {


    /**
     * Populate linked list with the specified array values.
     * 
     * !!! NOT PART OF SOLUTION !!!
     */
    static ListNode populate(int[] arr) {

        // **** sanity ckeck(s) ****
        if (arr.length == 0)
            return null;

        // **** initialization ****
        ListNode h = null;

        // **** traverse array adding nodes to the linked list ****
        for (int i = arr.length - 1; i >= 0; i--) {
            h = new ListNode(arr[i], h);
        }

        // **** return head of linked list ****
        return h;
    }


    /**
     * Display linked list.
     * 
     * !!! NOT PART OF SOLUTION !!!
     */
    static void display(ListNode head) {

        // **** sanity check(s) ****
        if (head == null)
            return;

        // **** traverse the link list ****
        for (ListNode p = head; p != null; p = p.next) {
            System.out.print(p.val);
            if (p.next != null)
                System.out.print("->");
        }
    }


    /**
     * Add the two numbers and return the sum as a linked list.
     * 
     * Runtime: 2 ms, faster than 79.87% of Java online submissions.
     * Memory Usage: 39.8 MB, less than 9.03% of Java online submissions.
     */
    static ListNode addTwoNumbers0(ListNode l1, ListNode l2) {

        // **** initialization ****
        ListNode l3     = null;
        ListNode p      = l1;
        ListNode q      = l2;
        int carry       = 0;
        ListNode tail   = null;

        // **** traverse the linked lists computing the sum digit by digit O(n) ****
        while (p != null || q != null) {

            // **** sum carry and digits for current position ****
            int s = carry;
            if (p != null)
                s += p.val;
            if (q != null)
                s += q.val;

            // **** update carry ****
            if (s >= 10)
                carry = 1;
            else
                carry = 0;

            // **** update s ****
            s %= 10;

            // **** save digit in new node and append to result list ****
            if (l3 == null) {
                l3      = new ListNode(s);
                tail    = l3;
            } else {
                ListNode d  = new ListNode(s);
                tail.next   = d;
                tail        = d;
            }

            // **** update p & q ****
            if (p != null)
                p = p.next;
            if (q != null)
                q = q.next;
        }

        // **** take care of carry bit (if needed) ****
        if (carry != 0) {
            ListNode d  = new ListNode(carry);
            tail.next   = d;
            tail        = d;
        }

        // **** return sum ****
        return l3;
    }


    /**
     * Add the two numbers and return the sum as a linked list.
     * 
     * Runtime: 2 ms, faster than 76.89% of Java online submissions.
     * Memory Usage: 38.8 MB, less than 95.20% of Java online submissions.
     */
    static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        // **** initialization ****
        ListNode l3 = null;
        ListNode p  = l1;
        int carry   = 0;

        // **** traverse the linked lists computing the sum digit by digit O(n) ****
        while (l1 != null || l2 != null) {

            // **** add two digits (if needed) ****
            if (l1 != null && l2 != null) {

                // **** add two digits and carry ****
                int sum = l1.val + l2.val + carry;

                // **** update sum and carry ****
                if (sum > 9) {
                    carry = sum / 10;
                    sum %= 10;
                } else {
                    carry = 0;
                }

                // **** insert new node into l3 ****
                if (l3 == null) {
                    p = new ListNode(sum, null);
                    l3 = p;
                } else {
                    p.next = new ListNode(sum, null);
                    p = p.next;
                }

                // **** move both pointers forward ****
                l1 = l1.next;
                l2 = l2.next;

            } else if (l1 != null) {

                // **** add digit and carry ****
                int sum = l1.val + carry;

                // **** update sum and carry ****
                if (sum > 9) {
                    carry = sum / 10;
                    sum %= 10;
                } else {
                    carry = 0;
                }

                // **** insert new node into l3 ****
                if (l3 == null) {
                    p = new ListNode(sum, null);
                    l3 = p;
                } else {
                    p.next = new ListNode(sum, null);
                    p = p.next;
                }

                // **** move l1 forward ****
                l1 = l1.next;

            } else if (l2 != null) {

                // **** add digit and carry ****
                int sum = l2.val + carry;

                // **** update sum and carry ****
                if (sum > 9) {
                    carry = sum / 10;
                    sum %= 10;
                } else {
                    carry = 0;
                }

                // **** insert new node into l3 ****
                if (l3 == null) {
                    p = new ListNode(sum, null);
                    l3 = p;
                } else {
                    p.next = new ListNode(sum, null);
                    p = p.next;
                }

                // **** move l2 forward ****
                l2 = l2.next;
            }
        }

        // **** insert new carry node into l3 ****
        if (carry > 0)
            p.next = new ListNode(carry, null);

        // **** return sum ****
        return l3;
    }


    /**
     * Test scaffolding
     * 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read first list of values ****
        String[] strArr1 = br.readLine().trim().split(",");

        // **** read second list of values ****
        String[] strArr2 = br.readLine().trim().split(",");

        // *** close buffered reader ****
        br.close();
        
        // **** generate array of integers for the first list ****
        int[] arr1 = Arrays.stream(strArr1).mapToInt(Integer::parseInt).toArray();

        // **** generate array of integers for second list ****
        int[] arr2 = Arrays.stream(strArr2).mapToInt(Integer::parseInt).toArray();

        // ???? ????
        System.out.println("main <<< arr1: " + Arrays.toString(arr1));
        System.out.println("main <<< arr2: " + Arrays.toString(arr2));

        // **** generate first linked list ****
        ListNode l1 = populate(arr1);

        // **** generate second linked list ****
        ListNode l2 = populate(arr2);

        // ???? display first linked list ????
        System.out.print("main <<< l1: ");
        display(l1);
        System.out.println();

        // ???? display second linked list ????
        System.out.print("main <<< l2: ");
        display(l2);
        System.out.println();

        // **** add the two numbers and return the result as a linked list ****
        ListNode l3 = addTwoNumbers0(l1, l2);

        // ???? display the linked list with result ????
        System.out.print("main <<< l3: ");
        display(l3);
        System.out.println();

        // **** add the two numbers and return the result as a linked list ****
        l3 = addTwoNumbers(l1, l2);

        // ???? display the linked list with result ????
        System.out.print("main <<< l3: ");
        display(l3);
        System.out.println();
    }
}