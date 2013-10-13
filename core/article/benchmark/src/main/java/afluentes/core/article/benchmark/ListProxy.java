package afluentes.core.article.benchmark;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import afluentes.core.api.IEvaluation;
import afluentes.loader.api.IEvaluationHolder;

public class ListProxy<E> implements IEvaluationHolder<List<E>>, List<E> {
	IEvaluation<List<E>> evaluation;
	
	@Override
	public IEvaluation<List<E>> getEvaluation() {
		return evaluation;
	}	

	@Override
	public int size() {
		return evaluation.y().size();
	}

	@Override
	public boolean isEmpty() {
		return evaluation.y().isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return evaluation.y().contains(o);
	}

	@Override
	public Iterator<E> iterator() {
		return evaluation.y().iterator();
	}

	@Override
	public Object[] toArray() {
		return evaluation.y().toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return evaluation.y().toArray(a);
	}

	@Override
	public boolean add(E e) {
		return evaluation.y().add(e);
	}

	@Override
	public boolean remove(Object o) {
		return evaluation.y().remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return evaluation.y().containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		return evaluation.y().addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		return evaluation.y().addAll(index, c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return evaluation.y().removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return evaluation.y().retainAll(c);
	}

	@Override
	public void clear() {
		evaluation.y().clear();
	}

	@Override
	public boolean equals(Object o) {
		return evaluation.y().equals(o);
	}

	@Override
	public int hashCode() {
		return evaluation.y().hashCode();
	}

	@Override
	public E get(int index) {
		return evaluation.y().get(index);
	}

	@Override
	public E set(int index, E element) {
		return evaluation.y().set(index, element);
	}

	@Override
	public void add(int index, E element) {
		evaluation.y().add(index, element);
	}

	@Override
	public E remove(int index) {
		return evaluation.y().remove(index);
	}

	@Override
	public int indexOf(Object o) {
		return evaluation.y().indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return evaluation.y().lastIndexOf(o);
	}

	@Override
	public ListIterator<E> listIterator() {
		return evaluation.y().listIterator();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return evaluation.y().listIterator(index);
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return evaluation.y().subList(fromIndex, toIndex);
	}
}