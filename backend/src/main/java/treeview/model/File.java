package treeview.model;

// leaf
public class File implements AbstractFile {

    private Integer id;
    private String name;
    private boolean isDirectory = false;

    public boolean isDirectory() {
        return isDirectory;
    }

    public Integer getId() {
        return id;
    }

    public File(String name) {
        id = GenerateID.getID();
        this.name = name;
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
