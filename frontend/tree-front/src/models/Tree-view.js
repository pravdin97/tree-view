import React, {useState, useEffect} from 'react'
import File from './File'
import Context from './Context'
import axios from 'axios'

function TreeView() {

    let [data, setData] = useState()

    useEffect(() => {
        fetch('http://localhost:8080/getTree')
            .then(response => response.json())
            .then(data => setData(data))
    }, [])

    function deleteFile(id) {
        console.log("delete ", id)

        fetch('http://localhost:8080/delete/'+id)
            .then(response => response.json())
            .then(data => setData(data))
    }

    function renameFile(id, newName) {
        console.log("rename ", id, "new name: ", newName)

        let postdata = new URLSearchParams();
        postdata.append('id',id);
        postdata.append('newName', newName);

        axios.post('http://localhost:8080/rename', postdata)
            .then(function (response) {
                console.log(response);
                setData(response.data)
            })
    }

    function moveFile(id, destId) {
        console.log("move ", id)
        
        let postdata = new URLSearchParams();
        postdata.append('id',id);
        postdata.append('destination', destId);

        axios.post('http://localhost:8080/move', postdata)
    }

    function addFile(id, name, isDirectory) {
        let postdata = new URLSearchParams();
        postdata.append('parentId',id);
        postdata.append('name', name);
        postdata.append('directory', isDirectory)

        axios.post('http://localhost:8080/add', postdata)
            .then(function (response) {
                console.log(response);
                setData(response.data)
            })
    }
    
    return (
        <Context.Provider 
        value={{deleteFile: deleteFile, 
        renameFile: renameFile, 
        addFile: addFile}} >
            <ul>
            {
                data !== undefined ? 
                    <File fileId={data.id} name={data.name} children={data.children} isDirectory={data.directory}/>
                : null
            }
            </ul>
        </Context.Provider>
    )
}

export default TreeView