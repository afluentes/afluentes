package afluentes.core.article.benchmark;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import afluentes.core.api.IEvaluation;

abstract class AbstractRecipientsProxy implements List<IUser> {	
	abstract List<IUser> getRecipients();

	@Override
	public int size() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isEmpty() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<IUser> iterator() {
		return getRecipients().iterator();
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean add(IUser e) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends IUser> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, Collection<? extends IUser> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}

	@Override
	public IUser get(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public IUser set(int index, IUser element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void add(int index, IUser element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public IUser remove(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int indexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<IUser> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<IUser> listIterator(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<IUser> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}
}

class StandardRecipientsProxy extends AbstractRecipientsProxy {
	AbstractDao dao;
	int messageId;
	List<IUser> recipients;

	StandardRecipientsProxy(AbstractDao dao, int messageId) {
		this.dao = dao;
		this.messageId = messageId;
	}

	@Override
	List<IUser> getRecipients() {
		if (recipients == null) {
			recipients = dao.getRecipients(messageId);
		}
		return recipients;
	}
}

class AfluentesRecipientsProxy extends AbstractRecipientsProxy {
	IEvaluation<List<IUser>> evaluation;

	AfluentesRecipientsProxy(IEvaluation<List<IUser>> evaluation) {
		this.evaluation = evaluation;
	}

	@Override
	List<IUser> getRecipients() {
		return evaluation.y();
	}
}