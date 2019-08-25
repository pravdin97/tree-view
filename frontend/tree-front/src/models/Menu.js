import React,{useContext} from 'react'
import Context from './Context'
// import { ContextMenu, MenuItem, ContextMenuTrigger } from "react-contextmenu"; 

function Menu(props) {

    const { deleteFile } = useContext(Context)
    const { renameFile } = useContext(Context)
    const { addFile } = useContext(Context)

    function rename() {
        let newName = prompt('Введите новое название: ', props.name)
        if (newName !== null)
            renameFile(props.fileId, newName)
    }

    function create() {
        let newName = prompt('Введите название: ', '')
        if (newName !== null) {
            let dir = window.confirm('Создать папку?')
            addFile(props.fileId, newName, dir)
        }
    }

    function move() {
        console.log('move')
    }

    return(
        <div className="menu" hidden={props.hidden}>
            <button type="button" className="menu-item" 
                hidden={props.isDirectory !== undefined && !props.isDirectory}
                onClick={create}>
                Создать новый файл/папку
              </button>
            <button type="button" className="menu-item" onClick={() => deleteFile(props.fileId)}>Удалить</button>
            
            <button type="button" className="menu-item" onClick={rename}>Переименовать</button>
            <button type="button" className="menu-item" onClick={move}>Переместить</button>
        </div>

        // <div>
        //     <ContextMenu id="context-menu">
        //         <MenuItem hidden={props.isDirectory !== undefined && !props.isDirectory} onClick={create} >
        //         Добавить новый файл/папку
        //         </MenuItem>
        //         <MenuItem onClick={rename}>
        //         Переименовать
        //         </MenuItem>
        //         <MenuItem onClick={() => deleteFile(props.fileId)}>
        //         Удалить
        //         </MenuItem>
        //     </ContextMenu>
        // </div>
    )
}

export default Menu