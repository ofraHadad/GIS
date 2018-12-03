package GIS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyGisLayer implements GIS_layer {

	private ArrayList<GIS_element> layer ;
	private LayerMetaData metaData;

	public LayerMetaData getMetaData() {
		return metaData;
	}



	public MyGisLayer() {
		layer= new ArrayList<GIS_element>();
		
	}


	private ArrayList<GIS_element> getLayer() {
		return layer;
	}

	public boolean equals(GIS_layer l) {
		Iterator it= l.iterator();
		if(l.size()!=size()) {
			return false;
		}
		while(it.hasNext()) {
			if(!contains(it.next())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean add(GIS_element arg0) {

		if(getLayer().contains(arg0)) {
			return false;
		}
		return layer.add(arg0);
	}

	@Override
	public boolean addAll(Collection<? extends GIS_element> arg0) {
		Iterator collection=arg0.iterator();
		boolean add= true;
		boolean out=true;
		while(collection.hasNext()) {
			GIS_element element= (GIS_element) collection.next();
			add= add(element);
			if(!add) {
				out=add;
			}
		}
		return out;
	}

	@Override
	public void clear() {
		layer.clear();		
	}

	@Override
	public boolean contains(Object arg0) {
		MyGisElement arg= (MyGisElement)arg0;
		Iterator<GIS_element> it= this.iterator();
		while(it.hasNext()) {
			arg.equals(it.next());
			return true;
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return layer.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		return layer.isEmpty();
	}

	@Override
	public Iterator<GIS_element> iterator() {
		return layer.iterator();

	}

	@Override
	public boolean remove(Object arg0) {
		return layer.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return layer.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return layer.retainAll(arg0);
	}

	@Override
	public int size() {
		return layer.size();
	}

	@Override
	public Object[] toArray() {
		return layer.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return layer.toArray(arg0);
	}

	@Override
	public Meta_data get_Meta_data() {
		return metaData;
	}

	public String toString() {
		Iterator it= iterator();
		String ans="Meta Data: "+ get_Meta_data()+"";
		while(it.hasNext()) {
			ans= ans+it.next()+"\n";
		}
		return ans;
	}



	private boolean isSet(Collection<? extends GIS_element> arg0) {
		Iterator a=iterator();
		Iterator b=iterator();
		while(a.hasNext()) {
			GIS_element elementA= (GIS_element) a.next();
			while(b.hasNext()) {
				GIS_element elementB= (GIS_element) b.next();
				if(!elementA.equals(elementB)) {
					return false;
				}
			}
		}
		return true;
	}



	public void setMetaData(LayerMetaData metaData) {
		this.metaData = metaData;
	}
	

}
