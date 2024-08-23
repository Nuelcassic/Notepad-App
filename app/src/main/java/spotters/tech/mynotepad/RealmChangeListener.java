package spotters.tech.mynotepad;

public abstract class RealmChangeListener<T> {
    public abstract void onChange(MainActivity.RealmResults<Note> notes);
}
