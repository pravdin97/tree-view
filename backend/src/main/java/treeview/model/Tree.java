package treeview.model;

import java.util.ArrayList;

public class Tree {
    private AbstractFile root;
    private static Tree instance;

    private Tree() {
        Directory root = new Directory("root");
        this.setRoot(root);
    }

    public void setRoot(AbstractFile root) {
        this.root = root;
    }

    public AbstractFile getRoot() {
        return root;
    }

    public static Tree getInstance() {
        if (instance == null)
            instance = new Tree();
        return instance;
    }

    /**
     * Поиск родителя файла
     * @param name - имя файла, для которого проводится поиск родителя
     * @return файл родителя или null
     */
    public AbstractFile findParentByFilename(String name) {
        return findParentByFilename(root, name);
    }

    private AbstractFile findParentByFilename(AbstractFile current, String filename) {
        ArrayList<AbstractFile> children = ((Directory) current).getChildren();
        for (int i = 0 ; i < children.size(); i++) {
            if (children.get(i).getName().equals(filename))
                return current;
        }

        for (int i = 0 ; i < children.size(); i++) {
            if(children.get(i).getClass().getSimpleName().equals("Directory")) {
                AbstractFile abstractFile = findParentByFilename(children.get(i), filename);
                if (abstractFile != null)
                    return abstractFile;
            }
        }
        return null;
    }

    /**
     * Поиск файла по имени
     * @param name - имя файла
     * @return файл или null
     */
    public AbstractFile findByFilename(String name) {
        return findByFilename(root, name);
    }

    private AbstractFile findByFilename(AbstractFile current, String name) {
        if (current.getName().equals(name))
            return current;

        ArrayList<AbstractFile> children = ((Directory) current).getChildren();
        for(int i = 0; i < children.size(); i++) {
            AbstractFile abstractFile = findByFilename(children.get(i), name);
            if (abstractFile != null)
                return abstractFile;
        }

        return null;
    }

    /**
     * Поиск файла родителя по ид
     * @param id - ид файла, для которого проводится поиск родителя
     * @return файл родителя или null
     */
    public AbstractFile findParentById(int id) {
        return findParentById(root, id);
    }

    private AbstractFile findParentById(AbstractFile current, int id) {
        ArrayList<AbstractFile> children = ((Directory) current).getChildren();
        for (int i = 0 ; i < children.size(); i++) {
            if (children.get(i).getId() == id)
                return current;
        }

        for (int i = 0 ; i < children.size(); i++) {
            if(children.get(i).getClass().getSimpleName().equals("Directory")) {
                AbstractFile abstractFile = findParentById(children.get(i), id);
                if (abstractFile != null)
                    return abstractFile;
            }
        }
        return null;
    }

    /**
     * Поиск файла по ид
     * @param id - ид файла
     * @return файл в дереве или null
     */
    public AbstractFile findById(int id) {
        return findById(root, id);
    }

    private AbstractFile findById(AbstractFile current, int id) {
        if (current.getId() == id)
            return current;

        try {
            ArrayList<AbstractFile> children;
            children = ((Directory) current).getChildren();
            for (int i = 0; i < children.size(); i++) {
                AbstractFile abstractFile = findById(children.get(i), id);
                if (abstractFile != null)
                    return abstractFile;
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        return null;
    }

}
