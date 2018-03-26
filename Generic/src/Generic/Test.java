package Generic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



abstract class Media {
	protected String name;
	
	public Media(String name) {
		super();
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}

class Book extends Media {

	int pages;

	public Book(String name, int pages) {
		super(name);
		this.pages = pages;
	}

	/**
	 * @return the pages
	 */
	public int getPages() {
		return pages;
	}
	/**
	 * @param pages the pages to set
	 */
	public void setPages(int pages) {
		this.pages = pages;
	}
}



class Library<T> {
	public ArrayList<T> arr;
	/**
	 * @return the arr
	 */
	public ArrayList<T> getArr() {
		return arr;
	}

	/**
	 * @param arr the arr to set
	 */
	public void setArr(ArrayList<T> arr) {
		this.arr = arr;
	}

	public void addElement(T t) {
		arr.add(t);
	}
	
	public Library() {
		arr = new ArrayList<T>();
		// TODO Auto-generated constructor stub
	}


}

public class Test {
	
	public Test() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String args[]) {
		
		Book book = new Book("Jury", 20);
		
		Book book2 = new Book("Tom", 1900);
		
		Library<Book> lib = new Library<Book>();
		lib.arr.add(book);
		lib.arr.add(book2);
	
		Collections.sort(lib.arr, new Comparator<Book>() {
			/* (non-Javadoc)
			 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
			 */
			@Override
			public int compare(Book o1, Book o2) {
				return o2.name.compareTo(o1.name);
			}
		});
		
		for (Book b : lib.arr) {
			System.out.println(b.name + " " + Integer.toString(b.pages));
		}
	}
}
