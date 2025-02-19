/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 * // @return true if this NestedInteger holds a single integer, rather than a
 * nested list.
 * public boolean isInteger();
 *
 * // @return the single integer that this NestedInteger holds, if it holds a
 * single integer
 * // Return null if this NestedInteger holds a nested list
 * public Integer getInteger();
 *
 * // @return the nested list that this NestedInteger holds, if it holds a
 * nested list
 * // Return empty list if this NestedInteger holds a single integer
 * public List<NestedInteger> getList();
 * }
 */

// In this problem, using a hashmap for O(1) lookup and doubly linked list for
// the order to maintain the least recently used near
// the tail and most recently used near the head.

// Time Complexity : O(1) for next and O(depth) for has next
// Space Complexity : O(depth)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
public class NestedIterator implements Iterator<Integer> {
    // Declare stack
    Stack<Iterator<NestedInteger>> s;
    // Nextel to get one value at a time
    NestedInteger nextEl;

    // Constructor
    public NestedIterator(List<NestedInteger> nestedList) {
        // Initialize stack and put list in it with iterator on it
        s = new Stack<>();
        s.push(nestedList.iterator());
    }

    // NExt method will just return the integer value of nextEl
    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        // Run a loop till stack is empty
        while (!s.isEmpty()) {
            // Check if there is not element in list
            if (!s.peek().hasNext()) {
                // In that case, pop that list, no point of iterating
                s.pop();
            }
            // Else we know there is next element(integer or list), so store it in nextEl
            // Then check if integer, simply return true
            else if ((nextEl = s.peek().next()).isInteger()) {
                return true;
            }
            // If list, add that list to stack and put iterator on it
            else {
                s.push(nextEl.getList().iterator());
            }
        }
        // If loop ends without returning true means, no next, so false
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */