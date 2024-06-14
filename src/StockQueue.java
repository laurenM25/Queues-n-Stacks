public class StockQueue {
    // instance variables
    /** Default array capacity. */
    public static final int CAPACITY = 1000; // default array capacity

    /** Generic array used for storage of queue elements. */
    private static String[] data = new String[1000]; // generic array used for storage

    /** Index of the top element of the queue in the array. */
    private static int f = 0; // index of the front element

    /** Current number of elements in the queue. */
    private static int sz = 0; // current number of elements

    // constructors
    /** Constructs an empty queue using the default array capacity. */
    public StockQueue() {
    } // constructs queue with default capacity

    // methods
    /**
     * Returns the number of elements in the queue.
     * 
     * @return number of elements in the queue
     */
    public static int size() {
        return sz;
    }

    /** Tests whether the queue is empty. */
    public static boolean isEmpty() {
        return (sz == 0);
    }

    /**
     * Inserts an element at the rear of the queue.
     * This method runs in O(1) time.
     * 
     * @param e new element to be inserted
     * @throws IllegalStateException if the array storing the elements is full
     */
    public static void enqueue(String e) throws IllegalStateException {
        if (sz == data.length)
            throw new IllegalStateException("Queue is full");
        int avail = (f + sz) % data.length; // use modular arithmetic
        data[avail] = e;
        sz++;
    }

    /**
     * Returns, but does not remove, the first element of the queue.
     * 
     * @return the first element of the queue (or null if empty)
     */
    public static String first() {
        if (isEmpty())
            return null;
        return data[f];
    }

    /**
     * Removes and returns the first element of the queue.
     * 
     * @return element removed (or null if empty)
     */
    public static String dequeue() {
        if (isEmpty())
            return null;
        String answer = data[f];
        data[f] = null; // dereference to help garbage collection
        f = (f + 1) % data.length;
        sz--;
        return answer;
    }

    public static void main(String[] args) {
        String[] queueData = { "B,30,100", "B,40,110", "S,50" };
        int gain = 0;

        for (String t : queueData) {
            if (t.charAt(0) == 'B') {
                enqueue(t);
            } else if (t.charAt(0) == 'S') {
                String[] sell = t.split(",");
                int stocksLeft = Integer.valueOf(sell[1]);

                while (stocksLeft > 0) {
                    String[] boughtData = dequeue().split(",");
                    int numBought = Integer.valueOf(boughtData[1]);
                    int boughtVal = Integer.valueOf(boughtData[2]);

                    if (stocksLeft <= numBought) {
                        gain = gain + stocksLeft * boughtVal;
                        stocksLeft = 0;
                    } else if (stocksLeft > numBought) {
                        gain = gain + numBought * boughtVal;
                        stocksLeft = stocksLeft - numBought;
                    }
                }
            }
        }

        System.out.println("Capital gain: $" + gain);
    }
}