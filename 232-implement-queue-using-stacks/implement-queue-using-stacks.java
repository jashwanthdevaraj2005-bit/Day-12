class MyQueue {
    private Stack<Integer> first;
    private Stack<Integer> second;

    public MyQueue() {
        first = new Stack<>();
        second = new Stack<>();
    }
    
    // Insert an element into the queue
    public void push(int x) {
        first.push(x);
    }
    
    // Remove and return the front element
    public int pop() {
        while (!first.isEmpty()) {
            second.push(first.pop());
        }

        int firstElement = second.pop();

        while (!second.isEmpty()) {
            first.push(second.pop());
        }

        return firstElement;
    }
    
    // Return the front element without removing it
    public int peek() {
        while (!first.isEmpty()) {
            second.push(first.pop());
        }

        int firstElement = second.peek();

        while (!second.isEmpty()) {
            first.push(second.pop());
        }

        return firstElement;
    }
    
    // Check whether the queue is empty
    public boolean empty() {
        return first.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */