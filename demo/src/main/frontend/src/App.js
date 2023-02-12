// src/main/frontend/src/App.js

import React, {useEffect, useState} from 'react';
import axios from 'axios';
import info from "./info";

function App() {
   const [hello, setHello] = useState('')
   const [visible, setVisible] = useState(true);

    useEffect(() => {
        axios.get('/api/hello')
        .then(response => setHello(response.data))
        .catch(error => console.log(error))
    }, []);


    function handleClick(){
        setVisible(false);
    }

    return (
    <div>
        <button onClick= {handleClick}>{visible ? "보이기" : "안보이기"}</button>
        <hr/>
        {hello}
    </div>


//        <div>
//            백엔드에서 가져온 데이터입니다 : {hello}
//        </div>
    );
}

export default App;