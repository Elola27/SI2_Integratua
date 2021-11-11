package domain;

import java.util.Vector;

public class ExtendedIteratorEvents implements ExtendedIterator {
	private int pos;
	private Vector<Event> apus;
	
	public ExtendedIteratorEvents(Vector<Event> p) {
		this.pos=0;
		this.apus=p;
	}
	
	
	public Object previous() {
		// TODO Auto-generated method stub
		Event uneko=this.apus.get(pos);
		pos--;
		return uneko;
	}

	public boolean hasPrevious() {
		// TODO Auto-generated method stub
		if (this.pos>=0) {
			return true;
		}else {
			return false; 
		}
	}

	public void goFirst() {
		// TODO Auto-generated method stub
		this.pos=0;
	}

	public void goLast() {
		// TODO Auto-generated method stub
		this.pos=apus.size()-1;
	}

	public boolean hasNext() {
		// TODO Auto-generated method stub
		if (this.pos<=this.apus.size()-1) {
			return true;
		}else {
			return false; 
		}
	}

	public Object next() {
		// TODO Auto-generated method stub
		Event uneko=this.apus.get(pos);
		pos++;
		return uneko;
	}

}
