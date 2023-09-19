package lab5;
import java.util.Stack;

//Definition for singly-linked list.
 class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
 
class Main {
    public static boolean isPalindrome(ListNode head) {
        
        ListNode slow = head;
        boolean isPalin = true;
        Stack<Integer> stack = new Stack<Integer>(); 

        //push every node onto the stack
        while(slow != null)
        {
            stack.push(head.val);
            slow = head.next;
        }
        if(stack.size() == 0)
        	isPalin = true; 
        else
        	if(stack.size() == 1)
        		isPalin = true;
        else
        	if(stack.size() == 2)
        		isPalin = true;
        else {
		        //compare each value of the stack (the end of the palin) with the front of
		        //the palin and so on
		        while(head != null)
		        {
		            int i = stack.pop();
		            if(head.val == i)
		                isPalin = true;
		            else
		                {
		                    isPalin = false;
		                    break; //exit code
		                }
	
		            head = head.next;
		        }
        }
        return isPalin;
    }

   

    public static void main (String[] args)
    {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = null;

        if(isPalindrome(node1))
            System.out.println("is a palindrome");
        else
            System.out.println("is NOT a palindrome");
    }
}
