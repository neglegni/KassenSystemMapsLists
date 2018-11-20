package de.hawhh.informatik.listen;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

// Fehler, da die notwendigen Methoden noch nicht implementiert sind
public class VerketteteListe<E> extends AbstractList<E> {

    private Node<E> head;
    private Node<E> tail;
    //neu eingeführt.
    private int anzahlElemente = 0;

    public static void main(String[] args) {
        VerketteteListe neu = new VerketteteListe();

    }

    public VerketteteListe(){
        this.tail= new Node();
        this.head= new Node(null,tail);
    }

    /*
        Generischer Kopier-Konstruktor
     */
    public VerketteteListe(VerketteteListe alteListe) {
        VerketteteListe neu = new VerketteteListe();
        neu.head.setContent(alteListe.head.getContent());
        neu.tail.setContent(alteListe.head.getSucc().getContent());
        Node<E> indexElem = alteListe.head
        while(indexElem.getSucc() != null) {
            this.add​(size(),indexElem.getContent());
            indexElem =indexElem.getSucc();
        }
    }

    /**
     * Konvertieren von Objekt-Sammlungen beliebigen Typs.
     * @param coll
     */
    public VerketteteListe(Collection<? extends E> coll){
        // TODO
    }

    // TODO Methoden implementieren 2 Pflichtmethoden
    /**
     * Returns the element at the specified position in this list.
     * Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
     * */
    @Override
    public int get(int index) throws IllegalArgumentException {
        Node<E> indexElement = head;
        for(int i = 0; i < index; i++) {
            indexElement = indexElement.getSucc();
        }
        return indexElement.getContent();
    }



    /**
     * Returns the number of elements in this list. If this list contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
     * */
    @Override
    public int size(){
        return anzahlElemente;
    }

    // TODO Methoden implementieren modifizierbareList Methoden.

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
    @Override
    public E set​(int index, E element)throws IllegalArgumentException {
        Node<E> indexElement = head;
        for(int i = 0; i < index; i++) {
            indexElement = indexElement.getSucc();
        }
        indexElement.setContent(element);
        return indexElement.getContent();
    }

    /**
     * Inserts the specified element at the specified position in this list (optional operation).
     * Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
     *
     * @throws UnsupportedOperationException - if the add operation is not supported by this list
     * ClassCastException - if the class of the specified element prevents it from being added to this list
     * NullPointerException - if the specified element is null and this list does not permit null elements
     * IllegalArgumentException - if some property of the specified element prevents it from being added to this list
     * IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size())
     * @param index
     * @param element
     */
    @Override
    public void add​(int index, E element) throws IllegalArgumentException {

            Node<E> neuesElement = new Node<T>(element);
            // Element ist erstes Element.
            if(anzahlElemente == 0) {
                head=neuesElement();
                tail=neuesElement();
                // neuesElement wird hinten angehängt.
            } else if (index == anzahlElemente) {
                tail.setSucc(neuesElement);
                neuesElement.setPred(tail);
                neuesElement.setPred(null);
                tail=neuesElement;
                // neuesElement soll in der Mitte eingehaengt werden .
            } else {
                Node<T> indexElement = neuesElement;
                for (int i = 0; i < index; i++) {
                    indexElement = indexElement.getSucc();
                }
                neuesElement.setSucc(indexElement.getSucc());
                indexElement.getSucc().setPred(neuesElement);
                indexElement.setSucc(neuesElement);
                neuesElement.setPred(indexElement);
                indexElement = indexElement.getSucc();
            }
            anzahlElemente++;
        }


    /**
     * Removes the element at the specified position in this list (optional operation). Shifts any subsequent elements to the left (subtracts one from their indices). Returns the element that was removed from the list.
     * @param index
     * @return the element previously at the specified position.
     * @throws UnsupportedOperationException - if the remove operation is not supported by this list
     * IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
     */
    @SuppressWarnings("unchecked")
    @Override
    public E remove​(int index) throws IllegalArgumentException {
            if (index < 0 || index > size()) {
                throw new IllegalArgumentException("ungueltiger Index!");
            }
            Node<T> indexElement = loeschen;
            Node<T> loeschen;
            // element ist hinterstes Element
            if(index == size()) {
                loeschen = tail.getPred();
                tail.setPred(tail.getPred().getPred());
                tail.getPred().setSucc(tail);

                // Element ist erstes Element
            } else if (index == 0){

                loeschen = loeschen.getSucc();
                loeschen.setSucc(loeschen.getSucc().getSucc());
                loeschen.getSucc().setPred(loeschen);
                // Element ist in der Mitte
            } else {
                for(int i = 1;i<index ;i++) {
                    indexElement = indexElement.getSucc();
                }
                loeschen = indexElement.getSucc().getSucc();
                indexElement.setSucc(indexElement.getSucc().getSucc());
                indexElement.getSucc().setPred(indexElement);
            }
            anzahlElemente--;
            return (T) loeschen.getObject();
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

}
