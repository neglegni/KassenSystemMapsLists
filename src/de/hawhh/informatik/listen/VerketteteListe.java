package de.hawhh.informatik.listen;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

// Fehler, da die notwendigen Methoden noch nicht implementiert sind
public class VerketteteListe<E> extends AbstractList<E> {

    private Node<E> head;
    private Node<E> tail;

    public VerketteteListe() {
        this.tail = new Node();
        this.head = new Node(null, tail);
    }

    /**
     * Konvertieren von Objekt-Sammlungen beliebigen Typs.
     *
     * @param coll
     */
    public VerketteteListe(Collection<? extends E> coll) {
        this.asList((E[]) coll.toArray());
    }

    //  2 Pflichtmethoden
    /**
     * Returns the element at the specified position in this list.
     * Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
     */
    @Override
    public E get(int index) throws IllegalArgumentException {
        if (index > 0 || index < size()) {
            Node<E> indexElement = head.getSucc();
            for (int i = 0; i < index; i++) {
                indexElement = indexElement.getSucc();
            }
            return indexElement.getContent();
        }
        return null;
    }

    /**
     * Returns the number of elements in this list. If this list contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
     */
    @Override
    public int size() {
        Node<E> elem = head;
        int counter = 0;
        while (elem.getSucc() != null) {
            elem = elem.getSucc();
            counter++;
        }
        return counter;
    }

    // 3 modifizierbareList Methoden.

    /**
     * Replaces the element at the specified position in this list with the specified element (optional operation).
     * Returns the element previously at the specified position.
     * Throws:
     * UnsupportedOperationException - if the set operation is not supported by this list
     * ClassCastException - if the class of the specified element prevents it from being added to this list
     * NullPointerException - if the specified element is null and this list does not permit null elements
     * IllegalArgumentException - if some property of the specified element prevents it from being added to this list
     * IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
     */
    public E set​(int index, E element) throws IllegalArgumentException {
        if (index > 0 || index < size()) {
            Node<E> indexElement = head.getSucc();
            for (int i = 0; i < index; i++) {
                indexElement = indexElement.getSucc();
            }
            indexElement.setContent(element);
            return indexElement.getContent();
        }
        return null;
    }

    /**
     * Inserts the specified element at the specified position in this list (optional operation).
     * Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
     *
     * @param index
     * @param element
     * @throws UnsupportedOperationException - if the add operation is not supported by this list
     *                                       ClassCastException - if the class of the specified element prevents it from being added to this list
     *                                       NullPointerException - if the specified element is null and this list does not permit null elements
     *                                       IllegalArgumentException - if some property of the specified element prevents it from being added to this list
     *                                       IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size())
     */
    public void add​(int index, E element) throws IllegalArgumentException {

        Node<E> neuesElement = new Node<E>(element);
        // Element ist erstes Element.
        if (size() == 0) {
            head.setSucc(neuesElement);
            tail.setPred(neuesElement);
            // neuesElement wird hinten angehängt.
        } else if (index == size()) {
            tail.getPred().setSucc(neuesElement);
            tail.setPred(neuesElement);
            // neuesElement soll in der Mitte eingehaengt werden .
        } else {
            if (index > 0 || index < size()) {
                Node<E> indexElement = head.getSucc();
                for (int i = 0; i < index; i++) {
                    indexElement = indexElement.getSucc();
                }
                indexElement.getPred().setSucc(neuesElement);
                indexElement.setPred(neuesElement);
            }
        }
    }


    /**
     * Removes the element at the specified position in this list (optional operation). Shifts any subsequent elements to the left (subtracts one from their indices). Returns the element that was removed from the list.
     *
     * @param index
     * @return the element previously at the specified position.
     * @throws UnsupportedOperationException - if the remove operation is not supported by this list
     *                                       IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
     */
    @SuppressWarnings("unchecked")
    public E remove​(int index) throws IllegalArgumentException {
        if (index < 0 || index > size()) {
            throw new IllegalArgumentException("ungueltiger Index!");
        }
        Node<E> indexElement = head;
        Node<E> loeschen = new Node<E>();
        // element ist hinterstes Element
        if (index == size()) {
            loeschen = tail.getPred();
            tail.setPred(loeschen.getPred());
            loeschen.getPred().setSucc(tail);

            // Element ist erstes Element
        } else if (index == 0) {
            loeschen = head.getSucc();
            head.setSucc(loeschen.getSucc());
            loeschen.getSucc().setPred(head);

            // Element ist in der Mitte
        } else {
            for (int i = 0; i < index; i++) {
                indexElement = indexElement.getSucc();
            }
            loeschen = indexElement;
            loeschen.getSucc().setPred(loeschen.getPred());
            loeschen.getPred().setSucc(loeschen.getSucc());
        }
        return (E) loeschen.getContent();
    }

    private void asList(E[] ary) {
        int counter = 0;
        for (E elem : ary) {
            this.add​(counter, elem);
            counter++;
        }
    }

}

    


    class Node<E>{
        private E content;
        private Node<E> succ;
        private Node<E> pred;

        Node() {
            this(null);
        }

        Node(E content) {
            this(content, null);
        }

        Node(E content, Node<E> succ) {
            this.content = content;
             setSucc(succ);
        }

        void setSucc(Node<E> succ) {
            this.succ = succ;
            if (succ != null){
                succ.pred = this;
            }
        }

        Node<E> getSucc(){
            return succ;
        }

        void setPred(Node<E> pred){
            this.pred = pred;
            if (pred != null){
                pred.succ = this;
            }
        }

        Node<E> getPred() {
            return pred;
        }

        public void setContent(E element) {
            this.content = content;
        }

        public E getContent() {
            return content;
        }

        @Override
        public String toString() {
            return ""+content;
        }


}
