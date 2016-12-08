package bzh.terrevirtuelle.navisu.core.model.generic.impl;

import bzh.terrevirtuelle.navisu.core.model.generic.Model;
import bzh.terrevirtuelle.navisu.core.model.generic.ModelEvents;
import bzh.terrevirtuelle.navisu.core.model.generic.ReadDataServices;
import bzh.terrevirtuelle.navisu.core.model.generic.WriteDataServices;
import bzh.terrevirtuelle.navisu.core.util.ICloneable;

import java.util.*;

/**
 * Created with IntelliJ IDEA. User: tibus Date: 17/10/13 Time: 20:01 To change
 * this template use File | Settings | File Templates.
 *
 * @param <T>
 */
public class ModelImpl<T extends ICloneable> implements Model<T>, WriteDataServices<T> {

    protected Map<Integer, T> dataMap;
    protected List<ModelEvents<T>> observerList;

    protected ModelEvents.ModelEventsSubscribe<T> eventsSubscribe;

    @SuppressWarnings("unchecked")
    public ModelImpl() {
        this.dataMap = new TreeMap<>();
        this.observerList = new ArrayList<>();
        this.eventsSubscribe = this.createEventsSubscribe();
    }

    protected ModelEvents.ModelEventsSubscribe createEventsSubscribe() {
        return new ModelEvents.ModelEventsSubscribe<T>() {

            @Override
            public void subscribe(ModelEvents<T> observer) {
                ModelImpl.this.observerList.add(observer);
            }

            @Override
            public void unsubscribe(ModelEvents<T> observer) {
                ModelImpl.this.observerList.remove(observer);
            }
        };
    }

    @Override
    public ReadDataServices<T> getReadDataServices() {
        return this;
    }

    @Override
    public WriteDataServices<T> getWriteDataServices() {
        return this;

    }

    @Override
    public ModelEvents.ModelEventsSubscribe getEventsSubscribe() {
        return this.eventsSubscribe;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void create(int id, T data) {
        checkId(id);
        if (data == null) {
            throw new NullPointerException("data is null");
        }
        if (this.dataMap.containsKey(id)) {
            throw new RuntimeException("id " + id + " already added to the tObjectModel");
        }
        final ICloneable clone = (ICloneable) data.getClone();
        this.dataMap.put(id, (T) clone);//pb

        for (ModelEvents<T> observer : this.observerList) {
            observer.notifyDataCreated(id, (T) data.getClone());
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void update(int id, T data) {

        checkId(id);
        if (data == null) {
            throw new NullPointerException("data is null");
        }

        if (!this.dataMap.containsKey(id)) {
            throw new RuntimeException("id " + id + " does not exists");
        }

        final ICloneable clone = (ICloneable) data.getClone();
        this.dataMap.put(id, (T) clone);

        this.observerList.forEach((observer) -> {
            observer.notifyDataUpdated(id, (T) data.getClone());
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public T delete(int id) {

        checkId(id);

        if (!this.dataMap.containsKey(id)) {
            throw new RuntimeException("id " + id + " does not exists");
        }

        // Keep the data
        T deletedData = dataMap.get(id);

        // And delete it from the map
        dataMap.remove(id);

        for (ModelEvents<T> observer : this.observerList) {
            observer.notifyDataDeleted(id, (T) deletedData.getClone());
        }

        return deletedData;
    }

    @Override
    public T read(int id) {

        checkId(id);

        if (!this.dataMap.containsKey(id)) {
            throw new RuntimeException("id " + id + " does not exists");
        }

        return dataMap.get(id);
    }

    @Override
    public boolean contains(int id) {

        checkId(id);

        return this.dataMap.containsKey(id);
    }

    @Override
    public int size() {
        return this.dataMap.size();
    }

    protected void checkId(int id) {

        if (id < 0) {
            throw new IllegalArgumentException("id must be greater than 0");
        }
    }
}
