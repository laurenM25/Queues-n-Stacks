public class StackMoney {
    String[] stack;
    public int t = -1;

    public StackMoney(int capacity) {
        stack = new String[capacity];
    }

    public void push(String s) throws IllegalStateException {
        if (size() == stack.length) {
            throw new IllegalStateException("Stack is full");
        }
        stack[++t] = s;
    }

    public String pop() {
        if (isEmpty()) {
            return null;
        }
        String answer = stack[t];
        stack[t] = null;
        t--;
        return answer;
    }

    public String top() {
        if (isEmpty()) {
            return null;
        }
        return stack[t];
    }

    public int size() {
        return (t + 1);
    }

    public boolean isEmpty() {
        return t == -1;
    }

    public static void main(String[] args) {
        StackMoney stack = new StackMoney(100);
        String[] queueData = { "B,30,100", "B,40,110", "S,50" };
        int gain = 0;

        for (String t : queueData) {
            if (t.charAt(0) == 'B') {
                stack.push(t);
            } else if (t.charAt(0) == 'S') {

                String[] sell = t.split(",");
                int stocksLeft = Integer.valueOf(sell[1]);

                while (stocksLeft > 0) {
                    String[] boughtData = stack.pop().split(",");
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