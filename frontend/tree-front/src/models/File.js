import React, {useState} from 'react'
import Menu from './Menu';
// import { ContextMenuTrigger } from "react-contextmenu"; 


function File(props) {
    let [selected, setSelected] = useState(false)
    let [open, setOpen] = useState(false)
    
    function openFolder() {
        if (props.isDirectory)
            setOpen(!open)
    }

    function select() {
        setSelected(!selected)
        console.log(props.name, " ", selected)
    }

    function image() {
        let path
        if (props.isDirectory) {
            if (!open) 
                path = "folder.png"
            else
                path = "opened-folder.png"
        }
        else path = "file.png"

        return <img src={path}></img>
    }

    return(
        <div>
            
            <button className="file well" type="button" onDoubleClick={openFolder} onClick={select}>
            {/* <ContextMenuTrigger id="context-menu" name={props.name} attributes={attributes}> */}
                 {image()} { props.name }
            {/* </ContextMenuTrigger> */}
            </button>

            <Menu fileId={props.fileId} name={props.name} hidden={!selected} isDirectory={props.isDirectory}/>
            <ul hidden={!open}>
            { props.children !== undefined ?
                props.children.map(child => <File key={child.id} fileId={child.id} name={child.name} children={child.children} isDirectory={child.directory}/>)
                : null
            }
            </ul>
        </div>
    )
}

export default File