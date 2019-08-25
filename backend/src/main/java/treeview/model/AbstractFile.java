package treeview.model;

// file interface
public interface AbstractFile {
    void move(AbstractFile destination);
    void rename(String name);
    String getName();
    Integer getId();
}
