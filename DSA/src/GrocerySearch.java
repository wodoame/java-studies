void main() {
    String[] receipt = {"apple", "banana", "orange", "grape", "kiwi", "mango", "peach", "pear", "plum", "watermelon", "avocado"};
    String target = "kiwi";
    int index = findItem(receipt, target);
    System.out.println(index); // Expected output: 4
}

int findItem(String[] receipt, String target){
    for (int i = 0; i < receipt.length; i++) {
        if (receipt[i].equalsIgnoreCase(target)) {
            return i; // Return the index of the found item
        }
    }
    return -1; // Return -1 if the item is not found
}
