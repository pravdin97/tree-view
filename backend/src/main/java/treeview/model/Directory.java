package treeview.model;

import java.util.ArrayList;
import java.util.Collections;


// composite
public class Directory implements AbstractFile {
    private Integer id;
    private String name;
    private ArrayList<AbstractFile> children;
    private boolean isDirectory = true;

    public boolean isDirectory() {
        return isDirectory;
    }

    public Integer getId() {
        return id;
    }

    public Directory(String name) {
        this.name = name;
        id = GenerateID.getID();
        children = new ArrayList<>();
    }

    public ArrayList<AbstractFile> getChildren() {
        return children;
    }

    public void add(AbstractFile ...files) {
        Collections.addAll(children, files);
    }

    public void delete(AbstractFile file) {
        children.remove(file);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void move(AbstractFile destination) {
        try {
            //удаляем в текущей директории
            AbstractFile parent = Tree.getInstance().findParentByFilename(name);
            if (parent != null)
                ((Directory) parent).delete(this);

            //добавляем в нужную директорию
            Directory destDir = (Directory) destination;
            destDir.add(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rename(String name) {
        this.name = name;
    }
}
