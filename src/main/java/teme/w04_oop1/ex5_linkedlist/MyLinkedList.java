package teme.w04_oop1.ex5_linkedlist;

/**
 * Represents a 'cell' of this linked list - containing the stored value,
 * and a link to the next cell in chain (will be null for last cell)
 */
class Cell {
    //TODO
}

/**
 * Custom class, which stores a sequence of elements, of type String,
 * using a linked list of Cell objects, each one storing one String value,
 * and a link to next cell.
 */
class MyLinkedList {

    //TODO!

    /**
     * Returns true if list is empty, false otherwise
     */
    boolean isEmpty() {
        return false;
    }

    /**
     * Get the size of the list (number of elements it contains)
     */
    int size() {
        return -1;
    }

    /**
     * Return the element at the specified index
     * (or null if index is invalid)
     */
    String get(int index) {
        return null;
    }

    /**
     * Update the element at a specified index
     * (does nothing if index is invalid)
     */
    void set(int index, String newValue) {

    }

    /**
     * Add a new value at the FRONT of the list (before the current head)
     */
    void addBefore(String newValue) {

    }

    /**
     * Insert a new value at the given position in the list,
     * shifting all elements after it with one position up.
     * (does nothing if index is invalid)
     */
    void insert(int index, String newValue) {

    }

    /**
     * Remove the first element (head), also returning its value.
     * (does nothing and returns null if list is empty)
     */
    String removeFirst() {
        return null;
    }

    /**
     * Remove the element at the given index from the list,
     * shifting all elements after it with one position down.
     * (does nothing if index is invalid)
     */
    String remove(int index) {
        return null;
    }

    /**
     * Searches for the given value in the list, and returns the index
     * of its first apparition (or -1 if not found)
     */
    int indexOf(String valueToFind) {
        return -1;
    }

    /**
     * Searches for the given value in the list and returns true if found,
     * false if not found.
     */
    boolean contains(String valueToFind) {
        return false;
    }

    /**
     * Returns a String description of the list, which includes all
     * elements (in the order in which they appear in list)
     */
    @Override
    public String toString() {
        return "MyLinkedList{ ??? }";
    }


    /**
     * Some manual tests...
     */
    public static void main(String[] args) {

        MyLinkedList list = new MyLinkedList();
        System.out.println("\nBefore any action: " + list); //[]
        System.out.println("size: " + list.size() + ", isEmpty: " + list.isEmpty());

        list.addBefore("dd");
        list.addBefore("cc");
        list.addBefore("bb");
        list.addBefore("aa");
        System.out.println("\nAfter adding 4 elements: " + list); //[aa, bb, cc, dd]
        System.out.println("size: " + list.size() + ", isEmpty: " + list.isEmpty());

        System.out.println("\nindexOf(aa): " + list.indexOf("aa")); //0
        System.out.println("indexOf(cc): " + list.indexOf("cc")); //2
        System.out.println("indexOf(xx): " + list.indexOf("xx")); //-1
        System.out.println("contains(cc): " + list.contains("cc")); //true
        System.out.println("contains(xx): " + list.contains("xx")); //false

        System.out.println("\nget(2): " + list.get(2)); //cc
        System.out.println("get(5): " + list.get(5)); //null


        list.insert(0, "ee"); //[ee, bb, cc, dd]
        System.out.println("\nAfter inserting 'ee' at index 0: " + list);
        list.insert(3, "ff"); //[ee, bb, cc, ff, dd]
        System.out.println("After inserting 'ff' at index 3: " + list);
        System.out.println("size: " + list.size());


        list.set(5, "DD!");
        System.out.println("\nAfter update(5, 'DD!'): " + list);
        list.set(10, "ZZ");
        System.out.println("After update(10, 'ZZ'): " + list);


        String removed = list.removeFirst();
        System.out.println("\nAfter removing first elem (" + removed + "): " + list);

        removed = list.remove(0); //[bb, cc, ff, dd]
        System.out.println("\nAfter removing elem at index 0 (" + removed + "): " + list);

        removed = list.remove(3); //[bb, cc, ff]
        System.out.println("After removing elem at index 3 (" + removed + "): " + list);

        System.out.println("size: " + list.size());
    }
}