import java.util.List;

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
        if (head == null) {
            return 0;
        }

        ListNode current = head;
        int listLen = 1;

        while (current.next != null) {
            listLen++;
            current = current.next;
        }
        return listLen;

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
        //make a new listNode(toAdd, head)
        //return that list node
        ListNode returnNode = new ListNode(toAdd, head);
        return returnNode;
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
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return null;
        }
        //loop through
        ListNode current = head;
        while (current.next.next != null) { //a -> b -> null
            current = current.next;
        }
        current.next = null; //nulling current doesn't work, have to null the thing next to my variable to actually erase it
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
        int val = current.data;
        while (current.next != null) {
            current = current.next;
            if (val > current.data) {
                val = current.data;
            }
        }
        return val;
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
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return null;
        }

        //use min to determine what number we are hunting for in the linklist
        int low = min(head);

        //if the first value is the lowest, then new head become next
        if (head.data == low) {
            return head.next;
        }

        //if im conceptualizing this correctly:
        // we need a - b - c (didn't need c)
        ListNode left = head;
        ListNode center = left.next;
        // ListNode right = center.next; (didn't need)

        //while loop through the list until we spot the min val
        while (center != null) { 

            if (center.data == low) {
                //connect left to right
                left.next = center.next; //by using nexts we find the thing at the location
                return head;
            }
            left = left.next; //shift over 1
            center = left.next; //shift based on left
            // right = center.next; //shift based on center (didn't need)
        }
        return null;
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
    public static boolean isDoubled(ListNode smallList, ListNode bigList) {
        if (smallList == null && bigList == null) { //are they null
            return true;
        }
        if (smallList == null || bigList == null) { //if only one is null
            return false;
        }
        if (length(smallList) != length(bigList)) { //cehck if same size
            return false;
        }
        ListNode smolC = smallList;
        ListNode bigC = bigList;
        while (smolC != null && bigC != null) {
            if (smolC.data * 2 != bigC.data) {
                return false;
            }
            smolC = smolC.next;
            bigC = bigC.next;
        }
        return true;
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
        ListNode current = head; // 1
        int len = length(head); // 5
        int[] array = new int[len]; // [_,_,_,_,_]
        for (int i = 0; i < len; i++) {
            array[i] = current.data;
            current = current.next;                        
        } // [1,2,3,4,5]

        int kLoop = k;
        while (kLoop >= len) {
            kLoop = kLoop - len; //ie if len is 5 and kloop is 5, rotate 0 times, or kloop is 6 and len is 5, rotate 1 time
        }

        if (kLoop == 0) { //if we're going to rotate 0 times or length number of times, just return the original
            return head;
        }

        int[] swapArray = new int[len]; //[_,_,_,_,_]
        //starting position is index[kloop + i]

        for (int i = 0; i < len - kLoop; i++) { //[_,_,X,_,_]
            swapArray[i] = array[kLoop + i];
        } // [3,4,5,_,_]
        for (int i = 0; i < kLoop; i++) { //[_,_,_,X,_]
            swapArray[len - kLoop + i] = array[i];
        } // [_,_,_,1,2]

        //make a new returnvalue to be my new head
        ListNode returnHead = new ListNode(swapArray[0]);
        //move current pointer
        current = returnHead;

        for (int i = 1; i < len; i++) {
            //make new ListNodes based off of the swapArray
            current.next = new ListNode(swapArray[i]);
            current = current.next;
        }
        return returnHead;
    }
}
