package treeview.controller;

import org.springframework.web.bind.annotation.*;
import treeview.model.AbstractFile;
import treeview.model.Directory;
import treeview.model.File;
import treeview.model.Tree;

@RestController
@CrossOrigin(origins = "*")
public class Controller {

    @RequestMapping("/getTree")
    public AbstractFile getTree() {
        Tree tree = Tree.getInstance();

        return tree.getRoot();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AbstractFile addFile(@RequestParam Integer parentId,
                        @RequestParam String name,
                        @RequestParam boolean directory) {
        AbstractFile newFile;
        if (directory)
            newFile = new Directory(name);
        else newFile = new File(name);

        Tree tree = Tree.getInstance();
        AbstractFile parent = tree.findById(parentId);
        if (parent != null)
            ((Directory)parent).add(newFile);

        return tree.getRoot();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public AbstractFile deleteFile(@PathVariable Integer id) {
        Tree tree = Tree.getInstance();
        AbstractFile file = tree.findById(id);
        if (file != null) {
            AbstractFile parent = tree.findParentById(id);
            if (parent != null)
                ((Directory) parent).delete(file);
        }

        return tree.getRoot();
    }

    @PostMapping("/move")
    public AbstractFile moveFile(@RequestParam Integer id,
                         @RequestParam Integer destination) {
        Tree tree = Tree.getInstance();
        AbstractFile file = tree.findById(id);

        if (file != null) {

            AbstractFile destinationFile = tree.findById(destination);

            if (destinationFile != null)
                file.move(destinationFile);
        }

        return tree.getRoot();
    }

    @PostMapping("/rename")
    public AbstractFile renameFile(@RequestParam Integer id,
                           @RequestParam String newName) {
        Tree tree = Tree.getInstance();
        AbstractFile file = tree.findById(id);

        if (file != null)
            file.rename(newName);

        return tree.getRoot();
    }
}
