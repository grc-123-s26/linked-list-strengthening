public class Exercises {
    /**
     * Returns a count of how many nodes are in the linked list.
     * 
     * Returns 0 if head is null.
     * 
     * Example: 
     * Input: 9 -> 2 -> 10 -> 1
     * Output: 4
     * 
     * @param head the head of the linked list
     * @return the length of the list
     */
    public static int length(ListNode head) {
        int count = 0;
        ListNode current = head;

        while (current != null) {
            count++;
            current = current.next;
        }

        return count;
    }

    /**
     * Adds a new value to the front of the list and returns the new head.
     * 
     * Example:
     * head: 7 -> 6 -> 0
     * toAdd: 4
     * 
     * Output: 4 -> 7 -> 6 -> 0
     * 
     * @param head the head of the linked list
     * @param toAdd the value to add to the front
     * @return the new head of the linked list
     */
    public static ListNode prepend(ListNode head, int toAdd) {
        return new ListNode(toAdd, head);
    }

    /**
     * Removes the last element in the linked list and returns the head.
     * 
     * If head is null or the only element in the list, returns null.
     * 
     * Example:
     * Input: 6 -> 4 -> 9 -> 2
     * Output: 6 -> 4 -> 9
     *  
     * @param head the head of the linked list
     * @return the head of the list with the last element removed
     */
    public static ListNode removeLast(ListNode head) {
        ListNode current = head;
        ListNode previous = null;

        if (head == null || head.next == null) { 
            return null;
        }

        while (current.next != null) {
            previous = current;
            current = current.next;
        }

        previous.next = null;
        return head;
    }

    /**
     * Returns the minimum value in the linked list, or Integer.MAX_VALUE if
     * head is null.
     * 
     * Example:
     * Input: 3 -> -4 -> 9 -> 2
     * Output: -4
     * 
     * @param head the head of the linked list
     * @return the minimum value in the list 
     */
    public static int min(ListNode head) {
        if (head == null) {
            return Integer.MAX_VALUE;
        }
        ListNode current = head;

        int minimum = current.data;
        while (current != null) {
            if (current.data < minimum) {
                minimum = current.data;
            }
            current = current.next;
        }
        return minimum;
    }

    /**
     * Removes the first instance of the node with the minimum value from the
     * list and returns the head. If head is null or the only element in the list,
     * returns null.
     * 
     * Examples:
     * Input: 3 -> 9 -> 0 -> -1 -> 5
     * Output: 3 -> 9 -> 0 -> 5
     * 
     * Input: 2 -> 4 -> 2 -> 9
     * Output: 4 -> 2 -> 9
     * 
     * HINT: use min as a helper method
     * 
     * @param head the head of the linked list
     * @return the head of the list with the first instance of the minimum value removed
     */
    public static ListNode removeMin(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        int minimumValue = min(head);
        ListNode current = head;
        ListNode previous = null;

        while (current.next != null) {
            if (current.data == minimumValue){
                if (previous == null){
                    return head.next;
                }
                previous.next = current.next;
            }

            previous = current;
            current = current.next;
        }

        return head;
    }

    /* ------ OPTIONAL CHALLENGE PROBLEMS ------ */

    /**
     * Returns whether two lists are of equal length AND each value in bigList is double
     * that of the corresponding value in smallList. Returns false otherwise.
     * 
     * If BOTH lists are null, returns true.
     * 
     * Example:
     * smallHead: 4 -> 3 -> 9
     * bigHead:   8 -> 6 -> 18
     * Output: true
     * 
     * smallHead: 4 -> 3 -> 9
     * bigHead:   8 -> 6 -> 7
     * Output: false
     * 
     * 
     * @param smallHead the head of the list with the half values
     * @param bigHead the head of the list with the doubled values
     * @return whether the values in bigList are twice the values in smallList
     */
    public static boolean isDoubled(ListNode smallHead, ListNode bigHead) {
        if (smallHead == null && bigHead == null) {
            return true;
        }

        ListNode smallListCurrent = smallHead;
        ListNode bigListCurrent = bigHead;

        while (smallListCurrent != null && bigListCurrent != null) {

            if (smallListCurrent.data * 2 != bigListCurrent.data) {
                return false;
            }

            if (smallListCurrent.next == null && bigListCurrent.next == null) {
                return true;
            }

            smallListCurrent = smallListCurrent.next;
            bigListCurrent = bigListCurrent.next;
        }

        return false;
    }

    /**
     * Rotates a list by k elements to the left and returns the new head.
     * 
     * If head is null, return null.
     * 
     * Example:
     * head: 1 -> 2 -> 3 -> 4 -> 5
     * k: 2
     * Output: 3 -> 4 -> 5 -> 1 -> 2
     * 
     * @param head the head of the linked list
     * @param k the number of positions to rotate
     * @return the head of the new list after k rotations to the left
     */
    public static ListNode rotateLeft(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        if (head.next == null || k == 0) {
                return head;
        }

        ListNode newHead = head;
        ListNode last = head;

        for (int i = 0; i < k; i++) {
            while (last.next != null) { // Get last element of the list
                last = last.next;
            }
            newHead = head.next;
            newHead.next = head.next.next;
            last.next = head;
            head.next = null;
            head = newHead;
        }

        return newHead;
    }
}
